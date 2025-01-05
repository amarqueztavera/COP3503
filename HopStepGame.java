/* Andrea Marquez Tavera
 * Dr. Andrew Steinberg
 * COP3503 Spring 2024
 * Programming Assignment 4
 */

 public class HopStepGame {// step 1: Create a class called HopStepGame.
	// 4. Make all methods public and class attribute private. It’s good practice!
	
	// non dynamic - recursive approach
	public int minCost(int[] scenario, int length) {// name from driver
		// This method will not use any of the dynamic programming techniques. You must apply a recursive approach. Top Down.
		int costS;// sums the step cost
    
	    // base case
		// top down approach!!!
	    if (length == 0) {
	    	return scenario[0];// if there's only one, return the "last"
	    } else if(length == 1) {
	    	return scenario[1];// if there's only two, return the last one
	    } else {
	    	// recursive
	    	costS = scenario[length] + Math.min(minCost(scenario, length - 1), minCost(scenario, length - 2));// math.min() was used in driver
	    }
	
	    return costS;// return sum
	}
	// memoization approach
	public int minCostMemoization(int[] scenario, int length, int[] memo) {// names from driver, first two from minCost()
		// The running time must be bounded by 𝑂(𝑛) where 𝑛 is the number of steps. Top down, memoization
		
		// Top down!!!!
		// base case
		if (length == 0) {// if only one, return the "last"
			return scenario[0];
		} else if(length == 1) {// if only two, return the last
			return scenario[1]; 
		} else if(memo[length] != 0) {// if the latest stored result is 0, return the last
			return memo[length];
		} else {
			// recursive
			memo[length] = scenario[length] + Math.min(minCostMemoization(scenario, length - 1, memo), minCostMemoization(scenario, length - 2, memo));// taken from driver
		}
		
		return memo[length];// return the latest stored result
	}
	//  tabulation appraoch, GOT HELP FROM TA'S
	public int minCostTabulation(int[] scenario) {// name from driver  
		// This method must apply the bottom-up tabulation approach. The running time must be bounded by 𝑂(𝑛) where 𝑛 is the number of steps.
		
		int length = scenario.length;
		int[] dA = new int[length];// allocate memory, dynamic array of size length

	    if(length == 0) {// if there are no steps, return 0
	    	return 0;
	    } else if (length == 1) {// if there's one, return "last"
	    	return scenario[0];
	    } else if (length >= 2) {// if two or more
	    	dA[0] = scenario[0];// store "first" 
	    	dA[1] = scenario[1];// store "second"
	    }
	    
	    for (int i = 2; i < length; i++) {// go through the length
	    	dA[i] = scenario[i] + Math.min(dA[i - 1], dA[i - 2]);// store each recursively
	    }

	    return Math.min(dA[length - 1], dA[length - 2]);
  	} 
}