public class TreeNode<T>
{
	protected T data; //Stores the data object
	protected TreeNode<T> leftChild; //Links to the left child of this node in a tree
	protected TreeNode<T> rightChild; //Links to the right child of this node in a tree

	//Make a TreeNode with a dataNode object and no pointers
	public TreeNode(T dataNode)
	{
		data = dataNode;
		leftChild = null;
		rightChild = null;
	}

	//When given a TreeNode, make a new deep copied TreeNode
	public TreeNode(TreeNode<T> node)
	{
		this.data = node.data;
		this.leftChild = node.leftChild;
		this.rightChild = node.rightChild;
	}

	//Return the data of a TreeNode
	public T getData()
	{
		return data;
	}
}