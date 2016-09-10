package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;

import uk.ac.swanseacoventry.cmt.ontrack.TopoRoute;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;

public class TopoRoutesClearCommand extends AbstractTransactionalCommand {
	
	private DiagramEditPart trackplanEP;
	private TrackPlan trackplan;
	
	public TopoRoutesClearCommand(IGraphicalEditPart ep) {
		super(ep.getEditingDomain(),"refreshing-topo-routes",null);
		trackplanEP = (DiagramEditPart)ep;
		trackplan = (TrackPlan)((View)trackplanEP.getModel()).getElement();
	}
	
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		
		EList<TopoRoute> old_trs = trackplan.getTopoRoutes();
		old_trs.clear();

		return CommandResult.newOKCommandResult();
		
	}
	

}
