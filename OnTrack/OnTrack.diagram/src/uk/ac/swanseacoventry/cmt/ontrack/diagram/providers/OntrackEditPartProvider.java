package uk.ac.swanseacoventry.cmt.ontrack.diagram.providers;

import org.eclipse.gmf.tooling.runtime.providers.DefaultEditPartProvider;

import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.OntrackEditPartFactory;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TrackPlanEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackVisualIDRegistry;

/**
 * @generated
 */
public class OntrackEditPartProvider extends DefaultEditPartProvider {

	/**
	* @generated
	*/
	public OntrackEditPartProvider() {
		super(new OntrackEditPartFactory(), OntrackVisualIDRegistry.TYPED_INSTANCE, TrackPlanEditPart.MODEL_ID);
	}

}
