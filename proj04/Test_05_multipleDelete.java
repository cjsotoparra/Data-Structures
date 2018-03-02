/* Project 4 Testcase (one of many)
 */

import java.util.Set;
import java.util.HashSet;


public class Test_05_multipleDelete
{
	public static final String HEADER_STRING = "dummy_string_for: ";

	public static void main(String[] args)
	{
		/* create the dictionaries.  I create two of each user type,
		 * to automatically check for static variables - the actions
		 * on one tree should not affect the other.
		 */
		Proj04Dictionary[] arr = new Proj04Dictionary[12];
		arr[0] = new UnsortedArray();
		arr[1] = new UnsortedArray();
		arr[2] = new SortedArray();
		arr[3] = new SortedArray();
		arr[4] = new BST();
		arr[5] = new BST();
		arr[6] = new AVLTree();
		arr[7] = new AVLTree();
		arr[8] = new HashTable();
		arr[9] = new HashTable();

		arr[10] = new HashMap_Wrapper();
		arr[11] = new TreeMap_Wrapper();


		/* TEST:
		 *
		 * Insert a bunch of keys, and then delete them back out.
		 */
		int[] keys = Proj04Timer.randIntArray(32, false);

		for (int d=0; d<arr.length; d++)
		for (int k=0; k<keys.length; k++)
			arr[d].insert(keys[k], HEADER_STRING+keys[k]);


		// confirm that all of the keys are still present, with the
		// proper keys, in each dictionary.
		for (int key: keys)
		for (int d=0; d<arr.length; d++)
			arr[d].delete(key);

		System.out.println("Test Completed");
	}
}

