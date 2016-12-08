package com.weaxme;

import com.weaxme.web.*;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ClockATmega8Application extends WebApplication {

	private static final Logger LOG = LoggerFactory.getLogger(ClockATmega8Application.class);

	@Override
	public Class<? extends WebPage> getHomePage() {
		return GetDataPage.class;
	}

	@Override
	public void init() {
		super.init();

//		getResourceSettings().setThrowExceptionOnMissingResource(false);
//		getSecuritySettings().setAuthorizationStrategy(new MetaDataRoleAuthorizationStrategy(this));
//		mountPage("/login", LoginPage.class);
		mountPage("/getData", GetDataPage.class);
		mountPage("/browse", BrowseClock.class);
		mountPage("/settings", SettingsPage.class);
		mountPage("/about", AboutPage.class);
		mountPage("/browse/clockEdit", ClockPage.class);
	}

//	@Override
//	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
//		return ClockATmega8Session.class;
//	}

//	@Override
//	protected Class<? extends WebPage> getSignInPageClass() {
//		return LoginPage.class;
//	}
}
