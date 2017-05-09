import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class RandomizedQueueTests {

	@Test
	public void testStack(){
		RandomizedQueue<Integer> rq = new RandomizedQueue<>();
		for(int i = 1; i <= 1000; i++){
			rq.enqueue(i);
		}
		
		int i = 1;
		while(rq.size() > 0){
			System.out.println(i++ + ": " + rq.dequeue());
		}
	}
	

}
