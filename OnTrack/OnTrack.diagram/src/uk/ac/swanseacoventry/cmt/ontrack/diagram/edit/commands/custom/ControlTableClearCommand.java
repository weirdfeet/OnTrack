package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;

import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;

public class ControlTableClearCommand extends AbstractTransactionalCommand {
	
	private DiagramEditPart trackplanEP;
	private TrackPlan trackplan;
	
	public ControlTableClearCommand(IGraphicalEditPart ep) {
		super(ep.getEditingDomain(),"clear-control-table",null);
		trackplanEP = (DiagramEditPart)ep;
		trackplan = (TrackPlan)((View)trackplanEP.getModel()).getElement();
	}
	
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		
		trackplan.getControlTable().clear();
		return CommandResult.newOKCommandResult();
		
	}
	
}
