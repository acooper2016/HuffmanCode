import java.lang.Comparable;

/**
*Node for the HuffmanTree class.  Contains a left and right pointer, along with a character value and an int value representing
*The count of that character in the specific tree.
*
*@author Aaron Cooper
*@version 1.0
*/
public class HuffmanNode implements Comparable<HuffmanNode>
{
	private String character;
	private int count;
	private HuffmanNode right;
	private HuffmanNode left;
	
	
	public HuffmanNode()
	{
	}
	
	/**
	*Constructs a HuffmanNode with a predefined character and count value.
	*
	*@param c Character of new Node
	*@param val Count of new Node
	*/
	public HuffmanNode(String s, int val)
	{
		character = s;
		count = val;	
	}
	
	/**
	*Sets right pointer to a given node
	*
	*@param node new right node
	*/
	public void setRight(HuffmanNode node)
	{
		right = node;
	}
	
	/**
	*Sets left pointer to a given node
	*
	*@param node new left node
	*/
	public void setLeft(HuffmanNode node)
	{
		left = node;
	}
	
	/**
	*Sets new count value
	*
	*@param val new count
	*/
	public void setCount(int val)
	{
		count = val;
	}
	
	/**
	*Sets new character value
	*
	*@param c new character 
	*/
	public void setChar(String s)
	{
		character = s;
	}
	
	/**
	*Returns the character
	*
	*@return Node's character value
	*/
	public String getCharacter()
	{
		return character;
	}	
	
	/**
	*Returns the count
	*
	*@return Node's count value
	*/
	public int getCount()
	{
		return count;
	}
	/**
	*Returns the right pointer
	*
	*@return Node's right pointer
	*/
	public HuffmanNode getRight()
	{
		return right;
	}
	
	/**
	*Returns the left pointer
	*
	*@return Node's left pointer 
	*/
	public HuffmanNode getLeft()
	{
		return left;
	}
	
	/**
	*Returns difference in this's count and another node's count.  Return value > 0 means this's count is greater, value < 0
	*means this's count is lower.  0 means they have the same count.
	*/
	public int compareTo(HuffmanNode n)
	{
		return this.getCount() - n.getCount();
	}
	
	public boolean isLeaf()
	{
		if(left == null && right == null)
		{
			return true;
		}
		else return false;
	}
	
	public String toString()
	{
		return "" + character + count;
	}
}