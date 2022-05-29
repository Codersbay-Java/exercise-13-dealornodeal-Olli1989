package application;


public class Case{
	
	private int caseNumber;
	private double value;
	private boolean isRemoved;
	
	public Case(int caseNumber, double value) {
		this.caseNumber= caseNumber;
		this.value = value;
		this.isRemoved = false;
	}

	public int getCaseNumber() {
		return caseNumber;
	}

	public double getValue() {
		return value;
	}

	public boolean isRemoved() {
		return isRemoved;
	}
	
	public void setIsRemoved () {
		this.isRemoved = true;
	}

}
