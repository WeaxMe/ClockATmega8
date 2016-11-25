package com.weaxme.web;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author Vitaliy Gonchar
 */
public class AboutPage extends BasePage {
    public AboutPage(PageParameters parameters) {
        super(parameters);
        add(new Label("aboutLabel", "About page"));
    }
}
