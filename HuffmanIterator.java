import java.util.Iterator;
/**
*Iterator for a HuffmanTree.  Uses inorder algorithm to convert a tree into a linear List of Strings.
*
*@version 1.0
*@author Aaron Cooper
*/
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
	public HuffmanIterator(HuffmanTree tree)
	{
		index = 0;
		order = new LinkedList<String>();
		getOrder(tree.getRoot(), order);
	}
	
	/**
	*Helper method that constructs the linked list in the constructor
	*
	*@param branch current tree being added recursively
	*@param order current contents of Linked List
	*/
	private void getOrder(HuffmanNode branch, Queue order)
	{
		if(branch.isLeaf())
			order.offer("" + branch.getCharacter() + branch.getCount());
		else if(branch.getRight() == null)
		{
			getOrder(branch.getLeft(), order);
			order.offer("" + branch.getCharacter() + branch.getCount());			
		}
		else if(branch.getLeft() == null)
		{
			order.offer("" + branch.getCharacter() + branch.getCount());
			getOrder(branch.getRight(), order);		
		}	
		else
		{
			getOrder(branch.getLeft(), order);
			order.offer("" + branch.getCharacter() + branch.getCount());
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
	public String next()
	{
		return "" + order.poll();
	}
}