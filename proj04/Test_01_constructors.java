/* Project 4 Testcase (one of many)
 *
 */


public class Test_01_constructors
{
	public static void main(String[] args)
	{
		/* simply create one of each type of dictionary, just to make sure that
		 * that basic functionality works.  All of them need to implement the
		 * interface.
		 */
		Proj04Dictionary[] arr = new Proj04Dictionary[7];
		arr[0] = new UnsortedArray();
		arr[1] = new SortedArray();
		arr[2] = new BST();
		arr[3] = new AVLTree();
		arr[4] = new HashTable();
		arr[5] = new HashMap_Wrapper();
		arr[6] = new TreeMap_Wrapper();

		System.out.println("Completed OK");
	}
}

