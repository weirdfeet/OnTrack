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
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.ReleaseTableItemImpl#getTrack <em>Track</em>}</li>
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
	 * The cached value of the '{@link #getTrack() <em>Track</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrack()
	 * @generated
	 * @ordered
	 */
	protected Track track;

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
	public Track getTrack() {
		if (track != null && track.eIsProxy()) {
			InternalEObject oldTrack = (InternalEObject)track;
			track = (Track)eResolveProxy(oldTrack);
			if (track != oldTrack) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OntrackPackage.RELEASE_TABLE_ITEM__TRACK, oldTrack, track));
			}
		}
		return track;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Track basicGetTrack() {
		return track;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTrack(Track newTrack) {
		Track oldTrack = track;
		track = newTrack;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.RELEASE_TABLE_ITEM__TRACK, oldTrack, track));
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
			case OntrackPackage.RELEASE_TABLE_ITEM__TRACK:
				if (resolve) return getTrack();
				return basicGetTrack();
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
			case OntrackPackage.RELEASE_TABLE_ITEM__TRACK:
				setTrack((Track)newValue);
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
			case OntrackPackage.RELEASE_TABLE_ITEM__TRACK:
				setTrack((Track)null);
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
			case OntrackPackage.RELEASE_TABLE_ITEM__TRACK:
				return track != null;
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
