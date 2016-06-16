tabris.registerWidget("ESCookieManager", {
  _type: "com.wna.CookieManager",
  _properties: {
    cookies: {type: "array", default: []},
    cookies2: {type: "any", default: {}},
    cookies3: {type: "string", nocache: true}
  },
  getCookie: function(url) {
    return this._nativeCall("getCookie", {url: url});
  }
});