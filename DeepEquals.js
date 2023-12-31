/*
Deep Equals problem
1. NaN === NaN
2. null === null
3. undefined === undefined
4. arrays equal if they deep equal
5. objects -> order botht heir keys on the test -> test deep equality
- no functions passed
- keys(obj) string only
- no consider prototype chain

Recursive based method

*/

// typeof yourVariable === 'object'
function isObject(val) {
  return (val !== null && Array.isArray(val) === false && typeof val === 'object' && typeof val !== 'string');
}

function isArray(val) {
  return (val !== null && Array.isArray(val) === true);
}

function deepEquals(valueOne, valueTwo) {
  let isDeepEqual = true
      // how to get the direct type of value though
    let typeOne = typeof valueOne
    let typeTwo = typeof valueTwo
    // This code threw an error with the symbol primitive
    // Your debug code caused the issue. Like ... wow!!
    // console.log("TypeOne = " + typeOne + "\t ValueOne = " + valueOne)
    // console.log("TypeTwo = " + typeTwo + "\t ValueTwo = " + valueTwo)

  if(isArray(valueOne)) {
    // Lengths must be matching here
    if (!isArray(valueTwo) || isArray(valueTwo) && valueTwo.length !== valueOne.length) {
      isDeepEqual = false
    } else {
      valueOne.forEach((val1,idx) => {
        let val2 = valueTwo[idx]
        if(!deepEquals(val1,val2)){
          isDeepEqual = false
        }
      })
    }
  } else if ( isObject(valueOne)) {
    if(!isObject(valueTwo)) {
        isDeepEqual = false
    } else {
      // Ensure same key set : if null, it should break at least
      // is null an object?
      var keysOne = Object.keys(valueOne)
      var keysTwo = Object.keys(valueTwo)
      if(keysOne.length !== keysTwo.length) {
        isDeepEqual = false
        return isDeepEqual
      }
      keysOne.forEach((keyOne) => {
        if(!keyOne in valueTwo ){
            isDeepEqual = false
        } else {
          if(!deepEquals(valueOne[keyOne], valueTwo[keyOne])){
            isDeepEqual = false
          }
        }     
      });
      keysTwo.forEach((keyTwo) => {
        if(!keyTwo in valueOne ){
            isDeepEqual = false
        } else {
          if(!deepEquals(valueOne[keyTwo], valueTwo[keyTwo])){
            isDeepEqual = false
          }
        }     
      });
    }
  } else {
    // Helper func set up
    // NaN Checking
    // Refactor to a seperate method later?
    // Strings are technically NaNs -> we want only NaN == Nan going on
    if(Number.isNaN(valueOne)) {
      if(!Number.isNaN(valueTwo)) {
          isDeepEqual = false
      } else {
        isDeepEqual = true
      }
      return isDeepEqual
    } else if ( Number.isNaN(valueTwo)) {
      if(!Number.isNaN(valueOne)) {
          isDeepEqual = false
      } else {
        isDeepEqual = true
      }
      return isDeepEqual
    }
    
    // if (variable === undefined || variable === null) {
    // Check undefined first before checking null
    // Undefined checks
    if(typeOne === 'undefined' ){
      if(typeTwo !== 'undefined') {
          isDeepEqual = false
      } else {
        isDeepEqual = true
      }
      return isDeepEqual
    } else if ( typeTwo === 'undefined') {
      if(typeOne !== 'undefined') {
          isDeepEqual = false
      } else {
        isDeepEqual = true
      }
      return isDeepEqual
    }

    // null checks
        // Undefined checks
    if(typeOne === null ){
      if(typeTwo !== null) {
          isDeepEqual = false
      } else {
        isDeepEqual = true
      }
      return isDeepEqual
    } else if ( typeTwo === null) {
      if(typeOne !== null) {
          isDeepEqual = false
      } else {
        isDeepEqual = true
      }
      return isDeepEqual
    }
    // @ end after FALSY checks
    if(typeOne !== typeTwo) {
      isDeepEqual = false
    } else {
      isDeepEqual = (valueOne === valueTwo)
    }
  }
  return isDeepEqual
}

// Do not edit the line below.
exports.deepEquals = deepEquals;
