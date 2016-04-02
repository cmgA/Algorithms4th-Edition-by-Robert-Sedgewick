/******************************************************************************
 *  Compilation:  javac LogN.java
 *  Execution:    java LogN 
 *
 ******************************************************************************/
public class LogN
{
	//µÝ¹é¼ÆËãLn(n!)
	public static double logFactN(int n)
	{
		if(n == 1)
			return 0;
		else
			return Math.log( (double)n ) + logFactN( n - 1 );
	}
	
	public static void main(String[] args)
	{
		System.out.println( logFactN(4) );
	}
}
