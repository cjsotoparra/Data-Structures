/*Filename:     Proj02_BST.c
  Assignement:  Proj02 binary search trees
  Aruthor:      Christian Soto
  Purpose:      The purpose of this program is to sho how binary trees
                work.  The main compents of this program are insert,
                delete, debug, count, search, preOrder, and inOrder.
*/

# include <stdio.h>
# include <stdlib.h>
# include <string.h>

//our struct node that will all the data of a node in the tree
typedef struct BST {
   int data;
   struct BST *left;
   struct BST *right;
} node;

//search the tree
int searchTree(node *root, int key) {
   //create temp node to search tree with
   node *temp;
   temp = root;

   //look for the int
   while (temp != NULL) {
      if (temp->data == key) {
         return 1;
      }//if found


      if (temp->data > key){
         temp = temp->left;
      }else{
         temp = temp->right;
      }//traverse

   }//while loop

   //return 0 if not found
   return 0;
}//search

//create a new node
node *create_node(){
   node *temp;
   temp = (node *) malloc(sizeof(node));

   //error check
   if(temp==NULL){
      fprintf(stderr, "out of memory\n");
      exit(1);
   }

   temp->left = NULL;
   temp->right = NULL;
   return temp;
}//create_node

/*insert node into bst*/
void insert(node *root, node *new_node){

   //check where to insert the node

   //go left
   if (new_node->data < root->data) {

      //check to see if we can insert on the left subtree
      if (root->left == NULL){

         //insert the node in the left
         root->left = new_node;
      }else{
         //else recurse to the left
         insert(root->left, new_node);
     }//else if for recursing left
   }//if statement 1

   //go right
   if (new_node->data > root->data) {

      if (root->right == NULL){
         root->right = new_node;
      }else{
         insert(root->right, new_node);
      }//else if statment for reursing right
   }//if statment 2
}//insert

//print elements inOrder
void inOrder(node *temp) {

   //traverse through the tree printing
   if (temp != NULL) {

      //recurse left
      inOrder(temp->left);

     //print
      printf("%d ", temp->data);

      //recurse right
      inOrder(temp->right);
   }//if statement to traverse and print

}//inOrder

/*Write to the file while traversing inOrder*/
void inOrderPrint(node *temp, FILE *fp2){

   //travese through the tree
   if (temp != NULL) {

      //recurse left
      inOrderPrint(temp->left, fp2);
      //print
      fprintf(fp2, "%d;\n", temp->data);
      //recurse right
      inOrderPrint(temp->right, fp2);
   }//if statement to traverse and print

}//inOrderPrint

/*Retruns 1 is the node is a leaf, otherwise it returns 0*/
int isLeaf(node *root){

       if(root->right==NULL && root->left==NULL){
           return 1;
       }else {
           return 0;
      }

}//isLeaf

/*This prints the node and the children of the node*/
void printChildren(node *temp, FILE *fp2, int ret){

   //travese through the tree
   if (temp != NULL) {

      //recurse left
      printChildren(temp->left, fp2, ret);

      //check if is not a leaf
      ret = isLeaf(temp);
      if(ret==0){

           //check is it has a left child first
           if(temp->left !=NULL){
            //print
            fprintf(fp2, "%d->%d;\n", temp->data, temp->left->data);
           }

           //check to see if it has a right child
           if(temp->right !=NULL){
                fprintf(fp2, "%d->%d;\n", temp->data, temp->right->data);
          }

      }//leaf check

      //recurse right
      printChildren(temp->right, fp2, ret);
   }//if statement to traverse and print

}//printChildren

/*Traverse through the tree and printing data in preOrder format*/
void preOrder(node *temp) {

  //traverse the tree
  if (temp != NULL) {

      //print element first
      printf("%d ", temp->data);

      //recurse left
      preOrder(temp->left);

      //recurse right
      preOrder(temp->right);

   }//if statement to traverse

}//preOrder

//delete node
node* deleteNode(node* root, int key){

     //base case
     if(root==NULL){
        return root;
      }

     //traverse to find the value

     //go left
     if(key< root->data){
         root->left = deleteNode(root->left, key);

     //go right
     }else if(key > root->data){
        root->right = deleteNode(root->right, key);

     //if it's not less than or greater than, then it must be equal
     }else{

         //case 1: node with only one child or no child
         if(root->left==NULL && root->right==NULL){
            free(root);
            return NULL;

         //case 2: node with one child
         }else if(root->left==NULL){
           node *temp = root->right;
           free(root);
           root = temp;
           return temp;

        }else if(root->right==NULL){
           node *temp = root->left;
           free(root);
           root = temp;
           return temp;

        //case 3: node with 2 children
        }else{

          node *temp = root->left;
          node *parent = NULL;

            //traverse to the right
            while(temp->right !=NULL){
               parent = temp;
               temp = temp->right;

            }//while loop for traversal

            //replace root->data with the right max value
            root->data = temp->data;

            //recurse to delete the parents child now
            if(parent != NULL){
                parent->right = deleteNode(parent->right, parent->data);
            }else{

                root->left = deleteNode(root->left, root->data);
            }//if statment to delete parent or parent child

        }//if statement for the cases

     }//else if statement 1

   return root;
}//deleteNode

/*Returns 1 is the tree is empty, otherwise it returns 0*/
int isEmpty(node *root){

     if(root==NULL){
        return 1;
     }else{
        return 0;
     }//if esle statment

}//isEMpty

//our main method acts as the driver to the whole program
int main (int argC, char *argV[]){
        FILE *fp;
        int status =0;
        int data;
        int ret = 0;
        int count = 0;
        node *root = NULL;

        //open the file and read from it
        fp = fopen(argV[1],"r");

            //error check
            if(fp == NULL){

                fprintf(stderr,"Could not open file %s\n", argV[1]);
		status=1;
                exit(1);
            }//if statment

            //read from file using fcanf
            char p2[9];

            //scan the strings of the file into str array
            while(fscanf(fp, "%8s", p2) != EOF){


               if(strcmp(p2, "insert")==0){

                   //scan for the int now
                   fscanf(fp, "%d", &data);

                   //check to see if the tree is empty first
                  if(root==NULL){

                     root = create_node(root);
                     root->data = data;
                     count++;
                  }else{

                     //check to see if the node already exists
                     ret = searchTree(root, data);
                     if(ret==1){
                        printf("ERROR: Could not insert %d because it is already in the tree.\n", data);
                     }else{
                        node *newNode = create_node();
                        newNode->data = data;
                        insert(root, newNode);
                        count++;
                     }//if statement to insert root or newNode

                  }//if statement to insert

              }else if(strcmp(p2, "search")==0){

                  //scan for the int now
                  fscanf(fp, "%d", &data);

  		  //set return value
                  ret=searchTree(root, data);

 		  //print approtiapte responce
		  if(ret==1){
			printf("HIT: The value %d was found in the tree.\n", data);
		  }else{
			printf("MISS: The value %d was *NOT* found in the tree.\n", data);
		  }//else if statemnet to print if it was found or not found

              }else if(strcmp(p2, "delete")==0){

                  //scan for the int now
                  fscanf(fp, "%d", &data);

                  //first check if the element is even in the tree
                  ret= searchTree(root, data);

                  if(ret==1){

                     root = deleteNode(root, data);
                     count--;

                  }else{

                     printf("ERROR: Could not delete %d because it was not in the tree.\n", data);

                  }//if statement to delete or print messagae

              } else if(strcmp(p2, "count")==0){

                   //print count
                   printf("Current tree size: %d nodes\n", count);

              }else if(strcmp(p2, "preOrder")==0){

                   preOrder(root);

                   //print new line
                   printf("\n");

              }else if(strcmp(p2, "inOrder")==0){

                    inOrder(root);
                    printf("\n");

              }else if(strcmp(p2, "debug")==0){

                      //create file pointer and string to store filename and node
                      FILE *fp2=NULL;
                      char filename[129];
                      node * curr = root;

                      //scan for the filename now
                      fscanf(fp, "%128s", filename);

                      //open the file and write to it
                      fp2 = fopen(filename, "w");

                      //error check
                      if(fp2==NULL){
                            fprintf(stderr, "Error: could not open %s\n", filename);
                            exit(1);
                      }

                     //write to the file
                     fprintf(fp2, "digraph\n");
                     fprintf(fp2, "{\n");

                      //get all the data from the tree by traversing with a while loop
                     inOrderPrint(curr, fp2);
                     printChildren(curr, fp2, ret);

                     fprintf(fp2, "\n");
                     fprintf(fp2, "}");

                     //close the file
                     fclose(fp2);

            } else{

             //do nothing

            }//else if statement


       }//while loop 2

       //close the file for it is not needed to be open anymore
       fclose(fp);

    return status;
}//main
