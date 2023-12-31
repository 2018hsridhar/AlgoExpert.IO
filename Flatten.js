/*
Input : A random value
Output : A flattened version of said value

1. Primitives left unchanged
2. Nested arrs -> vals to topLevel arr
3. Nested Objects -> key removal
  key collision : use ANY associated value ( just leave as-is )
4. 
5.

No value is a function :-)
Object key type. Here, all strings.

*/

function isObject(val) {
  return (val !== null && Array.isArray(val) === false && typeof val === 'object')  
}

function isArray(val){
  return (val !== null && Array.isArray(val) === true)  
}

// Very recursive function here
// Ret new val -> inPlace modify later
// toggle the behavior based on what currentValue is too!
// JS powerful for handling multiple param types

function isEmptyObj(obj) {
    return Object.keys(obj).length === 0;
}

function isEmptyArr(arr){
  return arr.length === 0;
}

function flatten(value) {
  let myChildObj = {}
  let myChildArr = []
  // console.log(isObject(value))
  // console.log(isArray(value))
  if(!isObject(value) && !isArray(value)){
    return value // base case of being a primtive here
  } else if ( isObject(value) && !isArray(value)) { // ret obj
    let myNewObj = {}
    if(isEmptyObj(value)){
      return {}
    }
    Object.entries(value).forEach(([objKey, objVal]) => {
        if(isObject(objVal)) {
          myChildObj = flatten(objVal)
          // console.log("For key = " + objKey + "\t childObj = " + JSON.stringify(myChildObj))
          // GAAAH array methods here
            Object.entries(myChildObj).forEach(([childKey,childVal]) => {
              // Object.defineProperty(myNewObj,childKey,childVal)
              myNewObj[childKey] = childVal
            })
         // no myNewObj.set(key,value) here
        } else if ( isArray(objVal)) {
          myChildArr = flatten(objVal)
          myNewObj[objKey] = myChildArr
          // Object.defineProperty(myNewObj,objKey,myChildArr)
        } else { // valeu is a primtiive : leave as is
          // Object.defineProperty(myNewObj,objKey,objVal)
          myNewObj[objKey] = objVal
        }
    })
    return myNewObj
  } else if ( !isObject(value) && isArray(value)) { // ret arr
    // console.log("In isArray")
    let myNewArr = []
    value.forEach((element) => {
        if(isObject(element)){
          myChildObj = flatten(element)
          myNewArr.push(myChildObj)
        } else if ( isArray(element)) {
          // console.log("Hit is array")
          myChildArr = flatten(element)
          myChildArr.forEach((childEl) => {
            myNewArr.push(childEl)
          })
        } else { // have a primitive
          myNewArr.push(element)
        }
    })
    return myNewArr
  }
  return null // not to hit this case
}

// Do not edit the line below.
exports.flatten = flatten;
