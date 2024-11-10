package project2;
import java.util.Random;

public class ResourceGenerator {
	//Set private variable for resource numbers for A, B and C
	private int resourceNumA;
	private int resourceNumB;
	private int resourceNumC;
	private static int blockedNum = -1; //declared static and set to -1 for sentinel 
	
	//Constructor
	public ResourceGenerator(){
		Random ran = new Random();
		//use of do while loop to make sure the code is at least run once before checking the condition.
		do {
			resourceNumA = ran.nextInt(11)+2; // Assign a distinct# to A
		}while(resourceNumA==7); // NumA must not equal to 7
		
		do {
			resourceNumB = ran.nextInt(11)+2; // Assign a distinct# to B
		}while(resourceNumB==7 || resourceNumB == resourceNumA); // NumB must not equal to 7 or NumA
		
		do {
			resourceNumC = ran.nextInt(11)+2;
		}while(resourceNumC==7 || resourceNumC == resourceNumB || resourceNumC == resourceNumA); // check all three conditions. 
			//At this point I regret naming them resourceNum# because it's so long to type :(  
	}
	
	//Methods
	
	public String generateResource(int num) {
		if (num!=blockedNum) { //Make sure the number is not blocked
			if(num==resourceNumA) return "A"; //Check A
			else if (num==resourceNumB) return "B"; // Check B
			else if (num==resourceNumC) return "C"; // Check C
		}
		return null; // if num is blocked or doesn't match any resource#s, return null.
	}
	
	public static void blockNumber(int num) {
		blockedNum = num;
	}
	
	public static int rollDice() {
	Random ran = new Random();
	return (ran.nextInt(6)+1)+(ran.nextInt(6)+1); //return sum of two dice rolled
	}
	
	
}
