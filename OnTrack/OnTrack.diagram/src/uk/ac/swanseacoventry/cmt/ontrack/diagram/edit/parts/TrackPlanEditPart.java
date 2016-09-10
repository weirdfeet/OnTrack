package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts;

import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;

import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.policies.TrackPlanCanonicalEditPolicy;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.policies.TrackPlanItemSemanticEditPolicy;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.policies.custom.TrackPlanCustomCreationEditPolicy;

/**
 * @generated
 */
public class TrackPlanEditPart extends DiagramEditPart {

	public boolean isTopoRouteHighlighted = false;

	/**
	* @generated
	*/
	public final static String MODEL_ID = "Ontrack"; //$NON-NLS-1$

	/**
	* @generated
	*/
	public static final int VISUAL_ID = 1000;

	/**
	* @generated
	*/
	public TrackPlanEditPart(View view) {
		super(view);
	}

	/**
	* @generated NOT
	*/
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new TrackPlanItemSemanticEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE, new TrackPlanCanonicalEditPolicy());
		installEditPolicy(EditPolicyRoles.CREATION_ROLE, new TrackPlanCustomCreationEditPolicy());
		// new CreationEditPolicyWithCustomReparent(OntrackVisualIDRegistry.TYPED_INSTANCE));
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.POPUPBAR_ROLE);
	}

}
