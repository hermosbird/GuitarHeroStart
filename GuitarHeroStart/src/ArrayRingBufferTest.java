import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayRingBufferTest {
	
	@Test (expected=CapacityException.class)
		public void testGame(){
		ArrayRingBuffer buffer = new ArrayRingBuffer(5);
		buffer.dequeue();
	}
	

	
	
	
	@Test public void testGetters() {
			ArrayRingBuffer buffer = new ArrayRingBuffer(5);
			assertTrue(buffer.isEmpty());
			assertFalse(buffer.isFull()); // Needs work to pass
			assertEquals(0, buffer.size());
	}

	@Test
	public void testisEmpty() {
		ArrayRingBuffer buffer = new ArrayRingBuffer(5);
		buffer.enqueue(1);
		buffer.enqueue(2);
		assertFalse(buffer.isEmpty());
		ArrayRingBuffer bufferr = new ArrayRingBuffer(5);
		assertTrue(bufferr.isEmpty());
		buffer.enqueue(2);
		
		assertFalse(buffer.isEmpty());
	}

	@Test
	public void testsize() {
		ArrayRingBuffer buffer = new ArrayRingBuffer(3);
		assertEquals(0, buffer.size());
		buffer.enqueue(0.1);
		assertEquals(1, buffer.size());
		
	}

	@Test
	public void testisFull() {
		ArrayRingBuffer buffer = new ArrayRingBuffer(20);
		assertEquals(false, buffer.isFull());
		
		buffer.enqueue(1);
		buffer.enqueue(1);
		buffer.enqueue(1);
		buffer.enqueue(1);
		buffer.enqueue(1);
		buffer.enqueue(1);
		buffer.enqueue(1);
		buffer.enqueue(1);
		buffer.enqueue(1);
		buffer.enqueue(1);
		buffer.enqueue(1);
		buffer.enqueue(1);
		buffer.enqueue(1);
		buffer.enqueue(1);
		buffer.enqueue(1);
		buffer.enqueue(1);
		buffer.enqueue(1);
		buffer.enqueue(1);
		buffer.enqueue(1);
		buffer.enqueue(1);
		assertEquals(true, buffer.isFull());
		ArrayRingBuffer bufferr = new ArrayRingBuffer(10);
		assertEquals(false, bufferr.isFull());
	}
	@Test(expected=CapacityException.class)
	public void testenqueuewhenout(){
		ArrayRingBuffer buffer = new ArrayRingBuffer(1);
		buffer.enqueue(0.1);
		buffer.enqueue(0.1);
	}
	@Test
	public void testenqueue() {
		ArrayRingBuffer buffer = new ArrayRingBuffer(5);
		buffer.enqueue(1.0);
		assertEquals(1.0,buffer.peek(),0.001);
		buffer.enqueue(2.0);
		assertEquals(1.0,buffer.peek(),0.001);
		
	}
	@Test(expected=CapacityException.class)
	public void testdequeuewhenout(){
		ArrayRingBuffer buffer = new ArrayRingBuffer(5);
		buffer.dequeue();
	}
	
	
	
	
	
	
	@Test(expected=CapacityException.class)
	public void testdequeuewhenout2(){
		try{
			ArrayRingBuffer buffer = new ArrayRingBuffer(5);
			buffer.dequeue();
		}
		catch(IndexOutOfBoundsException e){
			
		}
			
		
	}
	
	
	
	
	
	
	
	
	@Test
	public void testdequeue() {
		ArrayRingBuffer buffer = new ArrayRingBuffer(10);
		buffer.enqueue(1.0);
		buffer.enqueue(2.0);
		buffer.enqueue(3.0);
		assertEquals(1.0,buffer.peek(),0.001);
		assertEquals(1.0,buffer.dequeue(),0.001);
		assertEquals(2.0,buffer.peek(),0.001);
		assertEquals(2.0,buffer.dequeue(),0.001);
		assertEquals(3.0,buffer.peek(),0.001);
		assertEquals(3.0,buffer.dequeue(),0.001);
		assertTrue(buffer.isEmpty());
	}

	@Test(expected=CapacityException.class)
	public void testpeekwhenout(){
		ArrayRingBuffer buffer = new ArrayRingBuffer(5);
		buffer.peek();
	}
	@Test
	public void testpeek() {
		ArrayRingBuffer buffer = new ArrayRingBuffer(5);
		buffer.enqueue(1.0);
		assertEquals(1.0,buffer.peek(),0.001);
	}

	 @Test
	 public void testFailedGettersWhenEmpty() {
	 ArrayRingBuffer buffer = new ArrayRingBuffer(5);
	 assertTrue(buffer.isEmpty());
	 assertFalse(buffer.isFull()); 
	 assertEquals(0, buffer.size());
	 buffer.enqueue(1.1);
	 buffer.enqueue(2.2);
	 assertEquals(2, buffer.size());
	 assertEquals(1.1, buffer.peek(), 0.0001);
	 double front = buffer.dequeue();
	 assertEquals(1.1, front, 0.0001);
	 assertEquals(2.2, buffer.dequeue(), 0.0001);
	 assertTrue(buffer.isEmpty());
	 }
	 
	 
	 
	// More @Tests needed here. No exception test are needed in this project
}