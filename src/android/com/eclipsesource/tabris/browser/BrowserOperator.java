
package com.eclipsesource.tabris.browser;

//import java.net.CookieHandler;
//import java.net.CookieManager;
//import java.net.CookiePolicy;

import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.webkit.CookieManager;

import com.eclipsesource.tabris.android.AbstractTabrisOperator;
import com.eclipsesource.tabris.android.TabrisContext;
import com.eclipsesource.tabris.android.TabrisPropertyHandler;
import com.eclipsesource.tabris.client.core.model.Properties;
import com.eclipsesource.tabris.client.core.util.ParamCheck;

public class BrowserOperator extends AbstractTabrisOperator<ViewStub> {

	private final Activity activity;
	// private final TabrisContext tabrisContext;
	// private final TabrisPropertyHandler<WebView> propertyHandler;

	private final TabrisPropertyHandler<ViewStub> propertyHandler;
	private final CookieManager cookieManager = CookieManager.getInstance();// new CookieManager(); // .getInstance();

	public BrowserOperator(Activity activity, TabrisContext tabrisContext) {
		// TabrisActivity tabrisActivity = (TabrisActivity) activity;

		// Browser browser = new Browser(tabrisActivity);
		// WebView webview = new WebView(tabrisActivity);
		// webview.setWebViewClient(new
		// BrowserWebViewClient(tabrisActivity.getIntentOfCreate().getBooleanExtra("strictSSL",
		// true)));

		this.activity = activity;
		// this.tabrisContext = tabrisContext;
//		cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
//		CookieHandler.setDefault(cookieManager);
		propertyHandler = new BrowserWidgetPropertyHandler(activity, tabrisContext, cookieManager);
	}

	@Override
	public TabrisPropertyHandler<ViewStub> getPropertyHandler() {
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
		return "com.wna.CookieManager";
	}

	@Override
	public ViewStub create(Properties properties) {
		// ValidationUtil.validateCreateOperation(operation);
		// WebView webView = new WebView();
		// TabrisActivity tabrisActivity = (TabrisActivity) activity;

		ViewStub browser = new ViewStub(activity.getApplicationContext());
		browser.setVisibility(0);
		// Browser browser = new Browser(tabrisActivity);
		// initiateNewView(operation, browser);
		// browser.init();

		// browser.setProgressListener(new
		// BrowserProgressListener(tabrisActivity, browser));

		return browser;
	}

	@Override
	public Object call(ViewStub object, String method, Properties properties) {
		Log.d( "com.wna.CookieManager", String.format( "Call to method \"%s\". Properties: %s", method, properties ) );
		
		if (method.equals("getCookie")) {
			String url = properties.getString("url");
			ParamCheck.notNull(url, "url");
			return cookieManager.getCookie(url);
		}
		return super.call(object, method, properties);
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
	public void destroy(ViewStub browser) {
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
