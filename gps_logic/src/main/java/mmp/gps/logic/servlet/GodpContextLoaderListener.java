package mmp.gps.logic.servlet;

import mmp.gps.logic.net.InstructSender;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class GodpContextLoaderListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent arg0) {
        InstructSender.stop();
    }

    public void contextInitialized(ServletContextEvent arg0) {
        InstructSender.start();
    }
}
