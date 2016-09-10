package uk.ac.swanseacoventry.cmt.ontrack.diagram.navigator;

import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.ConnectorEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.ConnectorIdEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.CrossingEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.EntranceConnectorEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.EntranceEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.ExitConnectorEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.ExitEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.NewCrossingEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.NewPointEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.NewTrackEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.PointEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.PointNameEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.SignalConnectorEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.SignalEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.SignalNameEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.SignalTrackEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TerminalConnectorEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TerminalEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TrackEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TrackNameEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TrackPlanEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackDiagramEditorPlugin;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackVisualIDRegistry;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.providers.OntrackElementTypes;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.providers.OntrackParserProvider;

/**
 * @generated
 */
public class OntrackNavigatorLabelProvider extends LabelProvider
		implements ICommonLabelProvider, ITreePathLabelProvider {

	/**
	* @generated
	*/
	static {
		OntrackDiagramEditorPlugin.getInstance().getImageRegistry().put("Navigator?UnknownElement", //$NON-NLS-1$
				ImageDescriptor.getMissingImageDescriptor());
		OntrackDiagramEditorPlugin.getInstance().getImageRegistry().put("Navigator?ImageNotFound", //$NON-NLS-1$
				ImageDescriptor.getMissingImageDescriptor());
	}

	/**
	* @generated
	*/
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof OntrackNavigatorItem && !isOwnView(((OntrackNavigatorItem) element).getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	* @generated
	*/
	public Image getImage(Object element) {
		if (element instanceof OntrackNavigatorGroup) {
			OntrackNavigatorGroup group = (OntrackNavigatorGroup) element;
			return OntrackDiagramEditorPlugin.getInstance().getBundledImage(group.getIcon());
		}

		if (element instanceof OntrackNavigatorItem) {
			OntrackNavigatorItem navigatorItem = (OntrackNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return super.getImage(element);
			}
			return getImage(navigatorItem.getView());
		}

		return super.getImage(element);
	}

	/**
	* @generated
	*/
	public Image getImage(View view) {
		switch (OntrackVisualIDRegistry.getVisualID(view)) {
		case TrackPlanEditPart.VISUAL_ID:
			return getImage("Navigator?Diagram?http:///uk/ac/swanseacoventry/cmt/ontrack.ecore?TrackPlan", //$NON-NLS-1$
					OntrackElementTypes.TrackPlan_1000);
		case ConnectorEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http:///uk/ac/swanseacoventry/cmt/ontrack.ecore?Connector", //$NON-NLS-1$
					OntrackElementTypes.Connector_2001);
		case SignalEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http:///uk/ac/swanseacoventry/cmt/ontrack.ecore?Signal", //$NON-NLS-1$
					OntrackElementTypes.Signal_2002);
		case EntranceEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http:///uk/ac/swanseacoventry/cmt/ontrack.ecore?Entrance", //$NON-NLS-1$
					OntrackElementTypes.Entrance_2003);
		case ExitEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http:///uk/ac/swanseacoventry/cmt/ontrack.ecore?Exit", //$NON-NLS-1$
					OntrackElementTypes.Exit_2004);
		case TerminalEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http:///uk/ac/swanseacoventry/cmt/ontrack.ecore?Terminal", //$NON-NLS-1$
					OntrackElementTypes.Terminal_2005);
		case NewTrackEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http:///uk/ac/swanseacoventry/cmt/ontrack.ecore?NewTrack", //$NON-NLS-1$
					OntrackElementTypes.NewTrack_2006);
		case NewPointEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http:///uk/ac/swanseacoventry/cmt/ontrack.ecore?NewPoint", //$NON-NLS-1$
					OntrackElementTypes.NewPoint_2007);
		case NewCrossingEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http:///uk/ac/swanseacoventry/cmt/ontrack.ecore?NewCrossing", //$NON-NLS-1$
					OntrackElementTypes.NewCrossing_2008);
		case PointEditPart.VISUAL_ID:
			return getImage("Navigator?Link?http:///uk/ac/swanseacoventry/cmt/ontrack.ecore?Point", //$NON-NLS-1$
					OntrackElementTypes.Point_4001);
		case CrossingEditPart.VISUAL_ID:
			return getImage("Navigator?Link?http:///uk/ac/swanseacoventry/cmt/ontrack.ecore?Crossing", //$NON-NLS-1$
					OntrackElementTypes.Crossing_4002);
		case SignalTrackEditPart.VISUAL_ID:
			return getImage("Navigator?Link?http:///uk/ac/swanseacoventry/cmt/ontrack.ecore?Signal?track", //$NON-NLS-1$
					OntrackElementTypes.SignalTrack_4003);
		case SignalConnectorEditPart.VISUAL_ID:
			return getImage("Navigator?Link?http:///uk/ac/swanseacoventry/cmt/ontrack.ecore?Signal?connector", //$NON-NLS-1$
					OntrackElementTypes.SignalConnector_4004);
		case TrackEditPart.VISUAL_ID:
			return getImage("Navigator?Link?http:///uk/ac/swanseacoventry/cmt/ontrack.ecore?Track", //$NON-NLS-1$
					OntrackElementTypes.Track_4005);
		case EntranceConnectorEditPart.VISUAL_ID:
			return getImage("Navigator?Link?http:///uk/ac/swanseacoventry/cmt/ontrack.ecore?Entrance?connector", //$NON-NLS-1$
					OntrackElementTypes.EntranceConnector_4006);
		case ExitConnectorEditPart.VISUAL_ID:
			return getImage("Navigator?Link?http:///uk/ac/swanseacoventry/cmt/ontrack.ecore?Exit?connector", //$NON-NLS-1$
					OntrackElementTypes.ExitConnector_4007);
		case TerminalConnectorEditPart.VISUAL_ID:
			return getImage("Navigator?Link?http:///uk/ac/swanseacoventry/cmt/ontrack.ecore?Terminal?connector", //$NON-NLS-1$
					OntrackElementTypes.TerminalConnector_4008);
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	* @generated
	*/
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = OntrackDiagramEditorPlugin.getInstance().getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null && OntrackElementTypes.isKnownElementType(elementType)) {
			image = OntrackElementTypes.getImage(elementType);
			imageRegistry.put(key, image);
		}

		if (image == null) {
			image = imageRegistry.get("Navigator?ImageNotFound"); //$NON-NLS-1$
			imageRegistry.put(key, image);
		}
		return image;
	}

	/**
	* @generated
	*/
	public String getText(Object element) {
		if (element instanceof OntrackNavigatorGroup) {
			OntrackNavigatorGroup group = (OntrackNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof OntrackNavigatorItem) {
			OntrackNavigatorItem navigatorItem = (OntrackNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return null;
			}
			return getText(navigatorItem.getView());
		}

		return super.getText(element);
	}

	/**
	* @generated
	*/
	public String getText(View view) {
		if (view.getElement() != null && view.getElement().eIsProxy()) {
			return getUnresolvedDomainElementProxyText(view);
		}
		switch (OntrackVisualIDRegistry.getVisualID(view)) {
		case TrackPlanEditPart.VISUAL_ID:
			return getTrackPlan_1000Text(view);
		case ConnectorEditPart.VISUAL_ID:
			return getConnector_2001Text(view);
		case SignalEditPart.VISUAL_ID:
			return getSignal_2002Text(view);
		case EntranceEditPart.VISUAL_ID:
			return getEntrance_2003Text(view);
		case ExitEditPart.VISUAL_ID:
			return getExit_2004Text(view);
		case TerminalEditPart.VISUAL_ID:
			return getTerminal_2005Text(view);
		case NewTrackEditPart.VISUAL_ID:
			return getNewTrack_2006Text(view);
		case NewPointEditPart.VISUAL_ID:
			return getNewPoint_2007Text(view);
		case NewCrossingEditPart.VISUAL_ID:
			return getNewCrossing_2008Text(view);
		case PointEditPart.VISUAL_ID:
			return getPoint_4001Text(view);
		case CrossingEditPart.VISUAL_ID:
			return getCrossing_4002Text(view);
		case SignalTrackEditPart.VISUAL_ID:
			return getSignalTrack_4003Text(view);
		case SignalConnectorEditPart.VISUAL_ID:
			return getSignalConnector_4004Text(view);
		case TrackEditPart.VISUAL_ID:
			return getTrack_4005Text(view);
		case EntranceConnectorEditPart.VISUAL_ID:
			return getEntranceConnector_4006Text(view);
		case ExitConnectorEditPart.VISUAL_ID:
			return getExitConnector_4007Text(view);
		case TerminalConnectorEditPart.VISUAL_ID:
			return getTerminalConnector_4008Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	* @generated
	*/
	private String getTrackPlan_1000Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	* @generated
	*/
	private String getConnector_2001Text(View view) {
		IParser parser = OntrackParserProvider.getParser(OntrackElementTypes.Connector_2001,
				view.getElement() != null ? view.getElement() : view,
				OntrackVisualIDRegistry.getType(ConnectorIdEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			OntrackDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getSignal_2002Text(View view) {
		IParser parser = OntrackParserProvider.getParser(OntrackElementTypes.Signal_2002,
				view.getElement() != null ? view.getElement() : view,
				OntrackVisualIDRegistry.getType(SignalNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			OntrackDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5002); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getEntrance_2003Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	* @generated
	*/
	private String getExit_2004Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	* @generated
	*/
	private String getTerminal_2005Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	* @generated
	*/
	private String getNewTrack_2006Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	* @generated
	*/
	private String getNewPoint_2007Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	* @generated
	*/
	private String getNewCrossing_2008Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	* @generated
	*/
	private String getPoint_4001Text(View view) {
		IParser parser = OntrackParserProvider.getParser(OntrackElementTypes.Point_4001,
				view.getElement() != null ? view.getElement() : view,
				OntrackVisualIDRegistry.getType(PointNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			OntrackDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 6001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getCrossing_4002Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	* @generated
	*/
	private String getSignalTrack_4003Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	* @generated
	*/
	private String getSignalConnector_4004Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	* @generated
	*/
	private String getTrack_4005Text(View view) {
		IParser parser = OntrackParserProvider.getParser(OntrackElementTypes.Track_4005,
				view.getElement() != null ? view.getElement() : view,
				OntrackVisualIDRegistry.getType(TrackNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			OntrackDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 6002); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getEntranceConnector_4006Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	* @generated
	*/
	private String getExitConnector_4007Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	* @generated
	*/
	private String getTerminalConnector_4008Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	* @generated
	*/
	private String getUnknownElementText(View view) {
		return "<UnknownElement Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	* @generated
	*/
	private String getUnresolvedDomainElementProxyText(View view) {
		return "<Unresolved domain element Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	* @generated
	*/
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	* @generated
	*/
	public void restoreState(IMemento aMemento) {
	}

	/**
	* @generated
	*/
	public void saveState(IMemento aMemento) {
	}

	/**
	* @generated
	*/
	public String getDescription(Object anElement) {
		return null;
	}

	/**
	* @generated
	*/
	private boolean isOwnView(View view) {
		return TrackPlanEditPart.MODEL_ID.equals(OntrackVisualIDRegistry.getModelID(view));
	}

}
