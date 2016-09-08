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

import uk.ac.swanseacoventry.cmt.ontrack.DirectedTrack;
import uk.ac.swanseacoventry.cmt.ontrack.ReleaseTableItem;
import uk.ac.swanseacoventry.cmt.ontrack.TopoRoute;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;

public class ReleaseTableAutoFillCommand extends AbstractTransactionalCommand {
	
	private DiagramEditPart trackplanEP;
	private TrackPlan trackplan;
	
	public ReleaseTableAutoFillCommand(IGraphicalEditPart ep) {
		super(ep.getEditingDomain(),"auto-fill-release-table",null);
		trackplanEP = (DiagramEditPart)ep;
		trackplan = (TrackPlan)((View)trackplanEP.getModel()).getElement();
	}
	
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		
		HashMap<String,TopoRoute> topoRoutes = new HashMap<String,TopoRoute>();
		for(TopoRoute tr : trackplan.getTopoRoutes())
			for(String n : tr.getNames())
				if (!n.trim().equals(""))
					topoRoutes.put(n, tr);

		for(ReleaseTableItem rti : trackplan.getReleaseTable()){
			if (topoRoutes.containsKey(rti.getRoute())){
				for(DirectedTrack dt : topoRoutes.get(rti.getRoute()).getDirectedTracks()){
					if (dt.getTrack()==rti.getPoint().getNormalTrack() ||
							dt.getTrack()==rti.getPoint().getReverseTrack())
						rti.setTrack(dt.getTrack());
				}
			}
		}
		
		return CommandResult.newOKCommandResult();
		
	}
	
}
