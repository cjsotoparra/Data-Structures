//BST.java
//CSC 345 Spring 17 - Project 4
//Eddy Valenzuela
//Has 7 methods that create and delete a BST it also inludes a Node class
public class BST implements Proj04Dictionary
{

        private AVLNode root;
        private int count;
        public BST(){

               root =null;
        }

	public void insert(int key, String data)
		throws IllegalArgumentException
	{
                //if temp is not null then that means element is already in the$
                if(data == null || search(key)!=null){
                	throw new IllegalArgumentException("key is in table");
                }

                //if not null then insert the element in the tree
                root = insertH(root, key, data);
                count++;

	}

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

                return subtree;

        }//insertH

	public void delete(int key)
		throws IllegalArgumentException
        {

                //first search for the value in the tree

                //if the element is not in the tree then print error and return
                if(search(key) == null){
	         //      	throw new IllegalArgumentException();
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

               return root;
        }//deleteH

	public String search(int key)
	{
		AVLNode curr = root;
		while(curr!=null){
			if(curr.key ==key){
				return curr.data;
			}else if(curr.key>key){
				curr = curr.left;

			}else{
				curr = curr.right;
			}
		}

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
                            getKeysH(a, node.right,index);
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

                /* Given a non-empty binary search tree, return the minimum data
                   value found in that tree. Note that the entire tree does not need
                   to be searched. */

                private int  minKey(AVLNode node) {
                         AVLNode current = node;

                        /* loop down to find the leftmost leaf */
                        while (current.left != null) {
                               current = current.left;
                        }
                        return current.key;
                }//minKey
}

