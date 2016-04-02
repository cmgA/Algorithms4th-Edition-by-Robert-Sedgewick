/******************************************************************************
 *  Compilation:  javac IntegerToBinaryString.java
 *  Execution:    java IntegerToBinaryString 
 *
 ******************************************************************************/
public class IntegerToBinaryString
{
	//将正整数positiveNumber转换为二进制的字符串
	public static String toBinayString(int positiveNumber)
	{
		String s = "";
		for(int i = positiveNumber; i > 0; i >>= 1)
			s = (i % 2) + s;
		return s;
	}
	
	public static void main(String[] args)
	{
		System.out.println( toBinayString(65535) );
		System.out.println( toBinayString(65536) );
	}
}
