This README file is intended how the whole program works.  There are 7 main
compents for the binary search tree program, which are, insert, search, count,
delete, preOrder, inOrder, and debug.

The main method of the program is the driver for the binary search tree
program.  It gets it's input and commands from a file that it reads in.
From there, it decides what commands to run, until it hits EOF.  Some of 
commands it reads will have arguements, and some won't.  Along the way, it
has error checks that the file opened correctly or that exists.  If something
went wrong, then it exits the program and returns 1 to indicate an error.
Otherwise, it will read commands and arguements until it reaches the EOF and 
exit the program.

The insert command intiates the program to insert into the Binary search tree.
After it confirms that insert was read from the file, it will then scan for 
the arguement, which should be a postive integer, to insert.  The first thing
it does is see if it is allowed to insert into the tree.  It first checks if
root is null, if it is then it justs insert into the root by first creating a
node and then insert it's data into root->data.  If root is not null, then it
calls the search method to see if the element already exists, if it does it 
prints and error, if not then it inserts into the tree.  Then it increments 
count to indicate we inserted into the tree.

The search commands go into part of the program to search for an element in the
tree.  After confirming that search command was read, it then scans for the
arguement to see if the element is in the tree.  It returns 1 if it finds that
element was found, otherwise it will return 0.  It then prints out a message if
was found or it wasn't found.

The count command simply goes to return the count from the elements currently
in the tree.  it is intiated to be 0 in the beginning of main, it then keeps 
track of the count throughout the program while the file is open.  When the 
command is inputed, it then returns that number.

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
Once the deletion is complete, it returns the node.

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

Some anaylsis, are that all methods are in current working condition.  They all do what
they are supposed to do.  Some problems that i ran into, is figuering out how to complete
the delete method.  To the best of my knowledge, delete moethod works.  One intersting note
is that i had to create 2 helper methods to get my debug command to run properly.
