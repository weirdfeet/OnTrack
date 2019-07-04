package click.nullpointer.tplayout.util;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

import click.nullpointer.tplayout.graph.Node;
import click.nullpointer.tplayout.graph.Point;
import click.nullpointer.tplayout.graph.Point.PointType;

public class GraphUtilities {

	public static final Function<Node, Boolean> EMPTY_CONSUMER = a -> true;

	public static void initializePointTypes(Node before, Node current) {
		initializePointTypes(before, current, true, new HashSet<>());
	}

	public static void initializePointTypes(Node before, Node current, boolean right, Set<Node> visited) {
		if (current == null || visited.contains(current))
			return;
		visited.add(current);
		if (current instanceof Point) {
			Point p = (Point) current;
			setPointType(p, right, p.getEntry() == before);

			initializePointTypes(current, p.getBranchNode(), p.getType().isRight(), visited);
			if (!visited.contains(p.getOppositeOfEntryNode()))
				initializePointTypes(current, p.getOppositeOfEntryNode(), p.getType().isRight(), visited);
			if (!visited.contains(p.getEntry()))
				initializePointTypes(current, p.getEntry(), right, visited);
		} else {
			for (Node n : current.getConnections()) {
				if (!visited.contains(n)) {
					initializePointTypes(current, n, right, visited);
				}
			}
		}
	}

	private static void setPointType(Point p, boolean goRight, boolean enterFromEntry) {
		/*
		 * If we are going towards the right and we find a point which we enter from
		 * normal or reverse
		 * OR we are going towards the left and we find a point which we enter from
		 * entry
		 * Then the point's entry is on the right
		 */
		if ((goRight && !enterFromEntry) || (!goRight && enterFromEntry)) {
			p.setType(PointType.RIGHT_ENTRY_BOTTOM_EN);// RIGHT entry
		}
		if ((goRight && enterFromEntry) || (!goRight && !enterFromEntry)) {
			p.setType(PointType.LEFT_ENTRY_BOTTOM_EN); // LEFT entry
		}
		// System.out.println(p.getName() + " -> " + p.getType().toSimpleString());
	}

	public static long composeCoordinates(int x, int y) {// O(1)
		return (((long) x) << 32) | (y & 0xFFFFFFFFL);
	}

	public static int[] decomposeCoordinates(long l) {
		return decomposeCoordinates(l, new int[2]);
	}

	public static int[] decomposeCoordinates(long l, int[] twoSlots) {
		int x = (int) (l >> 32);
		int y = (int) l;
		twoSlots[0] = x;
		twoSlots[1] = y;
		return twoSlots;
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	/**
	* Normalize a given graph, moving all nodes by a constant such that at least one
	* node will be in position (X,Y)
	* @param x minimum x
	* @param y minimum y
	* @param start the start node for the traversal.
	*/
	public static void normalizeGraph(Node start, int x, int y) {
		AtomicInteger minX = new AtomicInteger(Integer.MAX_VALUE);
		AtomicInteger minY = new AtomicInteger(Integer.MAX_VALUE);
		traverse(start, (a) -> {
			minX.lazySet(Math.min(minX.get(), a.x));
			minY.lazySet(Math.min(minY.get(), a.y));
			return true;
		});
		traverse(start, (a) -> {
			a.x -= minX.get() + x;
			a.y -= minY.get() + y;
			return true;
		});
	}

	public static int[] getGraphWidthAndHeight(Node start) {
		AtomicInteger maxX = new AtomicInteger(Integer.MIN_VALUE);
		AtomicInteger maxY = new AtomicInteger(Integer.MIN_VALUE);
		traverse(start, (a) -> {
			maxX.lazySet(Math.max(maxX.get(), Math.abs(a.x)));
			maxY.lazySet(Math.max(maxY.get(), Math.abs(a.y)));
			return true;
		});
		return new int[] { maxX.get(), maxY.get() };
	}

	public static void traverse(Node aNode, Function<Node, Boolean> consumer) {
		traverseImpl(aNode, new HashSet<Node>(), consumer);
	}

	public static Node findStartingNode(Node start) {
		AtomicReference<Node> r = new AtomicReference<>(null);
		traverse(start, a -> {
			if (a instanceof Point) {
				Point p = (Point) a;
				if (p.getEntry() == null || p.getOppositeOfEntryNode() == null) {
					r.set(p);
					return false;
				}
			} else {
				if (a.getConnections()[0] == null || a.getConnections()[1] == null) {
					r.set(a);
					return false;
				}
			}
			return true;
		});
		return r.get();
	}

	public static void traverseImpl(Node start, Set<Node> visited, Function<Node, Boolean> consume) {
		if (start == null || visited.contains(start))
			return;
		visited.add(start);
		if (!consume.apply(start))
			return;
		for (Node n : start.getConnections()) {
			if (!visited.contains(n))
				traverseImpl(n, visited, consume);
		}
	}

}
