import java.util.Iterator;

public class HuffmanIterator implements Iterator
{
	private Queue order;
	private int index;
	
	/**
	*Constructs a new inorder iterator with a linked list containing contents of  HuffmanTree "in order"
	*upon construction.  Inorder adds values recursively according to the following priorities: left branch, value, right branch.
	*
	*@return new inorder iterator
	*@param root Binary tree that is the root of the contents of the iterator
	*/
	public HuffmanIterator(HuffmanTree root)
	{
		index = 0;
		order = new LinkedList<String>();
		getOrder(root, order);
	}
	
	/**
	*Helper method that constructs the linked list in the constructor
	*
	*@param branch current tree being added recursively
	*@param order current contents of Linked List
	*/
	private void getOrder(HuffmanTree branch, Queue order)
	{
		if(branch.isLeaf())
			order.offer("" + branch.getValue());
		else if(branch.getRight() == null)
		{
			getOrder(branch.getLeft(), order);
			order.offer(branch.getValue());			
		}
		else if(branch.getLeft() == null)
		{
			order.offer(branch.getValue());
			getOrder(branch.getRight(), order);		
		}	
		else
		{
			getOrder(branch.getLeft(), order);
			order.offer(branch.getValue());
			getOrder(branch.getRight(), order);
		}
	}
	
	/**
	*Returns whether or not this iterator has a next value
	*
	*@return whether or not this iterator has a next value
	*/
	public boolean hasNext()
	{
		if(order.isEmpty())
			return false;
		else
			return true;
	}
	
	/**
	*Next value of the iterator
	*
	*@return next value
	*/
	public E next()
	{
		return order.poll();
	}
}