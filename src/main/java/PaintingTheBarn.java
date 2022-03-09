//USACO 2019 February Contest, Silver
//Problem 2. Painting the Barn

//not coded

public class PaintingTheBarn {

}

/*


Painting the Barn
http://www.usaco.org/index.php?page=viewproblem2&cpid=919
    
    
    keep track of the state of the side of the barn
        how many coats of paint are where
        
        2D array!
        
        Level 0: directly the coats of paint
        
            0 1 2 3 4 5 6 7 8 9 ... 1,000
          
        0   0 0 0 0 0 0 0 0 0 0 
        1   0 1 1 1 1 1 0 0 0 0 
        2   0 1 1 1 1 1 0 0 0 0 
        3   0 1 1 1 1 1 0 0 0 0 
        4   0 1 1 1 2 2 1 1 0 0 
        5   0 1 1 1 2 2 1 1 0 0 
        6   0 0 0 0 1 1 1 1 0 0 
        7   0 0 0 0 0 0 0 0 0 0 
        ...
        1000
        
        how much work will it be to fill in 1 square?
            1,000,000
        
        how many squares do i need to do?
            100,000
            
            100,000,000,000 not good!
        
        just look at the slice of row 4
        
        4   0  1  1  1  2  2  1  1  0  0
        
        4   0 +1  0  0 +1  0 -1  0 -1  0
        
        just keep track of the the change

        Level 1: change in coats of paint per row

            0  1  2  3  4  5  6  7  8  9 ... 1,000
          
        0   0  0  0  0  0  0  0  0  0  0 
        1   0 +1  0  0  0 -1  0  0  0  0 
        2   0 +1  0  0  0 -1  0  0  0  0 
        3   0 +1  0  0  0 -1  0  0  0  0 
        4   0 +1  0 +1  0 -1  0 -1  0  0 
        5   0 +1  0 +1  0 -1  0 -1  0  0 
        6   0  0  0 +1  0  0  0 -1  0  0 
        7   0  0  0  0  0  0  0  0  0  0 
        ...
        1000

        how much work will it be to fill in 1 square?
            2,000
        
        how many squares do i need to do?
            100,000
        
        all together ...
            100,000,000
        
        
        what do we do ...
        
        the same thing over again
        
        Level 2: change in cahnges in coats of paint per row per column

            0  1  2  3  4  5  6  7  8  9 ... 1,000
          
        0   0  0  0  0  0  0  0  0  0  0 
        1   0 +1  0  0  0 -1  0  0  0  0 
        2   0  0  0  0  0  0  0  0  0  0 
        3   0  0  0  0  0  0  0  0  0  0 
        4   0  0  0 +1  0  0  0 -1  0  0 
        5   0 -1  0  0  0 +1  0  0  0  0 
        6   0  0  0 -1  0  0  0 +1  0  0 
        7   0  0  0  0  0  0  0  0  0  0 
        ...
        1000


*/



