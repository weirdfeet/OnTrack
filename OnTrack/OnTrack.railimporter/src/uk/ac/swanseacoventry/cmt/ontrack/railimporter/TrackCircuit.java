package uk.ac.swanseacoventry.cmt.ontrack.railimporter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TrackCircuit {

	private String name;
	private ArrayList<Path> paths;
	public Node startNode = null;
	public Node endNode = null;
	private boolean computed = false;
	
	protected TrackCircuit(String name, ArrayList<Path> paths){
		this.name = name;
		this.paths = paths;
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

	public static class TrackCircuitBuilder 
    {
		private String name;
		private ArrayList<Path> paths;
		
        public TrackCircuitBuilder(String name) 
        {
			this.name = name;
			paths = new ArrayList<Path>();
		}
		
		public TrackCircuitBuilder addPath(Path p)
		{
			paths.add(p);
			return this;
		}

		public TrackCircuitBuilder setPaths(ArrayList<Path> paths)
		{
			this.paths = paths;
			return this;
		}
		
		public TrackCircuit createTrackCircuit()
		{
			return new TrackCircuit(name, paths);
		}
    }
}
