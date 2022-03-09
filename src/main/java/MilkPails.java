//USACO 2016 February Contest, Silver
//Problem 3. Milk Pails

//not coded

public class MilkPails {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}



/*

Milk Pail
http://www.usaco.org/index.php?page=viewproblem2&cpid=620
    
    
    I could go trhough every combination of every possible set of moves

    do by hand
        X  Y  K M
        14 50 3 32
            
        begining: 0,0
            fillX: 14,0
                fillX: 14,0
                fillY: 14, 50
                dumpX: 0,0
                dumpY: 14,0
                transXY: 0,14
                transYX: 14,0
            fillY: 0,50
                transYX: 14,36
            dump X: 0,0
                fillX: 14,0 // was this important?
            dump Y: 0,0
            trans XY: 0,0
            trans YX: 0,0
        
    interesting note:
        doing the same thing twice is never useful
    
    ACTIONS:
        fill X
        fill Y
        dump X
        dump Y
        dump X into Y
        dump Y into X



*/

