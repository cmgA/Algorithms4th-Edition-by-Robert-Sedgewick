/******************************************************************************
 *  Compilation:  javac IntegerToBinaryString.java
 *  Execution:    java IntegerToBinaryString 
 *
 ******************************************************************************/
public class IntegerToBinaryString
{
	//��������positiveNumberת��Ϊ�����Ƶ��ַ���
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