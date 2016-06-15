/**
 * 
 * This collection class model a circular queue in that it uses an array as the
 * data structure to hold elements. When when an element is added, modulus
 * arithmetic is employed to place the new element in the "last" position.
 * Another instance variable "first", keeps track of the where the first is.
 * 
 * When first is at index 6 and last is at index 1 in this RingBuffer:
 * 
 * data-> | 0.2 | 0.4 | 0.6 | -0.1 | -0.4 | 0.2 | 0.5 | 0.2 | -0.1 | -0.4 | ^ ^
 * last first
 * 
 * peek() would return 0.5 and enqueue(-0.3) would place -0.3 where the 0.6 is.
 * 
 * Note: When attempting to add new elements and the queue is full or dequeue
 * from an empty RingBuffer, throw new CapacityException();
 */
public class ArrayRingBuffer implements RingBuffer {

	/**
	 * The size() method returns the number of elements currently in this
	 * RingBuffer
	 */

	private double[] data;
	private int size;
	private int back;
	private int front;

	public ArrayRingBuffer(int capacity) {
		data = new double[capacity];
		size = 0;
		back = 0;
		front = 0;

	}

	public int size() {

		return size;
	}

	/**
	 * Returns true if the RingBuffer is empty
	 */
	public boolean isEmpty() {

		return size()==0;
	}

	/**
	 * Returns true if the RingBuffer is full
	 */
	public boolean isFull() {
		return this.size() == data.length;
	}

	/**
	 * This method puts element x into the RingBuffer. Since the RingBuffer is a
	 * queue, the element being inserted should be added at the end of the queue
	 * 
	 * @throws CapacityException
	 *             when attempting to enqueue to a full queue
	 */
	public void enqueue(double x) throws CapacityException {

		if (isFull()==true){
			throw new CapacityException();
		}
		
		data[back] = x;
		back = (back + 1) % data.length;
		size++;
		
		
	}

	/**
	 * Removes the first element from the RingBuffer and returns it.
	 * 
	 * @throws CapacityException
	 *             when attempting to dequeue from an empty queue
	 */
	public double dequeue() throws CapacityException {
		
		
		if (isEmpty()==true){
			throw new CapacityException();
		}
		
		double temp = data[front];
		if(front>=0&&front<data.length){
		front = (front + 1) % data.length;
		size--;}
		return temp;
		
	}

	/**
	 * Returns the first element from the RingBuffer and does NOT remove it from
	 * this RingBuffer
	 * 
	 * @throws CapacityException
	 *             when attempting to peek an empty queue
	 */
	public double peek() throws CapacityException {

		if (isEmpty()==true) {
			throw new CapacityException();
		}

		
			double temp=data[front];
			return temp;
		
	}
}