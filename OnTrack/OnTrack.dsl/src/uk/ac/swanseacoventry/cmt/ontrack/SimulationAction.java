package uk.ac.swanseacoventry.cmt.ontrack;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * @model 
 */
public interface SimulationAction extends EObject {

	/**
	 * @model 
	 */
	public String getName();
	
	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.SimulationAction#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * @model
	 */
	public EList<String> getParameters();
		
}
