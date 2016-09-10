/**
 */
package uk.ac.swanseacoventry.cmt.ontrack.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import uk.ac.swanseacoventry.cmt.ontrack.OntrackPackage;
import uk.ac.swanseacoventry.cmt.ontrack.Point;
import uk.ac.swanseacoventry.cmt.ontrack.Track;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Point</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.PointImpl#getNormalTrack <em>Normal Track</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.PointImpl#getReverseTrack <em>Reverse Track</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PointImpl extends UnitImpl implements Point {
	/**
	 * The cached value of the '{@link #getNormalTrack() <em>Normal Track</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNormalTrack()
	 * @generated
	 * @ordered
	 */
	protected Track normalTrack;

	/**
	 * The cached value of the '{@link #getReverseTrack() <em>Reverse Track</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReverseTrack()
	 * @generated
	 * @ordered
	 */
	protected Track reverseTrack;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PointImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OntrackPackage.Literals.POINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Track getNormalTrack() {
		if (normalTrack != null && normalTrack.eIsProxy()) {
			InternalEObject oldNormalTrack = (InternalEObject)normalTrack;
			normalTrack = (Track)eResolveProxy(oldNormalTrack);
			if (normalTrack != oldNormalTrack) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OntrackPackage.POINT__NORMAL_TRACK, oldNormalTrack, normalTrack));
			}
		}
		return normalTrack;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Track basicGetNormalTrack() {
		return normalTrack;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNormalTrack(Track newNormalTrack, NotificationChain msgs) {
		Track oldNormalTrack = normalTrack;
		normalTrack = newNormalTrack;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OntrackPackage.POINT__NORMAL_TRACK, oldNormalTrack, newNormalTrack);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNormalTrack(Track newNormalTrack) {
		if (newNormalTrack != normalTrack) {
			NotificationChain msgs = null;
			if (normalTrack != null)
				msgs = ((InternalEObject)normalTrack).eInverseRemove(this, OntrackPackage.TRACK__POINT_NORMAL, Track.class, msgs);
			if (newNormalTrack != null)
				msgs = ((InternalEObject)newNormalTrack).eInverseAdd(this, OntrackPackage.TRACK__POINT_NORMAL, Track.class, msgs);
			msgs = basicSetNormalTrack(newNormalTrack, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.POINT__NORMAL_TRACK, newNormalTrack, newNormalTrack));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Track getReverseTrack() {
		if (reverseTrack != null && reverseTrack.eIsProxy()) {
			InternalEObject oldReverseTrack = (InternalEObject)reverseTrack;
			reverseTrack = (Track)eResolveProxy(oldReverseTrack);
			if (reverseTrack != oldReverseTrack) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OntrackPackage.POINT__REVERSE_TRACK, oldReverseTrack, reverseTrack));
			}
		}
		return reverseTrack;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Track basicGetReverseTrack() {
		return reverseTrack;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReverseTrack(Track newReverseTrack, NotificationChain msgs) {
		Track oldReverseTrack = reverseTrack;
		reverseTrack = newReverseTrack;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OntrackPackage.POINT__REVERSE_TRACK, oldReverseTrack, newReverseTrack);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReverseTrack(Track newReverseTrack) {
		if (newReverseTrack != reverseTrack) {
			NotificationChain msgs = null;
			if (reverseTrack != null)
				msgs = ((InternalEObject)reverseTrack).eInverseRemove(this, OntrackPackage.TRACK__POINT_REVERSE, Track.class, msgs);
			if (newReverseTrack != null)
				msgs = ((InternalEObject)newReverseTrack).eInverseAdd(this, OntrackPackage.TRACK__POINT_REVERSE, Track.class, msgs);
			msgs = basicSetReverseTrack(newReverseTrack, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.POINT__REVERSE_TRACK, newReverseTrack, newReverseTrack));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OntrackPackage.POINT__NORMAL_TRACK:
				if (normalTrack != null)
					msgs = ((InternalEObject)normalTrack).eInverseRemove(this, OntrackPackage.TRACK__POINT_NORMAL, Track.class, msgs);
				return basicSetNormalTrack((Track)otherEnd, msgs);
			case OntrackPackage.POINT__REVERSE_TRACK:
				if (reverseTrack != null)
					msgs = ((InternalEObject)reverseTrack).eInverseRemove(this, OntrackPackage.TRACK__POINT_REVERSE, Track.class, msgs);
				return basicSetReverseTrack((Track)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OntrackPackage.POINT__NORMAL_TRACK:
				return basicSetNormalTrack(null, msgs);
			case OntrackPackage.POINT__REVERSE_TRACK:
				return basicSetReverseTrack(null, msgs);
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
			case OntrackPackage.POINT__NORMAL_TRACK:
				if (resolve) return getNormalTrack();
				return basicGetNormalTrack();
			case OntrackPackage.POINT__REVERSE_TRACK:
				if (resolve) return getReverseTrack();
				return basicGetReverseTrack();
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
			case OntrackPackage.POINT__NORMAL_TRACK:
				setNormalTrack((Track)newValue);
				return;
			case OntrackPackage.POINT__REVERSE_TRACK:
				setReverseTrack((Track)newValue);
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
			case OntrackPackage.POINT__NORMAL_TRACK:
				setNormalTrack((Track)null);
				return;
			case OntrackPackage.POINT__REVERSE_TRACK:
				setReverseTrack((Track)null);
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
			case OntrackPackage.POINT__NORMAL_TRACK:
				return normalTrack != null;
			case OntrackPackage.POINT__REVERSE_TRACK:
				return reverseTrack != null;
		}
		return super.eIsSet(featureID);
	}

} //PointImpl
