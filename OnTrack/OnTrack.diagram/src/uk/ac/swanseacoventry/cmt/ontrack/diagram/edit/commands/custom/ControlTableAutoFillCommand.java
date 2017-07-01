package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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
import uk.ac.swanseacoventry.cmt.ontrack.Point;
import uk.ac.swanseacoventry.cmt.ontrack.Signal;
import uk.ac.swanseacoventry.cmt.ontrack.TopoRoute;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;

public class ControlTableAutoFillCommand extends AbstractTransactionalCommand {
	
	private DiagramEditPart trackplanEP;
	private TrackPlan trackplan;
	
	public ControlTableAutoFillCommand(IGraphicalEditPart ep) {
		super(ep.getEditingDomain(),"auto-fill-control-table",null);
		trackplanEP = (DiagramEditPart)ep;
		trackplan = (TrackPlan)((View)trackplanEP.getModel()).getElement();
	}
	
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		
		
		HashMap<String,TopoRoute> topoRoutes = new HashMap<String,TopoRoute>();
		for(TopoRoute tr : trackplan.getTopoRoutes())
			for(String n : tr.getNames())
				if (!n.trim().equals(""))
					topoRoutes.put(n, tr);
		
		for(ControlTableItem cti : trackplan.getControlTable()){
			if (topoRoutes.containsKey(cti.getRoute())){
				TopoRoute tr = topoRoutes.get(cti.getRoute());
				cti.setSignal(tr.getStartSignal());
				cti.getNormals().clear();
				cti.getReverses().clear();
				cti.getClears().clear();
				cti.getDirections().clear();
				for(DirectedTrack t : tr.getDirectedTracks()){
					if (t.getSignal()!=cti.getSignal()) {
						cti.getClears().add(t.getTrack());
						cti.getDirections().add(t);
					}
					Point p = t.getTrack().getPointNormal();
					if (p!=null) cti.getNormals().add(p);
					Point rp = t.getTrack().getPointReverse();
					if (rp!=null) cti.getReverses().add(rp);
				}
			} else {
				// try to generate direction locks if possible from clears
				if (cti.getRoute().equals("R3938"))
					System.out.println("Break here");
				cti.getDirections().clear();
				Signal s = cti.getSignal();
				Connector c = s.getConnector();
				HashSet<Track> visited = new HashSet<Track>();
				while (c!=null){
					Connector nc = null;
					for(Track t : cti.getClears()){
						if (visited.contains(t)) continue;
						if (t.getC1()==c) {
							nc = t.getC2();
						} else if (t.getC2()==c) {
							nc = t.getC1();
						}
						if (nc!=null) {
							cti.getDirections().add(t.getDirectedTrackByConnector(nc, false));
							visited.add(t);
							break;
						}
					}
					c = nc;
				}
			}
		}
		
		// try to remove redundant directions
		HashMap<DirectedTrack,Boolean> dti = new HashMap<DirectedTrack,Boolean>();
		for(ControlTableItem cti : trackplan.getControlTable()){
			for(DirectedTrack dt : cti.getDirections()) {
				dti.put(dt, false);
			}
		}			
		for(DirectedTrack dt : dti.keySet()){
			if (dti.containsKey(dt.getOppositeDirectedTrack()) || dt.getConnector().getEntrances().size()>0)
				dti.put(dt, true);
		}			
		for(ControlTableItem cti : trackplan.getControlTable()){
			ArrayList<DirectedTrack> dts = new ArrayList<DirectedTrack>();
			for(DirectedTrack dt : cti.getDirections()) {
				if (dti.get(dt).booleanValue()) dts.add(dt);
			}
			cti.getDirections().clear();
			cti.getDirections().addAll(dts);
		}			
				
		return CommandResult.newOKCommandResult();
		
	}
	
}
