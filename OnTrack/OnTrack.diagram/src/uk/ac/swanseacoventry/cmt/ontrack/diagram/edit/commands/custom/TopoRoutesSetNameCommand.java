package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

import uk.ac.swanseacoventry.cmt.ontrack.TopoRoute;

public class TopoRoutesSetNameCommand  extends AbstractTransactionalCommand {
	
	private DiagramEditPart trackplanEP;
	private TopoRoute topoRoute;
	private String[] names;
	
	public TopoRoutesSetNameCommand(IGraphicalEditPart ep, TopoRoute tr, String[] ns) {
		super(ep.getEditingDomain(),"set-topo-route-name",null);
		trackplanEP = (DiagramEditPart)ep;
		topoRoute = tr;
		names = ns;
	}
	
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		if (names!=null){
			topoRoute.getNames().clear();
			for(String n : names)
				if (!n.trim().equals(""))
					topoRoute.getNames().add(n);
		}
		return CommandResult.newOKCommandResult();
		
	}
	

}
