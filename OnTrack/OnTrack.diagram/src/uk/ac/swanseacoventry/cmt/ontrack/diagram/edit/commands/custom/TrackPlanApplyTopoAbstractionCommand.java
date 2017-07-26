
package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom;

import java.util.ArrayList;
import java.util.Hashtable;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;

import uk.ac.swanseacoventry.cmt.ontrack.Connector;
import uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem;
import uk.ac.swanseacoventry.cmt.ontrack.DirectedTrack;
import uk.ac.swanseacoventry.cmt.ontrack.ReleaseTableItem;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;

public class TrackPlanApplyTopoAbstractionCommand extends AbstractTransactionalCommand {
	
	private DiagramEditPart trackplanEP;
	private TrackPlan trackplan;
	private Hashtable<Track, ArrayList<Track>> topoAbs;
	
	public TrackPlanApplyTopoAbstractionCommand(IGraphicalEditPart ep, Hashtable<Track, ArrayList<Track>> topoAbs) {
		super(ep.getEditingDomain(),"compute-covering",null);
		trackplanEP = (DiagramEditPart)ep;
		trackplan = (TrackPlan)((View)trackplanEP.getModel()).getElement();
		this.topoAbs = topoAbs;
	}
	
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		for(Track t : topoAbs.keySet()){   // <-dt1-...<c1-t-c2>...-dt2->
			ArrayList<Track> list = topoAbs.get(t);
			Connector c1 = t.getC1();
			DirectedTrack dt1 = t.getDirectedTrackByConnector(c1, false);
			boolean found = true;
			// search for dt1
			while(found){
				found = false;
				dt1.getNexts();
				for(DirectedTrack dt : dt1.getNexts()) {
					if (list.contains(dt.getTrack())) {
						dt1 = dt;
						found = true;
						break;
					}
				}
			}
			Connector c2 = t.getC2();
			DirectedTrack dt2 = t.getDirectedTrackByConnector(c2, false);
			found = true;
			// search for dr2
			while(found){
				found = false;
				dt2.getNexts();
				for(DirectedTrack dt : dt2.getNexts()) {
					if (list.contains(dt.getTrack())) {
						dt2 = dt;
						found = true;
						break;
					}
				}
			}
			// build directios1 and 2
			// ->-> dirs1
			// <-<- dirs2
			ArrayList<DirectedTrack> directions1 = new ArrayList<DirectedTrack>();
			DirectedTrack start = dt1.getOppositeDirectedTrack();
			directions1.add(start);
			while(start!=dt2) {
				for(DirectedTrack dt : start.getNexts()){
					if (list.contains(dt.getTrack()) || dt.getTrack()==t){
						start = dt;
						directions1.add(start);
						break;
					}
				}
			}
			ArrayList<DirectedTrack> directions2 = new ArrayList<DirectedTrack>();
			start = dt2.getOppositeDirectedTrack();
			directions2.add(start);
			while(start!=dt1) {
				for(DirectedTrack dt : start.getNexts()){
					if (list.contains(dt.getTrack()) || dt.getTrack()==t){
						start = dt;
						directions2.add(start);
						break;
					}
				}
			}
			// remove clears in control table
			for(ControlTableItem cti : trackplan.getControlTable()){
				cti.getClears().removeAll(list);
				if (cti.getDirections().containsAll(directions1)){
					cti.getDirections().removeAll(directions1);
					cti.getDirections().add(t.getDirectedTrackByConnector(c1, true));
				} else if (cti.getDirections().containsAll(directions2)){
					cti.getDirections().removeAll(directions2);
					cti.getDirections().add(t.getDirectedTrackByConnector(c2, true));
				} else {
					cti.getDirections().removeAll(directions1);
					cti.getDirections().removeAll(directions2);
				}
			}
			// replace occ and unocc tracks in release table
			for(ReleaseTableItem rti : trackplan.getReleaseTable()){
				if (list.contains(rti.getOccupiedTrack()))
					rti.setOccupiedTrack(t);
				if (list.contains(rti.getUnoccupiedTrack()))
					rti.setUnoccupiedTrack(t);
			}
			// remove track in list
			DirectedTrack t1 = t.getDirectedTrackByConnector(c1, false);
			DirectedTrack t2 = t.getDirectedTrackByConnector(c2, false);
			c1 = dt1.getConnector();
			c2 = dt2.getConnector();
			c1.getTrack1s().removeAll(list);
			c1.getTrack2s().removeAll(list);
			t.setC1(c1);
			t1.setConnector(c1);
			c1.getTrack1s().add(t);
			c2.getTrack1s().removeAll(list);
			c2.getTrack2s().removeAll(list);
			t.setC2(c2);
			t2.setConnector(c2);
			c2.getTrack2s().add(t);
			
			trackplan.getTracks().removeAll(list);
			// HashSet<Connector> emptyCons = new HashSet<Connector>();
			for(Track deletedTrack : list){
				if (deletedTrack.getC1()!=null) trackplan.getConnectors().remove(deletedTrack.getC1());
				if (deletedTrack.getC2()!=null) trackplan.getConnectors().remove(deletedTrack.getC2());
			}
		}
		return CommandResult.newOKCommandResult();
		
	}
	
	
}
