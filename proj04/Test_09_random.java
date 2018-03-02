/* Project 4 Testcase (one of many)
 */

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;


public class Test_09_random
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


		/* RANDOM TEST:
		 *
		 * We will randomly select 1000 steps to make over time!  We
		 * will, on each step, confirm that *ALL* of the Dictionaries
		 * match the standard dictionaries.
		 *
		 * If you are failing this testcase, then set DEBUG = true
		 * below.  This will cause you to mismatch the output (since
		 * there will be lots of stuff printed), but should help you
		 * debug your errors.
		 */

		boolean DEBUG = false;

		Set<Integer> curKeys = new HashSet<Integer>();

		// confirm that the dictionaries all start in the same empty set.
		test_getKeys(arr, curKeys, DEBUG);

		// insert a few values, just to get started
		test_insert(arr, curKeys, DEBUG);
		test_insert(arr, curKeys, DEBUG);
		test_insert(arr, curKeys, DEBUG);
		test_insert(arr, curKeys, DEBUG);

		for (int i=0; i<1000; i++)
		{
			switch ((int)(5 * Math.random()))
			{
			case 0: test_insert (arr, curKeys, DEBUG);	break;
			case 1: test_delete (arr, curKeys, DEBUG);	break;
			case 2: test_search (arr, curKeys, DEBUG);	break;
			case 3: test_getKeys(arr, curKeys, DEBUG);	break;
			case 4: test_getSucc(arr, curKeys, DEBUG);	break;
			}
		}


		// we always end with a getKeys() call, just to make sure
		// that the last operations were reflected in the current
		// state.
		test_getKeys(arr, curKeys, DEBUG);


		System.out.println("Test Completed");
	}


	public static void test_insert(Proj04Dictionary[] arr, Set<Integer> keys,
	                               boolean DEBUG)
	{
		// generate a new key
		int key;
		do
			key = -1000*1000 + (int)(2*1000*1000 * Math.random());
		while (keys.contains(key));

		String val = HEADER_STRING+key;

		// record the value into the set
		keys.add(key);


		if (DEBUG)
			System.out.printf("test_insert: Inserting %d into the dictionaries.\n", key);


		// we should be able to insert this key into every dictionary.
		for (int d=0; d<arr.length; d++)
			arr[d].insert(key,val);
	}


	public static void test_delete(Proj04Dictionary[] arr, Set<Integer> keys,
	                               boolean DEBUG)
	{
		if (keys.isEmpty())
		{
			if (DEBUG)
				System.out.println("test_delete: There are no keys");
			return;
		}

		// randomly choose one key to remove
		Object[] keysOut = keys.toArray();
		int      indx    = (int)(keysOut.length * Math.random());
		int      key     = (Integer)keysOut[indx];

		keys.remove(key);

		for (int d=0; d<arr.length; d++)
			arr[d].delete(key);
	}


	public static void test_search(Proj04Dictionary[] arr, Set<Integer> keys,
	                               boolean DEBUG)
	{
		if (keys.isEmpty())
		{
			if (DEBUG)
				System.out.println("test_search: There are no keys");
			return;
		}

		// randomly choose one key to remove
		Object[] keysOut = keys.toArray();
		int      indx    = (int)(keysOut.length * Math.random());
		int      key     = (Integer)keysOut[indx];

		// this is the gold standard, expected value!
		String expectedVal = arr[arr.length-1].search(key);

		for (int d=0; d<arr.length; d++)
		{
			String actual = arr[d].search(key);

			if (actual != expectedVal)
			{
				System.out.printf("   ERROR: Retval mismatch on search().  d=%d class=%s   key=%d   val(expected)=\"%s\" val(actual)=\"%s\"\n",
				                  d, arr[d].getClass().getSimpleName(),
				                  key, expectedVal, actual);
			}
		}
	}


	public static void test_getKeys(Proj04Dictionary[] arr, Set<Integer> keys,
	                                boolean DEBUG)
	{
		// NOTE: We do *NOT* special-case the no-keys-currently case;
		// it ought to pass trivially, with empty arrays.


		if (DEBUG)
			System.out.println("test_getKeys: keys.size()="+keys.size());


		// what is the "correct" value?
		Integer[] keysExp = arr[arr.length-1].getKeys();


		// compare it to every other dictionary.  Actually, we'll compare
		// it to *ITSELF* as well, in order to confirm that the value isn't
		// changing.

		for (int d=0; d<arr.length-1; d++)
		{
			// read the keys from the data structure.  They might not
			// be sorted, so sort them after you get them!
			Integer[] keysActual = arr[d].getKeys();
			Arrays.sort(keysActual);

			if (Arrays.equals(keysExp, keysActual) == false)
			{
				System.out.printf("   ERROR: Key mismatch.  d=%d   class=%s\n", d, arr[d].getClass().getSimpleName());

				System.out.printf("       keysExp.length=%d   keysActual.length=%d\n",
				                  keysExp.length, keysActual.length);

				if (keysExp.length == keysActual.length)
				{
					for (int i=0; i<keysExp.length; i++)
					{
						if (keysExp[i] != keysActual[i])
						{
							System.out.printf("     [%d]: expected: %d actual: %d\n", i, keysExp[i], keysActual[i]);
						}
					}
				}
			}
		}
	}


	public static void test_getSucc(Proj04Dictionary[] arr, Set<Integer> keys,
	                                boolean DEBUG)
	{
		if (keys.isEmpty())
		{
			if (DEBUG)
				System.out.println("test_getSucc: There are no keys");
			return;
		}


		// randomly choose one index to test.
		Object[] keysOut = keys.toArray();
                                   Arrays.sort(keysOut);

		int      indx    = (int)(keysOut.length * Math.random());
		int      key     = (Integer)keysOut[indx];


		// did we choose to start at the last element?
		if (indx == keysOut.length-1)
		{
			for (int d=0; d<arr.length; d++)
			try {
				int badSucc = arr[d].getSuccessor(key);
				System.out.printf("test_getSucc: ERROR: A Dictionary did not throw an Exception as required.  d=%d class=%s   (bad)succ=%d\n",
				                  d, arr[d].getClass().getSimpleName(),
				                  badSucc);
			} catch (IllegalArgumentException e) {
				if (DEBUG)
					System.out.printf("  Exception thrown OK\n");
			}
		}
		else
		{
			int succExp = arr[arr.length-1].getSuccessor(key);

			for (int d=0; d<arr.length; d++)
			{
				int succActual = arr[d].getSuccessor(key);

				if (succActual != succExp)
				{
					System.out.printf("   ERROR: getSuccessor mismatch!   d=%d class=%s   succ(expected)=%d succ(actual)=%d\n",
					                  d, arr[d].getClass().getSimpleName(),
					                  succExp, succActual);
				}
			}
		}
	}
}

