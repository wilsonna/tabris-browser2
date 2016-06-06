package com.wna.tabris.browser;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

import android.view.ViewGroup;

import com.eclipsesource.tabris.android.AbstractTabrisOperator;
import com.eclipsesource.tabris.android.TabrisActivity;
import com.eclipsesource.tabris.android.TabrisContext;
import com.eclipsesource.tabris.android.TabrisPropertyHandler;
import com.eclipsesource.tabris.android.internal.toolkit.view.Browser;
import com.eclipsesource.tabris.android.internal.toolkit.view.BrowserProgressListener;
import com.eclipsesource.tabris.client.core.model.Properties;

public class BrowserOperator extends AbstractTabrisOperator<Browser> {

	private final TabrisActivity activity;
	// private final TabrisContext tabrisContext;
	// private final TabrisPropertyHandler<WebView> propertyHandler;

	private final TabrisPropertyHandler<Browser> propertyHandler;
	private final CookieManager cookieManager = new CookieManager(); // .getInstance();

	public BrowserOperator(TabrisActivity activity, TabrisContext tabrisContext) {
		this.activity = activity;
		// this.tabrisContext = tabrisContext;
		cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
		CookieHandler.setDefault(cookieManager);
		propertyHandler = new BrowserPropertyHandler(activity, tabrisContext, cookieManager);
	}

	@Override
	public TabrisPropertyHandler<Browser> getPropertyHandler() {
		return propertyHandler;
	}

	// public WebViewOperator(Activity activity, TabrisContext tabrisContext) {
	// this.activity = activity;
	// this.tabrisContext = tabrisContext;
	// propertyHandler = new WebViewWidgetPropertyHandler(activity,
	// tabrisContext);
	// }

	@Override
	public String getType() {
		return "com.wna.Browser";
	}

	@Override
	public Browser create(Properties properties) {
		// ValidationUtil.validateCreateOperation(operation);
		// WebView webView = new WebView();
		Browser browser = new Browser(activity);
		// initiateNewView(operation, browser);

		browser.setProgressListener(new BrowserProgressListener(activity, browser));

		browser.init();
		return browser;
	}

	// @Override
	// protected void attachProgressListener(ListenOperation operation) {
	// Browser browser = findObjectById(operation.getTarget(), Browser.class);
	// browser.setProgressListener(new BrowserProgressListener(getActivity(),
	// browser));
	// }
	//
	// @Override
	// protected void removeProgressListener(ListenOperation operation) {
	// Browser browser = findObjectById(operation.getTarget(), Browser.class);
	// browser.setProgressListener(null);
	// }

	// @Override
	// public Object call(CallOperation operation) {
	// ValidationUtil.validateCallOperation(operation);
	// Properties properties = operation.getProperties();
	// if ("evaluate".equals(operation.getMethod())) {
	// String script = properties.getString("script");
	// if (script != null) {
	// Browser browser = findObjectById(operation.getTarget(), Browser.class);
	// browser.executeScript(script);
	// }
	// }
		// else
		// if ("screenshot".equals(operation.getMethod())) {
		// Browser browser = (Browser)findObjectById(operation.getTarget(),
		// Browser.class);
		// return browser.takeScreenshot(getScreenshotFilename());
		// }
	// return null;
	// }

	// private String getScreenshotFilename() {
	// return (new
	// StringBuilder()).append("screenshot").append(screenshotCounter++ %
	// 10).append(".png").toString();
	// }

	// @Override
	// public void create(CreateOperation operation) {
	// ValidationUtil.validateCreateOperation(operation);
	// Browser browser = new Browser(getActivity());
	//
	// cookieManager.acceptThirdPartyCookies(browser);
	//
	// initiateNewView(operation, browser);
	// browser.init();
	// // super.create(operation);
	// }

	// @Override
	// public TabrisPropertyHandler<WebView> getPropertyHandler() {
	// return propertyHandler;
	// }
	//
	// @Override
	// public WebView create(Properties properties) {
	// WebView webView = new WebView(activity);
	// // calendarView.setOnDateChangeListener( new OnDateChangeListener() );
	// return webView;
	// }

	@Override
	public void destroy(Browser browser) {
		((ViewGroup) browser.getParent()).removeView(browser);
	}

	// private class OnDateChangeListener implements
	// CalendarView.OnDateChangeListener {
	//
	// @Override
	// public void onSelectedDayChange( CalendarView view, int year, int month,
	// int dayOfMonth ) {
	// String date = String.valueOf( new GregorianCalendar( year, month,
	// dayOfMonth + 1 ).getTimeInMillis() );
	// RemoteObject remoteObject =
	// tabrisContext.getObjectRegistry().getRemoteObjectForObject( view );
	// remoteObject.notify( "change:date", "date", date );
	// }
	// }

}
