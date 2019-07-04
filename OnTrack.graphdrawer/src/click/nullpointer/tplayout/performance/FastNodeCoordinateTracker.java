package click.nullpointer.tplayout.performance;

import java.util.Collection;
import java.util.Collections;

import click.nullpointer.tplayout.graph.Node;
import click.nullpointer.tplayout.util.GraphUtilities;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongRBTreeSet;
import it.unimi.dsi.fastutil.objects.ObjectRBTreeSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;

/**
 * This class is designed to hold coordinate information for a set of nodes.
 */
public class FastNodeCoordinateTracker {

	/**
	 * Mapping a coordinate long to a Red/Black treeset of nodes. 
	 * this is essentially mapping a coordinate (x,y) to a collection of ndoes that are there.
	 */
	private final Long2ObjectOpenHashMap<ObjectRBTreeSet<Node>> dynamicNodeGrid = new Long2ObjectOpenHashMap<>();
	
	/**
	 * Red/Black tree (self balancing)  to keep track of coordinates where overlaps occur
	 * for easy retreival.
	 */
	private final LongRBTreeSet overlapsAtCoordinates = new LongRBTreeSet();

	/**
	 * Add a node at a given coordinate. Other nodes at this coordinate will
	 * be left untouched. This node must not be at any other position in the cuboid.
	 * @param x The X coordinate.
	 * @param y The Y coordinate.
	 * @param n The node to add.
	 */
	public void addAt(int x, int y, Node n) {// O(log n)
		long l = GraphUtilities.composeCoordinates(x, y);
		ObjectRBTreeSet<Node> s = dynamicNodeGrid.get(l);
		if (s == null) {
			s = new ObjectRBTreeSet<>();
			dynamicNodeGrid.put(l, s);
		}
		s.add(n);
		if (s.size() > 1)
			overlapsAtCoordinates.add(l);
	}

	/**
	 * Check if overlaps appear, that is if at any x,y coordinate two nodes are placed.
	 * @return true if at least one node overlaps, false otherwise
	 */
	public boolean areThereOverlaps() {
		return !overlapsAtCoordinates.isEmpty();
	}
	
	/**
	 * Remove a node from a place in the grid. 
	 * @param x The X coordinate of the node,
	 * @param y The Y coodinate of the node.
	 * @param n The node to be removed from these coordinates.
	 */
	public void removeAt(int x, int y, Node n) {// O(log n)
		long l = GraphUtilities.composeCoordinates(x, y);
		ObjectRBTreeSet<Node> s = dynamicNodeGrid.get(l);
		if (s == null)
			return;
		s.remove(n);
		if (s.isEmpty())
			dynamicNodeGrid.remove(l);
		else if (s.size() == 1)
			overlapsAtCoordinates.remove(l);
	}

	/**
	 * Get the colleciton of nodes that lye at a given x,y coordinate.
	 * @param x The X coordinate. 
	 * @param y the Y coordinate. 
	 * @return A collection of nodes that appear at this position which is unmodifiable but may 
	 * be null
	 */
	public Collection<Node> getAt(int x, int y) {// O(1)
		long l = GraphUtilities.composeCoordinates(x, y);
		if (dynamicNodeGrid.get(l) == null)
			return ObjectSets.emptySet();
		return Collections.unmodifiableCollection(dynamicNodeGrid.get(l));
	}

	/**
	 * Check the number of overlaps that are knwon. 
	 * @return The number of overlaps;
	 */
	public int overlapCount() {
		return overlapsAtCoordinates.size();
	}

}
