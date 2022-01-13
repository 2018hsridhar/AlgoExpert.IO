Sample Input
array = [8, 5, 2, 9, 5, 6, 3]
Sample Output
[2, 3, 5, 5, 6, 8, 9]


Takes in arr -> returns sorted version of array
Review of quicksort algo : in-place
Recursive, Divide-and-Conquer, ( Iterative? )
Base case : single element
Pivot -> first/last/middle element
Parition : ( <=, > the pivot point )

Shift operations : O(N)
Count out-of-order elements?
	       i      j
	 P
	[3,2,1,4,....]
	[2,3,1,4,6,8,7]
	     ^	   ^
         P1    P2			( both ptrs or a single pointer exhausts exploration )
	   
           i       j
	[1,3,2,4,8,6,6,7]
	 P
	 A[j]>pivot -> j++
	 A[j]<=pivot -> i++; swap(A, i, j); j++
	 end
	 swap(A, l, i)
	 return i
	   
	desired output : 
	[3,1,2,4,6,8.7] => desired output
	

		   !	
    [1,6,3,4,2,8,7]
    [1,3,2,4,6,8,7] written
     (   )  (     )
	 
	[1,6,3,4,2,8,7]
	[1,2,3,4,6,8,7]
	   ^		 ^
	 P1			 P2
	   (P1/P2)
In-place : swap operations and cmp operations ?

	 
PSEUDOCODE : 

	quicksort(A,0,len(A) - 1)
	
	// Involve auxillary space
    def quicksort(A, low, high):
		if(low >= high)
			return
		else
		{
			// Pivot selection : optimal desired?
			sz = high-low+1
			//balance(A,low,high)	// investigate
			pivotIndex = partition(A,low,high)
			quickSort(A,low,pivotIndex)
			quickSort(A,pivotIndex,high)
		}
		
	def partition(A,low,high):
		pivot = A[low]
		i = low
		for(j in range(low + 1,high,1))
			if(A[j] < pivot)
				i++
				swp(A,i,j)
		swp(A,low,i)
		pivotIndex = i+1
		return pivotIndex
			
	def swp(A,i,j)
		temp = A[i]
		A[i] = A[j]
		A[j] = temp
		
		
		
    def balance_space(A,low,high):
		mid = low + (high-low)/2
		pivot = A[mid]
		B[sz]
		wIdx = 0
		// Two pass approach?
		for i in range(low,high,1):
			if(A[i] < pivot)
				B[wIdx++] = A[i]
		A[wIdx++] = pivot
		for i in range(low,high,1):
			if(A[i] > pivot)
				B[wIdx++] = A[i]
		for(i in range(0,len(B),1)):
			A[low+i] = B[i]


Clarification
- Integer values ( can be negative ) 
- Fits in RAM
- 

Complexity :
Let N := len(array)

Best / Average Case
TIME = O(N*lgN)
SPACE = O(lgN)

True worst case : 
	=> good pivot selection strategy?
TIME = O(N*N) = O(N^2) : partition subroutine invoked each helper func call
SPACE = O(N) ( call stack ) O(1) ( auxillary ) 


pivot on the boundary of the array each step

Test Cases :
(A) [1]
(B) [1,2]
(C) [1,2,3,4,5]
(D) [1,2,3,4,5,6]		<-
(E) [5,4,3,2,1]			<-
(F) [-1,6,3,9,8,-7,3,10]


	[4,2,1,3,6,5,7]
    [(2,1,3),4,(6,5,7)]
	[(1),2,(3),4,(5),6,(7)]
	


import java.util.*;

class Program {
  public static int[] quickSort(int[] array) 
  {
	helper(array,0,array.length - 1);
  	return array;
  }
	
	private static void helper(int[] A, int low, int high)
	{
		if(low < high)
		{
			int pivotIndex = partition(A,low,high);
			helper(A,low,pivotIndex-1);
			helper(A,pivotIndex+1,high);
		}
	}
	
	private static int partition(int[] A,int low,int high)
	{
		int pivot = A[low];	// Math.rand(low,high)
		int i = low;
		for(int j = low + 1; j <= high; ++j)
		{
			if(A[j] < pivot)
			{
				i++;
				swp(A,i,j);
			}
		}
		swp(A,low,i);
		int pivotIndex = i;
		return pivotIndex;
	}
	
	private static void swp(int[] A,int i,int j)
	{	
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}			
}
