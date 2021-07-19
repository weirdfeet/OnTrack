package uk.ac.swanseacoventry.cmt.ontrack.railimporter;

import java.util.ArrayList;

public class Node
{
	public ArrayList<String> getPaths() {
		return paths;
	}
	
	public void addPath(String p) {
	    this.paths.add(p);
	}


	public String getLocationX() {
		return locationX;
	}


	public void setLocationX(String location) {
		this.locationX = location;
	}


	public String getLocationY() {
		return locationY;
	}


	public void setLocationY(String location) {
		this.locationY = location;
	}
 
	private String name;
	private String locationX;
	private String locationY;
	private ArrayList<String> paths = new  ArrayList<String>();
	
	public Node(String name){
		this.name = name;
	}

	
	public String getName(){
		return name;
	}
	
	@Override
	public String toString(){
		return toStringAux().toString();
	}
	
	public StringBuilder toStringAux(){
		StringBuilder result = new StringBuilder();
		result.append("Node: ");
		result.append(name);
		result.append(", Location: ");
		result.append(locationX + ":" + locationY);
		return result;
	}
	
	
}