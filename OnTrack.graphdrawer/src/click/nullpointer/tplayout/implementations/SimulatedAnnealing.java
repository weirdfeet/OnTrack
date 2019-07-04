package click.nullpointer.tplayout.implementations;

import static click.nullpointer.tplayout.WatchedSystemProperties.globalInstance;
import static click.nullpointer.tplayout.WatchedSystemProperties.p;
import static click.nullpointer.tplayout.implementations.SimulatedAnnealing.SAProperties.AIM_ONLY_FOR_VALID_RESULT;
import static click.nullpointer.tplayout.implementations.SimulatedAnnealing.SAProperties.DISCONNECTED_NODE_SCORE;
import static click.nullpointer.tplayout.implementations.SimulatedAnnealing.SAProperties.OVERLAPING_NODE_SCORE;
import static click.nullpointer.tplayout.implementations.SimulatedAnnealing.SAProperties.POINT_REVERSE_TOO_CLOSE_TO_DISCONNECTED_NODE;
import static click.nullpointer.tplayout.implementations.SimulatedAnnealing.SAProperties.STARTING_TEMPERATURE_MULTIPLIER;
import static click.nullpointer.tplayout.implementations.SimulatedAnnealing.SAProperties.TEMPERATURE_GAMMA_CONSTANT;
import static click.nullpointer.tplayout.implementations.SimulatedAnnealing.SAProperties.TEMPERATURE_ITERATION_MULTIPLIER;
import static click.nullpointer.tplayout.implementations.SimulatedAnnealing.SAProperties.initialize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

import click.nullpointer.tplayout.IProgressObserver;
import click.nullpointer.tplayout.LayoutAlgorithm;
import click.nullpointer.tplayout.graph.Node;
import click.nullpointer.tplayout.graph.Point;
import click.nullpointer.tplayout.graph.Point.PointType;
import click.nullpointer.tplayout.graph.Track;
import click.nullpointer.tplayout.performance.FastNodeCoordinateTracker;
import click.nullpointer.tplayout.performance.FastVisitedNodeTracker;
import click.nullpointer.tplayout.util.GraphUtilities;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;

public class SimulatedAnnealing extends LayoutAlgorithm {

	static {
		initialize();
	}

	/**
	 * To avoid constantly creating and destroying arrays (alloc and dealoc of
	 * memory) a shared buffer to hold x,y coordinates is allocated here that is
	 * later used by {@link #assignCoordinates(Node, int, int, boolean)}
	 */
	private final int[] buffer = new int[2];// used to store x,y coordinates in #updateGridr for efficiency.

	/**
	 * Used to keep track of the positions of nodes in an efficient way.
	 */
	private final FastNodeCoordinateTracker nct = new FastNodeCoordinateTracker();

	/**
	 * Used internally for node visit tracking, stored globaly to prevent
	 * construction/destruction of objects
	 */
	private final FastVisitedNodeTracker visited;
	private final ThreadLocalRandom random = ThreadLocalRandom.current();

	/**
	 * List of all nodes connected to the graph as defined by traversing from the
	 * node provided during construction
	 */
	private final List<Node> allNodes;
	/**
	 * List of all Points in this graph. MUST be a RandomAccess list.
	 */
	private final List<Point> points;

	/**
	 * Counts the number of iterations performed so far attemtping to find a layout.
	 */
	private long currentIteration;

	/**
	 * Used as a temperature counter whilst running.
	 */
	private int temperature;

	// Frequently used, user-defined constants.
	private final int scoreOverlapCost = p(OVERLAPING_NODE_SCORE, Integer.class);
	private final int scoreDisconnectedNode = p(DISCONNECTED_NODE_SCORE, Integer.class);
	private final int scoreReverseTooClose = p(POINT_REVERSE_TOO_CLOSE_TO_DISCONNECTED_NODE, Integer.class);
	private final float temperatureIterationMult = p(TEMPERATURE_ITERATION_MULTIPLIER, Float.class);
	private final float temperatureGammaConst = p(TEMPERATURE_GAMMA_CONSTANT, Float.class);

	/**
	 * Construct a simulated annealing layout algorithm
	 * 
	 * @param graph
	 * @see {@link LayoutAlgorithm#LayoutAlgorithm(Node)}
	 */
	public SimulatedAnnealing(Node graph) {
		super(graph);
		GraphUtilities.initializePointTypes(null, graph);
		this.allNodes = new ArrayList<>();
		GraphUtilities.traverse(graph, allNodes::add);
		this.temperature = (int) (p(STARTING_TEMPERATURE_MULTIPLIER, Float.class) * allNodes.size());
		this.visited = new FastVisitedNodeTracker(allNodes);
		this.points = new ArrayList<>();
		allNodes.stream().filter(a -> a instanceof Point).map(a -> (Point) a).forEach(points::add);
		updateGrid(); // initialization step. Gives nodes a starting x,y position.
	}

	/**
	 * Updates the coordinates of each node in the graph.
	 */
	private void updateGrid() {
		assignCoordinates(graph, 0, 0, true);
		visited.clear();
	}

	/**
	 * Temperature reduction fucntion. This function may not reduce the temperature
	 * upon every invocation, and currently relies on the number of iterations the
	 * system has undergone so far.
	 */
	private void decreaseTemperature() {
		if (currentIteration > 0 && currentIteration % (temperatureIterationMult * temperature) == 0) {
			temperature *= temperatureGammaConst;
		}
	}

	/**
	 * neibourghood function.
	 */
	private void neibourgh() {
		// 1..(min(N,P) changes where n=temperature, and p = pointcount
		int end = random.nextInt(1, Math.min(points.size(), temperature) + 1);
		for (int i = 0; i < end; i++) {
			Point c = points.get(random.nextInt(points.size()));
			_pointNeibourghInPlace(c);
		}
	}

	/**
	 * Neibourghood sub-function applicable to an individual point from the graph,
	 * and changing it's type.
	 * 
	 * @param p The point to update.
	 */
	private void _pointNeibourghInPlace(Point p) {
		PointType x = p.getType();
		p.setType(p.getType().other());
	}

	// 0 = average, -2^63 is worst, +2^63 -1 is best.
	// -? Can there be intersections without overlaps? (Yes)
	// --? Can there be intersections in our case without overlaps?
	/**
	 * Scoring function. This function will score the graph provided to this object
	 * during construction.
	 * 
	 * @return the score as a long. Maximally the score will be zero but this is
	 * subject to change.
	 */
	private long score() {
		long score = 0;
		score += nct.overlapCount() * scoreOverlapCost;
		score += _scoreCountDisconnectedNodes() * scoreDisconnectedNode;
		score += _scoreCountPointReverseTooClose() * scoreReverseTooClose;
		return score;
	}

	/**
	 * Scoring sub-method, that counds the number of point branches that grapphically appear
	 * next to nodes they are not connected to giving the false impression that they are connected.
	 * @return the number of such occurances in the current configuration.
	 */
	private int _scoreCountPointReverseTooClose() { // O(P) where P = num of points.
		int tooClose = 0;
		for (Point p : points) {
			Node branch = p.getBranchNode();
			if (branch == null)
				continue;
			int nextSlotX = branch.x;
			if (p.getType().isRight())
				nextSlotX--;
			else
				nextSlotX++;
			if (!nct.getAt(nextSlotX, branch.y).isEmpty())
				tooClose++;
		}
		return tooClose;
	}

	/*
	 * IDEA: For each node, get it's position, and compute the expected position of
	 * the nodes connected to it making sure they are as expected.
	 * 
	 * OPTIMIZATION: Keep track of 'confirmed' nodes to reduce O(N^2) to O(N) by
	 * removing redundant checks.
	 */
	private int _scoreCountDisconnectedNodes() { // O(N^2) where N is number of nodes.
		int disconnected = 0;
		Node a, b;
		for (Node n : allNodes) {
			if (n instanceof Track) {
				Track t = (Track) n;
				a = t.getConnectionA();
				b = t.getConnectionB();
			} else {
				Point p = (Point) n;
				// Pick only the main track of the point.
				a = p.getEntry();
				b = p.getOppositeOfEntryNode();
			}
			// It wont be the case that the X values are the same even if Ys are different

			if (a != null) {
				if (n.x == a.x)
					disconnected++;
				if (a instanceof Track) {
					if (a.y != n.y)
						disconnected++;
				} else {
					Point p = (Point) a;
					if (p.getBranchNode() == n) {
						PointType r = p.getType();
						int expy = r.isBottom() ? 1 : -1;
						if (p.y != n.y + expy)
							disconnected++;
					} else if (p.y != n.y)
						disconnected++;
				}
			}
			if (b != null) {
				if (b != null && n.x == b.x)
					disconnected++;
				if (b instanceof Track) {
					if (b.y != n.y)
						disconnected++;
				} else {// its a point!
					Point p = (Point) b;
					if (p.getBranchNode() == n) {
						PointType r = p.getType();
						int expy = r.isBottom() ? 1 : -1;
						if (p.y != n.y + expy)
							disconnected++;
					} else if (p.y != n.y)
						disconnected++;
				}
			}
		}
		return disconnected;
	}

	@Override
	public void performLayout(IProgressObserver progress) {
		boolean aimOnlyForValid = p(AIM_ONLY_FOR_VALID_RESULT);
		// Last known good configuration as a mapping of nodes to corresponding points
		Object2LongOpenHashMap<Node> configuration = new Object2LongOpenHashMap<>(allNodes.size());
		// Map of all points to their types for the best known configuration
		HashMap<Node, PointType> points = new HashMap<>(this.points.size());
		// Best known score (held by the above configuration) cached to avoid repeated
		// invocation of scoring function
		long bestScore = Long.MIN_VALUE;

		progress.beginTask("Searching for valid layout...", temperature);
		// Main loop, running while the temperature has not reached zero.
		for (currentIteration = 0; temperature > 0; currentIteration++) {
			// Change current layout to a neibourgh
			neibourgh();
			// Update the coordinates of each node after making the change
			updateGrid();
			// score the new layout
			long score = score();

			// if either this is a better layout, or a probabilistic move to escape local
			// maxima should be made.
			if (score > bestScore || score != bestScore
					&& random.nextDouble() < Math.pow(Math.E, (score - bestScore) / temperature)) {
				// Update the best score, and best knwon configuration.
				bestScore = score;
				for (Node n : allNodes) {
					configuration.put(n, GraphUtilities.composeCoordinates(n.x, n.y));
					if (n instanceof Point)
						points.put((Point) n, ((Point) n).getType());
				}
				// Typicaly when the score is maximized we should stop but there is a setting
				// to force this algorithm to continue running until the temperature is
				// minimized.
				if (score >= 0 && aimOnlyForValid)
					break;
			}
			// Invoke a temperature reduction.
			int temper = temperature;
			decreaseTemperature();
			if (temper != temperature)
				progress.worked(1);
		}
		progress.done();
		progress.beginTask("Applying found configuration", 1);
		// At this stage we potentially have some good configuration so go and apply it
		// to the nodes, and this concludes the layout process of this algorithm.
		for (Node n : configuration.keySet()) {
			GraphUtilities.decomposeCoordinates(configuration.getLong(n), buffer);
			tp(n, buffer[0], buffer[1]);// update the internals too.
			if (n instanceof Point)
				((Point) n).setType(points.get(n));
		}
		progress.worked(1);
		progress.done();
	}

	@Override
	public Optional<Node> getResult() {
		return Optional.of(graph);
	}

	/**
	 * 'Teleport' a node to the given coordinates
	 * 
	 * @param n The node to move
	 * @param x The X coordinate
	 * @param y The Y coordinate
	 */
	private void tp(Node n, int x, int y) {
		nct.removeAt(n.x, n.y, n);
		nct.addAt(x, y, n);
		n.x = x;
		n.y = y;
	}

	/**
	 * Recursively explores the graph, updating the x,y coordinates of each node to
	 * the expected coordinate based on previous nodes. This function will not try
	 * to 'correct' any disconnections.
	 * 
	 * @param g The starting point of the traversal (any node in the graph)
	 * @param x The starting x coordinate (typically 0)
	 * @param y The starting y coordinate (typically 0)
	 * @param goRight should be set to 'true' for initial calls.
	 */
	private void assignCoordinates(Node g, int x, int y, boolean goRight) {
		if (g == null)
			return;
		if (visited.hasVisited(g))
			return;
		visited.visit(g);
		tp(g, x, y);
		Node[] con = g.getConnections();
		if (g instanceof Point) {
			Point p = (Point) g;
			if (!visited.hasVisited(p.getBranchNode())) {
				coordinatesOfConnected(p.getBranchNode(), g, x, y, p.getType().isRight(), buffer);
				assignCoordinates(p.getBranchNode(), buffer[0], buffer[1], p.getType().isRight());
			}
			if (!visited.hasVisited(p.getOppositeOfEntryNode())) {
				coordinatesOfConnected(p.getOppositeOfEntryNode(), g, x, y, p.getType().isRight(), buffer);
				assignCoordinates(p.getOppositeOfEntryNode(), buffer[0], buffer[1], p.getType().isRight());
			}
			if (!visited.hasVisited(p.getEntry())) {
				coordinatesOfConnected(p.getEntry(), g, x, y, goRight, buffer);
				assignCoordinates(p.getEntry(), buffer[0], buffer[1], goRight);
			}
		} else {
			for (int i = 0; i < con.length; i++) {
				Node o = con[i];
				coordinatesOfConnected(o, g, x, y, goRight, buffer);
				assignCoordinates(o, buffer[0], buffer[1], goRight);
			}
		}
	}

	/**
	 * A method used to compute the coordinates of some node.
	 * 
	 * @param of The node to compute the coordinates of.
	 * @param current The node we are currently on (which must be connected to the
	 * one we are requesting the coordinates of)
	 * @param x the current x value value of the traversal.
	 * @param y the current y value of the traversal.
	 * @param right true if the traversal is moving to the right.
	 * @param buffer array buffer of at least 2 length to store the x and y
	 * coordinates (indexes 0 and 1)
	 */
	private void coordinatesOfConnected(Node of, Node current, int x, int y, boolean right, int[] buffer) {
		int newX = x + (right ? 1 : -1);
		int newY = y;
		if (of instanceof Point) { // Target node is a point
			Point p = (Point) of;
			PointType t = p.getType();
			if (current == p.getNormal()) {
				if (!t.isNormalSameTrackAsEntry()) {
					if (t.isBottom())
						newY++;
					else
						newY--;
				}
			} else if (current == p.getReverse()) {// If we enter from the opposite of the entry node
				if (t.isNormalSameTrackAsEntry()) {
					if (t.isBottom())
						newY++;
					else
						newY--;
				}
			}
		}
		if (current instanceof Point) {// We are on a point
			Point p = (Point) current;
			PointType t = p.getType();
			if (of == p.getNormal()) { // We are at the branch
				if (!t.isNormalSameTrackAsEntry()) {
					if (t.isBottom()) {
						newY--;
					} else {
						newY++;
					}
				}
			} else if (of == p.getReverse()) {
				if (t.isNormalSameTrackAsEntry()) {
					if (t.isBottom()) {
						newY--;
					} else {
						newY++;
					}
				}
			}
		}
		buffer[0] = newX;
		buffer[1] = newY;
	}

	/**
	 * Enum constants to store properties of the SimulatedAnealing algorithm for
	 * Global property system.
	 */
	public static enum SAProperties {
		DISCONNECTED_NODE_SCORE(-100), INTERSECTING_NODES_SCORE(-500), OVERLAPING_NODE_SCORE(
				-1000), AIM_ONLY_FOR_VALID_RESULT(false), POINT_REVERSE_TOO_CLOSE_TO_DISCONNECTED_NODE(-100),
		// c*node count because when allowing expantions, this will be useful
		// Without track expantions n would be appropriate (n=node count)
		STARTING_TEMPERATURE_MULTIPLIER(1F),
		// T=T-T*γ
		TEMPERATURE_GAMMA_CONSTANT(0.75F, (Float a) -> a >= 0.15F && a < 1F, Float.class),
		// Reduce temperature after n many iterations where n:
		TEMPERATURE_ITERATION_MULTIPLIER(3F, (Float a) -> a > 0, Float.class);

		private final Object def;
		private final Class<?> expType;
		private final Predicate<Object> valueChecker;

		private SAProperties(Object val) {
			this(val, val.getClass());
		}

		private SAProperties(Object val, Class<?> type) {
			this(val, null, type);
		}

		@SuppressWarnings("unchecked")
		private <P> SAProperties(Object val, Predicate<P> validation, Class<?> type) {
			this.def = val;
			this.expType = type;
			this.valueChecker = (Predicate<Object>) validation;
		}

		@Override
		public String toString() {
			return getClass().getSimpleName() + "." + super.toString();
		}

		public static void initialize() {
			for (SAProperties p : values()) {
				if (globalInstance().containsKey(p))
					continue;
				if (globalInstance().containsKey(p)) {
					globalInstance().watchType(p, p.expType);
				} else
					globalInstance().initialize(p, p.def, p.expType);
				if (p.valueChecker != null)
					globalInstance().enableValidation(p, p.valueChecker);
			}
		}
	}
}
