package uk.ac.swanseacoventry.cmt.ontrack.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.ConnectorEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TerminalEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.providers.OntrackElementTypes;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.providers.OntrackModelingAssistantProvider;

/**
 * @generated
 */
public class OntrackModelingAssistantProviderOfTerminalEditPart extends OntrackModelingAssistantProvider {

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getRelTypesOnSource(IAdaptable source) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnSource((TerminalEditPart) sourceEditPart);
	}

	/**
	* @generated
	*/
	public List<IElementType> doGetRelTypesOnSource(TerminalEditPart source) {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(OntrackElementTypes.TerminalConnector_4008);
		return types;
	}

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getRelTypesOnSourceAndTarget(IAdaptable source, IAdaptable target) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnSourceAndTarget((TerminalEditPart) sourceEditPart, targetEditPart);
	}

	/**
	* @generated
	*/
	public List<IElementType> doGetRelTypesOnSourceAndTarget(TerminalEditPart source,
			IGraphicalEditPart targetEditPart) {
		List<IElementType> types = new LinkedList<IElementType>();
		if (targetEditPart instanceof ConnectorEditPart) {
			types.add(OntrackElementTypes.TerminalConnector_4008);
		}
		return types;
	}

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getTypesForTarget(IAdaptable source, IElementType relationshipType) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		return doGetTypesForTarget((TerminalEditPart) sourceEditPart, relationshipType);
	}

	/**
	* @generated
	*/
	public List<IElementType> doGetTypesForTarget(TerminalEditPart source, IElementType relationshipType) {
		List<IElementType> types = new ArrayList<IElementType>();
		if (relationshipType == OntrackElementTypes.TerminalConnector_4008) {
			types.add(OntrackElementTypes.Connector_2001);
		}
		return types;
	}

}
