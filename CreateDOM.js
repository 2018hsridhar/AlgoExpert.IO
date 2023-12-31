/*
root param := obj rep ( DOM tree rot node ) or string rep ( txt node ) 
  if(isobj)
    ret ElemNode

  child is a string -> string to use a textual contnent for the obj
  https://developer.mozilla.org/en-US/docs/Web/API/Element
  
*/
function createDom(root) {
  return helperDOM(root)
}

function isObject(val) {
  return (val !== null && typeof val === 'object' )
}

function isString(val){
  return (val !== null && typeof val === 'string')
}

function helperDOM(root){
  if(isObject(root)){
    // console.log("Is object")
    var type = root.type
    var newElemNode = document.createElement(type);
    // console.log(newElemNode)
    // Type required ( is tag name ) 
    // console.log("Here1")    
    // Null check no work -> key in object/not in object works
    if("attributes" in root) {
      var attrs = root.attributes
      for (const [attrKey, attrVal] of Object.entries(attrs)) {
        newElemNode.setAttribute(attrKey,attrVal)
  // console.log(`${key} ${value}`); // "a 5", "b 7", "c 9"
      }
    }
    // console.log("Here2")    
    // Is in array of objects as root param
    if("children" in root) {
      var children = root.children
      children.forEach((child) => {
        let childDOMNode = helperDOM(child)
        if(isString(childDOMNode)) {
          // use as text
          // innerHTML more accurate than innerText
          // https://www.skillsugar.com/how-to-set-text-of-an-element-with-javascript#:~:text=Using%20the%20innerText%20Property%20If%20you%20have%20plain,id%3D%22foo%22%3E%3C%2Fdiv%3E%20document.getElementById%28%27foo%27%29.innerText%20%3D%20%27Some%20text.%27%3B%20%3Cdiv%20id%3D%22foo%22%3ESome%20text.%3C%2Fdiv%3E
          newElemNode.innerHTML = childDOMNode
        } else {
          // extend cur elem node children
          newElemNode.append(childDOMNode) // can append strings and objects
        }
      })
    }
    // console.log("Here3")    
    // console.log(newElemNode)
    return newElemNode
  } else if ( isString(root)) {
    // Delineate textNode from elementNodes in DOM Node creation
    return document.createTextNode(root)
    // return root // it's a string -> just return as is?
  } 
  return null // should NOT encounter
}

// Do not edit the line below.
exports.createDom = createDom;
