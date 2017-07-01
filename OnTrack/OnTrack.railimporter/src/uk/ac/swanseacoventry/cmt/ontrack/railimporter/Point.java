package uk.ac.swanseacoventry.cmt.ontrack.railimporter;

public class Point extends Node
{
	public void setSwitchTime(int switchTime) {
		this.switchTime = switchTime;
	}

	public void setEnterPath(String enterPath) {
		this.enterPath = enterPath;
	}

	public void setExitPath(String exitPath) {
		this.exitPath = exitPath;
	}

	public void setBranchPath(String branchPath) {
		this.branchPath = branchPath;
	}

	private int switchTime;
	private String enterPath;
	private String exitPath;
	private String branchPath;
	
		
	public Point(String name){
		super(name);
	}
	
	public int getSwitchTime() {
		return switchTime;
	}
	
	public String getEnterPath() {
		return enterPath;
	}

	public String getExitPath() {
		return exitPath;
	}

	public String getBranchPath() {
		return branchPath;
	}

	@Override
	public String toString(){
		return toStringAux().toString();
	}
	
	public StringBuilder toStringAux(){
		StringBuilder result = super.toStringAux();
		result.append(", Enter path: ");
		result.append(enterPath);
		result.append(", Exit path: ");
		result.append(exitPath);
		result.append(", Branch path: ");
		result.append(branchPath);
		result.append(", Swith time: ");
		result.append(switchTime);
		return result;
	}
	
	
	
}