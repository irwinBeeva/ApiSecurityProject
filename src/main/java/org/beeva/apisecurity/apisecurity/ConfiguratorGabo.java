package org.beeva.apisecurity.apisecurity;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ConfiguratorGabo implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent evt) {
		// TODO Auto-generated method stub

	}

	public void contextInitialized(ServletContextEvent evt) {
		ServletContext context = evt.getServletContext();
		FilterRegistration.Dynamic registration = context.addFilter(
				"authenticationFilter", new AuthenticationFilter());
		registration.setAsyncSupported(true);
		registration.addMappingForUrlPatterns(null, false, 
				"/sessions", "/tickets");
	}

}
