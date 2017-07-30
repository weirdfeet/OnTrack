package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;

import uk.ac.swanseacoventry.cmt.ontrack.DirectedTrack;
import uk.ac.swanseacoventry.cmt.ontrack.Exit;
import uk.ac.swanseacoventry.cmt.ontrack.OntrackFactory;
import uk.ac.swanseacoventry.cmt.ontrack.Signal;
import uk.ac.swanseacoventry.cmt.ontrack.TopoRoute;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;
import uk.ac.swanseacoventry.cmt.ontrack.impl.TopoRouteImpl;

public class TopoRoutesInitCommand extends AbstractTransactionalCommand {
	
	private DiagramEditPart trackplanEP;
	private TrackPlan trackplan;
	
	public TopoRoutesInitCommand(IGraphicalEditPart ep) {
		super(ep.getEditingDomain(),"refreshing-topo-routes",null);
		trackplanEP = (DiagramEditPart)ep;
		trackplan = (TrackPlan)((View)trackplanEP.getModel()).getElement();
	}
	
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		
		Stack<TopoRoute> trs = collectTopoRouteFrom(trackplan);
		EList<TopoRoute> old_trs = trackplan.getTopoRoutes();
		
		for(TopoRoute tr : trs){
			TopoRoute found = null;
			for(TopoRoute otr : old_trs){
				if (((TopoRouteImpl)tr).hasSameDirectedTrack(otr)){
					found = otr;
					break;
				}
			}
			if (found!=null){
				tr.getNames().clear();
				tr.getNames().addAll(found.getNames());
			}
		}
		
		old_trs.clear();
		old_trs.addAll(trs);
			
		return CommandResult.newOKCommandResult();
		
	}
	
	Stack<TopoRoute> collectTopoRouteFrom(TrackPlan tp){
	
		Stack<TopoRoute> TR = new Stack<TopoRoute>(); // Topo routes
		Stack<TopoRoute> PTR = new Stack<TopoRoute>(); // Partial topo routes
		
		// initialise PTR
		for(Signal s : tp.getSignals()){
			TopoRoute r = OntrackFactory.eINSTANCE.createTopoRoute();
			DirectedTrack dt = null;
			if (s.getTrack()!=null) dt = s.getDirectedTrack();
			if (dt!=null) r.getDirectedTracks().add(dt);
			r.setStartSignal(s);
			PTR.push(r);
		}
		
		// complete TR
		while(!PTR.isEmpty()) {
			TopoRoute r = (TopoRoute)PTR.pop();
			
			DirectedTrack dt = null;
			if (!r.getDirectedTracks().isEmpty()) dt = r.getDirectedTracks().get(r.getDirectedTracks().size() - 1);
			Signal se = null; 
			if (dt!=null) {
				se = dt.getSignal();
				if (se!=null && se!=r.getStartSignal() && r.getEndSignal()==null) r.setEndSignal(se);
				HashSet<Exit> es = new HashSet<Exit>();
				es.addAll(dt.getConnector().getExits());
				es.retainAll(trackplan.getExits());
				if (dt.getConnector().getTerminal()!=null || 
						es.size() > 0 || 
						(se!=r.getStartSignal() && trackplan.isOverlapped() && se!=r.getEndSignal()) ||
						(se!=r.getStartSignal() && !trackplan.isOverlapped() && r.getEndSignal()!=null)
						) {
					if (dt.getTrack().getPointReverse()==null || dt.getTrack().getCrossing2()==null)
						TR.push(r);
					continue;
				}
			}
				
			List<DirectedTrack> nexts = null;
			
			if (dt!=null) {
				nexts = dt.getNexts();
			}
			else {
				nexts = new ArrayList<DirectedTrack>();
				for(Track t : r.getStartSignal().getConnector().getTracks()) {
					nexts.add(t.getDirectedTrackByConnector(r.getStartSignal().getConnector(), true));
				}
			}
				
			boolean first = true;
			for(DirectedTrack n : nexts){
				if (!r.getDirectedTracks().contains(n)){ // not allow cycle
					if (first) {
						r.getDirectedTracks().add(n);
						PTR.push(r);
						first = false;
					} else {
						TopoRoute newr = OntrackFactory.eINSTANCE.createTopoRoute();
						newr.setStartSignal(r.getStartSignal());
						newr.getDirectedTracks().addAll(r.getDirectedTracks());
						newr.getDirectedTracks().set(r.getDirectedTracks().size()-1, n);
						PTR.push(newr);
					}
				}
			}
			
		}
		
		return TR;
	}
	


}
