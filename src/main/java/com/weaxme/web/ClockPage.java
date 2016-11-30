package com.weaxme.web;

import com.weaxme.clock.Clock;
import com.weaxme.web.panel.ClockEditPanel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author Vitaliy Gonchar
 */
public class ClockPage extends BasePage {
    private ClockEditPanel editPanel;

    public ClockPage(PageParameters parameters) {
        super(parameters);
        editPanel = new ClockEditPanel("clockEdit");
    }

    public ClockPage(PageParameters pageParameters, ClockEditPanel editPanel) {
        super(pageParameters);
        this.editPanel = editPanel;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        title.setDefaultModelObject("Clock edit");
        add(editPanel);

    }

    public void setEditPanel(ClockEditPanel editPanel) {
        this.editPanel = editPanel;
    }
}
