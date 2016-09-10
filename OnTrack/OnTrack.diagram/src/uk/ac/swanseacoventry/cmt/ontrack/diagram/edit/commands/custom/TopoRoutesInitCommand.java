package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom;

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
			DirectedTrack dt = s.getDirectedTrack();
			r.getDirectedTracks().add(dt);
			r.setStartSignal(s);
			PTR.push(r);
		}
		
		// complete TR
		while(!PTR.isEmpty()) {
			TopoRoute r = (TopoRoute)PTR.pop();
			
			DirectedTrack dt = r.getDirectedTracks().get(r.getDirectedTracks().size() - 1);
			Signal se = dt.getSignal();
			HashSet<Exit> es = new HashSet<Exit>();
			es.addAll(dt.getConnector().getExits());
			es.retainAll(trackplan.getExits());
			if (dt.getConnector().getTerminal()!=null || 
					es.size() > 0 || 
					(se!=null && se!=r.getStartSignal())
					) {
				r.setEndSignal(se); 
				TR.push(r);
				continue;
			}
				
			List<DirectedTrack> nexts = dt.getNexts();
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
