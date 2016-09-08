package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;

import uk.ac.swanseacoventry.cmt.ontrack.Entrance;
import uk.ac.swanseacoventry.cmt.ontrack.Exit;
import uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;

public class TrackPlanCoveringClearCommand extends AbstractTransactionalCommand {
	
	private DiagramEditPart trackplanEP;
	private TrackPlan trackplan;
	
	public TrackPlanCoveringClearCommand(IGraphicalEditPart ep) {
		super(ep.getEditingDomain(),"compute-covering",null);
		trackplanEP = (DiagramEditPart)ep;
		trackplan = (TrackPlan)((View)trackplanEP.getModel()).getElement();
	}
	
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		trackplan.setSelectedSubTrackPlan(null);
		for(SubTrackPlan stp : trackplan.getSubTrackPlans()){
			for(Entrance e : stp.getEntrances()){
				e.getConnector().getEntrances().remove(e);
			}
			for(Exit e : stp.getExits()){
				e.getConnector().getExits().remove(e);
			}
		}
		trackplan.getSubTrackPlans().clear();
		return CommandResult.newOKCommandResult();
		
	}
	
	
}
