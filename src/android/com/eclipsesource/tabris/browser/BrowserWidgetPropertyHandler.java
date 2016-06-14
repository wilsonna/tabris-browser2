
package com.eclipsesource.tabris.browser;

import java.net.CookieManager;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.view.ViewStub;

import com.eclipsesource.tabris.android.TabrisContext;
import com.eclipsesource.tabris.android.TabrisWidgetPropertyHandler;
import com.eclipsesource.tabris.client.core.model.Properties;

public class BrowserWidgetPropertyHandler extends TabrisWidgetPropertyHandler<ViewStub> {
	private final CookieManager cookieManager;

	public BrowserWidgetPropertyHandler(Activity activity, TabrisContext tabrisContext, CookieManager cookieManager) {
		super(activity, tabrisContext);
		this.cookieManager = cookieManager;
	}

	// public WebViewWidgetPropertyHandler(Activity activity, TabrisContext
	// tabrisContext) {
	// super(activity, tabrisContext);
	// }

	// @Override
	// public void set(Browser browser, Properties properties) {
	// super.set(browser, properties);
	// // if( properties.hasProperty( "date" ) ) {
	// // view.setDate( properties.getLong( "date" ), true, false );
	// // }
	// }

	// @Override
	// public Object get(Browser browser, String property) {
	// ParamCheck.notNull(browser, Browser.class);
	// ParamCheck.notNull(property, "property");
	//
	// if (property.equals("cookies")) {
	// return getCookies(browser);
	// }
	//
	// // if( property.equals( "date" ) ) {
	// // return String.valueOf( view.getDate() );
	// // } else {
	// return super.get(browser, property);
	// // }
	// }

	@Override
	public void set(ViewStub browser, Properties properties) {
		super.set(browser, properties);
		// setInitScript(browser, properties);
		// setHeaders(browser, properties);
		// setUrl(browser, properties);
		// setHtml(browser, properties);
	}

	// private void setUrl(Browser browser, Properties properties) {
	// String url = properties.getString("url");
	// if (url != null) {
	// // if (Uri.parse(url).isAbsolute()) {
	// browser.loadUrl(url);
	// // } else {
	// // browser.loadUrl(absoluteUriBuilder.build(url).toString());
	// // }
	// }
	// }
	//
	// private void setInitScript(Browser browser, Properties properties) {
	// String initScript = properties.getString("initScript");
	// if (initScript != null) {
	// browser.setInitScript(initScript);
	// }
	// }
	//
	// private void setHeaders(Browser browser, Properties properties) {
	// Properties headers = properties.getProperties("headers");
	// if (headers != null) {
	// browser.setHeaders(headers.getAll());
	// }
	// }
	//
	// private void setHtml(Browser browser, Properties properties) {
	// String html = properties.getString("html");
	// if (html != null) {
	// browser.setHtml(html);
	// }
	// }

	@Override
	public Object get(ViewStub browser, String property) {
		// ParamCheck.notNull(browser, Browser.class);
		// ParamCheck.notNull(property, "property");
		if (property.equals("cookies")) {
			return getCookies(browser);
		} else if (property.equals("cookies2")) {
			return getCookies2(browser);
		} else if (property.equals("cookies3")) {
			return getCookies3(browser);
		}
		// if (property.equals("url")) {
		// return getUrl(browser);
		// }
		// if (property.equals("html")) {
		// return getHtml(browser);
		// }
		return super.get(browser, property);
	}

	// private Object getHtml(Browser browser) {
	// return browser.getHtml();
	// }
	//
	// private String getUrl(Browser browser) {
	// if (browser.getHtml() != null) {
	// return "";
	// }
	// if (browser.getUrl() != null) {
	// return browser.getUrl();
	// }
	// if (browser.getOriginalUrl() != null) {
	// return browser.getOriginalUrl();
	// } else {
	// return "";
	// }
	// }

	private Object getCookies(ViewStub browser) {
		List<HttpCookie> cookies = cookieManager.getCookieStore().getCookies();
		List<HashMap<String, String>> cookies2 = new ArrayList<HashMap<String, String>>();
		for (HttpCookie cookie : cookies) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("name", cookie.getName());
			map.put("value", cookie.getValue());
			map.put("path", cookie.getPath());
			map.put("domain", cookie.getDomain());
			map.put("maxAge", Long.toString(cookie.getMaxAge()));
			// map.put("isHttpOnly", Boolean.toString(cookie.isHttpOnly()));
			map.put("isSecure", Boolean.toString(cookie.getSecure()));
			cookies2.add(map);
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("name", "dummy1");
		map.put("value", "dummy2");
		map.put("path", "dummy3");
		map.put("domain", "dummy4");
		map.put("maxAge", "9000");
		// map.put("isHttpOnly", Boolean.toString(cookie.isHttpOnly()));
		map.put("isSecure", "false");
		cookies2.add(map);

		return cookies2.toArray();
	}

	private Object getCookies2(ViewStub browser) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("name", "dummy1");
		map.put("value", "dummy2");
		map.put("path", "dummy3");
		map.put("domain", "dummy4");
		map.put("maxAge", "9000");
		// map.put("isHttpOnly", Boolean.toString(cookie.isHttpOnly()));
		map.put("isSecure", "false");

		return map;
	}

	private Object getCookies3(ViewStub browser) {
		StringBuffer cookieString = new StringBuffer();
		List<HttpCookie> cookies = cookieManager.getCookieStore().getCookies();
		for (HttpCookie cookie : cookies) {
			if (cookieString.length() > 0) {
				cookieString.append(",");
			}
			cookieString
			.append("{")
			.append("name: '" + cookie.getName() + "',")
			.append("value: '" + cookie.getValue() + "',")
			.append("path: '" + cookie.getPath() + "',")
			.append("domain: '" + cookie.getDomain() + "',")
			.append("maxAge: " + Long.toString(cookie.getMaxAge()) + ",")
			.append("isSecure: " + Boolean.toString(cookie.getSecure()))
			.append("}");
		}

		cookieString
		.append(",{")
		.append("name: 'dummy1',")
		.append("value: 'dummy2',")
		.append("path: 'dummy3',")
		.append("domain: 'dummy4',")
		.append("maxAge: 9000,")
		.append("isSecure: false")
		.append("}");

		return "[" + cookieString.toString() + "]";
	}

}
