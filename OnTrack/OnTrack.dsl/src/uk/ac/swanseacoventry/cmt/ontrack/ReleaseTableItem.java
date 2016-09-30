package uk.ac.swanseacoventry.cmt.ontrack;

import org.eclipse.emf.ecore.EObject;

/**
 * @model 
 */

public interface ReleaseTableItem extends EObject {
	/**
	 * @model 
	 */
	public String getRoute();
	
	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.ReleaseTableItem#getRoute <em>Route</em>}' attribute.
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
	public Point getPoint();
	
	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.ReleaseTableItem#getPoint <em>Point</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Point</em>' reference.
	 * @see #getPoint()
	 * @generated
	 */
	void setPoint(Point value);

	/**
	 * @model 
	 */
	public Track getUnoccupiedTrack();

	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.ReleaseTableItem#getUnoccupiedTrack <em>Unoccupied Track</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unoccupied Track</em>' reference.
	 * @see #getUnoccupiedTrack()
	 * @generated
	 */
	void setUnoccupiedTrack(Track value);

	/**
	 * @model 
	 */
	public Track getOccupiedTrack();

	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.ReleaseTableItem#getOccupiedTrack <em>Occupied Track</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Occupied Track</em>' reference.
	 * @see #getOccupiedTrack()
	 * @generated
	 */
	void setOccupiedTrack(Track value);
}
