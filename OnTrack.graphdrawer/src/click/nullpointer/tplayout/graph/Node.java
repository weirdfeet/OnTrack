package click.nullpointer.tplayout.graph;

/**
 * An abstract Node of a RailwayGraph. 
 */
public abstract class Node implements Comparable<Node> {
	/**
	 * Node ID counter. Each node constructed has a unique ID used internally.
	 */
	private static long idCounter = 0;
	
	/**
	 * The x and y coordinates of this Node.
	 */
	public int x, y;
	
	/**
	 * The name of this Node
	 */
	protected String name;
	
	/**
	 * The unique ID of this particular instance of a Node
	 */
	protected long id;
	
	/**
	 * Construct a Node with a given name.
	 * @param name The name of the node.
	 */
	public Node(String name) {
		this.name = name;
		this.id = idCounter++;
	}

	/**
	 * Construct a nameless Node.
	 * This constructor should be used with care.
	 */
	protected Node() {
		this.id = idCounter++;
	}

	/**
	 * Get all Nodes connected to this Node.
	 * @return An array of Nodes that have an edge to this Node.
	 */
	public abstract Node[] getConnections();
	
	/**
	 * Get the name of this node.
	 * @return The name of the node.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of this node.
	 * @param name The new name of the node.
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[" + getName() + "] ";
	}

	/**
	 * {@inheritDoc}
	 * Compare this node to some other Node. The comparison of the two 
	 * is purely based on time of construction (Earliest to Latest)
	 */
	@Override
	public int compareTo(Node o) {
		//Compare incremental unique IDs
		return Long.compare(id, o.id);
	}

	/**
	 * Provides a detailed String representation for this node with as much 
	 * @return A detailed String representation of this node.
	 */
	public String toDetailedString() {
		return toString() + " at (" + x + "," + y + ")";
	}
}
