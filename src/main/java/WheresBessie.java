//USACO 2017 US Open Contest, Silver
//Problem 3. Where's Bessie?

//not coded

public class WheresBessie {

}

/*

Where's Bessie
http://www.usaco.org/index.php?page=viewproblem2&cpid=740

Original Image:

AAAAAA
ABABAA  
AAABBA
CCBAAA


AAAAA
ABABA   <- is a PCL
AAABB


A is the 1 big region
B would be the one with multiple region


AAAA
ABAA
ABBA
BAAA


A
B    <- not a PCL
A

B is the 1 region
A is in multiple
IS contained within a bigger PCL X



How to I find how many PCLs there are
    Brute Force: check all possible rectangles
        original image is NxN
        how many rectangles?

        starting R, ending R, starting C, ending C
             N         N          N          N

        N*N*N*N _> 160000

    OK, we have a way to look at each rectangle, how do we find out if it's a PCL or not?
        floodfill!!!
        N*N
        calc portential PCLs

        Look back to previous to see if this is a subset
            N



 v  v
AAAAAA
ABABAA  
AAABBA<
CCBAAA
DDDDDD      AABB
DDDDDD<     CBAA
            DDDD
            DDDD

Solution
http://www.usaco.org/current/data/sol_where_silver_open17.html



 */






