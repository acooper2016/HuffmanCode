//Using HashMap because it is more efficient than TreeMap, and the ordering of TreeMap isn's useful to us.
import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanTree
{
	//Root of the HuffmanTree
	private HuffmanNode root;
	private PriorityQueue<HuffmanNode> nodeQueue;
	
	
	//String containing a single instance of ever character that appears in the original encoding String.
	//Used to check whether new Strings can be encoded with this tree.
	private String charSet;
	
	/**
	*Encodes the String into a HuffmanTree that can be used to encode and decode other messages.
	*Creates new Huffman nodes for each unique character in s, with count values representing
	*the number of times each character appears in s.  Puts all of these in a PriorityQueue, ordered
	*by count.  Then uses the BuildTree function to turn that PriorityQueue into a complete HuffmanTree
	*
	*@param s Encoding string
	*/
	//public HuffmanTree(String s)
	
	
	/**
	*Takes in a PriorityQueue of HuffmanNodes and converts it into a finished HuffmanTree, using the following algorithm:
	*1. Poll the 2 nodes with the lowest counts.
	*2. Construct a new Node with those 2 nodes as its left and right values, lowest Left.  Count is equal to left's count + right's count.
	*3. Put new Node back in PriorityQueue.  
	*4. If Queue's size == 1, return only remaining node.  Else, repeat from 1.
	*
	*@param queue full queue used to construct the HuffmanTree
	*@return HuffmanNode that is the HuffmanTree's root.
	*
	//public HuffmanTree Buildtree(PriorityQueue<HuffmanNode> queue)
	
	/**
	*Encodes a given String based on the HuffmanTree.  If s's characters aren't all contained in charset, throws and exception.
	*Else, navigates through HuffmanTree to find each character in the tree, appending a 1 to the String? for each right navigation,
	*and a 0 for each left navigation.
	*
	*@param s String to be encoded
	*@return encoded string
	//public String? encode(String s)
	
	/**
	*Helper method for encode(String) that returns a single character's binary representation
	*
	*@param c character to be encoded
	*@return encoded character.
	//private String? encodeChar(char c)	
	
	/**
	*Decodes a given binary sequence.  Follows the sequence of Binary Characters, with a 0 indicating left and a 1 indicating right.
	*When a leaf node is reached, adds that char to the final String, and starts the navigation again from the root.
	*
	*@param s Sequence of binary values to be decoded.
	*@return decoded String.
	*/
	//public String decode(String? s)
	
	/**
	*Uses an inorder iterator to print the characters and counts of each node in the HuffmanTree.
	*
	*@return String representation of HuffmanTree
	*/
	//public String toString
	
	
	
	
}