/**
 */
package uk.ac.swanseacoventry.cmt.ontrack;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see uk.ac.swanseacoventry.cmt.ontrack.OntrackPackage
 * @generated
 */
public interface OntrackFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OntrackFactory eINSTANCE = uk.ac.swanseacoventry.cmt.ontrack.impl.OntrackFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Connector</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connector</em>'.
	 * @generated
	 */
	Connector createConnector();

	/**
	 * Returns a new object of class '<em>Control Table Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Control Table Item</em>'.
	 * @generated
	 */
	ControlTableItem createControlTableItem();

	/**
	 * Returns a new object of class '<em>Crossing</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Crossing</em>'.
	 * @generated
	 */
	Crossing createCrossing();

	/**
	 * Returns a new object of class '<em>Directed Track</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Directed Track</em>'.
	 * @generated
	 */
	DirectedTrack createDirectedTrack();

	/**
	 * Returns a new object of class '<em>Entrance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Entrance</em>'.
	 * @generated
	 */
	Entrance createEntrance();

	/**
	 * Returns a new object of class '<em>Exit</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Exit</em>'.
	 * @generated
	 */
	Exit createExit();

	/**
	 * Returns a new object of class '<em>New Crossing</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>New Crossing</em>'.
	 * @generated
	 */
	NewCrossing createNewCrossing();

	/**
	 * Returns a new object of class '<em>New Point</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>New Point</em>'.
	 * @generated
	 */
	NewPoint createNewPoint();

	/**
	 * Returns a new object of class '<em>New Track</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>New Track</em>'.
	 * @generated
	 */
	NewTrack createNewTrack();

	/**
	 * Returns a new object of class '<em>Point</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Point</em>'.
	 * @generated
	 */
	Point createPoint();

	/**
	 * Returns a new object of class '<em>Release Table Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Release Table Item</em>'.
	 * @generated
	 */
	ReleaseTableItem createReleaseTableItem();

	/**
	 * Returns a new object of class '<em>Signal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Signal</em>'.
	 * @generated
	 */
	Signal createSignal();

	/**
	 * Returns a new object of class '<em>Sub Track Plan</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sub Track Plan</em>'.
	 * @generated
	 */
	SubTrackPlan createSubTrackPlan();

	/**
	 * Returns a new object of class '<em>Terminal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Terminal</em>'.
	 * @generated
	 */
	Terminal createTerminal();

	/**
	 * Returns a new object of class '<em>Topo Route</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Topo Route</em>'.
	 * @generated
	 */
	TopoRoute createTopoRoute();

	/**
	 * Returns a new object of class '<em>Track</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Track</em>'.
	 * @generated
	 */
	Track createTrack();

	/**
	 * Returns a new object of class '<em>Track Plan</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Track Plan</em>'.
	 * @generated
	 */
	TrackPlan createTrackPlan();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	OntrackPackage getOntrackPackage();

} //OntrackFactory
