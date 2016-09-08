package uk.ac.swanseacoventry.cmt.ontrack;

/**
 * @model 
 */

public interface Point extends Unit {
	
	/**
	 * @model opposite="pointNormal"
	 */
	Track getNormalTrack();

	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.Point#getNormalTrack <em>Normal Track</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Normal Track</em>' reference.
	 * @see #getNormalTrack()
	 * @generated
	 */
	void setNormalTrack(Track value);

	/**
	 * @model opposite="pointReverse"
	 */
	Track getReverseTrack();

	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.Point#getReverseTrack <em>Reverse Track</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reverse Track</em>' reference.
	 * @see #getReverseTrack()
	 * @generated
	 */
	void setReverseTrack(Track value);

}
