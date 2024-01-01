const SYMBOLS_API_BASE_URL = 'https://api.frontendexpert.io/api/fe/stock-symbols';
const MARKET_CAPS_API_BASE_URL = 'https://api.frontendexpert.io/api/fe/stock-market-caps';
const PRICES_API_BASE_URL = 'https://api.frontendexpert.io/api/fe/stock-prices';

/*
Async function to grab trending U.S. stocks by market cap
500 top US-based stocks
Symbols consistent : 

Given n -> return the top n US-based stocks by market cap
n objects returned with 6 properties each

Minimize daisy-chained fetch requests -> ASYNC req behavior
n always positive
Symbols unique -> store information there and sort later


*/

// compareBymarketCap
function compareByCapSymbol(a,b) {
  if(a["market-cap"] < b["market-cap"]) {
    return 1
  } else if ( a["market-cap"] > b["market-cap"]) {
    return -1
  } else {
    return a.symbol < b.symbol
  }
}

async function trendingStocks(n) {
  var myTrendingStocks = []
  // Keep a localized data store here too?

  // Fetch API JSON way to grab data
  // API already JSON-formatted array : no conv needed
  // // Promise based APi
  // fetch(SYMBOLS_API_BASE_URL)
  //  .then((response) => {
  //    let name = response.name;
  //    let symbol = response.symbol
  //    console.log("Name = \t" + name + "\t symbol = " + symbol)
  //   })
  //   .catch(function() {
  //     // Handle DOWN APIs
  //     console.log("Error fetching data from SYMBOLS API")
  //   })

  // Promise based API
  // Non=parameterized API calls : not based on each other -> execute at the same time
  // Use both pieces information later
  // Destructure results of promise.call
  const [symbolsRes, marketRes] = await Promise.all([fetch(SYMBOLS_API_BASE_URL), fetch(MARKET_CAPS_API_BASE_URL)]);
  const [symbolJson, marketJson] = await Promise.all([symbolsRes.json(), marketRes.json()]);
  var requestedStockSymbols = []
  // Set up symbol->name lookup here?
  // Store in a map too?
  var symbolName = new Map()
  var symbolCap = new Map()
  symbolJson.forEach((entry) => {
     let name = entry.name;
     let symbol = entry.symbol
     // requestedStockSymbols.push(symbol);
     symbolName.set(symbol,name)
     // console.log("Name = \t" + name + "\t symbol = " + symbol)
  })
  marketJson.sort(compareByCapSymbol)
  marketJson.forEach((marketEntry) => {
    let cap = marketEntry["market-cap"];
    let symbol = marketEntry["symbol"];
    symbolCap.set(symbol,cap)
  })
  for(let i = 0; i < n; i++){
    requestedStockSymbols.push(marketJson.at(i).symbol)
  }
  // console.log(requestedStockSymbols)
  var priceQueryParam = JSON.stringify(requestedStockSymbols)
  // fetch('https://example.com?' + new URLSearchParams({
    // foo: 'value',
    // bar: 2,
// }))
  // const priceRes = await fetch(PRICES_API_BASE_URL, priceQueryParam)
  priceRes = await fetch(PRICES_API_BASE_URL + "?" + new URLSearchParams({
    symbols: priceQueryParam,
  }))
  priceJson = await priceRes.json();
  // https://javascript.plainenglish.io/how-to-sort-json-object-arrays-based-on-a-key-a157461e9610
  // priceObjArr = JSON.parse(priceJson)
  priceJson.forEach((priceEntry) => {
    let high = priceEntry["52-week-high"];
    let low = priceEntry["52-week-low"];
    let price = priceEntry["price"]
    let symbol = priceEntry["symbol"]
    let cap = symbolCap.get(symbol)
    let name = symbolName.get(symbol)
    // var flower = {"propertyName1": propertyValue1, "propertyName2": propertyValue}; 
    let trendingStock = {
      "52-week-high": high,
      "52-week-low": low,
      "market-cap": cap,
      "name": name,
      "price": price,
      "symbol": symbol
    }
    myTrendingStocks.push(trendingStock)
    // console.log("For symbol = \t" + symbol + "\t price = " + price + "\t, low = " + low + "\t, high = " + high)
  })
  return myTrendingStocks
}

// Do not edit the line below.
exports.trendingStocks = trendingStocks;
v
