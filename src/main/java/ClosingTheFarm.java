//USACO 2016 US Open Contest, Silver
//Problem 3. Closing the Farm

import java.util.*;
import java.io.*;

public class ClosingTheFarm {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("closing.in"));
		int n = in.nextInt();   // barns
	    int m = in.nextInt();   // edges
	    
	    Barn[] barns = new Barn[n+1];
	    
	    for(int i=1; i<=n; i++) {
	    	barns[i]=new Barn(i);
	    }
	    
	    for(int i=0; i<m; i++) {
	    	int p = in.nextInt();
	    	int q = in.nextInt();
	    	
	    	barns[p].neighbors.add(barns[q]);
	    	barns[q].neighbors.add(barns[p]);

	    }
	    
	    int[] closings = new int[n];
	    
	    for(int i=0; i<n; i++) {
	    	closings[i] = in.nextInt();
	    }
	    
	    in.close();
	    
	    int first = 1;	// when i close it, i always search forward
	    for(int c = 0; c<n; c++) {
	    	
	    }   

	}
	
	//recursive graph search
	static int find(Barn b, Set<Barn> visited) {
		if(visited.contains(b)) {
			return 0;
		}
		// NEVER out of bounds
	    // RARELY do we check for other condition (some info held in node)
		
		visited.add(b);
		
		int count = 1;
		
		for(Barn b2: b.neighbors) {
			count+=find(b2, visited);
		}
		
		return count;
		
	}

	static class Barn{
		int id;
		boolean open;
		Set<Barn> neighbors = new HashSet();
		
		Barn(int i){
			id = i;
			open = true;
		}

	}
	
}


/*


Graphs
    a set of NODES
    connected by EDGES
    
    Weights?
        wont work with much yet, store values in edges
    
    Directional
        edges only go one way
    If Not
        add each other to each other's neighbor list
    
    Fully Linked Graph
        I can find a PATH from any one node to any other node

    Representation
        make a Class to represent a node
        keep a collection of edges in the node

http://www.usaco.org/index.php?page=viewproblem2&cpid=644

Graph
    Each barn -> Node
    Each path -> Edge

Use graph search on any node
        count how many places ive been
        if in the end ive been to N - (places that have been removed)
            im connected
    
    Graph Search
        1. should i not be here?
            have i visited before
        2. what do i do here
            mark visited
            SOMETHING (depends on problem)
        3. look at list of neightbors and keep searching


*/






