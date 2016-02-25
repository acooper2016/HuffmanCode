//Using HashMap because it is more efficient than TreeMap, and the ordering of TreeMap isn's useful to us.
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Iterator;

/**
*Program that creates efficient encoded strings of characters.  Using a Binary Tree as the structure for the encoding algorithm,
*More common characters are stored in less space, while less common characters are stored in more space.
*
*@version 1.0
*@author Aaron Cooper
*/

public class HuffmanTree implements Iterable<String>
{
	//Root of the HuffmanTree
	private HuffmanNode root;
	
	
	//String containing a single instance of ever character that appears in the original encoding String.
	//Used to check whether new Strings can be encoded with this tree.
	private String charSet;
	
	public static void main(String [] args)
	{
		String key = "the quick brown fox jumps over the lazy dog...!?!?";
		HuffmanTree rosetta = new HuffmanTree(key);
		String s = rosetta.encode("abcdefg");
		System.out.println(rosetta.decode(s));
		
		
		
	}
	
	/**
	*Returns the root of the HuffmanTree
	*
	*@return root of the HuffmanTree
	*/
	public HuffmanNode getRoot()
	{
		return root;
	}
	
	/**
	*Encodes the String into a HuffmanTree that can be used to encode and decode other messages.
	*Creates new Huffman nodes for each unique character in s, with count values representing
	*the number of times each character appears in s.  Puts all of these in a PriorityQueue, ordered
	*by count.  Then uses the BuildTree function to turn that PriorityQueue into a complete HuffmanTree
	*
	*@param s Encoding string
	*/
	public HuffmanTree(String s)
	{
		HashMap map = new HashMap<String, Integer>();
		for(int i = 0; i < s.length(); i++)
		{
			if(map.containsKey(s.charAt(i)))
			{
				int count = (Integer) map.get(s.charAt(i));
				count++;
				map.remove(s.charAt(i));
				map.put(s.charAt(i), count);				
			}
			else
			{
				map.put(s.charAt(i), 1);
			}
		}
		
		PriorityQueue queue = new PriorityQueue<HuffmanNode>();
		Set entrySet = map.entrySet();
		Iterator setIterator = entrySet.iterator();
		while(setIterator.hasNext())
		{
			Map.Entry e = (Map.Entry) setIterator.next();
			Integer i = (Integer) e.getValue();
			String str = "" + e.getKey();
			queue.offer(new HuffmanNode(str,i));
		}
		
		root = BuildTree(queue);
		charSet = root.getCharacter();
		
	}
	
	
	/**
	*Takes in a PriorityQueue of HuffmanNodes and converts it into a finished HuffmanTree, using the following algorithm:
	*1. Poll the 2 nodes with the lowest counts.
	*2. Construct a new Node with those 2 nodes as its left and right values, lowest Left.  Count is equal to left's count + right's count.
	*3. Put new Node back in PriorityQueue.  
	*4. If Queue's size == 1, return only remaining node.  Else, repeat from 1.
	*
	*@param queue full queue used to construct the HuffmanTree
	*@return HuffmanNode that is the HuffmanTree's root.
	*/
	public HuffmanNode BuildTree(PriorityQueue<HuffmanNode> queue)
	{
		while(queue.size() > 1)
		{
			HuffmanNode nodeA = queue.poll();
			HuffmanNode nodeB = queue.poll();
			HuffmanNode nodeC = new HuffmanNode(nodeA.getCharacter() + nodeB.getCharacter(), nodeA.getCount() + nodeB.getCount());
			if(nodeA.getCount() < nodeB.getCount())
			{
				nodeC.setLeft(nodeA);
				nodeC.setRight(nodeB);
			}
			else
			{
				nodeC.setLeft(nodeB);
				nodeC.setRight(nodeA);
			}
			queue.offer(nodeC);
		}
		return queue.poll();
	}
	
	/**
	*Encodes a given String based on the HuffmanTree.  If s's characters aren't all contained in charset, throws and exception.
	*Else, navigates through HuffmanTree to find each character in the tree, appending a 1 to the String? for each right navigation,
	*and a 0 for each left navigation.
	*
	*@param s String to be encoded
	*@return encoded string
	*/
	public String encode(String s)
	{
		if(! encodable(s))
		{
			throw new NullPointerException("String contains character not in HuffmanTree");
		}
		String codeString = "";
		for(int i = 0; i < s.length(); i++)
		{
			codeString += encodeChar(s.charAt(i));
		}
		return codeString;
	}
	
	/**
	*Helper method for encode(String) that returns a single character's binary representation
	*
	*@param c character to be encoded
	*@return encoded character.
	*/
	private String encodeChar(char c)	
	{
		String charString = "";
		HuffmanNode branch = root;
		int l = branch.getCharacter().length();
		while(l != 1)
		{
			String leftString = branch.getLeft().getCharacter();
			String rightString = branch.getRight().getCharacter();
			boolean isLeft = false;
			for(int i = 0; i < leftString.length(); i++)
			{
				if(c == leftString.charAt(i))
				{
					isLeft = true;
				}
			}
			if(isLeft)
			{
				charString += "0";
				branch = branch.getLeft();
			}
			else
			{
				charString += "1";
				branch = branch.getRight();
			}
			l = branch.getCharacter().length();	
		}
		return charString;	
	}

	
	/**
	*Decodes a given binary sequence.  Follows the sequence of Binary Characters, with a 0 indicating left and a 1 indicating right.
	*When a leaf node is reached, adds that char to the final String, and starts the navigation again from the root.
	*
	*@param s Sequence of binary values to be decoded.
	*@return decoded String.
	*/
	public String decode(String s)
	{
		String decodedString = "";
		s = s + (s.charAt(s.length() - 1));
		HuffmanNode branch = root;
		while(s.length() > 0)
		{
			if(branch.isLeaf())
			{
				decodedString += branch.getCharacter();
				branch = root;
			}
			else
			{
				if(s.charAt(0) == '0')
					branch = branch.getLeft();
				else
					branch = branch.getRight();
				
				s = s.substring(1);
			}
		}
		return decodedString;
	}
	
	/**
	*Uses an inorder iterator to print the characters and counts of each node in the HuffmanTree.
	*
	*@return String representation of HuffmanTree
	*/
	public String toString()
	{
		Iterator<String> i = iterator();
		String s = "";
		while(i.hasNext())
		{
			s += i.next(); 
		}
		return s;
		
	}
	
	/**
	*Returns an iterator that returns each Node in the tree in String form inorder.
	*
	*@return Iterator of all nodes in Tree
	*/
	public Iterator<String> iterator()
	{
		return new HuffmanIterator(this);
	}
	
	/**
	*Returns whether or not a given String is encodable by this HuffmanTree
	*
	*@param s String being checked
	*@return whether or not s is encodable by this HuffmanTree
	*/
	public boolean encodable(String s)
	{
		for(int i = 0; i < s.length(); i++)
		{
			boolean isIn = false;
			for(int j = 0; j < charSet.length(); j++)
			{
				if(s.charAt(i) == charSet.charAt(j))
					isIn = true;
			}
			if (isIn == false)
				return false;
		}
		return true;
	}
	
	
	
	
}