package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;

import uk.ac.swanseacoventry.cmt.ontrack.Point;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;

public class PointDeleteCommand extends Command  {
	
	private DiagramEditPart trackplanEP;
	private TrackPlan trackplan;
	private Point point;
	private Track reverse;
	private Track normal;
	
	public PointDeleteCommand(TrackPlan pl, Point p) {
		// TODO Auto-generated constructor stub
		trackplan = pl;
		point = p;
	}
	
	@Override public boolean canExecute() {
        return point != null;
    }
	

	@Override public void execute() {
		reverse = point.getReverseTrack();
		reverse.setPointReverse(null);
		normal = point.getNormalTrack();
		normal.setPointNormal(null);
		point.setNormalTrack(null);
		point.setReverseTrack(null);
		trackplan.getPoints().remove(point);
	}
	
	@Override public void undo() {
		normal.setPointNormal(point);
		reverse.setPointReverse(point);
		point.setNormalTrack(normal);
		point.setReverseTrack(reverse);
		trackplan.getPoints().add(point);
	}
	
}
