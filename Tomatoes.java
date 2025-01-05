/* Andrea Marquez Tavera
Dr. Steinberg
COP3503 Spring 2024
Programming Assignment 3
*/


public class Tomatoes {
	
	public int minTomatoMoves(int[] intArr) {
		 int tomatoesCount = 0;// keeps track of how mamy tomatoes in total (sum of all tomatoes in the pots)
		 int potsCount = 0;// keeps track of how many pots / array size
		 
		 // go through array and initialize how many tomatoes and pots
		 for (int i : intArr) {
		     	tomatoesCount += i;
		     	potsCount++;
		 }
		 
		 // check if can be evenly distributed, otherwise stop counting
	     if (tomatoesCount % potsCount != 0) {
	    	 return -1; // Cannot evenly distribute tomatoes
	     }
	     
	     // find how many tomatoes per pot
         int perPot = tomatoesCount / potsCount;
         
         // variables to keep track of total moves, and differences between what's in each pot vs what we want in them (total vs current, respectively)
         int minMoves = 0, sumOfDifference = 0, currentDifference = 0;
        
         // go through array to figure out move count, GOT A LOT OF HELP FROM TA'S!!!!
         // greedy technique
         for (int i = 0; i < potsCount; i++) {
        	 // got this idea from TA's
        	 currentDifference = intArr[i] - perPot;// keeps track of current difference of what's in the pot vs how much we want in the pot

        	 sumOfDifference += currentDifference;// keeps track of the sum of what's in each pot vs how much we want in each pot
        	 
        	 int temp = Math.abs(sumOfDifference); 
        	 int temp2 = Math.abs(currentDifference);
        	 
        	 if(temp > minMoves) {// if abs val of sum is greater than total move, replace the value
        		 minMoves = temp;
        	 }

        	 // if current difference in array is greater than total move count, make that the move count
        	 if (temp2 > minMoves) {
        		 minMoves = temp2;
        	 }
   
         }

         return minMoves; 
	}
}