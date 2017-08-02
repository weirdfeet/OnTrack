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
		return paths;
	}
	
	public void computeEndNodes() {
		if (computed) return;
		HashMap<Node,Integer> counter = new HashMap<Node,Integer>();
		for(Path p : paths){
			// initialising
			if (!counter.containsKey(p.getStartNode())) counter.put(p.getStartNode(), 0);
			if (!counter.containsKey(p.getEndNode())) counter.put(p.getEndNode(), 0);
			
			// counting
			counter.put(p.getStartNode(), counter.get(p.getStartNode()) + 1);
			counter.put(p.getEndNode(), counter.get(p.getEndNode()) + 1);
		}
		for(Node n : counter.keySet()){
			if (n instanceof Point) numberOfPoints++;
			if (n instanceof Diamond) numberOfCrossings++;
			if (counter.get(n)==1) {
				endNodes.add(n);
			}
		}
		if (numberOfCrossings>1) {
			System.out.println("WARNING: Track circuit " + this.name + " has more than one crossings! Importing may be incorrect!");
		}
		if (numberOfPoints>2) {
			System.out.println("WARNING: Track circuit " + this.name + " has more than two points attached! Importing may be incorrect!");
		}
		computed = true;
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
