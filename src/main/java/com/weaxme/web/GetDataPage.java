package com.weaxme.web;

import com.weaxme.driver.ClientESP8266;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author Vitaliy Gonchar
 */
public class GetDataPage extends BasePage {

    private static final ClientESP8266 ESP_8266 = new ClientESP8266();

    private String data = "No input data";
    private Label dataLabel;

    public GetDataPage(PageParameters parameters) {
        super(parameters);
        dataLabel = new Label("data", Model.of(data));
        dataLabel.setOutputMarkupId(true);
        add(dataLabel);
        add(new AjaxLink("getDataBut") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                data = ESP_8266.getData();
                dataLabel.setDefaultModelObject(data);
                target.add(dataLabel);
            }
        });
    }
}
