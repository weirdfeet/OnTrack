/**
 */
package uk.ac.swanseacoventry.cmt.ontrack.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import uk.ac.swanseacoventry.cmt.ontrack.Connector;
import uk.ac.swanseacoventry.cmt.ontrack.Crossing;
import uk.ac.swanseacoventry.cmt.ontrack.DirectedTrack;
import uk.ac.swanseacoventry.cmt.ontrack.OntrackPackage;
import uk.ac.swanseacoventry.cmt.ontrack.Point;
import uk.ac.swanseacoventry.cmt.ontrack.Signal;
import uk.ac.swanseacoventry.cmt.ontrack.Track;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Track</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TrackImpl#getC1 <em>C1</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TrackImpl#getC2 <em>C2</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TrackImpl#getPointReverse <em>Point Reverse</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TrackImpl#getPointNormal <em>Point Normal</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TrackImpl#getCrossing2 <em>Crossing2</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TrackImpl#getCrossing1 <em>Crossing1</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TrackImpl#getDirectedTracks <em>Directed Tracks</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.TrackImpl#getSignals <em>Signals</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TrackImpl extends UnitImpl implements Track {
	/**
	 * The cached value of the '{@link #getC1() <em>C1</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getC1()
	 * @generated
	 * @ordered
	 */
	protected Connector c1;

	/**
	 * The cached value of the '{@link #getC2() <em>C2</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getC2()
	 * @generated
	 * @ordered
	 */
	protected Connector c2;

	/**
	 * The cached value of the '{@link #getPointReverse() <em>Point Reverse</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPointReverse()
	 * @generated
	 * @ordered
	 */
	protected Point pointReverse;

	/**
	 * The cached value of the '{@link #getPointNormal() <em>Point Normal</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPointNormal()
	 * @generated
	 * @ordered
	 */
	protected Point pointNormal;

	/**
	 * The cached value of the '{@link #getCrossing2() <em>Crossing2</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCrossing2()
	 * @generated
	 * @ordered
	 */
	protected Crossing crossing2;

	/**
	 * The cached value of the '{@link #getCrossing1() <em>Crossing1</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCrossing1()
	 * @generated
	 * @ordered
	 */
	protected Crossing crossing1;

	/**
	 * The cached value of the '{@link #getDirectedTracks() <em>Directed Tracks</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirectedTracks()
	 * @generated
	 * @ordered
	 */
	protected EList<DirectedTrack> directedTracks;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TrackImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OntrackPackage.Literals.TRACK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getName() {
		
		if (getPointReverse()!=null && getPointReverse().getNormalTrack()!=null) {
			return getPointReverse().getNormalTrack().getName();
		}
		
		if (getCrossing2()!=null) {
			return getCrossing2().getTrack1().getName();
		}

		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Connector getC1() {
		if (c1 != null && c1.eIsProxy()) {
			InternalEObject oldC1 = (InternalEObject)c1;
			c1 = (Connector)eResolveProxy(oldC1);
			if (c1 != oldC1) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OntrackPackage.TRACK__C1, oldC1, c1));
			}
		}
		return c1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Connector basicGetC1() {
		return c1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetC1(Connector newC1, NotificationChain msgs) {
		Connector oldC1 = c1;
		c1 = newC1;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OntrackPackage.TRACK__C1, oldC1, newC1);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setC1(Connector newC1) {
		if (newC1 != c1) {
			NotificationChain msgs = null;
			if (c1 != null)
				msgs = ((InternalEObject)c1).eInverseRemove(this, OntrackPackage.CONNECTOR__TRACK1S, Connector.class, msgs);
			if (newC1 != null)
				msgs = ((InternalEObject)newC1).eInverseAdd(this, OntrackPackage.CONNECTOR__TRACK1S, Connector.class, msgs);
			msgs = basicSetC1(newC1, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.TRACK__C1, newC1, newC1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Connector getC2() {
		if (c2 != null && c2.eIsProxy()) {
			InternalEObject oldC2 = (InternalEObject)c2;
			c2 = (Connector)eResolveProxy(oldC2);
			if (c2 != oldC2) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OntrackPackage.TRACK__C2, oldC2, c2));
			}
		}
		return c2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Connector basicGetC2() {
		return c2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetC2(Connector newC2, NotificationChain msgs) {
		Connector oldC2 = c2;
		c2 = newC2;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OntrackPackage.TRACK__C2, oldC2, newC2);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setC2(Connector newC2) {
		if (newC2 != c2) {
			NotificationChain msgs = null;
			if (c2 != null)
				msgs = ((InternalEObject)c2).eInverseRemove(this, OntrackPackage.CONNECTOR__TRACK2S, Connector.class, msgs);
			if (newC2 != null)
				msgs = ((InternalEObject)newC2).eInverseAdd(this, OntrackPackage.CONNECTOR__TRACK2S, Connector.class, msgs);
			msgs = basicSetC2(newC2, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.TRACK__C2, newC2, newC2));
	}

	/**
	 * @generated NOT
	 */
	public Point getPoint() {
		Point p = getPointNormal();
		return p !=null ? p : getPointReverse();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Crossing getCrossing() {
		Crossing c = getCrossing1();
		return c !=null ? c : getCrossing2();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Point getPointNormal() {
		if (pointNormal != null && pointNormal.eIsProxy()) {
			InternalEObject oldPointNormal = (InternalEObject)pointNormal;
			pointNormal = (Point)eResolveProxy(oldPointNormal);
			if (pointNormal != oldPointNormal) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OntrackPackage.TRACK__POINT_NORMAL, oldPointNormal, pointNormal));
			}
		}
		return pointNormal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Point basicGetPointNormal() {
		return pointNormal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPointNormal(Point newPointNormal, NotificationChain msgs) {
		Point oldPointNormal = pointNormal;
		pointNormal = newPointNormal;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OntrackPackage.TRACK__POINT_NORMAL, oldPointNormal, newPointNormal);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPointNormal(Point newPointNormal) {
		if (newPointNormal != pointNormal) {
			NotificationChain msgs = null;
			if (pointNormal != null)
				msgs = ((InternalEObject)pointNormal).eInverseRemove(this, OntrackPackage.POINT__NORMAL_TRACK, Point.class, msgs);
			if (newPointNormal != null)
				msgs = ((InternalEObject)newPointNormal).eInverseAdd(this, OntrackPackage.POINT__NORMAL_TRACK, Point.class, msgs);
			msgs = basicSetPointNormal(newPointNormal, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.TRACK__POINT_NORMAL, newPointNormal, newPointNormal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Crossing getCrossing1() {
		if (crossing1 != null && crossing1.eIsProxy()) {
			InternalEObject oldCrossing1 = (InternalEObject)crossing1;
			crossing1 = (Crossing)eResolveProxy(oldCrossing1);
			if (crossing1 != oldCrossing1) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OntrackPackage.TRACK__CROSSING1, oldCrossing1, crossing1));
			}
		}
		return crossing1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Crossing basicGetCrossing1() {
		return crossing1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCrossing1(Crossing newCrossing1, NotificationChain msgs) {
		Crossing oldCrossing1 = crossing1;
		crossing1 = newCrossing1;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OntrackPackage.TRACK__CROSSING1, oldCrossing1, newCrossing1);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCrossing1(Crossing newCrossing1) {
		if (newCrossing1 != crossing1) {
			NotificationChain msgs = null;
			if (crossing1 != null)
				msgs = ((InternalEObject)crossing1).eInverseRemove(this, OntrackPackage.CROSSING__TRACK1, Crossing.class, msgs);
			if (newCrossing1 != null)
				msgs = ((InternalEObject)newCrossing1).eInverseAdd(this, OntrackPackage.CROSSING__TRACK1, Crossing.class, msgs);
			msgs = basicSetCrossing1(newCrossing1, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.TRACK__CROSSING1, newCrossing1, newCrossing1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DirectedTrack> getDirectedTracks() {
		if (directedTracks == null) {
			directedTracks = new EObjectContainmentEList<DirectedTrack>(DirectedTrack.class, this, OntrackPackage.TRACK__DIRECTED_TRACKS);
		}
		return directedTracks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Signal> getSignals() {
		if (signals == null) {
			signals = new EObjectWithInverseResolvingEList<Signal>(Signal.class, this, OntrackPackage.TRACK__SIGNALS, OntrackPackage.SIGNAL__TRACK);
		}
		return signals;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Point getPointReverse() {
		if (pointReverse != null && pointReverse.eIsProxy()) {
			InternalEObject oldPointReverse = (InternalEObject)pointReverse;
			pointReverse = (Point)eResolveProxy(oldPointReverse);
			if (pointReverse != oldPointReverse) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OntrackPackage.TRACK__POINT_REVERSE, oldPointReverse, pointReverse));
			}
		}
		return pointReverse;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Point basicGetPointReverse() {
		return pointReverse;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPointReverse(Point newPointReverse, NotificationChain msgs) {
		Point oldPointReverse = pointReverse;
		pointReverse = newPointReverse;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OntrackPackage.TRACK__POINT_REVERSE, oldPointReverse, newPointReverse);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPointReverse(Point newPointReverse) {
		if (newPointReverse != pointReverse) {
			NotificationChain msgs = null;
			if (pointReverse != null)
				msgs = ((InternalEObject)pointReverse).eInverseRemove(this, OntrackPackage.POINT__REVERSE_TRACK, Point.class, msgs);
			if (newPointReverse != null)
				msgs = ((InternalEObject)newPointReverse).eInverseAdd(this, OntrackPackage.POINT__REVERSE_TRACK, Point.class, msgs);
			msgs = basicSetPointReverse(newPointReverse, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.TRACK__POINT_REVERSE, newPointReverse, newPointReverse));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Crossing getCrossing2() {
		if (crossing2 != null && crossing2.eIsProxy()) {
			InternalEObject oldCrossing2 = (InternalEObject)crossing2;
			crossing2 = (Crossing)eResolveProxy(oldCrossing2);
			if (crossing2 != oldCrossing2) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OntrackPackage.TRACK__CROSSING2, oldCrossing2, crossing2));
			}
		}
		return crossing2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Crossing basicGetCrossing2() {
		return crossing2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCrossing2(Crossing newCrossing2, NotificationChain msgs) {
		Crossing oldCrossing2 = crossing2;
		crossing2 = newCrossing2;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OntrackPackage.TRACK__CROSSING2, oldCrossing2, newCrossing2);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCrossing2(Crossing newCrossing2) {
		if (newCrossing2 != crossing2) {
			NotificationChain msgs = null;
			if (crossing2 != null)
				msgs = ((InternalEObject)crossing2).eInverseRemove(this, OntrackPackage.CROSSING__TRACK2, Crossing.class, msgs);
			if (newCrossing2 != null)
				msgs = ((InternalEObject)newCrossing2).eInverseAdd(this, OntrackPackage.CROSSING__TRACK2, Crossing.class, msgs);
			msgs = basicSetCrossing2(newCrossing2, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.TRACK__CROSSING2, newCrossing2, newCrossing2));
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
			case OntrackPackage.TRACK__C1:
				if (c1 != null)
					msgs = ((InternalEObject)c1).eInverseRemove(this, OntrackPackage.CONNECTOR__TRACK1S, Connector.class, msgs);
				return basicSetC1((Connector)otherEnd, msgs);
			case OntrackPackage.TRACK__C2:
				if (c2 != null)
					msgs = ((InternalEObject)c2).eInverseRemove(this, OntrackPackage.CONNECTOR__TRACK2S, Connector.class, msgs);
				return basicSetC2((Connector)otherEnd, msgs);
			case OntrackPackage.TRACK__POINT_REVERSE:
				if (pointReverse != null)
					msgs = ((InternalEObject)pointReverse).eInverseRemove(this, OntrackPackage.POINT__REVERSE_TRACK, Point.class, msgs);
				return basicSetPointReverse((Point)otherEnd, msgs);
			case OntrackPackage.TRACK__POINT_NORMAL:
				if (pointNormal != null)
					msgs = ((InternalEObject)pointNormal).eInverseRemove(this, OntrackPackage.POINT__NORMAL_TRACK, Point.class, msgs);
				return basicSetPointNormal((Point)otherEnd, msgs);
			case OntrackPackage.TRACK__CROSSING2:
				if (crossing2 != null)
					msgs = ((InternalEObject)crossing2).eInverseRemove(this, OntrackPackage.CROSSING__TRACK2, Crossing.class, msgs);
				return basicSetCrossing2((Crossing)otherEnd, msgs);
			case OntrackPackage.TRACK__CROSSING1:
				if (crossing1 != null)
					msgs = ((InternalEObject)crossing1).eInverseRemove(this, OntrackPackage.CROSSING__TRACK1, Crossing.class, msgs);
				return basicSetCrossing1((Crossing)otherEnd, msgs);
			case OntrackPackage.TRACK__SIGNALS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSignals()).basicAdd(otherEnd, msgs);
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
			case OntrackPackage.TRACK__C1:
				return basicSetC1(null, msgs);
			case OntrackPackage.TRACK__C2:
				return basicSetC2(null, msgs);
			case OntrackPackage.TRACK__POINT_REVERSE:
				return basicSetPointReverse(null, msgs);
			case OntrackPackage.TRACK__POINT_NORMAL:
				return basicSetPointNormal(null, msgs);
			case OntrackPackage.TRACK__CROSSING2:
				return basicSetCrossing2(null, msgs);
			case OntrackPackage.TRACK__CROSSING1:
				return basicSetCrossing1(null, msgs);
			case OntrackPackage.TRACK__DIRECTED_TRACKS:
				return ((InternalEList<?>)getDirectedTracks()).basicRemove(otherEnd, msgs);
			case OntrackPackage.TRACK__SIGNALS:
				return ((InternalEList<?>)getSignals()).basicRemove(otherEnd, msgs);
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
			case OntrackPackage.TRACK__C1:
				if (resolve) return getC1();
				return basicGetC1();
			case OntrackPackage.TRACK__C2:
				if (resolve) return getC2();
				return basicGetC2();
			case OntrackPackage.TRACK__POINT_REVERSE:
				if (resolve) return getPointReverse();
				return basicGetPointReverse();
			case OntrackPackage.TRACK__POINT_NORMAL:
				if (resolve) return getPointNormal();
				return basicGetPointNormal();
			case OntrackPackage.TRACK__CROSSING2:
				if (resolve) return getCrossing2();
				return basicGetCrossing2();
			case OntrackPackage.TRACK__CROSSING1:
				if (resolve) return getCrossing1();
				return basicGetCrossing1();
			case OntrackPackage.TRACK__DIRECTED_TRACKS:
				return getDirectedTracks();
			case OntrackPackage.TRACK__SIGNALS:
				return getSignals();
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
			case OntrackPackage.TRACK__C1:
				setC1((Connector)newValue);
				return;
			case OntrackPackage.TRACK__C2:
				setC2((Connector)newValue);
				return;
			case OntrackPackage.TRACK__POINT_REVERSE:
				setPointReverse((Point)newValue);
				return;
			case OntrackPackage.TRACK__POINT_NORMAL:
				setPointNormal((Point)newValue);
				return;
			case OntrackPackage.TRACK__CROSSING2:
				setCrossing2((Crossing)newValue);
				return;
			case OntrackPackage.TRACK__CROSSING1:
				setCrossing1((Crossing)newValue);
				return;
			case OntrackPackage.TRACK__DIRECTED_TRACKS:
				getDirectedTracks().clear();
				getDirectedTracks().addAll((Collection<? extends DirectedTrack>)newValue);
				return;
			case OntrackPackage.TRACK__SIGNALS:
				getSignals().clear();
				getSignals().addAll((Collection<? extends Signal>)newValue);
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
			case OntrackPackage.TRACK__C1:
				setC1((Connector)null);
				return;
			case OntrackPackage.TRACK__C2:
				setC2((Connector)null);
				return;
			case OntrackPackage.TRACK__POINT_REVERSE:
				setPointReverse((Point)null);
				return;
			case OntrackPackage.TRACK__POINT_NORMAL:
				setPointNormal((Point)null);
				return;
			case OntrackPackage.TRACK__CROSSING2:
				setCrossing2((Crossing)null);
				return;
			case OntrackPackage.TRACK__CROSSING1:
				setCrossing1((Crossing)null);
				return;
			case OntrackPackage.TRACK__DIRECTED_TRACKS:
				getDirectedTracks().clear();
				return;
			case OntrackPackage.TRACK__SIGNALS:
				getSignals().clear();
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
			case OntrackPackage.TRACK__C1:
				return c1 != null;
			case OntrackPackage.TRACK__C2:
				return c2 != null;
			case OntrackPackage.TRACK__POINT_REVERSE:
				return pointReverse != null;
			case OntrackPackage.TRACK__POINT_NORMAL:
				return pointNormal != null;
			case OntrackPackage.TRACK__CROSSING2:
				return crossing2 != null;
			case OntrackPackage.TRACK__CROSSING1:
				return crossing1 != null;
			case OntrackPackage.TRACK__DIRECTED_TRACKS:
				return directedTracks != null && !directedTracks.isEmpty();
			case OntrackPackage.TRACK__SIGNALS:
				return signals != null && !signals.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	public DirectedTrack getDirectedTrackByConnector(Connector c, boolean opposite){
		DirectedTrack c1 = getDirectedTracks().get(0);
		DirectedTrack c2 = getDirectedTracks().get(1);
		if (c1.getConnector()==c){
			return opposite ? c2 : c1;
		} else {
			return opposite ? c1 : c2;
		}
	}

} //TrackImpl
