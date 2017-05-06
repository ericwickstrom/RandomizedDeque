import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.NullPointerException;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Arrays;

public class RandomizedQueue<T> implements Iterable<T> {
		private int mSize = 0;
		private final int initialSize = 10;
		private Object[] array;
		private int front = 0;
		Stack stack;
		
		/*
		 * Stack holds open positions in array.
		 */
		private class Stack {
			public int size;
			Num front = null;
			
			public Stack(){
				size = 0;
			}
			
			public void push(int i){
				Num num = new Num(i);
				if(front != null){
					num.next = front;
					front = num;
				} 
				front = num;
				size++;
			}
			
			public int pop(){
				if(size > 0){
					int i = front.val;
					front = front.next;
					return i;
				}
				// no elements, return -1
				return -1;
			}
			
			private class Num {
				public int val;
				public Num next = null;
				
				public Num(int i){
					val = i;
				}
			}
		}
		
	   public RandomizedQueue(){
		   // construct an empty randomized queue
		   array = new Object[initialSize];
	   }
	   
	   public boolean isEmpty(){
		   // is the queue empty?
		   return mSize == 0;
	   }
	   
	   public int size()   {
		   // return the number of items on the queue
		   return mSize;
	   }
	   
	   public void enqueue(T t){
		   // add the item
		   if(t == null){
			   throw new NullPointerException();
		   }
		   if(front == mSize){
			   array[front] = t;
			   front++;
		   } else {
			   
		   }
		   mSize++;
			
	   }
	   
	   @SuppressWarnings("unchecked")
	public T dequeue(){
		   if(mSize == 0){
			   throw new NoSuchElementException();
		   }
		   int i;
		   do {
			   i = StdRandom.uniform(mSize);   
		   } while(array[i] == null);
		   T t = (T) array[i];
		   array[i] = null;
		   mSize--;
		   if(i == front) front--;
		   return t;
		   // remove and return a random item
	   }
	   
	   @SuppressWarnings("unchecked")
	   public T sample(){
		   int i = -1;
		   if(size() != 0){
			   do {
				   i = StdRandom.uniform(mSize);   
			   } while(array[i] == null);
		   }
		   
		   if(i == -1){
			   return null;
		   } else {
			   return (T) array[i];
		   }
		   
	   }
	
	
	   @Override
	   public String toString(){
		   return Arrays.toString(array);
	   }
	   
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
