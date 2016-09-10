package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;

import uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem;
import uk.ac.swanseacoventry.cmt.ontrack.OntrackFactory;
import uk.ac.swanseacoventry.cmt.ontrack.Point;
import uk.ac.swanseacoventry.cmt.ontrack.ReleaseTableItem;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;

public class ReleaseTableInitCommand extends AbstractTransactionalCommand {
	
	private DiagramEditPart trackplanEP;
	private TrackPlan trackplan;
	
	public ReleaseTableInitCommand(IGraphicalEditPart ep) {
		super(ep.getEditingDomain(),"initialise-control-table",null);
		trackplanEP = (DiagramEditPart)ep;
		trackplan = (TrackPlan)((View)trackplanEP.getModel()).getElement();
	}
	
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		trackplan.getReleaseTable().clear();
		
		for(ControlTableItem cti : trackplan.getControlTable()){
			for(Point p : cti.getNormals()){
				ReleaseTableItem rti = OntrackFactory.eINSTANCE.createReleaseTableItem();
				rti.setRoute(cti.getRoute());
				rti.setPoint(p);
				trackplan.getReleaseTable().add(rti);
			}
			for(Point p : cti.getReverses()){
				ReleaseTableItem rti = OntrackFactory.eINSTANCE.createReleaseTableItem();
				rti.setRoute(cti.getRoute());
				rti.setPoint(p);
				trackplan.getReleaseTable().add(rti);
			}			
		}
		
		return CommandResult.newOKCommandResult();
		
	}
	
}
