/******************************************************************************
 *  Compilation:  javac BinarySearch.java
 *  Execution:    java BinarySearch tinyW.txt < tinyT.txt
 *
 ******************************************************************************/
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class BinarySearch {

	//����arr��������,�˴��������Ǵӵ͵�������
	//��������,ע�����Խ������,�±�ֵ���Ϊarr.length-1
	public static int rank(int key, int[] arr)
	{
		int lo = 0;
		int hi = arr.length - 1;
		while(lo <= hi)
		{
			int mid = lo + (hi - lo) / 2;
			if(key < arr[mid])
				hi = mid - 1;
			else if(key > arr[mid])
				lo = mid + 1;
			else
				return mid;
		}
		return -1;
	}
	
	//��������,����ʱע�����Խ������,hiΪarr.length-1
	//level���ڸ��ٵ��ù���
	public static int rankR(int key, int[] arr, int lo, int hi, int level)
	{
		//��ӡ�ݹ���ù���
		String preStr = "";
		for(int i = 0; i < level; ++i)
			preStr += "\t";
		StdOut.println(preStr + "lo: " + lo + " hi: " + hi);
		
		if(lo > hi)			//�ݹ���ֹ����
			return -1;
		int mid = lo + (hi - lo) / 2;
		if(key < arr[mid])
			return rankR(key, arr, lo, mid - 1, level + 1);
		else if(key > arr[mid])
			return rankR(key, arr, mid + 1, hi, level + 1);
		else
			return mid;
	}
	
	
	public static void main(String[] args) 
	{
		int[] whiteList = In.readInts(args[0]);
		Arrays.sort(whiteList);
		
		//��ӡ��������whiteList
		for(int i = 0; i < whiteList.length; ++i)
			StdOut.println(whiteList[i]);
		StdOut.println("===================");
		
		while(!StdIn.isEmpty())
		{
			//��ȡ��ֵ�����ڰ����������ӡ����
			int key = StdIn.readInt();
			//if(rank(key, whiteList) < 0)
			if(rankR(key, whiteList, 0, whiteList.length - 1, 0) < 0)		//�ݹ鷽�����ж��ֲ���
			{
				StdOut.println(key);
			}
		}
	}
}
