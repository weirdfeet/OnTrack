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
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import uk.ac.swanseacoventry.cmt.ontrack.OntrackPackage;
import uk.ac.swanseacoventry.cmt.ontrack.Simulation;
import uk.ac.swanseacoventry.cmt.ontrack.SimulationAction;
import uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Simulation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.SimulationImpl#getName <em>Name</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.SimulationImpl#getSteps <em>Steps</em>}</li>
 *   <li>{@link uk.ac.swanseacoventry.cmt.ontrack.impl.SimulationImpl#getSubTrackPlan <em>Sub Track Plan</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SimulationImpl extends MinimalEObjectImpl.Container implements Simulation {
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
	 * The cached value of the '{@link #getSteps() <em>Steps</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSteps()
	 * @generated
	 * @ordered
	 */
	protected EList<SimulationAction> steps;

	/**
	 * The cached value of the '{@link #getSubTrackPlan() <em>Sub Track Plan</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubTrackPlan()
	 * @generated
	 * @ordered
	 */
	protected SubTrackPlan subTrackPlan;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SimulationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OntrackPackage.Literals.SIMULATION;
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
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.SIMULATION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SimulationAction> getSteps() {
		if (steps == null) {
			steps = new EObjectContainmentEList<SimulationAction>(SimulationAction.class, this, OntrackPackage.SIMULATION__STEPS);
		}
		return steps;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SubTrackPlan getSubTrackPlan() {
		if (subTrackPlan != null && subTrackPlan.eIsProxy()) {
			InternalEObject oldSubTrackPlan = (InternalEObject)subTrackPlan;
			subTrackPlan = (SubTrackPlan)eResolveProxy(oldSubTrackPlan);
			if (subTrackPlan != oldSubTrackPlan) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OntrackPackage.SIMULATION__SUB_TRACK_PLAN, oldSubTrackPlan, subTrackPlan));
			}
		}
		return subTrackPlan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SubTrackPlan basicGetSubTrackPlan() {
		return subTrackPlan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubTrackPlan(SubTrackPlan newSubTrackPlan) {
		SubTrackPlan oldSubTrackPlan = subTrackPlan;
		subTrackPlan = newSubTrackPlan;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OntrackPackage.SIMULATION__SUB_TRACK_PLAN, oldSubTrackPlan, subTrackPlan));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OntrackPackage.SIMULATION__STEPS:
				return ((InternalEList<?>)getSteps()).basicRemove(otherEnd, msgs);
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
			case OntrackPackage.SIMULATION__NAME:
				return getName();
			case OntrackPackage.SIMULATION__STEPS:
				return getSteps();
			case OntrackPackage.SIMULATION__SUB_TRACK_PLAN:
				if (resolve) return getSubTrackPlan();
				return basicGetSubTrackPlan();
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
			case OntrackPackage.SIMULATION__NAME:
				setName((String)newValue);
				return;
			case OntrackPackage.SIMULATION__STEPS:
				getSteps().clear();
				getSteps().addAll((Collection<? extends SimulationAction>)newValue);
				return;
			case OntrackPackage.SIMULATION__SUB_TRACK_PLAN:
				setSubTrackPlan((SubTrackPlan)newValue);
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
			case OntrackPackage.SIMULATION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case OntrackPackage.SIMULATION__STEPS:
				getSteps().clear();
				return;
			case OntrackPackage.SIMULATION__SUB_TRACK_PLAN:
				setSubTrackPlan((SubTrackPlan)null);
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
			case OntrackPackage.SIMULATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case OntrackPackage.SIMULATION__STEPS:
				return steps != null && !steps.isEmpty();
			case OntrackPackage.SIMULATION__SUB_TRACK_PLAN:
				return subTrackPlan != null;
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

} //SimulationImpl
