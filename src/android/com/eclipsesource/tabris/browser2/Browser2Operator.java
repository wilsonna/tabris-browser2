
package com.eclipsesource.tabris.browser2;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

import com.eclipsesource.tabris.android.TabrisActivity;
import com.eclipsesource.tabris.android.internal.toolkit.IAbsoluteUriBuilder;
import com.eclipsesource.tabris.android.internal.toolkit.operator.BrowserOperator;
import com.eclipsesource.tabris.android.internal.toolkit.property.IPropertyHandler;
import com.eclipsesource.tabris.android.internal.toolkit.view.Browser;
import com.eclipsesource.tabris.android.internal.toolkit.view.BrowserProgressListener;
import com.eclipsesource.tabris.client.core.model.Properties;
import com.eclipsesource.tabris.client.core.operation.CallOperation;
import com.eclipsesource.tabris.client.core.operation.CreateOperation;
import com.eclipsesource.tabris.client.core.operation.ListenOperation;
import com.eclipsesource.tabris.client.core.util.ValidationUtil;

public class Browser2Operator extends BrowserOperator {

	// private final Activity activity;
	// private final TabrisContext tabrisContext;
	// private final TabrisPropertyHandler<WebView> propertyHandler;

	private final IPropertyHandler<Browser> propertyHandler;
	private final CookieManager cookieManager = new CookieManager(); // .getInstance();

	public Browser2Operator(TabrisActivity activity, IAbsoluteUriBuilder absoluteUriBuilder) {
		super(activity, absoluteUriBuilder);

		// cookieManager.setAcceptCookie(true);
		cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
		CookieHandler.setDefault(cookieManager);
		propertyHandler = new Browser2PropertyHandler(activity, absoluteUriBuilder, cookieManager);
	}

	@Override
	protected IPropertyHandler<Browser> getPropertyHandler(Object object) {
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
		return "rwt.widgets.Browser2";
	}

	@Override
	public void create(CreateOperation operation) {
		ValidationUtil.validateCreateOperation(operation);
		Browser browser = new Browser(getActivity());
		initiateNewView(operation, browser);
		browser.init();
	}

	@Override
	protected void attachProgressListener(ListenOperation operation) {
		Browser browser = findObjectById(operation.getTarget(), Browser.class);
		browser.setProgressListener(new BrowserProgressListener(getActivity(), browser));
	}

	@Override
	protected void removeProgressListener(ListenOperation operation) {
		Browser browser = findObjectById(operation.getTarget(), Browser.class);
		browser.setProgressListener(null);
	}

	@Override
	public Object call(CallOperation operation) {
		ValidationUtil.validateCallOperation(operation);
		Properties properties = operation.getProperties();
		if ("evaluate".equals(operation.getMethod())) {
			String script = properties.getString("script");
			if (script != null) {
				Browser browser = findObjectById(operation.getTarget(), Browser.class);
				browser.executeScript(script);
			}
		}
		// else
		// if ("screenshot".equals(operation.getMethod())) {
		// Browser browser = (Browser)findObjectById(operation.getTarget(),
		// Browser.class);
		// return browser.takeScreenshot(getScreenshotFilename());
		// }
		return null;
	}

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

	// @Override
	// public void destroy(WebView webView) {
	// ((ViewGroup) webView.getParent()).removeView(webView);
	// }

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
