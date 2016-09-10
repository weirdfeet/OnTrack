package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

import uk.ac.swanseacoventry.cmt.ontrack.Connector;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.policies.OntrackBaseItemSemanticEditPolicy;

/**
 * @generated
 */
public class TrackReorientCommand extends EditElementCommand {

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
	public TrackReorientCommand(ReorientRelationshipRequest request) {
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	* @generated
	*/
	public boolean canExecute() {
		if (false == getElementToEdit() instanceof Track) {
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
		if (!(oldEnd instanceof Connector && newEnd instanceof Connector)) {
			return false;
		}
		Connector target = getLink().getC2();
		if (!(getLink().eContainer() instanceof TrackPlan)) {
			return false;
		}
		TrackPlan container = (TrackPlan) getLink().eContainer();
		return OntrackBaseItemSemanticEditPolicy.getLinkConstraints().canExistTrack_4005(container, getLink(),
				getNewSource(), target);
	}

	/**
	* @generated
	*/
	protected boolean canReorientTarget() {
		if (!(oldEnd instanceof Connector && newEnd instanceof Connector)) {
			return false;
		}
		Connector source = getLink().getC1();
		if (!(getLink().eContainer() instanceof TrackPlan)) {
			return false;
		}
		TrackPlan container = (TrackPlan) getLink().eContainer();
		return OntrackBaseItemSemanticEditPolicy.getLinkConstraints().canExistTrack_4005(container, getLink(), source,
				getNewTarget());
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
	* @generated NOT
	*/
	protected CommandResult reorientSource() throws ExecutionException {
		getLink().setC1(getNewSource());
		getLink().getDirectedTracks().get(0).setConnector(getNewSource());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	* @generated NOT
	*/
	protected CommandResult reorientTarget() throws ExecutionException {
		getLink().setC2(getNewTarget());
		getLink().getDirectedTracks().get(1).setConnector(getNewTarget());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	* @generated
	*/
	protected Track getLink() {
		return (Track) getElementToEdit();
	}

	/**
	* @generated
	*/
	protected Connector getOldSource() {
		return (Connector) oldEnd;
	}

	/**
	* @generated
	*/
	protected Connector getNewSource() {
		return (Connector) newEnd;
	}

	/**
	* @generated
	*/
	protected Connector getOldTarget() {
		return (Connector) oldEnd;
	}

	/**
	* @generated
	*/
	protected Connector getNewTarget() {
		return (Connector) newEnd;
	}
}
