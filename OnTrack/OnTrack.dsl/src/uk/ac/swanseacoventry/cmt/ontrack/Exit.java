package uk.ac.swanseacoventry.cmt.ontrack;

import org.eclipse.emf.ecore.EObject;

/**
 * @model
 */
public interface Exit extends EObject {

	/**
	 * @model
	 */
	Connector getConnector();

	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.Exit#getConnector <em>Connector</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connector</em>' reference.
	 * @see #getConnector()
	 * @generated
	 */
	void setConnector(Connector value);
}
