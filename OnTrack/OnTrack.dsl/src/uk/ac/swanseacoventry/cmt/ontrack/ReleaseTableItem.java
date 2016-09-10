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
	public Track getTrack();

	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.ReleaseTableItem#getTrack <em>Track</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Track</em>' reference.
	 * @see #getTrack()
	 * @generated
	 */
	void setTrack(Track value);
}
