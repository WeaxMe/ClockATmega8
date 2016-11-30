package com.weaxme.web.panel;

import com.google.common.base.Strings;
import com.weaxme.Cache;
import com.weaxme.clock.Clock;
import com.weaxme.clock.ClockTime;
import com.weaxme.web.BrowseClock;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vitaliy Gonchar
 */
public class ClockEditPanel extends Panel {

    private static final Logger LOG = LoggerFactory.getLogger(ClockEditPanel.class);

    private Clock clock;

    private boolean isNew;

    public ClockEditPanel(String id) {
        super(id);
        isNew = true;
    }

    public ClockEditPanel(String id, Clock clock) {
        super(id);
        this.clock = clock;
        isNew = false;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new ClockEditForm("clockEditForm"));
    }


    class ClockEditForm extends Form {

        private String name;
        private String ip;
        private Integer port;

        private Integer hours = 0;
        private Integer minutes = 0;
        private Integer seconds = 0;

        private Label resultLabel;

        private TextField<String> nameField = new TextField<String>("name");
        private TextField<String> ipField   = new TextField<String>("ip");
        private TextField<Integer> portField = new TextField<Integer>("port");
        private DropDownChoice<Integer> hoursChoice = new DropDownChoice<Integer>("hours", getTimeRange(24));
        private DropDownChoice<Integer> minutesChoice = new DropDownChoice<Integer>("minutes", getTimeRange(60));
        private DropDownChoice<Integer> secondsChoice = new DropDownChoice<Integer>("seconds", getTimeRange(60));

        public ClockEditForm(String id) {
            super(id);
            if (clock != null) {
                name = clock.getName();
                ip = clock.getIp();
                port = clock.getPort();
                hours = clock.getHours();
                minutes = clock.getMinutes();
                seconds = clock.getMinutes();
                nameField.setDefaultModel(Model.of(name));
                ipField.setDefaultModel(Model.of(ip));
                portField.setDefaultModel(Model.of(port));
                hoursChoice.setDefaultModel(Model.of(hours));
                minutesChoice.setDefaultModel(Model.of(minutes));
                secondsChoice.setDefaultModel(Model.of(seconds));
            }
        }

        @Override
        protected void onInitialize() {
            super.onInitialize();
            resultLabel = new Label("result", Model.of(""));
            resultLabel.setVisible(false);
            setDefaultModel(new CompoundPropertyModel(this));
            add(resultLabel);
            add(nameField);
            add(ipField);
            add(portField);
            add(new Label("setTimeLabel", Model.of("Set Time: ")));
            add(hoursChoice);
            add(minutesChoice);
            add(secondsChoice);

            add(new Link("cancel") {
                @Override
                public void onClick() {
                    setResponsePage(BrowseClock.class);
                }
            });
        }

        private List<Integer> getTimeRange(int max) {
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i <= max; i++) {
                result.add(i);
            }
            return result;
        }


        @Override
        protected void onSubmit() {
            if (isIncorrectData()) {
                resultLabel.setDefaultModelObject("Incorrect input data. Cannot create new clock");
                resultLabel.setVisible(true);
            } else {
                if (isNew) {
                    LOG.debug("Add new clock");
                    boolean isAdd = Cache.addNewClockConfig(new Clock(name, ip, port, new ClockTime(hours, minutes, seconds)));
                    if (isAdd) {
                        setResponsePage(BrowseClock.class);
                    } else {
                        resultLabel.setDefaultModelObject("Incorrect input data. Cannot create new clock");
                        resultLabel.setVisible(true);
                    }
                } else {
                    LOG.debug("Edit exists clock");
                    name = nameField.getDefaultModelObjectAsString();
                    ip = ipField.getDefaultModelObjectAsString();
                    port = (Integer) portField.getDefaultModelObject();
                    hours = (Integer) hoursChoice.getDefaultModelObject();
                    minutes = (Integer) minutesChoice.getDefaultModelObject();
                    seconds = (Integer) secondsChoice.getDefaultModelObject();
                    boolean isEdit = Cache.editClockConfig(clock.getName(), new Clock(name, ip, port, new ClockTime(hours, minutes, seconds)));
                    if (isEdit) {
                        setResponsePage(BrowseClock.class);
                    } else {
                        resultLabel.setDefaultModelObject("Incorrect input data. Cannot edit clock");
                        resultLabel.setVisible(true);
                    }
                }
            }
        }

        private boolean isIncorrectData() {
            return Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(ip) || port == null;
        }
    }

}
