package uk.ac.swanseacoventry.cmt.ontrack.railimporter;

import java.util.ArrayList;
import java.util.HashMap;

public class TrackCircuit {

	private String name;
	private ArrayList<Path> paths = new ArrayList<Path>();
	public Node startNode = null; /// WAT? Phil fix
	public Node endNode = null;
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
			if (counter.containsKey(p.getStartNode()))
				counter.put(p.getStartNode(), counter.get(p.getStartNode()) + 1);
			else
				counter.put(p.getStartNode(), 1);
			if (counter.containsKey(p.getEndNode()))
				counter.put(p.getEndNode(), counter.get(p.getEndNode()) + 1);
			else
				counter.put(p.getEndNode(), 1);
		}
		startNode = null;
		endNode = null;
		for(Node n : counter.keySet()){
			if (startNode==null) {
				if (counter.get(n)==1) startNode = n;
			}
			else if (counter.get(n)==1) {
				endNode = n;
				break;
			}
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
