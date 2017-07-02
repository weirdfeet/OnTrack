
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

public class VerificationResultUpdateCommand extends AbstractTransactionalCommand {
	
	private DiagramEditPart trackplanEP;
	private TrackPlan trackplan;
	private SubTrackPlan subplan;
	private String time;
	private String states;
	private String result;
	
	public VerificationResultUpdateCommand(IGraphicalEditPart ep, SubTrackPlan subplan, String time, String states, String result) {
		super(ep.getEditingDomain(),"compute-covering",null);
		trackplanEP = (DiagramEditPart)ep;
		trackplan = (TrackPlan)((View)trackplanEP.getModel()).getElement();
		this.subplan = subplan;
		this.time = time;
		this.states = states;
		this.result = result;
	}
	
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		if (subplan !=null) {
			subplan.setVerificationTime(time);
			subplan.setVerificationStates(states);
			subplan.setVerificationResult(result);
		}
		else {
			trackplan.setVerificationTime(time);
			trackplan.setVerificationStates(states);
			trackplan.setVerificationResult(result);
		}
		return CommandResult.newOKCommandResult();
		
	}
	
	
}
