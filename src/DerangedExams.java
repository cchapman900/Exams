
import java.math.*;

public class DerangedExams {

	public static void main(String[] args) {
		
		//test values
		int n = 7;
		int k = 3;
		
		System.out.println(partialDerange(n, n-k));
		
	}
	
	
	
	public static int subfactorial(int n){
		
		/* From Wolfram.com:
		 * 
		 * http://mathworld.wolfram.com/Derangement.html
		 * 
		 * The function giving the number of distinct derangements on 
		 * n elements is called the subfactorial !n and is equal to 
		 * 
		 * 				_n_	(-1)^k
		 * 		!n=	 n! \	______
		 *				/__	  k!
		 *				k=0
		 */
		
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
	
	
	
	public static long partialDerange(int n, int k) {
		/* ****************************************************
		 * http://mathworld.wolfram.com/PartialDerangement.html
		 * 
		 * 							/ n \
		 * 				R(n, k) =	|	|	!(n-k)
		 * 							\ k /
		 * 
		 *************************************************** */
		
		int combNK = combination(n, k);
		//System.out.println("CombNK " + combNK);
		
		double subFactorialNK = subfactorial(n - k);
		//System.out.println("sub fact of " + (n-k) + " is " + (int)subFactorialNK);
		
		int partialDer = (int)(combNK * subFactorialNK);
		
		//System.out.println("PartDir: " + partialDer);
		
		return partialDer;
	}
	
	
	
	
/* **************************************************
 * 					UTILITY FUNCTIONS
 ************************************************** */
	
	
	/* ***********************************
	 * 
	 * Utility function for Factorial - n!
	 * 
	 ********************************** */
	public static long factorial(int n){
		long nFactorial = 1;
		
		for (int i = n; i > 0; i--) {
			nFactorial = nFactorial * i;
		}
		
		return nFactorial;
	}
	
	
	
	

	/* ******************************************
	 * 										
	 * Utility function for Combination:	/ n \
	 * 										|	|
	 * 										\ r /
	 * 
	 * 					 n!
	 * 				= --------
	 * 				  (n-r)!r!
	 * 
	 ******************************************* */
	
	public static int combination(int n, int r){
		
		long nFactorial = factorial(n);
		long nSubRFactorial = factorial(n-r);
		long rFactorial = factorial(r);
		
		int C = (int) (nFactorial / (nSubRFactorial * rFactorial));
		return C;
		
	}
}