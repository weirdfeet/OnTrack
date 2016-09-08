package uk.ac.swanseacoventry.cmt.ontrack;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * @model 
 */


public interface TopoRoute extends EObject {

	/**
	 * @model 
	 */
	public EList<String> getNames();
	
	/**
	 * @model
	 */
	public EList<DirectedTrack> getDirectedTracks();
	
	/**
	 * @model 
	 */
	public Signal getStartSignal();
	
	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.TopoRoute#getStartSignal <em>Start Signal</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Signal</em>' reference.
	 * @see #getStartSignal()
	 * @generated
	 */
	void setStartSignal(Signal value);

	/**
	 * @model 
	 */
	public Signal getEndSignal();

	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.TopoRoute#getEndSignal <em>End Signal</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End Signal</em>' reference.
	 * @see #getEndSignal()
	 * @generated
	 */
	void setEndSignal(Signal value);
}
