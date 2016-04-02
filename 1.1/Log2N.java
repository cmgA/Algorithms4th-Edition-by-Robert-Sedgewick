/******************************************************************************
 *  Compilation:  javac Log2N.java
 *  Execution:    java Log2N 
 *
 ******************************************************************************/
public class Log2N
{
	//¼ÆËã²»´óÓÚLog2(N)µÄÕûÊý
	//2^m =< N
	public static int log2N(int n)
	{
		int m = 0;
		int product  = 2;
		while(product <= n)
		{
			product *= 2;
			++m;
		}
		return m;
	}
	
	public static void main(String[] args)
	{
		System.out.println( log2N(3) );
		System.out.println( log2N(4) );
		System.out.println( log2N(5) );
		System.out.println( log2N(6) );
		System.out.println( log2N(7) );
		System.out.println( log2N(8) );
	}
}
