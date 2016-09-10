/**
 */
package uk.ac.swanseacoventry.cmt.ontrack.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import uk.ac.swanseacoventry.cmt.ontrack.DirectedTrack;
import uk.ac.swanseacoventry.cmt.ontrack.OntrackPackage;
import uk.ac.swanseacoventry.cmt.ontrack.Signal;
import uk.ac.swanseacoventry.cmt.ontrack.TopoRoute;
import uk.ac.swanseacoventry.cmt.ontrack.Track;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Topo Route</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TopoRouteImpl#getNames <em>Names</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TopoRouteImpl#getDirectedTracks <em>Directed Tracks</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TopoRouteImpl#getStartSignal <em>Start Signal</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TopoRouteImpl#getEndSignal <em>End Signal</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TopoRouteImpl extends MinimalEObjectImpl.Container implements TopoRoute {
	/**
	 * The cached value of the '{@link #getNames() <em>Names</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNames()
	 * @generated
	 * @ordered
	 */
	protected EList<String> names;

	/**
	 * The cached value of the '{@link #getDirectedTracks() <em>Directed Tracks</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirectedTracks()
	 * @generated
	 * @ordered
	 */
	protected EList<DirectedTrack> directedTracks;

	/**
	 * The cached value of the '{@link #getStartSignal() <em>Start Signal</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartSignal()
	 * @generated
	 * @ordered
	 */
	protected Signal startSignal;

	/**
	 * The cached value of the '{@link #getEndSignal() <em>End Signal</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndSignal()
	 * @generated
	 * @ordered
	 */
	protected Signal endSignal;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TopoRouteImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OntrackPackage.Literals.TOPO_ROUTE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Signal getStartSignal() {
		if (startSignal != null && startSignal.eIsProxy()) {
			InternalEObject oldStartSignal = (InternalEObject)startSignal;
			startSignal = (Signal)eResolveProxy(oldStartSignal);
			if (startSignal != oldStartSignal) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OntrackPackage.TOPO_ROUTE__START_SIGNAL, oldStartSignal, startSignal));
			}
		}
		return startSignal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Signal basicGetStartSignal() {
		return startSignal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartSignal(Signal newStartSignal) {
		Signal oldStartSignal = startSignal;
		startSignal = newStartSignal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.TOPO_ROUTE__START_SIGNAL, oldStartSignal, startSignal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Signal getEndSignal() {
		if (endSignal != null && endSignal.eIsProxy()) {
			InternalEObject oldEndSignal = (InternalEObject)endSignal;
			endSignal = (Signal)eResolveProxy(oldEndSignal);
			if (endSignal != oldEndSignal) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OntrackPackage.TOPO_ROUTE__END_SIGNAL, oldEndSignal, endSignal));
			}
		}
		return endSignal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Signal basicGetEndSignal() {
		return endSignal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEndSignal(Signal newEndSignal) {
		Signal oldEndSignal = endSignal;
		endSignal = newEndSignal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.TOPO_ROUTE__END_SIGNAL, oldEndSignal, endSignal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DirectedTrack> getDirectedTracks() {
		if (directedTracks == null) {
			directedTracks = new EObjectResolvingEList<DirectedTrack>(DirectedTrack.class, this, OntrackPackage.TOPO_ROUTE__DIRECTED_TRACKS);
		}
		return directedTracks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getNames() {
		if (names == null) {
			names = new EDataTypeUniqueEList<String>(String.class, this, OntrackPackage.TOPO_ROUTE__NAMES);
		}
		return names;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OntrackPackage.TOPO_ROUTE__NAMES:
				return getNames();
			case OntrackPackage.TOPO_ROUTE__DIRECTED_TRACKS:
				return getDirectedTracks();
			case OntrackPackage.TOPO_ROUTE__START_SIGNAL:
				if (resolve) return getStartSignal();
				return basicGetStartSignal();
			case OntrackPackage.TOPO_ROUTE__END_SIGNAL:
				if (resolve) return getEndSignal();
				return basicGetEndSignal();
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
			case OntrackPackage.TOPO_ROUTE__NAMES:
				getNames().clear();
				getNames().addAll((Collection<? extends String>)newValue);
				return;
			case OntrackPackage.TOPO_ROUTE__DIRECTED_TRACKS:
				getDirectedTracks().clear();
				getDirectedTracks().addAll((Collection<? extends DirectedTrack>)newValue);
				return;
			case OntrackPackage.TOPO_ROUTE__START_SIGNAL:
				setStartSignal((Signal)newValue);
				return;
			case OntrackPackage.TOPO_ROUTE__END_SIGNAL:
				setEndSignal((Signal)newValue);
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
			case OntrackPackage.TOPO_ROUTE__NAMES:
				getNames().clear();
				return;
			case OntrackPackage.TOPO_ROUTE__DIRECTED_TRACKS:
				getDirectedTracks().clear();
				return;
			case OntrackPackage.TOPO_ROUTE__START_SIGNAL:
				setStartSignal((Signal)null);
				return;
			case OntrackPackage.TOPO_ROUTE__END_SIGNAL:
				setEndSignal((Signal)null);
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
			case OntrackPackage.TOPO_ROUTE__NAMES:
				return names != null && !names.isEmpty();
			case OntrackPackage.TOPO_ROUTE__DIRECTED_TRACKS:
				return directedTracks != null && !directedTracks.isEmpty();
			case OntrackPackage.TOPO_ROUTE__START_SIGNAL:
				return startSignal != null;
			case OntrackPackage.TOPO_ROUTE__END_SIGNAL:
				return endSignal != null;
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
		result.append(" (names: ");
		result.append(names);
		result.append(')');
		return result.toString();
	}
	
	public String getTrackSequence(){
		String ret = "";
		boolean first = true;
		EList<DirectedTrack> topo = getDirectedTracks(); 
		for(DirectedTrack dt : topo){
			if (!first) ret += ", "; else first = false;
			ret += dt.getTrack().getName();
		}
		return ret;
	}
	
	public boolean hasSameDirectedTrack(TopoRoute tr){
		EList<DirectedTrack> dts1 = getDirectedTracks();
		EList<DirectedTrack> dts2 = tr.getDirectedTracks();
		if (dts1.size() != dts2.size()) return false;
		for(int i = 0; i < dts1.size(); i++){
			if (dts1.get(i).getTrack().equals(dts2.get(i).getTrack()) &&
				dts1.get(i).getConnector().equals(dts2.get(i).getConnector()))
				continue;
			else
				return false;
		}
		
		return true;
	}
	
	public Track getEndTrack(){
		int i = getDirectedTracks().size();
		if (i<=0) return null;
		return getDirectedTracks().get(i-1).getTrack();
	}


} //TopoRouteImpl
