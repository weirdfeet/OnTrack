package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.ConnectorCreateCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.EntranceCreateCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.ExitCreateCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.NewCrossingCreateCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.NewPointCreateCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.NewTrackCreateCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.SignalCreateCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.TerminalCreateCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.providers.OntrackElementTypes;

/**
 * @generated
 */
public class TrackPlanItemSemanticEditPolicy extends OntrackBaseItemSemanticEditPolicy {

	/**
	* @generated
	*/
	public TrackPlanItemSemanticEditPolicy() {
		super(OntrackElementTypes.TrackPlan_1000);
	}

	/**
	* @generated
	*/
	protected Command getCreateCommand(CreateElementRequest req) {
		if (OntrackElementTypes.Connector_2001 == req.getElementType()) {
			return getGEFWrapper(new ConnectorCreateCommand(req));
		}
		if (OntrackElementTypes.Signal_2002 == req.getElementType()) {
			return getGEFWrapper(new SignalCreateCommand(req));
		}
		if (OntrackElementTypes.Entrance_2003 == req.getElementType()) {
			return getGEFWrapper(new EntranceCreateCommand(req));
		}
		if (OntrackElementTypes.Exit_2004 == req.getElementType()) {
			return getGEFWrapper(new ExitCreateCommand(req));
		}
		if (OntrackElementTypes.Terminal_2005 == req.getElementType()) {
			return getGEFWrapper(new TerminalCreateCommand(req));
		}
		if (OntrackElementTypes.NewTrack_2006 == req.getElementType()) {
			return getGEFWrapper(new NewTrackCreateCommand(req));
		}
		if (OntrackElementTypes.NewPoint_2007 == req.getElementType()) {
			return getGEFWrapper(new NewPointCreateCommand(req));
		}
		if (OntrackElementTypes.NewCrossing_2008 == req.getElementType()) {
			return getGEFWrapper(new NewCrossingCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	* @generated
	*/
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
		return getGEFWrapper(new DuplicateAnythingCommand(editingDomain, req));
	}

	/**
	* @generated
	*/
	private static class DuplicateAnythingCommand extends DuplicateEObjectsCommand {

		/**
		* @generated
		*/
		public DuplicateAnythingCommand(TransactionalEditingDomain editingDomain, DuplicateElementsRequest req) {
			super(editingDomain, req.getLabel(), req.getElementsToBeDuplicated(), req.getAllDuplicatedElementsMap());
		}

	}

}
