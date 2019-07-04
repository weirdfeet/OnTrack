package click.nullpointer.tplayout.graph;

/**
 * A Track node of a Railway Graph.
 */
public class Track extends Node {

	/**
	 * The two nodes connected to this node. 
	 */
	private Node a, b;

	/**
	 * Cached array of connections to avoid construction 
	 * and destruction each time connections are requested.
	 */
	private Node[] connections;

	/**
	 * Construct a disconnected, unnamed track. 
	 * This constructor must be used with care.
	 */
	protected Track() {}

	/**
	 * Construct a Track with a given name, and connections. 
	 * Each connection can be 'null' indicating the track is disconnected by one 
	 * or both.
	 * @param name The name of the Track
	 * @param a A connected node to this Track.
	 * @param b A different (w.r.t a) node connected to this Track.
	 */
	public Track(String name, Node a, Node b) {
		super(name);
		this.a = a;
		this.b = b;
	}

	/**
	 * Get one of the connected nodes
	 */
	public Node getConnectionA() {
		return a;
	}

	/**
	 * Set one of the connected nodes to this Track.
	 * If the node is already connected with the parameter node an exception
	 * will be raised.
	 * @param a The new connected node.
	 */
	public void setConnectionA(Node a) {
		if (a == b)
			throw new IllegalArgumentException("Already connected with " + a);
		this.a = a;
	}

	/**
	 * Get one of the connected nodes
	 */
	public Node getConnectionB() {
		return b;
	}

	/**
	 * Set one of the connected nodes to this Track.
	 * If the node is already connected with the parameter node an exception
	 * will be raised.
	 * @param b The new connected node.
	 */
	public void setConnectionB(Node b) {
		if (a == b)
			throw new IllegalArgumentException("Already connected with " + b);
		this.b = b;
	}

	@Override
	public Node[] getConnections() {
		if (connections == null)
			connections = new Node[] { a, b };
		return connections;
	}

}
