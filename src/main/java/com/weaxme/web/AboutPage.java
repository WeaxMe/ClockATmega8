package com.weaxme.web;

import org.apache.wicket.Application;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Localizer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author Vitaliy Gonchar
 */
public class AboutPage extends BasePage {
    public AboutPage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        title.setDefaultModelObject("About");
        Label label = new Label("aboutLabel", "About page");
        label.add(new AttributeModifier("style", "color:red;font-weight:bold"));
        add(label);
        super.onInitialize();
    }
}
