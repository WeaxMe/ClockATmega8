package com.weaxme.web.panel;

import com.weaxme.Cache;
import com.weaxme.clock.Clock;
import com.weaxme.web.ClockPage;
import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Vitaliy Gonchar
 */
public class ClockInfoPanel extends Panel {

    private static final Logger LOG = LoggerFactory.getLogger(ClockInfoPanel.class);
    private Clock clock;
    private Label timeLabel;

    private InfoForm clockInfo;
    private int counter = 0;

    public ClockInfoPanel(String id, Clock clock) {
        super(id);
        this.clock = clock;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        clockInfo = new InfoForm("clockInfo");
        add(clockInfo);
    }

    public InfoForm getClockInfo() {
        return clockInfo;
    }

    public class InfoForm extends Form {

        private boolean isDelete;

        private CheckBox checkBox;

        public InfoForm(String id) {
            super(id);
        }

        @Override
        protected void onInitialize() {
            super.onInitialize();
            add(checkBox = new CheckBox("isDelete"));
            setDefaultModel(new CompoundPropertyModel(this));
            add(new Label("name", Model.of(clock.getName())));
            add(new Label("address", Model.of(clock.getIp() + ":" + clock.getPort())));
            timeLabel = new Label("time", Model.of(counter));
            timeLabel.add(new AbstractAjaxTimerBehavior(Duration.seconds(1)) {

                @Override
                protected void onTimer(AjaxRequestTarget target) {
                    timeLabel.setDefaultModelObject(counter);
                    target.add(timeLabel);
                    counter++;
                }
            });
            add(timeLabel);
            add(new Link("edit") {
                @Override
                public void onClick() {
                    ClockEditPanel editPanel = new ClockEditPanel("clockEdit", clock);
                    ClockPage clockPage = new ClockPage(null, editPanel);
                    setResponsePage(clockPage);
                }
            });
        }

        @Override
        protected void onSubmit() {
            super.onSubmit();
            LOG.debug("isDelete: " + isDelete);
            LOG.debug("read: " + checkBox.getDefaultModelObject());
            if (isDelete) {
                Cache.deleteClock(clock);
            }
        }
    }


}
