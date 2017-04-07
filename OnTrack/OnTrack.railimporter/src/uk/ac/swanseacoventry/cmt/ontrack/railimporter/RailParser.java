package uk.ac.swanseacoventry.cmt.ontrack.railimporter;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


import java.util.ArrayList;
import java.util.HashMap; 
 
/// nice command line flags
/// change delimeter, also ask first line?
/// nice menu options!
/// Exception when nodes not found not null!
/// All tostring consistent
/// good builder constructirs...
/// Check all parses create valid objects


public class RailParser
{
	private final String DELIMITER = ",";
	private HashMap<String,Node> nodes = new HashMap<String,Node>();
	private HashMap<String,Path> paths = new HashMap<String,Path>();
	private HashMap<String,TrackCircuit> tracks = new HashMap<String,TrackCircuit>();
	private HashMap<String,Route> routes = new HashMap<String,Route>();
	private ArrayList<Node> missingNodes = new ArrayList<Node>();
	private ArrayList<TrackCircuit> missingTracks = new ArrayList<TrackCircuit>();
	

	public RailParser()
	{
				
	}

	
	public HashMap<String,Node> getNodes() {
		return nodes;
	}

	public void setNodes(HashMap<String,Node> nodes) {
		this.nodes = nodes;
	}

	public HashMap<String,Path> getPaths() {
		return paths;
	}

	public void setPaths(HashMap<String,Path> paths) {
		this.paths = paths;
	}

	public HashMap<String,TrackCircuit> getTracks() {
		return tracks;
	}

	public void setTracks(HashMap<String,TrackCircuit> tracks) {
		this.tracks = tracks;
	}

	public HashMap<String,Route> getRoutes() {
		return routes;
	}

	public void setRoutes(HashMap<String,Route> routes) {
		this.routes = routes;
	}

	public ArrayList<Node> getMissingNodes() {
		return missingNodes;
	}

	public void setMissingNodes(ArrayList<Node> missingNodes) {
		this.missingNodes = missingNodes;
	}

	public ArrayList<TrackCircuit> getMissingTracks() {
		return missingTracks;
	}

	public void setMissingTracks(ArrayList<TrackCircuit> missingTracks) {
		this.missingTracks = missingTracks;
	}

	public void  parseNodes(String filename) throws IOException{
		File f = new File(filename);
		Scanner scanner = new Scanner(f);
		//Ignore first line
		String line = scanner.nextLine();
		while (scanner.hasNextLine())
        {
			line = scanner.nextLine();
			String [] parts = line.split(DELIMITER,-1);
            
			Node.NodeBuilder n = new Node.NodeBuilder(parts[0].trim());
			n.setLocation(Double.valueOf(parts[1].trim()));
			n.setPaths(Double.valueOf(parts[2].trim()));
			n.setId(parts[3].trim());
			n.setDescription(parts[4].trim());
		    Node created = n.createNode();
			nodes.put(created.getName(), created);
			System.out.println("Adding: " + created.toString());
        }
         
        //Do not forget to close the scanner 
        scanner.close();
	}
	
		
	public void parsePoints(String filename) throws IOException{
		File f = new File(filename);
		Scanner scanner = new Scanner(f);
		
		String line = scanner.nextLine();
		while (scanner.hasNextLine())
        {
			line = scanner.nextLine();
			String [] parts = line.split(DELIMITER, -1);
            
			Node n = nodes.get(parts[0].trim());
			
			if (n != null){
				Point.PointBuilder p = new Point.PointBuilder(n);
				p.setSwitchTime(Integer.parseInt(parts[2].trim()));
				Point created = p.createPoint();
				nodes.put(created.getName(),created);
				System.out.println("Adding: " + created.toString());
					
			}
        }
         
        //Do not forget to close the scanner 
        scanner.close();
	}

	public void parseSignals(String filename) throws IOException{
		File f = new File(filename);
		Scanner scanner = new Scanner(f);
		
		String line = scanner.nextLine();
		while (scanner.hasNextLine())
        {
			line = scanner.nextLine();
		    // deal with extra non needed info.
			if(!line.isEmpty() && !line.startsWith("CLEAR")){
				String [] parts = line.split(DELIMITER, -1);
            
				Node n = nodes.get(parts[0].trim());
				if (n != null){
					Signal.SignalBuilder s = new Signal.SignalBuilder(n);
					s.setSwitchTime(Integer.parseInt(parts[2].trim()));
					Signal created = s.createSignal();
					nodes.put(created.getName(),created);
					System.out.println("Adding: " + created.toString());
				}	
			}
        }
         
        //Do not forget to close the scanner 
        scanner.close();
		
	}
	
	public void parsePaths(String filename) throws IOException{
		File f = new File(filename);
		Scanner scanner = new Scanner(f);
		
		//Ignore first line
		String line = scanner.nextLine();
		while (scanner.hasNextLine())
        {
			line = scanner.nextLine();
			String [] parts = line.split(DELIMITER, -1);
            
			Path.PathBuilder p = new Path.PathBuilder(parts[0].trim());
			Node startNode = nodes.get(parts[1].trim());
			Node endNode = nodes.get(parts[2].trim());
			p.setStartNode(startNode);
			p.setEndNode(endNode);
			p.setLength(Double.valueOf(parts[3].trim()));
			p.setSpeedLimitUp(Double.valueOf(parts[4].trim()));
			p.setSpeedLimitDown(Double.valueOf(parts[5].trim()));
			p.setGradient(Double.valueOf(parts[6].trim()));
		    Path created = p.createPath();
			paths.put(created.getName(), created);
			System.out.println("Adding: " + created.toString());
        }
         
        //Do not forget to close the scanner 
        scanner.close();
     
	}
	
	public void parseTracks(String filename) throws IOException{
		File f = new File(filename);
		Scanner scanner = new Scanner(f);
		
		//Ignore first line
		String line = scanner.nextLine();
		while (scanner.hasNextLine())
        {
			line = scanner.nextLine();
			String[] parts = line.split(DELIMITER,-1);
			
			 
			TrackCircuit.TrackCircuitBuilder t = new TrackCircuit.TrackCircuitBuilder(parts[0].trim());
			t.setPaths(extractPaths(parts[1].trim()));
			TrackCircuit created = t.createTrackCircuit();
			
			tracks.put(created.getName(), created);
			System.out.println("Adding: " + created.toString());
        }
        //Do not forget to close the scanner 
        scanner.close();
	}
	
	public void parseRoutes(String filename) throws IOException{
		File f = new File(filename);
		Scanner scanner = new Scanner(f);
		
		//Ignore first line
		String line = scanner.nextLine();
		while (scanner.hasNextLine())
        {
			
			line = scanner.nextLine();
			Route r = parseRoute(line);
			
			
			//r.setTrackCircuits();
			//r.setOverlaps();
				
			if(r!= null){		
				routes.put(r.getName(), r);
				System.out.println("Adding: " + r.toString());
			}
			
        }
        //Do not forget to close the scanner 
        scanner.close();
	}
	
	
	public Route parseRoute(String line){
		
		String[] parts = line.split(DELIMITER, -1);
		
	//	int signalPosition = lookupNode(parts[2].trim());
		
		Signal sig = (Signal) nodes.get(parts[2].trim());
		
		Route.RouteBuilder r = new Route.RouteBuilder(parts[0].trim(), sig);

		for(int i=3; i < parts.length - 9; i++ ){
			String s = parts[i].trim();
			if(s.contains("NORMAL")){
				Point p = (Point) nodes.get(s.split(" ")[0].trim());
				if(p != null){
					r.addNormalPoint(p);
				}
				else{
					return null;
				}
			}
			else if(s.contains("REVERSE")){
				Point p = (Point) nodes.get(s.split(" ")[0].trim());
				if(p != null){
					r.addReversePoint(p);
				}	
				else{
					return null;
				}
			}
			//No overlaps so ok to assume all just in route
			else if(s.contains("TC")){
				TrackCircuit tc = tracks.get(s);
				if(tc != null){
					r.addTrackCircuit(tc);
				}	
				else{
					return null;
				}
			}
		}
		int clearTime = Integer.parseInt(parts[parts.length-4].trim()); 
		r.setClearTime(clearTime);
		return r.createRoute();
	}
	
	
	public ArrayList<Path> extractPaths(String pathString){
		ArrayList<Path> result = new ArrayList<Path>();
		
		String[] parts = pathString.split("=");
		String[] pathParts = parts[1].split(" ");
		
		 
		for(String s : pathParts){
			if(!s.isEmpty()){
				boolean found = false;
				for(Path p : paths.values()){
					if(p.getName().equals(s.trim())){
						result.add(p);
						found = true;
					}
				}
				if(!found){
					System.out.println("Warning: Path " + s + " not found.");
				}
			}
		}
		
		return result;
		
	}
	

//	public int lookupTrackCircuit(String tcName){
//		for(int i = 0; i< tracks.size(); i++){
//			TrackCircuit tc = tracks.get(i);
//			if(tc.getName().equals(tcName)){
//				return i;
//			}
//		}
//		System.out.println("Track Circuit " + tcName + " not found");
//		Scanner input = new Scanner(System.in);
//		String response = input.nextLine();
//		
//		TrackCircuit missing = new TrackCircuit.TrackCircuitBuilder(tcName).createTrackCircuit();
//
//		missingTracks.add(missing);
//
//		return -1;
//	
//	}
//	
//	public int lookupNode(String nodeName){
//		for(int i = 0; i< nodes.size(); i++){
//			Node n = nodes.get(i);
//			if(n.getName().equals(nodeName)){
//				return i;
//			}
//		}
//		System.out.println("Node " + nodeName + " not found");
//		
//		Node missing = new Node.NodeBuilder(nodeName).createNode();
//		
//		missingNodes.add(missing);
//		
//		/*Scanner input = new Scanner(System.in);
//		String response = input.nextLine();
//		if(response.equals("y")){
//			Node.NodeBuilder n = new Node.NodeBuilder(nodeName);
//			Node created = n.createNode();
//			nodes.add(created);
//			System.out.println("Adding: " + created.toString());
//			return nodes.size() - 1;
//		}
//		else{ */
//			return -1;
//		//}
//	}
	
	
	
  
}