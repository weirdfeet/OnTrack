package uk.ac.swansea.coventry.cmd.ontrack.diagram.importWizards;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

import uk.ac.swanseacoventry.cmt.ontrack.railimporter.TopologyCalculator;


public class RailImporterWizardPage extends WizardNewFileCreationPage {
	
	protected DirectoryFieldEditor editor;
	protected TopologyCalculator tc;
	protected String BraveInputFolder = "";

	public RailImporterWizardPage(String pageName, IStructuredSelection selection) {
		super(pageName, selection);
		setTitle(pageName); //NON-NLS-1
		setDescription("Existing Brave model"); //NON-NLS-1
		tc = new TopologyCalculator();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#createAdvancedControls(org.eclipse.swt.widgets.Composite)
	 */	
	protected void createAdvancedControls(Composite parent) {
		Composite fileSelectionArea = new Composite(parent, SWT.NONE);
		GridData fileSelectionData = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.FILL_HORIZONTAL);
		fileSelectionArea.setLayoutData(fileSelectionData);

		GridLayout fileSelectionLayout = new GridLayout();
		fileSelectionLayout.numColumns = 3;
		fileSelectionLayout.makeColumnsEqualWidth = false;
		fileSelectionLayout.marginWidth = 0;
		fileSelectionLayout.marginHeight = 0;
		fileSelectionArea.setLayout(fileSelectionLayout);
		
		editor = new DirectoryFieldEditor("folderSelect","Select Brave's Data Folder: ",fileSelectionArea); //NON-NLS-1 //NON-NLS-2
		editor.getTextControl(fileSelectionArea).addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent e) {
				BraveInputFolder = RailImporterWizardPage.this.editor.getStringValue();
				IPath path = new Path(BraveInputFolder);
				setFileName(path.lastSegment() + ".ontrack");
			}
		});
//		String[] extensions = new String[] { "*.*" }; //NON-NLS-1
//		editor.setFileExtensions(extensions);
		fileSelectionArea.moveAbove(null);

	}
	
	 /* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#createLinkTarget()
	 */
	protected void createLinkTarget() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#getInitialContents()
	 */
	protected InputStream getInitialContents() {
//		try {
//			return new FileInputStream(new File(editor.getStringValue()));
//		} catch (FileNotFoundException e) {
			return null;
//		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#getNewFileLabel()
	 */
	protected String getNewFileLabel() {
		return "New File Name:"; //NON-NLS-1
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#validateLinkedResource()
	 */
	protected IStatus validateLinkedResource() {
		if (!tc.isInputFolderValid(BraveInputFolder)) 
			return new Status(IStatus.ERROR, "OnTrack.dsl.diagram", IStatus.ERROR, "Input folder does not contain Brave's data", null);
		if (isTargetExist())
			return new Status(IStatus.ERROR, "OnTrack.dsl.diagram", IStatus.ERROR, "Output file exists", null);
		
		return	new Status(IStatus.OK, "OnTrack.dsl.diagram", IStatus.OK, "", null);
	}
	
	protected boolean isTargetExist(){
		File f = new File(getContainerFullPath().toOSString() + File.separator + getFileName());
		return f.exists();
	}
	
	public File doImport(IFile ifile){
		return tc.importBraveData(BraveInputFolder, ifile.getLocation().toOSString());
	}
}
