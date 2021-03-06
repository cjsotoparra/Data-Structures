//AVLTree.java
//CSC 345 Spring 17 - Project 4
//Eddy Valenzuela
//Has 16 methods that are used to creatind deleting, and much more for an AVLTree
public class AVLTree implements Proj04Dictionary
{

        private AVLNode root;
        private int count;

        public AVLTree()
        {
                root = null;
        }

	public void insert(int key, String data)
		throws IllegalArgumentException
	{

                //if temp is not null then that means element is already in the tree
                if(data == null){
                	throw new IllegalArgumentException();
                }

		if(search(key) != null){
			throw new IllegalArgumentException();
		}

                //if not null then insert the element in the tree
                root = insertH(root, key, data);
                count++;
	}//insert

        private static AVLNode insertH(AVLNode subtree, int key, String data){

                //if the tree is empty then just create a new node
                if (subtree == null){
                        subtree = new AVLNode(key, data);

                //else travese the tree and find where to insert
                }else {

                        //go left
                        if (key < subtree.key){
                                subtree.left = insertH(subtree.left, key, data);
                        //else go right
                        }else{
                                subtree.right = insertH(subtree.right, key, data);
                        }
                }//if else for traversal


                //update the hieght
                 subtree.height = Math.max(getHeight(subtree.left), getHeight(subtree.right)) + 1;

                //call rebalnce on it
                subtree = rebalance(subtree);

                //return subtree
                return subtree;
        }//AVLNode insert



	public void delete(int key)
		throws IllegalArgumentException
	{
		//throw new RuntimeException("TODO");

                //create new node
  //              AVLNode temp = root;

                //first search for the value in the tree
//                temp.data = search(key);

                //if the element is not in the tree then print error and return
                if(search(key) == null){
//                	throw new IllegalArgumentException("key is in table");
                      //throw new IllegalArgumentException();
                }

		if(root==null){
			throw new IllegalArgumentException();
		}

                //else go delete the element
                 root= deleteH(root, key);

	}

        private AVLNode deleteH(AVLNode root, int key){

                // STEP 1: PERFORM STANDARD BST DELETE
                if (root == null){
                        return null;
                }

                // If the val to be deleted is smaller than
                // the root's val, then it lies in left subtree
                if (key < root.key){
                        root.left = deleteH(root.left, key);
                }

                // If the val to be deleted is greater than the
                // root's val, then it lies in right subtree
                else if (key > root.key){
                        root.right = deleteH(root.right, key);
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
                                root.key = getSuccessor(root.right.key);
                                root.left = deleteH(root.left, root.key);
                        }


                // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
                root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;

                //call rebalance on it
                root = rebalance(root);
                return root;
        }


	public String search(int key)
	{
                AVLNode node = root;
                while(node!=null){
                        if(node.key==key){
                                return node.data;
                        }else if(node.key>key){
                                node = node.left;
                        }else{
                                node = node.right;
                        }//if else
                }//while loop

                //if the element was niot ofund then return null
                return null;

	}


	public Integer[] getKeys()
	{
             Integer[] a = new Integer[count];
             AVLNode node = root;

             if (node == null) {
                 return a;
             }

             int index = 0;
             return a = getKeysH(a, node, index);

	}

       private Integer[] getKeysH(Integer[] a, AVLNode node, int index){

                      //traverse left
                      if(node != null){
                            getKeysH(a, node.left, index);
                            a[index] = node.key;
                            index++;
                      }
             return a;
        }



       public int getSuccessor(int key)
    			throws IllegalArgumentException
    		{
    	                AVLNode node = root;
    	                AVLNode parent = null;

    	                //get to the key in question
    	                while(node!=null){
    	                        if(node.key==key){

    	                                //check if there is a right subtree
    	                                if(node.right !=null){
    	                                        return minKey(node.right);
    	                                }

    	                                //if no right child then return the parent key
    	                                return parent.key;
    	                        }else if(node.key>key){
    	                                parent = node;
    	                                node = node.left;
    	                        }else{
    	                                parent = node;
    	                                node = node.right;
    	                        }//if else
    	                }//while loop
    	                return key;
    		}

        private static int getHeight(AVLNode subtree)
        {
                if(subtree == null){
                        return -1;
                }

                return subtree.height;
        }

        private static AVLNode rebalance(AVLNode node)
        {
                if(node == null){
                        throw new NullPointerException();
                }


                int balance = getBalance(node);        //get the blance of the n$

                if (balance < -1){                      // indicates either left$

                        if (getBalance(node.right) > 0){ // confirms left-left c$
                                node.right = rotateRight(node.right);
                                node = rotateLeft(node);
                        }else{                           // confirms left-right $
                                node = rotateLeft(node);
                        }//if else

                }else if(balance > 1){                 // indicates either right$

                        if (getBalance(node.left) < 0){ // confirms right-right $
                                node.left = rotateLeft(node.left);
                                node = rotateRight(node);
                        }else{
                                node = rotateRight(node);
                        }//if else
                }//if else

                //return the root
                return node;
        }//rebalance

        private static AVLNode rotateRight(AVLNode subtree)
        {
                //if(subtree.left == null  subtree.right == null){
                //      throw new NullPointerException();
        //      }

                AVLNode y = subtree.left;

                subtree.left = y.right;
                y.right = subtree;


                //update hiehgt
                subtree.height = Math.max(getHeight(subtree.left), getHeight(subtree.right)) +1;
                y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

                //return the root
                return y;
        }
        
        private int  minKey(AVLNode node) {
            AVLNode current = node;

           /* loop down to find the leftmost leaf */
           while (current.left != null) {
                  current = current.left;
           }
           return current.key;
        }//minKey


        /* static rotateLeft(AVLNode)
         *
         * See the documentation for rotateRight() above.  This is simply its
         * mirror image.
         */
        private static AVLNode rotateLeft(AVLNode subtree)
        {
                AVLNode y = subtree.right;
                subtree.right = y.left;
                y.left = subtree;

                //update hieght
                subtree.height = Math.max(getHeight(subtree.left), getHeight(subtree.right)) +1;
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
}



