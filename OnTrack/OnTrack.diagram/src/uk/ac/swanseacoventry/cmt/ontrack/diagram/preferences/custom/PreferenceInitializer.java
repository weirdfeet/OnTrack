package uk.ac.swanseacoventry.cmt.ontrack.diagram.preferences.custom;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackDiagramEditorPlugin;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	private final String PATH_TO_FDR3_MAC = "/Applications/FDR3.app/Contents/MacOS/fdr3"; // for mac
	private final String PATH_TO_FDR3_WIN = "C:\\Program Files\\FDR\\bin\\fdr3.exe"; // for windows
	private final String PATH_TO_PROB_MAC = "/Applications/ProB/prob"; // on mac, temporary, to be placed in eclipse preferences
	private final String PATH_TO_PROB_WIN = "C:\\Program Files (x86)\\ProB\\ProBWin.exe"; // on win, temporary, to be placed in eclipse preferences
	private final String PATH_TO_CATS_MAC = ""; 
	private final String PATH_TO_CATS_WIN = ""; 

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = OntrackDiagramEditorPlugin.getInstance().getPreferenceStore();
		String os = System.getProperty("os.name").toLowerCase();
		if (os.startsWith("windows")){
			store.setDefault(PreferenceConstants.FDR3_PATH, PATH_TO_FDR3_WIN);
			store.setDefault(PreferenceConstants.PROB_PATH, PATH_TO_PROB_WIN);
			store.setDefault(PreferenceConstants.PROB_PATH, PATH_TO_CATS_WIN);
		}
		else {
			store.setDefault(PreferenceConstants.FDR3_PATH, PATH_TO_FDR3_MAC);
			store.setDefault(PreferenceConstants.PROB_PATH, PATH_TO_PROB_MAC);
			store.setDefault(PreferenceConstants.PROB_PATH, PATH_TO_CATS_MAC);
		}
			
//		store.setDefault(PreferenceConstants.P_BOOLEAN, true);
//		store.setDefault(PreferenceConstants.P_CHOICE, "choice2");
//		store.setDefault(PreferenceConstants.P_STRING,
//				"Default value");
	}

}
