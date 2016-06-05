
package com.eclipsesource.tabris.browser2;

//import android.webkit.CookieManager;

import java.net.CookieManager;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.eclipsesource.tabris.android.TabrisActivity;
import com.eclipsesource.tabris.android.internal.toolkit.IAbsoluteUriBuilder;
import com.eclipsesource.tabris.android.internal.toolkit.property.BrowserPropertyHandler;
import com.eclipsesource.tabris.android.internal.toolkit.view.Browser;
import com.eclipsesource.tabris.client.core.model.Properties;
import com.eclipsesource.tabris.client.core.util.ParamCheck;

public class Browser2PropertyHandler extends BrowserPropertyHandler<Browser> {
	private final CookieManager cookieManager;

	public Browser2PropertyHandler(TabrisActivity activity, IAbsoluteUriBuilder absoluteUriBuilder, CookieManager cookieManager) {
		super(activity, absoluteUriBuilder);
		this.cookieManager = cookieManager;
	}

	// public WebViewWidgetPropertyHandler(Activity activity, TabrisContext
	// tabrisContext) {
	// super(activity, tabrisContext);
	// }

	@Override
	public void set(Browser browser, Properties properties) {
		super.set(browser, properties);
		// if( properties.hasProperty( "date" ) ) {
		// view.setDate( properties.getLong( "date" ), true, false );
		// }
	}

	@Override
	public Object get(Browser browser, String property) {
		ParamCheck.notNull(browser, Browser.class);
		ParamCheck.notNull(property, "property");

		if (property.equals("cookies")) {
			return getCookies(browser);
		}

		// if( property.equals( "date" ) ) {
		// return String.valueOf( view.getDate() );
		// } else {
		return super.get(browser, property);
		// }
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
			map.put("isHttpOnly", Boolean.toString(cookie.isHttpOnly()));
			map.put("isSecure", Boolean.toString(cookie.getSecure()));
			cookies2.add(map);
		}

		return cookies2;
	}

}
