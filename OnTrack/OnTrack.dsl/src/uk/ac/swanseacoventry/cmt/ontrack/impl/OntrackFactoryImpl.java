/**
 */
package uk.ac.swanseacoventry.cmt.ontrack.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import uk.ac.swanseacoventry.cmt.ontrack.Connector;
import uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem;
import uk.ac.swanseacoventry.cmt.ontrack.Crossing;
import uk.ac.swanseacoventry.cmt.ontrack.DirectedTrack;
import uk.ac.swanseacoventry.cmt.ontrack.Entrance;
import uk.ac.swanseacoventry.cmt.ontrack.Exit;
import uk.ac.swanseacoventry.cmt.ontrack.NewCrossing;
import uk.ac.swanseacoventry.cmt.ontrack.NewPoint;
import uk.ac.swanseacoventry.cmt.ontrack.NewTrack;
import uk.ac.swanseacoventry.cmt.ontrack.OntrackFactory;
import uk.ac.swanseacoventry.cmt.ontrack.OntrackPackage;
import uk.ac.swanseacoventry.cmt.ontrack.Point;
import uk.ac.swanseacoventry.cmt.ontrack.ReleaseTableItem;
import uk.ac.swanseacoventry.cmt.ontrack.Signal;
import uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan;
import uk.ac.swanseacoventry.cmt.ontrack.Terminal;
import uk.ac.swanseacoventry.cmt.ontrack.TopoRoute;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OntrackFactoryImpl extends EFactoryImpl implements OntrackFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OntrackFactory init() {
		try {
			OntrackFactory theOntrackFactory = (OntrackFactory)EPackage.Registry.INSTANCE.getEFactory(OntrackPackage.eNS_URI);
			if (theOntrackFactory != null) {
				return theOntrackFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new OntrackFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OntrackFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case OntrackPackage.CONNECTOR: return createConnector();
			case OntrackPackage.CONTROL_TABLE_ITEM: return createControlTableItem();
			case OntrackPackage.CROSSING: return createCrossing();
			case OntrackPackage.DIRECTED_TRACK: return createDirectedTrack();
			case OntrackPackage.ENTRANCE: return createEntrance();
			case OntrackPackage.EXIT: return createExit();
			case OntrackPackage.NEW_CROSSING: return createNewCrossing();
			case OntrackPackage.NEW_POINT: return createNewPoint();
			case OntrackPackage.NEW_TRACK: return createNewTrack();
			case OntrackPackage.POINT: return createPoint();
			case OntrackPackage.RELEASE_TABLE_ITEM: return createReleaseTableItem();
			case OntrackPackage.SIGNAL: return createSignal();
			case OntrackPackage.SUB_TRACK_PLAN: return createSubTrackPlan();
			case OntrackPackage.TERMINAL: return createTerminal();
			case OntrackPackage.TOPO_ROUTE: return createTopoRoute();
			case OntrackPackage.TRACK: return createTrack();
			case OntrackPackage.TRACK_PLAN: return createTrackPlan();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Connector createConnector() {
		ConnectorImpl connector = new ConnectorImpl();
		return connector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ControlTableItem createControlTableItem() {
		ControlTableItemImpl controlTableItem = new ControlTableItemImpl();
		return controlTableItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Crossing createCrossing() {
		CrossingImpl crossing = new CrossingImpl();
		return crossing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DirectedTrack createDirectedTrack() {
		DirectedTrackImpl directedTrack = new DirectedTrackImpl();
		return directedTrack;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Entrance createEntrance() {
		EntranceImpl entrance = new EntranceImpl();
		return entrance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Exit createExit() {
		ExitImpl exit = new ExitImpl();
		return exit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NewCrossing createNewCrossing() {
		NewCrossingImpl newCrossing = new NewCrossingImpl();
		return newCrossing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NewPoint createNewPoint() {
		NewPointImpl newPoint = new NewPointImpl();
		return newPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NewTrack createNewTrack() {
		NewTrackImpl newTrack = new NewTrackImpl();
		return newTrack;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Point createPoint() {
		PointImpl point = new PointImpl();
		return point;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReleaseTableItem createReleaseTableItem() {
		ReleaseTableItemImpl releaseTableItem = new ReleaseTableItemImpl();
		return releaseTableItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Signal createSignal() {
		SignalImpl signal = new SignalImpl();
		return signal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SubTrackPlan createSubTrackPlan() {
		SubTrackPlanImpl subTrackPlan = new SubTrackPlanImpl();
		return subTrackPlan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Terminal createTerminal() {
		TerminalImpl terminal = new TerminalImpl();
		return terminal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TopoRoute createTopoRoute() {
		TopoRouteImpl topoRoute = new TopoRouteImpl();
		return topoRoute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Track createTrack() {
		TrackImpl track = new TrackImpl();
		return track;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TrackPlan createTrackPlan() {
		TrackPlanImpl trackPlan = new TrackPlanImpl();
		return trackPlan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OntrackPackage getOntrackPackage() {
		return (OntrackPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static OntrackPackage getPackage() {
		return OntrackPackage.eINSTANCE;
	}

} //OntrackFactoryImpl
