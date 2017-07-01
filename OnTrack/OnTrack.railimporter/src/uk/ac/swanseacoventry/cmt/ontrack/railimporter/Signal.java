package uk.ac.swanseacoventry.cmt.ontrack.railimporter;

public class Signal extends Node
{
	public void setSwitchTime(int switchTime) {
		this.switchTime = switchTime;
	}

	public void setDirPath(String dirPath) {
		this.dirPath = dirPath;
	}

	private int switchTime;
	private String dirPath;
		
	public Signal(String name){
		super(name);
	}
	
	public int getSwitchTime() {
		return switchTime;
	}
	
	public String getDirPath() {
		return dirPath;
	}

	@Override
	public String toString(){
		return toStringAux().toString();
	}
	
	public StringBuilder toStringAux(){
		StringBuilder result = super.toStringAux();
		result.append(", Direction path: ");
		result.append(dirPath);
		result.append(", Switch time: ");
		result.append(switchTime);
		return result;
	}
		
}