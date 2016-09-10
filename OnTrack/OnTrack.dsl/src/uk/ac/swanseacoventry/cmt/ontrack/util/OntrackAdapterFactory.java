/**
 */
package uk.ac.swanseacoventry.cmt.ontrack.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import uk.ac.swanseacoventry.cmt.ontrack.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see uk.ac.swanseacoventry.cmt.ontrack.OntrackPackage
 * @generated
 */
public class OntrackAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static OntrackPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OntrackAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = OntrackPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OntrackSwitch<Adapter> modelSwitch =
		new OntrackSwitch<Adapter>() {
			@Override
			public Adapter caseConnector(Connector object) {
				return createConnectorAdapter();
			}
			@Override
			public Adapter caseControlTableItem(ControlTableItem object) {
				return createControlTableItemAdapter();
			}
			@Override
			public Adapter caseCrossing(Crossing object) {
				return createCrossingAdapter();
			}
			@Override
			public Adapter caseDirectedTrack(DirectedTrack object) {
				return createDirectedTrackAdapter();
			}
			@Override
			public Adapter caseEntrance(Entrance object) {
				return createEntranceAdapter();
			}
			@Override
			public Adapter caseExit(Exit object) {
				return createExitAdapter();
			}
			@Override
			public Adapter caseNewCrossing(NewCrossing object) {
				return createNewCrossingAdapter();
			}
			@Override
			public Adapter caseNewPoint(NewPoint object) {
				return createNewPointAdapter();
			}
			@Override
			public Adapter caseNewTrack(NewTrack object) {
				return createNewTrackAdapter();
			}
			@Override
			public Adapter casePoint(Point object) {
				return createPointAdapter();
			}
			@Override
			public Adapter caseReleaseTableItem(ReleaseTableItem object) {
				return createReleaseTableItemAdapter();
			}
			@Override
			public Adapter caseSignal(Signal object) {
				return createSignalAdapter();
			}
			@Override
			public Adapter caseSubTrackPlan(SubTrackPlan object) {
				return createSubTrackPlanAdapter();
			}
			@Override
			public Adapter caseTerminal(Terminal object) {
				return createTerminalAdapter();
			}
			@Override
			public Adapter caseTopoRoute(TopoRoute object) {
				return createTopoRouteAdapter();
			}
			@Override
			public Adapter caseTrack(Track object) {
				return createTrackAdapter();
			}
			@Override
			public Adapter caseTrackPlan(TrackPlan object) {
				return createTrackPlanAdapter();
			}
			@Override
			public Adapter caseUnit(Unit object) {
				return createUnitAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link uk.ac.swanseacoventry.cmt.ontrack.Connector <em>Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Connector
	 * @generated
	 */
	public Adapter createConnectorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem <em>Control Table Item</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem
	 * @generated
	 */
	public Adapter createControlTableItemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link uk.ac.swanseacoventry.cmt.ontrack.Crossing <em>Crossing</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Crossing
	 * @generated
	 */
	public Adapter createCrossingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link uk.ac.swanseacoventry.cmt.ontrack.DirectedTrack <em>Directed Track</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.DirectedTrack
	 * @generated
	 */
	public Adapter createDirectedTrackAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link uk.ac.swanseacoventry.cmt.ontrack.Entrance <em>Entrance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Entrance
	 * @generated
	 */
	public Adapter createEntranceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link uk.ac.swanseacoventry.cmt.ontrack.Exit <em>Exit</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Exit
	 * @generated
	 */
	public Adapter createExitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link uk.ac.swanseacoventry.cmt.ontrack.NewCrossing <em>New Crossing</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.NewCrossing
	 * @generated
	 */
	public Adapter createNewCrossingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link uk.ac.swanseacoventry.cmt.ontrack.NewPoint <em>New Point</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.NewPoint
	 * @generated
	 */
	public Adapter createNewPointAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link uk.ac.swanseacoventry.cmt.ontrack.NewTrack <em>New Track</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.NewTrack
	 * @generated
	 */
	public Adapter createNewTrackAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link uk.ac.swanseacoventry.cmt.ontrack.Point <em>Point</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Point
	 * @generated
	 */
	public Adapter createPointAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link uk.ac.swanseacoventry.cmt.ontrack.ReleaseTableItem <em>Release Table Item</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.ReleaseTableItem
	 * @generated
	 */
	public Adapter createReleaseTableItemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link uk.ac.swanseacoventry.cmt.ontrack.Signal <em>Signal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Signal
	 * @generated
	 */
	public Adapter createSignalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan <em>Sub Track Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan
	 * @generated
	 */
	public Adapter createSubTrackPlanAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link uk.ac.swanseacoventry.cmt.ontrack.Terminal <em>Terminal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Terminal
	 * @generated
	 */
	public Adapter createTerminalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link uk.ac.swanseacoventry.cmt.ontrack.TopoRoute <em>Topo Route</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.TopoRoute
	 * @generated
	 */
	public Adapter createTopoRouteAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link uk.ac.swanseacoventry.cmt.ontrack.Track <em>Track</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Track
	 * @generated
	 */
	public Adapter createTrackAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link uk.ac.swanseacoventry.cmt.ontrack.TrackPlan <em>Track Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.TrackPlan
	 * @generated
	 */
	public Adapter createTrackPlanAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link uk.ac.swanseacoventry.cmt.ontrack.Unit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Unit
	 * @generated
	 */
	public Adapter createUnitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //OntrackAdapterFactory
