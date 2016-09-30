/**
 */
package uk.ac.swanseacoventry.cmt.ontrack;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see uk.ac.swanseacoventry.cmt.ontrack.OntrackFactory
 * @model kind="package"
 * @generated
 */
public interface OntrackPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ontrack";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///uk/ac/swanseacoventry/cmt/ontrack.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "uk.ac.swanseacoventry.cmt.ontrack";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OntrackPackage eINSTANCE = uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl.init();

	/**
	 * The meta object id for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.ConnectorImpl <em>Connector</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.ConnectorImpl
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getConnector()
	 * @generated
	 */
	int CONNECTOR = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__ID = 0;

	/**
	 * The feature id for the '<em><b>Track1s</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__TRACK1S = 1;

	/**
	 * The feature id for the '<em><b>Track2s</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__TRACK2S = 2;

	/**
	 * The feature id for the '<em><b>Signals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__SIGNALS = 3;

	/**
	 * The feature id for the '<em><b>Terminal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__TERMINAL = 4;

	/**
	 * The feature id for the '<em><b>Entrances</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__ENTRANCES = 5;

	/**
	 * The feature id for the '<em><b>Exits</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__EXITS = 6;

	/**
	 * The number of structural features of the '<em>Connector</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_FEATURE_COUNT = 7;

	/**
	 * The number of operations of the '<em>Connector</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.ControlTableItemImpl <em>Control Table Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.ControlTableItemImpl
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getControlTableItem()
	 * @generated
	 */
	int CONTROL_TABLE_ITEM = 1;

	/**
	 * The feature id for the '<em><b>Signal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_TABLE_ITEM__SIGNAL = 0;

	/**
	 * The feature id for the '<em><b>Route</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_TABLE_ITEM__ROUTE = 1;

	/**
	 * The feature id for the '<em><b>Normals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_TABLE_ITEM__NORMALS = 2;

	/**
	 * The feature id for the '<em><b>Reverses</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_TABLE_ITEM__REVERSES = 3;

	/**
	 * The feature id for the '<em><b>Clears</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_TABLE_ITEM__CLEARS = 4;

	/**
	 * The feature id for the '<em><b>Directions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_TABLE_ITEM__DIRECTIONS = 5;

	/**
	 * The number of structural features of the '<em>Control Table Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_TABLE_ITEM_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Control Table Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_TABLE_ITEM_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.UnitImpl <em>Unit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.UnitImpl
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getUnit()
	 * @generated
	 */
	int UNIT = 17;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT__NAME = 0;

	/**
	 * The number of structural features of the '<em>Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.CrossingImpl <em>Crossing</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.CrossingImpl
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getCrossing()
	 * @generated
	 */
	int CROSSING = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CROSSING__NAME = UNIT__NAME;

	/**
	 * The feature id for the '<em><b>Track1</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CROSSING__TRACK1 = UNIT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Track2</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CROSSING__TRACK2 = UNIT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Crossing</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CROSSING_FEATURE_COUNT = UNIT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Crossing</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CROSSING_OPERATION_COUNT = UNIT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.DirectedTrackImpl <em>Directed Track</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.DirectedTrackImpl
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getDirectedTrack()
	 * @generated
	 */
	int DIRECTED_TRACK = 3;

	/**
	 * The feature id for the '<em><b>Track</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRECTED_TRACK__TRACK = 0;

	/**
	 * The feature id for the '<em><b>Connector</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRECTED_TRACK__CONNECTOR = 1;

	/**
	 * The number of structural features of the '<em>Directed Track</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRECTED_TRACK_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Directed Track</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRECTED_TRACK_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.EntranceImpl <em>Entrance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.EntranceImpl
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getEntrance()
	 * @generated
	 */
	int ENTRANCE = 4;

	/**
	 * The feature id for the '<em><b>Connector</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRANCE__CONNECTOR = 0;

	/**
	 * The number of structural features of the '<em>Entrance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRANCE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Entrance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRANCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.ExitImpl <em>Exit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.ExitImpl
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getExit()
	 * @generated
	 */
	int EXIT = 5;

	/**
	 * The feature id for the '<em><b>Connector</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT__CONNECTOR = 0;

	/**
	 * The number of structural features of the '<em>Exit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Exit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.NewCrossingImpl <em>New Crossing</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.NewCrossingImpl
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getNewCrossing()
	 * @generated
	 */
	int NEW_CROSSING = 6;

	/**
	 * The number of structural features of the '<em>New Crossing</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_CROSSING_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>New Crossing</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_CROSSING_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.NewPointImpl <em>New Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.NewPointImpl
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getNewPoint()
	 * @generated
	 */
	int NEW_POINT = 7;

	/**
	 * The number of structural features of the '<em>New Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_POINT_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>New Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_POINT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.NewTrackImpl <em>New Track</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.NewTrackImpl
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getNewTrack()
	 * @generated
	 */
	int NEW_TRACK = 8;

	/**
	 * The number of structural features of the '<em>New Track</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_TRACK_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>New Track</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_TRACK_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.PointImpl <em>Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.PointImpl
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getPoint()
	 * @generated
	 */
	int POINT = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT__NAME = UNIT__NAME;

	/**
	 * The feature id for the '<em><b>Normal Track</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT__NORMAL_TRACK = UNIT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Reverse Track</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT__REVERSE_TRACK = UNIT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_FEATURE_COUNT = UNIT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_OPERATION_COUNT = UNIT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.ReleaseTableItemImpl <em>Release Table Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.ReleaseTableItemImpl
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getReleaseTableItem()
	 * @generated
	 */
	int RELEASE_TABLE_ITEM = 10;

	/**
	 * The feature id for the '<em><b>Route</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELEASE_TABLE_ITEM__ROUTE = 0;

	/**
	 * The feature id for the '<em><b>Point</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELEASE_TABLE_ITEM__POINT = 1;

	/**
	 * The feature id for the '<em><b>Unoccupied Track</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELEASE_TABLE_ITEM__UNOCCUPIED_TRACK = 2;

	/**
	 * The feature id for the '<em><b>Occupied Track</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELEASE_TABLE_ITEM__OCCUPIED_TRACK = 3;

	/**
	 * The number of structural features of the '<em>Release Table Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELEASE_TABLE_ITEM_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Release Table Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELEASE_TABLE_ITEM_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.SignalImpl <em>Signal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.SignalImpl
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getSignal()
	 * @generated
	 */
	int SIGNAL = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNAL__NAME = 0;

	/**
	 * The feature id for the '<em><b>Track</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNAL__TRACK = 1;

	/**
	 * The feature id for the '<em><b>Connector</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNAL__CONNECTOR = 2;

	/**
	 * The number of structural features of the '<em>Signal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNAL_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Signal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNAL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.SubTrackPlanImpl <em>Sub Track Plan</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.SubTrackPlanImpl
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getSubTrackPlan()
	 * @generated
	 */
	int SUB_TRACK_PLAN = 12;

	/**
	 * The feature id for the '<em><b>Criticals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_TRACK_PLAN__CRITICALS = 0;

	/**
	 * The feature id for the '<em><b>Tracks</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_TRACK_PLAN__TRACKS = 1;

	/**
	 * The feature id for the '<em><b>Connectors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_TRACK_PLAN__CONNECTORS = 2;

	/**
	 * The feature id for the '<em><b>Points</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_TRACK_PLAN__POINTS = 3;

	/**
	 * The feature id for the '<em><b>Crossings</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_TRACK_PLAN__CROSSINGS = 4;

	/**
	 * The feature id for the '<em><b>Signals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_TRACK_PLAN__SIGNALS = 5;

	/**
	 * The feature id for the '<em><b>Entrances</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_TRACK_PLAN__ENTRANCES = 6;

	/**
	 * The feature id for the '<em><b>Exits</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_TRACK_PLAN__EXITS = 7;

	/**
	 * The feature id for the '<em><b>Terminals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_TRACK_PLAN__TERMINALS = 8;

	/**
	 * The feature id for the '<em><b>Topo Routes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_TRACK_PLAN__TOPO_ROUTES = 9;

	/**
	 * The feature id for the '<em><b>Control Table</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_TRACK_PLAN__CONTROL_TABLE = 10;

	/**
	 * The feature id for the '<em><b>Release Table</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_TRACK_PLAN__RELEASE_TABLE = 11;

	/**
	 * The number of structural features of the '<em>Sub Track Plan</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_TRACK_PLAN_FEATURE_COUNT = 12;

	/**
	 * The number of operations of the '<em>Sub Track Plan</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_TRACK_PLAN_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TerminalImpl <em>Terminal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.TerminalImpl
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getTerminal()
	 * @generated
	 */
	int TERMINAL = 13;

	/**
	 * The feature id for the '<em><b>Connector</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERMINAL__CONNECTOR = 0;

	/**
	 * The number of structural features of the '<em>Terminal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERMINAL_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Terminal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERMINAL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TopoRouteImpl <em>Topo Route</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.TopoRouteImpl
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getTopoRoute()
	 * @generated
	 */
	int TOPO_ROUTE = 14;

	/**
	 * The feature id for the '<em><b>Names</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPO_ROUTE__NAMES = 0;

	/**
	 * The feature id for the '<em><b>Directed Tracks</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPO_ROUTE__DIRECTED_TRACKS = 1;

	/**
	 * The feature id for the '<em><b>Start Signal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPO_ROUTE__START_SIGNAL = 2;

	/**
	 * The feature id for the '<em><b>End Signal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPO_ROUTE__END_SIGNAL = 3;

	/**
	 * The number of structural features of the '<em>Topo Route</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPO_ROUTE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Topo Route</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPO_ROUTE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TrackImpl <em>Track</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.TrackImpl
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getTrack()
	 * @generated
	 */
	int TRACK = 15;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK__NAME = UNIT__NAME;

	/**
	 * The feature id for the '<em><b>C1</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK__C1 = UNIT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>C2</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK__C2 = UNIT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Point Reverse</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK__POINT_REVERSE = UNIT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Point Normal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK__POINT_NORMAL = UNIT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Crossing2</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK__CROSSING2 = UNIT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Crossing1</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK__CROSSING1 = UNIT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Directed Tracks</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK__DIRECTED_TRACKS = UNIT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Signals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK__SIGNALS = UNIT_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Track</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK_FEATURE_COUNT = UNIT_FEATURE_COUNT + 8;

	/**
	 * The number of operations of the '<em>Track</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK_OPERATION_COUNT = UNIT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TrackPlanImpl <em>Track Plan</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.TrackPlanImpl
	 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getTrackPlan()
	 * @generated
	 */
	int TRACK_PLAN = 16;

	/**
	 * The feature id for the '<em><b>Tracks</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK_PLAN__TRACKS = 0;

	/**
	 * The feature id for the '<em><b>Connectors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK_PLAN__CONNECTORS = 1;

	/**
	 * The feature id for the '<em><b>Points</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK_PLAN__POINTS = 2;

	/**
	 * The feature id for the '<em><b>Crossings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK_PLAN__CROSSINGS = 3;

	/**
	 * The feature id for the '<em><b>Signals</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK_PLAN__SIGNALS = 4;

	/**
	 * The feature id for the '<em><b>Entrances</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK_PLAN__ENTRANCES = 5;

	/**
	 * The feature id for the '<em><b>Exits</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK_PLAN__EXITS = 6;

	/**
	 * The feature id for the '<em><b>Terminals</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK_PLAN__TERMINALS = 7;

	/**
	 * The feature id for the '<em><b>Topo Routes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK_PLAN__TOPO_ROUTES = 8;

	/**
	 * The feature id for the '<em><b>Control Table</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK_PLAN__CONTROL_TABLE = 9;

	/**
	 * The feature id for the '<em><b>Release Table</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK_PLAN__RELEASE_TABLE = 10;

	/**
	 * The feature id for the '<em><b>New Track</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK_PLAN__NEW_TRACK = 11;

	/**
	 * The feature id for the '<em><b>New Point</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK_PLAN__NEW_POINT = 12;

	/**
	 * The feature id for the '<em><b>New Crossing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK_PLAN__NEW_CROSSING = 13;

	/**
	 * The feature id for the '<em><b>Sub Track Plans</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK_PLAN__SUB_TRACK_PLANS = 14;

	/**
	 * The feature id for the '<em><b>Selected Sub Track Plan</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK_PLAN__SELECTED_SUB_TRACK_PLAN = 15;

	/**
	 * The feature id for the '<em><b>Overlapped</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK_PLAN__OVERLAPPED = 16;

	/**
	 * The number of structural features of the '<em>Track Plan</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK_PLAN_FEATURE_COUNT = 17;

	/**
	 * The number of operations of the '<em>Track Plan</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK_PLAN_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link uk.ac.swanseacoventry.cmt.ontrack.Connector <em>Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connector</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Connector
	 * @generated
	 */
	EClass getConnector();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.swanseacoventry.cmt.ontrack.Connector#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Connector#getId()
	 * @see #getConnector()
	 * @generated
	 */
	EAttribute getConnector_Id();

	/**
	 * Returns the meta object for the reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.Connector#getTrack1s <em>Track1s</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Track1s</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Connector#getTrack1s()
	 * @see #getConnector()
	 * @generated
	 */
	EReference getConnector_Track1s();

	/**
	 * Returns the meta object for the reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.Connector#getTrack2s <em>Track2s</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Track2s</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Connector#getTrack2s()
	 * @see #getConnector()
	 * @generated
	 */
	EReference getConnector_Track2s();

	/**
	 * Returns the meta object for the reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.Connector#getSignals <em>Signals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Signals</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Connector#getSignals()
	 * @see #getConnector()
	 * @generated
	 */
	EReference getConnector_Signals();

	/**
	 * Returns the meta object for the reference '{@link uk.ac.swanseacoventry.cmt.ontrack.Connector#getTerminal <em>Terminal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Terminal</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Connector#getTerminal()
	 * @see #getConnector()
	 * @generated
	 */
	EReference getConnector_Terminal();

	/**
	 * Returns the meta object for the reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.Connector#getEntrances <em>Entrances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Entrances</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Connector#getEntrances()
	 * @see #getConnector()
	 * @generated
	 */
	EReference getConnector_Entrances();

	/**
	 * Returns the meta object for the reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.Connector#getExits <em>Exits</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Exits</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Connector#getExits()
	 * @see #getConnector()
	 * @generated
	 */
	EReference getConnector_Exits();

	/**
	 * Returns the meta object for class '{@link uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem <em>Control Table Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Control Table Item</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem
	 * @generated
	 */
	EClass getControlTableItem();

	/**
	 * Returns the meta object for the reference '{@link uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem#getSignal <em>Signal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Signal</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem#getSignal()
	 * @see #getControlTableItem()
	 * @generated
	 */
	EReference getControlTableItem_Signal();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem#getRoute <em>Route</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Route</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem#getRoute()
	 * @see #getControlTableItem()
	 * @generated
	 */
	EAttribute getControlTableItem_Route();

	/**
	 * Returns the meta object for the reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem#getNormals <em>Normals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Normals</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem#getNormals()
	 * @see #getControlTableItem()
	 * @generated
	 */
	EReference getControlTableItem_Normals();

	/**
	 * Returns the meta object for the reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem#getReverses <em>Reverses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Reverses</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem#getReverses()
	 * @see #getControlTableItem()
	 * @generated
	 */
	EReference getControlTableItem_Reverses();

	/**
	 * Returns the meta object for the reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem#getClears <em>Clears</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Clears</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem#getClears()
	 * @see #getControlTableItem()
	 * @generated
	 */
	EReference getControlTableItem_Clears();

	/**
	 * Returns the meta object for the reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem#getDirections <em>Directions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Directions</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem#getDirections()
	 * @see #getControlTableItem()
	 * @generated
	 */
	EReference getControlTableItem_Directions();

	/**
	 * Returns the meta object for class '{@link uk.ac.swanseacoventry.cmt.ontrack.Crossing <em>Crossing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Crossing</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Crossing
	 * @generated
	 */
	EClass getCrossing();

	/**
	 * Returns the meta object for the reference '{@link uk.ac.swanseacoventry.cmt.ontrack.Crossing#getTrack1 <em>Track1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Track1</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Crossing#getTrack1()
	 * @see #getCrossing()
	 * @generated
	 */
	EReference getCrossing_Track1();

	/**
	 * Returns the meta object for the reference '{@link uk.ac.swanseacoventry.cmt.ontrack.Crossing#getTrack2 <em>Track2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Track2</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Crossing#getTrack2()
	 * @see #getCrossing()
	 * @generated
	 */
	EReference getCrossing_Track2();

	/**
	 * Returns the meta object for class '{@link uk.ac.swanseacoventry.cmt.ontrack.DirectedTrack <em>Directed Track</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Directed Track</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.DirectedTrack
	 * @generated
	 */
	EClass getDirectedTrack();

	/**
	 * Returns the meta object for the reference '{@link uk.ac.swanseacoventry.cmt.ontrack.DirectedTrack#getTrack <em>Track</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Track</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.DirectedTrack#getTrack()
	 * @see #getDirectedTrack()
	 * @generated
	 */
	EReference getDirectedTrack_Track();

	/**
	 * Returns the meta object for the reference '{@link uk.ac.swanseacoventry.cmt.ontrack.DirectedTrack#getConnector <em>Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Connector</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.DirectedTrack#getConnector()
	 * @see #getDirectedTrack()
	 * @generated
	 */
	EReference getDirectedTrack_Connector();

	/**
	 * Returns the meta object for class '{@link uk.ac.swanseacoventry.cmt.ontrack.Entrance <em>Entrance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entrance</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Entrance
	 * @generated
	 */
	EClass getEntrance();

	/**
	 * Returns the meta object for the reference '{@link uk.ac.swanseacoventry.cmt.ontrack.Entrance#getConnector <em>Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Connector</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Entrance#getConnector()
	 * @see #getEntrance()
	 * @generated
	 */
	EReference getEntrance_Connector();

	/**
	 * Returns the meta object for class '{@link uk.ac.swanseacoventry.cmt.ontrack.Exit <em>Exit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exit</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Exit
	 * @generated
	 */
	EClass getExit();

	/**
	 * Returns the meta object for the reference '{@link uk.ac.swanseacoventry.cmt.ontrack.Exit#getConnector <em>Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Connector</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Exit#getConnector()
	 * @see #getExit()
	 * @generated
	 */
	EReference getExit_Connector();

	/**
	 * Returns the meta object for class '{@link uk.ac.swanseacoventry.cmt.ontrack.NewCrossing <em>New Crossing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>New Crossing</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.NewCrossing
	 * @generated
	 */
	EClass getNewCrossing();

	/**
	 * Returns the meta object for class '{@link uk.ac.swanseacoventry.cmt.ontrack.NewPoint <em>New Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>New Point</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.NewPoint
	 * @generated
	 */
	EClass getNewPoint();

	/**
	 * Returns the meta object for class '{@link uk.ac.swanseacoventry.cmt.ontrack.NewTrack <em>New Track</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>New Track</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.NewTrack
	 * @generated
	 */
	EClass getNewTrack();

	/**
	 * Returns the meta object for class '{@link uk.ac.swanseacoventry.cmt.ontrack.Point <em>Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Point</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Point
	 * @generated
	 */
	EClass getPoint();

	/**
	 * Returns the meta object for the reference '{@link uk.ac.swanseacoventry.cmt.ontrack.Point#getNormalTrack <em>Normal Track</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Normal Track</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Point#getNormalTrack()
	 * @see #getPoint()
	 * @generated
	 */
	EReference getPoint_NormalTrack();

	/**
	 * Returns the meta object for the reference '{@link uk.ac.swanseacoventry.cmt.ontrack.Point#getReverseTrack <em>Reverse Track</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reverse Track</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Point#getReverseTrack()
	 * @see #getPoint()
	 * @generated
	 */
	EReference getPoint_ReverseTrack();

	/**
	 * Returns the meta object for class '{@link uk.ac.swanseacoventry.cmt.ontrack.ReleaseTableItem <em>Release Table Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Release Table Item</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.ReleaseTableItem
	 * @generated
	 */
	EClass getReleaseTableItem();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.swanseacoventry.cmt.ontrack.ReleaseTableItem#getRoute <em>Route</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Route</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.ReleaseTableItem#getRoute()
	 * @see #getReleaseTableItem()
	 * @generated
	 */
	EAttribute getReleaseTableItem_Route();

	/**
	 * Returns the meta object for the reference '{@link uk.ac.swanseacoventry.cmt.ontrack.ReleaseTableItem#getPoint <em>Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Point</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.ReleaseTableItem#getPoint()
	 * @see #getReleaseTableItem()
	 * @generated
	 */
	EReference getReleaseTableItem_Point();

	/**
	 * Returns the meta object for the reference '{@link uk.ac.swanseacoventry.cmt.ontrack.ReleaseTableItem#getUnoccupiedTrack <em>Unoccupied Track</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Unoccupied Track</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.ReleaseTableItem#getUnoccupiedTrack()
	 * @see #getReleaseTableItem()
	 * @generated
	 */
	EReference getReleaseTableItem_UnoccupiedTrack();

	/**
	 * Returns the meta object for the reference '{@link uk.ac.swanseacoventry.cmt.ontrack.ReleaseTableItem#getOccupiedTrack <em>Occupied Track</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Occupied Track</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.ReleaseTableItem#getOccupiedTrack()
	 * @see #getReleaseTableItem()
	 * @generated
	 */
	EReference getReleaseTableItem_OccupiedTrack();

	/**
	 * Returns the meta object for class '{@link uk.ac.swanseacoventry.cmt.ontrack.Signal <em>Signal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Signal</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Signal
	 * @generated
	 */
	EClass getSignal();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.swanseacoventry.cmt.ontrack.Signal#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Signal#getName()
	 * @see #getSignal()
	 * @generated
	 */
	EAttribute getSignal_Name();

	/**
	 * Returns the meta object for the reference '{@link uk.ac.swanseacoventry.cmt.ontrack.Signal#getTrack <em>Track</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Track</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Signal#getTrack()
	 * @see #getSignal()
	 * @generated
	 */
	EReference getSignal_Track();

	/**
	 * Returns the meta object for the reference '{@link uk.ac.swanseacoventry.cmt.ontrack.Signal#getConnector <em>Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Connector</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Signal#getConnector()
	 * @see #getSignal()
	 * @generated
	 */
	EReference getSignal_Connector();

	/**
	 * Returns the meta object for class '{@link uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan <em>Sub Track Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sub Track Plan</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan
	 * @generated
	 */
	EClass getSubTrackPlan();

	/**
	 * Returns the meta object for the reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan#getCriticals <em>Criticals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Criticals</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan#getCriticals()
	 * @see #getSubTrackPlan()
	 * @generated
	 */
	EReference getSubTrackPlan_Criticals();

	/**
	 * Returns the meta object for the reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan#getTracks <em>Tracks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Tracks</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan#getTracks()
	 * @see #getSubTrackPlan()
	 * @generated
	 */
	EReference getSubTrackPlan_Tracks();

	/**
	 * Returns the meta object for the reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan#getConnectors <em>Connectors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Connectors</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan#getConnectors()
	 * @see #getSubTrackPlan()
	 * @generated
	 */
	EReference getSubTrackPlan_Connectors();

	/**
	 * Returns the meta object for the reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan#getPoints <em>Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Points</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan#getPoints()
	 * @see #getSubTrackPlan()
	 * @generated
	 */
	EReference getSubTrackPlan_Points();

	/**
	 * Returns the meta object for the reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan#getCrossings <em>Crossings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Crossings</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan#getCrossings()
	 * @see #getSubTrackPlan()
	 * @generated
	 */
	EReference getSubTrackPlan_Crossings();

	/**
	 * Returns the meta object for the reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan#getSignals <em>Signals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Signals</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan#getSignals()
	 * @see #getSubTrackPlan()
	 * @generated
	 */
	EReference getSubTrackPlan_Signals();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan#getEntrances <em>Entrances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Entrances</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan#getEntrances()
	 * @see #getSubTrackPlan()
	 * @generated
	 */
	EReference getSubTrackPlan_Entrances();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan#getExits <em>Exits</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exits</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan#getExits()
	 * @see #getSubTrackPlan()
	 * @generated
	 */
	EReference getSubTrackPlan_Exits();

	/**
	 * Returns the meta object for the reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan#getTerminals <em>Terminals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Terminals</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan#getTerminals()
	 * @see #getSubTrackPlan()
	 * @generated
	 */
	EReference getSubTrackPlan_Terminals();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan#getTopoRoutes <em>Topo Routes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Topo Routes</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan#getTopoRoutes()
	 * @see #getSubTrackPlan()
	 * @generated
	 */
	EReference getSubTrackPlan_TopoRoutes();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan#getControlTable <em>Control Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Control Table</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan#getControlTable()
	 * @see #getSubTrackPlan()
	 * @generated
	 */
	EReference getSubTrackPlan_ControlTable();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan#getReleaseTable <em>Release Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Release Table</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan#getReleaseTable()
	 * @see #getSubTrackPlan()
	 * @generated
	 */
	EReference getSubTrackPlan_ReleaseTable();

	/**
	 * Returns the meta object for class '{@link uk.ac.swanseacoventry.cmt.ontrack.Terminal <em>Terminal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Terminal</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Terminal
	 * @generated
	 */
	EClass getTerminal();

	/**
	 * Returns the meta object for the reference '{@link uk.ac.swanseacoventry.cmt.ontrack.Terminal#getConnector <em>Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Connector</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Terminal#getConnector()
	 * @see #getTerminal()
	 * @generated
	 */
	EReference getTerminal_Connector();

	/**
	 * Returns the meta object for class '{@link uk.ac.swanseacoventry.cmt.ontrack.TopoRoute <em>Topo Route</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Topo Route</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.TopoRoute
	 * @generated
	 */
	EClass getTopoRoute();

	/**
	 * Returns the meta object for the attribute list '{@link uk.ac.swanseacoventry.cmt.ontrack.TopoRoute#getNames <em>Names</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Names</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.TopoRoute#getNames()
	 * @see #getTopoRoute()
	 * @generated
	 */
	EAttribute getTopoRoute_Names();

	/**
	 * Returns the meta object for the reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.TopoRoute#getDirectedTracks <em>Directed Tracks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Directed Tracks</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.TopoRoute#getDirectedTracks()
	 * @see #getTopoRoute()
	 * @generated
	 */
	EReference getTopoRoute_DirectedTracks();

	/**
	 * Returns the meta object for the reference '{@link uk.ac.swanseacoventry.cmt.ontrack.TopoRoute#getStartSignal <em>Start Signal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Start Signal</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.TopoRoute#getStartSignal()
	 * @see #getTopoRoute()
	 * @generated
	 */
	EReference getTopoRoute_StartSignal();

	/**
	 * Returns the meta object for the reference '{@link uk.ac.swanseacoventry.cmt.ontrack.TopoRoute#getEndSignal <em>End Signal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>End Signal</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.TopoRoute#getEndSignal()
	 * @see #getTopoRoute()
	 * @generated
	 */
	EReference getTopoRoute_EndSignal();

	/**
	 * Returns the meta object for class '{@link uk.ac.swanseacoventry.cmt.ontrack.Track <em>Track</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Track</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Track
	 * @generated
	 */
	EClass getTrack();

	/**
	 * Returns the meta object for the reference '{@link uk.ac.swanseacoventry.cmt.ontrack.Track#getC1 <em>C1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>C1</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Track#getC1()
	 * @see #getTrack()
	 * @generated
	 */
	EReference getTrack_C1();

	/**
	 * Returns the meta object for the reference '{@link uk.ac.swanseacoventry.cmt.ontrack.Track#getC2 <em>C2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>C2</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Track#getC2()
	 * @see #getTrack()
	 * @generated
	 */
	EReference getTrack_C2();

	/**
	 * Returns the meta object for the reference '{@link uk.ac.swanseacoventry.cmt.ontrack.Track#getPointReverse <em>Point Reverse</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Point Reverse</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Track#getPointReverse()
	 * @see #getTrack()
	 * @generated
	 */
	EReference getTrack_PointReverse();

	/**
	 * Returns the meta object for the reference '{@link uk.ac.swanseacoventry.cmt.ontrack.Track#getPointNormal <em>Point Normal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Point Normal</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Track#getPointNormal()
	 * @see #getTrack()
	 * @generated
	 */
	EReference getTrack_PointNormal();

	/**
	 * Returns the meta object for the reference '{@link uk.ac.swanseacoventry.cmt.ontrack.Track#getCrossing2 <em>Crossing2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Crossing2</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Track#getCrossing2()
	 * @see #getTrack()
	 * @generated
	 */
	EReference getTrack_Crossing2();

	/**
	 * Returns the meta object for the reference '{@link uk.ac.swanseacoventry.cmt.ontrack.Track#getCrossing1 <em>Crossing1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Crossing1</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Track#getCrossing1()
	 * @see #getTrack()
	 * @generated
	 */
	EReference getTrack_Crossing1();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.Track#getDirectedTracks <em>Directed Tracks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Directed Tracks</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Track#getDirectedTracks()
	 * @see #getTrack()
	 * @generated
	 */
	EReference getTrack_DirectedTracks();

	/**
	 * Returns the meta object for the reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.Track#getSignals <em>Signals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Signals</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Track#getSignals()
	 * @see #getTrack()
	 * @generated
	 */
	EReference getTrack_Signals();

	/**
	 * Returns the meta object for class '{@link uk.ac.swanseacoventry.cmt.ontrack.TrackPlan <em>Track Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Track Plan</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.TrackPlan
	 * @generated
	 */
	EClass getTrackPlan();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getTracks <em>Tracks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tracks</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getTracks()
	 * @see #getTrackPlan()
	 * @generated
	 */
	EReference getTrackPlan_Tracks();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getConnectors <em>Connectors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connectors</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getConnectors()
	 * @see #getTrackPlan()
	 * @generated
	 */
	EReference getTrackPlan_Connectors();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getPoints <em>Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Points</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getPoints()
	 * @see #getTrackPlan()
	 * @generated
	 */
	EReference getTrackPlan_Points();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getCrossings <em>Crossings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Crossings</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getCrossings()
	 * @see #getTrackPlan()
	 * @generated
	 */
	EReference getTrackPlan_Crossings();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getSignals <em>Signals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Signals</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getSignals()
	 * @see #getTrackPlan()
	 * @generated
	 */
	EReference getTrackPlan_Signals();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getEntrances <em>Entrances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Entrances</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getEntrances()
	 * @see #getTrackPlan()
	 * @generated
	 */
	EReference getTrackPlan_Entrances();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getExits <em>Exits</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exits</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getExits()
	 * @see #getTrackPlan()
	 * @generated
	 */
	EReference getTrackPlan_Exits();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getTerminals <em>Terminals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Terminals</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getTerminals()
	 * @see #getTrackPlan()
	 * @generated
	 */
	EReference getTrackPlan_Terminals();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getTopoRoutes <em>Topo Routes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Topo Routes</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getTopoRoutes()
	 * @see #getTrackPlan()
	 * @generated
	 */
	EReference getTrackPlan_TopoRoutes();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getControlTable <em>Control Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Control Table</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getControlTable()
	 * @see #getTrackPlan()
	 * @generated
	 */
	EReference getTrackPlan_ControlTable();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getReleaseTable <em>Release Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Release Table</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getReleaseTable()
	 * @see #getTrackPlan()
	 * @generated
	 */
	EReference getTrackPlan_ReleaseTable();

	/**
	 * Returns the meta object for the containment reference '{@link uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getNewTrack <em>New Track</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>New Track</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getNewTrack()
	 * @see #getTrackPlan()
	 * @generated
	 */
	EReference getTrackPlan_NewTrack();

	/**
	 * Returns the meta object for the containment reference '{@link uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getNewPoint <em>New Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>New Point</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getNewPoint()
	 * @see #getTrackPlan()
	 * @generated
	 */
	EReference getTrackPlan_NewPoint();

	/**
	 * Returns the meta object for the containment reference '{@link uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getNewCrossing <em>New Crossing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>New Crossing</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getNewCrossing()
	 * @see #getTrackPlan()
	 * @generated
	 */
	EReference getTrackPlan_NewCrossing();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getSubTrackPlans <em>Sub Track Plans</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sub Track Plans</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getSubTrackPlans()
	 * @see #getTrackPlan()
	 * @generated
	 */
	EReference getTrackPlan_SubTrackPlans();

	/**
	 * Returns the meta object for the reference '{@link uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getSelectedSubTrackPlan <em>Selected Sub Track Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Selected Sub Track Plan</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getSelectedSubTrackPlan()
	 * @see #getTrackPlan()
	 * @generated
	 */
	EReference getTrackPlan_SelectedSubTrackPlan();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#isOverlapped <em>Overlapped</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Overlapped</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#isOverlapped()
	 * @see #getTrackPlan()
	 * @generated
	 */
	EAttribute getTrackPlan_Overlapped();

	/**
	 * Returns the meta object for class '{@link uk.ac.swanseacoventry.cmt.ontrack.Unit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unit</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Unit
	 * @generated
	 */
	EClass getUnit();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.swanseacoventry.cmt.ontrack.Unit#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see uk.ac.swanseacoventry.cmt.ontrack.Unit#getName()
	 * @see #getUnit()
	 * @generated
	 */
	EAttribute getUnit_Name();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	OntrackFactory getOntrackFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.ConnectorImpl <em>Connector</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.ConnectorImpl
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getConnector()
		 * @generated
		 */
		EClass CONNECTOR = eINSTANCE.getConnector();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR__ID = eINSTANCE.getConnector_Id();

		/**
		 * The meta object literal for the '<em><b>Track1s</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR__TRACK1S = eINSTANCE.getConnector_Track1s();

		/**
		 * The meta object literal for the '<em><b>Track2s</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR__TRACK2S = eINSTANCE.getConnector_Track2s();

		/**
		 * The meta object literal for the '<em><b>Signals</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR__SIGNALS = eINSTANCE.getConnector_Signals();

		/**
		 * The meta object literal for the '<em><b>Terminal</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR__TERMINAL = eINSTANCE.getConnector_Terminal();

		/**
		 * The meta object literal for the '<em><b>Entrances</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR__ENTRANCES = eINSTANCE.getConnector_Entrances();

		/**
		 * The meta object literal for the '<em><b>Exits</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR__EXITS = eINSTANCE.getConnector_Exits();

		/**
		 * The meta object literal for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.ControlTableItemImpl <em>Control Table Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.ControlTableItemImpl
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getControlTableItem()
		 * @generated
		 */
		EClass CONTROL_TABLE_ITEM = eINSTANCE.getControlTableItem();

		/**
		 * The meta object literal for the '<em><b>Signal</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTROL_TABLE_ITEM__SIGNAL = eINSTANCE.getControlTableItem_Signal();

		/**
		 * The meta object literal for the '<em><b>Route</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTROL_TABLE_ITEM__ROUTE = eINSTANCE.getControlTableItem_Route();

		/**
		 * The meta object literal for the '<em><b>Normals</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTROL_TABLE_ITEM__NORMALS = eINSTANCE.getControlTableItem_Normals();

		/**
		 * The meta object literal for the '<em><b>Reverses</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTROL_TABLE_ITEM__REVERSES = eINSTANCE.getControlTableItem_Reverses();

		/**
		 * The meta object literal for the '<em><b>Clears</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTROL_TABLE_ITEM__CLEARS = eINSTANCE.getControlTableItem_Clears();

		/**
		 * The meta object literal for the '<em><b>Directions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTROL_TABLE_ITEM__DIRECTIONS = eINSTANCE.getControlTableItem_Directions();

		/**
		 * The meta object literal for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.CrossingImpl <em>Crossing</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.CrossingImpl
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getCrossing()
		 * @generated
		 */
		EClass CROSSING = eINSTANCE.getCrossing();

		/**
		 * The meta object literal for the '<em><b>Track1</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CROSSING__TRACK1 = eINSTANCE.getCrossing_Track1();

		/**
		 * The meta object literal for the '<em><b>Track2</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CROSSING__TRACK2 = eINSTANCE.getCrossing_Track2();

		/**
		 * The meta object literal for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.DirectedTrackImpl <em>Directed Track</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.DirectedTrackImpl
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getDirectedTrack()
		 * @generated
		 */
		EClass DIRECTED_TRACK = eINSTANCE.getDirectedTrack();

		/**
		 * The meta object literal for the '<em><b>Track</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIRECTED_TRACK__TRACK = eINSTANCE.getDirectedTrack_Track();

		/**
		 * The meta object literal for the '<em><b>Connector</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIRECTED_TRACK__CONNECTOR = eINSTANCE.getDirectedTrack_Connector();

		/**
		 * The meta object literal for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.EntranceImpl <em>Entrance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.EntranceImpl
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getEntrance()
		 * @generated
		 */
		EClass ENTRANCE = eINSTANCE.getEntrance();

		/**
		 * The meta object literal for the '<em><b>Connector</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTRANCE__CONNECTOR = eINSTANCE.getEntrance_Connector();

		/**
		 * The meta object literal for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.ExitImpl <em>Exit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.ExitImpl
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getExit()
		 * @generated
		 */
		EClass EXIT = eINSTANCE.getExit();

		/**
		 * The meta object literal for the '<em><b>Connector</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXIT__CONNECTOR = eINSTANCE.getExit_Connector();

		/**
		 * The meta object literal for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.NewCrossingImpl <em>New Crossing</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.NewCrossingImpl
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getNewCrossing()
		 * @generated
		 */
		EClass NEW_CROSSING = eINSTANCE.getNewCrossing();

		/**
		 * The meta object literal for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.NewPointImpl <em>New Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.NewPointImpl
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getNewPoint()
		 * @generated
		 */
		EClass NEW_POINT = eINSTANCE.getNewPoint();

		/**
		 * The meta object literal for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.NewTrackImpl <em>New Track</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.NewTrackImpl
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getNewTrack()
		 * @generated
		 */
		EClass NEW_TRACK = eINSTANCE.getNewTrack();

		/**
		 * The meta object literal for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.PointImpl <em>Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.PointImpl
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getPoint()
		 * @generated
		 */
		EClass POINT = eINSTANCE.getPoint();

		/**
		 * The meta object literal for the '<em><b>Normal Track</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POINT__NORMAL_TRACK = eINSTANCE.getPoint_NormalTrack();

		/**
		 * The meta object literal for the '<em><b>Reverse Track</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POINT__REVERSE_TRACK = eINSTANCE.getPoint_ReverseTrack();

		/**
		 * The meta object literal for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.ReleaseTableItemImpl <em>Release Table Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.ReleaseTableItemImpl
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getReleaseTableItem()
		 * @generated
		 */
		EClass RELEASE_TABLE_ITEM = eINSTANCE.getReleaseTableItem();

		/**
		 * The meta object literal for the '<em><b>Route</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELEASE_TABLE_ITEM__ROUTE = eINSTANCE.getReleaseTableItem_Route();

		/**
		 * The meta object literal for the '<em><b>Point</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELEASE_TABLE_ITEM__POINT = eINSTANCE.getReleaseTableItem_Point();

		/**
		 * The meta object literal for the '<em><b>Unoccupied Track</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELEASE_TABLE_ITEM__UNOCCUPIED_TRACK = eINSTANCE.getReleaseTableItem_UnoccupiedTrack();

		/**
		 * The meta object literal for the '<em><b>Occupied Track</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELEASE_TABLE_ITEM__OCCUPIED_TRACK = eINSTANCE.getReleaseTableItem_OccupiedTrack();

		/**
		 * The meta object literal for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.SignalImpl <em>Signal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.SignalImpl
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getSignal()
		 * @generated
		 */
		EClass SIGNAL = eINSTANCE.getSignal();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIGNAL__NAME = eINSTANCE.getSignal_Name();

		/**
		 * The meta object literal for the '<em><b>Track</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIGNAL__TRACK = eINSTANCE.getSignal_Track();

		/**
		 * The meta object literal for the '<em><b>Connector</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIGNAL__CONNECTOR = eINSTANCE.getSignal_Connector();

		/**
		 * The meta object literal for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.SubTrackPlanImpl <em>Sub Track Plan</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.SubTrackPlanImpl
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getSubTrackPlan()
		 * @generated
		 */
		EClass SUB_TRACK_PLAN = eINSTANCE.getSubTrackPlan();

		/**
		 * The meta object literal for the '<em><b>Criticals</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUB_TRACK_PLAN__CRITICALS = eINSTANCE.getSubTrackPlan_Criticals();

		/**
		 * The meta object literal for the '<em><b>Tracks</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUB_TRACK_PLAN__TRACKS = eINSTANCE.getSubTrackPlan_Tracks();

		/**
		 * The meta object literal for the '<em><b>Connectors</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUB_TRACK_PLAN__CONNECTORS = eINSTANCE.getSubTrackPlan_Connectors();

		/**
		 * The meta object literal for the '<em><b>Points</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUB_TRACK_PLAN__POINTS = eINSTANCE.getSubTrackPlan_Points();

		/**
		 * The meta object literal for the '<em><b>Crossings</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUB_TRACK_PLAN__CROSSINGS = eINSTANCE.getSubTrackPlan_Crossings();

		/**
		 * The meta object literal for the '<em><b>Signals</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUB_TRACK_PLAN__SIGNALS = eINSTANCE.getSubTrackPlan_Signals();

		/**
		 * The meta object literal for the '<em><b>Entrances</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUB_TRACK_PLAN__ENTRANCES = eINSTANCE.getSubTrackPlan_Entrances();

		/**
		 * The meta object literal for the '<em><b>Exits</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUB_TRACK_PLAN__EXITS = eINSTANCE.getSubTrackPlan_Exits();

		/**
		 * The meta object literal for the '<em><b>Terminals</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUB_TRACK_PLAN__TERMINALS = eINSTANCE.getSubTrackPlan_Terminals();

		/**
		 * The meta object literal for the '<em><b>Topo Routes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUB_TRACK_PLAN__TOPO_ROUTES = eINSTANCE.getSubTrackPlan_TopoRoutes();

		/**
		 * The meta object literal for the '<em><b>Control Table</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUB_TRACK_PLAN__CONTROL_TABLE = eINSTANCE.getSubTrackPlan_ControlTable();

		/**
		 * The meta object literal for the '<em><b>Release Table</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUB_TRACK_PLAN__RELEASE_TABLE = eINSTANCE.getSubTrackPlan_ReleaseTable();

		/**
		 * The meta object literal for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TerminalImpl <em>Terminal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.TerminalImpl
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getTerminal()
		 * @generated
		 */
		EClass TERMINAL = eINSTANCE.getTerminal();

		/**
		 * The meta object literal for the '<em><b>Connector</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TERMINAL__CONNECTOR = eINSTANCE.getTerminal_Connector();

		/**
		 * The meta object literal for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TopoRouteImpl <em>Topo Route</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.TopoRouteImpl
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getTopoRoute()
		 * @generated
		 */
		EClass TOPO_ROUTE = eINSTANCE.getTopoRoute();

		/**
		 * The meta object literal for the '<em><b>Names</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOPO_ROUTE__NAMES = eINSTANCE.getTopoRoute_Names();

		/**
		 * The meta object literal for the '<em><b>Directed Tracks</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOPO_ROUTE__DIRECTED_TRACKS = eINSTANCE.getTopoRoute_DirectedTracks();

		/**
		 * The meta object literal for the '<em><b>Start Signal</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOPO_ROUTE__START_SIGNAL = eINSTANCE.getTopoRoute_StartSignal();

		/**
		 * The meta object literal for the '<em><b>End Signal</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOPO_ROUTE__END_SIGNAL = eINSTANCE.getTopoRoute_EndSignal();

		/**
		 * The meta object literal for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TrackImpl <em>Track</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.TrackImpl
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getTrack()
		 * @generated
		 */
		EClass TRACK = eINSTANCE.getTrack();

		/**
		 * The meta object literal for the '<em><b>C1</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACK__C1 = eINSTANCE.getTrack_C1();

		/**
		 * The meta object literal for the '<em><b>C2</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACK__C2 = eINSTANCE.getTrack_C2();

		/**
		 * The meta object literal for the '<em><b>Point Reverse</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACK__POINT_REVERSE = eINSTANCE.getTrack_PointReverse();

		/**
		 * The meta object literal for the '<em><b>Point Normal</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACK__POINT_NORMAL = eINSTANCE.getTrack_PointNormal();

		/**
		 * The meta object literal for the '<em><b>Crossing2</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACK__CROSSING2 = eINSTANCE.getTrack_Crossing2();

		/**
		 * The meta object literal for the '<em><b>Crossing1</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACK__CROSSING1 = eINSTANCE.getTrack_Crossing1();

		/**
		 * The meta object literal for the '<em><b>Directed Tracks</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACK__DIRECTED_TRACKS = eINSTANCE.getTrack_DirectedTracks();

		/**
		 * The meta object literal for the '<em><b>Signals</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACK__SIGNALS = eINSTANCE.getTrack_Signals();

		/**
		 * The meta object literal for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TrackPlanImpl <em>Track Plan</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.TrackPlanImpl
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getTrackPlan()
		 * @generated
		 */
		EClass TRACK_PLAN = eINSTANCE.getTrackPlan();

		/**
		 * The meta object literal for the '<em><b>Tracks</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACK_PLAN__TRACKS = eINSTANCE.getTrackPlan_Tracks();

		/**
		 * The meta object literal for the '<em><b>Connectors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACK_PLAN__CONNECTORS = eINSTANCE.getTrackPlan_Connectors();

		/**
		 * The meta object literal for the '<em><b>Points</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACK_PLAN__POINTS = eINSTANCE.getTrackPlan_Points();

		/**
		 * The meta object literal for the '<em><b>Crossings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACK_PLAN__CROSSINGS = eINSTANCE.getTrackPlan_Crossings();

		/**
		 * The meta object literal for the '<em><b>Signals</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACK_PLAN__SIGNALS = eINSTANCE.getTrackPlan_Signals();

		/**
		 * The meta object literal for the '<em><b>Entrances</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACK_PLAN__ENTRANCES = eINSTANCE.getTrackPlan_Entrances();

		/**
		 * The meta object literal for the '<em><b>Exits</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACK_PLAN__EXITS = eINSTANCE.getTrackPlan_Exits();

		/**
		 * The meta object literal for the '<em><b>Terminals</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACK_PLAN__TERMINALS = eINSTANCE.getTrackPlan_Terminals();

		/**
		 * The meta object literal for the '<em><b>Topo Routes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACK_PLAN__TOPO_ROUTES = eINSTANCE.getTrackPlan_TopoRoutes();

		/**
		 * The meta object literal for the '<em><b>Control Table</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACK_PLAN__CONTROL_TABLE = eINSTANCE.getTrackPlan_ControlTable();

		/**
		 * The meta object literal for the '<em><b>Release Table</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACK_PLAN__RELEASE_TABLE = eINSTANCE.getTrackPlan_ReleaseTable();

		/**
		 * The meta object literal for the '<em><b>New Track</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACK_PLAN__NEW_TRACK = eINSTANCE.getTrackPlan_NewTrack();

		/**
		 * The meta object literal for the '<em><b>New Point</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACK_PLAN__NEW_POINT = eINSTANCE.getTrackPlan_NewPoint();

		/**
		 * The meta object literal for the '<em><b>New Crossing</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACK_PLAN__NEW_CROSSING = eINSTANCE.getTrackPlan_NewCrossing();

		/**
		 * The meta object literal for the '<em><b>Sub Track Plans</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACK_PLAN__SUB_TRACK_PLANS = eINSTANCE.getTrackPlan_SubTrackPlans();

		/**
		 * The meta object literal for the '<em><b>Selected Sub Track Plan</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACK_PLAN__SELECTED_SUB_TRACK_PLAN = eINSTANCE.getTrackPlan_SelectedSubTrackPlan();

		/**
		 * The meta object literal for the '<em><b>Overlapped</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRACK_PLAN__OVERLAPPED = eINSTANCE.getTrackPlan_Overlapped();

		/**
		 * The meta object literal for the '{@link uk.ac.swanseacoventry.cmt.ontrack.impl.UnitImpl <em>Unit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.UnitImpl
		 * @see uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackPackageImpl#getUnit()
		 * @generated
		 */
		EClass UNIT = eINSTANCE.getUnit();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNIT__NAME = eINSTANCE.getUnit_Name();

	}

} //OntrackPackage
