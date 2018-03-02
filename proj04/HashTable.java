//File NameHashTable.java
//Author: Christian Soto
//Has 6 methods to use  hashtable and includes a node class and a HashTable class
import java.util.Arrays;

public class HashTable implements Proj04Dictionary
{
	//set intial size
	private final static int TABLE_SIZE = 5000;
	private int counter;
    	HashNode[] table;
    	int inserts= 0;

    	HashTable() {
		//make a table
  		table = new HashNode[TABLE_SIZE];

		//intialize table
        	for (int i = 0; i < TABLE_SIZE; i++){
               		 table[i] = null;
		}
    	}

	public class HashNode {
		private int key;
		private String data;
	        private HashNode next;

		//contructor
	        HashNode(int key, String data) {
	            this.key = key;
	            this.data = null;
	            this.next = null;
	        }

	      //getters and setters
	      public String getData() {
	            return data;
	      }

	      public void setData(String data) {
	            this.data = data;
	      }

	      public int getKey() {
	            return key;
	      }

	      public HashNode getNext() {
	            return next;
	      }

	      public void setNext(HashNode next) {
	            this.next = next;
	      }
	}


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

		//if the key exist or the string is null, throw exception
		if(search(key) != null || data == null){
			throw new IllegalArgumentException("illegal");
		}

		//hash the key
		int hash = Math.abs(key % TABLE_SIZE);

        	if (table[hash] == null){
              		table[hash] = new HashNode(key, data);
              		table[hash].setData(data);
        	}
        	else {
              		HashNode entry = table[hash];

			while (entry.getNext() != null && entry.getKey() != key){
                    		entry = entry.getNext();
			}//while loop

              		if (entry.getKey() == key){
                    		entry.setData(data);
			}
              		else{
                    		entry.setNext(new HashNode(key, data));
			}//else if statement 2

        	}//else if statement 1

		//increament insert to indicate a key was put in
     		inserts +=1;
		//increase the size of the array
        	if((inserts/TABLE_SIZE) >= .5){
        		Arrays.copyOf(table,TABLE_SIZE*2);
       		 }

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
		if(search(key)== null){
			throw new IllegalArgumentException();
		}

		int hash = Math.abs(key% TABLE_SIZE);

        	if (table[hash] != null){

	                HashNode prevEntry = null;
        	        HashNode entry = table[hash];

			while (entry.getNext() != null && entry.getKey() != key) {
				prevEntry = entry;
                      		entry = entry.getNext();
              		}//while loop

              		if (entry.getKey() == key) {
                    		if (prevEntry == null){
                         		table[hash] = entry.getNext();
                    		}else{
                         		prevEntry.setNext(entry.getNext());
				}//else if satement
              		}//if statement 2
        	}//if stateement 1
	}//delete

        // String search(int)
        //
        // This function searches the data structure for the key.  If the key
        // is found, then this returns the String associated with it; if the
        // key is not found, it returns null.

	public String search(int key)
	{

		int hash = (Math.abs(key) % TABLE_SIZE);
	        if (table[hash] == null){
        	      return null;
        	}
        	else {
              		HashNode entry = table[hash];

              		while (entry != null && entry.getKey() != key){
                    		entry = entry.getNext();
			}

	              	if (entry == null){
        	            return null;
			}else{
                    	    return entry.getData();
			}
        	}
	}//search

        // Integer[] getKeys()
        //
        // This function generates an exhaustive list of all keys in the
        // data structure, and returns them as an array.  A new array may be
        // allocated for each call; do *NOT* reuse the same array over and
        // over.

	public Integer[] getKeys()
	{
		Integer [] keys = new Integer[inserts];
        	HashNode entry;
        	int j = 0;

	        for(int i = 0;i < TABLE_SIZE; i++){
        		if (table[i] == null){
        			continue;
        		}

		        entry = table[i];
            		while(entry != null){
            			keys [j] = entry.key; 
            			j++;
            			entry = entry.getNext();
            		}
        	}
	        return keys;
	}//getKeys

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
		int hash = (key % TABLE_SIZE);
        	if (table[hash] == null){
        		throw new IllegalArgumentException("key is null");
        	}
        	else {
       		        HashNode entry = table[hash];
			while (entry != null && entry.getKey() < key){
                    		entry = entry.getNext();
			}

	              if (entry == null){
        	    	  throw new IllegalArgumentException("no succesor");
              		}else{
                    		return entry.getKey();
			}
        	}//else if
	}//getSuccessor
}//HasHTable

