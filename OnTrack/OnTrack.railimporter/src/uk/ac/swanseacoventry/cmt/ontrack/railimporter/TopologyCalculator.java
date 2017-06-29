package uk.ac.swanseacoventry.cmt.ontrack.railimporter;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import uk.ac.swanseacoventry.cmt.ontrack.Connector;
import uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem;
import uk.ac.swanseacoventry.cmt.ontrack.DirectedTrack;
import uk.ac.swanseacoventry.cmt.ontrack.OntrackFactory;
import uk.ac.swanseacoventry.cmt.ontrack.TopoRoute;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;



public class TopologyCalculator {
	RailParser rp = new RailParser();
	
	//String[] routes4Barkston = {"R3936","R3935", "R3934", "R3939", "R3938", "R3937"};
	String[] routes4Barkston = {"R3936","R3935", "R3934", "R3939", "R3938", "R3937"};
	//String[] entrySignals = {"N10425", "N10254", "N10253", "N10459", "N19435", "N9517", "N10035", "N10002"};
	//String[] exitSignals = {"N10424", "N10255", "N19409", "N9552", "N10034", "N10033"};
	// String[] entrySignals = {"N5860"};
	// String[] exitSignals = {"N5857"};
	ArrayList<String> entrySignals = new ArrayList<String>();
	ArrayList<String> exitSignals = new ArrayList<String>();
	
	public boolean isInputFolderValid(String inputFolder) {
		File f = new File(inputFolder);
		if (!f.isDirectory()) return false;
		
		String[] requiredFiles = {"Nodes.csv","Points.csv","Signals.csv","Paths.csv","TrackCircuits.csv","Routes.csv", "Boundary.csv"};
		for(String rqfile : requiredFiles){
			f = new File(inputFolder + File.separator + rqfile);
			if (!f.exists() || !f.isFile()) return false;
		}
		
		return true;
	}
	
	HashMap<TrackCircuit,Track> createdTracks = new HashMap<TrackCircuit,Track>();
	HashMap<Node,Connector> createdConnectors = new HashMap<Node,Connector>();
	HashMap<Signal,uk.ac.swanseacoventry.cmt.ontrack.Signal> createdSignals = new HashMap<Signal,uk.ac.swanseacoventry.cmt.ontrack.Signal>();
	HashMap<Point,uk.ac.swanseacoventry.cmt.ontrack.Point> createdPoints = new HashMap<Point,uk.ac.swanseacoventry.cmt.ontrack.Point>();
	HashMap<Connector, HashSet<Track>> normalPointTracks = new HashMap<Connector, HashSet<Track>>();
	HashMap<Connector, HashSet<Track>> reversePointTracks = new HashMap<Connector, HashSet<Track>>();
	//HashMap<Point, HashSet<ControlTableItem>> normalPointRoutes = new HashMap<Point, HashSet<ControlTableItem>>();
	//HashMap<Point, HashSet<ControlTableItem>> reversePointRoutes = new HashMap<Point, HashSet<ControlTableItem>>();

	int conNum = 1;
	TrackPlan barkston = OntrackFactory.eINSTANCE.createTrackPlan();
	
	uk.ac.swanseacoventry.cmt.ontrack.Signal getOnTrackSignal(Signal n){
		uk.ac.swanseacoventry.cmt.ontrack.Signal s = createdSignals.get(n);
		if (s==null) {
			s = OntrackFactory.eINSTANCE.createSignal();
			s.setName(n.getName());
			barkston.getSignals().add(s);
			createdSignals.put(n, s);
		}
		
		Connector c = getOnTrackConnector(n);
		s.setConnector(c);
		c.getSignals().add(s);
		
		return s;
	}
	
	uk.ac.swanseacoventry.cmt.ontrack.Connector getOnTrackConnector(Node n){
		Connector c = createdConnectors.get(n);
		if (c==null) {
			c = OntrackFactory.eINSTANCE.createConnector();
			c.setId(conNum++);
			barkston.getConnectors().add(c);
			createdConnectors.put(n, c);
		}
		return c;
	}

	uk.ac.swanseacoventry.cmt.ontrack.Point getOnTrackPoint(Point n){
		uk.ac.swanseacoventry.cmt.ontrack.Point p = createdPoints.get(n);
		if (p==null) {
			p = OntrackFactory.eINSTANCE.createPoint();
			p.setName(n.getName());
			barkston.getPoints().add(p);
			createdPoints.put(n, p);

			String pointTrackName = "TC" + n.getName();
			
			Connector c1 = OntrackFactory.eINSTANCE.createConnector();
			c1.setId(conNum++);
			barkston.getConnectors().add(c1);
			Connector c2 = OntrackFactory.eINSTANCE.createConnector();
			c2.setId(conNum++);
			barkston.getConnectors().add(c2);
			Connector c3 = OntrackFactory.eINSTANCE.createConnector();
			c3.setId(conNum++);
			barkston.getConnectors().add(c3);
			
			Track nt = OntrackFactory.eINSTANCE.createTrack();
			nt.setName(pointTrackName);
			barkston.getTracks().add(nt);

			Track rt = OntrackFactory.eINSTANCE.createTrack();
			rt.setName(pointTrackName);
			barkston.getTracks().add(rt);
			
			p.setNormalTrack(nt);
			p.setReverseTrack(rt);
			nt.setPointNormal(p);
			nt.setC1(c1);
			nt.setC2(c2);
			rt.setPointReverse(p);
			rt.setC1(c1);
			rt.setC2(c3);
			c1.getTrack1s().add(nt);
			c1.getTrack1s().add(rt);
			c2.getTrack2s().add(nt);
			c3.getTrack2s().add(rt);
		}
		return p;
	}

	uk.ac.swanseacoventry.cmt.ontrack.Track getOnTrackTrack(TrackCircuit tc){
		uk.ac.swanseacoventry.cmt.ontrack.Track t = createdTracks.get(tc);
		if (t==null) {
			t = OntrackFactory.eINSTANCE.createTrack();
			t.setName(tc.getName());
			barkston.getTracks().add(t);
			createdTracks.put(tc, t);
			
			Connector c1 = getOnTrackConnector(tc.startNode);
			Connector c2 = getOnTrackConnector(tc.endNode);
			
			t.setC1(c1);
			t.setC2(c2);
			c1.getTrack1s().add(t);
			c2.getTrack2s().add(t);
		}
		return t;
	}
	
	public void rememberNormalPointInCtrlTable(Node n, Track t, ControlTableItem ct){
		Connector c = getOnTrackConnector(n);
		HashSet<Track> ts = normalPointTracks.get(c);
		if (ts==null) {
			ts = new HashSet<Track>();
			normalPointTracks.put(c, ts);
		}
		ts.add(t);
		
//		HashSet<ControlTableItem> cts = normalPointRoutes.get(n);
//		if (cts==null) {
//			cts = new HashSet<ControlTableItem>();
//			normalPointRoutes.put((Point)n, cts);
//		}
//		cts.add(ct);
	}
	
	public void rememberReversePointInCtrlTable(Node n, Track t, ControlTableItem ct){
		Connector c = getOnTrackConnector(n);
		HashSet<Track> ts = reversePointTracks.get(c);
		if (ts==null) {
			ts = new HashSet<Track>();
			reversePointTracks.put(c, ts);
		}
		ts.add(t);
	}
	
	
	void parseBoundary(String filename) throws IOException{
		
		entrySignals.clear();
		exitSignals.clear();
		
		File f = new File(filename);
		Scanner scanner = new Scanner(f);


		// read the list of entry signal node names
		String line = scanner.nextLine();
		String data = line.split("#", -1)[0];
		String[] parts = data.split(",");
		for(String p : parts)
			entrySignals.add(p.trim());
		
		// read the list of exit signal node names
		line = scanner.nextLine();
		data = line.split("#", -1)[0];
		parts = data.split(",");
		for(String p : parts)
			exitSignals.add(p.trim());
		
		// output result for debuging
		System.out.println("Entry signals to import: " + String.join(", ", entrySignals));
		System.out.println("Exit signals to import: " + String.join(", ", exitSignals));
		
        //Do not forget to close the scanner 
        scanner.close();
	}
		
	
	public File importBraveData(String inputFolder, String outputFile){
		try{
			String nodesFilename = inputFolder + File.separator + "Nodes.csv";
			rp.parseNodes(nodesFilename);
	
			String pointsFilename = inputFolder + File.separator + "Points.csv";
			rp.parsePoints(pointsFilename);
	
			String signalsFilename = inputFolder + File.separator + "Signals.csv";
			rp.parseSignals(signalsFilename);
		
			String pathsFilename = inputFolder + File.separator + "Paths.csv";
			rp.parsePaths(pathsFilename);
	
			String tracksFilename = inputFolder + File.separator + "TrackCircuits.csv";
			rp.parseTracks(tracksFilename);
		
			String routesFilename = inputFolder + File.separator + "Routes.csv";
			rp.parseRoutes(routesFilename);
			
			String boundaryFilename = inputFolder + File.separator + "Boundary.csv";
			parseBoundary(boundaryFilename);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//buildTopologicalRoutes();
		
		ResourceSet resourceSet = new ResourceSetImpl(); 

		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new  XMIResourceFactoryImpl());		
		Resource myModel = resourceSet.createResource(URI.createFileURI( outputFile + ".xmi"));
		
		class PathNode {
			Path path;
			Node node;
			PathNode(Path p, Node n){
				path = p;
				node = n;
			}
		}
		
		ArrayDeque<PathNode> queue = new ArrayDeque<PathNode>();

		
		for(String s : entrySignals){
			System.out.println(s);
			Node nextNode = null;
			Path nextPath = null;
			for(Route r : rp.getRoutes().values()){
				if(r.getSignal().getName().equals(s)){
						TrackCircuit tc = r.getTrackCircuits().get(0);
					for(Path p : tc.getPaths()){
						if(p.getStartNode().getName().equals(s)){
							nextNode = p.getEndNode();
							nextPath = p;
							System.out.println(p.getEndNode());
							break;
						}
						else if(p.getEndNode().getName().equals(s)){
							nextNode = p.getStartNode();
							nextPath = p;
							System.out.println(p.getStartNode());
							break;
						}
					}
				}
				
			}
			if (nextPath!=null) 
				queue.add(new PathNode(nextPath, rp.getNodes().get(s)));
		}
		
		for(String s : exitSignals){
			System.out.println(s);
			Node nextNode = null;
			Path nextPath = null;
			for(Route r : rp.getRoutes().values()){
				if(r.getSignal().getName().equals(s)){
						TrackCircuit tc = r.getTrackCircuits().get(0);
					for(Path p : tc.getPaths()){
						if(p.getStartNode().getName().equals(s)){
							nextNode = p.getEndNode();
							nextPath = p;
							System.out.println(p.getEndNode());
							break;
						}
						else if(p.getEndNode().getName().equals(s)){
							nextNode = p.getStartNode();
							nextPath = p;
							System.out.println(p.getStartNode());
							break;
						}
					}
				}
				
			}
			if (nextPath!=null) {
				for(Path p : rp.getPaths().values())
				{
					if(p.getStartNode().getName().equals(s)){
						if (p != nextPath) {
							queue.add(new PathNode(p, rp.getNodes().get(s)));
							break;
						}
					}
					else if(p.getEndNode().getName().equals(s)){
						if (p != nextPath) {
							queue.add(new PathNode(p, rp.getNodes().get(s)));
							break;
						}
					}
				}
			}
		}

		
		HashSet<Node> visited = new HashSet<Node>();
		HashSet<String> boundarySignals = new HashSet<String>();
		
		for(String s : entrySignals) {
			boundarySignals.add(s);
		}

		for(String s : exitSignals) { 
			boundarySignals.add(s);
		}
		
		for(PathNode n : queue){
			visited.add(n.node);
		}

		while(!queue.isEmpty()){
			PathNode pn = queue.pop();
			Node nextNode = pn.path.getEndNode() == pn.node ? pn.path.getStartNode() : pn.path.getEndNode();
			System.out.println("Popping:" + pn);
			if (!visited.contains(nextNode) && !boundarySignals.contains(nextNode.getName())){
				for(Path p : rp.getPaths().values())
				{
					if(p.getStartNode()==nextNode){
						if (p != pn.path) {
							queue.add(new PathNode(p, nextNode));
							visited.add(nextNode);
							System.out.println("adding:" + nextNode);
							break;
						}
					}
					else if(p.getEndNode()==nextNode){
						if (p != pn.path) {
							queue.add(new PathNode(p, nextNode));
							visited.add(nextNode);
							System.out.println("adding:" + nextNode);
							break;
						}
					}
				}
			}
			
		}
		
		// collect all paths to import; it must start and end with a visited node
		HashMap<String,Path> importedPaths = new HashMap<String,Path>();
		for(String pname : rp.getPaths().keySet()){
			Path p = rp.getPaths().get(pname);
			if (!visited.contains(p.getStartNode())) continue;
			if (!visited.contains(p.getEndNode())) continue;
			importedPaths.put(pname, p);
		}
		
		// collect all track circuits to import; it must contains at least a path in importedPaths
		HashMap<String,TrackCircuit> importedTrackCircuits = new HashMap<String,TrackCircuit>();
		for(String tname : rp.getTracks().keySet()){
			TrackCircuit t = rp.getTracks().get(tname);
			
			for(Path p : t.getPaths())
				if (importedPaths.containsValue(p)) {
					importedTrackCircuits.put(tname, t);
					break;
				};
		}
		
		for(String t : importedTrackCircuits.keySet()){
			System.out.println("Imported Track: " + t);
		}

		// collect all routes to import; it must have both its signal in visited and at least a track in importedTrackCircuits
		HashMap<String,Route> importedRoutes = new HashMap<String,Route>();
		for(String rname : rp.getRoutes().keySet()){
			Route r = rp.getRoutes().get(rname);
			if (!visited.contains(r.getSignal())) continue;
			for(TrackCircuit t : r.getTrackCircuits())
				if (importedTrackCircuits.containsValue(t)){
					importedRoutes.put(rname, r);
					break;
				}
		}
		
		for(String t : importedRoutes.keySet()){
			System.out.println("Imported Route: " + t);
		}
		
		return null;
	}
	
}

