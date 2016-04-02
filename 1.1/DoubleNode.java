/******************************************************************************
 *  Compilation:  javac DoubleNode.java
 *  Execution:    java DoubleNode
 *  1.3.31
 ******************************************************************************/


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;
import java.util.function.Consumer;

//双向链表
public class DoubleNode<T> implements Iterable<T>
{
	private int N = 0;					//双向链表的节点的个数
	private Node<T> head = null;		//双向链表的头节点
	
	private class Node<T>
	{
		T item = null;
		Node<T> next = null;
		Node<T> prev = null;
	}
	
	public DoubleNode()
	{
		N = 0;
		head = null;
	}
	public DoubleNode( T[] items)
	{
		int len = items.length;
		for(int i = 0; i < len; ++i)
		{
			insertToTail(items[i]);
		}
	}
	
	//在表头插入节点
	public void insertToHead(T item)
	{
		Node<T> newNode = new Node<T>();
		newNode.item = item;
		
		if( isEmpty() )
		{
			head = newNode;
			++N;
			return;
		}
		else
		{
			newNode.prev = null;
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
			++N;
			return;
		}
	}
	
	//在表尾插入节点
	public void insertToTail(T item)
	{
		Node<T> newNode = new Node<T>();
		newNode.item = item;
		
		if( isEmpty() )
		{
			head = newNode;
			++N;
			return;
		}
		else
		{
			Node<T> cur = head;
			while( cur.next != null )
			{
				cur = cur.next;
			}
			cur.next = newNode;
			newNode.prev = cur;
			newNode.next = null;
			++N;
			return;
		}
	}
	
	//从表头删除节点,返回删除的节点的item
	public T deleteFromHead()
	{
		if( isEmpty() )
			throw new NoSuchElementException("delete from head: no element");
		
		T item = head.item;
		head = head.next;
		if(head == null)
		{
			--N;
			return item;
		}
		head.prev = null;
		--N;
		return item;
	}
	
	//从表尾删除节点，返回删除的节点的item
	public T deleteFromTail()
	{
		if( isEmpty() )
			throw new NoSuchElementException("delete from head: no element");
		
		Node<T> cur = head;
		if( cur.next == null )	//仅有一个节点
		{
			T item = cur.item;
			head = null; 
			--N;
			return item;
		}
		
		//对于一个节点
		while( cur.next != null )
		{
			cur = cur.next;
		}
		
		T item = cur.item;
		Node<T> pre = cur.prev;
		pre.next = null;
		--N;
		return item;
	}
	
	//在指定节点之前插入新的节点
	//参数index： 1代表第一个节点，依次递增
	//参数item：代表待插入的节点的item值
	public boolean insertBeforeIndex(int index, T item)
	{
		if(index <= 0 || index > size() )
			return false;
		
		Node<T> newNode = new Node<T>();
		newNode.item = item;
		
		Node<T> cur = head;
		for(int i = 1; i < index; ++i)
		{
			cur = cur.next;
		}
		Node<T> pre = cur.prev;
		if( pre == null )	//在头节点之前插入
		{
			insertToHead(item);
			return true;
		}
		pre.next = newNode;
		newNode.prev = pre;
		newNode.next = cur;
		cur.prev = newNode;
		++N;
		return true;
	}
	
	
	//在指定节点之后插入新的节点
	//参数index： 1代表第一个节点
	//参数item：代表待插入的节点的item值
	public boolean insertAfterIndex(int index, T item)
	{
		if(index <= 0 || index > size() )
			return false;
		
		Node<T> newNode = new Node<T>();
		newNode.item = item;
		
		Node<T> cur = head;
		for(int i = 1; i < index; ++i)
		{
			cur = cur.next;
		}
		Node<T> nex = cur.next;
		if( nex == null )		//在尾节点之后插入
		{
			insertToTail(item);
			return true;
		}
		cur.next = newNode;
		newNode.prev = cur;
		newNode.next = nex;
		nex.prev = newNode;
		++N;
		return true;
	}
	
	//删除指定的节点
	//参数k：1代表第一个节点
	//返回值：删除节点的item值
	public T deleteNodeK(int k)
	{
		if(k <= 0 || k > size() )
			return null;
		
		Node<T> cur = head;
		for(int i = 1; i < k; ++i)
		{
			cur = cur.next;
		}
		
		if( cur == head )	//删除头节点
		{
			return deleteFromHead(); 
		}
		
		if( cur.next == null )	//删除尾节点
		{
			return deleteFromTail();
		}
		
		//删除其他节点(非头部和尾部节点)
		T item = cur.item;
		cur.prev.next = cur.next;
		cur.next.prev = cur.prev;
		--N;
		return item;
	}
	
	//返回双向链表的大小
	public int size()
	{
		return N;
	}
	
	//双向链表是否为空
	public boolean isEmpty()
	{
		return N == 0;
	}
	
	//实现迭代器接口
	public Iterator<T> iterator() 
	{
		return new DoubleListIterator();
	}
	private class DoubleListIterator implements Iterator<T>
	{
		Node<T> cur = head;
		public boolean hasNext() 
		{
			return cur != null;
		}

		public T next() {
			if(!hasNext())
				throw new NoSuchElementException("hasNext error!");
			T item = cur.item;
			cur = cur.next;
			return item;
		}

		public void remove() {
			throw new UnsupportedOperationException("remove error! unsupported operatiron");
		}	
	}
	
	
	public static void main(String[] args) 
	{
		Integer[] arr = {0, 1, 2, 3, 4};
		DoubleNode<Integer> s = new DoubleNode<Integer>(arr);
		
		StdOut.println( "insert " + ( s.insertBeforeIndex(5, -9) ? "success" : "failed") );
		StdOut.println( "insert " + ( s.insertAfterIndex(1, -999) ? "success" : "failed") );
		StdOut.println( "del :" + s.deleteNodeK(1) );
		StdOut.println( "del :" + s.deleteNodeK(1) );
		StdOut.println( "del :" + s.deleteNodeK(5) );
		StdOut.println( "del :" + s.deleteNodeK(3) );
		StdOut.println( "del :" + s.deleteNodeK(1) );
		Iterator<Integer> it = s.iterator();
		while(it.hasNext())
		{
			StdOut.println(it.next() + "\t");	
		}
		StdOut.println( "size : " + s.size() );
		
	}




}
