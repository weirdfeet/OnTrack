/**
 */
package uk.ac.swanseacoventry.cmt.ontrack.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import uk.ac.swanseacoventry.cmt.ontrack.Connector;
import uk.ac.swanseacoventry.cmt.ontrack.DirectedTrack;
import uk.ac.swanseacoventry.cmt.ontrack.Exit;
import uk.ac.swanseacoventry.cmt.ontrack.OntrackPackage;
import uk.ac.swanseacoventry.cmt.ontrack.Signal;
import uk.ac.swanseacoventry.cmt.ontrack.Terminal;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Directed Track</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.DirectedTrackImpl#getTrack <em>Track</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.DirectedTrackImpl#getConnector <em>Connector</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DirectedTrackImpl extends MinimalEObjectImpl.Container implements DirectedTrack {
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
	protected DirectedTrackImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OntrackPackage.Literals.DIRECTED_TRACK;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OntrackPackage.DIRECTED_TRACK__TRACK, oldTrack, track));
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
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.DIRECTED_TRACK__TRACK, oldTrack, track));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OntrackPackage.DIRECTED_TRACK__CONNECTOR, oldConnector, connector));
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
	public void setConnector(Connector newConnector) {
		Connector oldConnector = connector;
		connector = newConnector;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.DIRECTED_TRACK__CONNECTOR, oldConnector, connector));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	private List<DirectedTrack> nexts;
	public List<DirectedTrack> getNexts() {
		if (nexts == null) {
			nexts = new ArrayList<DirectedTrack>();
		}
		
		nexts.clear();
		
		List<Track> connectorTracks = getConnector().getTracks();
		for(Track t : connectorTracks){
			if (t!=getTrack()){
				if (t.getPoint()==null || t.getPoint()!=getTrack().getPoint())
					nexts.add(((TrackImpl)t).getDirectedTrackByConnector(getConnector(),true));
			} else if (getConnector().getTerminal()!=null) {
				nexts.add(((TrackImpl)t).getDirectedTrackByConnector(getConnector(), true));
			}
		}
		
		return nexts;
	}
	private List<DirectedTrack> prevs;
	public List<DirectedTrack> getPrevs() {
		if (prevs == null) {
			prevs = new ArrayList<DirectedTrack>();
		}
		
		prevs.clear();
		
		DirectedTrack rvDT = getTrack().getDirectedTrackByConnector(getConnector(), true);
		for(DirectedTrack dt : rvDT.getNexts()){
			prevs.add(dt.getTrack().getDirectedTrackByConnector(dt.getConnector(), true));
		}
		
		return prevs;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OntrackPackage.DIRECTED_TRACK__TRACK:
				if (resolve) return getTrack();
				return basicGetTrack();
			case OntrackPackage.DIRECTED_TRACK__CONNECTOR:
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
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case OntrackPackage.DIRECTED_TRACK__TRACK:
				setTrack((Track)newValue);
				return;
			case OntrackPackage.DIRECTED_TRACK__CONNECTOR:
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
			case OntrackPackage.DIRECTED_TRACK__TRACK:
				setTrack((Track)null);
				return;
			case OntrackPackage.DIRECTED_TRACK__CONNECTOR:
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
			case OntrackPackage.DIRECTED_TRACK__TRACK:
				return track != null;
			case OntrackPackage.DIRECTED_TRACK__CONNECTOR:
				return connector != null;
		}
		return super.eIsSet(featureID);
	}
	
	/**
	 * @generated NOT
	 */
	public boolean isTerminal(TrackPlan tp){
		for(Terminal t : tp.getTerminals()){
			if (t.getConnector()==connector)
				return true;
		}
		return false;
	}

	/**
	 * @generated NOT
	 */
	public boolean isExit(TrackPlan tp){
		for(Exit e : tp.getExits()){
			if (e.getConnector()==connector)
				return true;
		}
		return false;
	}

	/**
	 * @generated NOT
	 */
	public Signal getSignal(){
		for(Signal s : getConnector().getSignals()){
			if (s.getTrack()==getTrack())
				return s;
		}
		return null;
	}

	@Override
	public Connector getOppositeConnector() {
		// TODO Auto-generated method stub
		if (getConnector()==getTrack().getC1())
			return getTrack().getC2();
		return getTrack().getC1();
	}

} //DirectedTrackImpl
