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
