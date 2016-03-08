/******************************************************************************
 *  Compilation:  javac Gcd.java
 *  Execution:    java Gcd 
 *
 ******************************************************************************/
public class Gcd
{
	//��p,q�����Լ��,p��q˳������
	public static int gcd(int p, int q)
	{
		System.out.println( p + "\t" + q );	
		if(q == 0)
			return p;
		if(p == 0)
			return q;
		int r = p%q;
		return gcd(q, r);
	}
	
	public static void main(String[] args)
	{
		//test gcd function
		System.out.println( gcd(3,2) );
		System.out.println( gcd(2,3) );
		System.out.println( gcd(0,3) );
		System.out.println( gcd(3,0) );
		System.out.println( gcd(4,2) );
		
		System.out.println( "====================" );
		System.out.println( gcd(105,24) );
		System.out.println( gcd(1111111,1234567) );
	}
}