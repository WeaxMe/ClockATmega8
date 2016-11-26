package com.weaxme.web;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author Vitaliy Gonchar
 */
public class SettingsPage extends BasePage {
    public SettingsPage(PageParameters parameters) {
        super(parameters);
        add(new Label("title", "Settings"));
        add(new Label("settingsLabel", "Settings"));
    }
}
