//USACO 2016 US Open Contest, Silver
//Problem 1. Field Reduction

//case 9 incorrect, all others correct

import java.util.*;
import java.io.*;

public class FieldReduction {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("reduce.in"));
		// TODO: read input
		int n = in.nextInt();

		Cow[] sortedHoriz = new Cow[n];
		Cow[] sortedVert = new Cow[n];

		for(int i=0; i<n; i++) {
			Cow c = new Cow (in.nextInt(), in.nextInt());
			sortedHoriz[i]=c;
			sortedVert[i]=c;
		}

		in.close();

		Arrays.sort(sortedHoriz, new HComparator());
		Arrays.sort(sortedVert, new VComparator());

		int result = Integer.MAX_VALUE;
		// TODO: try removing some cows to get a smaller field
		
		Cow[] vertices = new Cow[16];

		if(n>16) {
			for(int i=0; i<4; i++) {
				vertices[4*i]=sortedHoriz[i];
				vertices[4*i+1]=sortedHoriz[n-i-1];
				vertices[4*i+2]=sortedVert[i];
				vertices[4*i+3]=sortedVert[n-i-1];
			}

		}else {
			vertices = sortedHoriz;
		}

		
		// a,b,c,d represent possible bounds of rectangle 
		for(int a=0; a<4; a++) {
			for(int b=0; b<4; b++) {
				for(int c=0; c<4; c++) {
					for(int d=0; d<4; d++) {
						Cow aCow = sortedHoriz[a];
						Cow bCow = sortedHoriz[n-b-1];
						Cow cCow = sortedVert[c];
						Cow dCow = sortedVert[n-d-1];
						int minX = Math.min(Math.min(aCow.x, bCow.x),Math.min(cCow.x, dCow.x));
						int minY = Math.min(Math.min(aCow.y, bCow.y),Math.min(cCow.y, dCow.y));
						int maxX = Math.max(Math.max(aCow.x, bCow.x),Math.max(cCow.x, dCow.x));
						int maxY = Math.max(Math.max(aCow.y, bCow.y),Math.max(cCow.y, dCow.y));
						
						if((maxX-minX)*(maxY-minY)<result) {
							if(works(minX, minY, maxX, maxY, vertices)) {
								if((maxX-minX)*(maxY-minY)<result)	
									result = ((maxX-minX)*(maxY-minY));
							}
						}
						
					}
				}
			}
		}


		PrintWriter out = new PrintWriter(new File("reduce.out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}

	public static boolean works(int minX, int minY, int maxX, int maxY, Cow[] cows) {
		int count = 0;
		for(int i=0; i<cows.length; i++) {
			Cow c = cows[i];
			if(c.x<minX || c.x>maxX || c.y<minY || c.y>maxY) {
				count++;
			}
		}
		if(count>3)	return false;
		return true;
	}

	static class HComparator implements Comparator<Cow> {
		public int compare(Cow a, Cow b) {
			// TODO: compare horizontally!
			return a.x-b.x;
		}
	}

	static class VComparator implements Comparator<Cow> {
		public int compare(Cow a, Cow b) {
			// TODO: compare vertically!
			return a.y-b.y;
		}
	}

	static class Cow {
		// TODO: complete
		int x, y;

		Cow(int x, int y){
			this.x=x;
			this.y=y;
		}

	}
}

/*

Complete a solution to this problem using sorting to find the most extreme values
 in either the x or y directions. This narrows down which cows could result in a 
 field size reduction. It also makes it easy to find what the new field borders 
 would be with certain cows removed. You will still have to work through some complex 
 combinations of cows! Additionally, you have to consider how to handle when cows are 
 tied in a particular direction, or when both of a cow's coordinates are extreme.


*/


/*

KTBYTE SOLUTION

// "Field Reduction" - Silver level
// http://www.usaco.org/index.php?page=viewproblem2&cpid=642
import java.util.*;
import java.io.*;

public class reduce {
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("reduce.in"));
    
    int n = in.nextInt();
    Cow[] sortedHoriz = new Cow[n];
    
    for (int i = 0; i < n; i++) {
      sortedHoriz[i] = new Cow(in);
    }
    in.close();
    Cow[] sortedVert = Arrays.copyOf(sortedHoriz, n);
    
    Arrays.sort(sortedHoriz, new HComparator());
    Arrays.sort(sortedVert, new VComparator());
    
    // baseline result is the original area
    int result = (sortedHoriz[n-1].x - sortedHoriz[0].x) *
                 (sortedVert[n-1].y  - sortedVert[0].y);
    
    // most extreme 3 cows in each direction could be sold
    List<Cow> extremes = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      extremes.add(sortedHoriz[i]);
      extremes.add(sortedHoriz[n-1-i]);
      extremes.add(sortedVert[i]);
      extremes.add(sortedVert[n-1-i]);
    }
    
    
    
    
    
    for (int i = 0; i < extremes.size(); i++) {
      // "sell" cow i
      extremes.get(i).sold = true;
      
      // order of sales is irrelevant so we can say i<j<k, no need
      //   to try other orderings
      for (int j = i+1; j < extremes.size(); j++) {
        extremes.get(j).sold = true;
        
        for (int k = j+1; k < extremes.size(); k++) {
          extremes.get(k).sold = true;
          
          // find new extremes by finding first and last unsold cows
          int p = 0;
          while (sortedHoriz[p].sold) p++;
          int xMin = sortedHoriz[p].x;
          p = n-1;
          while (sortedHoriz[p].sold) p--;
          int xMax = sortedHoriz[p].x;
          
          p = 0;
          while (sortedVert[p].sold) p++;
          int yMin = sortedVert[p].y;
          p = n-1;
          while (sortedVert[p].sold) p--;
          int yMax = sortedVert[p].y;
          
          int area = (xMax-xMin)*(yMax-yMin);
          result = Math.min(result, area);
          
          extremes.get(k).sold = false;
        }
        
        extremes.get(j).sold = false;
      }
      
      // "buy back" cow i so we can try selling a different one
      extremes.get(i).sold = false;
    }
    
    
    PrintWriter out = new PrintWriter(new File("reduce.out"));
    System.out.println(result);
    out.println(result);
    out.close();
  }
  
  
  static class HComparator implements Comparator<Cow> {
    public int compare(Cow a, Cow b) {
      return a.x - b.x;
      // ex: a.x = 5, b.x = 9, a should be sorted before b
      //     5-9 = -4, a negative number
      //     therefore a is considered smaller than b
    }
  }
  
  static class VComparator implements Comparator<Cow> {
    public int compare(Cow a, Cow b) {
      return a.y - b.y;
    }
  }
  
  static class Cow {
    int x, y;
    boolean sold;
    
    Cow(Scanner in) {
      x = in.nextInt();
      y = in.nextInt();
    }
    
    // doesn't need to be Comparable -
    //   there is no "natural ordering"
    
    // alternative: have two Cow classes, each
    //   naturally ordered a different way, make
    //   each one able to "clone" itself into
    //   the other format
  }
}

*/