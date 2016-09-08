package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.policies.custom;

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.DeferredCreateConnectionViewAndElementCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest.ViewAndElementDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequestFactory;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.View;

import uk.ac.swanseacoventry.cmt.ontrack.diagram.providers.OntrackElementTypes;

public class TrackPlanCustomCreationEditPolicy extends CreationEditPolicy {

	@Override
	protected Command getCreateElementAndViewCommand(CreateViewAndElementRequest request) {

		ViewAndElementDescriptor descriptor = request.getViewAndElementDescriptor();
		Point reqLoc = request.getLocation();
		IHintedType newTrackHintedType = (IHintedType) OntrackElementTypes.NewTrack_2006;
		IHintedType newPointHintedType = (IHintedType) OntrackElementTypes.NewPoint_2007;
		IHintedType newCrossingHintedType = (IHintedType) OntrackElementTypes.NewCrossing_2008;
		
		if (descriptor.getSemanticHint()==newTrackHintedType.getSemanticHint()){
			CompoundCommand cc = new CompoundCommand("Create a new track");
			
			IGraphicalEditPart trackplanEP = (IGraphicalEditPart) getHost();
			EObject trackplan = ((View) trackplanEP.getModel()).getElement();
			
			// create connector 1
			CreateViewRequest c1Request = CreateViewRequestFactory.getCreateShapeRequest(OntrackElementTypes.Connector_2001, trackplanEP.getDiagramPreferencesHint());
			c1Request.setLocation(reqLoc.getCopy());
			Command c1Cmd = trackplanEP.getCommand(c1Request);
			IAdaptable c1Adapter = (IAdaptable) ((List) c1Request.getNewObject()).get(0);
			cc.add(c1Cmd);
			
			// create connector 2
			CreateViewRequest c2Request = CreateViewRequestFactory.getCreateShapeRequest(OntrackElementTypes.Connector_2001, trackplanEP.getDiagramPreferencesHint());
			c2Request.setLocation(reqLoc.getCopy().translate(80, 0));
			Command c2Cmd = trackplanEP.getCommand(c2Request);
			IAdaptable c2Adapter = (IAdaptable) ((List) c2Request.getNewObject()).get(0);
			cc.add(c2Cmd);
			
			
			
			// create track linking c1 and c2
			CreateConnectionViewAndElementRequest trackReq = 
					new CreateConnectionViewAndElementRequest(
							OntrackElementTypes.Track_4005,
							((IHintedType) OntrackElementTypes.Track_4005).getSemanticHint(), 
							trackplanEP.getDiagramPreferencesHint());
			ICommand trackCmd = new DeferredCreateConnectionViewAndElementCommand(
					trackReq, 
					c1Adapter,
					c2Adapter,
					trackplanEP.getViewer());
	 
			cc.add(new ICommandProxy(trackCmd));
			
			return cc;
		} 
		if (descriptor.getSemanticHint()==newPointHintedType.getSemanticHint()){
			// System.out.println("DEBUG: " + descriptor.getSemanticHint());
			CompoundCommand cc = new CompoundCommand("Create a new point");
			
			IGraphicalEditPart trackplanEP = (IGraphicalEditPart) getHost();
			EObject trackplan = ((View) trackplanEP.getModel()).getElement();
			
			// create connector 1
			CreateViewRequest c1Request = CreateViewRequestFactory.getCreateShapeRequest(OntrackElementTypes.Connector_2001, trackplanEP.getDiagramPreferencesHint());
			c1Request.setLocation(reqLoc.getCopy());
			Command c1Cmd = trackplanEP.getCommand(c1Request);
			IAdaptable c1Adapter = (IAdaptable) ((List) c1Request.getNewObject()).get(0);
			cc.add(c1Cmd);
			
			// create connector 2
			CreateViewRequest c2Request = CreateViewRequestFactory.getCreateShapeRequest(OntrackElementTypes.Connector_2001, trackplanEP.getDiagramPreferencesHint());
			c2Request.setLocation(reqLoc.getCopy().translate(80, 0));
			Command c2Cmd = trackplanEP.getCommand(c2Request);
			IAdaptable c2Adapter = (IAdaptable) ((List) c2Request.getNewObject()).get(0);
			cc.add(c2Cmd);
			
			// create connector 3
			CreateViewRequest c3Request = CreateViewRequestFactory.getCreateShapeRequest(OntrackElementTypes.Connector_2001, trackplanEP.getDiagramPreferencesHint());
			c3Request.setLocation(reqLoc.getCopy().translate(80, -40));
			Command c3Cmd = trackplanEP.getCommand(c3Request);
			IAdaptable c3Adapter = (IAdaptable) ((List) c3Request.getNewObject()).get(0);
			cc.add(c3Cmd);
			
			
			// create track linking c1 and c2
			CreateConnectionViewAndElementRequest track1Req = 
					new CreateConnectionViewAndElementRequest(
							OntrackElementTypes.Track_4005,
							((IHintedType) OntrackElementTypes.Track_4005).getSemanticHint(), 
							trackplanEP.getDiagramPreferencesHint());
			IAdaptable track1Adapter = (IAdaptable) track1Req.getNewObject();
			ICommand track1Cmd = new DeferredCreateConnectionViewAndElementCommand(
					track1Req, 
					c1Adapter,
					c2Adapter,
					trackplanEP.getViewer());
	 
			cc.add(new ICommandProxy(track1Cmd));
			
			// create track linking c1 and c3
			CreateConnectionViewAndElementRequest track2Req = 
					new CreateConnectionViewAndElementRequest(
							OntrackElementTypes.Track_4005,
							((IHintedType) OntrackElementTypes.Track_4005).getSemanticHint(), 
							trackplanEP.getDiagramPreferencesHint());
			IAdaptable track2Adapter = (IAdaptable) track2Req.getNewObject();
			ICommand track2Cmd = new DeferredCreateConnectionViewAndElementCommand(
					track2Req, 
					c1Adapter,
					c3Adapter,
					trackplanEP.getViewer());
	 
			cc.add(new ICommandProxy(track2Cmd));
			
			// create point linking t1 and t2
			CreateConnectionViewAndElementRequest pointReq = 
					new CreateConnectionViewAndElementRequest(
							OntrackElementTypes.Point_4001,
							((IHintedType) OntrackElementTypes.Point_4001).getSemanticHint(), 
							trackplanEP.getDiagramPreferencesHint());
			ICommand pointCmd = new DeferredCreateConnectionViewAndElementCommand(
					pointReq, 
					track2Adapter,
					track1Adapter,
					trackplanEP.getViewer());
	 
			cc.add(new ICommandProxy(pointCmd));

			return cc;
		}
		if (descriptor.getSemanticHint()==newCrossingHintedType.getSemanticHint()){
			// System.out.println("DEBUG: " + descriptor.getSemanticHint());
			CompoundCommand cc = new CompoundCommand("Create a new crossing");
			
			IGraphicalEditPart trackplanEP = (IGraphicalEditPart) getHost();
			EObject trackplan = ((View) trackplanEP.getModel()).getElement();
			
			// create connector 1
			CreateViewRequest c1Request = CreateViewRequestFactory.getCreateShapeRequest(OntrackElementTypes.Connector_2001, trackplanEP.getDiagramPreferencesHint());
			c1Request.setLocation(reqLoc.getCopy());
			Command c1Cmd = trackplanEP.getCommand(c1Request);
			IAdaptable c1Adapter = (IAdaptable) ((List) c1Request.getNewObject()).get(0);
			cc.add(c1Cmd);
			
			// create connector 2
			CreateViewRequest c2Request = CreateViewRequestFactory.getCreateShapeRequest(OntrackElementTypes.Connector_2001, trackplanEP.getDiagramPreferencesHint());
			c2Request.setLocation(reqLoc.getCopy().translate(80, 0));
			Command c2Cmd = trackplanEP.getCommand(c2Request);
			IAdaptable c2Adapter = (IAdaptable) ((List) c2Request.getNewObject()).get(0);
			cc.add(c2Cmd);
			
			// create connector 3
			CreateViewRequest c3Request = CreateViewRequestFactory.getCreateShapeRequest(OntrackElementTypes.Connector_2001, trackplanEP.getDiagramPreferencesHint());
			c3Request.setLocation(reqLoc.getCopy().translate(80, -40));
			Command c3Cmd = trackplanEP.getCommand(c3Request);
			IAdaptable c3Adapter = (IAdaptable) ((List) c3Request.getNewObject()).get(0);
			cc.add(c3Cmd);
			
			// create connector 4
			CreateViewRequest c4Request = CreateViewRequestFactory.getCreateShapeRequest(OntrackElementTypes.Connector_2001, trackplanEP.getDiagramPreferencesHint());
			c4Request.setLocation(reqLoc.getCopy().translate(0, 40));
			Command c4Cmd = trackplanEP.getCommand(c4Request);
			IAdaptable c4Adapter = (IAdaptable) ((List) c4Request.getNewObject()).get(0);
			cc.add(c4Cmd);

			
			// create track linking c1 and c2
			CreateConnectionViewAndElementRequest track1Req = 
					new CreateConnectionViewAndElementRequest(
							OntrackElementTypes.Track_4005,
							((IHintedType) OntrackElementTypes.Track_4005).getSemanticHint(), 
							trackplanEP.getDiagramPreferencesHint());
			IAdaptable track1Adapter = (IAdaptable) track1Req.getNewObject();
			ICommand track1Cmd = new DeferredCreateConnectionViewAndElementCommand(
					track1Req, 
					c1Adapter,
					c2Adapter,
					trackplanEP.getViewer());
	 
			cc.add(new ICommandProxy(track1Cmd));
			
			// create track linking c3 and c4
			CreateConnectionViewAndElementRequest track2Req = 
					new CreateConnectionViewAndElementRequest(
							OntrackElementTypes.Track_4005,
							((IHintedType) OntrackElementTypes.Track_4005).getSemanticHint(), 
							trackplanEP.getDiagramPreferencesHint());
			IAdaptable track2Adapter = (IAdaptable) track2Req.getNewObject();
			ICommand track2Cmd = new DeferredCreateConnectionViewAndElementCommand(
					track2Req, 
					c3Adapter,
					c4Adapter,
					trackplanEP.getViewer());
	 
			cc.add(new ICommandProxy(track2Cmd));
			
			// create crossing by linking t1 and t2
			CreateConnectionViewAndElementRequest crossReq = 
					new CreateConnectionViewAndElementRequest(
							OntrackElementTypes.Crossing_4002,
							((IHintedType) OntrackElementTypes.Crossing_4002).getSemanticHint(), 
							trackplanEP.getDiagramPreferencesHint());
			ICommand crossCmd = new DeferredCreateConnectionViewAndElementCommand(
					crossReq, 
					track1Adapter,
					track2Adapter,
					trackplanEP.getViewer());
	 
			cc.add(new ICommandProxy(crossCmd));

			return cc;
		}
			
		
		return super.getCreateElementAndViewCommand(request);	
	}	
}
