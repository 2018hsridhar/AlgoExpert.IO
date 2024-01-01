/*
Like EventTarget interface of the DOM -> track event listeners ; dispatch event
Callbacks for an event
Invoke all callback functions for a given event, if eventListeners exist

14 mins
*/
class EventTarget {
  // Write your code here.
  eventListenerMap = new Map()
  addEventListener(name, callback) {
    // NOP : add exact event listener ( combon of event callback ) 
    // Can have same event, different functions though
    if(!this.eventListenerMap.has(name)){
      this.eventListenerMap.set(name,new Set())
    }
    // JS really lets you have undefined variables `eventListener`
    // console.log(this.eventListenerMap)
    this.eventListenerMap.get(name).add(callback)
  }

  removeEventListener(name, callback) {
    if(this.eventListenerMap.has(name)){
      this.eventListenerMap.get(name).delete(callback)
    }
  }

  dispatchEvent(name) {
    // if(name in this.eventListenerMap){
    if(this.eventListenerMap.has(name)){
      // console.log("Dispatching \t " + name)
      let myCallbacks = this.eventListenerMap.get(name)
      // a.forEach(key => console.log(key))
      myCallbacks.forEach(callback => {
        callback()
      })
    }
  }
}

// Do not edit the line below.
exports.EventTarget = EventTarget;
