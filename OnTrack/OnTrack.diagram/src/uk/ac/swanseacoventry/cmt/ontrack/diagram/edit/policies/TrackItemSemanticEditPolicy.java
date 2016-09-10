package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.CrossingCreateCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.CrossingReorientCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.PointCreateCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.PointReorientCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.SignalTrackCreateCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.SignalTrackReorientCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.CrossingEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.PointEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.SignalTrackEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.providers.OntrackElementTypes;

/**
 * @generated
 */
public class TrackItemSemanticEditPolicy extends OntrackBaseItemSemanticEditPolicy {

	/**
	* @generated
	*/
	public TrackItemSemanticEditPolicy() {
		super(OntrackElementTypes.Track_4005);
	}

	/**
	* @generated
	*/
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getGEFWrapper(new DestroyElementCommand(req));
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		Command command = req.getTarget() == null ? getStartCreateRelationshipCommand(req)
				: getCompleteCreateRelationshipCommand(req);
		return command != null ? command : super.getCreateRelationshipCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getStartCreateRelationshipCommand(CreateRelationshipRequest req) {
		if (OntrackElementTypes.Point_4001 == req.getElementType()) {
			return getGEFWrapper(new PointCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if (OntrackElementTypes.Crossing_4002 == req.getElementType()) {
			return getGEFWrapper(new CrossingCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if (OntrackElementTypes.SignalTrack_4003 == req.getElementType()) {
			return null;
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(CreateRelationshipRequest req) {
		if (OntrackElementTypes.Point_4001 == req.getElementType()) {
			return getGEFWrapper(new PointCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if (OntrackElementTypes.Crossing_4002 == req.getElementType()) {
			return getGEFWrapper(new CrossingCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if (OntrackElementTypes.SignalTrack_4003 == req.getElementType()) {
			return getGEFWrapper(new SignalTrackCreateCommand(req, req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * Returns command to reorient EClass based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientRelationshipCommand(ReorientRelationshipRequest req) {
		switch (getVisualID(req)) {
		case PointEditPart.VISUAL_ID:
			return getGEFWrapper(new PointReorientCommand(req));
		case CrossingEditPart.VISUAL_ID:
			return getGEFWrapper(new CrossingReorientCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
	}

	/**
	 * Returns command to reorient EReference based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientReferenceRelationshipCommand(ReorientReferenceRelationshipRequest req) {
		switch (getVisualID(req)) {
		case SignalTrackEditPart.VISUAL_ID:
			return getGEFWrapper(new SignalTrackReorientCommand(req));
		}
		return super.getReorientReferenceRelationshipCommand(req);
	}

}
