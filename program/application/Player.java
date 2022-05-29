package application;

public class Player {
	
	private String name;
	private int choosenCaseNumber;
	
	public Player(String name, int choosenCaseNumber) {
		this.name = name;
		this.choosenCaseNumber = choosenCaseNumber;
	}
	
	public int getChoosenCaseNumeber () {
		return choosenCaseNumber;
	}
	
	public String getName() {
		return name;
	}

}
