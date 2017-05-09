import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.NullPointerException;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Arrays;

public class RandomizedQueue<T> implements Iterable<T> {
		private int mSize = 0;
		private final int mInitialSize = 10;
		private Object[] mArray;
		private int mFront = 0;
		Stack mStack;
		
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
					size--;
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
		
		private class RQIterator implements Iterator<T> {
			private int pos = 0;
			private Object[] array;
			
			@SuppressWarnings("unchecked")
			public RQIterator(){
				array = new Object[mSize];
				for(int i = 0; i < mSize; i++){
					array[i] = dequeue();
				}
				
			}

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				
				return pos < mSize;
			}

			@Override
			public T next() {
				// TODO Auto-generated method stub
				T t = (T) array[pos];
				pos++;
				return t;
			}
			
			
		}
		
	   public RandomizedQueue(){
		   // construct an empty randomized queue
		   mArray = new Object[mInitialSize];
		   mStack = new Stack();
	   }
	   
	   public boolean isEmpty(){
		   // is the queue empty?
		   return mSize == 0;
	   }
	   
	   public int size()   {
		   // return the number of items on the queue
		   return mSize;
	   }
	   
	   /*
	    * Doubles array size if array is full
	    * Halves array size if number of elements in array is 
	    * 1/4 of array size
	    */
	   private void resize(){
		   // create a temporary array, size is 2x mArray
		   // copy mArray items to temp, then sent reference
		   // to mArray
		   if(mSize == mArray.length){
			   Object[] temp = new Object[mArray.length * 2];
			   for(int i = 0; i < mArray.length; i++){
				   temp[i] = mArray[i];
			   }
			   mArray = temp;
			   temp = null;
		   }
		 
		   // when mSize is less than 1/4 of mArray.length
		   // create a new array that is half the size of mArray
		   // then copy contents over
		   if(mSize <= (mArray.length / 4)){
			   shuffle(mArray.length/4);
		   }
	   }
	   
	   /*
	    * shuffle shifts items so mArray is continuous in terms of values
	    * ie: [1,2,3,4,5,null,null,null,null,null]
	    * not [1,null,2,null,null,3,4,5,null,null] 
	    */
	   private void shuffle(int size){
		   Object[] temp = new Object[mArray.length / 2];
		   int total = mSize;
		   int i = 0;
		   int j = 0;
		   do {
			   if(mArray[j] != null){
				   temp[i] = mArray[j];
				   total--;
				   i++;
			   }
			   j++;
			   
		   } while(j < mArray.length || total > 0);
		   mArray = temp;
		   mFront = mSize;
		   mStack = new Stack();
	   }
	   
	   public void enqueue(T t){
		   // add the item
		   if(t == null){
			   throw new NullPointerException();
		   }
		   if(mSize == mArray.length){
			   resize();
		   }
		   
		   if(mFront == mSize){
			   mArray[mFront] = t;
			   mFront++;
		   } else {
			   int i = mStack.pop();
			   mArray[i] = t;
		   }
		   mSize++;
	   }
	   
	   /*
	    * old dequeue using stack
	    */
	   
	   
	   @SuppressWarnings("unchecked")
	public T dequeue(){
		   if(mSize == 0){
			   throw new NoSuchElementException();
		   }
		   int i;
		   int upperBound = mArray.length;
		   
		   do {
			   i = StdRandom.uniform(upperBound);
		   } while(mArray[i] == null);
		   T t = (T) mArray[i];
		   mArray[i] = null;
		   mStack.push(i);
		   mSize--;
		   if(i == mFront) {
			   mFront--;
		   }
		   if(mSize <= (mArray.length / 4) && mArray.length > mInitialSize){
			 
			   resize();
		   } else if(mStack.size > mArray.length / 2) {
			   shuffle(mArray.length);
		   }
		   return t;
		   // remove and return a random item
	   }
	   
	   
	   
	   @SuppressWarnings("unchecked")
	   public T sample(){
		   int i = -1;
		   if(size() != 0){
			   do {
				   i = StdRandom.uniform(mSize);   
			   } while(mArray[i] == null);
		   }
		   
		   if(i == -1){
			   return null;
		   } else {
			   return (T) mArray[i];
		   }
		   
	   }
	
	
	   @Override
	   public String toString(){
		   return Arrays.toString(mArray);
	   }
	   
	@Override
	public Iterator<T> iterator() {
		if(mFront != mSize){
			shuffle(mArray.length);
		}
		return new RQIterator();
		// TODO Auto-generated method stub
	
		
	}

}
