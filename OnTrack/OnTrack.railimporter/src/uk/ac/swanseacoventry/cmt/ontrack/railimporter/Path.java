package uk.ac.swanseacoventry.cmt.ontrack.railimporter;

import java.util.ArrayList;

public class Path
{
	private String name;
	private Node startNode;
	private Node endNode;
	private double length;
	private double speedLimitUp;
	private double speedLimitDown;
	private double gradient;
	private ArrayList<TrackCircuit> tracks = new ArrayList<TrackCircuit>();
	
	
	public void setStartNode(Node startNode) {
		this.startNode = startNode;
	}

	public void setEndNode(Node endNode) {
		this.endNode = endNode;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public void setSpeedLimitUp(double speedLimitUp) {
		this.speedLimitUp = speedLimitUp;
	}

	public void setSpeedLimitDown(double speedLimitDown) {
		this.speedLimitDown = speedLimitDown;
	}

	public void setGradient(double gradient) {
		this.gradient = gradient;
	}

	public ArrayList<TrackCircuit> getTracks() {
		return this.tracks;
	}	
	
	public void addTrack(TrackCircuit t) {
	    this.tracks.add(t);
	}
	
	public Path(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public Node getStartNode() {
		return startNode;
	}

	public Node getEndNode() {
		return endNode;
	}

	public double getLength() {
		return length;
	}

	public double getSpeedLimitUp() {
		return speedLimitUp;
	}

	public double getSpeedLimitDown() {
		return speedLimitDown;
	}

	public double getGradient() {
		return gradient;
	}

	@Override
	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append("Path Name: ");
		result.append(name);
		result.append(" Start Node: ");
		result.append(startNode.getName());
		result.append(" End Node: ");
		result.append(endNode.getName());
		result.append(" Length: ");
		result.append(length);
		result.append(" Speed Limit Up: ");
		result.append(speedLimitUp);
		result.append(" Speed Limit Down: ");
		result.append(speedLimitDown);
		result.append(" Gradient: ");
		result.append(gradient); 
		return result.toString();
	}	
	
}