package uk.ac.swanseacoventry.cmt.ontrack;

import org.eclipse.emf.ecore.EObject;

/**
 * @model abstract="true"
 */


public interface Unit extends EObject {

	/**
	 * @model 
	 */
	public String getName();

	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.Unit#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

}
