package com.weaxme.web;

import com.weaxme.web.panel.MenuPanel;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;

public abstract class BasePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public static final CssResourceReference MAIN_CSS = new CssResourceReference(BasePage.class, "style.css");

	protected Component menuPanel;
	protected Label title;
	public BasePage(final PageParameters parameters) {
		super(parameters);
		title = new Label("title", "Title");
    }

	@Override
	protected void onInitialize() {
		menuPanel = new MenuPanel("menuPanel");
		add(menuPanel);
		add(title);
		super.onInitialize();
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		response.render(CssReferenceHeaderItem.forReference(MAIN_CSS));
	}

//	@Override
//	protected void onConfigure() {
//		super.onConfigure();
//		AuthenticatedWebApplication app = (AuthenticatedWebApplication) Application.get();
//		if (!AuthenticatedWebSession.get().isSignedIn() && !getClass().equals(LoginPage.class)) {
//			app.restartResponseAtSignInPage();
//		}
//	}
}
