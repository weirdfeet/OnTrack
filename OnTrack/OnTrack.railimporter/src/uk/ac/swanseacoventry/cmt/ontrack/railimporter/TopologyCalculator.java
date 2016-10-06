package uk.ac.swanseacoventry.cmt.ontrack.railimporter;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
	
	String[] routes4Barkston = {"R3936","R3935", "R3934", "R3939", "R3938", "R3937"}; 
	
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

//		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new  XMIResourceFactoryImpl());
	
//		Resource myMetaModel= resourceSet.getResource(URI.createFileURI("model/safetrack.ecore"), true);		
		Resource myModel = resourceSet.createResource(URI.createFileURI( outputFile + ".xmi"));

		
		
		TrackPlan barkston = OntrackFactory.eINSTANCE.createTrackPlan();
		
		//ArrayList<Track> tracks = new ArrayList<Track>();

		for(Route r : rp.getRoutes()){
		  if(Arrays.asList(routes4Barkston).contains(r.getName())){
			ArrayList<Point> nPoints = r.getNormalPoints();
			ArrayList<Point> rPoints = r.getReversePoints();
			ControlTableItem ct = OntrackFactory.eINSTANCE.createControlTableItem();
			ct.setRoute(r.getName());
			uk.ac.swanseacoventry.cmt.ontrack.Signal s = OntrackFactory.eINSTANCE.createSignal();
			s.setName(r.getSignal().getName());
			ct.setSignal(s);
			barkston.getSignals().add(s);
			for(Point pn : nPoints){
				uk.ac.swanseacoventry.cmt.ontrack.Point p = OntrackFactory.eINSTANCE.createPoint();
				p.setName(pn.getName());
				ct.getNormals().add(p);
				barkston.getPoints().add(p);
			    barkston.getControlTable().add(ct);
			}
			
			for(Point pn : rPoints){
				uk.ac.swanseacoventry.cmt.ontrack.Point p = OntrackFactory.eINSTANCE.createPoint();
				p.setName(pn.getName());
				ct.getReverses().add(p);
				barkston.getPoints().add(p);
			    barkston.getControlTable().add(ct);
			}
			
			
			TopoRoute tp = OntrackFactory.eINSTANCE.createTopoRoute();
			tp.getNames().add(r.getName());
			tp.setStartSignal(s);
			
			int conNum = 0;
			Connector c1 = OntrackFactory.eINSTANCE.createConnector();
			c1.setId(conNum);
			barkston.getConnectors().add(c1);
			for(TrackCircuit tc: r.getTrackCircuits()){
				
				Track t = OntrackFactory.eINSTANCE.createTrack();
				t.setName(tc.getName());;
				t.setC1(c1);
				
				conNum++;	
				
				Connector c2 = OntrackFactory.eINSTANCE.createConnector();
				c2.setId(conNum);
				t.setC2(c2);
				barkston.getConnectors().add(c2);
				
				
				DirectedTrack dt = OntrackFactory.eINSTANCE.createDirectedTrack();
				dt.setTrack(t);
				dt.setConnector(c1);
				t.getDirectedTracks().add(dt);
				DirectedTrack dt2 = OntrackFactory.eINSTANCE.createDirectedTrack();
				dt2.setTrack(t);
				dt2.setConnector(c2);
				t.getDirectedTracks().add(dt2);
				
				tp.getDirectedTracks().add(dt);
				
				c1 = c2;
				//hack to get signal track
				if(conNum==1){
					s.setTrack(t);
					s.setConnector(c2);
				}
			    			
				ct.getClears().add(t);
				barkston.getTracks().add(t);
				
			}
			
			
			Track last =  tp.getDirectedTracks().get(tp.getDirectedTracks().size()-1).getTrack();
			for(Track find: barkston.getTracks()){
				if(find.getC1() == last.getC2())
				{
					tp.setEndSignal(find.getSignals().get(0));
				}
			}
			barkston.getTopoRoutes().add(tp);
			
		
		 }
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
	}
		
		
		
	
	
//	//todo: inefficient looping through all nodes!
//	private void buildTopologicalRoutes() {
//		ArrayList<Signal> signals = new ArrayList<Signal>();
//		for(Node s : rp.getNodes()){
//			//System.out.println(n.getClass());
//			if(s.getClass().equals(Signal.class)){
//				
//				System.out.println("Start Sig:" + s.getName());
//			
//				TopologicalRoute topoRoute = new TopologicalRoute((Signal) s);
//				
//				Node current = s;
//				
//				Boolean endSignalFound = false;
//				
//				
//				int i = 0;
//				while(!endSignalFound && i < 1000){
//					ArrayList<Node> nextNodes = findSuccesor(current);
//					Node nextNode = nextNodes.get(0); 
//					i++;
//					if(nextNode!=null){
//						System.out.println(nextNode.getName());
//						if(nextNode.getClass().equals(Signal.class)){
//							endSignalFound = true;
//							System.out.println("End Sig:" + current.getName());
//						}
//						else{
//							current = nextNode;
//						}
//					}
//					else{
//						System.out.println("Error next node not found: " + current.getName());
//						endSignalFound = true;
//					}
//				}
//				
//			}
//			
//		}
//		
//	}
//
//	public ArrayList<Node> findSuccesor(Node startNode){
//		ArrayList<Path> paths = rp.getPaths();
//		ArrayList<Node> found = new ArrayList<Node>();
//		for(Path p : paths){
//			if(p.getStartNode().equals(startNode)){
//				found.add(p.getEndNode());
//			}
//		}
//		return found;
//	}
	
//	public static void main(String [] args){
//		TopologyCalculator tp = new TopologyCalculator();
//		
//	}
}

