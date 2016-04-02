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

//˫������
public class DoubleNode<T> implements Iterable<T>
{
	private int N = 0;					//˫������Ľڵ�ĸ���
	private Node<T> head = null;		//˫�������ͷ�ڵ�
	
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
	
	//�ڱ�ͷ����ڵ�
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
	
	//�ڱ�β����ڵ�
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
	
	//�ӱ�ͷɾ���ڵ�,����ɾ���Ľڵ��item
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
	
	//�ӱ�βɾ���ڵ㣬����ɾ���Ľڵ��item
	public T deleteFromTail()
	{
		if( isEmpty() )
			throw new NoSuchElementException("delete from head: no element");
		
		Node<T> cur = head;
		if( cur.next == null )	//����һ���ڵ�
		{
			T item = cur.item;
			head = null; 
			--N;
			return item;
		}
		
		//����һ���ڵ�
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
	
	//��ָ���ڵ�֮ǰ�����µĽڵ�
	//����index�� 1�����һ���ڵ㣬���ε���
	//����item�����������Ľڵ��itemֵ
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
		if( pre == null )	//��ͷ�ڵ�֮ǰ����
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
	
	
	//��ָ���ڵ�֮������µĽڵ�
	//����index�� 1�����һ���ڵ�
	//����item�����������Ľڵ��itemֵ
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
		if( nex == null )		//��β�ڵ�֮�����
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
	
	//ɾ��ָ���Ľڵ�
	//����k��1�����һ���ڵ�
	//����ֵ��ɾ���ڵ��itemֵ
	public T deleteNodeK(int k)
	{
		if(k <= 0 || k > size() )
			return null;
		
		Node<T> cur = head;
		for(int i = 1; i < k; ++i)
		{
			cur = cur.next;
		}
		
		if( cur == head )	//ɾ��ͷ�ڵ�
		{
			return deleteFromHead(); 
		}
		
		if( cur.next == null )	//ɾ��β�ڵ�
		{
			return deleteFromTail();
		}
		
		//ɾ�������ڵ�(��ͷ����β���ڵ�)
		T item = cur.item;
		cur.prev.next = cur.next;
		cur.next.prev = cur.prev;
		--N;
		return item;
	}
	
	//����˫������Ĵ�С
	public int size()
	{
		return N;
	}
	
	//˫�������Ƿ�Ϊ��
	public boolean isEmpty()
	{
		return N == 0;
	}
	
	//ʵ�ֵ������ӿ�
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
