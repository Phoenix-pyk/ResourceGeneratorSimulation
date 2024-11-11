package project2;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
public class Simulation {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		//Ask user input
		System.out.print("Enter number of factions: ");
		int numfactions = scanner.nextInt();
		System.out.print("Enter number of resource generators for victory: ");
		int gensForvic = scanner.nextInt();
		
		//Construct an arraylist factions with user input
		ArrayList<Faction> factions = new ArrayList<Faction>();
		construct(factions,numfactions);
		
		int i = 0; //The loop will start from zero.
		//Start of the game here
		while(true){ //every faction takes turn numerically
			int roll = ResourceGenerator.rollDice();
			System.out.print("Faction " + i + " rolled a " + roll + ".\n");
			
			if(roll!=7) {
				for(Faction f : factions)f.generateResources(roll); //Every faction generate resources based on roll.
				System.out.println("Generating resources...");
			} else { // if the roll does equal to 7.
				for(Faction f : factions) f.loseOverflow(); //Every faction lose resources
				ResourceGenerator.blockNumber(ResourceGenerator.rollDice());//block a random number chosen by random dice roll.
				Faction randomfaction;
				int randomindex;
				do{ // a do while loop ensure that the code is run at least one time.
					randomindex = new Random().nextInt(factions.size());
					randomfaction = factions.get(randomindex);
				}while(i==randomindex); // ensures that the current faction does not randomly choose itself.
				factions.get(i).stealResourceFrom(randomfaction);//current faction steals resources from another faction chosen at random.
				System.out.println(
						"Eliminating Overflow... \n" +
						"Blocking Number... \n" +
						"Stealing Resources...");
						
			}
			System.out.print(factions.get(i).toString()); //Print current faction's #of resources and generators.
			factions.get(i).constructNewGenerators();//Construct new generators for current faction.
			System.out.println("Constructing Generators...");
			System.out.println(factions.get(i).toString()); //Print updated #of resources and generators of this faction.
			
			if(checkVictory(factions.get(i),gensForvic)) {
				System.out.println("Faction " + i + " is Victorious !");
				break; //Exist the loop
		}
			i = (i+1)%factions.size(); 
			// when i is equal to faction size, it will reset to zero. 
			//This ensure the loop will run continuously untion a winner is declared.
	}
scanner.close();
	}
	//Add amount of factions to the arraylist according to user input
	private static void construct(ArrayList<Faction> factions, int num) {
		while(num>0) {
			factions.add(new Faction());
			num--;
		}
	}
	
	private static boolean checkVictory(Faction faction,int num) {
		return faction.getNumberOfResourceGenerators()>=num;
	}
	
	
}
