import static org.junit.Assert.*;

import org.junit.Test;

public class RandomizedQueueTests {

	@Test
	public void intialSizeShouldBe0() {
		RandomizedQueue<Integer> rq = new RandomizedQueue<>();
		
		assertEquals(0, rq.size());
	}
	
	@Test
	public void insert10ShouldBeSize10() {
		RandomizedQueue<Integer> rq = new RandomizedQueue<>();
		for(int i = 1; i <= 10; i++){
			rq.enqueue(i);
		}
		assertEquals(10, rq.size());
	}
	
	@Test
	public void testSample(){
		RandomizedQueue<Integer> rq = new RandomizedQueue<>();
		for(int i = 1; i <= 10; i++){
			rq.enqueue(i);
		}
		System.out.println(rq.toString());
		assertTrue(true);
	}

}
