package uk.ac.swanseacoventry.cmt.ontrack;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * @model
 */

public interface SubTrackPlan extends EObject {
	
	/**
	 * @model 
	 */
	String getVerificationTime();

	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan#getVerificationTime <em>Verification Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Verification Time</em>' attribute.
	 * @see #getVerificationTime()
	 * @generated
	 */
	void setVerificationTime(String value);

	/**
	 * @model 
	 */
	String getVerificationStates();

	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan#getVerificationStates <em>Verification States</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Verification States</em>' attribute.
	 * @see #getVerificationStates()
	 * @generated
	 */
	void setVerificationStates(String value);

	/**
	 * @model 
	 */
	String getVerificationResult();

	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan#getVerificationResult <em>Verification Result</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Verification Result</em>' attribute.
	 * @see #getVerificationResult()
	 * @generated
	 */
	void setVerificationResult(String value);

	/**
	 * @model 
	 */
	EList<Track> getCriticals();
	

	/**
	 * @model 
	 */
	EList<Track> getTracks();
	
	/**
	 * @model 
	 */
	EList<Connector> getConnectors();

	/**
	 * @model 
	 */
	EList<Point> getPoints();

	/**
	 * @model 
	 */
	EList<Crossing> getCrossings();

	/**
	 * @model 
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
	 * @model 
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
	
	public String getName();
	public String getTrackList();
	public String getPointList();
	public String getSignalList();
	public String getRouteList();
}
