package uk.ac.swanseacoventry.cmt.ontrack.railimporter;

import java.util.ArrayList;

public class TopologicalRoute {
	private Signal entrySignal;
	private ArrayList<TrackCircuit> trackCircuits;
	
	public TopologicalRoute(Signal entrySignal){
		this.entrySignal = entrySignal;
		trackCircuits = new ArrayList<TrackCircuit>();
	}
	
	public void addTrackCircuit(TrackCircuit tc){
		trackCircuits.add(tc);
	}
	
}
