/**
 * 
 */
package com.waheed.spring.hibernate;

import java.io.File;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;


/**
 * @author abdul
 * 
 */
public class WebServer {

    private static Logger LOG = LoggerFactory.getLogger(WebServer.class);

    private static final String JETTY_WAR = "jetty.war";
    private int port = 9443;
    private File dataDir = null;
    private Server server;

    @Required
    public void setDataDir(File dataDir) {
        this.dataDir = dataDir;
    }

    public WebServer() {
        server = new Server();
    }

    /**
     * @return webServer port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port
     *            the port to set
     */
    @Required
    public void setPort(int port) {
        this.port = port;
    }


    public void start() throws Exception {
        LOG.info("Starting web server at " + dataDir);

        File warFile = new File(dataDir, JETTY_WAR);
        if (!warFile.exists()) {

            // Try another location - just to enable run from the workspace
            File devWarFile = new File(".", "WebContent");
            if (devWarFile.exists()) {
                warFile = devWarFile;
            } else {
                throw new Exception("Web appllication can not be found : "
                        + warFile);
            }
        }

        Connector connector = new SelectChannelConnector();
        connector.setPort(port);
        server.setConnectors(new Connector[] { connector });
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setWar(warFile.toString());
        webapp.setParentLoaderPriority(true);
        server.setHandler(webapp);
        server.start();
    }

    public void stop() throws Exception {
        if (server != null) {
            server.stop();
            server.join();
            server = null;
        }
    }
}
