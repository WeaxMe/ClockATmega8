package com.weaxme.web;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author Vitaliy Gonchar
 */
public class BrowseClock extends BasePage {
    public BrowseClock(PageParameters parameters) {
        super(parameters);
        add(new Label("browseLabel", "Browse clock"));
    }
}
