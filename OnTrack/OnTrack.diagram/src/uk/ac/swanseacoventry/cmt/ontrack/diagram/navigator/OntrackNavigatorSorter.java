package uk.ac.swanseacoventry.cmt.ontrack.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;

import uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackVisualIDRegistry;

/**
 * @generated
 */
public class OntrackNavigatorSorter extends ViewerSorter {

	/**
	* @generated
	*/
	private static final int GROUP_CATEGORY = 4010;

	/**
	* @generated
	*/
	public int category(Object element) {
		if (element instanceof OntrackNavigatorItem) {
			OntrackNavigatorItem item = (OntrackNavigatorItem) element;
			return OntrackVisualIDRegistry.getVisualID(item.getView());
		}
		return GROUP_CATEGORY;
	}

}
