package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.policies.custom;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
//import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TrackEditPart;

public class TrackConnectionEditPolicy extends ConnectionEditPolicy {

	@Override
	protected Command getDeleteCommand(GroupRequest request) {
		// TODO Auto-generated method stub
		TrackEditPart trackEP = (TrackEditPart) (IGraphicalEditPart) getHost();
		Set<IGraphicalEditPart> deleteDomains = new HashSet<IGraphicalEditPart>();
		deleteDomains.add(trackEP);
		if (trackEP.getTargetConnections().size() > 0)
		{
			for(Object ep : trackEP.getTargetConnections()){
				deleteDomains.add((IGraphicalEditPart)ep);
			}
		}
		if (trackEP.getSourceConnections().size() > 0)
		{
			for(Object ep : trackEP.getSourceConnections()){
				deleteDomains.add((IGraphicalEditPart)ep);
			}
		}
	        
		CompoundCommand cc = new CompoundCommand();
		for(IGraphicalEditPart ep : deleteDomains){
			EditCommandRequestWrapper semReq = null;
			semReq = new EditCommandRequestWrapper(new DestroyElementRequest(ep.getEditingDomain(), false), request.getExtendedData());
			Command semanticCmd = ep.getCommand(semReq);
			if (semanticCmd != null && semanticCmd.canExecute()) {
				cc.add(semanticCmd);
			}
		}
		return cc;
	}

//	@Override
//	protected Command getDeleteCommand(GroupRequest request) {
//		// TODO Auto-generated method stub
//		IGraphicalEditPart pointEP = (IGraphicalEditPart) getHost();
//		IGraphicalEditPart trackplanEP = (IGraphicalEditPart) pointEP.getParent().getChildren().get(0);
//		Point p = (Point) ((View)pointEP.getModel()).getElement();
//		TrackPlan pl = (TrackPlan) ((View)trackplanEP.getModel()).getElement();
//		pointEP.getParent().getChildren().remove(pointEP);
//		Command cmd = new PointDeleteCommand(pl, p);
//		trackplanEP.getFigure().invalidate();
//		return cmd;
//	}

}
