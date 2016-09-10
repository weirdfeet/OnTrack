package uk.ac.swanseacoventry.cmt.ontrack.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.ConnectorEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.providers.OntrackElementTypes;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.providers.OntrackModelingAssistantProvider;

/**
 * @generated
 */
public class OntrackModelingAssistantProviderOfConnectorEditPart extends OntrackModelingAssistantProvider {

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getRelTypesOnSource(IAdaptable source) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnSource((ConnectorEditPart) sourceEditPart);
	}

	/**
	* @generated
	*/
	public List<IElementType> doGetRelTypesOnSource(ConnectorEditPart source) {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(OntrackElementTypes.Track_4005);
		return types;
	}

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getRelTypesOnSourceAndTarget(IAdaptable source, IAdaptable target) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnSourceAndTarget((ConnectorEditPart) sourceEditPart, targetEditPart);
	}

	/**
	* @generated
	*/
	public List<IElementType> doGetRelTypesOnSourceAndTarget(ConnectorEditPart source,
			IGraphicalEditPart targetEditPart) {
		List<IElementType> types = new LinkedList<IElementType>();
		if (targetEditPart instanceof ConnectorEditPart) {
			types.add(OntrackElementTypes.Track_4005);
		}
		return types;
	}

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getTypesForTarget(IAdaptable source, IElementType relationshipType) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		return doGetTypesForTarget((ConnectorEditPart) sourceEditPart, relationshipType);
	}

	/**
	* @generated
	*/
	public List<IElementType> doGetTypesForTarget(ConnectorEditPart source, IElementType relationshipType) {
		List<IElementType> types = new ArrayList<IElementType>();
		if (relationshipType == OntrackElementTypes.Track_4005) {
			types.add(OntrackElementTypes.Connector_2001);
		}
		return types;
	}

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getRelTypesOnTarget(IAdaptable target) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnTarget((ConnectorEditPart) targetEditPart);
	}

	/**
	* @generated
	*/
	public List<IElementType> doGetRelTypesOnTarget(ConnectorEditPart target) {
		List<IElementType> types = new ArrayList<IElementType>(5);
		types.add(OntrackElementTypes.SignalConnector_4004);
		types.add(OntrackElementTypes.Track_4005);
		types.add(OntrackElementTypes.EntranceConnector_4006);
		types.add(OntrackElementTypes.ExitConnector_4007);
		types.add(OntrackElementTypes.TerminalConnector_4008);
		return types;
	}

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getTypesForSource(IAdaptable target, IElementType relationshipType) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return doGetTypesForSource((ConnectorEditPart) targetEditPart, relationshipType);
	}

	/**
	* @generated
	*/
	public List<IElementType> doGetTypesForSource(ConnectorEditPart target, IElementType relationshipType) {
		List<IElementType> types = new ArrayList<IElementType>();
		if (relationshipType == OntrackElementTypes.SignalConnector_4004) {
			types.add(OntrackElementTypes.Signal_2002);
		} else if (relationshipType == OntrackElementTypes.Track_4005) {
			types.add(OntrackElementTypes.Connector_2001);
		} else if (relationshipType == OntrackElementTypes.EntranceConnector_4006) {
			types.add(OntrackElementTypes.Entrance_2003);
		} else if (relationshipType == OntrackElementTypes.ExitConnector_4007) {
			types.add(OntrackElementTypes.Exit_2004);
		} else if (relationshipType == OntrackElementTypes.TerminalConnector_4008) {
			types.add(OntrackElementTypes.Terminal_2005);
		}
		return types;
	}

}
