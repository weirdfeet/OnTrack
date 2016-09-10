package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;

import uk.ac.swanseacoventry.cmt.ontrack.Connector;
import uk.ac.swanseacoventry.cmt.ontrack.DirectedTrack;
import uk.ac.swanseacoventry.cmt.ontrack.OntrackFactory;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.policies.OntrackBaseItemSemanticEditPolicy;

/**
 * @generated
 */
public class TrackCreateCommand extends EditElementCommand {

	/**
	* @generated
	*/
	private final EObject source;

	/**
	* @generated
	*/
	private final EObject target;

	/**
	* @generated
	*/
	private final TrackPlan container;

	/**
	* @generated
	*/
	public TrackCreateCommand(CreateRelationshipRequest request, EObject source, EObject target) {
		super(request.getLabel(), null, request);
		this.source = source;
		this.target = target;
		container = deduceContainer(source, target);
	}

	/**
	* @generated
	*/
	public boolean canExecute() {
		if (source == null && target == null) {
			return false;
		}
		if (source != null && false == source instanceof Connector) {
			return false;
		}
		if (target != null && false == target instanceof Connector) {
			return false;
		}
		if (getSource() == null) {
			return true; // link creation is in progress; source is not defined yet
		}
		// target may be null here but it's possible to check constraint
		if (getContainer() == null) {
			return false;
		}
		return OntrackBaseItemSemanticEditPolicy.getLinkConstraints().canCreateTrack_4005(getContainer(), getSource(),
				getTarget());
	}

	/**
	* @generated NOT
	*/
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		if (!canExecute()) {
			throw new ExecutionException("Invalid arguments in create link command"); //$NON-NLS-1$
		}

		Track newElement = OntrackFactory.eINSTANCE.createTrack();
		getContainer().getTracks().add(newElement);
		newElement.setC1(getSource());
		newElement.setC2(getTarget());

		DirectedTrack trackToC1 = OntrackFactory.eINSTANCE.createDirectedTrack();
		trackToC1.setTrack(newElement);
		trackToC1.setConnector(getSource());

		DirectedTrack trackToC2 = OntrackFactory.eINSTANCE.createDirectedTrack();
		trackToC2.setTrack(newElement);
		trackToC2.setConnector(getTarget());

		newElement.getDirectedTracks().add(trackToC1);
		newElement.getDirectedTracks().add(trackToC2);

		doConfigure(newElement, monitor, info);
		((CreateElementRequest) getRequest()).setNewElement(newElement);
		return CommandResult.newOKCommandResult(newElement);

	}

	/**
	* @generated
	*/
	protected void doConfigure(Track newElement, IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		IElementType elementType = ((CreateElementRequest) getRequest()).getElementType();
		ConfigureRequest configureRequest = new ConfigureRequest(getEditingDomain(), newElement, elementType);
		configureRequest.setClientContext(((CreateElementRequest) getRequest()).getClientContext());
		configureRequest.addParameters(getRequest().getParameters());
		configureRequest.setParameter(CreateRelationshipRequest.SOURCE, getSource());
		configureRequest.setParameter(CreateRelationshipRequest.TARGET, getTarget());
		ICommand configureCommand = elementType.getEditCommand(configureRequest);
		if (configureCommand != null && configureCommand.canExecute()) {
			configureCommand.execute(monitor, info);
		}
	}

	/**
	* @generated
	*/
	protected void setElementToEdit(EObject element) {
		throw new UnsupportedOperationException();
	}

	/**
	* @generated
	*/
	protected Connector getSource() {
		return (Connector) source;
	}

	/**
	* @generated
	*/
	protected Connector getTarget() {
		return (Connector) target;
	}

	/**
	* @generated
	*/
	public TrackPlan getContainer() {
		return container;
	}

	/**
	* Default approach is to traverse ancestors of the source to find instance of container.
	* Modify with appropriate logic.
	* @generated
	*/
	private static TrackPlan deduceContainer(EObject source, EObject target) {
		// Find container element for the new link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null; element = element.eContainer()) {
			if (element instanceof TrackPlan) {
				return (TrackPlan) element;
			}
		}
		return null;
	}

}
