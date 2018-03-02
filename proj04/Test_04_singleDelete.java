/* Project 4 Testcase (one of many)
 */

import java.util.Set;
import java.util.HashSet;


public class Test_04_singleDelete
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
		 * If we insert a single value, and then delete it, does it
		 * go away?
		 */
		int    key = -1000*1000 + (int)(Math.random() * 2*1000*1000);
		String val = HEADER_STRING+key;


		// first pass: insert into each dictionary
		for (int d=0; d<arr.length; d++)
		{
			if (arr[d].search(key) != null)
			{
				System.out.printf("   ERROR: Before insert(), search() returned non-null!  key=%d   d=%d class=%s\n",
				                  key,
			                          d, arr[d].getClass().getSimpleName());
			}

			arr[d].insert(key, val);

			if (arr[d].search(key) != val)
			{
				System.out.printf("   ERROR: After insert(), search() returned the wrong object!  key=%d   d=%d class=%s\n",
				                  key,
			                          d, arr[d].getClass().getSimpleName());
			}
		}


		// second pass: delete from each dictionary
		for (int d=0; d<arr.length; d++)
		{
			if (arr[d].search(key) != val)
			{
				System.out.printf("   ERROR: Before delete(), search() returned the wrong object!  key=%d   d=%d class=%s\n",
				                  key,
			                          d, arr[d].getClass().getSimpleName());
			}

			arr[d].delete(key);

			if (arr[d].search(key) != null)
			{
				System.out.printf("   ERROR: After delete(), search() returned non-null!  key=%d   d=%d class=%s\n",
				                  key,
			                          d, arr[d].getClass().getSimpleName());
			}
		}


		System.out.println("Test Completed");
	}
}

