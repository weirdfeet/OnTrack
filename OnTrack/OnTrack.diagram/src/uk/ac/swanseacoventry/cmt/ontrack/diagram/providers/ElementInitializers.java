package uk.ac.swanseacoventry.cmt.ontrack.diagram.providers;

import uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackDiagramEditorPlugin;

/**
 * @generated
 */
public class ElementInitializers {

	protected ElementInitializers() {
		// use #getInstance to access cached instance
	}

	/**
	* @generated
	*/
	public static ElementInitializers getInstance() {
		ElementInitializers cached = OntrackDiagramEditorPlugin.getInstance().getElementInitializers();
		if (cached == null) {
			OntrackDiagramEditorPlugin.getInstance().setElementInitializers(cached = new ElementInitializers());
		}
		return cached;
	}
}
