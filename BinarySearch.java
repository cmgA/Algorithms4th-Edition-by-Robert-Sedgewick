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

	//数组arr必须有序,此处假设了是从低到高排列
	//对于数组,注意访问越界问题,下标值最大为arr.length-1
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
	
	//对于数组,调用时注意访问越界问题,hi为arr.length-1
	//level用于跟踪调用过程
	public static int rankR(int key, int[] arr, int lo, int hi, int level)
	{
		//打印递归调用过程
		String preStr = "";
		for(int i = 0; i < level; ++i)
			preStr += "\t";
		StdOut.println(preStr + "lo: " + lo + " hi: " + hi);
		
		if(lo > hi)			//递归终止条件
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
		
		//打印出排序后的whiteList
		for(int i = 0; i < whiteList.length; ++i)
			StdOut.println(whiteList[i]);
		StdOut.println("===================");
		
		while(!StdIn.isEmpty())
		{
			//读取键值，不在白名单内则打印出来
			int key = StdIn.readInt();
			//if(rank(key, whiteList) < 0)
			if(rankR(key, whiteList, 0, whiteList.length - 1, 0) < 0)		//递归方法进行二分查找
			{
				StdOut.println(key);
			}
		}
	}
}
