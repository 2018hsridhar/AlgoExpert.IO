'''
Complexity
Let N := #-nodes in the tree
Let H := height of the tree ( log_2(N) balanced, (N) worst ) 
Time = O(N)
Space = O(H) ( imp ) O(N) ( exp ) 

'''
def inOrderTraverse(tree, array):
	if ( tree is None ):
    	return
	if ( tree.left is not None ):
		inOrderTraverse(tree.left,array)
	array.append(tree.value)
	if ( tree.right is not None ):
		inOrderTraverse(tree.right, array)
	return array

def preOrderTraverse(tree, array):
	if ( tree is None ) :
		return
	array.append(tree.value)
	if ( tree.left is not None ) :
		preOrderTraverse(tree.left, array)
	if ( tree.right is not None ) :
		preOrderTraverse(tree.right, array)
	return array


def postOrderTraverse(tree, array):
	if ( tree is None ) :
		return
	if ( tree.left is not None ) :
		postOrderTraverse(tree.left, array)
	if ( tree.right is not None ) :
		postOrderTraverse(tree.right, array)
	array.append(tree.value)
	return array
