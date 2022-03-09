//USACO 2021 US Open, Silver
//Problem 3. Acowdemia

//15/20 correct

import java.util.*;

public class Acowdemia {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
	    int n = in.nextInt();
	    int k = in.nextInt();
	    int l = in.nextInt();
	    
	    int[] citations = new int[n];
	    for(int i=0; i<n; i++) {
	    	citations[i] = in.nextInt();
	    }
	    Arrays.sort(citations);
	    
	    int ans = 0;
	    
	    //int h = result(citations,n);
	    
	    int lbound = 0;
	    int rbound = citations.length-1;
	    
	    
	    //total number of citations to add
	    int cites = k*l;
	    
	    boolean done = false;
	    
//	    while(!done && h<=n) {
//	    	cites = k*l;
//	    	int kcount = 0;
//	    	int lcount = 0;
//	    	for(int i=0; i<h; i++) {
//	    		int cite = citations[citations.length-i-1];
//	    		if(cite<h) {
//	    			if(h-cite>k) {
//	    				done = true;
//	    				break;
//	    			}else if (cites>=h-cite){
//	    				cites-= (h-cite);
//	    				lcount+= (h-cite);
//	    			}else if(cites-(h-cite)<0) {
//	    				done = true;
//	    				break;
//	    			}
//	    		}
//	    	}
//	    	if(!done)	ans=h;
//	    	
//	    	h++;
//	    }
	    int h = lbound+rbound/2;
	    while(rbound>lbound) {
	    	h = (lbound+rbound)/2;
	    	if(rbound == lbound+1) {
	    		h++;
	    		if(works(cites, citations, ans, h, k, l, done)) {
		    		ans = h;
		    	}
	    		break;
	    	}
	    	if(works(cites, citations, ans, h, k, l, done)) {
	    		lbound = h;
	    		ans = h;
	    	}else {
	    		rbound = h-1;
	    	}

	    }

	    System.out.println(ans);
	}
	
	public static boolean works(int cites, int[] citations, int ans, int h, int k, int l, boolean done) {
		cites = k*l;

    	for(int i=0; i<h; i++) {
    		int cite = citations[citations.length-i-1];
    		if(cite<h) {
    			if(h-cite>k) {
    				done = true;
    				break;
    			}else if (cites>=h-cite){
    				cites-= (h-cite);
    			}else if(cites-(h-cite)<0) {
    				done = true;
    				break;
    			}
    		}
    	}
    	
    	return !done;	//true of works, false if not works
	}
	
	public static int result(int[] citations, int n) {
		
		int h = Integer.MAX_VALUE;
		
		for(int i=0; i<citations.length; i++) {
	    	if(citations[i]<h)		h = citations[i];
	    }
	    
	    if(h>n) {
	    	h=n;
	    }
	    
	    return h;
		
	}

}

/*


4 4 1
1 100 1 1

4 1 4
1 100 1 1

4 4 2
1 100 1 1

4 0 1
1 100 1 1


*/


