package com.weaxme.web;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

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
    }
}
