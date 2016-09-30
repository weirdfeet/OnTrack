/**
 */
package uk.ac.swanseacoventry.cmt.ontrack.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import uk.ac.swanseacoventry.cmt.ontrack.OntrackPackage;
import uk.ac.swanseacoventry.cmt.ontrack.Point;
import uk.ac.swanseacoventry.cmt.ontrack.ReleaseTableItem;
import uk.ac.swanseacoventry.cmt.ontrack.Track;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Release Table Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.ReleaseTableItemImpl#getRoute <em>Route</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.ReleaseTableItemImpl#getPoint <em>Point</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.ReleaseTableItemImpl#getUnoccupiedTrack <em>Unoccupied Track</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.ReleaseTableItemImpl#getOccupiedTrack <em>Occupied Track</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReleaseTableItemImpl extends MinimalEObjectImpl.Container implements ReleaseTableItem {
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
	 * The cached value of the '{@link #getPoint() <em>Point</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPoint()
	 * @generated
	 * @ordered
	 */
	protected Point point;

	/**
	 * The cached value of the '{@link #getUnoccupiedTrack() <em>Unoccupied Track</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnoccupiedTrack()
	 * @generated
	 * @ordered
	 */
	protected Track unoccupiedTrack;

	/**
	 * The cached value of the '{@link #getOccupiedTrack() <em>Occupied Track</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOccupiedTrack()
	 * @generated
	 * @ordered
	 */
	protected Track occupiedTrack;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReleaseTableItemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OntrackPackage.Literals.RELEASE_TABLE_ITEM;
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
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.RELEASE_TABLE_ITEM__ROUTE, oldRoute, route));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Point getPoint() {
		if (point != null && point.eIsProxy()) {
			InternalEObject oldPoint = (InternalEObject)point;
			point = (Point)eResolveProxy(oldPoint);
			if (point != oldPoint) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OntrackPackage.RELEASE_TABLE_ITEM__POINT, oldPoint, point));
			}
		}
		return point;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Point basicGetPoint() {
		return point;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPoint(Point newPoint) {
		Point oldPoint = point;
		point = newPoint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.RELEASE_TABLE_ITEM__POINT, oldPoint, point));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Track getUnoccupiedTrack() {
		if (unoccupiedTrack != null && unoccupiedTrack.eIsProxy()) {
			InternalEObject oldUnoccupiedTrack = (InternalEObject)unoccupiedTrack;
			unoccupiedTrack = (Track)eResolveProxy(oldUnoccupiedTrack);
			if (unoccupiedTrack != oldUnoccupiedTrack) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OntrackPackage.RELEASE_TABLE_ITEM__UNOCCUPIED_TRACK, oldUnoccupiedTrack, unoccupiedTrack));
			}
		}
		return unoccupiedTrack;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Track basicGetUnoccupiedTrack() {
		return unoccupiedTrack;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnoccupiedTrack(Track newUnoccupiedTrack) {
		Track oldUnoccupiedTrack = unoccupiedTrack;
		unoccupiedTrack = newUnoccupiedTrack;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.RELEASE_TABLE_ITEM__UNOCCUPIED_TRACK, oldUnoccupiedTrack, unoccupiedTrack));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Track getOccupiedTrack() {
		if (occupiedTrack != null && occupiedTrack.eIsProxy()) {
			InternalEObject oldOccupiedTrack = (InternalEObject)occupiedTrack;
			occupiedTrack = (Track)eResolveProxy(oldOccupiedTrack);
			if (occupiedTrack != oldOccupiedTrack) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OntrackPackage.RELEASE_TABLE_ITEM__OCCUPIED_TRACK, oldOccupiedTrack, occupiedTrack));
			}
		}
		return occupiedTrack;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Track basicGetOccupiedTrack() {
		return occupiedTrack;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOccupiedTrack(Track newOccupiedTrack) {
		Track oldOccupiedTrack = occupiedTrack;
		occupiedTrack = newOccupiedTrack;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.RELEASE_TABLE_ITEM__OCCUPIED_TRACK, oldOccupiedTrack, occupiedTrack));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OntrackPackage.RELEASE_TABLE_ITEM__ROUTE:
				return getRoute();
			case OntrackPackage.RELEASE_TABLE_ITEM__POINT:
				if (resolve) return getPoint();
				return basicGetPoint();
			case OntrackPackage.RELEASE_TABLE_ITEM__UNOCCUPIED_TRACK:
				if (resolve) return getUnoccupiedTrack();
				return basicGetUnoccupiedTrack();
			case OntrackPackage.RELEASE_TABLE_ITEM__OCCUPIED_TRACK:
				if (resolve) return getOccupiedTrack();
				return basicGetOccupiedTrack();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case OntrackPackage.RELEASE_TABLE_ITEM__ROUTE:
				setRoute((String)newValue);
				return;
			case OntrackPackage.RELEASE_TABLE_ITEM__POINT:
				setPoint((Point)newValue);
				return;
			case OntrackPackage.RELEASE_TABLE_ITEM__UNOCCUPIED_TRACK:
				setUnoccupiedTrack((Track)newValue);
				return;
			case OntrackPackage.RELEASE_TABLE_ITEM__OCCUPIED_TRACK:
				setOccupiedTrack((Track)newValue);
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
			case OntrackPackage.RELEASE_TABLE_ITEM__ROUTE:
				setRoute(ROUTE_EDEFAULT);
				return;
			case OntrackPackage.RELEASE_TABLE_ITEM__POINT:
				setPoint((Point)null);
				return;
			case OntrackPackage.RELEASE_TABLE_ITEM__UNOCCUPIED_TRACK:
				setUnoccupiedTrack((Track)null);
				return;
			case OntrackPackage.RELEASE_TABLE_ITEM__OCCUPIED_TRACK:
				setOccupiedTrack((Track)null);
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
			case OntrackPackage.RELEASE_TABLE_ITEM__ROUTE:
				return ROUTE_EDEFAULT == null ? route != null : !ROUTE_EDEFAULT.equals(route);
			case OntrackPackage.RELEASE_TABLE_ITEM__POINT:
				return point != null;
			case OntrackPackage.RELEASE_TABLE_ITEM__UNOCCUPIED_TRACK:
				return unoccupiedTrack != null;
			case OntrackPackage.RELEASE_TABLE_ITEM__OCCUPIED_TRACK:
				return occupiedTrack != null;
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

} //ReleaseTableItemImpl
