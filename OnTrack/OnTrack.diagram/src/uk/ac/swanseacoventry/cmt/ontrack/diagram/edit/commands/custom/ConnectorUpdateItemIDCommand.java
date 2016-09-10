package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

import uk.ac.swanseacoventry.cmt.ontrack.Connector;

public class ConnectorUpdateItemIDCommand extends AbstractTransactionalCommand {
	
	//private DiagramEditPart trackplanEP;
	//private TrackPlan trackplan;
	private Connector connector;
	private int id;
	
	public ConnectorUpdateItemIDCommand(IGraphicalEditPart ep, Connector c, int i) {
		super(ep.getEditingDomain(),"initialise-control-table",null);
		//trackplanEP = (DiagramEditPart)ep;
		//trackplan = (TrackPlan)((View)trackplanEP.getModel()).getElement();
		connector = c;
		id = i;
	}
	
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		
		if (connector!=null && id > 0)
			connector.setId(id);
		return CommandResult.newOKCommandResult();
		
	}
	
}
