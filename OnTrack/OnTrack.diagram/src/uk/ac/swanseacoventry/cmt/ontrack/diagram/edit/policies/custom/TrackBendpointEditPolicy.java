package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.policies.custom;

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.Connection;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.DeferredCreateConnectionViewAndElementCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ConnectionBendpointEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequestFactory;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.DeferredUpdateTrackSourceAndTargetCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TrackEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.providers.OntrackElementTypes;

public class TrackBendpointEditPolicy extends ConnectionBendpointEditPolicy {

	
	// when create a bendpoint, we replace it by a connector
	@Override
	protected Command getBendpointsChangedCommand(Connection connection, Edge edge) {
		TrackEditPart trkEP = (TrackEditPart)getHost();
		Track trk = (Track)((View)trkEP.getModel()).getElement();
		if (trk.getPoint()!=null || trk.getCrossing()!=null){
			if (connection.getPoints().size() > 2) {
				// disallow middle point addition
				return null;
			}
			return super.getBendpointsChangedCommand(connection, edge);
		}
		
		// only do this when a new mid benp point is created
		if (connection.getPoints().size()==3) {
			//System.out.println(connection.getPoints().getPoint(1).toString());
			
			IGraphicalEditPart trackplanEP = (IGraphicalEditPart) getHost().getParent().getChildren().get(0);
			//TrackPlan trackplan = (TrackPlan) ((View) trackplanEP.getModel()).getElement();
			
			CompoundCommand cc = new CompoundCommand();
			
			// create command to create a new connector
			CreateViewRequest conReq = CreateViewRequestFactory.getCreateShapeRequest(OntrackElementTypes.Connector_2001, trackplanEP.getDiagramPreferencesHint());
			conReq.setLocation(connection.getPoints().getPoint(1).getCopy());
			IAdaptable con = (IAdaptable) ((List) conReq.getNewObject()).get(0); // take note of the new connector
			Command conCmd = trackplanEP.getCommand(conReq);
			cc.add(conCmd);
			
			// create command to create a new track to connect source -> new connector
			DeferredUpdateTrackSourceAndTargetCommand t1Cmd = new DeferredUpdateTrackSourceAndTargetCommand(
					(TrackEditPart)this.getHost(),
					new EObjectAdapter(edge.getSource()),
					con,
					trackplanEP.getViewer());
			cc.add(new ICommandProxy(t1Cmd));
			
			// create command to create a new track to connect new connector -> target
			CreateConnectionViewAndElementRequest t1Req = new CreateConnectionViewAndElementRequest(
					OntrackElementTypes.Track_4005, 
					((IHintedType) OntrackElementTypes.Track_4005).getSemanticHint(), 
					trackplanEP.getDiagramPreferencesHint());
			ICommand t2Cmd = new DeferredCreateConnectionViewAndElementCommand(
					t1Req, 
					con,
					new EObjectAdapter(edge.getTarget()),
					trackplanEP.getViewer());
			cc.add(new ICommandProxy(t2Cmd));
			
			// finally we remove the old edge
//			GroupRequest deleteReq = new GroupRequest(RequestConstants.REQ_DELETE);
//			deleteReq.setEditParts(trackplanEP);
//			Command deleteCmd = getHost().getCommand(deleteReq);
//			
//			cc.add(deleteCmd);
			
			return cc;
		}
		// return super.getBendpointsChangedCommand(connection, edge);
		return null;
	}
	
	protected EditPart getEditPartFromAdaptable(EditPartViewer viewer, IAdaptable adapter) {
		return (IGraphicalEditPart) viewer.getEditPartRegistry().get(
				adapter.getAdapter(View.class));
	}

	
}
