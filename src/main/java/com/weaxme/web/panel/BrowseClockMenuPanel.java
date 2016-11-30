package com.weaxme.web.panel;

import com.weaxme.Cache;
import com.weaxme.web.ClockPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Vitaliy Gonchar
 */
public class BrowseClockMenuPanel extends Panel {

    private static final Logger LOG = LoggerFactory.getLogger(BrowseClockMenuPanel.class);

    private List<ClockInfoPanel> panels;

    public BrowseClockMenuPanel(String id, List<ClockInfoPanel> panels) {
        super(id);
        this.panels = panels;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Link("add") {
            @Override
            public void onClick() {
                setResponsePage(ClockPage.class);
            }
        });
        add(new Link("delete") {
            @Override
            public void onClick() {
                LOG.debug("Cache before delete size: " + Cache.getSize());
                for (ClockInfoPanel panel : panels) {
                    panel.getClockInfo().onSubmit();
                }
                LOG.debug("Cache after delete size: " + Cache.getSize());
            }
        });
    }
}
