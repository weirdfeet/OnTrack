package uk.ac.swanseacoventry.cmt.ontrack.railimporter;


import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
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
import uk.ac.swanseacoventry.cmt.ontrack.Entrance;
import uk.ac.swanseacoventry.cmt.ontrack.Exit;
import uk.ac.swanseacoventry.cmt.ontrack.OntrackFactory;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;



public class TopologyCalculator {
	RailParser rp = new RailParser();
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
	TrackPlan barkston = OntrackFactory.eINSTANCE.createTrackPlan(); // barkston was named due to historic reason, should rename to trackplan
	
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
	
//			String pointsFilename = inputFolder + File.separator + "Points.csv";
//			rp.parsePoints(pointsFilename);
//	
//			String signalsFilename = inputFolder + File.separator + "Signals.csv";
//			rp.parseSignals(signalsFilename);
		
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
		Resource myModel = resourceSet.createResource(URI.createFileURI( outputFile )); // + ".xmi"));
		
		ArrayDeque<Node> queue = new ArrayDeque<Node>();

		HashSet<Node> visited = new HashSet<Node>(); // nodes in the region
		HashSet<String> boundarySignals = new HashSet<String>(); // node surrounding the region
		boundarySignals.addAll(entrySignals);
		boundarySignals.addAll(exitSignals);

		// calculate paths not to be used in the searching for nodes in the region,
		// they are before then entry signals and after the exit signals
		HashSet<String> ignorePaths = new HashSet<String>(); // forbidden paths when BFS
		for(String s : entrySignals){
			Node n = rp.getNodes().get(s);
			Signal sig = (Signal)n;
			String beforePath = sig.getDirPath();
			ignorePaths.add(beforePath);
		}
		
		for(String s : exitSignals){
			Node n = rp.getNodes().get(s);
			Signal sig = (Signal)n;
			String beforePath = sig.getDirPath();
			for(String p : n.getPaths())
				if (!p.equals(beforePath))
					ignorePaths.add(p);
		}
		
		while(!queue.isEmpty()){
			Node n = queue.pop();
			visited.add(n);
			for(String p : n.getPaths()){
				if (ignorePaths.contains(p)) break;
				Path path = rp.getPaths().get(p);
				Node nextNode = path.getStartNode() == n ? path.getEndNode() : path.getStartNode();
				if (!visited.contains(nextNode)) {
					queue.add(nextNode);
				}
			}
		}
		
		// generate all connectors from node
//		for(Node n : visited){
//			getOnTrackConnector(n);
//		}
		
		// collect all paths to import; it must start and end with a visited node
		HashMap<String,Path> importedPaths = new HashMap<String,Path>();
		for(String pname : rp.getPaths().keySet()){
			Path p = rp.getPaths().get(pname);
			if (!visited.contains(p.getStartNode())) continue;
			if (!visited.contains(p.getEndNode())) continue;
			importedPaths.put(pname, p);
		}
		
		// collect all track circuits to import; it must contains at least a path in importedPaths
		for(String tname : rp.getTracks().keySet()){
			TrackCircuit t = rp.getTracks().get(tname);
			
			for(Path p : t.getPaths())
				if (importedPaths.containsValue(p)) {
					getOnTrackTrack(t);
					break;
				};
		}
		
		for(TrackCircuit t : createdTracks.keySet()){
			System.out.println("Imported Track: " + t.getName());
		}
		
		
		// expand all point nodes into points and their two corresponding tracks
		// node that we need to connect their connectors later
		for(Node n : visited){
			System.out.print(n.getName() + ",");
			
			if (n instanceof Point){
				Point bravep = (Point)n;			
				uk.ac.swanseacoventry.cmt.ontrack.Point  p = getOnTrackPoint(bravep);
				
				// replace the connector of surrounding tracks of this point with the connectors of the point
				Connector c = getOnTrackConnector(bravep);
				ArrayList<Track> tracks = new ArrayList<Track>();
				tracks.addAll(c.getTracks());
				for(Track t : tracks){
					if (t.getName().equals(bravep.getEnterPath())){
						if (t.getC1()==c){
							t.setC1(p.getNormalTrack().getC1());
						}
						else {
							t.setC2(p.getNormalTrack().getC1());
						}
					} else	if (t.getName().equals(bravep.getExitPath())){
						if (t.getC1()==c){
							t.setC1(p.getNormalTrack().getC2());
						}
						else {
							t.setC2(p.getNormalTrack().getC2());
						}
					} else	if (t.getName().equals(bravep.getBranchPath())){
						if (t.getC1()==c){
							t.setC1(p.getReverseTrack().getC2());
						}
						else {
							t.setC2(p.getReverseTrack().getC2());
						}
					}
				}
			} else if (n instanceof Signal) {
				Signal braves = (Signal)n;			
				uk.ac.swanseacoventry.cmt.ontrack.Signal s = getOnTrackSignal(braves);
				if (importedPaths.keySet().contains(braves.getDirPath())){
					Track t = createdTracks.get(importedPaths.get(braves.getDirPath()).getTracks().get(0));
					if (t!=null) s.setTrack(t);
					t.getSignals().add(s);
				}
			}
			
			if (n instanceof Terminal){
				Connector c = getOnTrackConnector(n);
				uk.ac.swanseacoventry.cmt.ontrack.Terminal ter = OntrackFactory.eINSTANCE.createTerminal();
				ter.setConnector(c);
				c.setTerminal(ter);
				barkston.getTerminals().add(ter);
			}
		}

		// collect all routes to import; it must have its signal in visited
		HashMap<String,Route> importedRoutes = new HashMap<String,Route>();
		for(String rname : rp.getRoutes().keySet()){
			Route r = rp.getRoutes().get(rname);
			if (!visited.contains(r.getSignal())) continue;
			importedRoutes.put(rname, r);
		}
		
		
		// generate control table for the imported track plan
		for(String rn : importedRoutes.keySet()){
			System.out.println("Imported Route: " + rn);
			Route r = importedRoutes.get(rn);
			ArrayList<Point> nPoints = r.getNormalPoints();
			ArrayList<Point> rPoints = r.getReversePoints();
			
			ControlTableItem ct = OntrackFactory.eINSTANCE.createControlTableItem();
			ct.setRoute(r.getName());

			for(Point pn : nPoints){
				uk.ac.swanseacoventry.cmt.ontrack.Point p = getOnTrackPoint(pn);
				ct.getNormals().add(p);
				ct.getClears().add(p.getNormalTrack());
			}
			
			for(Point pn : rPoints){
				uk.ac.swanseacoventry.cmt.ontrack.Point p = getOnTrackPoint(pn);
				ct.getReverses().add(p);
				ct.getClears().add(p.getReverseTrack());
			}
			
			for(TrackCircuit t : r.getTrackCircuits()){
				Track tr = createdTracks.get(t);
				if (tr!=null) ct.getClears().add(tr);
			}
			
			ct.setSignal(getOnTrackSignal(r.getSignal()));
		    barkston.getControlTable().add(ct);
		}
		
		// add entry track
		int entryCount = 0;
		for(String entrySignal : entrySignals){
			Signal braves = (Signal)rp.getNodes().get(entrySignal);
			uk.ac.swanseacoventry.cmt.ontrack.Signal s = createdSignals.get(braves);
			
			Track entry = OntrackFactory.eINSTANCE.createTrack();
			entry.setName("ENTRY_" + (entryCount++));
			barkston.getTracks().add(entry);
			Connector ec = OntrackFactory.eINSTANCE.createConnector();
			ec.setId(conNum++);
			barkston.getConnectors().add(ec);
			
			Connector c = getOnTrackConnector(braves);
			
			s.setTrack(entry);
			
			entry.setC1(ec);
			entry.setC2(c);
			entry.getSignals().add(s);
			
			ec.getTrack1s().add(entry);
			c.getTrack2s().add(entry);		
			
			Entrance ent = OntrackFactory.eINSTANCE.createEntrance();
			ent.setConnector(ec);
			ec.getEntrances().add(ent);
			barkston.getEntrances().add(ent);
		}
						
		for(String entrySignal : exitSignals){
			Signal braves = (Signal)rp.getNodes().get(entrySignal);
			Connector c = getOnTrackConnector(braves);
			
			Exit ex = OntrackFactory.eINSTANCE.createExit();
			ex.setConnector(c);
			c.getExits().add(ex);
			barkston.getExits().add(ex);
		}
		
		for(Track t : barkston.getTracks()){
			DirectedTrack dt1 = OntrackFactory.eINSTANCE.createDirectedTrack();
			dt1.setTrack(t);
			dt1.setConnector(t.getC1());
			DirectedTrack dt2 = OntrackFactory.eINSTANCE.createDirectedTrack();
			dt2.setTrack(t);
			dt2.setConnector(t.getC2());
			t.getDirectedTracks().add(dt1);
			t.getDirectedTracks().add(dt2);
		}
		
		EList<EObject> ModelObjects = new BasicEList<EObject>(); 
		ModelObjects.add(barkston);
		myModel.getContents().addAll(ModelObjects);
		
		try {
			myModel.save(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File f = new File(outputFile);
//		File newf = new File(outputFile);
//		f.renameTo(newf);
		return f; // newf;
		
	}
	
}

