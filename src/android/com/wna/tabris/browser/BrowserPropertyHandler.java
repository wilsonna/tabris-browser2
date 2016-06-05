package com.wna.tabris.browser;

import java.net.CookieManager;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;

import com.eclipsesource.tabris.android.TabrisContext;
import com.eclipsesource.tabris.android.TabrisWidgetPropertyHandler;
import com.eclipsesource.tabris.android.internal.toolkit.view.Browser;
import com.eclipsesource.tabris.client.core.model.Properties;
import com.eclipsesource.tabris.client.core.util.ParamCheck;

public class BrowserPropertyHandler extends TabrisWidgetPropertyHandler<Browser> {
	private final CookieManager cookieManager;

	public BrowserPropertyHandler(Activity activity, TabrisContext tabrisContext, CookieManager cookieManager) {
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
	public void set(Browser browser, Properties properties) {
		super.set(browser, properties);
		setInitScript(browser, properties);
		setHeaders(browser, properties);
		setUrl(browser, properties);
		setHtml(browser, properties);
	}

	private void setUrl(Browser browser, Properties properties) {
		String url = properties.getString("url");
		if (url != null) {
			// if (Uri.parse(url).isAbsolute()) {
				browser.loadUrl(url);
			// } else {
			// browser.loadUrl(absoluteUriBuilder.build(url).toString());
			// }
		}
	}

	private void setInitScript(Browser browser, Properties properties) {
		String initScript = properties.getString("initScript");
		if (initScript != null) {
			browser.setInitScript(initScript);
		}
	}

	private void setHeaders(Browser browser, Properties properties) {
		Properties headers = properties.getProperties("headers");
		if (headers != null) {
			browser.setHeaders(headers.getAll());
		}
	}

	private void setHtml(Browser browser, Properties properties) {
		String html = properties.getString("html");
		if (html != null) {
			browser.setHtml(html);
		}
	}

	@Override
	public Object get(Browser browser, String property) {
		ParamCheck.notNull(browser, Browser.class);
		ParamCheck.notNull(property, "property");
		if (property.equals("cookies")) {
			return getCookies(browser);
		}
		if (property.equals("url")) {
			return getUrl(browser);
		}
		if (property.equals("html")) {
			return getHtml(browser);
		}
		return super.get(browser, property);
	}

	private Object getHtml(Browser browser) {
		return browser.getHtml();
	}

	private String getUrl(Browser browser) {
		if (browser.getHtml() != null) {
			return "";
		}
		if (browser.getUrl() != null) {
			return browser.getUrl();
		}
		if (browser.getOriginalUrl() != null) {
			return browser.getOriginalUrl();
		} else {
			return "";
		}
	}

	private Object getCookies(Browser browser) {
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

		return cookies2;
	}

}
