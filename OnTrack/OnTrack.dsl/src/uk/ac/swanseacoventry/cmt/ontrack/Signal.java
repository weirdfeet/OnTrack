package uk.ac.swanseacoventry.cmt.ontrack;

import org.eclipse.emf.ecore.EObject;

/**
 * @model
 */

public interface Signal extends EObject {

	/**
	 * @model 
	 */
	public String getName();

	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.Signal#getName <em>Name</em>}' attribute.
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
	public Track getTrack();

	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.Signal#getTrack <em>Track</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Track</em>' reference.
	 * @see #getTrack()
	 * @generated
	 */
	void setTrack(Track value);

	/**
	 * @model 
	 */
	public Connector getConnector();

	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.Signal#getConnector <em>Connector</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connector</em>' reference.
	 * @see #getConnector()
	 * @generated
	 */
	void setConnector(Connector value);
	
	public DirectedTrack getDirectedTrack();

}
