/**
 */
package uk.ac.swanseacoventry.cmt.ontrack.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import uk.ac.swanseacoventry.cmt.ontrack.Connector;
import uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem;
import uk.ac.swanseacoventry.cmt.ontrack.Crossing;
import uk.ac.swanseacoventry.cmt.ontrack.Entrance;
import uk.ac.swanseacoventry.cmt.ontrack.Exit;
import uk.ac.swanseacoventry.cmt.ontrack.OntrackPackage;
import uk.ac.swanseacoventry.cmt.ontrack.Point;
import uk.ac.swanseacoventry.cmt.ontrack.ReleaseTableItem;
import uk.ac.swanseacoventry.cmt.ontrack.Signal;
import uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan;
import uk.ac.swanseacoventry.cmt.ontrack.Terminal;
import uk.ac.swanseacoventry.cmt.ontrack.TopoRoute;
import uk.ac.swanseacoventry.cmt.ontrack.Track;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sub Track Plan</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.SubTrackPlanImpl#getCriticals <em>Criticals</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.SubTrackPlanImpl#getTracks <em>Tracks</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.SubTrackPlanImpl#getConnectors <em>Connectors</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.SubTrackPlanImpl#getPoints <em>Points</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.SubTrackPlanImpl#getCrossings <em>Crossings</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.SubTrackPlanImpl#getSignals <em>Signals</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.SubTrackPlanImpl#getEntrances <em>Entrances</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.SubTrackPlanImpl#getExits <em>Exits</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.SubTrackPlanImpl#getTerminals <em>Terminals</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.SubTrackPlanImpl#getTopoRoutes <em>Topo Routes</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.SubTrackPlanImpl#getControlTable <em>Control Table</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.SubTrackPlanImpl#getReleaseTable <em>Release Table</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SubTrackPlanImpl extends MinimalEObjectImpl.Container implements SubTrackPlan {
	/**
	 * The cached value of the '{@link #getCriticals() <em>Criticals</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCriticals()
	 * @generated
	 * @ordered
	 */
	protected EList<Track> criticals;

	/**
	 * The cached value of the '{@link #getTracks() <em>Tracks</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTracks()
	 * @generated
	 * @ordered
	 */
	protected EList<Track> tracks;

	/**
	 * The cached value of the '{@link #getConnectors() <em>Connectors</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectors()
	 * @generated
	 * @ordered
	 */
	protected EList<Connector> connectors;

	/**
	 * The cached value of the '{@link #getPoints() <em>Points</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPoints()
	 * @generated
	 * @ordered
	 */
	protected EList<Point> points;

	/**
	 * The cached value of the '{@link #getCrossings() <em>Crossings</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCrossings()
	 * @generated
	 * @ordered
	 */
	protected EList<Crossing> crossings;

	/**
	 * The cached value of the '{@link #getSignals() <em>Signals</em>}' reference list.
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
	 * The cached value of the '{@link #getTerminals() <em>Terminals</em>}' reference list.
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SubTrackPlanImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OntrackPackage.Literals.SUB_TRACK_PLAN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Track> getTracks() {
		if (tracks == null) {
			tracks = new EObjectResolvingEList<Track>(Track.class, this, OntrackPackage.SUB_TRACK_PLAN__TRACKS);
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
			connectors = new EObjectResolvingEList<Connector>(Connector.class, this, OntrackPackage.SUB_TRACK_PLAN__CONNECTORS);
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
			points = new EObjectResolvingEList<Point>(Point.class, this, OntrackPackage.SUB_TRACK_PLAN__POINTS);
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
			crossings = new EObjectResolvingEList<Crossing>(Crossing.class, this, OntrackPackage.SUB_TRACK_PLAN__CROSSINGS);
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
			signals = new EObjectResolvingEList<Signal>(Signal.class, this, OntrackPackage.SUB_TRACK_PLAN__SIGNALS);
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
			entrances = new EObjectContainmentEList<Entrance>(Entrance.class, this, OntrackPackage.SUB_TRACK_PLAN__ENTRANCES);
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
			exits = new EObjectContainmentEList<Exit>(Exit.class, this, OntrackPackage.SUB_TRACK_PLAN__EXITS);
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
			terminals = new EObjectResolvingEList<Terminal>(Terminal.class, this, OntrackPackage.SUB_TRACK_PLAN__TERMINALS);
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
			topoRoutes = new EObjectContainmentEList<TopoRoute>(TopoRoute.class, this, OntrackPackage.SUB_TRACK_PLAN__TOPO_ROUTES);
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
			controlTable = new EObjectContainmentEList<ControlTableItem>(ControlTableItem.class, this, OntrackPackage.SUB_TRACK_PLAN__CONTROL_TABLE);
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
			releaseTable = new EObjectContainmentEList<ReleaseTableItem>(ReleaseTableItem.class, this, OntrackPackage.SUB_TRACK_PLAN__RELEASE_TABLE);
		}
		return releaseTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Track> getCriticals() {
		if (criticals == null) {
			criticals = new EObjectResolvingEList<Track>(Track.class, this, OntrackPackage.SUB_TRACK_PLAN__CRITICALS);
		}
		return criticals;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OntrackPackage.SUB_TRACK_PLAN__ENTRANCES:
				return ((InternalEList<?>)getEntrances()).basicRemove(otherEnd, msgs);
			case OntrackPackage.SUB_TRACK_PLAN__EXITS:
				return ((InternalEList<?>)getExits()).basicRemove(otherEnd, msgs);
			case OntrackPackage.SUB_TRACK_PLAN__TOPO_ROUTES:
				return ((InternalEList<?>)getTopoRoutes()).basicRemove(otherEnd, msgs);
			case OntrackPackage.SUB_TRACK_PLAN__CONTROL_TABLE:
				return ((InternalEList<?>)getControlTable()).basicRemove(otherEnd, msgs);
			case OntrackPackage.SUB_TRACK_PLAN__RELEASE_TABLE:
				return ((InternalEList<?>)getReleaseTable()).basicRemove(otherEnd, msgs);
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
			case OntrackPackage.SUB_TRACK_PLAN__CRITICALS:
				return getCriticals();
			case OntrackPackage.SUB_TRACK_PLAN__TRACKS:
				return getTracks();
			case OntrackPackage.SUB_TRACK_PLAN__CONNECTORS:
				return getConnectors();
			case OntrackPackage.SUB_TRACK_PLAN__POINTS:
				return getPoints();
			case OntrackPackage.SUB_TRACK_PLAN__CROSSINGS:
				return getCrossings();
			case OntrackPackage.SUB_TRACK_PLAN__SIGNALS:
				return getSignals();
			case OntrackPackage.SUB_TRACK_PLAN__ENTRANCES:
				return getEntrances();
			case OntrackPackage.SUB_TRACK_PLAN__EXITS:
				return getExits();
			case OntrackPackage.SUB_TRACK_PLAN__TERMINALS:
				return getTerminals();
			case OntrackPackage.SUB_TRACK_PLAN__TOPO_ROUTES:
				return getTopoRoutes();
			case OntrackPackage.SUB_TRACK_PLAN__CONTROL_TABLE:
				return getControlTable();
			case OntrackPackage.SUB_TRACK_PLAN__RELEASE_TABLE:
				return getReleaseTable();
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
			case OntrackPackage.SUB_TRACK_PLAN__CRITICALS:
				getCriticals().clear();
				getCriticals().addAll((Collection<? extends Track>)newValue);
				return;
			case OntrackPackage.SUB_TRACK_PLAN__TRACKS:
				getTracks().clear();
				getTracks().addAll((Collection<? extends Track>)newValue);
				return;
			case OntrackPackage.SUB_TRACK_PLAN__CONNECTORS:
				getConnectors().clear();
				getConnectors().addAll((Collection<? extends Connector>)newValue);
				return;
			case OntrackPackage.SUB_TRACK_PLAN__POINTS:
				getPoints().clear();
				getPoints().addAll((Collection<? extends Point>)newValue);
				return;
			case OntrackPackage.SUB_TRACK_PLAN__CROSSINGS:
				getCrossings().clear();
				getCrossings().addAll((Collection<? extends Crossing>)newValue);
				return;
			case OntrackPackage.SUB_TRACK_PLAN__SIGNALS:
				getSignals().clear();
				getSignals().addAll((Collection<? extends Signal>)newValue);
				return;
			case OntrackPackage.SUB_TRACK_PLAN__ENTRANCES:
				getEntrances().clear();
				getEntrances().addAll((Collection<? extends Entrance>)newValue);
				return;
			case OntrackPackage.SUB_TRACK_PLAN__EXITS:
				getExits().clear();
				getExits().addAll((Collection<? extends Exit>)newValue);
				return;
			case OntrackPackage.SUB_TRACK_PLAN__TERMINALS:
				getTerminals().clear();
				getTerminals().addAll((Collection<? extends Terminal>)newValue);
				return;
			case OntrackPackage.SUB_TRACK_PLAN__TOPO_ROUTES:
				getTopoRoutes().clear();
				getTopoRoutes().addAll((Collection<? extends TopoRoute>)newValue);
				return;
			case OntrackPackage.SUB_TRACK_PLAN__CONTROL_TABLE:
				getControlTable().clear();
				getControlTable().addAll((Collection<? extends ControlTableItem>)newValue);
				return;
			case OntrackPackage.SUB_TRACK_PLAN__RELEASE_TABLE:
				getReleaseTable().clear();
				getReleaseTable().addAll((Collection<? extends ReleaseTableItem>)newValue);
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
			case OntrackPackage.SUB_TRACK_PLAN__CRITICALS:
				getCriticals().clear();
				return;
			case OntrackPackage.SUB_TRACK_PLAN__TRACKS:
				getTracks().clear();
				return;
			case OntrackPackage.SUB_TRACK_PLAN__CONNECTORS:
				getConnectors().clear();
				return;
			case OntrackPackage.SUB_TRACK_PLAN__POINTS:
				getPoints().clear();
				return;
			case OntrackPackage.SUB_TRACK_PLAN__CROSSINGS:
				getCrossings().clear();
				return;
			case OntrackPackage.SUB_TRACK_PLAN__SIGNALS:
				getSignals().clear();
				return;
			case OntrackPackage.SUB_TRACK_PLAN__ENTRANCES:
				getEntrances().clear();
				return;
			case OntrackPackage.SUB_TRACK_PLAN__EXITS:
				getExits().clear();
				return;
			case OntrackPackage.SUB_TRACK_PLAN__TERMINALS:
				getTerminals().clear();
				return;
			case OntrackPackage.SUB_TRACK_PLAN__TOPO_ROUTES:
				getTopoRoutes().clear();
				return;
			case OntrackPackage.SUB_TRACK_PLAN__CONTROL_TABLE:
				getControlTable().clear();
				return;
			case OntrackPackage.SUB_TRACK_PLAN__RELEASE_TABLE:
				getReleaseTable().clear();
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
			case OntrackPackage.SUB_TRACK_PLAN__CRITICALS:
				return criticals != null && !criticals.isEmpty();
			case OntrackPackage.SUB_TRACK_PLAN__TRACKS:
				return tracks != null && !tracks.isEmpty();
			case OntrackPackage.SUB_TRACK_PLAN__CONNECTORS:
				return connectors != null && !connectors.isEmpty();
			case OntrackPackage.SUB_TRACK_PLAN__POINTS:
				return points != null && !points.isEmpty();
			case OntrackPackage.SUB_TRACK_PLAN__CROSSINGS:
				return crossings != null && !crossings.isEmpty();
			case OntrackPackage.SUB_TRACK_PLAN__SIGNALS:
				return signals != null && !signals.isEmpty();
			case OntrackPackage.SUB_TRACK_PLAN__ENTRANCES:
				return entrances != null && !entrances.isEmpty();
			case OntrackPackage.SUB_TRACK_PLAN__EXITS:
				return exits != null && !exits.isEmpty();
			case OntrackPackage.SUB_TRACK_PLAN__TERMINALS:
				return terminals != null && !terminals.isEmpty();
			case OntrackPackage.SUB_TRACK_PLAN__TOPO_ROUTES:
				return topoRoutes != null && !topoRoutes.isEmpty();
			case OntrackPackage.SUB_TRACK_PLAN__CONTROL_TABLE:
				return controlTable != null && !controlTable.isEmpty();
			case OntrackPackage.SUB_TRACK_PLAN__RELEASE_TABLE:
				return releaseTable != null && !releaseTable.isEmpty();
		}
		return super.eIsSet(featureID);
	}
	
	public String getName(){
		ArrayList<String> ss = new ArrayList<String>();
		for(Track t : getCriticals()){
			ss.add(t.getName());
		}
		return String.join(",", ss);
	}
	public String getTrackList(){
		ArrayList<String> ss = new ArrayList<String>();
		for(Track t : getTracks()){
			if (t.getPointReverse()!=null || t.getCrossing2()!=null) continue;
			ss.add(t.getName());
		}
		return String.join(",", ss);
	}
	public String getPointList(){
		ArrayList<String> ss = new ArrayList<String>();
		for(Point t : getPoints()){
			ss.add(t.getName());
		}
		return String.join(",", ss);
	}
	public String getSignalList(){
		ArrayList<String> ss = new ArrayList<String>();
		for(Signal t : getSignals()){
			ss.add(t.getName());
		}
		return String.join(",", ss);
	}
	public String getRouteList(){
		ArrayList<String> ss = new ArrayList<String>();
		for(ControlTableItem t : getControlTable()){
			ss.add(t.getSignal()!=null ? t.getRoute() : "("+t.getRoute()+")");
		}
		return String.join(",", ss);
	}


} //SubTrackPlanImpl
