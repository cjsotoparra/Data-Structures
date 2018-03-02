/* Project 4 Testcase (one of many)
 */


public class Test_02_emptySearch
{
	public static void main(String[] args)
	{
		/* create one of each type of dictionary for a simple test,
		 * detailed below.
		 */
		Proj04Dictionary[] arr = new Proj04Dictionary[7];
		arr[0] = new UnsortedArray();
		arr[1] = new SortedArray();
		arr[2] = new BST();
		arr[3] = new AVLTree();
		arr[4] = new HashTable();
		arr[5] = new HashMap_Wrapper();
		arr[6] = new TreeMap_Wrapper();


		/* TEST:
		 *
		 * Does the empty dictionary return null for various searches?
		 * We try this for several random keys.
		 */
		for (int i=0; i<32; i++)
		{
			System.out.println("Testing: iteration="+i);

			int key = -1000*1000 + (int)(Math.random() * 2*1000*1000);

			for (int d=0; d<arr.length; d++)
			if (arr[d].search(key) != null)
			{
				System.out.printf("   ERROR: A search in an empty Dictionary returned non-null.  iteration=%d key=%d   class=%s\n",
				                  i, key,
				                  arr[d].getClass().getSimpleName());
			}
		}


		System.out.println("Test Completed");
	}
}

