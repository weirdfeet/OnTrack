package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

import uk.ac.swanseacoventry.cmt.ontrack.Crossing;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.policies.OntrackBaseItemSemanticEditPolicy;

/**
 * @generated
 */
public class CrossingReorientCommand extends EditElementCommand {

	/**
	* @generated
	*/
	private final int reorientDirection;

	/**
	* @generated
	*/
	private final EObject oldEnd;

	/**
	* @generated
	*/
	private final EObject newEnd;

	/**
	* @generated
	*/
	public CrossingReorientCommand(ReorientRelationshipRequest request) {
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	* @generated
	*/
	public boolean canExecute() {
		if (false == getElementToEdit() instanceof Crossing) {
			return false;
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return canReorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return canReorientTarget();
		}
		return false;
	}

	/**
	* @generated
	*/
	protected boolean canReorientSource() {
		if (!(oldEnd instanceof Track && newEnd instanceof Track)) {
			return false;
		}
		Track target = getLink().getTrack2();
		if (!(getLink().eContainer() instanceof TrackPlan)) {
			return false;
		}
		TrackPlan container = (TrackPlan) getLink().eContainer();
		return OntrackBaseItemSemanticEditPolicy.getLinkConstraints().canExistCrossing_4002(container, getLink(),
				getNewSource(), target);
	}

	/**
	* @generated
	*/
	protected boolean canReorientTarget() {
		if (!(oldEnd instanceof Track && newEnd instanceof Track)) {
			return false;
		}
		Track source = getLink().getTrack1();
		if (!(getLink().eContainer() instanceof TrackPlan)) {
			return false;
		}
		TrackPlan container = (TrackPlan) getLink().eContainer();
		return OntrackBaseItemSemanticEditPolicy.getLinkConstraints().canExistCrossing_4002(container, getLink(),
				source, getNewTarget());
	}

	/**
	* @generated
	*/
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		if (!canExecute()) {
			throw new ExecutionException("Invalid arguments in reorient link command"); //$NON-NLS-1$
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return reorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return reorientTarget();
		}
		throw new IllegalStateException();
	}

	/**
	* @generated
	*/
	protected CommandResult reorientSource() throws ExecutionException {
		getLink().setTrack1(getNewSource());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	* @generated
	*/
	protected CommandResult reorientTarget() throws ExecutionException {
		getLink().setTrack2(getNewTarget());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	* @generated
	*/
	protected Crossing getLink() {
		return (Crossing) getElementToEdit();
	}

	/**
	* @generated
	*/
	protected Track getOldSource() {
		return (Track) oldEnd;
	}

	/**
	* @generated
	*/
	protected Track getNewSource() {
		return (Track) newEnd;
	}

	/**
	* @generated
	*/
	protected Track getOldTarget() {
		return (Track) oldEnd;
	}

	/**
	* @generated
	*/
	protected Track getNewTarget() {
		return (Track) newEnd;
	}
}
