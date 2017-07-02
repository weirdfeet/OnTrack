/**
 */
package uk.ac.swanseacoventry.cmt.ontrack.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import uk.ac.swanseacoventry.cmt.ontrack.Connector;
import uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem;
import uk.ac.swanseacoventry.cmt.ontrack.DirectedTrack;
import uk.ac.swanseacoventry.cmt.ontrack.OntrackPackage;
import uk.ac.swanseacoventry.cmt.ontrack.Point;
import uk.ac.swanseacoventry.cmt.ontrack.ReleaseTableItem;
import uk.ac.swanseacoventry.cmt.ontrack.Signal;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Control Table Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.ControlTableItemImpl#getSignal <em>Signal</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.ControlTableItemImpl#getRoute <em>Route</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.ControlTableItemImpl#getNormals <em>Normals</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.ControlTableItemImpl#getReverses <em>Reverses</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.ControlTableItemImpl#getClears <em>Clears</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.ControlTableItemImpl#getDirections <em>Directions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ControlTableItemImpl extends MinimalEObjectImpl.Container implements ControlTableItem {
	/**
	 * The cached value of the '{@link #getSignal() <em>Signal</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignal()
	 * @generated
	 * @ordered
	 */
	protected Signal signal;

	/**
	 * The default value of the '{@link #getRoute() <em>Route</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoute()
	 * @generated
	 * @ordered
	 */
	protected static final String ROUTE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRoute() <em>Route</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoute()
	 * @generated
	 * @ordered
	 */
	protected String route = ROUTE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getNormals() <em>Normals</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNormals()
	 * @generated
	 * @ordered
	 */
	protected EList<Point> normals;

	/**
	 * The cached value of the '{@link #getReverses() <em>Reverses</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReverses()
	 * @generated
	 * @ordered
	 */
	protected EList<Point> reverses;

	/**
	 * The cached value of the '{@link #getClears() <em>Clears</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClears()
	 * @generated
	 * @ordered
	 */
	protected EList<Track> clears;

	/**
	 * The cached value of the '{@link #getDirections() <em>Directions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirections()
	 * @generated
	 * @ordered
	 */
	protected EList<DirectedTrack> directions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ControlTableItemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OntrackPackage.Literals.CONTROL_TABLE_ITEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRoute() {
		return route;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoute(String newRoute) {
		String oldRoute = route;
		route = newRoute;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.CONTROL_TABLE_ITEM__ROUTE, oldRoute, route));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Signal getSignal() {
		if (signal != null && signal.eIsProxy()) {
			InternalEObject oldSignal = (InternalEObject)signal;
			signal = (Signal)eResolveProxy(oldSignal);
			if (signal != oldSignal) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OntrackPackage.CONTROL_TABLE_ITEM__SIGNAL, oldSignal, signal));
			}
		}
		return signal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Signal basicGetSignal() {
		return signal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSignal(Signal newSignal) {
		Signal oldSignal = signal;
		signal = newSignal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.CONTROL_TABLE_ITEM__SIGNAL, oldSignal, signal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DirectedTrack> getDirections() {
		if (directions == null) {
			directions = new EObjectResolvingEList<DirectedTrack>(DirectedTrack.class, this, OntrackPackage.CONTROL_TABLE_ITEM__DIRECTIONS);
		}
		return directions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Point> getNormals() {
		if (normals == null) {
			normals = new EObjectResolvingEList<Point>(Point.class, this, OntrackPackage.CONTROL_TABLE_ITEM__NORMALS);
		}
		return normals;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Point> getReverses() {
		if (reverses == null) {
			reverses = new EObjectResolvingEList<Point>(Point.class, this, OntrackPackage.CONTROL_TABLE_ITEM__REVERSES);
		}
		return reverses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Track> getClears() {
		if (clears == null) {
			clears = new EObjectResolvingEList<Track>(Track.class, this, OntrackPackage.CONTROL_TABLE_ITEM__CLEARS);
		}
		return clears;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OntrackPackage.CONTROL_TABLE_ITEM__SIGNAL:
				if (resolve) return getSignal();
				return basicGetSignal();
			case OntrackPackage.CONTROL_TABLE_ITEM__ROUTE:
				return getRoute();
			case OntrackPackage.CONTROL_TABLE_ITEM__NORMALS:
				return getNormals();
			case OntrackPackage.CONTROL_TABLE_ITEM__REVERSES:
				return getReverses();
			case OntrackPackage.CONTROL_TABLE_ITEM__CLEARS:
				return getClears();
			case OntrackPackage.CONTROL_TABLE_ITEM__DIRECTIONS:
				return getDirections();
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
			case OntrackPackage.CONTROL_TABLE_ITEM__SIGNAL:
				setSignal((Signal)newValue);
				return;
			case OntrackPackage.CONTROL_TABLE_ITEM__ROUTE:
				setRoute((String)newValue);
				return;
			case OntrackPackage.CONTROL_TABLE_ITEM__NORMALS:
				getNormals().clear();
				getNormals().addAll((Collection<? extends Point>)newValue);
				return;
			case OntrackPackage.CONTROL_TABLE_ITEM__REVERSES:
				getReverses().clear();
				getReverses().addAll((Collection<? extends Point>)newValue);
				return;
			case OntrackPackage.CONTROL_TABLE_ITEM__CLEARS:
				getClears().clear();
				getClears().addAll((Collection<? extends Track>)newValue);
				return;
			case OntrackPackage.CONTROL_TABLE_ITEM__DIRECTIONS:
				getDirections().clear();
				getDirections().addAll((Collection<? extends DirectedTrack>)newValue);
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
			case OntrackPackage.CONTROL_TABLE_ITEM__SIGNAL:
				setSignal((Signal)null);
				return;
			case OntrackPackage.CONTROL_TABLE_ITEM__ROUTE:
				setRoute(ROUTE_EDEFAULT);
				return;
			case OntrackPackage.CONTROL_TABLE_ITEM__NORMALS:
				getNormals().clear();
				return;
			case OntrackPackage.CONTROL_TABLE_ITEM__REVERSES:
				getReverses().clear();
				return;
			case OntrackPackage.CONTROL_TABLE_ITEM__CLEARS:
				getClears().clear();
				return;
			case OntrackPackage.CONTROL_TABLE_ITEM__DIRECTIONS:
				getDirections().clear();
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
			case OntrackPackage.CONTROL_TABLE_ITEM__SIGNAL:
				return signal != null;
			case OntrackPackage.CONTROL_TABLE_ITEM__ROUTE:
				return ROUTE_EDEFAULT == null ? route != null : !ROUTE_EDEFAULT.equals(route);
			case OntrackPackage.CONTROL_TABLE_ITEM__NORMALS:
				return normals != null && !normals.isEmpty();
			case OntrackPackage.CONTROL_TABLE_ITEM__REVERSES:
				return reverses != null && !reverses.isEmpty();
			case OntrackPackage.CONTROL_TABLE_ITEM__CLEARS:
				return clears != null && !clears.isEmpty();
			case OntrackPackage.CONTROL_TABLE_ITEM__DIRECTIONS:
				return directions != null && !directions.isEmpty();
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
		result.append(" (route: ");
		result.append(route);
		result.append(')');
		return result.toString();
	}
	
	public String getNormalsSequence(TrackPlan tp){
		return getPointsSequence(tp, getNormals());
	}

	public String getReversesSequence(TrackPlan tp){
		return getPointsSequence(tp, getReverses());
	}

	private String getPointsSequence(TrackPlan tp, EList<Point> points){
		List<String> rets = new ArrayList<String>();
		for(Point p : points){
			String name = p.getName();
			if (tp==null) continue;
			boolean found = false;
			for(ReleaseTableItem rti : tp.getReleaseTable()){
				if (rti.getRoute().equals(this.getRoute()) && rti.getPoint()==p) {
					found = true;
					if (rti.getUnoccupiedTrack()!=null && rti.getOccupiedTrack()!=null) {
						if (rti.getUnoccupiedTrack().getPoint()!=p)
							name += "*";
					}
					else 
						name += "-";
				}
			}
			rets.add(name + (found ? "" : "-"));
		}
		return String.join(", ", rets);
	}

	
	public String getClearsSequence(){
		List<String> rets = new ArrayList<String>();
		for(Track p : getClears()){
			rets.add(p.getName());
		}
		return String.join(", ", rets);
	}

	@Override
	public String getDirectionsSequence() {
		List<String> rets = new ArrayList<String>();
		for(DirectedTrack dt : getDirections()){
			rets.add(dt.getOppositeConnector().getId() + "->" + dt.getTrack().getName() + "->" + dt.getConnector().getId());
		}
		// TODO Auto-generated method stub
		return String.join("; ", rets);
	}

	@Override
	public ArrayList<DirectedTrack> guessDirections() {
		ArrayList<DirectedTrack> ret = new ArrayList<DirectedTrack>();
		HashSet<Track> visited = new HashSet<Track>();
		Connector c = getSignal().getConnector();
		while(c!=null){
			Connector nc = null;
			for(Track t : getClears()){
				if (visited.contains(t)) continue;
				if (t.getC1()==c)
					nc = t.getC2();
				else if (t.getC2()==c)
					nc = t.getC1();
				if (nc!=null) {
					visited.add(t);
					ret.add(t.getDirectedTrackByConnector(nc, false));
					break;
				}
			}
			c = nc;
		}
		
		return ret;
	}
} //ControlTableItemImpl
