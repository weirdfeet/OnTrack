package uk.ac.swanseacoventry.cmt.ontrack;

/**
 * @model 
 */
public interface Crossing extends Unit {

	/**
	 * @model opposite="crossing1"
	 */
	public Track getTrack1();
	
	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.Crossing#getTrack1 <em>Track1</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Track1</em>' reference.
	 * @see #getTrack1()
	 * @generated
	 */
	void setTrack1(Track value);

	/**
	 * @model opposite="crossing2"
	 */
	public Track getTrack2();

	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.Crossing#getTrack2 <em>Track2</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Track2</em>' reference.
	 * @see #getTrack2()
	 * @generated
	 */
	void setTrack2(Track value);
	
}
