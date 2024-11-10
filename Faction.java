package project2;
import java.util.ArrayList;
import java.util.Random;

public class Faction {
	
	private ArrayList <ResourceGenerator> rGens; //naming it resourceGenerators is too long.
	private int stockA = 0;
	private int stockB = 0;
	private int stockC = 0; // initial number of stocks A,B and C for each faction.
	
	
	
	//Constructor
	public Faction() {
		rGens = new ArrayList<ResourceGenerator>();
		rGens.add(new ResourceGenerator());
		rGens.add(new ResourceGenerator());
	}
	
	public void generateResources(int num) {
		for (ResourceGenerator r : rGens) {
			String resource = r.generateResource(num);//generate resource for each ResourceGenerator instance in rGens
			if(resource!=null) { //as long as the return value is not null.
			if(resource.equals("A")) stockA++; // if it returns A, increase stockA.
			else if(resource.equals("B")) stockB++; // if it returns B, increase stockB.
			else if(resource.equals("C")) stockC++; // if it returns C, increase stockC. 
		}}
	}
	
	//Private method to get eligible resources because not all resources might be over the threshold.
	//Easier to select randomly
	private ArrayList<Character> eligibleResources(){ 
		ArrayList<Character> er = new ArrayList<Character>(); //temporary ArrayList for eligible resources
		if(stockA>=8) er.add('A'); // if stockA is above threshold, add A.
		if(stockB>=8) er.add('B'); // same as above
		if(stockC>=8) er.add('C'); // same as above
		return er; 
	}
	
	//Private helper methods for stealResourceFrom method starts here.
	//Helps identify if the other faction has stock or not.
	private boolean hasResource() {
		return stockA > 0 || stockB > 0 || stockC > 0;
	}
	//Helps choose a random resource from private fields.
	private char selectRandomResource() {
		char[] resources = {'A','B','C'};
		char selectedResource = resources[new Random().nextInt(resources.length)]; 
		return selectedResource;
	}
	
	//Helps to steal the stock amount and set the stolen stock to zero.
	private int getStock(char type) {
		int stock = 0;
		if (type == 'A') {
			stock = stockA;
			stockA = 0;
		} else if (type == 'B') {
			stock = stockB;
			stockB = 0;
		} else if (type == 'C') {
			stock = stockC;
			stockC = 0;
		}
		return stock;
	}
	
	//Helps to increment stolen stock.
	private void incrementStock(char type, int amount) {
		if (type == 'A') stockA += amount;
		if (type == 'B') stockB += amount;
		if (type == 'C') stockC += amount;
	}
	//Ends here. Above helper methods will be used in stealResourceFrom().
	
	
	public void loseOverflow() {
		ArrayList<Character> er = eligibleResources(); // called private method to get eligible resources.
		if(!er.isEmpty()) { // condition check. if the list is not empty because it might be.
			
			// select random stock from er by getting a random index generated from 0 to size of er.
			char randomstock = er.get(new Random().nextInt(er.size()));
			if(randomstock=='A') stockA/=2; //integer operation, round down automatically.
			if(randomstock=='B') stockB/=2;
			if(randomstock=='C') stockC/=2;
		}
		
	}
	
	public void stealResourceFrom(Faction otherFaction) {
		if(otherFaction.hasResource()) { // check if the other faction has resources
			char chosenResource = otherFaction.selectRandomResource(); // randomly selected chosen resource
			int amount = otherFaction.getStock(chosenResource); // amount of chosen resource
			// increment resource accordingly.
			if (chosenResource == 'A') this.incrementStock(chosenResource, amount);
			if (chosenResource == 'B') this.incrementStock(chosenResource, amount);
			if (chosenResource == 'C') this.incrementStock(chosenResource, amount);
		}
		else System.out.println("The other faction has no resources to steal");
	}
	
	
	public void constructNewGenerators() {
		while (stockA>0&&stockB>0&&stockC>0) { //while there is at least one stock
			rGens.add(new ResourceGenerator()); //Add a new ResourceGenerator
			stockA--;//decrement each stock by 1.
			stockB--;
			stockC--;
		}
	}
	
	//Private Helper getter methods to construct toString.
	private int getStockA() {
		return stockA;
	}
	private int getStockB() {
		return stockB;
	}
	private int getStockC() {
		return stockC;
	}


	public String toString() {
		return 	"A: " + getStockA() + "\n" + 
				"B: " + getStockB() + "\n" + 
				"C: " + getStockC() + "\n" + 
				"ResourceGenerators: " + getNumberOfResourceGenerators() + "\n";
	}
	
	public int getNumberOfResourceGenerators() {
		return rGens.size(); //return the number of elements in the ArrayList rGens.
	}
}
