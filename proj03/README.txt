The main method of the program is the driver for the AVL tree
program.  It gets it's input and commands from a file that it reads in.
From there, it decides what commands to run, until it hits EOF.  Some of 
commands it reads will have arguements, and some won't.  Along the way, it
has error checks that the file opened correctly or that exists and if the 
commands are valid.  If something went wrong, then it exits the program and 
returns 1 to indicate an error.Otherwise, it will read commands and 
arguements until it reaches the EOF and exit the program.

The insert command intiates the program to insert into the AVL tree.
After it confirms that insert was read from the file, it will then scan for 
the arguement, which should be a postive integer, to insert.  The first thing
it does is see if it is allowed to insert into the tree.  It first checks if
root is null, if it is then it justs insert into the root by first creating a
node and then insert it's data into root->data.  If root is not null, then it
calls the search method to see if the element already exists, if it does it 
prints and error, if not then it inserts into the tree.  It then updates the 
hieght of the current node.  After that if will check and rebalance if 
nessaceorry by calling the rebalance method.

The search commands go into part of the program to search for an element in the
tree.  After confirming that search command was read, it then scans for the
arguement to see if the element is in the tree.  It returns 1 if it finds that
element was found, otherwise it will return null.

The delete command will go into the delete method and delete a node.  Before it
goes into the delete method, it first checks if the node is even in the tree. If
it not found, then it prints and error, otherwise it goes into the delete method.
In the delete method, it first recursivily searches for the node.  Once the node,
is found it deteremines what kind of node it is.  In other words, if the node is 
a leaf, if it has one child, or two children.  The first case is the easist, if 
it is a leaf, it just deletes the node and returns null.  It it has one child,
then it deletes the parent of the child and maves the child up by one in the 
tree.  If it has two children, then it finds the predacessor.  Once the predacessor
is found, it deletes the node and moves the predacesor into where the node was.  It
then calls the delete method once again on the predacessor to delete the duplicate.
Once the deletion is complete, it returns the node.  After, it will update the height
of the node.  Once the hieht is done, it will then check if it needs to be rebalanced
by calling the rebalance method.  Sometimes deleteing a node will casuse mutliple
rebalances for the tree to stay balanced.

The inOrder command, will traverse the tree recursivly and print the data of the
tress in inOrder form.

The preOrder command, will similiary travers the tree, and print out the data of the
tree in pre-order format.

The debug command will prompt the program to make a file.  Within that file, it will
contain information about the tree, and who the children of each node, if it even has 
children.  It first creates a file and then opens it.  It will write to the file some
prelimary things like the name of the command and its arguements.  The arguements are
the nodes in the tree and it's children and where they point to.  It does this with 
assisstance of 2 helper methods.  inOrderWrite will write to the file of all the nodes
in the tree in in-order format.  After that, it will call the printChildren method to
go through the nodes again and write to the file the children of each node that it has.
it will then close the file after these two methods are executred.

The rotateleft method ia called If a tree becomes unbalanced, when a node is inserted 
or deleted into the right subtree of the right subtree, then we perform a 
single left rotation.  If the balance is more than -1 or 1. then we perform rotations.
 There are cases in which we will need to do double rotations.We determine this by getting 
the balance factor of the current node we are at. After weare done rotateing, we update 
the height of the nodes that were rotated.

The rotateright method works the same way as the rotateleft method by the inverse way.

The getBalance method simply returns the balance of the current node by subtracing
the height of the left subtree and the right subtree.

The getHiehgt method just returns the height of the current node.

Some complications that arose is first understanding how nodes are rotated.  After that,
I needed to understand when to do rotations on the tree, which is related to the balance
factor.  Getting the rotations was also difficult to understand as well, drawing it
became simple, but coding proved to be a challenge.  Then finally, getting the double
rotaions was the last challenging step.  I think the most difficult was getting delete
to work because of the ripple effect it had when i deleted and needed rebalanceing.
