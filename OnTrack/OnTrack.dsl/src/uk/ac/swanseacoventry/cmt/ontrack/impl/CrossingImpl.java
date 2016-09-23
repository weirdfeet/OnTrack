/**
 */
package uk.ac.swanseacoventry.cmt.ontrack.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import uk.ac.swanseacoventry.cmt.ontrack.Crossing;
import uk.ac.swanseacoventry.cmt.ontrack.OntrackPackage;
import uk.ac.swanseacoventry.cmt.ontrack.Track;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Crossing</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.CrossingImpl#getTrack1 <em>Track1</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.CrossingImpl#getTrack2 <em>Track2</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CrossingImpl extends UnitImpl implements Crossing {
	/**
	 * The cached value of the '{@link #getTrack1() <em>Track1</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrack1()
	 * @generated
	 * @ordered
	 */
	protected Track track1;

	/**
	 * The cached value of the '{@link #getTrack2() <em>Track2</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrack2()
	 * @generated
	 * @ordered
	 */
	protected Track track2;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CrossingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OntrackPackage.Literals.CROSSING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Track getTrack1() {
		if (track1 != null && track1.eIsProxy()) {
			InternalEObject oldTrack1 = (InternalEObject)track1;
			track1 = (Track)eResolveProxy(oldTrack1);
			if (track1 != oldTrack1) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OntrackPackage.CROSSING__TRACK1, oldTrack1, track1));
			}
		}
		return track1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Track basicGetTrack1() {
		return track1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTrack1(Track newTrack1, NotificationChain msgs) {
		Track oldTrack1 = track1;
		track1 = newTrack1;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OntrackPackage.CROSSING__TRACK1, oldTrack1, newTrack1);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTrack1(Track newTrack1) {
		if (newTrack1 != track1) {
			NotificationChain msgs = null;
			if (track1 != null)
				msgs = ((InternalEObject)track1).eInverseRemove(this, OntrackPackage.TRACK__CROSSING1, Track.class, msgs);
			if (newTrack1 != null)
				msgs = ((InternalEObject)newTrack1).eInverseAdd(this, OntrackPackage.TRACK__CROSSING1, Track.class, msgs);
			msgs = basicSetTrack1(newTrack1, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.CROSSING__TRACK1, newTrack1, newTrack1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Track getTrack2() {
		if (track2 != null && track2.eIsProxy()) {
			InternalEObject oldTrack2 = (InternalEObject)track2;
			track2 = (Track)eResolveProxy(oldTrack2);
			if (track2 != oldTrack2) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OntrackPackage.CROSSING__TRACK2, oldTrack2, track2));
			}
		}
		return track2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Track basicGetTrack2() {
		return track2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTrack2(Track newTrack2, NotificationChain msgs) {
		Track oldTrack2 = track2;
		track2 = newTrack2;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OntrackPackage.CROSSING__TRACK2, oldTrack2, newTrack2);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTrack2(Track newTrack2) {
		if (newTrack2 != track2) {
			NotificationChain msgs = null;
			if (track2 != null)
				msgs = ((InternalEObject)track2).eInverseRemove(this, OntrackPackage.TRACK__CROSSING2, Track.class, msgs);
			if (newTrack2 != null)
				msgs = ((InternalEObject)newTrack2).eInverseAdd(this, OntrackPackage.TRACK__CROSSING2, Track.class, msgs);
			msgs = basicSetTrack2(newTrack2, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.CROSSING__TRACK2, newTrack2, newTrack2));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OntrackPackage.CROSSING__TRACK1:
				if (track1 != null)
					msgs = ((InternalEObject)track1).eInverseRemove(this, OntrackPackage.TRACK__CROSSING1, Track.class, msgs);
				return basicSetTrack1((Track)otherEnd, msgs);
			case OntrackPackage.CROSSING__TRACK2:
				if (track2 != null)
					msgs = ((InternalEObject)track2).eInverseRemove(this, OntrackPackage.TRACK__CROSSING2, Track.class, msgs);
				return basicSetTrack2((Track)otherEnd, msgs);
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
			case OntrackPackage.CROSSING__TRACK1:
				return basicSetTrack1(null, msgs);
			case OntrackPackage.CROSSING__TRACK2:
				return basicSetTrack2(null, msgs);
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
			case OntrackPackage.CROSSING__TRACK1:
				if (resolve) return getTrack1();
				return basicGetTrack1();
			case OntrackPackage.CROSSING__TRACK2:
				if (resolve) return getTrack2();
				return basicGetTrack2();
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
			case OntrackPackage.CROSSING__TRACK1:
				setTrack1((Track)newValue);
				return;
			case OntrackPackage.CROSSING__TRACK2:
				setTrack2((Track)newValue);
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
			case OntrackPackage.CROSSING__TRACK1:
				setTrack1((Track)null);
				return;
			case OntrackPackage.CROSSING__TRACK2:
				setTrack2((Track)null);
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
			case OntrackPackage.CROSSING__TRACK1:
				return track1 != null;
			case OntrackPackage.CROSSING__TRACK2:
				return track2 != null;
		}
		return super.eIsSet(featureID);
	}

} //CrossingImpl
