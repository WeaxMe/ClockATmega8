//package com.weaxme.web;
//
//import com.google.common.base.Strings;
//import org.apache.wicket.Application;
//import org.apache.wicket.Localizer;
//import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
//import org.apache.wicket.authroles.authentication.panel.SignInPanel;
//import org.apache.wicket.markup.html.WebPage;
//import org.apache.wicket.markup.html.basic.Label;
//import org.apache.wicket.markup.html.form.Form;
//import org.apache.wicket.markup.html.form.PasswordTextField;
//import org.apache.wicket.markup.html.form.TextField;
//import org.apache.wicket.markup.html.image.ContextImage;
//import org.apache.wicket.model.CompoundPropertyModel;
//import org.apache.wicket.request.mapper.parameter.PageParameters;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * @author Vitaliy Gonchar
// */
//public class LoginPage extends BasePage {
//
//    private static final Logger LOG = LoggerFactory.getLogger(LoginPage.class);
//
//    public LoginPage(PageParameters parameters) {
//        super(parameters);
//    }
//
//    @Override
//    protected void onInitialize() {
//        super.onInitialize();
//        title.setDefaultModelObject("Login in ATmega8Clock");
//        if (menuPanel.isVisible()) menuPanel.setVisible(false);
//        ContextImage image = new ContextImage("atmega8", "atmega8.png");
//        SignInPanel signInPanel = new SignInPanel("signInPanel");
//        signInPanel.setRememberMe(false);
//        add(signInPanel);
//        add(image);
//
//    }
//}
