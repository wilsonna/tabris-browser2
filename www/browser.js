tabris.registerWidget("ESCookieManager", {
  _type: "com.wna.CookieManager",
  _properties: {
    cookies: {type: "array", default: []},
    cookies2: {type: "any", default: {}},
    cookies3: {type: "string", nocache: true}
  },
  getCookie: function() {
    return this._nativeCall("getCookie", arguments);
  }
});