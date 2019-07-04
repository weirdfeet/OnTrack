package uk.ac.swanseacoventry.cmt.ontrack.diagram.automaticLayout;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class GraphDrawingHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
		    public void run() {
		        Shell activeShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				TrackLayoutConfigurationDialog d = new TrackLayoutConfigurationDialog(activeShell);
				d.open();
				System.out.println("Yey :-)");
		    }
		});
		return null;
	}

}
