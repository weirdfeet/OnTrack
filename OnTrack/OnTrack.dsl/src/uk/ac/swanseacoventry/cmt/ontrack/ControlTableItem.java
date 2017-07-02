package uk.ac.swanseacoventry.cmt.ontrack;

import java.util.ArrayList;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * @model 
 */
public interface ControlTableItem extends EObject {

	/**
	 * @model 
	 */
	public Signal getSignal();

	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem#getSignal <em>Signal</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Signal</em>' reference.
	 * @see #getSignal()
	 * @generated
	 */
	void setSignal(Signal value);

	/**
	 * @model 
	 */
	public String getRoute();
	
	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem#getRoute <em>Route</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Route</em>' attribute.
	 * @see #getRoute()
	 * @generated
	 */
	void setRoute(String value);

	/**
	 * @model 
	 */
	public EList<Point> getNormals();
	
	/**
	 * @model 
	 */
	public EList<Point> getReverses();
	
	/**
	 * @model 
	 */
	public EList<Track> getClears();
	
	/**
	 * @model 
	 */
	public EList<DirectedTrack> getDirections();
	
	public String getNormalsSequence(TrackPlan tp);
	public String getReversesSequence(TrackPlan tp);
	public String getClearsSequence();
	public String getDirectionsSequence();
	public ArrayList<DirectedTrack> guessDirections();
}
