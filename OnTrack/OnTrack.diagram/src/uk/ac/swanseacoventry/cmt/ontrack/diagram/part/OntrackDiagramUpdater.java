package uk.ac.swanseacoventry.cmt.ontrack.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.update.DiagramUpdater;

import uk.ac.swanseacoventry.cmt.ontrack.Connector;
import uk.ac.swanseacoventry.cmt.ontrack.Crossing;
import uk.ac.swanseacoventry.cmt.ontrack.Entrance;
import uk.ac.swanseacoventry.cmt.ontrack.Exit;
import uk.ac.swanseacoventry.cmt.ontrack.NewCrossing;
import uk.ac.swanseacoventry.cmt.ontrack.NewPoint;
import uk.ac.swanseacoventry.cmt.ontrack.NewTrack;
import uk.ac.swanseacoventry.cmt.ontrack.OntrackPackage;
import uk.ac.swanseacoventry.cmt.ontrack.Point;
import uk.ac.swanseacoventry.cmt.ontrack.Signal;
import uk.ac.swanseacoventry.cmt.ontrack.Terminal;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;
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
import uk.ac.swanseacoventry.cmt.ontrack.diagram.providers.OntrackElementTypes;

/**
 * @generated
 */
public class OntrackDiagramUpdater {

	/**
	* @generated
	*/
	public static List<OntrackNodeDescriptor> getSemanticChildren(View view) {
		switch (OntrackVisualIDRegistry.getVisualID(view)) {
		case TrackPlanEditPart.VISUAL_ID:
			return getTrackPlan_1000SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	* @generated
	*/
	public static List<OntrackNodeDescriptor> getTrackPlan_1000SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		TrackPlan modelElement = (TrackPlan) view.getElement();
		LinkedList<OntrackNodeDescriptor> result = new LinkedList<OntrackNodeDescriptor>();
		for (Iterator<?> it = modelElement.getConnectors().iterator(); it.hasNext();) {
			Connector childElement = (Connector) it.next();
			int visualID = OntrackVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == ConnectorEditPart.VISUAL_ID) {
				result.add(new OntrackNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getSignals().iterator(); it.hasNext();) {
			Signal childElement = (Signal) it.next();
			int visualID = OntrackVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == SignalEditPart.VISUAL_ID) {
				result.add(new OntrackNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getEntrances().iterator(); it.hasNext();) {
			Entrance childElement = (Entrance) it.next();
			int visualID = OntrackVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == EntranceEditPart.VISUAL_ID) {
				result.add(new OntrackNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getExits().iterator(); it.hasNext();) {
			Exit childElement = (Exit) it.next();
			int visualID = OntrackVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == ExitEditPart.VISUAL_ID) {
				result.add(new OntrackNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getTerminals().iterator(); it.hasNext();) {
			Terminal childElement = (Terminal) it.next();
			int visualID = OntrackVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == TerminalEditPart.VISUAL_ID) {
				result.add(new OntrackNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		{
			NewTrack childElement = modelElement.getNewTrack();
			int visualID = OntrackVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == NewTrackEditPart.VISUAL_ID) {
				result.add(new OntrackNodeDescriptor(childElement, visualID));
			}
		}
		{
			NewPoint childElement = modelElement.getNewPoint();
			int visualID = OntrackVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == NewPointEditPart.VISUAL_ID) {
				result.add(new OntrackNodeDescriptor(childElement, visualID));
			}
		}
		{
			NewCrossing childElement = modelElement.getNewCrossing();
			int visualID = OntrackVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == NewCrossingEditPart.VISUAL_ID) {
				result.add(new OntrackNodeDescriptor(childElement, visualID));
			}
		}
		return result;
	}

	/**
	* @generated
	*/
	public static List<OntrackLinkDescriptor> getContainedLinks(View view) {
		switch (OntrackVisualIDRegistry.getVisualID(view)) {
		case TrackPlanEditPart.VISUAL_ID:
			return getTrackPlan_1000ContainedLinks(view);
		case ConnectorEditPart.VISUAL_ID:
			return getConnector_2001ContainedLinks(view);
		case SignalEditPart.VISUAL_ID:
			return getSignal_2002ContainedLinks(view);
		case EntranceEditPart.VISUAL_ID:
			return getEntrance_2003ContainedLinks(view);
		case ExitEditPart.VISUAL_ID:
			return getExit_2004ContainedLinks(view);
		case TerminalEditPart.VISUAL_ID:
			return getTerminal_2005ContainedLinks(view);
		case NewTrackEditPart.VISUAL_ID:
			return getNewTrack_2006ContainedLinks(view);
		case NewPointEditPart.VISUAL_ID:
			return getNewPoint_2007ContainedLinks(view);
		case NewCrossingEditPart.VISUAL_ID:
			return getNewCrossing_2008ContainedLinks(view);
		case PointEditPart.VISUAL_ID:
			return getPoint_4001ContainedLinks(view);
		case CrossingEditPart.VISUAL_ID:
			return getCrossing_4002ContainedLinks(view);
		case TrackEditPart.VISUAL_ID:
			return getTrack_4005ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	* @generated
	*/
	public static List<OntrackLinkDescriptor> getIncomingLinks(View view) {
		switch (OntrackVisualIDRegistry.getVisualID(view)) {
		case ConnectorEditPart.VISUAL_ID:
			return getConnector_2001IncomingLinks(view);
		case SignalEditPart.VISUAL_ID:
			return getSignal_2002IncomingLinks(view);
		case EntranceEditPart.VISUAL_ID:
			return getEntrance_2003IncomingLinks(view);
		case ExitEditPart.VISUAL_ID:
			return getExit_2004IncomingLinks(view);
		case TerminalEditPart.VISUAL_ID:
			return getTerminal_2005IncomingLinks(view);
		case NewTrackEditPart.VISUAL_ID:
			return getNewTrack_2006IncomingLinks(view);
		case NewPointEditPart.VISUAL_ID:
			return getNewPoint_2007IncomingLinks(view);
		case NewCrossingEditPart.VISUAL_ID:
			return getNewCrossing_2008IncomingLinks(view);
		case PointEditPart.VISUAL_ID:
			return getPoint_4001IncomingLinks(view);
		case CrossingEditPart.VISUAL_ID:
			return getCrossing_4002IncomingLinks(view);
		case TrackEditPart.VISUAL_ID:
			return getTrack_4005IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	* @generated
	*/
	public static List<OntrackLinkDescriptor> getOutgoingLinks(View view) {
		switch (OntrackVisualIDRegistry.getVisualID(view)) {
		case ConnectorEditPart.VISUAL_ID:
			return getConnector_2001OutgoingLinks(view);
		case SignalEditPart.VISUAL_ID:
			return getSignal_2002OutgoingLinks(view);
		case EntranceEditPart.VISUAL_ID:
			return getEntrance_2003OutgoingLinks(view);
		case ExitEditPart.VISUAL_ID:
			return getExit_2004OutgoingLinks(view);
		case TerminalEditPart.VISUAL_ID:
			return getTerminal_2005OutgoingLinks(view);
		case NewTrackEditPart.VISUAL_ID:
			return getNewTrack_2006OutgoingLinks(view);
		case NewPointEditPart.VISUAL_ID:
			return getNewPoint_2007OutgoingLinks(view);
		case NewCrossingEditPart.VISUAL_ID:
			return getNewCrossing_2008OutgoingLinks(view);
		case PointEditPart.VISUAL_ID:
			return getPoint_4001OutgoingLinks(view);
		case CrossingEditPart.VISUAL_ID:
			return getCrossing_4002OutgoingLinks(view);
		case TrackEditPart.VISUAL_ID:
			return getTrack_4005OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getTrackPlan_1000ContainedLinks(View view) {
		TrackPlan modelElement = (TrackPlan) view.getElement();
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Point_4001(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_Crossing_4002(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_Track_4005(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getConnector_2001ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getSignal_2002ContainedLinks(View view) {
		Signal modelElement = (Signal) view.getElement();
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Signal_Track_4003(modelElement));
		result.addAll(getOutgoingFeatureModelFacetLinks_Signal_Connector_4004(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getEntrance_2003ContainedLinks(View view) {
		Entrance modelElement = (Entrance) view.getElement();
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Entrance_Connector_4006(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getExit_2004ContainedLinks(View view) {
		Exit modelElement = (Exit) view.getElement();
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Exit_Connector_4007(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getTerminal_2005ContainedLinks(View view) {
		Terminal modelElement = (Terminal) view.getElement();
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Terminal_Connector_4008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getNewTrack_2006ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getNewPoint_2007ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getNewCrossing_2008ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getPoint_4001ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getCrossing_4002ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getTrack_4005ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getConnector_2001IncomingLinks(View view) {
		Connector modelElement = (Connector) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		result.addAll(getIncomingFeatureModelFacetLinks_Signal_Connector_4004(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Track_4005(modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_Entrance_Connector_4006(modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_Exit_Connector_4007(modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_Terminal_Connector_4008(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getSignal_2002IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getEntrance_2003IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getExit_2004IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getTerminal_2005IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getNewTrack_2006IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getNewPoint_2007IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getNewCrossing_2008IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getPoint_4001IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getCrossing_4002IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getTrack_4005IncomingLinks(View view) {
		Track modelElement = (Track) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Point_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Crossing_4002(modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_Signal_Track_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getConnector_2001OutgoingLinks(View view) {
		Connector modelElement = (Connector) view.getElement();
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Track_4005(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getSignal_2002OutgoingLinks(View view) {
		Signal modelElement = (Signal) view.getElement();
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Signal_Track_4003(modelElement));
		result.addAll(getOutgoingFeatureModelFacetLinks_Signal_Connector_4004(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getEntrance_2003OutgoingLinks(View view) {
		Entrance modelElement = (Entrance) view.getElement();
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Entrance_Connector_4006(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getExit_2004OutgoingLinks(View view) {
		Exit modelElement = (Exit) view.getElement();
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Exit_Connector_4007(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getTerminal_2005OutgoingLinks(View view) {
		Terminal modelElement = (Terminal) view.getElement();
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Terminal_Connector_4008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getNewTrack_2006OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getNewPoint_2007OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getNewCrossing_2008OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getPoint_4001OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getCrossing_4002OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OntrackLinkDescriptor> getTrack_4005OutgoingLinks(View view) {
		Track modelElement = (Track) view.getElement();
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Point_4001(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_Crossing_4002(modelElement));
		return result;
	}

	/**
	* @generated
	*/
	private static Collection<OntrackLinkDescriptor> getContainedTypeModelFacetLinks_Point_4001(TrackPlan container) {
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		for (Iterator<?> links = container.getPoints().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Point) {
				continue;
			}
			Point link = (Point) linkObject;
			if (PointEditPart.VISUAL_ID != OntrackVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Track dst = link.getNormalTrack();
			Track src = link.getReverseTrack();
			result.add(
					new OntrackLinkDescriptor(src, dst, link, OntrackElementTypes.Point_4001, PointEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	* @generated
	*/
	private static Collection<OntrackLinkDescriptor> getContainedTypeModelFacetLinks_Crossing_4002(
			TrackPlan container) {
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		for (Iterator<?> links = container.getCrossings().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Crossing) {
				continue;
			}
			Crossing link = (Crossing) linkObject;
			if (CrossingEditPart.VISUAL_ID != OntrackVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Track dst = link.getTrack2();
			Track src = link.getTrack1();
			result.add(new OntrackLinkDescriptor(src, dst, link, OntrackElementTypes.Crossing_4002,
					CrossingEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	* @generated
	*/
	private static Collection<OntrackLinkDescriptor> getContainedTypeModelFacetLinks_Track_4005(TrackPlan container) {
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		for (Iterator<?> links = container.getTracks().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Track) {
				continue;
			}
			Track link = (Track) linkObject;
			if (TrackEditPart.VISUAL_ID != OntrackVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Connector dst = link.getC2();
			Connector src = link.getC1();
			result.add(
					new OntrackLinkDescriptor(src, dst, link, OntrackElementTypes.Track_4005, TrackEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<OntrackLinkDescriptor> getIncomingTypeModelFacetLinks_Point_4001(Track target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != OntrackPackage.eINSTANCE.getPoint_NormalTrack()
					|| false == setting.getEObject() instanceof Point) {
				continue;
			}
			Point link = (Point) setting.getEObject();
			if (PointEditPart.VISUAL_ID != OntrackVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Track src = link.getReverseTrack();
			result.add(new OntrackLinkDescriptor(src, target, link, OntrackElementTypes.Point_4001,
					PointEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<OntrackLinkDescriptor> getIncomingTypeModelFacetLinks_Crossing_4002(Track target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != OntrackPackage.eINSTANCE.getCrossing_Track2()
					|| false == setting.getEObject() instanceof Crossing) {
				continue;
			}
			Crossing link = (Crossing) setting.getEObject();
			if (CrossingEditPart.VISUAL_ID != OntrackVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Track src = link.getTrack1();
			result.add(new OntrackLinkDescriptor(src, target, link, OntrackElementTypes.Crossing_4002,
					CrossingEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<OntrackLinkDescriptor> getIncomingFeatureModelFacetLinks_Signal_Track_4003(Track target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == OntrackPackage.eINSTANCE.getSignal_Track()) {
				result.add(new OntrackLinkDescriptor(setting.getEObject(), target, OntrackElementTypes.SignalTrack_4003,
						SignalTrackEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<OntrackLinkDescriptor> getIncomingFeatureModelFacetLinks_Signal_Connector_4004(
			Connector target, Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == OntrackPackage.eINSTANCE.getSignal_Connector()) {
				result.add(new OntrackLinkDescriptor(setting.getEObject(), target,
						OntrackElementTypes.SignalConnector_4004, SignalConnectorEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<OntrackLinkDescriptor> getIncomingTypeModelFacetLinks_Track_4005(Connector target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != OntrackPackage.eINSTANCE.getTrack_C2()
					|| false == setting.getEObject() instanceof Track) {
				continue;
			}
			Track link = (Track) setting.getEObject();
			if (TrackEditPart.VISUAL_ID != OntrackVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Connector src = link.getC1();
			result.add(new OntrackLinkDescriptor(src, target, link, OntrackElementTypes.Track_4005,
					TrackEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<OntrackLinkDescriptor> getIncomingFeatureModelFacetLinks_Entrance_Connector_4006(
			Connector target, Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == OntrackPackage.eINSTANCE.getEntrance_Connector()) {
				result.add(new OntrackLinkDescriptor(setting.getEObject(), target,
						OntrackElementTypes.EntranceConnector_4006, EntranceConnectorEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<OntrackLinkDescriptor> getIncomingFeatureModelFacetLinks_Exit_Connector_4007(
			Connector target, Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == OntrackPackage.eINSTANCE.getExit_Connector()) {
				result.add(new OntrackLinkDescriptor(setting.getEObject(), target,
						OntrackElementTypes.ExitConnector_4007, ExitConnectorEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<OntrackLinkDescriptor> getIncomingFeatureModelFacetLinks_Terminal_Connector_4008(
			Connector target, Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == OntrackPackage.eINSTANCE.getTerminal_Connector()) {
				result.add(new OntrackLinkDescriptor(setting.getEObject(), target,
						OntrackElementTypes.TerminalConnector_4008, TerminalConnectorEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	* @generated
	*/
	private static Collection<OntrackLinkDescriptor> getOutgoingTypeModelFacetLinks_Point_4001(Track source) {
		TrackPlan container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element.eContainer()) {
			if (element instanceof TrackPlan) {
				container = (TrackPlan) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		for (Iterator<?> links = container.getPoints().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Point) {
				continue;
			}
			Point link = (Point) linkObject;
			if (PointEditPart.VISUAL_ID != OntrackVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Track dst = link.getNormalTrack();
			Track src = link.getReverseTrack();
			if (src != source) {
				continue;
			}
			result.add(
					new OntrackLinkDescriptor(src, dst, link, OntrackElementTypes.Point_4001, PointEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	* @generated
	*/
	private static Collection<OntrackLinkDescriptor> getOutgoingTypeModelFacetLinks_Crossing_4002(Track source) {
		TrackPlan container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element.eContainer()) {
			if (element instanceof TrackPlan) {
				container = (TrackPlan) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		for (Iterator<?> links = container.getCrossings().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Crossing) {
				continue;
			}
			Crossing link = (Crossing) linkObject;
			if (CrossingEditPart.VISUAL_ID != OntrackVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Track dst = link.getTrack2();
			Track src = link.getTrack1();
			if (src != source) {
				continue;
			}
			result.add(new OntrackLinkDescriptor(src, dst, link, OntrackElementTypes.Crossing_4002,
					CrossingEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	* @generated
	*/
	private static Collection<OntrackLinkDescriptor> getOutgoingFeatureModelFacetLinks_Signal_Track_4003(
			Signal source) {
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		Track destination = source.getTrack();
		if (destination == null) {
			return result;
		}
		result.add(new OntrackLinkDescriptor(source, destination, OntrackElementTypes.SignalTrack_4003,
				SignalTrackEditPart.VISUAL_ID));
		return result;
	}

	/**
	* @generated
	*/
	private static Collection<OntrackLinkDescriptor> getOutgoingFeatureModelFacetLinks_Signal_Connector_4004(
			Signal source) {
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		Connector destination = source.getConnector();
		if (destination == null) {
			return result;
		}
		result.add(new OntrackLinkDescriptor(source, destination, OntrackElementTypes.SignalConnector_4004,
				SignalConnectorEditPart.VISUAL_ID));
		return result;
	}

	/**
	* @generated
	*/
	private static Collection<OntrackLinkDescriptor> getOutgoingTypeModelFacetLinks_Track_4005(Connector source) {
		TrackPlan container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element.eContainer()) {
			if (element instanceof TrackPlan) {
				container = (TrackPlan) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		for (Iterator<?> links = container.getTracks().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Track) {
				continue;
			}
			Track link = (Track) linkObject;
			if (TrackEditPart.VISUAL_ID != OntrackVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Connector dst = link.getC2();
			Connector src = link.getC1();
			if (src != source) {
				continue;
			}
			result.add(
					new OntrackLinkDescriptor(src, dst, link, OntrackElementTypes.Track_4005, TrackEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	* @generated
	*/
	private static Collection<OntrackLinkDescriptor> getOutgoingFeatureModelFacetLinks_Entrance_Connector_4006(
			Entrance source) {
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		Connector destination = source.getConnector();
		if (destination == null) {
			return result;
		}
		result.add(new OntrackLinkDescriptor(source, destination, OntrackElementTypes.EntranceConnector_4006,
				EntranceConnectorEditPart.VISUAL_ID));
		return result;
	}

	/**
	* @generated
	*/
	private static Collection<OntrackLinkDescriptor> getOutgoingFeatureModelFacetLinks_Exit_Connector_4007(
			Exit source) {
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		Connector destination = source.getConnector();
		if (destination == null) {
			return result;
		}
		result.add(new OntrackLinkDescriptor(source, destination, OntrackElementTypes.ExitConnector_4007,
				ExitConnectorEditPart.VISUAL_ID));
		return result;
	}

	/**
	* @generated
	*/
	private static Collection<OntrackLinkDescriptor> getOutgoingFeatureModelFacetLinks_Terminal_Connector_4008(
			Terminal source) {
		LinkedList<OntrackLinkDescriptor> result = new LinkedList<OntrackLinkDescriptor>();
		Connector destination = source.getConnector();
		if (destination == null) {
			return result;
		}
		result.add(new OntrackLinkDescriptor(source, destination, OntrackElementTypes.TerminalConnector_4008,
				TerminalConnectorEditPart.VISUAL_ID));
		return result;
	}

	/**
	* @generated
	*/
	public static final DiagramUpdater TYPED_INSTANCE = new DiagramUpdater() {
		/**
		* @generated
		*/
		@Override

		public List<OntrackNodeDescriptor> getSemanticChildren(View view) {
			return OntrackDiagramUpdater.getSemanticChildren(view);
		}

		/**
		* @generated
		*/
		@Override

		public List<OntrackLinkDescriptor> getContainedLinks(View view) {
			return OntrackDiagramUpdater.getContainedLinks(view);
		}

		/**
		* @generated
		*/
		@Override

		public List<OntrackLinkDescriptor> getIncomingLinks(View view) {
			return OntrackDiagramUpdater.getIncomingLinks(view);
		}

		/**
		* @generated
		*/
		@Override

		public List<OntrackLinkDescriptor> getOutgoingLinks(View view) {
			return OntrackDiagramUpdater.getOutgoingLinks(view);
		}
	};

}
