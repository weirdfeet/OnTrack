package uk.ac.swanseacoventry.cmt.ontrack.abs.covering;

import java.util.HashSet;
import org.eclipse.emf.common.util.EList;

import uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem;
import uk.ac.swanseacoventry.cmt.ontrack.Crossing;
import uk.ac.swanseacoventry.cmt.ontrack.DirectedTrack;
import uk.ac.swanseacoventry.cmt.ontrack.Entrance;
import uk.ac.swanseacoventry.cmt.ontrack.Exit;
import uk.ac.swanseacoventry.cmt.ontrack.OntrackFactory;
import uk.ac.swanseacoventry.cmt.ontrack.Point;
import uk.ac.swanseacoventry.cmt.ontrack.ReleaseTableItem;
import uk.ac.swanseacoventry.cmt.ontrack.Signal;
import uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan;
import uk.ac.swanseacoventry.cmt.ontrack.Terminal;
import uk.ac.swanseacoventry.cmt.ontrack.TopoRoute;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;

public class TrackPlanCovering  {
	
	private TrackPlan trackplan;
	
	private HashSet<DirectedTrack> entryReachables; // set of dtracks that can be reached from any entry

	public TrackPlanCovering(TrackPlan tp) {
		trackplan = tp;
		entryReachables = computeEntryReachables();
	}
	
	HashSet<DirectedTrack> computeEntryReachables(){
		HashSet<DirectedTrack> ret = new HashSet<DirectedTrack>();

		// collect entries
		for(Entrance e : trackplan.getEntrances()){
			for(Track t : e.getConnector().getTracks()){
				ret.add(t.getDirectedTrackByConnector(e.getConnector(), true));
			}
		}
		return computeReachables(ret,false);
	}
		
	HashSet<DirectedTrack> computeReachables(HashSet<DirectedTrack> begins, boolean untilNextSignal){
		HashSet<DirectedTrack> ret = begins;
		
		HashSet<Signal> beginSignals = new HashSet<Signal>();
		for(DirectedTrack dt : begins){
			Signal s = dt.getSignal();
			if (s!=null) beginSignals.add(s);
			if (trackplan.isOverlapped()){
				for(DirectedTrack dt2 : dt.getNexts()){
					Signal s2 = dt2.getTrack().getDirectedTrackByConnector(dt2.getConnector(), true).getSignal();
					if (s2!=null) beginSignals.add(s2);
				}
			}
		}

		// collect reachables
		HashSet<DirectedTrack> nexts = new HashSet<DirectedTrack>();
		do{
			nexts.clear();
			for(DirectedTrack dt : ret){
				if (untilNextSignal){
					Signal s = dt.getTrack().getDirectedTrackByConnector(dt.getConnector(), true).getSignal();
					if (s!=null & !beginSignals.contains(s))
						continue; // found a signal, not continue expanding reachables from this directed track
				}
				nexts.addAll(dt.getNexts());
			}
			nexts.removeAll(ret); // new reachables
			ret.addAll(nexts);
		} while(!nexts.isEmpty());
		
		return ret;
	}
		
	HashSet<DirectedTrack> reverseDirectedTrackSet(HashSet<DirectedTrack> dts){
		HashSet<DirectedTrack> ret = new HashSet<DirectedTrack>();
		for(DirectedTrack dt : dts){
			ret.add(dt.getTrack().getDirectedTrackByConnector(dt.getConnector(), true));
		}
		return ret;
	}
	
	public void doCovering(){
		for(Track t : trackplan.getTracks()){
			if (t.getPointReverse()!=null || t.getCrossing2()!=null) continue;
			HashSet<Track> criticals = new HashSet<Track>();
			criticals.add(t);
			covering(criticals);
		}
		
	}

	public void collapseSubTrackPlans(){
		boolean collapsed = true;
		while(collapsed){
			collapsed = false;
			for(SubTrackPlan stp : trackplan.getSubTrackPlans()){
				for(SubTrackPlan stp2 : trackplan.getSubTrackPlans()){
					if (stp!=stp2 && checkSubPlanIsSubsumed(stp, stp2)){ // stp <= stp2
						stp2.getCriticals().addAll(stp.getCriticals());
						removeSubTrackPlan(stp);
						collapsed = true;
						break;
					}
				}
				if (collapsed)
					break;
			}
		}
	}
	
	private void removeSubTrackPlan(SubTrackPlan stp){
		for(Entrance e : stp.getEntrances()){
			e.getConnector().getEntrances().remove(e);
		}
		for(Exit e : stp.getExits()){
			e.getConnector().getExits().remove(e);
		}
		trackplan.getSubTrackPlans().remove(stp);	
	}

	private boolean checkSubPlanIsSubsumed(SubTrackPlan stp, SubTrackPlan stp2){
		HashSet<Track> trs1 =  new HashSet<Track>(stp.getTracks());
		HashSet<Track> trs2 =  new HashSet<Track>(stp2.getTracks());
		return (trs2.containsAll(trs1));
	}
	
//	class DirectedTrack{ // similar to Directed track but defined here to have free use
//		Track track;
//		Connector dir;
//		DirectedTrack(Track t, Connector d){
//			track = t;
//			dir = d;
//		}
//	}
	
	void covering(HashSet<Track> criticals){
		
		HashSet<DirectedTrack> cone = new HashSet<DirectedTrack>();
		for(Track t : criticals){
			if (t.getName().equals("TC402"))
			{
				String t1 = t.getName();
				t1 = t1 + "-TC402";
			}
			
			cone.addAll(t.getDirectedTracks());
			if (t.getPoint()!=null){
				cone.addAll(t.getPoint().getNormalTrack().getDirectedTracks());
				cone.addAll(t.getPoint().getReverseTrack().getDirectedTracks());
			}
			if (t.getCrossing()!=null){
				cone.addAll(t.getCrossing().getTrack1().getDirectedTracks());
				cone.addAll(t.getCrossing().getTrack2().getDirectedTracks());
			}
		}
		
		// collect DirectedTracks can reach to any criticals maximal up to a signal
		computeReachables(cone, true);
		// add both directions to cone, i.e. reverse directions.
		cone = reverseDirectedTrackSet(cone);
		
		// compute the intersection
		// Rechable tracks from entries (entryReachables).
		cone.retainAll(entryReachables);

		// collect points, crossings, tracks in the region
		HashSet<Point> conePoints = new HashSet<Point>();
		HashSet<Crossing> coneCrossings = new HashSet<Crossing>();
		HashSet<Track> coneTracks = new HashSet<Track>();
		HashSet<DirectedTrack> coneDirectedTracks = new HashSet<DirectedTrack>();
		HashSet<Signal> coneSignals = new HashSet<Signal>();
		for(DirectedTrack dt : cone){
			coneTracks.add(dt.getTrack());
			coneDirectedTracks.addAll(dt.getTrack().getDirectedTracks());

			Point p = dt.getTrack().getPoint();
			if (p!=null) {
				conePoints.add(p);
				coneTracks.add(p.getNormalTrack());
				coneTracks.add(p.getReverseTrack());
			}

			Crossing c = dt.getTrack().getCrossing();
			if (c!=null) {
				coneCrossings.add(c);
				coneTracks.add(c.getTrack1());
				coneTracks.add(c.getTrack2());
			}
			
			
		}
		
		// compute signals within entryCone
		for(Track t : coneTracks){
			coneSignals.addAll(t.getSignals());
		}
		
		
		// copy the control table
		HashSet<OwnControlTableItem> subControlTable = new HashSet<OwnControlTableItem>();
		for(ControlTableItem cti : trackplan.getControlTable()){
			subControlTable.add(new OwnControlTableItem(cti.getRoute(), cti.getSignal(), cti.getNormals(), cti.getReverses(), cti.getClears(), cti.getDirections()));
		}

		// project the control table on the region, get set of releasing tracks
		HashSet<Track> coneReleasingTracks = new HashSet<Track>();
		for(OwnControlTableItem octi : subControlTable){
			octi.normals.retainAll(conePoints);
			octi.reverses.retainAll(conePoints);
			octi.clears.retainAll(coneTracks);
			octi.directions.retainAll(coneDirectedTracks);
			if (!coneSignals.contains(octi.signal))
				octi.signal = null;
			if (octi.signal!=null &&
					!(octi.normals.isEmpty() && octi.reverses.isEmpty()))
				for(ReleaseTableItem rti : trackplan.getReleaseTable()){
					if (rti.getRoute().equals(octi.name) && 
							(octi.normals.contains(rti.getPoint()) || octi.reverses.contains(rti.getPoint())))
						coneReleasingTracks.add(rti.getUnoccupiedTrack());
				}
		}
		
		// get those not in the region
		coneReleasingTracks.removeAll(coneTracks);
		if (!coneReleasingTracks.isEmpty()) {
			criticals.addAll(coneReleasingTracks);
			covering(criticals);
			return;
		}
		
		// ok let's define new subtrackplan TODO
		SubTrackPlan subpl = OntrackFactory.eINSTANCE.createSubTrackPlan();
		subpl.getCriticals().addAll(criticals);
		subpl.getTracks().addAll(coneTracks);
		// subpl.getConnectors().addAll(trackplan.getConnectors());
		subpl.getPoints().addAll(conePoints);
		subpl.getCrossings().addAll(coneCrossings);
		
		// connectors
		for(Track t : coneTracks){
			subpl.getConnectors().add(t.getC1());
			subpl.getConnectors().add(t.getC2());
		}
		
		// signal and control table
		// routes
		HashSet<String> coneRoutes = new HashSet<String>();
		for(OwnControlTableItem octi : subControlTable){
			if (coneSignals.contains(octi.signal) &&
					(!octi.normals.isEmpty() ||
					 !octi.reverses.isEmpty() ||
					 !octi.clears.isEmpty() ||
					 !octi.directions.isEmpty())) {
				subpl.getSignals().add(octi.signal);
				coneRoutes.add(octi.name);
//			}
//			if (!octi.normals.isEmpty() ||
//					 !octi.reverses.isEmpty()){
				ControlTableItem cti = OntrackFactory.eINSTANCE.createControlTableItem();
				cti.setSignal(octi.signal);
				cti.setRoute(octi.name);
				cti.getNormals().addAll(octi.normals);
				cti.getReverses().addAll(octi.reverses);
				cti.getClears().addAll(octi.clears);
				cti.getDirections().addAll(octi.directions);
				subpl.getControlTable().add(cti);
			}
		}
		
		// determine entries, exits, terminal
		for(Track t : coneTracks){
			for(DirectedTrack dt : t.getDirectedTracks()){
				// entries
				for(DirectedTrack pre : dt.getPrevs()){
					if (entryReachables.contains(pre) && !coneTracks.contains(pre.getTrack())){
						Entrance e = OntrackFactory.eINSTANCE.createEntrance();
						e.setConnector(pre.getConnector());
						subpl.getEntrances().add(e);
						break;
					}
				}
				// exits
				for(DirectedTrack nex : dt.getNexts()){
					if (entryReachables.contains(nex) && !coneTracks.contains(nex.getTrack())){
						HashSet<Exit> es = new HashSet<Exit>();
						es.addAll(dt.getConnector().getExits());
						es.retainAll(subpl.getExits());
						if (!es.isEmpty()) continue; // if there is already an exit attached, no need to add more

						Exit e = OntrackFactory.eINSTANCE.createExit();
						e.setConnector(dt.getConnector());
						subpl.getExits().add(e);
						break;
					}
				}
			}
		}
		for(Entrance e : trackplan.getEntrances()){
			if (subpl.getConnectors().contains(e.getConnector())){
				Entrance e1 = OntrackFactory.eINSTANCE.createEntrance();
				e1.setConnector(e.getConnector());
				subpl.getEntrances().add(e1);
			}
		}		
		for(Exit e : trackplan.getExits()){
			if (subpl.getConnectors().contains(e.getConnector())){
				Exit e1 = OntrackFactory.eINSTANCE.createExit();
				e1.setConnector(e.getConnector());
				subpl.getExits().add(e1);
			}
		}
		for(Terminal e : trackplan.getTerminals()){
			if (subpl.getConnectors().contains(e.getConnector())){
				subpl.getTerminals().add(e);
			}
		}
		
		// topo routes
		for(TopoRoute tr : trackplan.getTopoRoutes()){
			if (subpl.getSignals().contains(tr.getStartSignal())){
				TopoRoute trp = OntrackFactory.eINSTANCE.createTopoRoute();
				trp.getNames().addAll(tr.getNames());
				trp.setStartSignal(tr.getStartSignal());
				for(DirectedTrack dt : tr.getDirectedTracks()){
					if (coneTracks.contains(dt.getTrack()))
						trp.getDirectedTracks().add(dt);
				}
				if (subpl.getSignals().contains(tr.getEndSignal()))
					trp.setEndSignal(tr.getEndSignal());
				subpl.getTopoRoutes().add(trp);
			}
		}
		
		// release table
		for(ReleaseTableItem rti : trackplan.getReleaseTable()){
			if (coneRoutes.contains(rti.getRoute()) &&
				conePoints.contains(rti.getPoint())) {
				ReleaseTableItem rtip = OntrackFactory.eINSTANCE.createReleaseTableItem();
				rtip.setRoute(rti.getRoute());
				rtip.setPoint(rti.getPoint());
				rtip.setUnoccupiedTrack(rti.getUnoccupiedTrack());
				if (coneTracks.contains(rti.getOccupiedTrack())) rtip.setOccupiedTrack(rti.getOccupiedTrack());
				subpl.getReleaseTable().add(rtip);
			}
		}
		
		// add this subplan
		trackplan.getSubTrackPlans().add(subpl);
	}
	
	class OwnControlTableItem{
		String name;
		Signal signal;
		HashSet<Point> normals;
		HashSet<Point> reverses;
		HashSet<Track> clears;
		HashSet<DirectedTrack> directions;
		public OwnControlTableItem(String n, Signal s, EList<Point> ns, EList<Point> rs, EList<Track> cs, EList<DirectedTrack> ds) {
			name = n;
			signal = s;
			normals = new HashSet<Point>();
			normals.addAll(ns);
			reverses = new HashSet<Point>();
			reverses.addAll(rs);
			clears = new HashSet<Track>();
			clears.addAll(cs);
			directions = new HashSet<DirectedTrack>();
			directions.addAll(ds);
		}
	}
	
}
