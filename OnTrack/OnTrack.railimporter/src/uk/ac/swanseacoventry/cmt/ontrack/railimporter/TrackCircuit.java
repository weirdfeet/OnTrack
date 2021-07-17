package uk.ac.swanseacoventry.cmt.ontrack.railimporter;

import java.util.ArrayList;
import java.util.HashMap;

public class TrackCircuit {

	private String name;
	private ArrayList<Path> paths = new ArrayList<Path>();
	//public Node startNode = null; /// WAT? Phil fix
	//public Node endNode = null;
	public int numberOfPoints = 0;
	public int numberOfCrossings = 0;
	public ArrayList<Node> endNodes = new ArrayList<Node>();
	private boolean computed = false;
	
	public TrackCircuit(String name){
		this.name = name;
		//computeEndNodes();
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Path> getPaths() {
		return this.paths;
	}
	
	public void addPaths(ArrayList<Path> ps) {
	    this.paths.addAll(ps);
	}
	
	/**
	 * Update the data to represent end nodes attached to this track circuit.
	 */
	public void computeEndNodes() {
		if (this.computed) return;

		// Go through all paths of this TC and count how many times each node occurs.
		HashMap<Node,Integer> nodeCounter = new HashMap<Node,Integer>();
		for(Path p : this.paths){
		    Node startNode = p.getStartNode();
		    Node endNode = p.getEndNode();
		    
			// initialising
			if (!nodeCounter.containsKey(startNode)) {
			    nodeCounter.put(startNode, 0);
			}
			if (!nodeCounter.containsKey(endNode)) {
			    nodeCounter.put(endNode, 0);
			}
			
			// counting
			Integer startNodeCount = nodeCounter.get(startNode);
			Integer endNodeCount = nodeCounter.get(endNode);
			nodeCounter.put(startNode, startNodeCount + 1);
			nodeCounter.put(endNode, endNodeCount + 1);
		}

		for(Node n : nodeCounter.keySet()){
		    // Keep counts of special node types
			if (n instanceof Point) this.numberOfPoints++;
			if (n instanceof Diamond) this.numberOfCrossings++;
			
			// If a node only occurs once, it is an end node.
			if (nodeCounter.get(n)==1) {
				this.endNodes.add(n);
			}
		}

		if (numberOfCrossings > 1) {
			System.out.println("WARNING: Track circuit " + this.name + " has more than one crossing! Importing may be incorrect!");
		}
		if (numberOfPoints > 2) {
			System.out.println("WARNING: Track circuit " + this.name + " has more than two points attached! Importing may be incorrect!");
		}

		this.computed = true;
	}
	
	@Override
	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append("Track Circuit Name: ");
		result.append(name);
		result.append(" Paths: ");
		for(Path p : paths){
			result.append(p.getName());
			result.append(" ");
		}
		return result.toString();
	}

}
