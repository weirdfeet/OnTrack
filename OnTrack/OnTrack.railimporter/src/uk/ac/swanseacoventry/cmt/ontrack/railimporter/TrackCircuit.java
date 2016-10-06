package uk.ac.swanseacoventry.cmt.ontrack.railimporter;

import java.util.ArrayList;

public class TrackCircuit {

	private String name;
	private ArrayList<Path> paths;
	
	protected TrackCircuit(String name, ArrayList<Path> paths){
		this.name = name;
		this.paths = paths;
	}
	public String getName() {
		return name;
	}
	public ArrayList<Path> getPaths() {
		return paths;
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
