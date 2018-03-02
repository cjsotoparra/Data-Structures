/* Project 4 Testcase (one of many)
 */

import java.util.Set;
import java.util.HashSet;


public class Test_03_insertSearch
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
		 * If we insert a certain key, can we then search and get the
		 * exact same String back?
		 *
		 * We use a Set to ensure that we never try to insert
		 * duplicates.
		 */
		Set<Integer> oldVals = new HashSet<Integer>();

		for (int i=0; i<32; i++)
		{
			System.out.println("Testing: iteration="+i);

			int    key;
			String val;

			// generate a random int, which we haven't used before
			do
				key = -1000*1000 + (int)(Math.random() * 2*1000*1000);
			while (oldVals.contains(key));
			oldVals.add(key);

			// create a val for each key.  The contents don't matter;
			// we just need to ensure that they are all different
			// objects.
			val = HEADER_STRING+key;


			for (int d=0; d<arr.length; d++)
			{
				if (arr[d].search(key) != null)
				{
					System.out.printf("   ERROR: Before insert(), search() returned non-null!  iteration=%d key=%d   d=%d class=%s\n",
					                  i,key,
				                          d, arr[d].getClass().getSimpleName());
				}

				arr[d].insert(key, val);

				if (arr[d].search(key) != val)
				{
					System.out.printf("   ERROR: After insert(), search() returned the wrong object!  iteration=%d key=%d   d=%d class=%s\n",
					                  i,key,
				                          d, arr[d].getClass().getSimpleName());
				}
			}
		}


		// confirm that all of the keys are still present, with the
		// proper keys, in each dictionary.
		for (int key: oldVals)
		for (int d=0; d<arr.length; d++)
		{
			String val      = arr[d].search(key);
			String expected = HEADER_STRING+key;

			if (val == null)
			{
				System.out.printf("   ERROR: In the final pass, search() returned null!  key=%d   d=%d class=%s\n",
					          key,
				                  d, arr[d].getClass().getSimpleName());
			}

			if (val.equals(expected) == false)
			{
				System.out.printf("   ERROR: In the final pass, search() returned the wrong string!  key=%d   val=\"%s\" expected\"%s\"   d=%d class=%s\n",
					          key,
				                  val, expected,
				                  d, arr[d].getClass().getSimpleName());
			}
		}


		System.out.println("Test Completed");
	}
}

