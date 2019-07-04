package click.nullpointer.tplayout;

import java.util.Optional;

import click.nullpointer.tplayout.graph.Node;

/**
 * An abstract LayoutAlgorithm implementation.
 */
public abstract class LayoutAlgorithm {

	/**
	 * Some node from a railway graph.
	 * This node is intended to be used by sub-classes and potentially
	 * future implementations in this class.
	 */
	protected final Node graph;

	/**
	 * Construct a LayoutAlgorithm.
	 * @param graph A {@link Node} part of the graph that is to be laid out.
	 */
	public LayoutAlgorithm(Node graph) {
		this.graph = graph;
	}

	/**
	 * Searches for a suitable layout for the graph that was passed during construction
	 * of this object. Implementations of this method may modify the parameter graph.
	 */
	public abstract void performLayout(IProgressObserver progress);

	/**
	 * Optionally gets a node from the result of performing the layout.
	 * Depending on the implementation, there may be no value returned if a layout
	 * has not been found or for reasons specific to the implementation. 
	 * Implementations that modify the parameter graph must return it through this function. 
	 * @return Optionally a graph that will contain layout information.
	 */
	public abstract Optional<Node> getResult();

}
