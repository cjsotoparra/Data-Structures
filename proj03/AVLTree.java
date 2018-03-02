import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/* class AVLTree
 *
 * Models a complete AVLTree.  Individual nodes are modeled by the AVLNode
 * class.
 *
 * The constructor creates an empty tree (root=null).
 *
 * This class has many methods which perform basic operations.  Most of these
 * (non-static) methods have (static) recursive helper methods, which take an
 * AVLNode as the first paramter; these are static because it is valid for the
 * parameter to be null.  Thus, the base case for each of these methods is an
 * empty tree.  (See the method documentation below for details; there are a
 * few exceptions to this rule.)
 *
 * Methods that perform modifications on the tree all use the x=change(x)
 * style.  These include highly visible methods (such as insert and delete),
 * and also utility methods (such as rotateRight).
 */
public class AVLTree
{
	// ALERT
	//
	// You *must not* add any fields other than this one to this class!
	// This field is sufficient for everything that you need to do.
	//
	// Also, remember that you can't change the AVLNode class *at all* -
	// and so you cannot add fields there, either!
	//
	// However, you can (and should!) add some static helper methods to
	// this class.
	private AVLNode root;


	/* default constructor
	 *
	 * Initializes this object to represent an empty tree.
	 */
	public AVLTree()
	{
		// See my comment in the constructor for AVLNode.  I like
		// to explicitly set things to null, instead of simply
		// using the default values.
		//	- Russ
		root = null;
	}



	/* debug(String)
	 *
	 * Opens a file with the given filename, and then fills that file with
	 * .dot file source code, to generate a picture for this tree.  This
	 * wrapper opens (and closes) the file, and adds the necessary
	 * prefix/suffix text inside it.  However, the actual body of the file
	 * should be printed by a recursive helper method.
	 *
	 *   - Note from Russ: I have not yet defined the helper method, since
	 *     I assume that different students might use different Java classes
	 *     for printing.  One option is to pass a FileWriter as a parameter;
	 *     another is to *return* a String.
	 */
	//accepts a name of a file and returns nothing
		//creates code from the binary tree to make a picture of the binary tree through a .dot file
	public void debug (String fileName){

		BufferedWriter bWriter=null;
		FileWriter fWriter=null;

		try {
			fWriter = new FileWriter( fileName);
			bWriter = new BufferedWriter(fWriter);
			if(root == null){
				bWriter.write("digraph\n {\n}");
				return;
			}
			String content = "digraph\n {\n	Dummy [style=invis];\n	Dummy -> " +root.val +";\n"
					+"	" + root.val +" [penwidth=2];\n\n	"+"\n";
			bWriter.write(content);

			debugHelper(bWriter, root, Integer.toString(root.val), "");

			content = "}";
			bWriter.write(content);
		} catch (IOException ee) {

			ee.printStackTrace();

		} finally {

			try {

				if (bWriter != null)
					bWriter.close();

				if (fWriter != null)
					fWriter.close();

			} catch (IOException eee) {
				eee.printStackTrace();
			}
		}
	}

	//Helps finish the debug by printing out the lines and making the variables
	//accepts a node buffered writer a string and a String direction renturns nothing

	void debugHelper(BufferedWriter bw, AVLNode root, String val, String direction){
		if (root == null) {
			  return;
		}
		String str =  "      " + "" + root.val + " [label=\""+ root.val +"\\nh=" + root.height + "\"];\n\n";
		String str1 = "      "+ val + " -> " + root.val + " [label=\"" +direction +"\"];\n";

		try {
			if(direction== ""){
				bw.write(str);
			}else{
				bw.write(str1);
				bw.write(str);
			}//if else statemetn

		} catch (IOException e) {
			e.printStackTrace();
		}//try catch

		debugHelper(bw, root.left, Integer.toString(root.val), "L");
		debugHelper(bw, root.right, Integer.toString(root.val), "R");
	}//debugHelper



	/* print_inOrder()
	 *
	 * Prints out an in-order traversal of the tree to stdout (followed by
	 * a newline).  The keys are separated by spaces, and the list (if
	 * non-empty) has a trailing space.  If the tree is empty, this prints
	 * out nothing except for the newline.  Uses a static recursive helper
	 * method.
	 *
	 * static print_inOrder(AVLNode)
	 *
	 * Helper method for the public method.  Is static; takes an AVLNode
	 * parameter, which might be null.  If the parameter is null, it does
	 * nothing.  Works recursively.
	 */
		//takes in no parameters and returns nothing
		//Uses a helper method by recurse left first then print then recurse right(inorder).
		public void print_inOrder() {
			if (root == null) {
				System.out.println();
			}else{
				inOrder(root);
				System.out.println("");
			}
		}

		private static void inOrder(AVLNode root){

			//base case
			if (root == null) {
				return;
			}

			//else traverse
			inOrder(root.left);
			System.out.print(root.val + " ");
			inOrder(root.right);
		}//inOrder

	/* print_preOrder()
	 *
	 * See print_inOrder() above - works in exactly the same way, except
	 * produces a pre-order traversal instead of an in-order traversal.
	 */
		//takes in no parameters and returns nothing
		//Uses a helper method by printing first then recurse left then recurse right(preorder).
	public void print_preOrder() {
		if (root == null) {
			System.out.println();
		}else{
			preOrder(root);
			System.out.println("");
		}
	}

	private static void preOrder(AVLNode root) {
		if (root == null) {
			return;
		}
		  System.out.print(root.val + " ");
			  preOrder(root.left);
			  preOrder(root.right);
	}//preORder

	/* search(int)
	 *
	 * Searches the tree for a given key.  Returns the node where the tree
	 * exists, or null if the key does not exist.  May be implemented
	 * recursively (with a helper method), or as a loop.
	 */
	public AVLNode search(int value){
		AVLNode node = root;
		while(node!=null){
			if(node.val==value){
				return node;
			}else if(node.val>value){
				node = node.left;
			}else{
				node = node.right;
			}//if else
		}//while loop

		//if the element was niot ofund then return null
		return null;
	}

	/* insert(int)
	 *
	 * Inserts a new key into the tree; throws IllegalArgumentException if
	 * the key is a duplicate.
	 *
	 * Uses the x=change(x) style to modify the tree.  Uses a static,
	 * recursive helper method to implement the modification.  The helper
	 * method is responsible for *all* of the changes associated with
	 * inserting a new value, including all rebalances.
	 *
	 * static insert(AVLNode,int)
	 *
	 * Static, recursive helper method for insert(int).  Takes an AVLNode
	 * parameter, which might be null; if the parameter is null, then it
	 * creates a new node and returns it.
	 *
	 * Always returns the root of the new tree; does all of the
	 * rebalancing work associated with the insert.
	 */
	public void insert(int val){

                //create new node
                AVLNode temp = root;

                //first search for the value in the tree
                temp = search(val);

		//if temp is not null then that means element is already in the tree
                if(temp != null){
                        System.err.println("ERROR: Could not insert "+val+" because it is already in the tree.");
                        return;
//                      throw new IllegalArgumentException();
                }

		//if not null then insert the element in the tree
		root = insert(root, val);
	}

	private static AVLNode insert(AVLNode subtree, int val){

		//if the tree is empty then just create a new node
		if (subtree == null){
            		subtree = new AVLNode(val);

		//else travese the tree and find where to insert
        	}else {
			//go left
            		if (val < subtree.val){
                		subtree.left = insert(subtree.left, val);
			//else go right
            		}else{
                		subtree.right = insert(subtree.right, val);
			}
        	}//if else for traversal


		//update the hieght
       		 subtree.height = Math.max(getHeight(subtree.left), getHeight(subtree.right)) + 1;

		//call rebalnce on it
		subtree = rebalance(subtree);

		//return subtree
        	return subtree;
	}

	/* delete(int)
	 *
	 * Deletes a key from the tree; throws IllegalArgumentException if
	 * the key does not exist.
	 *
	 * Uses the x=change(x) style to modify the tree.  Uses a static,
	 * recursive helper method to implement the modification.  The helper
	 * method is responsible for *all* of the changes associated with
	 * deleting the value, including all rebalances.
	 *
	 * static delete(AVLNode,int)
	 *
	 * Static, recursive helper method for delete(int).  Takes an AVLNode
	 * parameter, which might be null; if the parameter is null, then we
	 * are attempting to delete from an empty tree, and thus the delete
	 * fails.  On the other hand, this method can *return* null quite
	 * normally; this happens when we delete the last node from the tree.
	 *
	 * Always returns the root of the new tree (which might be null); does
	 * all of the rebalancing work associated with the delete.  (But see
	 * note in the spec about possibly skipping rebalance-on-delete.)
	 */
	public void delete(int val){

		//create new node
		AVLNode temp = root;

		//first search for the value in the tree
	        temp = search(val);

		//if the element is not in the tree then print error and return
	        if(temp == null){
	               	System.err.println("ERROR: Could not delete "+val+" because it was not in the tree.");
                        return;
//	               	throw new IllegalArgumentException();
	        }

		//else go delete the element
	         root= delete(root, val);
	}

	private static AVLNode delete(AVLNode root, int val){

	        // STEP 1: PERFORM STANDARD BST DELETE
        	if (root == null){
            		return null;
		}

        	// If the val to be deleted is smaller than
        	// the root's val, then it lies in left subtree
        	if (val < root.val){
            		root.left = delete(root.left, val);
		}

        	// If the val to be deleted is greater than the
        	// root's val, then it lies in right subtree
        	else if (val > root.val){
            		root.right = delete(root.right, val);
		}

        	// if val is same as root's val, then this is the node
        	// to be deleted
        	else{

                        //node with no children
                        if(root.left == null && root.right == null){
                                root = null;
                                return root;

            		// node with only one child
     		       	}else if (root.left == null){
				root = root.right;
				return root;
			}else if(root.right == null){
				root= root.left;
				return root;
                	}else //two children
				root.val = getPred(root.left);
				root.left = delete(root.left, root.val);
			}


       	  	// STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
      		root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;

		//call rebalance on it
		root = rebalance(root);
		return root;
	}//delete

	/*This reurns the element Predacessor */
        private static int getPred(AVLNode node)
        {

                AVLNode curr = node;
                while (curr.right != null){
                        curr = curr.right;
                }
                return curr.val;
        }

	/* static rebalance(AVLNode)
	 *
	 * Performs a rebalance (if required) on the current node.  This
	 * method assumes that the parameter is non-null; if it is passed a
	 * null parameter, it will hit NullPointerException.  However, it
	 * is valid for either (or both) of the children of this node to
	 * be null; to be clear, it is OK to call this on a leaf node.
	 *
	 * This method assumes that the height is correct, in the current
	 * node and all its descendants; thus, after an insert or delete,
	 * the *CALLER* must update the height before calling this method.
	 *
	 * This method returns the root of the new tree.  If no rotations
	 * were required, then this is simply the parameter; if rotations
	 * were required, then this returns the root after the rotation.
	 *
	 * This method is NON-RECURSIVE.  It runs in O(1) time.  As such, it
	 * only checks for imbalances at THIS PARTICULAR NODE, never at any
	 * descendants.
	 */
	public static AVLNode rebalance(AVLNode node)
	{
		if(node == null){
			throw new NullPointerException();
		}


		int balance = getBalance(node);        //get the blance of the node

		if (balance < -1){                      // indicates either left-left or left-right case

			if (getBalance(node.right) > 0){ // confirms left-left case
				node.right = rotateRight(node.right);
                                node = rotateLeft(node);
			}else{                           // confirms left-right case
				node = rotateLeft(node);
			}//if else

		}else if(balance > 1){                 // indicates either right-right or right-left case

			if (getBalance(node.left) < 0){ // confirms right-right case
				node.left = rotateLeft(node.left);
                                node = rotateRight(node);
			}else{
				node = rotateRight(node);
			}//if else
		}//if else

		//return the root
		return node;
	}//rebalance

	/* static getHeight(AVLNode)
	 *
	 * Helper method that returns the height of a subtree.  The parameter
	 * may be null.
	 *
	 * Since a single node has height=0, this should return -1 if passed
	 * a null parameter.
	 *
	 * This method is NON-RECURSIVE.  It runs in O(1) time.  As such, it
	 * may only check the height field in the current node; it does not
	 * look at any other nodes.
	 */
	public static int getHeight(AVLNode subtree)
	{
		if(subtree == null){
			return -1;
                }

		return subtree.height;
	}



	/* static rotateRight(AVLNode)
	 *
	 * Performs a right-rotation at the current node.  This method assumes
	 * that both the parameter, and its left child, are non-null; if
	 * either is null, then a NullPointerException will result.
	 *
	 * Returns the root of the new tree, which is always the node which
	 * (used to be) the left child of the old root.
	 *
	 * This method works by changing REFERENCES NOT VALUES.  That is, it
	 * does not inspect (or change) the .val field of any node; instead,
	 * it changes the left and right pointers.
	 *
	 * This method updates the height of all appropriate nodes after
	 * rearranging the references.
	 *
	 * This method is NON-RECURSIVE.  It runs in O(1) time.  As such, it
	 * may only check the fields in a fixed number of nodes.
	 */
	public static AVLNode rotateRight(AVLNode subtree)
	{
		//if(subtree.left == null  subtree.right == null){
		//	throw new NullPointerException();
	//	}

		AVLNode y = subtree.left;

                subtree.left = y.right;
                y.right = subtree;


		//update hiehgt
       	 	subtree.height = Math.max(getHeight(subtree.left), getHeight(subtree.right)) + 1;
        	y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

		//return the root
	        return y;
	}

	/* static rotateLeft(AVLNode)
	 *
	 * See the documentation for rotateRight() above.  This is simply its
	 * mirror image.
	 */
	public static AVLNode rotateLeft(AVLNode subtree)
	{
//		if(subtree.left == null || subtree.right == null)
//			throw new NullPointerException();

		AVLNode y = subtree.right;
                subtree.right = y.left;
                y.left = subtree;

		//update hieght
		subtree.height = Math.max(getHeight(subtree.left), getHeight(subtree.right)) + 1;
	        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

		//return root
		return y;
	}

	/*This method returns the balance factor of the current node*/
	private static int getBalance(AVLNode node){

		if(node == null){
			return 0;
		}else{

		return getHeight(node.left)-getHeight(node.right);
                }//if else
	}//getBalance
}//
