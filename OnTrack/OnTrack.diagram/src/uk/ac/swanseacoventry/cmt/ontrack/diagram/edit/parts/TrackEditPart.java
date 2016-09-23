package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts;

import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.policies.TrackItemSemanticEditPolicy;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.policies.custom.TrackConnectionEditPolicy;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackDiagramEditor;

/**
 * @generated
 */
public class TrackEditPart extends ConnectionNodeEditPart implements ITreeBranchEditPart {

	/**
	* @generated
	*/
	public static final int VISUAL_ID = 4005;

	/**
	* @generated
	*/
	public TrackEditPart(View view) {
		super(view);
	}

	/**
	* @generated NOT
	*/
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new TrackItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_ROLE, new TrackConnectionEditPolicy());
	}

	/**
	* @generated
	*/
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof TrackNameEditPart) {
			((TrackNameEditPart) childEditPart).setLabel(getPrimaryShape().getFigureTrackNameFigure());
			return true;
		}
		return false;
	}

	/**
	* @generated
	*/
	protected void addChildVisual(EditPart childEditPart, int index) {
		if (addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, index);
	}

	/**
	* @generated
	*/
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof TrackNameEditPart) {
			return true;
		}
		return false;
	}

	/**
	* @generated
	*/
	protected void removeChildVisual(EditPart childEditPart) {
		if (removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
	}

	/**
	* Creates figure for this edit part.
	* 
	* Body of this method does not depend on settings in generation model
	* so you may safely remove <i>generated</i> tag and modify it.
	* 
	* @generated
	*/

	protected Connection createConnectionFigure() {
		return new TrackFigure();
	}

	/**
	* @generated
	*/
	public TrackFigure getPrimaryShape() {
		return (TrackFigure) getFigure();
	}

	private Color fColor;
	private final Color hColor = ColorConstants.green;

	public void highLight() {
		if (fColor == null)
			fColor = getFigure().getForegroundColor();
		getFigure().setForegroundColor(hColor);
		getFigure().invalidate();

	}

	public void unhighLight() {
		if (fColor != null) {
			getFigure().setForegroundColor(fColor);
			getFigure().invalidate();
		}
	}

	/**
	 * @generated
	 */
	public class TrackFigure extends PolylineConnectionEx {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureTrackNameFigure;

		/**
		 * @generated
		 */
		public TrackFigure() {
			this.setLineWidth(3);

			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			fFigureTrackNameFigure = new WrappingLabel();

			fFigureTrackNameFigure.setText("T");

			this.add(fFigureTrackNameFigure);

		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureTrackNameFigure() {
			return fFigureTrackNameFigure;
		}

		IEditorPart getEditorPart() {
			IWorkbench wb = PlatformUI.getWorkbench();
			IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
			IWorkbenchPage page = win.getActivePage();

			if (page == null)
				return null;

			IEditorPart editor = page.getActiveEditor();
			return editor;
		}

		/**
		 * @generated NOT
		 */
		@SuppressWarnings("rawtypes")
		public PointList getPoints() {
			View view = (View) getModel();
			Track track = (Track) view.getElement();
			uk.ac.swanseacoventry.cmt.ontrack.Point p = track != null ? track.getPoint() : null;
			uk.ac.swanseacoventry.cmt.ontrack.Crossing crossing = track != null ? track.getCrossing() : null;
			Point midPoint = null;

			if (p != null || crossing != null) {
				Track straightTrack;
				if (p != null)
					straightTrack = p.getNormalTrack();
				else
					straightTrack = crossing.getTrack1();

				IEditorPart editorPart = getEditorPart();

				if (editorPart instanceof OntrackDiagramEditor) {
					List editParts = ((OntrackDiagramEditor) editorPart).getDiagramGraphicalViewer()
							.findEditPartsForElement(EMFCoreUtil.getProxyID(straightTrack), TrackEditPart.class);
					if (editParts.size() >= 1) {
						TrackEditPart straightTrackEP = (TrackEditPart) editParts.get(0);
						TrackFigure trackFig = (TrackFigure) straightTrackEP.getFigure();
						Point s = trackFig.getStart();
						Point e = trackFig.getEnd();
						midPoint = new Point((s.x + e.x) / 2, (s.y + e.y) / 2);
					}
				}

			}

			PointList pts = super.getPoints();
			if (pts.size() >= 2) {
				Point start = super.getStart();
				Point end = super.getEnd();
				PointList newPts = new PointList();
				newPts.addPoint(start);
				if (midPoint != null)
					newPts.addPoint(midPoint);
				newPts.addPoint(end);
				pts = newPts;
			}
			return pts;
		}

		@Override
		public void paint(Graphics graphics) {
			getPoints();
			View view = (View) getModel();
			Track track = (Track) view.getElement();
			fFigureTrackNameFigure.setVisible(track.getPointReverse() == null && track.getCrossing2() == null);
			super.paint(graphics);
		}

	}

}
