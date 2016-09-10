package uk.ac.swanseacoventry.cmt.ontrack.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import uk.ac.swanseacoventry.cmt.ontrack.diagram.providers.OntrackElementTypes;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.providers.OntrackModelingAssistantProvider;

/**
 * @generated
 */
public class OntrackModelingAssistantProviderOfTrackPlanEditPart extends OntrackModelingAssistantProvider {

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getTypesForPopupBar(IAdaptable host) {
		List<IElementType> types = new ArrayList<IElementType>(8);
		types.add(OntrackElementTypes.Connector_2001);
		types.add(OntrackElementTypes.Signal_2002);
		types.add(OntrackElementTypes.Entrance_2003);
		types.add(OntrackElementTypes.Exit_2004);
		types.add(OntrackElementTypes.Terminal_2005);
		types.add(OntrackElementTypes.NewTrack_2006);
		types.add(OntrackElementTypes.NewPoint_2007);
		types.add(OntrackElementTypes.NewCrossing_2008);
		return types;
	}

}
