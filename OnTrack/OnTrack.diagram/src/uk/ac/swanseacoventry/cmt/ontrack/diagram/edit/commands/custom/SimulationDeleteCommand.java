package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom;

import java.util.ArrayList;

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
import uk.ac.swanseacoventry.cmt.ontrack.Simulation;
import uk.ac.swanseacoventry.cmt.ontrack.SimulationAction;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;

public class SimulationDeleteCommand extends AbstractTransactionalCommand {
	
	private DiagramEditPart trackplanEP;
	private TrackPlan trackplan;
	private Simulation sim;
	
	public SimulationDeleteCommand(IGraphicalEditPart ep, Simulation sim) {
		super(ep.getEditingDomain(),"add-simulation",null);
		trackplanEP = (DiagramEditPart)ep;
		trackplan = (TrackPlan)((View)trackplanEP.getModel()).getElement();
		this.sim = sim;
	}
	
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		
		trackplan.getSimulations().remove(sim);

		return CommandResult.newOKCommandResult();
		
	}
	
}
