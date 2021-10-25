/*
Array of non-neg integers
Returns the sorted version of that array

Based on the radixes of each value as well
Is it a comparative pairwise sorting algorithm? -> no 
Does it preserve order of elemenets as encountered -> yes

Linked List in the input ( for binning -> akin to a sharding/hash strategy )
#-radixes is guaranteed to be a fixed number [ 0-9 ]
Binned originally by their set of predecing digits as well

Complexity : 
Let N := #-elements in the input array
Let K := length(longest input)
Radix :=> key in the has ( known to be a constant -> 9 : M )
Time = O(NK)
Space = O(MN) = O(9N) = O(N) 

-> Let us vary the radices @ the end
TEST BENCH : 
(A) [1,5]
(B) [1,5,12,23]
(C) [0,1,12,23,344,455]
(D) [12,23]
(E) [344,455]
(F) [], NULL
(G) [9876], [1]
[ * PROVIDED * ] (H) [8762, 654, 3008, 345, 87, 65, 234, 12, 2]
(I)
(J)


*/
import java.util.*;

class Program {

  public ArrayList<Integer> radixSort(ArrayList<Integer> array) {
    // Write your code here.
    return null;
  }
}
