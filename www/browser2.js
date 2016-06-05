/*
tabris.registerWidget("WebView2", {
  _properties: {
    "cookie": {type: "any", nocache: true}
  }
  ,
  _events: {
    "change:date": {
      trigger: function(event) {
        this.trigger("change:date", this, event.date);
      }
    }
  }
});
*/

tabris.registerWidget("WebView2", {
  _type: "rwt.widgets.Browser2",
  _events: {
    navigate: {
      trigger: function(event, name) {
        var intercepted = false;
        event.preventDefault = function() {
          intercepted = true;
        };
        this.trigger(name, this, event);
        return intercepted;
      }
    },
    load: {
      name: "Progress",
      trigger: function(event) {
        this.trigger("load", this, event);
      }
    }
  },
  _properties: {
    url: {type: "string", nocache: true},
    html: {type: "string", nocache: true},
    headers: {type: "any", default: {}},
    cookies: {type: "any", default: {}},
    initScript: {type: "string"}
  }
});