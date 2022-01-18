Valid Starting City
Imagine you have a set of cities that are laid out in a circle, connected
by a circular road that runs clockwise. Each city has a gas station that 
provides gallons of fuel, and each city is some distance away from the 
next city.

You have a car that can drive some number of miles per gallon of fuel, 
and your goal is to pick a starting city such that you can fill up your 
car with that city's fuel, drive to the next city, refill up your car 
with that city's fuel, drive to the next city, and so on and so forth 
until you return back to the starting city with 0 or more gallons of fuel 
left.

This city is called a valid starting city, and it's guaranteed that there
will always be exactly one valid starting city.

For the actual problem, you'll be given an array of distances such that 
city i is distances[i] away from city i + 1. Since the cities are connected
via a circular road, the last city is connected to the first city. In other
words, the last distance in the distances array is equal to the distance 
from the last city to the first city. You'll also be given an array of 
fuel available at each city, where fuel[i] is equal to the fuel available 
at city i. The total amount of fuel available (from all cities combined)
is exactly enough to travel to all cities. Your fuel tank always starts out 
empty, and you're given a positive integer value for the number of miles 
that your car can travel per gallon of fuel (miles per gallon, or MPG). 
You can assume that you will always be given at least two cities.

Write a function that returns the index of the valid starting city.

Sample Input
cities	  = [0, 1,  2,   3,  4]
distances = [5, 25, 15, 10, 15]
fuel = 		[1,  2,  1,  0,  3]
			
		                     ^
			                (s,f)
					
F = S+1
mpg = 10
Sample Output
4

Move back the "start" pointer until we get another valid start


3*10 = 30 
-15 = 15
15+10 = 25 - 5 = 20
20+2*10 = 40 - 25 = 15
15+10 = 25 - 15 = 10
10-10 = 0
back at index = 4 with 0 gallons remaining

3*10 = 30-15 = 15


At index = 0
Starting index = 0
Current index = ____

1*10 = 10 miles
10-5 = 5
2*10+5 = 25 - 25 = 0
1*10 = 10 - 15 = -5 XX

10-10 = 0


5 gallons needed

Pseudocode : 
	
	1. Find a potential candidate for a valid start city
	2. Increment buffer window until pointers meeting again
	
	
def validStartingCity(distance,fuel,mpg):	
	n = len(distance)
	s = getStartCity(distances,fuel,mpg)
	f = ( s + 1 ) % n
	fuelRemain = (mpg * fuel[s]) - distances[s]
	while(s != f):
		fuelRemain = fuelRemain + (mpg * fuel[f]) - distances[f]
		f += 1
		while fuelRemain < 0:
			s = (s-1 ) % n
			fuelRemain = fuelRemain + (mpg * fuel[s]) - distance[s]
		# fuel remaing >= 0 : s changed to position	
	validCity = s
	return validCity
	
def getStartCity(distances,fuel,mpg):
	n = len(distances)
	s = 0
	for i in range(n):
		if mpg * fuel[i] >= distances[i]
			s = i
			break
	return s

Complexity
Let N := #-stops
TIME = O(N) + O(N) = O(N)
SPACE = O(1)


Clariifcations
1. Any case always has a valid starting city
2. Any case always has at least two cities
3. Can return to valid start city : >= 0 gallons remaining 


Test Cases :
(A) 	[5,5,5,5,5]
	fuel = [0,4,0,0,1]
	mpg = 5
	=> 1
(B) cities	  = [0, 1,  2,   3,  4]
distances = [5, 25, 15, 10, 15]
fuel = 		[0,  7,  0,  0,  0]
(D)





def validStartingCity(distance,fuel,mpg):	
	return getValidCity(distance,fuel,mpg)
	
def getValidCity(distance,fuel,mpg):
	n = len(distance)
	s = getStartCity(distance,fuel,mpg)
	f = ( s + 1 ) % n
	fuelRemain = (mpg * fuel[s]) - distance[s]
	while(s != f):
		fuelRemain = fuelRemain + (mpg * fuel[f]) - distance[f]
		f = (f + 1 ) % n
		while fuelRemain < 0 :
			s = (s-1 ) % n
			fuelRemain = fuelRemain + (mpg * fuel[s]) - distance[s]
			# fuel remaing >= 0 : s changed to position	
	validCity = s
	return validCity
# your voice is breaking
def getStartCity(distance,fuel,mpg):
	n = len(distance)
	s = 0
	for i in range(n):
		if (mpg * fuel[i]) >= distance[i]:
			s = i
			break
	return s



https://leetcode.com/problems/gas-station/
