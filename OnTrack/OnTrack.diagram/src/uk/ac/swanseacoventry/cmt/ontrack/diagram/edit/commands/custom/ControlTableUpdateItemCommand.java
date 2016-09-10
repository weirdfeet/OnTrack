package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;

import uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem;
import uk.ac.swanseacoventry.cmt.ontrack.DirectedTrack;
import uk.ac.swanseacoventry.cmt.ontrack.Point;
import uk.ac.swanseacoventry.cmt.ontrack.Signal;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;

public class ControlTableUpdateItemCommand extends AbstractTransactionalCommand {
	
	private DiagramEditPart trackplanEP;
	private TrackPlan trackplan;
	private ControlTableItem udItem;
	private Signal udSignal;
	private EList<Point> udNormals;
	private EList<Point> udReverses;
	private EList<Track> udClears;
	private EList<DirectedTrack> udDirections;
	private String udName;
	
	
	public ControlTableUpdateItemCommand(IGraphicalEditPart ep, ControlTableItem cti, String name, Signal s, EList<Point> ns, EList<Point> rs, EList<Track> cs, EList<DirectedTrack> ds) {
		super(ep.getEditingDomain(),"initialise-control-table",null);
		trackplanEP = (DiagramEditPart)ep;
		trackplan = (TrackPlan)((View)trackplanEP.getModel()).getElement();
		udSignal = s;
		udNormals = ns;
		udReverses = rs;
		udClears = cs;
		udItem = cti;
		udName = name;
		udDirections = ds;
	}
	
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		
		if (udName!=null && !udName.trim().equals(""))
			udItem.setRoute(udName);
		
		if (udSignal!=null) 
			udItem.setSignal(udSignal);
		
		if (udNormals!=null){ 
			udItem.getNormals().clear();
			udItem.getNormals().addAll(udNormals);
		}
		
		if (udReverses!=null){ 
			udItem.getReverses().clear();
			udItem.getReverses().addAll(udReverses);
		}
		
		if (udClears!=null){ 
			udItem.getClears().clear();
			udItem.getClears().addAll(udClears);
		}
		
		if (udDirections!=null){ 
			udItem.getDirections().clear();
			udItem.getDirections().addAll(udDirections);
		}
				
		return CommandResult.newOKCommandResult();
		
	}
	
}
