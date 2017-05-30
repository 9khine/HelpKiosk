package com.prototype.helpkiosk.instruction;

public class Instruction {
	
	private String instruction;
	private boolean hasMoreHelp;
	private int[] moreHelpID;
	private boolean isActive = false;
	private boolean isDone = false;
	private MoreHelp[] moreHelp = null;
	private String type;
	private int id;
	private boolean hasCheckmark;
	
	public Instruction(String instruction, boolean hasMoreHelp, int[] moreHelpID, String type, int id, boolean hasChecmark){
		this.setInstruction(instruction);
		this.setHasMoreHelp(hasMoreHelp);
		this.setMoreHelpID(moreHelpID);
		this.setType(type);
		this.setId(id);
		this.setHasCheckmark(hasChecmark);
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setHasMoreHelp(boolean hasMoreHelp) {
		this.hasMoreHelp = hasMoreHelp;
	}

	public boolean isHasMoreHelp() {
		return hasMoreHelp;
	}

	public void setMoreHelpID(int[] moreHelpID) {
		this.moreHelpID = moreHelpID;
	}

	public int[] getMoreHelpID() {
		return moreHelpID;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setDone(boolean isDone) {
		System.out.println("instruction id="+this.id+" is set to be " + isDone);
		this.isDone = isDone;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setHasCheckmark(boolean hasCheckmark) {
		this.hasCheckmark = hasCheckmark;
	}

	public boolean isHasCheckmark() {
		return hasCheckmark;
	}
	
}
