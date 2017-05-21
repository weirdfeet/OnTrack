package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom;

import java.util.Collections;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;

import uk.ac.swanseacoventry.cmt.ontrack.Connector;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.ConnectorEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TrackEditPart;

public class DeferredUpdateTrackSourceAndTargetCommand extends AbstractTransactionalCommand {
	
	TrackEditPart trackEP;
	IAdaptable srcAdapter;
	IAdaptable tagAdapter;
	EditPartViewer viewer;
	
	EditPart oldSource;
	EditPart oldTarget;
	Connector oldSrc;
	Connector oldTag;

	public DeferredUpdateTrackSourceAndTargetCommand(TrackEditPart track, IAdaptable srcConnector, IAdaptable tagConnector, EditPartViewer viewer){
		super(track.getEditingDomain(), "Update connectors of  a track EP", null);
		trackEP = track;
		srcAdapter = srcConnector;
		tagAdapter = tagConnector;
		this.viewer = viewer;
	}

	@Override
	public boolean canExecute() {
		// TODO Auto-generated method stub
		return trackEP!=null && super.canExecute();
	}
	
	@Override
	protected IStatus doUndo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		Track trk = (Track)((View)trackEP.getModel()).getElement();
		if (trk!=null) {
			trk.setC1(oldSrc);
			trk.setC2(oldTag);
		}
		return super.doUndo(monitor, info);
	}
	
	protected GraphicalEditPart getSourceEditPart() {
		return (GraphicalEditPart) viewer.getEditPartRegistry().get(
				srcAdapter.getAdapter(View.class));
	}

	protected GraphicalEditPart getTargetEditPart() {
		return (GraphicalEditPart) viewer.getEditPartRegistry().get(
				tagAdapter.getAdapter(View.class));
	}

	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		if (trackEP==null) return null;
		oldSource = trackEP.getSource();
		oldTarget = trackEP.getTarget();
		
		if (oldSource==null || oldTarget==null) return null;
		
		oldSrc = (Connector)((View)oldSource.getModel()).getElement();
		oldTag = (Connector)((View)oldTarget.getModel()).getElement();
		
		Track trk = (Track)((View)trackEP.getModel()).getElement();

		GraphicalEditPart newSource = getSourceEditPart();
		GraphicalEditPart newTarget = getTargetEditPart();
		
		if (newSource==null || newTarget==null) return null;
		
		Connector src = (Connector)((View)newSource.getModel()).getElement();
		Connector tag = (Connector)((View)newTarget.getModel()).getElement();
		
		trk.setC1(src);
		trk.setC2(tag);
		
		trackEP.getParent().addNotify();
		
		return CommandResult.newOKCommandResult();
	}

}
