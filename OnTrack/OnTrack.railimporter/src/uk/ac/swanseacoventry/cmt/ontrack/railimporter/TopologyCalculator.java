package uk.ac.swanseacoventry.cmt.ontrack.railimporter;


import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

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
	String[] entrySignals = {"N5860"};
	String[] exitSignals = {"N5857"};
	
	public boolean isInputFolderValid(String inputFolder) {
		File f = new File(inputFolder);
		if (!f.isDirectory()) return false;
		
		String[] requiredFiles = {"Nodes.csv","Points.csv","Signals.csv","Paths.csv","TrackCircuits.csv","Routes.csv"};
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
		
//		HashSet<ControlTableItem> cts = reversePointRoutes.get(n);
//		if (cts==null) {
//			cts = new HashSet<ControlTableItem>();
//			reversePointRoutes.put((Point)n, cts);
//		}
//		cts.add(ct);
	}
	
//	public File importBraveDataOLD(String inputFolder, String outputFile){
//		try{
//			String nodesFilename = inputFolder + File.separator + "Nodes.csv";
//			rp.parseNodes(nodesFilename);
//	
//			String pointsFilename = inputFolder + File.separator + "Points.csv";
//			rp.parsePoints(pointsFilename);
//	
//			String signalsFilename = inputFolder + File.separator + "Signals.csv";
//			rp.parseSignals(signalsFilename);
//		
//			String pathsFilename = inputFolder + File.separator + "Paths.csv";
//			rp.parsePaths(pathsFilename);
//	
//			String tracksFilename = inputFolder + File.separator + "TrackCircuits.csv";
//			rp.parseTracks(tracksFilename);
//		
//			String routesFilename = inputFolder + File.separator + "Routes.csv";
//			rp.parseRoutes(routesFilename);
//		}
//		catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		//buildTopologicalRoutes();
//		
//		ResourceSet resourceSet = new ResourceSetImpl(); 
//
////		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
//		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new  XMIResourceFactoryImpl());
//	
////		Resource myMetaModel= resourceSet.getResource(URI.createFileURI("model/safetrack.ecore"), true);		
//		Resource myModel = resourceSet.createResource(URI.createFileURI( outputFile + ".xmi"));
//
//		
//		for(Route r : rp.getRoutes()){
//		if(Arrays.asList(routes4Barkston).contains(r.getName())){
//			ArrayList<Point> nPoints = r.getNormalPoints();
//			ArrayList<Point> rPoints = r.getReversePoints();
//
//			ControlTableItem ct = OntrackFactory.eINSTANCE.createControlTableItem();
//			ct.setRoute(r.getName());
//		    barkston.getControlTable().add(ct);
//
//			for(Point pn : nPoints){
//				uk.ac.swanseacoventry.cmt.ontrack.Point p = getOnTrackPoint(pn);
//				ct.getNormals().add(p);
//				ct.getClears().add(p.getNormalTrack());
//			}
//			
//			for(Point pn : rPoints){
//				uk.ac.swanseacoventry.cmt.ontrack.Point p = getOnTrackPoint(pn);
//				ct.getReverses().add(p);
//				ct.getClears().add(p.getReverseTrack());
//			}
//			
//			
////			TopoRoute tp = OntrackFactory.eINSTANCE.createTopoRoute();
////			tp.getNames().add(r.getName());
////			tp.setStartSignal(s);
//			
//	
//			
//			for(TrackCircuit tc: r.getTrackCircuits()){
//				tc.computeEndNodes();
//				Track t = getOnTrackTrack(tc);
//				ct.getClears().add(t);
//				
//				if (nPoints.contains(tc.startNode)) {
//					rememberNormalPointInCtrlTable(tc.startNode, t, ct);
//				}
//
//				if (nPoints.contains(tc.endNode)) {
//					rememberNormalPointInCtrlTable(tc.endNode, t, ct);
//				}
//
//				if (rPoints.contains(tc.startNode)) {
//					rememberReversePointInCtrlTable(tc.startNode, t, ct);
//				}
//
//				if (rPoints.contains(tc.endNode)) {
//					rememberReversePointInCtrlTable(tc.endNode, t, ct);
//				}
//			}
//			
////			Track last =  tp.getDirectedTracks().get(tp.getDirectedTracks().size()-1).getTrack();
////			for(Track find: barkston.getTracks()){
////				if(find.getC1() == last.getC2())
////				{
////					tp.setEndSignal(find.getSignals().get(0));
////				}
////			}
////			barkston.getTopoRoutes().add(tp);
//
//			ct.setSignal(getOnTrackSignal(r.getSignal()));
//			for(Track t : ct.getClears() ){
//				if (t.getC1()==ct.getSignal().getConnector()
//						|| t.getC2()==ct.getSignal().getConnector()) {
//					ct.getSignal().setTrack(t);
//					break;
//				}
//			}
//		}
//		}
//		
//		int entryCount = 0;
//		for(uk.ac.swanseacoventry.cmt.ontrack.Signal s : barkston.getSignals()){
//			Connector c = s.getConnector();
//			Track t = s.getTrack();
//			boolean found = false;
//			for(Track t1 : c.getTracks()) {
//				if (t1!=t) {
//					s.setTrack(t1);
//					t1.getSignals().add(s);
//					found = true;
//					break;
//				}
//			}
//			if (!found) {
//				Track entry = OntrackFactory.eINSTANCE.createTrack();
//				entry.setName("ENTRY_" + (entryCount++));
//				barkston.getTracks().add(entry);
//				Connector ec = OntrackFactory.eINSTANCE.createConnector();
//				ec.setId(conNum++);
//				barkston.getConnectors().add(ec);
//				
//				s.setTrack(entry);
//				
//				entry.setC1(ec);
//				entry.setC2(c);
//				entry.getSignals().add(s);
//				
//				ec.getTrack1s().add(entry);
//				c.getTrack2s().add(entry);
//			}
//		
//		}
//		
//		for(Point pn : createdPoints.keySet()){
//			uk.ac.swanseacoventry.cmt.ontrack.Point p = createdPoints.get(pn);
//			Connector c = createdConnectors.get(pn);
//			// if (c==null) break;
//			HashSet<Track> nts = normalPointTracks.get(c);
//			HashSet<Track> rts = reversePointTracks.get(c);
//			// if (nts==null || rts==null) break;
//			HashSet<Track> commonTracks = new HashSet<Track>();
//			commonTracks.addAll(nts);
//			commonTracks.retainAll(rts);
//			nts.removeAll(commonTracks);
//			rts.removeAll(commonTracks);
//			//if (commonTracks.size()>0 && nts.size()>0 && rts.size()>0) 
//			{
//				Track ct = (Track)commonTracks.toArray()[0];
//				Track nt = (Track)nts.toArray()[0];
//				Track rt = (Track)rts.toArray()[0];
//				
//				Connector c1 = p.getNormalTrack().getC1();
//				Connector c2 = p.getNormalTrack().getC2();
//				Connector c3 = p.getReverseTrack().getC2();
//				
//				if (ct.getC1()==c) {
//					ct.setC1(c1);
//					c1.getTrack1s().add(ct);
//				}
//				else {
//					ct.setC2(c1);
//					c1.getTrack2s().add(ct);
//				}
//
//				if (nt.getC1()==c) {
//					nt.setC1(c2);
//					c2.getTrack1s().add(nt);
//				}
//				else {
//					nt.setC2(c2);
//					c2.getTrack2s().add(nt);
//				}
//
//				if (rt.getC1()==c) {
//					rt.setC1(c3);
//					c3.getTrack1s().add(rt);
//				}
//				else {
//					rt.setC2(c3);
//					c3.getTrack2s().add(rt);
//				}
//				
//				barkston.getConnectors().remove(c);
//			}
//			
//		}
//		
//		for(Track t : barkston.getTracks()){
//			DirectedTrack dt1 = OntrackFactory.eINSTANCE.createDirectedTrack();
//			dt1.setTrack(t);
//			dt1.setConnector(t.getC1());
//			DirectedTrack dt2 = OntrackFactory.eINSTANCE.createDirectedTrack();
//			dt2.setTrack(t);
//			dt2.setConnector(t.getC2());
//			t.getDirectedTracks().add(dt1);
//			t.getDirectedTracks().add(dt2);
//		}
//		
//		
//		
//		
//		
//		
//		EList<EObject> ModelObjects = new BasicEList<EObject>(); 
//		ModelObjects.add(barkston);
//		myModel.getContents().addAll(ModelObjects);
//		
//		try {
//			myModel.save(null);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		File f = new File(outputFile + ".xmi");
//		File newf = new File(outputFile);
//		f.renameTo(newf);
//		return newf;
//		
//		//test
//	}
//		
//		
		
	
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
		
		for(Node n : visited){
			System.out.println(n.getName());
		}
		
		return null;
		
		//old shnizzle
		/*
		for(Route r : rp.getRoutes()){
		if(Arrays.asList(routes4Barkston).contains(r.getName())){
			ArrayList<Point> nPoints = r.getNormalPoints();
			ArrayList<Point> rPoints = r.getReversePoints();

			ControlTableItem ct = OntrackFactory.eINSTANCE.createControlTableItem();
			ct.setRoute(r.getName());
		    barkston.getControlTable().add(ct);

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
			
			
//			TopoRoute tp = OntrackFactory.eINSTANCE.createTopoRoute();
//			tp.getNames().add(r.getName());
//			tp.setStartSignal(s);
			
	
			
			for(TrackCircuit tc: r.getTrackCircuits()){
				tc.computeEndNodes();
				Track t = getOnTrackTrack(tc);
				ct.getClears().add(t);
				
				if (nPoints.contains(tc.startNode)) {
					rememberNormalPointInCtrlTable(tc.startNode, t, ct);
				}

				if (nPoints.contains(tc.endNode)) {
					rememberNormalPointInCtrlTable(tc.endNode, t, ct);
				}

				if (rPoints.contains(tc.startNode)) {
					rememberReversePointInCtrlTable(tc.startNode, t, ct);
				}

				if (rPoints.contains(tc.endNode)) {
					rememberReversePointInCtrlTable(tc.endNode, t, ct);
				}
			}
			


			ct.setSignal(getOnTrackSignal(r.getSignal()));
			for(Track t : ct.getClears() ){
				if (t.getC1()==ct.getSignal().getConnector()
						|| t.getC2()==ct.getSignal().getConnector()) {
					ct.getSignal().setTrack(t);
					break;
				}
			}
		}
		}
		
		int entryCount = 0;
		for(uk.ac.swanseacoventry.cmt.ontrack.Signal s : barkston.getSignals()){
			Connector c = s.getConnector();
			Track t = s.getTrack();
			boolean found = false;
			for(Track t1 : c.getTracks()) {
				if (t1!=t) {
					s.setTrack(t1);
					t1.getSignals().add(s);
					found = true;
					break;
				}
			}
			if (!found) {
				Track entry = OntrackFactory.eINSTANCE.createTrack();
				entry.setName("ENTRY_" + (entryCount++));
				barkston.getTracks().add(entry);
				Connector ec = OntrackFactory.eINSTANCE.createConnector();
				ec.setId(conNum++);
				barkston.getConnectors().add(ec);
				
				s.setTrack(entry);
				
				entry.setC1(ec);
				entry.setC2(c);
				entry.getSignals().add(s);
				
				ec.getTrack1s().add(entry);
				c.getTrack2s().add(entry);
			}
		
		}
		
		for(Point pn : createdPoints.keySet()){
			uk.ac.swanseacoventry.cmt.ontrack.Point p = createdPoints.get(pn);
			Connector c = createdConnectors.get(pn);
			// if (c==null) break;
			HashSet<Track> nts = normalPointTracks.get(c);
			HashSet<Track> rts = reversePointTracks.get(c);
			// if (nts==null || rts==null) break;
			HashSet<Track> commonTracks = new HashSet<Track>();
			commonTracks.addAll(nts);
			commonTracks.retainAll(rts);
			nts.removeAll(commonTracks);
			rts.removeAll(commonTracks);
			//if (commonTracks.size()>0 && nts.size()>0 && rts.size()>0) 
			{
				Track ct = (Track)commonTracks.toArray()[0];
				Track nt = (Track)nts.toArray()[0];
				Track rt = (Track)rts.toArray()[0];
				
				Connector c1 = p.getNormalTrack().getC1();
				Connector c2 = p.getNormalTrack().getC2();
				Connector c3 = p.getReverseTrack().getC2();
				
				if (ct.getC1()==c) {
					ct.setC1(c1);
					c1.getTrack1s().add(ct);
				}
				else {
					ct.setC2(c1);
					c1.getTrack2s().add(ct);
				}

				if (nt.getC1()==c) {
					nt.setC1(c2);
					c2.getTrack1s().add(nt);
				}
				else {
					nt.setC2(c2);
					c2.getTrack2s().add(nt);
				}

				if (rt.getC1()==c) {
					rt.setC1(c3);
					c3.getTrack1s().add(rt);
				}
				else {
					rt.setC2(c3);
					c3.getTrack2s().add(rt);
				}
				
				barkston.getConnectors().remove(c);
			}
			
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
		File f = new File(outputFile + ".xmi");
		File newf = new File(outputFile);
		f.renameTo(newf);
		return newf;
		
		//test
		 
		 */
	}
	
}

