/**
 */
package uk.ac.swanseacoventry.cmt.ontrack.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import uk.ac.swanseacoventry.cmt.ontrack.Connector;
import uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem;
import uk.ac.swanseacoventry.cmt.ontrack.Crossing;
import uk.ac.swanseacoventry.cmt.ontrack.Entrance;
import uk.ac.swanseacoventry.cmt.ontrack.Exit;
import uk.ac.swanseacoventry.cmt.ontrack.NewCrossing;
import uk.ac.swanseacoventry.cmt.ontrack.NewPoint;
import uk.ac.swanseacoventry.cmt.ontrack.NewTrack;
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
 * An implementation of the model object '<em><b>Track Plan</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TrackPlanImpl#getTracks <em>Tracks</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TrackPlanImpl#getConnectors <em>Connectors</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TrackPlanImpl#getPoints <em>Points</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TrackPlanImpl#getCrossings <em>Crossings</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TrackPlanImpl#getSignals <em>Signals</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TrackPlanImpl#getEntrances <em>Entrances</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TrackPlanImpl#getExits <em>Exits</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TrackPlanImpl#getTerminals <em>Terminals</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TrackPlanImpl#getTopoRoutes <em>Topo Routes</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TrackPlanImpl#getControlTable <em>Control Table</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TrackPlanImpl#getReleaseTable <em>Release Table</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TrackPlanImpl#getNewTrack <em>New Track</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TrackPlanImpl#getNewPoint <em>New Point</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TrackPlanImpl#getNewCrossing <em>New Crossing</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TrackPlanImpl#getSubTrackPlans <em>Sub Track Plans</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TrackPlanImpl#getSelectedSubTrackPlan <em>Selected Sub Track Plan</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TrackPlanImpl#isOverlapped <em>Overlapped</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TrackPlanImpl extends MinimalEObjectImpl.Container implements TrackPlan {
	/**
	 * The cached value of the '{@link #getTracks() <em>Tracks</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTracks()
	 * @generated
	 * @ordered
	 */
	protected EList<Track> tracks;

	/**
	 * The cached value of the '{@link #getConnectors() <em>Connectors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectors()
	 * @generated
	 * @ordered
	 */
	protected EList<Connector> connectors;

	/**
	 * The cached value of the '{@link #getPoints() <em>Points</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPoints()
	 * @generated
	 * @ordered
	 */
	protected EList<Point> points;

	/**
	 * The cached value of the '{@link #getCrossings() <em>Crossings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCrossings()
	 * @generated
	 * @ordered
	 */
	protected EList<Crossing> crossings;

	/**
	 * The cached value of the '{@link #getSignals() <em>Signals</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignals()
	 * @generated
	 * @ordered
	 */
	protected EList<Signal> signals;

	/**
	 * The cached value of the '{@link #getEntrances() <em>Entrances</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntrances()
	 * @generated
	 * @ordered
	 */
	protected EList<Entrance> entrances;

	/**
	 * The cached value of the '{@link #getExits() <em>Exits</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExits()
	 * @generated
	 * @ordered
	 */
	protected EList<Exit> exits;

	/**
	 * The cached value of the '{@link #getTerminals() <em>Terminals</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTerminals()
	 * @generated
	 * @ordered
	 */
	protected EList<Terminal> terminals;

	/**
	 * The cached value of the '{@link #getTopoRoutes() <em>Topo Routes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTopoRoutes()
	 * @generated
	 * @ordered
	 */
	protected EList<TopoRoute> topoRoutes;

	/**
	 * The cached value of the '{@link #getControlTable() <em>Control Table</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getControlTable()
	 * @generated
	 * @ordered
	 */
	protected EList<ControlTableItem> controlTable;

	/**
	 * The cached value of the '{@link #getReleaseTable() <em>Release Table</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReleaseTable()
	 * @generated
	 * @ordered
	 */
	protected EList<ReleaseTableItem> releaseTable;

	/**
	 * The cached value of the '{@link #getNewTrack() <em>New Track</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNewTrack()
	 * @generated
	 * @ordered
	 */
	protected NewTrack newTrack;

	/**
	 * The cached value of the '{@link #getNewPoint() <em>New Point</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNewPoint()
	 * @generated
	 * @ordered
	 */
	protected NewPoint newPoint;

	/**
	 * The cached value of the '{@link #getNewCrossing() <em>New Crossing</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNewCrossing()
	 * @generated
	 * @ordered
	 */
	protected NewCrossing newCrossing;

	/**
	 * The cached value of the '{@link #getSubTrackPlans() <em>Sub Track Plans</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubTrackPlans()
	 * @generated
	 * @ordered
	 */
	protected EList<SubTrackPlan> subTrackPlans;

	/**
	 * The cached value of the '{@link #getSelectedSubTrackPlan() <em>Selected Sub Track Plan</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSelectedSubTrackPlan()
	 * @generated
	 * @ordered
	 */
	protected SubTrackPlan selectedSubTrackPlan;

	/**
	 * The default value of the '{@link #isOverlapped() <em>Overlapped</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOverlapped()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OVERLAPPED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOverlapped() <em>Overlapped</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOverlapped()
	 * @generated
	 * @ordered
	 */
	protected boolean overlapped = OVERLAPPED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TrackPlanImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OntrackPackage.Literals.TRACK_PLAN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Track> getTracks() {
		if (tracks == null) {
			tracks = new EObjectContainmentEList<Track>(Track.class, this, OntrackPackage.TRACK_PLAN__TRACKS);
		}
		return tracks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Connector> getConnectors() {
		if (connectors == null) {
			connectors = new EObjectContainmentEList<Connector>(Connector.class, this, OntrackPackage.TRACK_PLAN__CONNECTORS);
		}
		return connectors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Point> getPoints() {
		if (points == null) {
			points = new EObjectContainmentEList<Point>(Point.class, this, OntrackPackage.TRACK_PLAN__POINTS);
		}
		return points;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Crossing> getCrossings() {
		if (crossings == null) {
			crossings = new EObjectContainmentEList<Crossing>(Crossing.class, this, OntrackPackage.TRACK_PLAN__CROSSINGS);
		}
		return crossings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Signal> getSignals() {
		if (signals == null) {
			signals = new EObjectContainmentEList<Signal>(Signal.class, this, OntrackPackage.TRACK_PLAN__SIGNALS);
		}
		return signals;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Entrance> getEntrances() {
		if (entrances == null) {
			entrances = new EObjectContainmentEList<Entrance>(Entrance.class, this, OntrackPackage.TRACK_PLAN__ENTRANCES);
		}
		return entrances;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Exit> getExits() {
		if (exits == null) {
			exits = new EObjectContainmentEList<Exit>(Exit.class, this, OntrackPackage.TRACK_PLAN__EXITS);
		}
		return exits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Terminal> getTerminals() {
		if (terminals == null) {
			terminals = new EObjectContainmentEList<Terminal>(Terminal.class, this, OntrackPackage.TRACK_PLAN__TERMINALS);
		}
		return terminals;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TopoRoute> getTopoRoutes() {
		if (topoRoutes == null) {
			topoRoutes = new EObjectContainmentEList<TopoRoute>(TopoRoute.class, this, OntrackPackage.TRACK_PLAN__TOPO_ROUTES);
		}
		return topoRoutes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ControlTableItem> getControlTable() {
		if (controlTable == null) {
			controlTable = new EObjectContainmentEList<ControlTableItem>(ControlTableItem.class, this, OntrackPackage.TRACK_PLAN__CONTROL_TABLE);
		}
		return controlTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ReleaseTableItem> getReleaseTable() {
		if (releaseTable == null) {
			releaseTable = new EObjectContainmentEList<ReleaseTableItem>(ReleaseTableItem.class, this, OntrackPackage.TRACK_PLAN__RELEASE_TABLE);
		}
		return releaseTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SubTrackPlan> getSubTrackPlans() {
		if (subTrackPlans == null) {
			subTrackPlans = new EObjectContainmentEList<SubTrackPlan>(SubTrackPlan.class, this, OntrackPackage.TRACK_PLAN__SUB_TRACK_PLANS);
		}
		return subTrackPlans;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NewTrack getNewTrack() {
		return newTrack;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNewTrack(NewTrack newNewTrack, NotificationChain msgs) {
		NewTrack oldNewTrack = newTrack;
		newTrack = newNewTrack;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OntrackPackage.TRACK_PLAN__NEW_TRACK, oldNewTrack, newNewTrack);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNewTrack(NewTrack newNewTrack) {
		if (newNewTrack != newTrack) {
			NotificationChain msgs = null;
			if (newTrack != null)
				msgs = ((InternalEObject)newTrack).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OntrackPackage.TRACK_PLAN__NEW_TRACK, null, msgs);
			if (newNewTrack != null)
				msgs = ((InternalEObject)newNewTrack).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OntrackPackage.TRACK_PLAN__NEW_TRACK, null, msgs);
			msgs = basicSetNewTrack(newNewTrack, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.TRACK_PLAN__NEW_TRACK, newNewTrack, newNewTrack));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NewPoint getNewPoint() {
		return newPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNewPoint(NewPoint newNewPoint, NotificationChain msgs) {
		NewPoint oldNewPoint = newPoint;
		newPoint = newNewPoint;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OntrackPackage.TRACK_PLAN__NEW_POINT, oldNewPoint, newNewPoint);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNewPoint(NewPoint newNewPoint) {
		if (newNewPoint != newPoint) {
			NotificationChain msgs = null;
			if (newPoint != null)
				msgs = ((InternalEObject)newPoint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OntrackPackage.TRACK_PLAN__NEW_POINT, null, msgs);
			if (newNewPoint != null)
				msgs = ((InternalEObject)newNewPoint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OntrackPackage.TRACK_PLAN__NEW_POINT, null, msgs);
			msgs = basicSetNewPoint(newNewPoint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.TRACK_PLAN__NEW_POINT, newNewPoint, newNewPoint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NewCrossing getNewCrossing() {
		return newCrossing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNewCrossing(NewCrossing newNewCrossing, NotificationChain msgs) {
		NewCrossing oldNewCrossing = newCrossing;
		newCrossing = newNewCrossing;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OntrackPackage.TRACK_PLAN__NEW_CROSSING, oldNewCrossing, newNewCrossing);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNewCrossing(NewCrossing newNewCrossing) {
		if (newNewCrossing != newCrossing) {
			NotificationChain msgs = null;
			if (newCrossing != null)
				msgs = ((InternalEObject)newCrossing).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OntrackPackage.TRACK_PLAN__NEW_CROSSING, null, msgs);
			if (newNewCrossing != null)
				msgs = ((InternalEObject)newNewCrossing).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OntrackPackage.TRACK_PLAN__NEW_CROSSING, null, msgs);
			msgs = basicSetNewCrossing(newNewCrossing, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.TRACK_PLAN__NEW_CROSSING, newNewCrossing, newNewCrossing));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SubTrackPlan getSelectedSubTrackPlan() {
		if (selectedSubTrackPlan != null && selectedSubTrackPlan.eIsProxy()) {
			InternalEObject oldSelectedSubTrackPlan = (InternalEObject)selectedSubTrackPlan;
			selectedSubTrackPlan = (SubTrackPlan)eResolveProxy(oldSelectedSubTrackPlan);
			if (selectedSubTrackPlan != oldSelectedSubTrackPlan) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OntrackPackage.TRACK_PLAN__SELECTED_SUB_TRACK_PLAN, oldSelectedSubTrackPlan, selectedSubTrackPlan));
			}
		}
		return selectedSubTrackPlan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SubTrackPlan basicGetSelectedSubTrackPlan() {
		return selectedSubTrackPlan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSelectedSubTrackPlan(SubTrackPlan newSelectedSubTrackPlan) {
		SubTrackPlan oldSelectedSubTrackPlan = selectedSubTrackPlan;
		selectedSubTrackPlan = newSelectedSubTrackPlan;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.TRACK_PLAN__SELECTED_SUB_TRACK_PLAN, oldSelectedSubTrackPlan, selectedSubTrackPlan));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOverlapped() {
		return overlapped;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOverlapped(boolean newOverlapped) {
		boolean oldOverlapped = overlapped;
		overlapped = newOverlapped;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.TRACK_PLAN__OVERLAPPED, oldOverlapped, overlapped));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OntrackPackage.TRACK_PLAN__TRACKS:
				return ((InternalEList<?>)getTracks()).basicRemove(otherEnd, msgs);
			case OntrackPackage.TRACK_PLAN__CONNECTORS:
				return ((InternalEList<?>)getConnectors()).basicRemove(otherEnd, msgs);
			case OntrackPackage.TRACK_PLAN__POINTS:
				return ((InternalEList<?>)getPoints()).basicRemove(otherEnd, msgs);
			case OntrackPackage.TRACK_PLAN__CROSSINGS:
				return ((InternalEList<?>)getCrossings()).basicRemove(otherEnd, msgs);
			case OntrackPackage.TRACK_PLAN__SIGNALS:
				return ((InternalEList<?>)getSignals()).basicRemove(otherEnd, msgs);
			case OntrackPackage.TRACK_PLAN__ENTRANCES:
				return ((InternalEList<?>)getEntrances()).basicRemove(otherEnd, msgs);
			case OntrackPackage.TRACK_PLAN__EXITS:
				return ((InternalEList<?>)getExits()).basicRemove(otherEnd, msgs);
			case OntrackPackage.TRACK_PLAN__TERMINALS:
				return ((InternalEList<?>)getTerminals()).basicRemove(otherEnd, msgs);
			case OntrackPackage.TRACK_PLAN__TOPO_ROUTES:
				return ((InternalEList<?>)getTopoRoutes()).basicRemove(otherEnd, msgs);
			case OntrackPackage.TRACK_PLAN__CONTROL_TABLE:
				return ((InternalEList<?>)getControlTable()).basicRemove(otherEnd, msgs);
			case OntrackPackage.TRACK_PLAN__RELEASE_TABLE:
				return ((InternalEList<?>)getReleaseTable()).basicRemove(otherEnd, msgs);
			case OntrackPackage.TRACK_PLAN__NEW_TRACK:
				return basicSetNewTrack(null, msgs);
			case OntrackPackage.TRACK_PLAN__NEW_POINT:
				return basicSetNewPoint(null, msgs);
			case OntrackPackage.TRACK_PLAN__NEW_CROSSING:
				return basicSetNewCrossing(null, msgs);
			case OntrackPackage.TRACK_PLAN__SUB_TRACK_PLANS:
				return ((InternalEList<?>)getSubTrackPlans()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OntrackPackage.TRACK_PLAN__TRACKS:
				return getTracks();
			case OntrackPackage.TRACK_PLAN__CONNECTORS:
				return getConnectors();
			case OntrackPackage.TRACK_PLAN__POINTS:
				return getPoints();
			case OntrackPackage.TRACK_PLAN__CROSSINGS:
				return getCrossings();
			case OntrackPackage.TRACK_PLAN__SIGNALS:
				return getSignals();
			case OntrackPackage.TRACK_PLAN__ENTRANCES:
				return getEntrances();
			case OntrackPackage.TRACK_PLAN__EXITS:
				return getExits();
			case OntrackPackage.TRACK_PLAN__TERMINALS:
				return getTerminals();
			case OntrackPackage.TRACK_PLAN__TOPO_ROUTES:
				return getTopoRoutes();
			case OntrackPackage.TRACK_PLAN__CONTROL_TABLE:
				return getControlTable();
			case OntrackPackage.TRACK_PLAN__RELEASE_TABLE:
				return getReleaseTable();
			case OntrackPackage.TRACK_PLAN__NEW_TRACK:
				return getNewTrack();
			case OntrackPackage.TRACK_PLAN__NEW_POINT:
				return getNewPoint();
			case OntrackPackage.TRACK_PLAN__NEW_CROSSING:
				return getNewCrossing();
			case OntrackPackage.TRACK_PLAN__SUB_TRACK_PLANS:
				return getSubTrackPlans();
			case OntrackPackage.TRACK_PLAN__SELECTED_SUB_TRACK_PLAN:
				if (resolve) return getSelectedSubTrackPlan();
				return basicGetSelectedSubTrackPlan();
			case OntrackPackage.TRACK_PLAN__OVERLAPPED:
				return isOverlapped();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case OntrackPackage.TRACK_PLAN__TRACKS:
				getTracks().clear();
				getTracks().addAll((Collection<? extends Track>)newValue);
				return;
			case OntrackPackage.TRACK_PLAN__CONNECTORS:
				getConnectors().clear();
				getConnectors().addAll((Collection<? extends Connector>)newValue);
				return;
			case OntrackPackage.TRACK_PLAN__POINTS:
				getPoints().clear();
				getPoints().addAll((Collection<? extends Point>)newValue);
				return;
			case OntrackPackage.TRACK_PLAN__CROSSINGS:
				getCrossings().clear();
				getCrossings().addAll((Collection<? extends Crossing>)newValue);
				return;
			case OntrackPackage.TRACK_PLAN__SIGNALS:
				getSignals().clear();
				getSignals().addAll((Collection<? extends Signal>)newValue);
				return;
			case OntrackPackage.TRACK_PLAN__ENTRANCES:
				getEntrances().clear();
				getEntrances().addAll((Collection<? extends Entrance>)newValue);
				return;
			case OntrackPackage.TRACK_PLAN__EXITS:
				getExits().clear();
				getExits().addAll((Collection<? extends Exit>)newValue);
				return;
			case OntrackPackage.TRACK_PLAN__TERMINALS:
				getTerminals().clear();
				getTerminals().addAll((Collection<? extends Terminal>)newValue);
				return;
			case OntrackPackage.TRACK_PLAN__TOPO_ROUTES:
				getTopoRoutes().clear();
				getTopoRoutes().addAll((Collection<? extends TopoRoute>)newValue);
				return;
			case OntrackPackage.TRACK_PLAN__CONTROL_TABLE:
				getControlTable().clear();
				getControlTable().addAll((Collection<? extends ControlTableItem>)newValue);
				return;
			case OntrackPackage.TRACK_PLAN__RELEASE_TABLE:
				getReleaseTable().clear();
				getReleaseTable().addAll((Collection<? extends ReleaseTableItem>)newValue);
				return;
			case OntrackPackage.TRACK_PLAN__NEW_TRACK:
				setNewTrack((NewTrack)newValue);
				return;
			case OntrackPackage.TRACK_PLAN__NEW_POINT:
				setNewPoint((NewPoint)newValue);
				return;
			case OntrackPackage.TRACK_PLAN__NEW_CROSSING:
				setNewCrossing((NewCrossing)newValue);
				return;
			case OntrackPackage.TRACK_PLAN__SUB_TRACK_PLANS:
				getSubTrackPlans().clear();
				getSubTrackPlans().addAll((Collection<? extends SubTrackPlan>)newValue);
				return;
			case OntrackPackage.TRACK_PLAN__SELECTED_SUB_TRACK_PLAN:
				setSelectedSubTrackPlan((SubTrackPlan)newValue);
				return;
			case OntrackPackage.TRACK_PLAN__OVERLAPPED:
				setOverlapped((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case OntrackPackage.TRACK_PLAN__TRACKS:
				getTracks().clear();
				return;
			case OntrackPackage.TRACK_PLAN__CONNECTORS:
				getConnectors().clear();
				return;
			case OntrackPackage.TRACK_PLAN__POINTS:
				getPoints().clear();
				return;
			case OntrackPackage.TRACK_PLAN__CROSSINGS:
				getCrossings().clear();
				return;
			case OntrackPackage.TRACK_PLAN__SIGNALS:
				getSignals().clear();
				return;
			case OntrackPackage.TRACK_PLAN__ENTRANCES:
				getEntrances().clear();
				return;
			case OntrackPackage.TRACK_PLAN__EXITS:
				getExits().clear();
				return;
			case OntrackPackage.TRACK_PLAN__TERMINALS:
				getTerminals().clear();
				return;
			case OntrackPackage.TRACK_PLAN__TOPO_ROUTES:
				getTopoRoutes().clear();
				return;
			case OntrackPackage.TRACK_PLAN__CONTROL_TABLE:
				getControlTable().clear();
				return;
			case OntrackPackage.TRACK_PLAN__RELEASE_TABLE:
				getReleaseTable().clear();
				return;
			case OntrackPackage.TRACK_PLAN__NEW_TRACK:
				setNewTrack((NewTrack)null);
				return;
			case OntrackPackage.TRACK_PLAN__NEW_POINT:
				setNewPoint((NewPoint)null);
				return;
			case OntrackPackage.TRACK_PLAN__NEW_CROSSING:
				setNewCrossing((NewCrossing)null);
				return;
			case OntrackPackage.TRACK_PLAN__SUB_TRACK_PLANS:
				getSubTrackPlans().clear();
				return;
			case OntrackPackage.TRACK_PLAN__SELECTED_SUB_TRACK_PLAN:
				setSelectedSubTrackPlan((SubTrackPlan)null);
				return;
			case OntrackPackage.TRACK_PLAN__OVERLAPPED:
				setOverlapped(OVERLAPPED_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case OntrackPackage.TRACK_PLAN__TRACKS:
				return tracks != null && !tracks.isEmpty();
			case OntrackPackage.TRACK_PLAN__CONNECTORS:
				return connectors != null && !connectors.isEmpty();
			case OntrackPackage.TRACK_PLAN__POINTS:
				return points != null && !points.isEmpty();
			case OntrackPackage.TRACK_PLAN__CROSSINGS:
				return crossings != null && !crossings.isEmpty();
			case OntrackPackage.TRACK_PLAN__SIGNALS:
				return signals != null && !signals.isEmpty();
			case OntrackPackage.TRACK_PLAN__ENTRANCES:
				return entrances != null && !entrances.isEmpty();
			case OntrackPackage.TRACK_PLAN__EXITS:
				return exits != null && !exits.isEmpty();
			case OntrackPackage.TRACK_PLAN__TERMINALS:
				return terminals != null && !terminals.isEmpty();
			case OntrackPackage.TRACK_PLAN__TOPO_ROUTES:
				return topoRoutes != null && !topoRoutes.isEmpty();
			case OntrackPackage.TRACK_PLAN__CONTROL_TABLE:
				return controlTable != null && !controlTable.isEmpty();
			case OntrackPackage.TRACK_PLAN__RELEASE_TABLE:
				return releaseTable != null && !releaseTable.isEmpty();
			case OntrackPackage.TRACK_PLAN__NEW_TRACK:
				return newTrack != null;
			case OntrackPackage.TRACK_PLAN__NEW_POINT:
				return newPoint != null;
			case OntrackPackage.TRACK_PLAN__NEW_CROSSING:
				return newCrossing != null;
			case OntrackPackage.TRACK_PLAN__SUB_TRACK_PLANS:
				return subTrackPlans != null && !subTrackPlans.isEmpty();
			case OntrackPackage.TRACK_PLAN__SELECTED_SUB_TRACK_PLAN:
				return selectedSubTrackPlan != null;
			case OntrackPackage.TRACK_PLAN__OVERLAPPED:
				return overlapped != OVERLAPPED_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (overlapped: ");
		result.append(overlapped);
		result.append(')');
		return result.toString();
	}

} //TrackPlanImpl
