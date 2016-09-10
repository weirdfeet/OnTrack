package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom;

import java.util.HashMap;

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
import uk.ac.swanseacoventry.cmt.ontrack.TopoRoute;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;

public class ControlTableInitCommand extends AbstractTransactionalCommand {
	
	private DiagramEditPart trackplanEP;
	private TrackPlan trackplan;
	
	public ControlTableInitCommand(IGraphicalEditPart ep) {
		super(ep.getEditingDomain(),"initialise-control-table",null);
		trackplanEP = (DiagramEditPart)ep;
		trackplan = (TrackPlan)((View)trackplanEP.getModel()).getElement();
	}
	
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		
		HashMap<String,ControlTableItem> ctrlTable = new HashMap<String,ControlTableItem>();
		for(ControlTableItem cti : trackplan.getControlTable())
			ctrlTable.put(cti.getRoute(), cti);
		
		HashMap<String,TopoRoute> topoRoutes = new HashMap<String,TopoRoute>();
		for(TopoRoute tr : trackplan.getTopoRoutes())
			for(String n : tr.getNames())
				if (!n.trim().equals(""))
					topoRoutes.put(n, tr);
			
		for(String r : topoRoutes.keySet()){
			if (!ctrlTable.containsKey(r)){
				ControlTableItem cti = OntrackFactory.eINSTANCE.createControlTableItem();
				cti.setRoute(r);
				cti.setSignal(topoRoutes.get(r).getStartSignal());
				trackplan.getControlTable().add(cti);
			}
		}
		
		return CommandResult.newOKCommandResult();
		
	}
	
}
