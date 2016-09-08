package uk.ac.swanseacoventry.cmt.ontrack;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * @model
 */

public interface TrackPlan extends EObject {

	/**
	 * @model containment="true"
	 */
	EList<Track> getTracks();
	
	/**
	 * @model containment="true"
	 */
	EList<Connector> getConnectors();

	/**
	 * @model containment="true"
	 */
	EList<Point> getPoints();

	/**
	 * @model containment="true"
	 */
	EList<Crossing> getCrossings();

	/**
	 * @model containment="true"
	 */
	EList<Signal> getSignals();

	/**
	 * @model containment="true"
	 */
	EList<Entrance> getEntrances();

	/**
	 * @model containment="true"
	 */
	EList<Exit> getExits();

	/**
	 * @model containment="true"
	 */
	EList<Terminal> getTerminals();

	/**
	 * @model containment="true"
	 */
	EList<TopoRoute> getTopoRoutes();

	/**
	 * @model containment="true"
	 */
	EList<ControlTableItem> getControlTable();

	/**
	 * @model containment="true"
	 */
	EList<ReleaseTableItem> getReleaseTable();
	
	/**
	 * @model containment="true"
	 */
	NewTrack getNewTrack();
	
	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getNewTrack <em>New Track</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>New Track</em>' containment reference.
	 * @see #getNewTrack()
	 * @generated
	 */
	void setNewTrack(NewTrack value);

	/**
	 * @model containment="true"
	 */
	NewPoint getNewPoint();
	
	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getNewPoint <em>New Point</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>New Point</em>' containment reference.
	 * @see #getNewPoint()
	 * @generated
	 */
	void setNewPoint(NewPoint value);

	/**
	 * @model containment="true"
	 */
	NewCrossing getNewCrossing();
	
	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getNewCrossing <em>New Crossing</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>New Crossing</em>' containment reference.
	 * @see #getNewCrossing()
	 * @generated
	 */
	void setNewCrossing(NewCrossing value);

	/**
	 * @model containment="true"
	 */
	EList<SubTrackPlan> getSubTrackPlans();
	
	/**
	 * @model
	 */
	SubTrackPlan getSelectedSubTrackPlan();

	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.TrackPlan#getSelectedSubTrackPlan <em>Selected Sub Track Plan</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Selected Sub Track Plan</em>' reference.
	 * @see #getSelectedSubTrackPlan()
	 * @generated
	 */
	void setSelectedSubTrackPlan(SubTrackPlan value);
}
