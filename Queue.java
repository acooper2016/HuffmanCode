public interface Queue<E>
{
	void offer(E item);
	E poll();
	E peak();
	boolean isEmpty();
}