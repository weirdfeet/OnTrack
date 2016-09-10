package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;

import uk.ac.swanseacoventry.cmt.ontrack.ReleaseTableItem;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;

public class ReleaseTableDelItemCommand extends AbstractTransactionalCommand {
	
	private DiagramEditPart trackplanEP;
	private TrackPlan trackplan;
	private ReleaseTableItem rti;
	
	public ReleaseTableDelItemCommand(IGraphicalEditPart ep, ReleaseTableItem i) {
		super(ep.getEditingDomain(),"release-table-delete-item",null);
		trackplanEP = (DiagramEditPart)ep;
		trackplan = (TrackPlan)((View)trackplanEP.getModel()).getElement();
		rti = i;
	}
	
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		
		if (rti!=null){
			trackplan.getReleaseTable().remove(rti);
		}
		
		
		return CommandResult.newOKCommandResult();
		
	}
	
}
