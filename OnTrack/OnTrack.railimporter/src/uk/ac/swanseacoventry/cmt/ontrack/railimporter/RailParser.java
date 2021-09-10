package uk.ac.swanseacoventry.cmt.ontrack.railimporter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

/// nice command line flags
/// change delimeter, also ask first line?
/// nice menu options!
/// Exception when nodes not found not null!
/// All tostring consistent
/// good builder constructors...
/// Check all parses create valid objects

public class RailParser {
    private final String DELIMITER = ",|;";
    private HashMap<String, Node> nodes = new HashMap<String, Node>();
    private HashMap<String, Path> paths = new HashMap<String, Path>();
    private HashMap<String, TrackCircuit> tracks = new HashMap<String, TrackCircuit>();
    private HashMap<String, Route> routes = new HashMap<String, Route>();
    private ArrayList<Node> missingNodes = new ArrayList<Node>();
    private ArrayList<TrackCircuit> missingTracks = new ArrayList<TrackCircuit>();

    public RailParser() {

    }

    public HashMap<String, Node> getNodes() {
        return nodes;
    }

    public void setNodes(HashMap<String, Node> nodes) {
        this.nodes = nodes;
    }

    public HashMap<String, Path> getPaths() {
        return paths;
    }

    public void setPaths(HashMap<String, Path> paths) {
        this.paths = paths;
    }

    public HashMap<String, TrackCircuit> getTracks() {
        return tracks;
    }

    public void setTracks(HashMap<String, TrackCircuit> tracks) {
        this.tracks = tracks;
    }

    public HashMap<String, Route> getRoutes() {
        return routes;
    }

    public void setRoutes(HashMap<String, Route> routes) {
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

    class IntPair {
        int first = -1;
        int second = -1;
    }

    /**
     * Look for up to 2 occurances of a {@link Pattern} in an array of strings.
     * 
     * @param input Array of strings representing a row in a CSV file
     * @param start The index at which we should start looking
     * @param prefixMatched A regex {@link Pattern} to search for
     * @return If the pattern is found, indices of the first 2 occurances of the pattern (if it only 
     * occurs once, the index of the occurance is repeated); null otherwise.
     */
    private IntPair splitMatched(String[] input, int start, String prefixMatched) {
        IntPair ret = new IntPair();
        boolean found = false;
        
        for (int i = start; i < input.length; i++) {
            String p = input[i];
            if (!found) {
                if (p.matches(prefixMatched)) {
                    ret.first = i;
                    ret.second = i;
                    found = true;
                } else {
                    continue;
                }
            } else {
                if (p.matches(prefixMatched)) {
                    ret.second = i;
                } else {
                    break;
                }
            }
        }

        return ret.first >= 0 ? ret : null;
    }

    /**
     * Import (all) nodes from a file.
     * 
     * @param filename Path to a CSV file containing information about nodes
     * @throws IOException when things go wrong
     */
    public void parseNodes(String filename) throws IOException {
        File f = new File(filename);
        Scanner scanner = new Scanner(f);

        // Ignore first line
        String line = scanner.nextLine();

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            String[] parts = line.split(DELIMITER, -1);
            
            // Extract the name, x and y positions
            String name = parts[0].trim();
//            String location = parts[1].trim();
            String pX = parts[1].trim();
            String pY = parts[2].trim();

            // Figure out the column numbers containing the paths and type of this node
            IntPair pathsCols = splitMatched(parts, 3, "^P[0-9]*$");
            IntPair typeCols = splitMatched(parts, 3, "^Signal.*$");
            if (typeCols == null)
                typeCols = splitMatched(parts, 3, "^Points:.*$");
            if (typeCols == null)
                typeCols = splitMatched(parts, 3, "^Diamond:.*$");
            if (typeCols == null)
                typeCols = splitMatched(parts, 3, "^Buffer$"); // this is a terminal node

            /* If the node has a specialised type, create an object of that type;
             * Otherwise create a generic Node.
             */
            Node n = new Node(name);
            if (typeCols != null) {
                if (parts[typeCols.first].startsWith("Signal")) {
                    n = new Signal(name);
                    Signal s = (Signal) n;
                    s.setDirPath(parts[typeCols.first + 1].trim());
                } else if (parts[typeCols.first].startsWith("Points:")) {
                    n = new Point(name);
                    Point p = (Point) n;
                    String pconfig = parts[typeCols.first].split(":")[1];
                    String[] pmainbranch = pconfig.split("/");
                    String[] pmain = pmainbranch[0].split("-");
                    String pbranch = pmainbranch[1].substring(1, pmainbranch[1].length() - 1);
                    p.setEnterPath(pmain[0].trim());
                    p.setExitPath(pmain[1].trim());
                    p.setBranchPath(pbranch.trim());
                } else if (parts[typeCols.first].startsWith("Diamond:")) {
                    n = new Diamond(name);
                    Diamond p = (Diamond) n;
                    String pconfig = parts[typeCols.first].split(":")[1];
                    String[] pmainbranch = pconfig.split("/");
                    String[] pmain = pmainbranch[0].split("-");
                    String[] pbranch = pmainbranch[1].substring(1, pmainbranch[1].length() - 1)
                            .split("-");
                    p.setMainEnter(pmain[0].trim());
                    p.setMainExit(pmain[1].trim());
                    p.setBranchEnter(pbranch[0].trim());
                    p.setBranchExit(pbranch[1].trim());
                } else if (parts[typeCols.first].startsWith("Buffer")) {
                    n = new Terminal(name);
                }
            }

            n.setLocationX(pX);
            n.setLocationY(pY);
            
            if (pathsCols != null)
                for (int i = pathsCols.first; i <= pathsCols.second; i++)
                    n.addPath(parts[i]);

            this.nodes.put(n.getName(), n);
            System.out.println("Adding: " + n);
        }

        scanner.close();
    }

//	public void parsePoints(String filename) throws IOException{
//		File f = new File(filename);
//		Scanner scanner = new Scanner(f);
//		
//		String line = scanner.nextLine();
//		while (scanner.hasNextLine())
//        {
//			line = scanner.nextLine();
//			String [] parts = line.split(DELIMITER, -1);
//            
//			Node n = nodes.get(parts[0].trim());
//			
//			if (n != null){
//				Point.PointBuilder p = new Point.PointBuilder(n);
//				p.setSwitchTime(Integer.parseInt(parts[2].trim()));
//				Point created = p.createPoint();
//				nodes.put(created.getName(),created);
//				System.out.println("Adding: " + created.toString());
//					
//			}
//        }
//         
//        //Do not forget to close the scanner 
//        scanner.close();
//	}

//	public void parseSignals(String filename) throws IOException{
//		File f = new File(filename);
//		Scanner scanner = new Scanner(f);
//		
//		String line = scanner.nextLine();
//		while (scanner.hasNextLine())
//        {
//			line = scanner.nextLine();
//		    // deal with extra non needed info.
//			if(!line.isEmpty() && !line.startsWith("CLEAR")){
//				String [] parts = line.split(DELIMITER, -1);
//            
//				Node n = nodes.get(parts[0].trim());
//				if (n != null){
//					Signal.SignalBuilder s = new Signal.SignalBuilder(n);
//					s.setSwitchTime(Integer.parseInt(parts[2].trim()));
//					Signal created = s.createSignal();
//					nodes.put(created.getName(),created);
//					System.out.println("Adding: " + created.toString());
//				}	
//			}
//        }
//         
//        //Do not forget to close the scanner 
//        scanner.close();
//		
//	}

    /**
     * Import all paths from a file.
     * @param filename Path to the Brave CSV file for paths
     * @throws IOException when things go wrong
     */
    public void parsePaths(String filename) throws IOException {
        File f = new File(filename);
        Scanner scanner = new Scanner(f);

        // Ignore first line
        String line = scanner.nextLine();
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            String[] parts = line.split(DELIMITER, -1);

            Path p = new Path(parts[0].trim());
            Node startNode = nodes.get(parts[1].trim());
            Node endNode = nodes.get(parts[2].trim());
            p.setStartNode(startNode);
            p.setEndNode(endNode);
            p.setLength(Double.valueOf(parts[3].trim()));
            p.setSpeedLimitUp(Double.valueOf(parts[4].trim()));
            p.setSpeedLimitDown(Double.valueOf(parts[5].trim()));
            p.setGradient(Double.valueOf(parts[6].trim()));
            
            this.paths.put(p.getName(), p);
            System.out.println("Adding: " + p);
        }

        // Do not forget to close the scanner
        scanner.close();

    }

    /**
     * Import all tracks from a file.
     * @param filename Path to the Brave CSV file for track circuits.
     * @throws IOException when things go wrong
     */
    public void parseTracks(String filename) throws IOException {
        File f = new File(filename);
        Scanner scanner = new Scanner(f);

        // Ignore first line
        String line = scanner.nextLine();
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            String[] parts = line.split(DELIMITER, -1);

            String name = parts[0].trim();
            String pathsStr = parts[1].trim();
            TrackCircuit t = new TrackCircuit(name);
            ArrayList<Path> paths = extractPaths(pathsStr);
            t.addPaths(paths);

            /*
             * The paths file doesn't specify tracks within each path, so we
             * add the new track to each of its paths.
             */
            for (Path p : paths) {
                p.addTrack(t);
            }

            t.computeEndNodes();

            tracks.put(name, t);
            System.out.println("Adding: " + t);
        }

        // Do not forget to close the scanner
        scanner.close();
    }

    /**
     * Import all routes from a file.
     * @param filename Path to the Brave CSV file for routes
     * @throws IOException
     */
    public void parseRoutes(String filename) throws IOException {
        File f = new File(filename);
        Scanner scanner = new Scanner(f);

        // Ignore first line
        String line = scanner.nextLine();
        while (scanner.hasNextLine()) {

            line = scanner.nextLine();
            Route r = parseRoute(line);

            // r.setTrackCircuits();
            // r.setOverlaps();

            if (r != null) {
                this.routes.put(r.getName(), r);
                System.out.println("Adding: " + r.getName());
            }
        }

        // Do not forget to close the scanner
        scanner.close();
    }

    /**
     * Construct a {@link Route} from a row of CSV.
     * @param line Contents of a row in the routes CSV file
     * @return The route specified by the CSV row, or {@code null} if the route contains a signal or TC that
     * is not in the data we have parsed.
     */
    private Route parseRoute(String line) {
        String[] parts = line.split(DELIMITER, -1);

        // int signalPosition = lookupNode(parts[2].trim());

        String sigName = parts[2].trim();
        Signal sig = (Signal) this.nodes.get(sigName);

        String routeName = parts[0].trim();
        Route.RouteBuilder r = new Route.RouteBuilder(routeName, sig);

        for (int i = 3; i < parts.length - 9; i++) {
            String s = parts[i].trim();
            if (s.contains("NORMAL") || s.contains("REVERSE")) {
                String[] pointParts = s.split(" ");
                String pointName = pointParts[0].trim();
                String pointPosition = pointParts[1].trim();
                Point p = (Point) this.nodes.get(pointName);

                if (p != null) {
                    if (pointPosition.equals("NORMAL")) {
                        r.addNormalPoint(p);
                    } else {
                        r.addReversePoint(p);
                    }
                } else {
                    return null;
                }
            } else if (s.contains("TC")) {
                // No overlaps so ok to assume all just in route
                TrackCircuit tc = this.tracks.get(s);

                if (tc != null) {
                    r.addTrackCircuit(tc);
                } else {
                    return null;
                }
            }
        }

        //hack for Haerbin as timees changes to float.
        //int clearTime = Integer.parseInt(parts[parts.length - 4].trim());
        int clearTime = 120;
        r.setClearTime(clearTime);
        return r.createRoute();
    }

    /**
     * Convert a string describing paths associated with a track circuit to a list of {@link Path}s.
     * A warning is printed if a non-existent path name is specified.
     * 
     * @param pathString A string in the following format: <BR>
     * {@code <track-circuit-name>:Paths = <path1> <path2> ...}
     * @return A list containing the constructed paths
     */
    public ArrayList<Path> extractPaths(String pathString) {
        ArrayList<Path> result = new ArrayList<Path>();

        String[] parts = pathString.split("=");
        String[] pathParts = parts[1].split(" ");

        for (String s : pathParts) {
            if (!s.isEmpty()) {
                boolean found = false;
                for (Path p : paths.values()) {
                    if (p.getName().equals(s.trim())) {
                        result.add(p);
                        found = true;
                    }
                }
                if (!found) {
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