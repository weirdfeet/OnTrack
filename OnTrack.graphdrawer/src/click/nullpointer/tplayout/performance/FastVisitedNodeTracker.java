package click.nullpointer.tplayout.performance;

import java.util.Arrays;
import java.util.Collection;

import click.nullpointer.tplayout.graph.Node;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;

/**
 * A class designed to be used in keeping track of nodes that have been visited.
 * This class can be used with time-sensitive traversals as it aims to minimize
 * the time and memory required to track the visited status of a node.
 */
public class FastVisitedNodeTracker {

	/**
	 * Mapping of nodes to incremental IDs used as indexes.
	 */
	private final Object2IntOpenHashMap<Node> mapping;

	/**
	 * Array of booleans keeping track of the visited status of each node.
	 */
	private final boolean[] bits;

	/**
	 * Construct a {@link FastVisitedNodeTracker} for a given colleciton of Nodes.
	 * @param nodes The nodes to track.
	 */
	public FastVisitedNodeTracker(Collection<Node> nodes) {
		this.mapping = new Object2IntOpenHashMap<>();
		int i = 0;
		for (Node n : nodes)
			mapping.put(n, i++);
		this.bits = new boolean[nodes.size()];
	}

	private boolean hasVisited(int index) {
		return bits[index];
	}

	/**
	 * Check if a node has been marked as visited.
	 * @param n The node to check.
	 * @return true if the node has been marked as visited, false otherwise.
	 */
	public boolean hasVisited(Node n) {
		return hasVisited(mapping.getInt(n));
	}

	private void visit(int index) {
		bits[index] = true;
	}

	/**
	 * Mark a node as visited.
	 * @param n The node to visit.
	 */
	public void visit(Node n) {
		visit(mapping.getInt(n));
	}

	/**
	 * Reset the visited status of all tracked nodes to false.
	 */
	public void clear() {
		Arrays.fill(bits, false);
	}

}
