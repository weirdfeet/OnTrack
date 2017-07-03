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

public class SimulationAddCommand extends AbstractTransactionalCommand {
	
	private DiagramEditPart trackplanEP;
	private TrackPlan trackplan;
	private String name;
	private ArrayList<String[]> trace;
	
	public SimulationAddCommand(IGraphicalEditPart ep, String n, ArrayList<String[]> trace) {
		super(ep.getEditingDomain(),"add-simulation",null);
		trackplanEP = (DiagramEditPart)ep;
		trackplan = (TrackPlan)((View)trackplanEP.getModel()).getElement();
		name = n;
		this.trace = trace;
	}
	
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		
		if (name!=null && !name.trim().equals("")){
			Simulation sim = OntrackFactory.eINSTANCE.createSimulation();
			sim.setName(name);
			for(String[] act : trace){
				SimulationAction simAct = OntrackFactory.eINSTANCE.createSimulationAction();
				simAct.setName(act[0]);
				for(int i=1; i<act.length; i++)
					simAct.getParameters().add(act[i]);
				sim.getSteps().add(simAct);
			}
			trackplan.getSimulations().add(sim);
		}
		
		
		return CommandResult.newOKCommandResult();
		
	}
	
}
