//AVLNode.java
//CSC 345 Spring 17 - Project 4
//Eddy Valenzuela
//Is a close that makes a node that is used for AVLTree
public class AVLNode
{
	public AVLNode left,right,parent;
	public int     height;

	public int key;
        public String data;

	public AVLNode(int key, String data)
	{
		this.key = key;
                this.data = data;

		// Java defaults all fields to zero/false/null, so the next
		// two lines aren't actually necessary.  But personally, I
		// think it's better to explicitly say "this field should be
		// zero" instead of use the default - otherwise, people who
		// read the code later won't know if you intended the values
		// to be zero, or simply just forgot to initialize them.
		//	- Russ
		left = right = parent= null;
		height = 0;
                data = null;
	}
}

