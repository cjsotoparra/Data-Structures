/* Project 4 Testcase (one of many)
 */

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;


public class Test_06_getKeys
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
		 * Insert a bunch of keys, and then check to see if getKeys()
		 * works properly.
		 */
		int   count = 100 + (int)(500 * Math.random());
		int[] keys  = Proj04Timer.randIntArray(count, false);

		for (int k=0; k<keys.length; k++)
		for (int d=0; d<arr.length; d++)
			arr[d].insert(keys[k], HEADER_STRING+keys[k]);


		// now that we've inserted the keys (*NOT* sorted), we sort
		// the array, for easier comparing later.
		//
		// UPDATE: Convert this to an array of Integer, so that it
		//         matches the getKeys() return type.
		Arrays.sort(keys);
		Integer[] keysI = new Integer[keys.length];
		for (int k=0; k<keys.length; k++)
			keysI[k] = keys[k];


		// confirm that all of the keys are still present, with the
		// proper keys, in each dictionary.
		for (int d=0; d<arr.length; d++)
		{
			Integer[] getKeysOut = arr[d].getKeys();
			Arrays.sort(getKeysOut);

			if (Arrays.equals(keysI, getKeysOut) == false)
			{
				System.out.printf("   ERROR: getKeys() mismatch.  d=%d class=%s\n", d, arr[d].getClass().getSimpleName());
			}
		}

		System.out.println("Test Completed");
	}
}

