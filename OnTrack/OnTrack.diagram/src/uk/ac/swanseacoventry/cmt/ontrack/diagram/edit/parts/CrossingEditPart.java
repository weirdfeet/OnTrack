package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts;

import java.util.List;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import uk.ac.swanseacoventry.cmt.ontrack.Crossing;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TrackEditPart.TrackFigure;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.policies.CrossingItemSemanticEditPolicy;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.policies.custom.PointConnectionEditPolicy;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackDiagramEditor;

/**
 * @generated
 */
public class CrossingEditPart extends ConnectionNodeEditPart implements ITreeBranchEditPart {

	/**
	* @generated
	*/
	public static final int VISUAL_ID = 4002;

	/**
	* @generated
	*/
	public CrossingEditPart(View view) {
		super(view);
	}

	/**
	* @generated NOT
	*/
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new CrossingItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_ROLE, new PointConnectionEditPolicy());
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
		return new CrossingFigure();
	}

	/**
	* @generated
	*/
	public CrossingFigure getPrimaryShape() {
		return (CrossingFigure) getFigure();
	}

	/**
	 * @generated
	 */
	public class CrossingFigure extends PolylineConnectionEx {

		/**
		 * @generated
		 */
		public CrossingFigure() {
			this.setLineStyle(Graphics.LINE_DOT);

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
			Crossing c = (Crossing) view.getElement();
			Track straightTrack;
			straightTrack = c.getTrack1();
			Point midPoint = null;
			PointList pts = super.getPoints();

			if (c != null && straightTrack != null && pts.size() >= 2) {
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

				if (midPoint != null) {
					PointList newPts = new PointList();
					newPts.addPoint(midPoint);
					newPts.addPoint(midPoint);
					return newPts;
				}
			}

			return pts;

		}

		@Override
		public void paint(Graphics graphics) {
			getPoints();
			super.paint(graphics);
		}

	}

}
