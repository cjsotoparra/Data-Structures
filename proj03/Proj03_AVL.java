import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Proj03_AVL {

	public static void main(String arg[]){

	String line = null;
	boolean ret = false;
	boolean ret2 = false;

	//create 4 tree objects
        AVLTree t1 = new AVLTree();
        AVLTree t2 = new AVLTree();
        AVLTree t3 = new AVLTree();
        AVLTree t4 = new AVLTree();

	//create an array of AVLTree objects to store the trees in
        AVLTree [] arr = new AVLTree[4];
        arr[0] = t1;
        arr[1] = t2;
        arr[2] = t3;
        arr[3] = t4;

	//get the filename in the argurment
        String fileName = arg[0];
		try {
	    //open the file to read
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

	    //go through the file and read all the lines
            while((line = bufferedReader.readLine()) != null) {
            	if(line.isEmpty()){
            		continue;
                }

		//Trim leading and trailing whitespace
                 line = line.trim();

		//spilt the string and put it in an array
            	String[] arr2 = line.split("\\s+");

            	//error check the command
		ret = errorCheck(arr2);

		if(ret == false){
			System.out.println("Invalid testcase format");
                        System.exit(1);
		}//if statement for error checking

		//get the index of the tree

		//check the if the numbers are valid as well
		ret = errorCheck2(arr2[0]);
            	int index = Integer.parseInt(arr2[0]);

		//only preOrder and inORder should have a length of 2
            	if(arr2.length == 2){

            		if(arr2[1].equals("inOrder")){
            			arr[index].print_inOrder();
            		}
            		if(arr2[1].equals("preOrder")){
            			arr[index].print_preOrder();
            		}

            	}else if (arr2.length == 3){

            		if(arr2[1].equals("insert")){

				ret2 = errorCheck2(arr2[2]);
	                        if(ret == false){
        	                         System.err.println("Invalid testcase format");
                	                 System.exit(1);
				}

            			arr[index].insert(Integer.parseInt(arr2[2]));

            		}//insert

            		if(arr2[1].equals("search")){

				ret2 = errorCheck2(arr2[2]);
                                if(ret == false){
                                         System.err.println("Invalid testcase format");
                                         System.exit(1);
                                }

				arr[index].search(Integer.parseInt(arr2[2]));
            		}//search

            		if(arr2[1].equals("debug")){
            			arr[index].debug(arr2[2]);
            		}

            		if(arr2[1].equals("delete")){
            			ret2 = errorCheck2(arr2[2]);
                                if(ret == false){
                                         System.err.println("Invalid testcase format");
                                         System.exit(1);
                                }

				arr[index].delete(Integer.parseInt(arr2[2]));
            		}
            	}else{
			//if none of the commends are good then print to Std error
            		System.err.println("Invalid testcase format");
            		System.exit(0);
            	}//if else

		//make the line null again
                line = null;

            }//while loop

            //close the file
            bufferedReader.close();

        } catch(FileNotFoundException ex) {
            System.err.println(
                "Error in opening file '" +
                fileName + "'");
        }//catch 1
        catch(IOException ex) {
            System.err.println(
                "Error in reading file '"
                + fileName + "'");
        }//catch 2

	}//main

	private static boolean errorCheck(String [] arr) {

		boolean ret = true;
		//first check the length of the array
		//which should be 2 or 3 only
		if(arr.length < 2 || arr.length > 3){
			return false;
		}

		return ret;
	}//errorCheck

	private static boolean errorCheck2(String input) {

                boolean ret = true;

		 try
                {
       	                Integer.parseInt(input);
               	        return true;
            	}
                       	catch( Exception e)
             	{
                       	return false;
                }


//                return ret;
        }//errorCheck2
}

