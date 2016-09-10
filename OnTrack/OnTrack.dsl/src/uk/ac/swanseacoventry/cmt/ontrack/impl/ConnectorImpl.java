/**
 */
package uk.ac.swanseacoventry.cmt.ontrack.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import uk.ac.swanseacoventry.cmt.ontrack.Connector;
import uk.ac.swanseacoventry.cmt.ontrack.Entrance;
import uk.ac.swanseacoventry.cmt.ontrack.Exit;
import uk.ac.swanseacoventry.cmt.ontrack.OntrackPackage;
import uk.ac.swanseacoventry.cmt.ontrack.Signal;
import uk.ac.swanseacoventry.cmt.ontrack.Terminal;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connector</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.ConnectorImpl#getId <em>Id</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.ConnectorImpl#getTrack1s <em>Track1s</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.ConnectorImpl#getTrack2s <em>Track2s</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.ConnectorImpl#getSignals <em>Signals</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.ConnectorImpl#getTerminal <em>Terminal</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.ConnectorImpl#getEntrances <em>Entrances</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.ConnectorImpl#getExits <em>Exits</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConnectorImpl extends MinimalEObjectImpl.Container implements Connector {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final int ID_EDEFAULT = 0;
	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected int id = ID_EDEFAULT;
	/**
	 * The cached value of the '{@link #getTrack1s() <em>Track1s</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrack1s()
	 * @generated
	 * @ordered
	 */
	protected EList<Track> track1s;
	/**
	 * The cached value of the '{@link #getTrack2s() <em>Track2s</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrack2s()
	 * @generated
	 * @ordered
	 */
	protected EList<Track> track2s;
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
	 * The cached value of the '{@link #getTerminal() <em>Terminal</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTerminal()
	 * @generated
	 * @ordered
	 */
	protected Terminal terminal;
	/**
	 * The cached value of the '{@link #getEntrances() <em>Entrances</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntrances()
	 * @generated
	 * @ordered
	 */
	protected EList<Entrance> entrances;
	/**
	 * The cached value of the '{@link #getExits() <em>Exits</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExits()
	 * @generated
	 * @ordered
	 */
	protected EList<Exit> exits;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConnectorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OntrackPackage.Literals.CONNECTOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Track> getTrack1s() {
		if (track1s == null) {
			track1s = new EObjectWithInverseResolvingEList<Track>(Track.class, this, OntrackPackage.CONNECTOR__TRACK1S, OntrackPackage.TRACK__C1);
		}
		return track1s;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Track> getTrack2s() {
		if (track2s == null) {
			track2s = new EObjectWithInverseResolvingEList<Track>(Track.class, this, OntrackPackage.CONNECTOR__TRACK2S, OntrackPackage.TRACK__C2);
		}
		return track2s;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Signal> getSignals() {
		if (signals == null) {
			signals = new EObjectWithInverseResolvingEList<Signal>(Signal.class, this, OntrackPackage.CONNECTOR__SIGNALS, OntrackPackage.SIGNAL__CONNECTOR);
		}
		return signals;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Terminal getTerminal() {
		if (terminal != null && terminal.eIsProxy()) {
			InternalEObject oldTerminal = (InternalEObject)terminal;
			terminal = (Terminal)eResolveProxy(oldTerminal);
			if (terminal != oldTerminal) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OntrackPackage.CONNECTOR__TERMINAL, oldTerminal, terminal));
			}
		}
		return terminal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Terminal basicGetTerminal() {
		return terminal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTerminal(Terminal newTerminal, NotificationChain msgs) {
		Terminal oldTerminal = terminal;
		terminal = newTerminal;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OntrackPackage.CONNECTOR__TERMINAL, oldTerminal, newTerminal);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTerminal(Terminal newTerminal) {
		if (newTerminal != terminal) {
			NotificationChain msgs = null;
			if (terminal != null)
				msgs = ((InternalEObject)terminal).eInverseRemove(this, OntrackPackage.TERMINAL__CONNECTOR, Terminal.class, msgs);
			if (newTerminal != null)
				msgs = ((InternalEObject)newTerminal).eInverseAdd(this, OntrackPackage.TERMINAL__CONNECTOR, Terminal.class, msgs);
			msgs = basicSetTerminal(newTerminal, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.CONNECTOR__TERMINAL, newTerminal, newTerminal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Entrance> getEntrances() {
		if (entrances == null) {
			entrances = new EObjectWithInverseResolvingEList<Entrance>(Entrance.class, this, OntrackPackage.CONNECTOR__ENTRANCES, OntrackPackage.ENTRANCE__CONNECTOR);
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
			exits = new EObjectWithInverseResolvingEList<Exit>(Exit.class, this, OntrackPackage.CONNECTOR__EXITS, OntrackPackage.EXIT__CONNECTOR);
		}
		return exits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setId(int newId) {
		int oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.CONNECTOR__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	private ArrayList<Track> tracks;
	public List<Track> getTracks() {
		if (tracks == null) {
			tracks = new ArrayList<Track>();
		}
		
		tracks.clear();
		tracks.addAll(getTrack1s());
		tracks.addAll(getTrack2s());
		
		return tracks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OntrackPackage.CONNECTOR__TRACK1S:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTrack1s()).basicAdd(otherEnd, msgs);
			case OntrackPackage.CONNECTOR__TRACK2S:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTrack2s()).basicAdd(otherEnd, msgs);
			case OntrackPackage.CONNECTOR__SIGNALS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSignals()).basicAdd(otherEnd, msgs);
			case OntrackPackage.CONNECTOR__TERMINAL:
				if (terminal != null)
					msgs = ((InternalEObject)terminal).eInverseRemove(this, OntrackPackage.TERMINAL__CONNECTOR, Terminal.class, msgs);
				return basicSetTerminal((Terminal)otherEnd, msgs);
			case OntrackPackage.CONNECTOR__ENTRANCES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getEntrances()).basicAdd(otherEnd, msgs);
			case OntrackPackage.CONNECTOR__EXITS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getExits()).basicAdd(otherEnd, msgs);
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
			case OntrackPackage.CONNECTOR__TRACK1S:
				return ((InternalEList<?>)getTrack1s()).basicRemove(otherEnd, msgs);
			case OntrackPackage.CONNECTOR__TRACK2S:
				return ((InternalEList<?>)getTrack2s()).basicRemove(otherEnd, msgs);
			case OntrackPackage.CONNECTOR__SIGNALS:
				return ((InternalEList<?>)getSignals()).basicRemove(otherEnd, msgs);
			case OntrackPackage.CONNECTOR__TERMINAL:
				return basicSetTerminal(null, msgs);
			case OntrackPackage.CONNECTOR__ENTRANCES:
				return ((InternalEList<?>)getEntrances()).basicRemove(otherEnd, msgs);
			case OntrackPackage.CONNECTOR__EXITS:
				return ((InternalEList<?>)getExits()).basicRemove(otherEnd, msgs);
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
			case OntrackPackage.CONNECTOR__ID:
				return getId();
			case OntrackPackage.CONNECTOR__TRACK1S:
				return getTrack1s();
			case OntrackPackage.CONNECTOR__TRACK2S:
				return getTrack2s();
			case OntrackPackage.CONNECTOR__SIGNALS:
				return getSignals();
			case OntrackPackage.CONNECTOR__TERMINAL:
				if (resolve) return getTerminal();
				return basicGetTerminal();
			case OntrackPackage.CONNECTOR__ENTRANCES:
				return getEntrances();
			case OntrackPackage.CONNECTOR__EXITS:
				return getExits();
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
			case OntrackPackage.CONNECTOR__ID:
				setId((Integer)newValue);
				return;
			case OntrackPackage.CONNECTOR__TRACK1S:
				getTrack1s().clear();
				getTrack1s().addAll((Collection<? extends Track>)newValue);
				return;
			case OntrackPackage.CONNECTOR__TRACK2S:
				getTrack2s().clear();
				getTrack2s().addAll((Collection<? extends Track>)newValue);
				return;
			case OntrackPackage.CONNECTOR__SIGNALS:
				getSignals().clear();
				getSignals().addAll((Collection<? extends Signal>)newValue);
				return;
			case OntrackPackage.CONNECTOR__TERMINAL:
				setTerminal((Terminal)newValue);
				return;
			case OntrackPackage.CONNECTOR__ENTRANCES:
				getEntrances().clear();
				getEntrances().addAll((Collection<? extends Entrance>)newValue);
				return;
			case OntrackPackage.CONNECTOR__EXITS:
				getExits().clear();
				getExits().addAll((Collection<? extends Exit>)newValue);
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
			case OntrackPackage.CONNECTOR__ID:
				setId(ID_EDEFAULT);
				return;
			case OntrackPackage.CONNECTOR__TRACK1S:
				getTrack1s().clear();
				return;
			case OntrackPackage.CONNECTOR__TRACK2S:
				getTrack2s().clear();
				return;
			case OntrackPackage.CONNECTOR__SIGNALS:
				getSignals().clear();
				return;
			case OntrackPackage.CONNECTOR__TERMINAL:
				setTerminal((Terminal)null);
				return;
			case OntrackPackage.CONNECTOR__ENTRANCES:
				getEntrances().clear();
				return;
			case OntrackPackage.CONNECTOR__EXITS:
				getExits().clear();
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
			case OntrackPackage.CONNECTOR__ID:
				return id != ID_EDEFAULT;
			case OntrackPackage.CONNECTOR__TRACK1S:
				return track1s != null && !track1s.isEmpty();
			case OntrackPackage.CONNECTOR__TRACK2S:
				return track2s != null && !track2s.isEmpty();
			case OntrackPackage.CONNECTOR__SIGNALS:
				return signals != null && !signals.isEmpty();
			case OntrackPackage.CONNECTOR__TERMINAL:
				return terminal != null;
			case OntrackPackage.CONNECTOR__ENTRANCES:
				return entrances != null && !entrances.isEmpty();
			case OntrackPackage.CONNECTOR__EXITS:
				return exits != null && !exits.isEmpty();
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
		result.append(" (id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}
	
	public int getFreshID(TrackPlan tp){
		Map<Integer,Connector> usedIDs = new HashMap<Integer,Connector>();
		for(Connector c : tp.getConnectors()) usedIDs.put(c.getId(), c);
		int i = 1;
		for(i = 1; i <= tp.getConnectors().size(); i++) {
			if (!usedIDs.containsKey(i)) {
				return i;
			}
		}
		return i;
	}

} //ConnectorImpl
