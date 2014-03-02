
import java.math.*;

public class DerangedExams {

	public static void main(String[] args) {
		
		int n = 4;
		int k = 1;
		
		long puget = pugetSoundFunction(n, k, true);
		
		System.out.println("Puget: " + puget);
	}
	
	public static long pugetSoundFunction(int n, int k, boolean explain) {
		
		/*
		 * Explaination found on 
		 * http://mathcs.pugetsound.edu/~mspivey/DT.pdf
		 * 
		 * Theorem 4.
		 * 
		 * 
		 * 					_k_			/ k \	
		 * 		S n,k =	 	\	(-1)^i	|	|	(n - i)!
		 *					/__			\ i /	  
		 *					i=0
		 */
		
		
		long ans = 0;
		
		if (explain) {
			System.out.println("With " + factorial(n) + " different ways to answer all of the " + n + " questions,");
			System.out.println("and starting with already having the first " + k + " wrong:");
			System.out.println();
		}
		
		for (int i = 0; i <= k; i++) {
			int neg1ToTheK = (int)Math.pow(-1, i);
			long combKI = combination(k, i);
			long nSubIFact = factorial(n-i);
			
			long inner = neg1ToTheK * combKI * nSubIFact;
			
			if (explain) {
				System.out.println("With only " + (i+1) + " questions answered,");
				System.out.println("Inner " + i + "= " + inner);
			}
			
			ans = ans + (inner);
		}
		
		return ans;
	}
	
	
	
	
	
/* **************************************************
 * 					UTILITY FUNCTIONS
 ************************************************** */
	
	
	

	/* ********************************
	 * 
	 * 				Factorial
	 * 				---------
	 * 
	 * 			1 * 2 * ... * n-1 * n
	 * 
	 *  				n!
	 * 
	 ******************************** */
	public static long factorial(int n){
		long nFactorial = 1;
		
		for (int i = n; i > 0; i--) {
			nFactorial = nFactorial * i;
		}
		
		return nFactorial;
	}
	
	
	
	

	/* *************************************
	 * 																
	 * 				Combination:
	 * 				------------
	 * 
	 * 		Number of different combinations
	 * 		you can make with r items out of
	 * 			 n objects in a set.
	 * 
	 * 					/ n \
	 * 					|	|
	 * 					\ r /
	 * 
	 * 
	 * 					 n!
	 * 				= --------
	 * 				  (n-r)!r!
	 * 
	 **************************************** */
	
	public static int combination(int n, int r){
		
		long nFactorial = factorial(n);
		long nSubRFactorial = factorial(n-r);
		long rFactorial = factorial(r);
		
		int C = (int) (nFactorial / (nSubRFactorial * rFactorial));
		return C;
		
	}
	
	
	

	/* ****************************************************
	 * 	
	 * 					Partial Derangement
	 * 					-------------------
	 * 
	 * 			The number of combinations that can 
	 * 			be made with k number of items in the 
	 * 			wrong place out of a set of n items
	 * 
	 * http://mathworld.wolfram.com/PartialDerangement.html
	 * 
	 * 							/ n \
	 * 				R(n, k) =	|	|	!(n-k)
	 * 							\ k /
	 * 
	 *************************************************** */
	
	
	public static long partialDerange(int n, int k) {
		
		int combNK = combination(n, k);
		//System.out.println("CombNK " + combNK);
		
		double subFactorialNK = subfactorial(n - k);
		//System.out.println("sub fact of " + (n-k) + " is " + (int)subFactorialNK);
		
		int partialDer = (int)(combNK * subFactorialNK);
		
		//System.out.println("PartDir: " + partialDer);
		
		return partialDer;
	}
	
	
	
	
	/* *******************************************
	 *  				Subfactorial:	
	 * 
	 * 						!n
	 * 
	 * from Wolfram.com:
	 * http://mathworld.wolfram.com/Derangement.html
	 * 
	 * 		The function giving the number of distinct 
	 * 		derangements on n elements is called the 
	 * 		subfactorial !n and is equal to 
	 * 
	 * 						_n_	(-1)^i
	 * 				!n=	 n! \	______
	 *						/__	  i!
	 *						i=0
	 *
	 ******************************************* */
	
	public static int subfactorial(int n){
		
		long nFactorial = factorial(n);
		
		double sigma = 0;
		
		//Compute sigma summation
		for (int i = 0; i <= n; i++){
			//System.out.println("i: " + i);
			double negOneToI = Math.pow(-1, i);
			//System.out.println("-1^i: " + negOneToI);
			long iFactorial = factorial(i);
			//System.out.println("i!: " + iFactorial);
			double innerVal = negOneToI/iFactorial;
			//System.out.println("inner: " + innerVal);
			sigma = sigma + innerVal;
			//System.out.println("Sigma " + sigma);
		}
		
		return (int) (nFactorial * sigma);
	}

}