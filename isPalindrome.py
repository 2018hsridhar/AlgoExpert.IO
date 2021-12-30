# Time Complexity = O(N)
# Space Complexity = O(1)
def isPalindrome(string):
	isPalin = True
	ptr1 = 0
	ptr2 = len(string) - 1
	# print(len(string))
	while(ptr1 <= ptr2):
		# print(string[ptr1], string[ptr2]) # Can pass in multiple args into a print statement
		if(string[ptr1] != string[ptr2]):
			isPalin = False
			break
		ptr1 += 1
		ptr2 -= 1
	return isPalin
