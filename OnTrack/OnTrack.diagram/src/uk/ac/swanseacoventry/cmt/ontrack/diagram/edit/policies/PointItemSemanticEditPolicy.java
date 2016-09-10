package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

import uk.ac.swanseacoventry.cmt.ontrack.diagram.providers.OntrackElementTypes;

/**
 * @generated
 */
public class PointItemSemanticEditPolicy extends OntrackBaseItemSemanticEditPolicy {

	/**
	* @generated
	*/
	public PointItemSemanticEditPolicy() {
		super(OntrackElementTypes.Point_4001);
	}

	/**
	* @generated
	*/
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getGEFWrapper(new DestroyElementCommand(req));
	}

}
