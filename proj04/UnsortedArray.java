import java.util.ArrayList;

//FileName:  UnsortedArray.java
//Authro:  Christian Soto
//Purpose: Has 6 methods that can create and delete a unsorted array

public class UnsortedArray implements Proj04Dictionary
{
	ArrayList<E> a= new ArrayList<E>();

	//class E
	class E{
		String data;
		int key;

		//contrcuor
		public E(String data){
			this.data = data;
			key = 0;
		}

	}//class E

        // insert(int,String)
        //
        // This function inserts a new record into the dictionary.
        // Any key is allowed.  However, the String must always be non-null.
        //
        // This function throws IllegalArgumentException if either of the
        // following are true:
        //     - key already exists in the data structure
        //     - String is null

	public void insert(int key, String data)
			throws IllegalArgumentException
	{
		E n = new E (data);
		n.key = key;
		if(data == null || search(key) !=null)
			throw new IllegalArgumentException();
		a.add(n);
	}


        // delete(int)
        //
        // This function deletes the key from the dictionary.
        //
        // This function throws IllegalArgumentException if:
        //     - the key does not exist in the data structure

	public void delete(int key)
		throws IllegalArgumentException
	{
		if(search(key) == null){
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < a.size(); i++) {
			if(key == a.get(i).key)
				a.remove(i);	
		}
	}


        // String search(int)
        //
        // This function searches the data structure for the key.  If the key
        // is found, then this returns the String associated with it; if the
        // key is not found, it returns null.

	public String search(int key)
	{
		for (int i = 0; i < a.size(); i++) {
			if(key == a.get(i).key)
				return a.get(i).data;
		}
		return null;
	}

        // Integer[] getKeys()
        //
        // This function generates an exhaustive list of all keys in the
        // data structure, and returns them as an array.  A new array may be
        // allocated for each call; do *NOT* reuse the same array over and
        // over.

	public Integer[] getKeys()
	{
		Integer [] keys = new Integer[a.size()];
		for (int i = 0; i < a.size(); i++) {
			keys [i] = a.get(i).key;
		}
		return keys;
	}

        // int getSuccessor(int)
        //
        // Searches the tree for the given key, and then returns the
        // successor key in the data structure - that is, the next key in a
        // sorted list of the keys.
        //
        // This function throws IllegalArgumentException if either of the
        // following are true:
        //     - the key does not exist
        //     - the key does not have any successor

	public int getSuccessor(int key)
		throws IllegalArgumentException
	{
		int temp = 0;

		//make sure the key is in the array
		if(search(key) == null){
			throw new IllegalArgumentException();
		}

		//now that we know the key is in the array, we search for the sucessor
		int successor=0;

		//iterata and find the fisrt elemetn that is larger than the key
		for (int i = 0; i < a.size() ; i++) {

			//set suceccor
			if(a.get(i).key > key){
				successor = a.get(i).key;
				break;
			}

		}//for loop 1

		//now go thourgh the array and make sure that it is the true successor
		for (int i = 0; i < a.size() ; i++) {

			//set succorsr
			if(a.get(i).key< successor && key < a.get(i).key){
				successor = a.get(i).key;
			}
		}//for loop 2

		//if success is never set then it is the max elememt and throw exception
		if(successor == 0){
			throw new IllegalArgumentException();
		}

		//return
		return successor;

	}//getSuccessor
}//unsorted

