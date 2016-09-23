package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts;

import java.util.List;

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
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TrackEditPart.TrackFigure;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.policies.PointItemSemanticEditPolicy;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.policies.custom.PointConnectionEditPolicy;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackDiagramEditor;

/**
 * @generated
 */
public class PointEditPart extends ConnectionNodeEditPart implements ITreeBranchEditPart {

	/**
	* @generated
	*/
	public static final int VISUAL_ID = 4001;

	/**
	* @generated
	*/
	public PointEditPart(View view) {
		super(view);
	}

	/**
	* @generated NOT
	*/
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new PointItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_ROLE, new PointConnectionEditPolicy());
	}

	/**
	* @generated
	*/
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof PointNameEditPart) {
			((PointNameEditPart) childEditPart).setLabel(getPrimaryShape().getFigurePointNameFigure());
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
		if (childEditPart instanceof PointNameEditPart) {
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
		return new PointFigure();
	}

	/**
	* @generated
	*/
	public PointFigure getPrimaryShape() {
		return (PointFigure) getFigure();
	}

	/**
	 * @generated
	 */
	public class PointFigure extends PolylineConnectionEx {

		/**
		* @generated
		*/
		private WrappingLabel fFigurePointNameFigure;

		/**
			 * @generated
			 */
		public PointFigure() {
			this.setLineStyle(Graphics.LINE_DOT);

			createContents();
		}

		/**
		* @generated NOT
		*/
		private void createContents() {

			fFigurePointNameFigure = new WrappingLabel();

			fFigurePointNameFigure.setText("P");
			fFigurePointNameFigure.setLocation(new Point(getMapMode().DPtoLP(-20), getMapMode().DPtoLP(-20)));

			this.add(fFigurePointNameFigure);

		}

		/**
		* @generated
		*/
		public WrappingLabel getFigurePointNameFigure() {
			return fFigurePointNameFigure;
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
			if (!(view.getElement() instanceof uk.ac.swanseacoventry.cmt.ontrack.Point))
				return super.getPoints();
			uk.ac.swanseacoventry.cmt.ontrack.Point p = (uk.ac.swanseacoventry.cmt.ontrack.Point) view.getElement();
			Track straightTrack;
			straightTrack = p.getNormalTrack();
			Point midPoint = null;
			PointList pts = super.getPoints();

			if (p != null && straightTrack != null && pts.size() >= 2) {
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
