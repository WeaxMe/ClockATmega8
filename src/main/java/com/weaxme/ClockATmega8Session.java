//package com.weaxme;
//
//import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
//import org.apache.wicket.authroles.authorization.strategies.role.Roles;
//import org.apache.wicket.request.Request;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.Locale;
//
///**
// * @author Vitaliy Gonchar
// */
//public class ClockATmega8Session extends AuthenticatedWebSession {
//
//    private static final Logger LOG = LoggerFactory.getLogger(ClockATmega8Session.class);
//
//    private String user;
//
//    public ClockATmega8Session(Request request) {
//        super(request);
//    }
//
//    @Override
//    protected boolean authenticate(String username, String password) {
//        boolean isAuth = username.equals("admin") && password.equals("admin");
//        user = isAuth ? username : null;
//        return isAuth;
//    }
//
//    @Override
//    public Roles getRoles() {
//        Roles roles = new Roles();
//        if (user.equals("admin")) roles.add(Roles.ADMIN);
//        else roles.add(Roles.USER);
//        return roles;
//    }
//
//    @Override
//    public void signOut() {
//        super.signOut();
//        user = null;
//    }
//}
