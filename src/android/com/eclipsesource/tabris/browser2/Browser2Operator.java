
package com.eclipsesource.tabris.browser2;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

import com.eclipsesource.tabris.android.TabrisActivity;
import com.eclipsesource.tabris.android.internal.toolkit.IAbsoluteUriBuilder;
import com.eclipsesource.tabris.android.internal.toolkit.operator.BrowserOperator;
import com.eclipsesource.tabris.android.internal.toolkit.property.IPropertyHandler;
import com.eclipsesource.tabris.android.internal.toolkit.view.Browser;

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
