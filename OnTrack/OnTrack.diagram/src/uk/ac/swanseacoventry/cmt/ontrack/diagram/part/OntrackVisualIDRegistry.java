package uk.ac.swanseacoventry.cmt.ontrack.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.structure.DiagramStructure;

import uk.ac.swanseacoventry.cmt.ontrack.OntrackPackage;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.ConnectorEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.ConnectorIdEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.CrossingEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.EntranceEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.ExitEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.NewCrossingEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.NewPointEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.NewTrackEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.PointEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.PointNameEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.SignalEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.SignalNameEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TerminalEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TrackEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TrackNameEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TrackPlanEditPart;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class OntrackVisualIDRegistry {

	/**
	* @generated
	*/
	private static final String DEBUG_KEY = "OnTrack.dsl.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	* @generated
	*/
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (TrackPlanEditPart.MODEL_ID.equals(view.getType())) {
				return TrackPlanEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackVisualIDRegistry.getVisualID(view.getType());
	}

	/**
	* @generated
	*/
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	* @generated
	*/
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(Platform.getDebugOption(DEBUG_KEY))) {
				OntrackDiagramEditorPlugin.getInstance()
						.logError("Unable to parse view type as a visualID number: " + type);
			}
		}
		return -1;
	}

	/**
	* @generated
	*/
	public static String getType(int visualID) {
		return Integer.toString(visualID);
	}

	/**
	* @generated
	*/
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (OntrackPackage.eINSTANCE.getTrackPlan().isSuperTypeOf(domainElement.eClass())
				&& isDiagram((TrackPlan) domainElement)) {
			return TrackPlanEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	* @generated
	*/
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		String containerModelID = uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackVisualIDRegistry
				.getModelID(containerView);
		if (!TrackPlanEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (TrackPlanEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = TrackPlanEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case TrackPlanEditPart.VISUAL_ID:
			if (OntrackPackage.eINSTANCE.getConnector().isSuperTypeOf(domainElement.eClass())) {
				return ConnectorEditPart.VISUAL_ID;
			}
			if (OntrackPackage.eINSTANCE.getSignal().isSuperTypeOf(domainElement.eClass())) {
				return SignalEditPart.VISUAL_ID;
			}
			if (OntrackPackage.eINSTANCE.getEntrance().isSuperTypeOf(domainElement.eClass())) {
				return EntranceEditPart.VISUAL_ID;
			}
			if (OntrackPackage.eINSTANCE.getExit().isSuperTypeOf(domainElement.eClass())) {
				return ExitEditPart.VISUAL_ID;
			}
			if (OntrackPackage.eINSTANCE.getTerminal().isSuperTypeOf(domainElement.eClass())) {
				return TerminalEditPart.VISUAL_ID;
			}
			if (OntrackPackage.eINSTANCE.getNewTrack().isSuperTypeOf(domainElement.eClass())) {
				return NewTrackEditPart.VISUAL_ID;
			}
			if (OntrackPackage.eINSTANCE.getNewPoint().isSuperTypeOf(domainElement.eClass())) {
				return NewPointEditPart.VISUAL_ID;
			}
			if (OntrackPackage.eINSTANCE.getNewCrossing().isSuperTypeOf(domainElement.eClass())) {
				return NewCrossingEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	* @generated
	*/
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackVisualIDRegistry
				.getModelID(containerView);
		if (!TrackPlanEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (TrackPlanEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = TrackPlanEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case TrackPlanEditPart.VISUAL_ID:
			if (ConnectorEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SignalEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EntranceEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ExitEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TerminalEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (NewTrackEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (NewPointEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (NewCrossingEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ConnectorEditPart.VISUAL_ID:
			if (ConnectorIdEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case SignalEditPart.VISUAL_ID:
			if (SignalNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case PointEditPart.VISUAL_ID:
			if (PointNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TrackEditPart.VISUAL_ID:
			if (TrackNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	* @generated
	*/
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (OntrackPackage.eINSTANCE.getPoint().isSuperTypeOf(domainElement.eClass())) {
			return PointEditPart.VISUAL_ID;
		}
		if (OntrackPackage.eINSTANCE.getCrossing().isSuperTypeOf(domainElement.eClass())) {
			return CrossingEditPart.VISUAL_ID;
		}
		if (OntrackPackage.eINSTANCE.getTrack().isSuperTypeOf(domainElement.eClass())) {
			return TrackEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	* User can change implementation of this method to handle some specific
	* situations not covered by default logic.
	* 
	* @generated
	*/
	private static boolean isDiagram(TrackPlan element) {
		return true;
	}

	/**
	* @generated
	*/
	public static boolean checkNodeVisualID(View containerView, EObject domainElement, int candidate) {
		if (candidate == -1) {
			//unrecognized id is always bad
			return false;
		}
		int basic = getNodeVisualID(containerView, domainElement);
		return basic == candidate;
	}

	/**
	* @generated
	*/
	public static boolean isCompartmentVisualID(int visualID) {
		return false;
	}

	/**
	* @generated
	*/
	public static boolean isSemanticLeafVisualID(int visualID) {
		switch (visualID) {
		case TrackPlanEditPart.VISUAL_ID:
			return false;
		case ConnectorEditPart.VISUAL_ID:
		case SignalEditPart.VISUAL_ID:
		case EntranceEditPart.VISUAL_ID:
		case ExitEditPart.VISUAL_ID:
		case TerminalEditPart.VISUAL_ID:
		case NewTrackEditPart.VISUAL_ID:
		case NewPointEditPart.VISUAL_ID:
		case NewCrossingEditPart.VISUAL_ID:
			return true;
		default:
			break;
		}
		return false;
	}

	/**
	* @generated
	*/
	public static final DiagramStructure TYPED_INSTANCE = new DiagramStructure() {
		/**
		* @generated
		*/
		@Override

		public int getVisualID(View view) {
			return uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackVisualIDRegistry.getVisualID(view);
		}

		/**
		* @generated
		*/
		@Override

		public String getModelID(View view) {
			return uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackVisualIDRegistry.getModelID(view);
		}

		/**
		* @generated
		*/
		@Override

		public int getNodeVisualID(View containerView, EObject domainElement) {
			return uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackVisualIDRegistry.getNodeVisualID(containerView,
					domainElement);
		}

		/**
		* @generated
		*/
		@Override

		public boolean checkNodeVisualID(View containerView, EObject domainElement, int candidate) {
			return uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackVisualIDRegistry
					.checkNodeVisualID(containerView, domainElement, candidate);
		}

		/**
		* @generated
		*/
		@Override

		public boolean isCompartmentVisualID(int visualID) {
			return uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackVisualIDRegistry
					.isCompartmentVisualID(visualID);
		}

		/**
		* @generated
		*/
		@Override

		public boolean isSemanticLeafVisualID(int visualID) {
			return uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackVisualIDRegistry
					.isSemanticLeafVisualID(visualID);
		}
	};

}
