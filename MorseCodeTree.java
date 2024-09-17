import java.util.ArrayList;

public class MorseCodeTree implements LinkedConverterTreeInterface<String>
{
	//Stores the root of the MorseCodeTree
	private TreeNode<String> root;

	//When this constructor is called, it will build the initial Morse code tree with Morse code letter
	public MorseCodeTree()
	{
		this.buildTree();
	}

	//Return the root of the tree
	public TreeNode<String> getRoot()
	{
		return root;
	}

	//Set the root of the tree to a new TreeNode<String> root object
	public void setRoot(TreeNode<String> newNode)
	{
		root = newNode;
	}

	//Adds a Morse code element to the correct position in the tree based on the tree encoding
	public MorseCodeTree insert(String code, String letter)
	{
		//This rejects incorrect inputs, any String with size > 4, the 4 unused Morse Strings, and if it has characters not .-
		//Checks for size > 4, then checks for characters not .-, then checks for if it is asking for the 4 unused Morse Strings
		if(code.length()<=4 & code.matches("[.-]+") & !code.equals("..--") & !code.equals(".-.-") & !code.equals("---.") & !code.equals("----"))
		{
			addNode(root, code, letter); //If the input is validated, call addNode to add the data into the true
		}
		return this; //Return this MorseCodeTree object
	}

	//This uses recursion to add a Morse Code String data element to the correct position in the tree.
	// "." Morse code letters means go to the left and "-" Morse code letters means go to the right
	public void addNode(TreeNode<String> root, String code, String letter)
	{
		if(code.length()>0) //If code string has any characters by being longer than 0
		{
			if(code.regionMatches(0, ".", 0, 1)) //If the first character matches "."
			{
				//Go to the left child
				if(root.leftChild!=null) //If left child exists by not being null
				{
					//Call addNode again with the first letter of the Morse String string removed
					addNode(root.leftChild, code.substring(1), letter);
				}
				else //The left child does not exist by being null
				{
					//Make a new empty TreeNode<String> child and add it to the left child pointer of the current root node
					root.leftChild = new TreeNode<String>("");
					//Call addNode again with the first letter of the Morse String string removed
					addNode(root.leftChild, code.substring(1), letter);
				}
			}
			else //Else the first character matches "/" by not being "."
			{
				//Go to the left child
				if(root.rightChild!=null) //If right child exists by not being null
				{
					//Call addNode again with the first letter of the Morse String string removed
					addNode(root.rightChild, code.substring(1), letter);
				}
				else //The right child does not exist by being null
				{
					//Make a new empty TreeNode<String> child and add it to the right child pointer of the current root node
					root.rightChild = new TreeNode<String>("");
					//Call addNode again with the first letter of the Morse String string removed
					addNode(root.rightChild, code.substring(1), letter);
				}
			}
		}
		else //Code string is empty by being zero, this will always work since the root node must exist for this to be called
		{
			root.data = letter; //Add letter to the root node's data
		}
	}

	//Fetch the letter data in the tree based on the Morse code in the String
	public String fetch(String code)
	{
		//This rejects incorrect inputs, any String with size > 4, the 4 unused Morse Strings, and if it has characters not .-
		//Checks for size > 4, then checks for characters not .-, then checks for if it is asking for the 4 unused Morse Strings
		if(code.length()<=4 & code.matches("[.-]+") & !code.equals("..--") & !code.equals(".-.-") & !code.equals("---.") & !code.equals("----"))
		{
			return fetchNode(root, code); //If the input is validated, look up the corresponding letter to a Morse code String
		}
		else //Otherwise there is incorrect input
		{
			return ""; //Return an empty String
		}
	}

	//This recursively fetches the letter of a corresponding Morse code String
	public String fetchNode(TreeNode<String> root, String code)
	{
		if(code.length()>0) //If code string has any characters by being longer than 0
		{
			if(code.regionMatches(0, ".", 0, 1)) //If the first character matches "."
			{
				return fetchNode(root.leftChild, code.substring(1)); //Go to the left child with a substring that removed the first character
			}
			else //First character is not a "." and must be a "-"
			{
				return fetchNode(root.rightChild, code.substring(1));//Go to the right child with a substring that removed the first character
			}
		}
		else //The code string has no characters
		{
			return root.getData(); //Return the letter data
		}
	}

	//This operation is not supported in the MorseCodeTree
	public MorseCodeTree delete(String data) throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException();
	}

	//This operation is not supported in the MorseCodeTree
	public MorseCodeTree update() throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException();
	}

	//This builds the Morse Code Tree by giving it the inputs
	public void buildTree()
	{
		//Construct the root of the tree using an empty "" String
		root = new TreeNode<String>("");
		//Build the Morse Code Alphabet, 26 letters
		this.insert(".-", "a");
		this.insert("-...", "b");
		this.insert("-.-.", "c");
		this.insert("-..", "d");
		this.insert(".", "e");
		this.insert("..-.", "f");
		this.insert("--.", "g");
		this.insert("....", "h");
		this.insert("..", "i");
		this.insert(".---", "j");
		this.insert("-.-", "k");
		this.insert(".-..", "l");
		this.insert("--", "m");
		this.insert("-.", "n");
		this.insert("---", "o");
		this.insert(".--.", "p");
		this.insert("--.-", "q");
		this.insert(".-.", "r");
		this.insert("...", "s");
		this.insert("-", "t");
		this.insert("..-", "u");
		this.insert("...-", "v");
		this.insert(".--", "w");
		this.insert("-..-", "x");
		this.insert("-.--", "y");
		this.insert("--..", "z");
	}

	//Calls the LNR(Left, Node, Right) Traversal method
	public ArrayList<String> toArrayList()
	{
		//This makes an ArrayList variable to hold the result
		ArrayList<String> result = new ArrayList<String>();
		//Call the LNRoutputTraversal and pass it the root node and ArrayList variable result to hold the data
		LNRoutputTraversal(root, result);
		//Return the resulting ArrayList
		return result;
	}

	//Traversals the Tree in LNR(Left, Node, Right) order and adds the data to the ArrayList list
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list)
	{
		if(root.leftChild!=null) //If left subtree exists by not being null
		{
			LNRoutputTraversal(root.leftChild, list); //Visit left subtree
		}
		//This will always exist otherwise the method would not have been called
		list.add(root.getData()); //Add the data of root to the ArrayList
		if(root.rightChild!=null) //If right subtree exists by not being null
		{
			LNRoutputTraversal(root.rightChild, list); //Visit right subtree
		}
	}
}