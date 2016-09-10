package uk.ac.swanseacoventry.cmt.ontrack;

import java.util.List;
import org.eclipse.emf.ecore.EObject;

/**
 * @model 
 */

public interface DirectedTrack extends EObject {

	/**
	 * @model 
	 */
	public Track getTrack();
	
	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.DirectedTrack#getTrack <em>Track</em>}' reference.
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
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.DirectedTrack#getConnector <em>Connector</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connector</em>' reference.
	 * @see #getConnector()
	 * @generated
	 */
	void setConnector(Connector value);
	
	List<DirectedTrack> getNexts();
	List<DirectedTrack> getPrevs();
	
	public Signal getSignal();
	public Connector getOppositeConnector();
}
