package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.policies;

import java.util.Iterator;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.EntranceConnectorCreateCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.EntranceConnectorReorientCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.ExitConnectorCreateCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.ExitConnectorReorientCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.SignalConnectorCreateCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.SignalConnectorReorientCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.TerminalConnectorCreateCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.TerminalConnectorReorientCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.TrackCreateCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.TrackReorientCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.EntranceConnectorEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.ExitConnectorEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.SignalConnectorEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TerminalConnectorEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TrackEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackVisualIDRegistry;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.providers.OntrackElementTypes;

/**
 * @generated
 */
public class ConnectorItemSemanticEditPolicy extends OntrackBaseItemSemanticEditPolicy {

	/**
	* @generated
	*/
	public ConnectorItemSemanticEditPolicy() {
		super(OntrackElementTypes.Connector_2001);
	}

	/**
	* @generated
	*/
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		View view = (View) getHost().getModel();
		CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(getEditingDomain(), null);
		cmd.setTransactionNestingEnabled(false);
		for (Iterator<?> it = view.getTargetEdges().iterator(); it.hasNext();) {
			Edge incomingLink = (Edge) it.next();
			if (OntrackVisualIDRegistry.getVisualID(incomingLink) == SignalConnectorEditPart.VISUAL_ID) {
				DestroyReferenceRequest r = new DestroyReferenceRequest(incomingLink.getSource().getElement(), null,
						incomingLink.getTarget().getElement(), false);
				cmd.add(new DestroyReferenceCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (OntrackVisualIDRegistry.getVisualID(incomingLink) == TrackEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (OntrackVisualIDRegistry.getVisualID(incomingLink) == EntranceConnectorEditPart.VISUAL_ID) {
				DestroyReferenceRequest r = new DestroyReferenceRequest(incomingLink.getSource().getElement(), null,
						incomingLink.getTarget().getElement(), false);
				cmd.add(new DestroyReferenceCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (OntrackVisualIDRegistry.getVisualID(incomingLink) == ExitConnectorEditPart.VISUAL_ID) {
				DestroyReferenceRequest r = new DestroyReferenceRequest(incomingLink.getSource().getElement(), null,
						incomingLink.getTarget().getElement(), false);
				cmd.add(new DestroyReferenceCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (OntrackVisualIDRegistry.getVisualID(incomingLink) == TerminalConnectorEditPart.VISUAL_ID) {
				DestroyReferenceRequest r = new DestroyReferenceRequest(incomingLink.getSource().getElement(), null,
						incomingLink.getTarget().getElement(), false);
				cmd.add(new DestroyReferenceCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
		}
		for (Iterator<?> it = view.getSourceEdges().iterator(); it.hasNext();) {
			Edge outgoingLink = (Edge) it.next();
			if (OntrackVisualIDRegistry.getVisualID(outgoingLink) == TrackEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
		}
		EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if (annotation == null) {
			// there are indirectly referenced children, need extra commands: false
			addDestroyShortcutsCommand(cmd, view);
			// delete host element
			cmd.add(new DestroyElementCommand(req));
		} else {
			cmd.add(new DeleteCommand(getEditingDomain(), view));
		}
		return getGEFWrapper(cmd.reduce());
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
		if (OntrackElementTypes.SignalConnector_4004 == req.getElementType()) {
			return null;
		}
		if (OntrackElementTypes.Track_4005 == req.getElementType()) {
			return getGEFWrapper(new TrackCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if (OntrackElementTypes.EntranceConnector_4006 == req.getElementType()) {
			return null;
		}
		if (OntrackElementTypes.ExitConnector_4007 == req.getElementType()) {
			return null;
		}
		if (OntrackElementTypes.TerminalConnector_4008 == req.getElementType()) {
			return null;
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(CreateRelationshipRequest req) {
		if (OntrackElementTypes.SignalConnector_4004 == req.getElementType()) {
			return getGEFWrapper(new SignalConnectorCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if (OntrackElementTypes.Track_4005 == req.getElementType()) {
			return getGEFWrapper(new TrackCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if (OntrackElementTypes.EntranceConnector_4006 == req.getElementType()) {
			return getGEFWrapper(new EntranceConnectorCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if (OntrackElementTypes.ExitConnector_4007 == req.getElementType()) {
			return getGEFWrapper(new ExitConnectorCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if (OntrackElementTypes.TerminalConnector_4008 == req.getElementType()) {
			return getGEFWrapper(new TerminalConnectorCreateCommand(req, req.getSource(), req.getTarget()));
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
		case TrackEditPart.VISUAL_ID:
			return getGEFWrapper(new TrackReorientCommand(req));
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
		case SignalConnectorEditPart.VISUAL_ID:
			return getGEFWrapper(new SignalConnectorReorientCommand(req));
		case EntranceConnectorEditPart.VISUAL_ID:
			return getGEFWrapper(new EntranceConnectorReorientCommand(req));
		case ExitConnectorEditPart.VISUAL_ID:
			return getGEFWrapper(new ExitConnectorReorientCommand(req));
		case TerminalConnectorEditPart.VISUAL_ID:
			return getGEFWrapper(new TerminalConnectorReorientCommand(req));
		}
		return super.getReorientReferenceRelationshipCommand(req);
	}

}
