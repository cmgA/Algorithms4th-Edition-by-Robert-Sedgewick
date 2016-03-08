/******************************************************************************
 *  Compilation:  javac Histogram.java
 *  Execution:    java Histogram 
 *
 ******************************************************************************/
public class Histogram
{
	//��������arr[],ͳ������ÿ�����ֳ��ֵĴ�����������ڷ��ص���������
	//M[0]��ֵ��ʾ0������arr[]�г��ֵĴ���
	public static int[] histogram(int arr[], int m)
	{
		int M[] = new int[m];
		for(int i = 0; i < M.length; ++i)
			M[i] = 0;
		
		for(int i = 0; i < arr.length; ++i)
			M[arr[i]] += 1;
		return M;
	}
	
	public static void main(String[] args)
	{
		int arr[] = {1,2,0,4,5,6,6,7,7,7,8,9};
		int maxNum = arr[0];
		for(int i = 0; i < arr.length; ++i)
		{
			if(arr[i] > maxNum)
				maxNum = arr[i];
		}
		
		int[] M = histogram(arr, maxNum + 1);
		for(int i = 0; i < M.length; ++i)
		{
			System.out.println(M[i]);
		}
	}
}