//USACO 2013 December Contest, Bronze
//Problem 3. Wormholes

import java.util.*;
import java.io.*;
 
public class Wormholes {
  public static void main(String[] args) throws FileNotFoundException {
    Scanner in = new Scanner(new File("wormholes.in"));
    int n = in.nextInt();
    
    Wormhole[] wormholes = new Wormhole[n];
    for(int i=0; i<n; i++) {
    	wormholes[i]=new Wormhole(in);
    }
    in.close();
    
    for(int i=0; i<n; i++) {
    	wormholes[i].setToRight(wormholes);
    }

    int result = countCombos(wormholes,0);

    PrintWriter out = new PrintWriter(new File("wormholes.out"));
    System.out.println(result);
    out.println(result);
    out.close();
  }
  
  static int countCombos(Wormhole[] whs, int next) {
	  
	  if(next >= whs.length) {
		  return simulate(whs) ? 1 : 0;
	  }
	  
	  // RULE: start with pairing the smalles available wormhole
	  if(whs[next].linked != null){
		  return countCombos(whs,next+1);
	  }
	  
	  int sum=0;
	  for(int other=next+1; other<whs.length; other++) {
		  if(whs[other].linked != null) {
			  continue;
		  }
		  whs[other].link(whs[next]);
		  
		  sum+=countCombos(whs, next+1);
		  
		  whs[next].unlink();
	  }
	  
	  return sum;
	  
  }
  
  static boolean simulate(Wormhole[] whs) {
	  
	  for(int start = 0; start<whs.length; start++) {
		  Wormhole whStart = whs[start];
		  Wormhole current = whStart.linked.toRight;
		  
		  while(current != null) {
			  if(current == whStart)	return true;
			  current = current.linked.toRight;
		  }
		  
	  }
	  
	  return false;
	  
  }
  
  static class Wormhole{
	  int x,y;  
	  Wormhole toRight;
	  Wormhole linked;
	  
	  Wormhole(Scanner in){
		  x = in.nextInt();
		  y = in.nextInt();
	  }
	  
	  void link(Wormhole other) {
		  this.linked = other;
		  other.linked = this;
	  }
	  
	  void unlink() {
		  linked.linked = null;
		  this.linked = null;
	  }
	  
	  void setToRight(Wormhole[] all){
          for(Wormhole wh: all){
              if(wh.y == y && wh.x > x){
                  if(toRight == null || wh.x < toRight.x) {
                	  toRight = wh;
                  }
              }
          }
      }
	  
  }
 
 
}



/*
Wormhole
http://www.usaco.org/index.php?page=viewproblem2&cpid=360

    Brute Force
    
    
    
    Try everything: Combinatorics
    
    Simulation: how?
        if i know a specific pairing of wormholes, i can simulate different places where Bessie could start and if any of those would infinite loop

    static boolean(some pairing of wormholes){
        
        //assume we have this
        
        return (magically) if there is possible loop
    }
    


   A       B



   B  *    A



    Find every combination of 2 wormholes
        1 - 1 makes no sense
        1 - 2 is smae as 2 - 1 (not repeat)
    
        
        
         pairs       pairs
        12 * 11 *   10  * 9  * 8  * 7 * 6 * 5

        1    2
        1    3
        ...
        2    1
        2    3
        
        
        11 * 9 * 7 * 3 * 1
        
        1 needs to be paired with someone
        
        1 2         3* 4
        1 3         2 * 4
        1 4
        
        
How to check all combinations
    first pick any wormhole, then pick any other worm, pair these
        then pick any wormhole left, another, pair these
        
        12 * 11 * 10 * 9 * 8 * 7 * 6 * 5 ...
        !12
        
        where is this ineficient?
            
        NEW RULE: 1 is always the first slot?
        
        NEW RULE: the smallest number that hasnt been paired is always the 3rd slot
                                
        Example: 4 wormhole ->  3 * 1
        1-2 3-4
        1-3 2-4
        1-4 2-3
        
        
        
        NEW RULE: the smallest number that hasnt been paired is always the first of a new pair
        
        11 * 9 * 7 * 5 * 3 * 1
        
        1-2  3
        1-3
        1-4
        1-5 
        ...
    
        

*/





