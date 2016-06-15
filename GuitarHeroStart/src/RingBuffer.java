public interface RingBuffer {
  
	/**
	 * The size() method returns the number of elements currently in this RingBuffer
	 */
	public int size();

	/**
	 * Returns true if the RingBuffer is empty
	 */
	public boolean isEmpty();

	/**
	 * Returns true if the RingBuffer is full
	 */
	public boolean isFull();

	/**
	 * This method puts element x into the RingBuffer. Since the RingBuffer is a
	 * queue, the element being inserted should be added to the end of the queue
	 */
	public void enqueue(double x);

	/**
	 * Removes the first element from the RingBuffer and returns it
	 */
	public double dequeue();

	/**
	 * Returns the first element from the RingBuffer and does NOT remove it from this RingBuffer
	 */
	public double peek();
}