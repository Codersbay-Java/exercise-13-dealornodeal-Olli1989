package application;

import java.util.List;

public class Banker {
	
	public int getOffer(List<Case> cases, double playersCaseValue, int roundNumber) {
		int totalValue = 0;
		int remainingCases = 0;
		for(int i = 0; i < cases.size(); i++) {
			if(!cases.get(i).isRemoved()) {
				remainingCases++;
				totalValue += cases.get(i).getValue();
			}
		}
		
		totalValue += playersCaseValue;
		remainingCases++;
		
		return (int)((totalValue / remainingCases) * roundNumber / 10);
	}
}
