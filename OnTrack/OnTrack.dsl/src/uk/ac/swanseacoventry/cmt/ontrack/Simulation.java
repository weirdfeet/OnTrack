package uk.ac.swanseacoventry.cmt.ontrack;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * @model 
 */
public interface Simulation extends EObject {

	/**
	 * @model 
	 */
	public String getName();
	
	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.Simulation#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * @model containment="true"
	 */
	public EList<SimulationAction> getSteps();
		
	/**
	 * @model
	 */
	public SubTrackPlan getSubTrackPlan();

	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.Simulation#getSubTrackPlan <em>Sub Track Plan</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sub Track Plan</em>' reference.
	 * @see #getSubTrackPlan()
	 * @generated
	 */
	void setSubTrackPlan(SubTrackPlan value);

}
