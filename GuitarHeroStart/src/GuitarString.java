public class GuitarString {

	// We're suggesting a few instance variables you will need.
	private ArrayRingBuffer buffer; // Complete ArrayRingbuffer before this
									// class!
	private int tic;
	private int capacity;

	/**
	 * The first constructor creates a ArrayRingBuffer. The capacity of the
	 * ArrayRingBuffer is the samplingRate divided by the frequency and rounded
	 * UP to the nearest whole number. Once the ArrayRingBuffer is created, the
	 * ArrayRingBuffer should be filled with 0's.
	 */
	public GuitarString(double frequency) {

		tic = 0;
		double N = (44100 / frequency);

		capacity = (int) Math.ceil(N);
		buffer = new ArrayRingBuffer(capacity);

	}

	/**
	 * The second constructor creates a ArrayRingBuffer of capacity equal to the
	 * size of the array, and initializes the contents of the buffer to the
	 * values in the array.
	 */
	public GuitarString(double[] init) {
		capacity = init.length;
		buffer = new ArrayRingBuffer(capacity);

		for (int i = 0; i < init.length; i++) {
			buffer.enqueue(init[i]);
		}
		tic = 0;
	}

	/**
	 * Replace all the items in the ring buffer with random values between -0.5
	 * and +0.5.
	 */
	public void pluck() {
		if (buffer.size() > 0) {
			for (int i = 0; i < capacity; i++) {
				buffer.dequeue();
			}
		}

		for (int i = 0; i < capacity; i++) {
			buffer.enqueue((Math.random() - 0.5) / 2);

		}
	}

	/**
	 * Apply the Karplus-Strong update. To do this, remove the sample at the
	 * front of the RingBuffer. Use the sample that was removed and the sample
	 * that is now at the front of the RingBuffer and find their average.
	 * Multiply the average of these two numbers with the energy decay factor.
	 * The energy decay factor is 0.996. Then, place the result at the end of
	 * the RingBuffer.
	 */
	public void tic() {
		double a = buffer.dequeue();
		double b = buffer.peek();
		buffer.enqueue(((a + b) / 2) * 0.996);
		tic++;
	}

	/**
	 * Return the value of the item at the front of the ring buffer
	 */
	public double sample() {

		double result = buffer.peek();
		return result;
	}

	/**
	 * Return the total number of times tic() was called on this instance. This
	 * is a measure of how much time has elapsed.
	 */
	public int time() {

		return tic;
	}

	/**
	 * Return the value for the maximum capacity of the RingBuffer
	 */
	public int getCapacity() {
		return capacity;
	}

}