package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

import uk.ac.swanseacoventry.cmt.ontrack.ReleaseTableItem;
import uk.ac.swanseacoventry.cmt.ontrack.Track;

public class ReleaseTableUpdateItemCommand extends AbstractTransactionalCommand {
	
	//private DiagramEditPart trackplanEP;
	//private TrackPlan trackplan;
	private ReleaseTableItem udItem;
	private Track udTrack;
	
	public ReleaseTableUpdateItemCommand(IGraphicalEditPart ep, ReleaseTableItem cti, Track t) {
		super(ep.getEditingDomain(),"initialise-control-table",null);
		//trackplanEP = (DiagramEditPart)ep;
		//trackplan = (TrackPlan)((View)trackplanEP.getModel()).getElement();
		udItem = cti;
		udTrack = t;
	}
	
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		
		if (udTrack!=null)
			udItem.setTrack(udTrack);
		return CommandResult.newOKCommandResult();
		
	}
	
}