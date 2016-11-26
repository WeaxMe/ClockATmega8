package com.weaxme.web.panel;

import com.weaxme.web.AboutPage;
import com.weaxme.web.BrowseClock;
import com.weaxme.web.SettingsPage;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author Vitaliy Gonchar
 */
public class MenuPanel extends Panel {

    public MenuPanel(String id) {
        super(id);
        add(new Link("browseLink") {
            @Override
            public void onClick() {
                setResponsePage(BrowseClock.class);
            }
        });
        add(new Link("settingsLink") {
            @Override
            public void onClick() {
                setResponsePage(SettingsPage.class);
            }
        });
        add(new Link("aboutLink") {
            @Override
            public void onClick() {
                setResponsePage(AboutPage.class);
            }
        });
        add(new Link("signOut") {
            @Override
            public void onClick() {
                AuthenticatedWebSession.get().signOut();
            }
        });
    }
}
