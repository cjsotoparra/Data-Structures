//File: SortedArray.java
//Author: Christian Soto
//Purpose: Has 6 methods that can create and delete a sorted array, as well find the u
//	   successor, and search for keys/  It can also return an array of keys.

import java.util.*;

public class SortedArray implements Proj04Dictionary
{
	private ArrayList<E> a= new ArrayList<E>();

	class E{
		String data;
		int key;

		public E(String data){
			this.data = data;
			key = 0;
		}//construcor

	}//class e

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

		if(data == null || search(key) !=null){
			throw new IllegalArgumentException("string null");
		}

		E n = new E (data);
		n.key = key;
		n.data = data;

		if(a.size() ==0){
			a.add(n);
			return;
		}

		for (int i = 0; i < a.size(); i++) {
	        // if the element you are looking at is smaller than x, 
	        // go to the next element
		        if (a.get(i).key < key){
		        	continue;
			}
	        	a.add(i, n);
	        	return;
		}

		 a.add(n);

	}//insert

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
			throw new IllegalArgumentException("key is missing");
		}

		for (int i = 0; i < a.size(); i++) {
			if(key == a.get(i).key){
				a.remove(i);
			}
		}
	}


        // String search(int)
        //
        // This function searches the data structure for the key.  If the key
        // is found, then this returns the String associated with it; if the
        // key is not found, it returns null.

	public String search(int key)
	{
		int s = a.size();
		int l = 0;
		int h = s - 1;

		while(l<=h) {
			int mid = (l + h) / 2;
			if(a.get(mid).key < key) {
				l = mid+1;
			}
			else if(a.get(mid).key > key) {
				h = mid - 1;
			}else{
				return a.get(mid).data;
			}
		}
		return null;
	}

        // Integer[] getKeys()
        //
        // This function generates an exhaustive list of all keys in the
        // data structure, and returns them as an array.  A new array may be
        // allocated for each call; do *NOT* reuse the same array over and

	public Integer[] getKeys()
	{

		//simply go throug the array and put the keys into the Integer array
		Integer [] keys = new Integer[a.size()+1];
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
		if(search(key) == null){
			throw new IllegalArgumentException();
		}

		if(key == a.get(a.size()-1).key){
			throw new IllegalArgumentException();
		}

		for (int i = 0; i < a.size() -1; i++) {
			if(key == a.get(i).key){
				return a.get(i+1).key;
			}
		}
		return -1;
	}//getSucccessor
}//sorted


