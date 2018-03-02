/* Project 4 Testcase (one of many)
 */

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;


public class Test_08_exceptions
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
		 * Tests some exceptions which are not tested elsewhere in
		 * the testcases.
		 */


		// do we get an exception when we insert() a duplicate?
		int    key = -1000*1000 + (int)(2*1000*1000 * Math.random());
		String val = HEADER_STRING+key;


		System.out.println("Checking to make sure that every dicationary throws an exception when you try to delete from an empty tree.");

		for (int d=0; d<arr.length; d++)
		{
			try {
				arr[d].delete(key);
				System.out.printf("   ERROR: Deletion in an empty tree did not throw an exception.  d=%d class=%s    key=%d\n",
				                  d, arr[d].getClass().getSimpleName(), key);
			} catch (IllegalArgumentException e) {
				// NOP
			}
		}


		System.out.println("Inserting with val=null; an exception should result.");

		for (int d=0; d<arr.length; d++)
		{
			try {
				arr[d].insert(key, null);
				System.out.printf("   ERROR: Inserting with a null parameter did not throw an exception.  d=%d class=%s    key=%d\n",
				                  d, arr[d].getClass().getSimpleName(), key);
			} catch (IllegalArgumentException e) {
				// NOP
			}
		}


		System.out.println("Inserting the chosen key into every dictionary.");

		for (int d=0; d<arr.length; d++)
			arr[d].insert(key,val);


		System.out.println("Inserting the key a second time; exceptions should result.");

		for (int d=0; d<arr.length; d++)
		{
			try {
				arr[d].insert(key,val);
				System.out.printf("   ERROR: Inserting a duplicate key did not throw an exception.  d=%d class=%s    key=%d\n",
				                  d, arr[d].getClass().getSimpleName(), key);
			} catch (IllegalArgumentException e) {
				// NOP
			}
		}


		System.out.println("Checking to make sure the getSuccessor() throws an exception if the key is not in the dictionary.");

		for (int d=0; d<arr.length; d++)
		{
			try {
				arr[d].getSuccessor(key-1);
				System.out.printf("   ERROR: Calling getSuccesor() on a non-existent key did throw an exception.  d=%d class=%s    key(existing)=%d key(broken)=%d.\n",
				                  d, arr[d].getClass().getSimpleName(),
				                  key, key-1);
			} catch (IllegalArgumentException e) {
				// NOP
			}
		}


		System.out.println("Test Completed");
	}
}

