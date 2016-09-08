/**
 */
package uk.ac.swanseacoventry.cmt.ontrack.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import uk.ac.swanseacoventry.cmt.ontrack.Connector;
import uk.ac.swanseacoventry.cmt.ontrack.DirectedTrack;
import uk.ac.swanseacoventry.cmt.ontrack.OntrackPackage;
import uk.ac.swanseacoventry.cmt.ontrack.Signal;
import uk.ac.swanseacoventry.cmt.ontrack.Track;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Signal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.SignalImpl#getName <em>Name</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.SignalImpl#getTrack <em>Track</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.SignalImpl#getConnector <em>Connector</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SignalImpl extends MinimalEObjectImpl.Container implements Signal {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

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
	 * The cached value of the '{@link #getConnector() <em>Connector</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnector()
	 * @generated
	 * @ordered
	 */
	protected Connector connector;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SignalImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OntrackPackage.Literals.SIGNAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.SIGNAL__NAME, oldName, name));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OntrackPackage.SIGNAL__TRACK, oldTrack, track));
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
	public NotificationChain basicSetTrack(Track newTrack, NotificationChain msgs) {
		Track oldTrack = track;
		track = newTrack;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OntrackPackage.SIGNAL__TRACK, oldTrack, newTrack);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTrack(Track newTrack) {
		if (newTrack != track) {
			NotificationChain msgs = null;
			if (track != null)
				msgs = ((InternalEObject)track).eInverseRemove(this, OntrackPackage.TRACK__SIGNALS, Track.class, msgs);
			if (newTrack != null)
				msgs = ((InternalEObject)newTrack).eInverseAdd(this, OntrackPackage.TRACK__SIGNALS, Track.class, msgs);
			msgs = basicSetTrack(newTrack, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.SIGNAL__TRACK, newTrack, newTrack));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Connector getConnector() {
		if (connector != null && connector.eIsProxy()) {
			InternalEObject oldConnector = (InternalEObject)connector;
			connector = (Connector)eResolveProxy(oldConnector);
			if (connector != oldConnector) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OntrackPackage.SIGNAL__CONNECTOR, oldConnector, connector));
			}
		}
		return connector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Connector basicGetConnector() {
		return connector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConnector(Connector newConnector, NotificationChain msgs) {
		Connector oldConnector = connector;
		connector = newConnector;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OntrackPackage.SIGNAL__CONNECTOR, oldConnector, newConnector);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnector(Connector newConnector) {
		if (newConnector != connector) {
			NotificationChain msgs = null;
			if (connector != null)
				msgs = ((InternalEObject)connector).eInverseRemove(this, OntrackPackage.CONNECTOR__SIGNALS, Connector.class, msgs);
			if (newConnector != null)
				msgs = ((InternalEObject)newConnector).eInverseAdd(this, OntrackPackage.CONNECTOR__SIGNALS, Connector.class, msgs);
			msgs = basicSetConnector(newConnector, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.SIGNAL__CONNECTOR, newConnector, newConnector));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public DirectedTrack getDirectedTrack() {
		for(DirectedTrack dt : getTrack().getDirectedTracks()){
			if (dt.getConnector()==getConnector())
				return dt;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OntrackPackage.SIGNAL__TRACK:
				if (track != null)
					msgs = ((InternalEObject)track).eInverseRemove(this, OntrackPackage.TRACK__SIGNALS, Track.class, msgs);
				return basicSetTrack((Track)otherEnd, msgs);
			case OntrackPackage.SIGNAL__CONNECTOR:
				if (connector != null)
					msgs = ((InternalEObject)connector).eInverseRemove(this, OntrackPackage.CONNECTOR__SIGNALS, Connector.class, msgs);
				return basicSetConnector((Connector)otherEnd, msgs);
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
			case OntrackPackage.SIGNAL__TRACK:
				return basicSetTrack(null, msgs);
			case OntrackPackage.SIGNAL__CONNECTOR:
				return basicSetConnector(null, msgs);
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
			case OntrackPackage.SIGNAL__NAME:
				return getName();
			case OntrackPackage.SIGNAL__TRACK:
				if (resolve) return getTrack();
				return basicGetTrack();
			case OntrackPackage.SIGNAL__CONNECTOR:
				if (resolve) return getConnector();
				return basicGetConnector();
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
			case OntrackPackage.SIGNAL__NAME:
				setName((String)newValue);
				return;
			case OntrackPackage.SIGNAL__TRACK:
				setTrack((Track)newValue);
				return;
			case OntrackPackage.SIGNAL__CONNECTOR:
				setConnector((Connector)newValue);
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
			case OntrackPackage.SIGNAL__NAME:
				setName(NAME_EDEFAULT);
				return;
			case OntrackPackage.SIGNAL__TRACK:
				setTrack((Track)null);
				return;
			case OntrackPackage.SIGNAL__CONNECTOR:
				setConnector((Connector)null);
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
			case OntrackPackage.SIGNAL__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case OntrackPackage.SIGNAL__TRACK:
				return track != null;
			case OntrackPackage.SIGNAL__CONNECTOR:
				return connector != null;
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
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //SignalImpl
