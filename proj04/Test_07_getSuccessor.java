/* Project 4 Testcase (one of many)
 */

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;


public class Test_07_getSuccessor
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
		 * Insert a bunch of keys, and then walk through the list,
		 * looking for the successor.
		 */
		int   count = 100 + (int)(500 * Math.random());
		int[] keys  = Proj04Timer.randIntArray(count, false);

		for (int k=0; k<keys.length; k++)
		for (int d=0; d<arr.length; d++)
			arr[d].insert(keys[k], HEADER_STRING+keys[k]);


		// now that we've inserted the keys (*NOT* sorted), we sort
		// the array, for comparing successors.
		Arrays.sort(keys);


		// check that every key (except the last) has the proper
		// successor.  We'll check the last (expecting an exception)
		// in a later loop.
		for (int k=0; k+1<keys.length; k++)
		for (int d=0; d<arr.length;  d++)
		{
			int succ = arr[d].getSuccessor(keys[k]);

			if (succ != keys[k+1])
			{
				System.out.printf("   ERROR: getSuccessor() mismatch.  k=%d d=%d class=%s   key=%d succ(expected)=%d succ(returned)=%d\n",
			                          k,d,
			                          arr[d].getClass().getSimpleName(),
				                  keys[k],keys[k+1],succ);
			}
		}

		for (int d=0; d<arr.length; d++)
		{
			try {
				int badNext = arr[d].getSuccessor(keys[keys.length-1]);

				System.out.printf("   ERROR: getSuccessor() did *NOT* throw an exception when the input was the last key in the set.  d=%d class=%s   key=%d (wrong)successor=%d\n",
				                  d, arr[d].getClass().getSimpleName(),
				                  keys[keys.length-1], badNext);
			} catch(IllegalArgumentException e) {
				System.out.printf("Success!  A Dictionary threw an exception from getSuccessor() as expected.  d=%d class=%s\n",
				                  d, arr[d].getClass().getSimpleName());
			}
		}

		System.out.println("Test Completed");
	}
}

