package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;

import uk.ac.swanseacoventry.cmt.ontrack.diagram.providers.OntrackElementTypes;

/**
 * @generated
 */
public class EntranceConnectorItemSemanticEditPolicy extends OntrackBaseItemSemanticEditPolicy {

	/**
	* @generated
	*/
	public EntranceConnectorItemSemanticEditPolicy() {
		super(OntrackElementTypes.EntranceConnector_4006);
	}

	/**
	* @generated
	*/
	protected Command getDestroyReferenceCommand(DestroyReferenceRequest req) {
		return getGEFWrapper(new DestroyReferenceCommand(req));
	}

}
