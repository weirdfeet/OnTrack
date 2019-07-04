package uk.ac.swanseacoventry.cmt.ontrack.diagram.automaticLayout;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;

public class TrackUpdateLocationCommand extends AbstractTransactionalCommand {
	
	private IGraphicalEditPart connector;
	private int x, y;
	private boolean translated;
	
	public TrackUpdateLocationCommand(IGraphicalEditPart ep, int ix, int iy, boolean itranslated) {
		super(ep.getEditingDomain(),"update-location",null);
		connector = ep;
		x = ix;
		y = iy;
		translated = itranslated;
	}
	
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		Location lc = (Location) ((Node)connector.getModel()).getLayoutConstraint();
		if (translated) {
			System.out.println("New location: " + (lc.getX() + x) + ":" + (lc.getY() + y));
			lc.setX(lc.getX() + x);
			lc.setY(lc.getY() + y);
		}
		else {
			System.out.println("New location: " + x + ":" + y);
			lc.setX(x);
			lc.setY(y);
		}
		return CommandResult.newOKCommandResult();
		
	}
	
}
