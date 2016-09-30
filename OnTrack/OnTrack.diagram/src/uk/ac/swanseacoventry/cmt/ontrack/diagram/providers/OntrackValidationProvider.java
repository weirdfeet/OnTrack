package uk.ac.swanseacoventry.cmt.ontrack.diagram.providers;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.IClientSelector;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.ITraversalStrategy;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

import uk.ac.swanseacoventry.cmt.ontrack.Connector;
import uk.ac.swanseacoventry.cmt.ontrack.Point;
import uk.ac.swanseacoventry.cmt.ontrack.Signal;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.CrossingEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.EntranceConnectorEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.ExitConnectorEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.PointEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.SignalConnectorEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.SignalEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TerminalConnectorEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TrackEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TrackPlanEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackDiagramEditorPlugin;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackVisualIDRegistry;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.util.ErrorDetectionHelper;

/**
 * @generated
 */
public class OntrackValidationProvider {

	/**
	* @generated
	*/
	private static boolean constraintsActive = false;

	/**
	* @generated
	*/
	public static boolean shouldConstraintsBePrivate() {
		return false;
	}

	/**
	* @generated
	*/
	public static void runWithConstraints(TransactionalEditingDomain editingDomain, Runnable operation) {
		final Runnable op = operation;
		Runnable task = new Runnable() {
			public void run() {
				try {
					constraintsActive = true;
					op.run();
				} finally {
					constraintsActive = false;
				}
			}
		};
		if (editingDomain != null) {
			try {
				editingDomain.runExclusive(task);
			} catch (Exception e) {
				OntrackDiagramEditorPlugin.getInstance().logError("Validation failed", e); //$NON-NLS-1$
			}
		} else {
			task.run();
		}
	}

	/**
	* @generated
	*/
	static boolean isInDefaultEditorContext(Object object) {
		if (shouldConstraintsBePrivate() && !constraintsActive) {
			return false;
		}
		if (object instanceof View) {
			return constraintsActive
					&& TrackPlanEditPart.MODEL_ID.equals(OntrackVisualIDRegistry.getModelID((View) object));
		}
		return true;
	}

	/**
	* @generated
	*/
	public static class Ctx_4001 implements IClientSelector {

		/**
		* @generated
		*/
		public boolean selects(Object object) {
			if (isInDefaultEditorContext(object) && object instanceof View) {
				final int id = OntrackVisualIDRegistry.getVisualID((View) object);
				boolean result = false;
				result = result || id == PointEditPart.VISUAL_ID;
				return result;
			}
			return false;
		}
	}

	/**
	* @generated
	*/
	public static class Ctx_4002 implements IClientSelector {

		/**
		* @generated
		*/
		public boolean selects(Object object) {
			if (isInDefaultEditorContext(object) && object instanceof View) {
				final int id = OntrackVisualIDRegistry.getVisualID((View) object);
				boolean result = false;
				result = result || id == CrossingEditPart.VISUAL_ID;
				return result;
			}
			return false;
		}
	}

	/**
	* @generated
	*/
	public static class Ctx_4006 implements IClientSelector {

		/**
		* @generated
		*/
		public boolean selects(Object object) {
			if (isInDefaultEditorContext(object) && object instanceof View) {
				final int id = OntrackVisualIDRegistry.getVisualID((View) object);
				boolean result = false;
				result = result || id == EntranceConnectorEditPart.VISUAL_ID;
				return result;
			}
			return false;
		}
	}

	/**
	* @generated
	*/
	public static class Ctx_4007 implements IClientSelector {

		/**
		* @generated
		*/
		public boolean selects(Object object) {
			if (isInDefaultEditorContext(object) && object instanceof View) {
				final int id = OntrackVisualIDRegistry.getVisualID((View) object);
				boolean result = false;
				result = result || id == ExitConnectorEditPart.VISUAL_ID;
				return result;
			}
			return false;
		}
	}

	/**
	* @generated
	*/
	public static class Ctx_4008 implements IClientSelector {

		/**
		* @generated
		*/
		public boolean selects(Object object) {
			if (isInDefaultEditorContext(object) && object instanceof View) {
				final int id = OntrackVisualIDRegistry.getVisualID((View) object);
				boolean result = false;
				result = result || id == TerminalConnectorEditPart.VISUAL_ID;
				return result;
			}
			return false;
		}
	}

	/**
	* @generated
	*/
	public static class Ctx_4005 implements IClientSelector {

		/**
		* @generated
		*/
		public boolean selects(Object object) {
			if (isInDefaultEditorContext(object) && object instanceof View) {
				final int id = OntrackVisualIDRegistry.getVisualID((View) object);
				boolean result = false;
				result = result || id == TrackEditPart.VISUAL_ID;
				return result;
			}
			return false;
		}
	}

	/**
	* @generated
	*/
	public static class Ctx_4004 implements IClientSelector {

		/**
		* @generated
		*/
		public boolean selects(Object object) {
			if (isInDefaultEditorContext(object) && object instanceof View) {
				final int id = OntrackVisualIDRegistry.getVisualID((View) object);
				boolean result = false;
				result = result || id == SignalConnectorEditPart.VISUAL_ID;
				return result;
			}
			return false;
		}
	}

	/**
	* @generated
	*/
	public static class Ctx_2002 implements IClientSelector {

		/**
		* @generated
		*/
		public boolean selects(Object object) {
			if (isInDefaultEditorContext(object) && object instanceof View) {
				final int id = OntrackVisualIDRegistry.getVisualID((View) object);
				boolean result = false;
				result = result || id == SignalEditPart.VISUAL_ID;
				return result;
			}
			return false;
		}
	}

	/**
	* @generated
	*/
	public static ITraversalStrategy getNotationTraversalStrategy(IBatchValidator validator) {
		return new CtxSwitchStrategy(validator);
	}

	/**
	* @generated
	*/
	private static class CtxSwitchStrategy implements ITraversalStrategy {

		/**
		* @generated
		*/
		private ITraversalStrategy defaultStrategy;

		/**
		* @generated
		*/
		private int currentSemanticCtxId = -1;

		/**
		* @generated
		*/
		private boolean ctxChanged = true;

		/**
		* @generated
		*/
		private EObject currentTarget;

		/**
		* @generated
		*/
		private EObject preFetchedNextTarget;

		/**
		* @generated
		*/
		private final int[] contextSwitchingIdentifiers;

		/**
		* @generated
		*/
		CtxSwitchStrategy(IBatchValidator validator) {
			this.defaultStrategy = validator.getDefaultTraversalStrategy();
			this.contextSwitchingIdentifiers = new int[] { PointEditPart.VISUAL_ID, CrossingEditPart.VISUAL_ID,
					EntranceConnectorEditPart.VISUAL_ID, ExitConnectorEditPart.VISUAL_ID,
					TerminalConnectorEditPart.VISUAL_ID, TrackEditPart.VISUAL_ID, SignalConnectorEditPart.VISUAL_ID,
					SignalEditPart.VISUAL_ID };
			Arrays.sort(this.contextSwitchingIdentifiers);
		}

		/**
		* @generated
		*/
		public void elementValidated(EObject element, IStatus status) {
			defaultStrategy.elementValidated(element, status);
		}

		/**
		* @generated
		*/
		public boolean hasNext() {
			return defaultStrategy.hasNext();
		}

		/**
		* @generated
		*/
		public boolean isClientContextChanged() {
			if (preFetchedNextTarget == null) {
				preFetchedNextTarget = next();
				prepareNextClientContext(preFetchedNextTarget);
			}
			return ctxChanged;
		}

		/**
		* @generated
		*/
		public EObject next() {
			EObject nextTarget = preFetchedNextTarget;
			if (nextTarget == null) {
				nextTarget = defaultStrategy.next();
			}
			this.preFetchedNextTarget = null;
			return this.currentTarget = nextTarget;
		}

		/**
		* @generated
		*/
		public void startTraversal(Collection traversalRoots, IProgressMonitor monitor) {
			defaultStrategy.startTraversal(traversalRoots, monitor);
		}

		/**
		* @generated
		*/
		private void prepareNextClientContext(EObject nextTarget) {
			if (nextTarget != null && currentTarget != null) {
				if (nextTarget instanceof View) {
					final int id = OntrackVisualIDRegistry.getVisualID((View) nextTarget);
					int nextSemanticId = (id != -1 && Arrays.binarySearch(contextSwitchingIdentifiers, id) >= 0) ? id
							: -1;
					if ((currentSemanticCtxId != -1 && currentSemanticCtxId != nextSemanticId)
							|| (nextSemanticId != -1 && nextSemanticId != currentSemanticCtxId)) {
						this.ctxChanged = true;
					}
					currentSemanticCtxId = nextSemanticId;
				} else {
					// context of domain model
					this.ctxChanged = currentSemanticCtxId != -1;
					currentSemanticCtxId = -1;
				}
			} else {
				this.ctxChanged = false;
			}
		}
	}

	/**
	* @generated
	*/
	public static class Adapter1 extends AbstractModelConstraint {

		/**
		* @generated NOT
		*/
		public IStatus validate(IValidationContext ctx) {
			Edge context = (Edge) ctx.getTarget();
			Track nT = (Track) context.getTarget().getElement();
			return ErrorDetectionHelper.errorPointDetected(nT) ? ctx.createSuccessStatus()
					: ctx.createFailureStatus(context);
		}
	}

	/**
	* @generated
	*/
	public static class Adapter2 extends AbstractModelConstraint {

		/**
		* @generated NOT
		*/
		public IStatus validate(IValidationContext ctx) {
			Edge context = (Edge) ctx.getTarget();
			Track t2 = (Track) context.getTarget().getElement();
			return ErrorDetectionHelper.errorCrossingDetected(t2) ? ctx.createSuccessStatus()
					: ctx.createFailureStatus(context);
		}
	}

	/**
	* @generated
	*/
	public static class Adapter3 extends AbstractModelConstraint {

		/**
		* @generated NOT
		*/
		public IStatus validate(IValidationContext ctx) {
			Edge context = (Edge) ctx.getTarget();
			Connector c = (Connector) context.getTarget().getElement();
			return ErrorDetectionHelper.errorEntrancePlacementDetected(c) ? ctx.createSuccessStatus()
					: ctx.createFailureStatus(context);
		}
	}

	/**
	* @generated
	*/
	public static class Adapter4 extends AbstractModelConstraint {

		/**
		* @generated NOT
		*/
		public IStatus validate(IValidationContext ctx) {
			Edge context = (Edge) ctx.getTarget();
			Connector c = (Connector) context.getTarget().getElement();
			return ErrorDetectionHelper.errorEntrancePlacementDetected(c) ? ctx.createSuccessStatus()
					: ctx.createFailureStatus(context);
		}
	}

	/**
	* @generated
	*/
	public static class Adapter5 extends AbstractModelConstraint {

		/**
		* @generated NOT
		*/
		public IStatus validate(IValidationContext ctx) {
			Edge context = (Edge) ctx.getTarget();
			Connector c = (Connector) context.getTarget().getElement();
			return ErrorDetectionHelper.errorTerminalPlacementDetected(c) ? ctx.createSuccessStatus()
					: ctx.createFailureStatus(context);
		}
	}

	/**
	* @generated
	*/
	public static class Adapter6 extends AbstractModelConstraint {

		/**
		* @generated NOT
		*/
		public IStatus validate(IValidationContext ctx) {
			Edge context = (Edge) ctx.getTarget();
			Connector c1 = (Connector) context.getTarget().getElement();
			Connector c2 = (Connector) context.getSource().getElement();
			Track t = (Track) context.getElement();
			return ErrorDetectionHelper.errorTrackDetected(c1) && ErrorDetectionHelper.errorTrackDetected(c2)
					&& ErrorDetectionHelper.errorTrackDetected(t) ? ctx.createSuccessStatus()
							: ctx.createFailureStatus(context);
		}
	}

	/**
	* @generated
	*/
	public static class Adapter7 extends AbstractModelConstraint {

		/**
		* @generated NOT
		*/
		public IStatus validate(IValidationContext ctx) {
			Edge context = (Edge) ctx.getTarget();
			Connector c = (Connector) context.getTarget().getElement();
			Signal s = (Signal) context.getSource().getElement();
			return ErrorDetectionHelper.errorSignalDirectionDetected(s, c) ?ctx.createSuccessStatus()
							: ctx.createFailureStatus(context);
		}
	}

	/**
	* @generated
	*/
	public static class Adapter8 extends AbstractModelConstraint {

		/**
		* @generated NOT
		*/
		public IStatus validate(IValidationContext ctx) {
			Node context = (Node) ctx.getTarget();
			Signal s = (Signal) context.getElement();
			return ErrorDetectionHelper.errorDuplicatedNameDetected(s) ?ctx.createSuccessStatus()
							: ctx.createFailureStatus(context);
		}
	}

	/**
	* @generated
	*/
	public static class Adapter9 extends AbstractModelConstraint {

		/**
		* @generated NOT
		*/
		public IStatus validate(IValidationContext ctx) {
			Edge context = (Edge) ctx.getTarget();
			Track t = (Track) context.getElement();
			return ErrorDetectionHelper.errorDuplicatedNameDetected(t) ?ctx.createSuccessStatus()
							: ctx.createFailureStatus(context);
		}
	}

	/**
	* @generated NOT
	*/
	public static class Adapter10 extends AbstractModelConstraint {

		/**
		* @generated
		*/
		public IStatus validate(IValidationContext ctx) {
			Edge context = (Edge) ctx.getTarget();
			Point p = (Point) context.getElement();
			return ErrorDetectionHelper.errorDuplicatedNameDetected(p) ?ctx.createSuccessStatus()
							: ctx.createFailureStatus(context);
		}
	}

	/**
	* @generated
	*/
	static String formatElement(EObject object) {
		return EMFCoreUtil.getQualifiedName(object, true);
	}

}
