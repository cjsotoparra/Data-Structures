/* class Proj05StudentCode
 *
 * This is the file that the students must turn in.  The instructors have
 * provided the skeleton for it.
 *
 * Skeleton code: Russell Lewis
 * Author:        TODO
 */


import java.io.*;
import java.util.*;


public class Proj05StudentCode
{
	/* this must open up the filename specified, and then print out a
	 * complete .dot file, which includes all of the information from
	 * the graph.
	 */
	public static void printDotFile(Proj05Vertex[] verts, String filename)
		throws IOException
	{

                BufferedWriter bw = null;
                FileWriter fw = null;
                String content = null;

                try {
                        //open file here
                        fw = new FileWriter(filename);
                        bw = new BufferedWriter(fw);
                        bw.write("digraph {\n");

                        //go through the array of vertices
                        for(int i = 0; i < verts.length; i++){

                              //write the vertex number to the file
                              content = "  " + verts[i].name + ";\n";
                              bw.write(content);

                              //iterate through the edges if any
                              for(Proj05Edge e: verts[i].outEdges){

                                     //Print Edges
                                     content = "    " + verts[i].name + " -> " + e.toVrt.name + " [label=" + e.weight + "];\n";
                                     bw.write(content);

                              }//edge for loop

                        }//vertex for loop

                        bw.write("}\n");
                } catch (IOException e) {
                        System.err.println("Error: " + e.getMessage());
                } finally {

                        if(bw != null){
                               bw.close();
                        }
                }//end try/catch/finally

	}//end method


	/* this checks to see if one index is reachable from another.  It
	 * prints out the solution to System.out.
	 *
	 * The 'fromIndx' and 'toIndx' are the indices, in the verts[]
	 * array parameter, of the endpoints of the search.  The path must
	 * start at the 'from' node, and get to the 'to' node.
	 */
	public static void reachable(Proj05Vertex[] verts,
	                             int fromIndx, int toIndx)
	{

		//set the connect component to -1
		for(int i=0; i<verts.length; i++){
			verts[i].accInt = -1;
		}//for loop1

		//Create Stacks to put the vertices into
		Stack<Proj05Vertex> vertStack = new Stack<Proj05Vertex>();
		Stack<Proj05Vertex> returnStack = new Stack<Proj05Vertex>();
		Stack<Proj05Vertex> printStack = new Stack<Proj05Vertex>();

		//set the vert fromIntdx to true because it was visited
		verts[ fromIndx ].accBool = true;

		//put the first vertex onto the stact vert
		vertStack.push( verts[ fromIndx ] );


		returnStack = rHelper( vertStack , verts , fromIndx , toIndx );

		if(returnStack == null){

                        //print no path message
			System.out.println("There is no path from " + verts[fromIndx].name + " to " + verts[toIndx].name);
		}else {

			while( !returnStack.isEmpty() ){
				printStack.push( returnStack.pop() );
			}

			System.out.print( "Reachable: " );
			while( !printStack.isEmpty() ){
				Proj05Vertex n = printStack.pop();
				if( printStack.isEmpty() ){
					System.out.println( n.name );
				}
				else{
					System.out.print( n.name + " -> ");
				}
			}

                }
	}


	/* This methods returns null if is not reachedable or the vertex at toIndx*/
	private static Stack<Proj05Vertex> rHelper(Stack<Proj05Vertex> vertStack , Proj05Vertex[] verts, int fromIndx, int toIndx){

		//itetrate throught the stack
		while( !vertStack.isEmpty() ){

			//set vertes to vertStack peek
			Proj05Vertex vrtx = vertStack.peek();

			if( vrtx.name.equals( verts[ toIndx ].name )){
				return vertStack;
			}

			Proj05Vertex v = Cnode( vrtx );

			if( v == null ){
				vertStack.pop();
			}
			else{
				if( v.accBool == false){
					v.accBool = true;
					vertStack.push( v );
				}
				else{
					vertStack.pop();
				}
			}
		}

		return null;
	}//DFS2


	private static Proj05Vertex Cnode( Proj05Vertex node ) {

		for( int i = 0 ; i < node.outEdges.size() ; i++ ){
			if( node.outEdges.get(i).toVrt.accBool == false ){
				return node.outEdges.get(i).toVrt;
			}
		}

		return null;
	}

        private static void DFS3(Proj05Vertex v){

		v.accBool = true;

		for(Proj05Edge e: v.outEdges){

			//if vertex is unvisited, then visit it
			if(e.toVrt.accBool == false){
				e.toVrt = v;  //store link toward root
				DFS3(v);
			}
		}

        }//DFS3


	private static void DFS4(Proj05Vertex v, int ccn){
		//Label this vertex with connect component number ccn
		v.accInt = ccn;

		//Recurse on unlabled neighbors
		for(Proj05Edge e: v.outEdges){

			//if vertex is unlabled
			if(e.accInt < 0){
				DFS4(e.toVrt, ccn);
			}
		}

	}


	/* this must run dijkstra's algorithm.  It prints out the solution
	 * to System.out - which will include both the path, and also the
	 * total length of the path.
	 *
	 * The 'fromIndx' and 'toIndx' are the indices, in the verts[]
	 * array parameter, of the endpoints of the search.  The path must
	 * start at the 'from' node, and get to the 'to' node.  And, of
	 * course, it must also be optimal.
	 */
	public static void dijkstra(Proj05Vertex[] verts,
	                            int fromIndx, int toIndx)
	{


                //set the connect component to -1
                for(int i=0; i<verts.length; i++){
                        verts[i].accInt = -1;
                }//for loop1

                int current_component_num =0;

                //itertate thg=rough the vertices
                for(int j = 0; j<verts.length; j++){

                        //check if it is unlabled and recurse if it is
                        if(verts[j].accInt < 0){
                                DFS4(verts[j], current_component_num++);
                        }

                }//for loop 2

                if(verts[fromIndx].accInt != verts[toIndx].accInt){

                        //print no path message
                        System.out.println("There is no path from " + verts[fromIndx].name + " to " + verts[toIndx].name);
                        return;
                }

                
//throw new RuntimeException("TODO");
	}
}

