/*
3 DOM elements : 3rd param is a node in tree1
Trees are isomorphic
T2(Y) = T1(node1) : find that Y
Always have a corresponding node
Recursive exploration of DOM needed
How to tell DOM elements isomorphic

Element Nodes and Text Nodes
<!DOCTYPE html> directive part of DOM too

15 mins -> good timing
huh DOM nodes have parentNode property -> iterate up too?

*/
function correspondingNode(tree1, tree2, node1) {
  let resNode = dfs(tree1,tree2,node1)
  return resNode
}

function dfs(tree1,tree2,node1) {
  var resNodeTwo = null
  if(tree1 === null || tree2 === null) {
    return null
  } else if(tree1 === node1) {
    resNodeTwo = tree2
  } else if ( tree1 !== node1) {
    // https://developer.mozilla.org/en-US/docs/Web/API/Element/children
    let childrenOne = tree1.children
    let childrenTwo = tree2.children
    let n = childrenOne.length
    for(let i = 0; i < n; i++){
      let child1 = childrenOne[i]
      let child2 = childrenTwo[i]
      let childRes = dfs(child1,child2,node1)
      if(childRes !== null) {
        resNodeTwo = childRes
        break
      }
    }
  }
  return resNodeTwo
}

// Do not edit the line below.
exports.correspondingNode = correspondingNode;
