package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.policies.custom;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

public class PointConnectionEditPolicy extends ConnectionEditPolicy {

	@Override
	protected Command getDeleteCommand(GroupRequest request) {
		// TODO Auto-generated method stub
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
	            .getEditingDomain();
	        
		EditCommandRequestWrapper semReq = null;
		semReq = new EditCommandRequestWrapper(new DestroyElementRequest(editingDomain, false), request.getExtendedData());
		Command semanticCmd = getHost().getCommand(semReq);
		if (semanticCmd != null && semanticCmd.canExecute()) {
			CompoundCommand cc = new CompoundCommand();
			cc.add(semanticCmd);
			return cc;
		}
		return null;
	}


}
