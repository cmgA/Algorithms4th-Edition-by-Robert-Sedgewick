/******************************************************************************
 *  Compilation:  javac LogN.java
 *  Execution:    java LogN 
 *
 ******************************************************************************/
public class LogN
{
	//µ›πÈº∆À„Ln(n!)
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