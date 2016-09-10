package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.directedit.locator.CellEditorLocatorAccess;

import uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackVisualIDRegistry;

/**
 * @generated
 */
public class OntrackEditPartFactory implements EditPartFactory {

	/**
	* @generated
	*/
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (OntrackVisualIDRegistry.getVisualID(view)) {

			case TrackPlanEditPart.VISUAL_ID:
				return new TrackPlanEditPart(view);

			case ConnectorEditPart.VISUAL_ID:
				return new ConnectorEditPart(view);

			case ConnectorIdEditPart.VISUAL_ID:
				return new ConnectorIdEditPart(view);

			case SignalEditPart.VISUAL_ID:
				return new SignalEditPart(view);

			case SignalNameEditPart.VISUAL_ID:
				return new SignalNameEditPart(view);

			case EntranceEditPart.VISUAL_ID:
				return new EntranceEditPart(view);

			case ExitEditPart.VISUAL_ID:
				return new ExitEditPart(view);

			case TerminalEditPart.VISUAL_ID:
				return new TerminalEditPart(view);

			case NewTrackEditPart.VISUAL_ID:
				return new NewTrackEditPart(view);

			case NewPointEditPart.VISUAL_ID:
				return new NewPointEditPart(view);

			case NewCrossingEditPart.VISUAL_ID:
				return new NewCrossingEditPart(view);

			case PointEditPart.VISUAL_ID:
				return new PointEditPart(view);

			case PointNameEditPart.VISUAL_ID:
				return new PointNameEditPart(view);

			case CrossingEditPart.VISUAL_ID:
				return new CrossingEditPart(view);

			case SignalTrackEditPart.VISUAL_ID:
				return new SignalTrackEditPart(view);

			case SignalConnectorEditPart.VISUAL_ID:
				return new SignalConnectorEditPart(view);

			case TrackEditPart.VISUAL_ID:
				return new TrackEditPart(view);

			case TrackNameEditPart.VISUAL_ID:
				return new TrackNameEditPart(view);

			case EntranceConnectorEditPart.VISUAL_ID:
				return new EntranceConnectorEditPart(view);

			case ExitConnectorEditPart.VISUAL_ID:
				return new ExitConnectorEditPart(view);

			case TerminalConnectorEditPart.VISUAL_ID:
				return new TerminalConnectorEditPart(view);

			}
		}
		return createUnrecognizedEditPart(context, model);
	}

	/**
	* @generated
	*/
	private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
		// Handle creation of unrecognized child node EditParts here
		return null;
	}

	/**
	* @generated
	*/
	public static CellEditorLocator getTextCellEditorLocator(ITextAwareEditPart source) {
		return CellEditorLocatorAccess.INSTANCE.getTextCellEditorLocator(source);
	}

}
