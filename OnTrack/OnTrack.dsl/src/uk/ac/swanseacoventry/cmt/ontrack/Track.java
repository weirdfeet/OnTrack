package uk.ac.swanseacoventry.cmt.ontrack;

import org.eclipse.emf.common.util.EList;

/**
 * @model
 */

public interface Track extends Unit {


	/**
	 * @model 
	 */
	Connector getC1();
	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.Track#getC1 <em>C1</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>C1</em>' reference.
	 * @see #getC1()
	 * @generated
	 */
	void setC1(Connector value);
	/**
	 * @model 
	 */
	Connector getC2();
	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.Track#getC2 <em>C2</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>C2</em>' reference.
	 * @see #getC2()
	 * @generated
	 */
	void setC2(Connector value);
	
	/**
	 * @model opposite="reverseTrack"
	 */
	Point getPointReverse();

	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.Track#getPointReverse <em>Point Reverse</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Point Reverse</em>' reference.
	 * @see #getPointReverse()
	 * @generated
	 */
	void setPointReverse(Point value);

	/**
	 * @model opposite="normalTrack"
	 */
	Point getPointNormal();
	
	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.Track#getPointNormal <em>Point Normal</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Point Normal</em>' reference.
	 * @see #getPointNormal()
	 * @generated
	 */
	void setPointNormal(Point value);

	/**
	 * @model opposite="track2"
	 */
	Crossing getCrossing2();

	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.Track#getCrossing2 <em>Crossing2</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Crossing2</em>' reference.
	 * @see #getCrossing2()
	 * @generated
	 */
	void setCrossing2(Crossing value);

	/**
	 * @model opposite="track1"
	 */
	Crossing getCrossing1();

	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.Track#getCrossing1 <em>Crossing1</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Crossing1</em>' reference.
	 * @see #getCrossing1()
	 * @generated
	 */
	void setCrossing1(Crossing value);

	/**
	 * @model containment="true"
	 */
	EList<DirectedTrack> getDirectedTracks();
	
	/**
	 * @model opposite="track"
	 */
	EList<Signal> getSignals();
	
	Point getPoint();
	Crossing getCrossing();
	public DirectedTrack getDirectedTrackByConnector(Connector c, boolean opposite);
}
