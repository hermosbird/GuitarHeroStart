import static org.junit.Assert.*;

import org.junit.Test;

public class GuitarStringTest {

	

	@Test
	public void testToShowMostMethods() {
		double[] init = { 0.4, 0.2, -0.1 };
		GuitarString gs = new GuitarString(init);
		assertEquals(3, gs.getCapacity());
		assertEquals(0.4, gs.sample(), 0.0001);
		assertEquals(0, gs.time());
		gs.tic(); 
		assertEquals(1, gs.time());
		assertEquals(0.2, gs.sample(), 0.0001);
		gs.tic();
		assertEquals(-0.1, gs.sample(), 0.0001);

		gs.tic();
		assertEquals(0.2988, gs.sample(), 0.0001); // (0.4 + 0.2) / 2.0 * 0.996
													// is 0.2998
	}
	
	
	
	@Test
	public void testgetCapacity(){
		double[] init = { 0.2, 0.4, 0.5, 0.3, -0.2, 0.4, 0.3, 0.0, -0.1, -0.3 };
		GuitarString gs = new GuitarString(init);
		assertEquals(10,gs.getCapacity(),0.001);
	}
	
	
	@Test
	  public void testToMakeSureYourArayRingBufferCanBeSetToAnyCapcity() {
	    // If the next statement does not compile, change the contructor 
	    // in ArrayRingBuffer to take int for initial capaciy
	    ArrayRingBuffer buffer = new ArrayRingBuffer(10);
	    assertEquals(0, buffer.size());
	  }

	  @Test
	  public void testConstructorWithAnArrayOfDouble() {
	    double[] init = { 0.33, 0.4, 0.5, 0.3, -0.2, 0.4, 0.3, 0.0, -0.1, -0.3 };
	    GuitarString gs = new GuitarString(init);
	    assertEquals(0.33, gs.sample(), 0.001);
	    assertEquals(10, gs.getCapacity());
	  }

	  @Test
	  public void testGetters() {
	    double[] init = { -0.2, 0.4, 0.3, 0.0, -0.1 };
	    GuitarString gs = new GuitarString(init);
	    gs.pluck();
	    assertEquals(5, gs.getCapacity());
	  }

	  @Test
	  public void testCapacityConstructorWithFrequencyArgumentNoArray() {
	    GuitarString gs = new GuitarString(4000.0);
	    assertEquals(12, gs.getCapacity());

	    GuitarString gs2 = new GuitarString(8000.0);
	    assertEquals(6, gs2.getCapacity());
	  }

	  @Test
	  public void testTicWithTimeSimpleIncrement() {
	    double[] init = { 0.4, 0.0, 2.0, -4.0 };
	    GuitarString gs = new GuitarString(init);
	    assertEquals(0, gs.time());
	    gs.tic();
	    assertEquals(1, gs.time());
	    gs.tic();
	    assertEquals(2, gs.time());
	    gs.tic();
	    assertEquals(3, gs.time());
	    gs.tic();
	    assertEquals(4, gs.time());
	  }

	  @Test
	  public void testTicNTimesToAssertAllRingBufferElementsAreCorrect() {
	    double[] init = { 0.4, 0.0, 2.0, -4.0 };
	    GuitarString gs = new GuitarString(init);
	    assertEquals(0, gs.time());
	    gs.tic();
	    assertEquals(1, gs.time());
	    gs.tic();
	    assertEquals(2, gs.time());
	    gs.tic();
	    assertEquals(3, gs.time());
	    gs.tic();
	    assertEquals(4, gs.time());
	  }

	  @Test
	  public void testTicChangesAllBufferElementsOnceWithTic() {

	    int N = 20; // runtime from user
	    double[] samples = { 0.2, 0.4, 0.5, 0.3, -0.2, .4, 0.3, 0.0, -0.1, -0.3 };
	    GuitarString testNote = new GuitarString(samples);
	    double[] result = new double[N];
	    for (int i = 0; i < N; i++) {
	      result[i] = testNote.sample();
	      testNote.tic();
	    }

	    // No change to the first 10
	    assertEquals(0.2, result[0], 0.001);
	    assertEquals(0.4, result[1], 0.001);
	    assertEquals(-0.1, result[8], 0.001);
	    assertEquals(-0.3, result[9], 0.001);

	    // Any expected value that follows had the average * 0.996 applied
	    assertEquals(0.2988, result[10], 0.001);
	    assertEquals(0.4482, result[11], 0.001);
	    assertEquals(0.3984, result[12], 0.001);
	    assertEquals(0.0498, result[13], 0.001);
	    assertEquals(0.0996, result[14], 0.001);
	    assertEquals(0.3486, result[15], 0.001);
	    assertEquals(0.1494, result[16], 0.001);
	    assertEquals(-0.0498, result[17], 0.001);
	    assertEquals(-0.1992, result[18], 0.001);
	    assertEquals(-0.0006, result[19], 0.001);
	  }

	  @Test
	  public void testTicChangesAllBufferElementsChangeFiveTimesInTic() {

	    int N = 50; // runtime from user
	    double[] samples = { 0.2, 0.4, 0.5, 0.3, -0.2, .4, 0.3, 0.0, -0.1, -0.3 };
	    GuitarString testNote = new GuitarString(samples);
	    double[] result = new double[N];
	    for (int i = 0; i < N; i++) {
	      result[i] = testNote.sample();
	      testNote.tic();
	    }

	    // No change to the first 10
	    assertEquals(0.2, result[0], 0.001);
	    assertEquals(0.4, result[1], 0.001);
	    assertEquals(-0.1, result[8], 0.001);
	    assertEquals(-0.3, result[9], 0.001);

	    // Any expected value that follows had the average * 0.996 applied
	    // These values exist after going through the buffer 5 times.
	    assertEquals(-0.0739, result[46], 0.001);
	    assertEquals(-0.0342, result[47], 0.001);
	    assertEquals(0.1651, result[48], 0.001);
	    assertEquals(0.3215, result[49], 0.001);
	  }

	  @Test
	  public void testConstructorWithOneFrequency() {
	    GuitarString gs = new GuitarString(4000);
	    assertEquals(0, gs.time());
	    gs.pluck();
	    for (int i = 0; i < 10; i++) {
	      System.out.println(gs.sample());
	      gs.tic();
	    }
	  }
}