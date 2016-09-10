package uk.ac.swanseacoventry.cmt.ontrack.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.tooling.runtime.providers.DiagramElementTypeImages;
import org.eclipse.gmf.tooling.runtime.providers.DiagramElementTypes;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import uk.ac.swanseacoventry.cmt.ontrack.OntrackPackage;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.ConnectorEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.CrossingEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.EntranceConnectorEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.EntranceEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.ExitConnectorEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.ExitEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.NewCrossingEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.NewPointEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.NewTrackEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.PointEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.SignalConnectorEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.SignalEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.SignalTrackEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TerminalConnectorEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TerminalEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TrackEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TrackPlanEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackDiagramEditorPlugin;

/**
 * @generated
 */
public class OntrackElementTypes {

	/**
	* @generated
	*/
	private OntrackElementTypes() {
	}

	/**
	* @generated
	*/
	private static Map<IElementType, ENamedElement> elements;

	/**
	* @generated
	*/
	private static DiagramElementTypeImages elementTypeImages = new DiagramElementTypeImages(
			OntrackDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory());

	/**
	* @generated
	*/
	private static Set<IElementType> KNOWN_ELEMENT_TYPES;

	/**
	* @generated
	*/
	public static final IElementType TrackPlan_1000 = getElementType("OnTrack.dsl.diagram.TrackPlan_1000"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Connector_2001 = getElementType("OnTrack.dsl.diagram.Connector_2001"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Signal_2002 = getElementType("OnTrack.dsl.diagram.Signal_2002"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Entrance_2003 = getElementType("OnTrack.dsl.diagram.Entrance_2003"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Exit_2004 = getElementType("OnTrack.dsl.diagram.Exit_2004"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Terminal_2005 = getElementType("OnTrack.dsl.diagram.Terminal_2005"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType NewTrack_2006 = getElementType("OnTrack.dsl.diagram.NewTrack_2006"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType NewPoint_2007 = getElementType("OnTrack.dsl.diagram.NewPoint_2007"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType NewCrossing_2008 = getElementType("OnTrack.dsl.diagram.NewCrossing_2008"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Point_4001 = getElementType("OnTrack.dsl.diagram.Point_4001"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Crossing_4002 = getElementType("OnTrack.dsl.diagram.Crossing_4002"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType SignalTrack_4003 = getElementType("OnTrack.dsl.diagram.SignalTrack_4003"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType SignalConnector_4004 = getElementType("OnTrack.dsl.diagram.SignalConnector_4004"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Track_4005 = getElementType("OnTrack.dsl.diagram.Track_4005"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType EntranceConnector_4006 = getElementType(
			"OnTrack.dsl.diagram.EntranceConnector_4006"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType ExitConnector_4007 = getElementType("OnTrack.dsl.diagram.ExitConnector_4007"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType TerminalConnector_4008 = getElementType(
			"OnTrack.dsl.diagram.TerminalConnector_4008"); //$NON-NLS-1$

	/**
	* @generated
	*/
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		return elementTypeImages.getImageDescriptor(element);
	}

	/**
	* @generated
	*/
	public static Image getImage(ENamedElement element) {
		return elementTypeImages.getImage(element);
	}

	/**
	* @generated
	*/
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		return getImageDescriptor(getElement(hint));
	}

	/**
	* @generated
	*/
	public static Image getImage(IAdaptable hint) {
		return getImage(getElement(hint));
	}

	/**
	* Returns 'type' of the ecore object associated with the hint.
	* 
	* @generated
	*/
	public static ENamedElement getElement(IAdaptable hint) {
		Object type = hint.getAdapter(IElementType.class);
		if (elements == null) {
			elements = new IdentityHashMap<IElementType, ENamedElement>();

			elements.put(TrackPlan_1000, OntrackPackage.eINSTANCE.getTrackPlan());

			elements.put(Connector_2001, OntrackPackage.eINSTANCE.getConnector());

			elements.put(Signal_2002, OntrackPackage.eINSTANCE.getSignal());

			elements.put(Entrance_2003, OntrackPackage.eINSTANCE.getEntrance());

			elements.put(Exit_2004, OntrackPackage.eINSTANCE.getExit());

			elements.put(Terminal_2005, OntrackPackage.eINSTANCE.getTerminal());

			elements.put(NewTrack_2006, OntrackPackage.eINSTANCE.getNewTrack());

			elements.put(NewPoint_2007, OntrackPackage.eINSTANCE.getNewPoint());

			elements.put(NewCrossing_2008, OntrackPackage.eINSTANCE.getNewCrossing());

			elements.put(Point_4001, OntrackPackage.eINSTANCE.getPoint());

			elements.put(Crossing_4002, OntrackPackage.eINSTANCE.getCrossing());

			elements.put(SignalTrack_4003, OntrackPackage.eINSTANCE.getSignal_Track());

			elements.put(SignalConnector_4004, OntrackPackage.eINSTANCE.getSignal_Connector());

			elements.put(Track_4005, OntrackPackage.eINSTANCE.getTrack());

			elements.put(EntranceConnector_4006, OntrackPackage.eINSTANCE.getEntrance_Connector());

			elements.put(ExitConnector_4007, OntrackPackage.eINSTANCE.getExit_Connector());

			elements.put(TerminalConnector_4008, OntrackPackage.eINSTANCE.getTerminal_Connector());
		}
		return (ENamedElement) elements.get(type);
	}

	/**
	* @generated
	*/
	private static IElementType getElementType(String id) {
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	* @generated
	*/
	public static boolean isKnownElementType(IElementType elementType) {
		if (KNOWN_ELEMENT_TYPES == null) {
			KNOWN_ELEMENT_TYPES = new HashSet<IElementType>();
			KNOWN_ELEMENT_TYPES.add(TrackPlan_1000);
			KNOWN_ELEMENT_TYPES.add(Connector_2001);
			KNOWN_ELEMENT_TYPES.add(Signal_2002);
			KNOWN_ELEMENT_TYPES.add(Entrance_2003);
			KNOWN_ELEMENT_TYPES.add(Exit_2004);
			KNOWN_ELEMENT_TYPES.add(Terminal_2005);
			KNOWN_ELEMENT_TYPES.add(NewTrack_2006);
			KNOWN_ELEMENT_TYPES.add(NewPoint_2007);
			KNOWN_ELEMENT_TYPES.add(NewCrossing_2008);
			KNOWN_ELEMENT_TYPES.add(Point_4001);
			KNOWN_ELEMENT_TYPES.add(Crossing_4002);
			KNOWN_ELEMENT_TYPES.add(SignalTrack_4003);
			KNOWN_ELEMENT_TYPES.add(SignalConnector_4004);
			KNOWN_ELEMENT_TYPES.add(Track_4005);
			KNOWN_ELEMENT_TYPES.add(EntranceConnector_4006);
			KNOWN_ELEMENT_TYPES.add(ExitConnector_4007);
			KNOWN_ELEMENT_TYPES.add(TerminalConnector_4008);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	* @generated
	*/
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case TrackPlanEditPart.VISUAL_ID:
			return TrackPlan_1000;
		case ConnectorEditPart.VISUAL_ID:
			return Connector_2001;
		case SignalEditPart.VISUAL_ID:
			return Signal_2002;
		case EntranceEditPart.VISUAL_ID:
			return Entrance_2003;
		case ExitEditPart.VISUAL_ID:
			return Exit_2004;
		case TerminalEditPart.VISUAL_ID:
			return Terminal_2005;
		case NewTrackEditPart.VISUAL_ID:
			return NewTrack_2006;
		case NewPointEditPart.VISUAL_ID:
			return NewPoint_2007;
		case NewCrossingEditPart.VISUAL_ID:
			return NewCrossing_2008;
		case PointEditPart.VISUAL_ID:
			return Point_4001;
		case CrossingEditPart.VISUAL_ID:
			return Crossing_4002;
		case SignalTrackEditPart.VISUAL_ID:
			return SignalTrack_4003;
		case SignalConnectorEditPart.VISUAL_ID:
			return SignalConnector_4004;
		case TrackEditPart.VISUAL_ID:
			return Track_4005;
		case EntranceConnectorEditPart.VISUAL_ID:
			return EntranceConnector_4006;
		case ExitConnectorEditPart.VISUAL_ID:
			return ExitConnector_4007;
		case TerminalConnectorEditPart.VISUAL_ID:
			return TerminalConnector_4008;
		}
		return null;
	}

	/**
	* @generated
	*/
	public static final DiagramElementTypes TYPED_INSTANCE = new DiagramElementTypes(elementTypeImages) {

		/**
		* @generated
		*/
		@Override

		public boolean isKnownElementType(IElementType elementType) {
			return uk.ac.swanseacoventry.cmt.ontrack.diagram.providers.OntrackElementTypes
					.isKnownElementType(elementType);
		}

		/**
		* @generated
		*/
		@Override

		public IElementType getElementTypeForVisualId(int visualID) {
			return uk.ac.swanseacoventry.cmt.ontrack.diagram.providers.OntrackElementTypes.getElementType(visualID);
		}

		/**
		* @generated
		*/
		@Override

		public ENamedElement getDefiningNamedElement(IAdaptable elementTypeAdapter) {
			return uk.ac.swanseacoventry.cmt.ontrack.diagram.providers.OntrackElementTypes
					.getElement(elementTypeAdapter);
		}
	};

}
