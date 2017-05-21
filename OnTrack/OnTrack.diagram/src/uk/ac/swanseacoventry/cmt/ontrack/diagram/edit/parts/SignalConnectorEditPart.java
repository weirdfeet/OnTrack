package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts;

import org.eclipse.draw2d.Connection;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.notation.View;

import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.policies.SignalConnectorItemSemanticEditPolicy;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.policies.custom.NoBendpointEditPolicy;

/**
 * @generated
 */
public class SignalConnectorEditPart extends ConnectionNodeEditPart implements ITreeBranchEditPart {

	/**
	* @generated
	*/
	public static final int VISUAL_ID = 4004;

	/**
	* @generated
	*/
	public SignalConnectorEditPart(View view) {
		super(view);
	}

	/**
	* @generated
	*/
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new SignalConnectorItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, new NoBendpointEditPolicy());
	}

	// NGA: the two following methods override the EditPolicy installation for Bendpoints Role
	//      they forces the use of our BendPoint Edit Policy
	@Override
	protected void refreshRouterChange() {
		super.refreshRouterChange();
		// whenever the old policy was just installed, add our own
		installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, new NoBendpointEditPolicy());
	}

	@Override
	protected void refreshRoutingStyles() {
		super.refreshRoutingStyles();
		// whenever the old policy was just installed, add our own
		installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, new NoBendpointEditPolicy());
	}

	/**
	* Creates figure for this edit part.
	* 
	* Body of this method does not depend on settings in generation model
	* so you may safely remove <i>generated</i> tag and modify it.
	* 
	* @generated
	*/
	protected Connection createConnectionFigure() {
		return new PolylineConnectionEx();
	}

	/**
	* @generated
	*/
	public PolylineConnectionEx getPrimaryShape() {
		return (PolylineConnectionEx) getFigure();
	}

}
