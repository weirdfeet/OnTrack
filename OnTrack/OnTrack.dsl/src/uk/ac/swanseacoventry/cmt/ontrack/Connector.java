package uk.ac.swanseacoventry.cmt.ontrack;

import org.eclipse.emf.ecore.EObject;

import java.util.List;

import org.eclipse.emf.common.util.EList;

/**
 * @model 
 */
public interface Connector extends EObject {

	/**
	 * @model 
	 */
	public int getId();
	
    /**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.Connector#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(int value);

				/**
	 * @model opposite="c1"
	 */
	EList<Track> getTrack1s();
	
    /**
	 * @model opposite="c2"
	 */
	EList<Track> getTrack2s();
	
	List<Track> getTracks();
	
	/**
	 * @model opposite="connector"
	 */
	EList<Signal> getSignals();
	
	/**
	 * @model opposite="connector"
	 */
	Terminal getTerminal();

	/**
	 * Sets the value of the '{@link uk.ac.swanseacoventry.cmt.ontrack.Connector#getTerminal <em>Terminal</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Terminal</em>' reference.
	 * @see #getTerminal()
	 * @generated
	 */
	void setTerminal(Terminal value);

	/**
	 * @model opposite="connector"
	 */
	EList<Entrance> getEntrances();

	/**
	 * @model opposite="connector"
	 */
	EList<Exit> getExits();
	
	public int getFreshID(TrackPlan tp);
	
}
