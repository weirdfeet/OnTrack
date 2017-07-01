package uk.ac.swanseacoventry.cmt.ontrack.railimporter;

public class Diamond extends Node {
	
	public String getMainEnter() {
		return mainEnter;
	}

	public void setMainEnter(String mainEnter) {
		this.mainEnter = mainEnter;
	}

	public String getMainExit() {
		return mainExit;
	}

	public void setMainExit(String mainExit) {
		this.mainExit = mainExit;
	}

	public String getBranchEnter() {
		return branchEnter;
	}

	public void setBranchEnter(String branchEnter) {
		this.branchEnter = branchEnter;
	}

	public String getBranchExit() {
		return branchExit;
	}

	public void setBranchExit(String branchExit) {
		this.branchExit = branchExit;
	}

	private String mainEnter;
	private String mainExit;
	private String branchEnter;
	private String branchExit;
	
	public Diamond(String name) {
		super(name);
	}

}
