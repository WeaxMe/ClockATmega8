package com.weaxme.web;

import com.weaxme.Cache;
import com.weaxme.clock.Clock;
import com.weaxme.web.panel.BrowseClockMenuPanel;
import com.weaxme.web.panel.ClockEditPanel;
import com.weaxme.web.panel.ClockInfoPanel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Vitaliy Gonchar
 */
public class BrowseClock extends BasePage {
    public BrowseClock(PageParameters parameters) {
        super(parameters);
    }

    private  BrowseClockMenuPanel browseClockMenuPanel;


    @Override
    protected void onInitialize() {
        super.onInitialize();
        title.setDefaultModelObject("Browse clock");

        List<Clock> allClocks = Cache.getAllClocks();
        final List<ClockInfoPanel> panels = new ArrayList<>();

        add(new ListView<Clock>("clockList", allClocks) {
            @Override
            protected void populateItem(ListItem<Clock> listItem) {
                ClockInfoPanel panel = new ClockInfoPanel("clock", listItem.getModelObject());
                panels.add(panel);
                listItem.add(panel);
            }
        });

        add(browseClockMenuPanel = new BrowseClockMenuPanel("clockMenu", panels));
    }
}
