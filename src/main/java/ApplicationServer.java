import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.rythmengine.Rythm;

import jakarta.servlet.Servlet;
import java.util.HashMap;
import java.util.Map;

public class ApplicationServer {

    private final int port;
    private final Servlet servlet;
    private Server server;

    public ApplicationServer(int port, Servlet servlet) {
        this.port = port;
        this.servlet = servlet;
    }

    public void start() throws Exception {
        server = new Server(port);
        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(new ServletHolder(servlet), "/*");
        addStaticFileServing(handler);
        server.setHandler(handler);
        server.start();
    }

    public void stop() throws Exception {
        server.stop();
    }

    public ApplicationServer withRythm() {
        initTemplateEngine();
        return this;
    }

    private void initTemplateEngine() {
        Map<String, Object> conf = new HashMap<>();
        conf.put("home.template", "templates");
        Rythm.init(conf);
    }

    private void addStaticFileServing(ServletContextHandler handler) {
        ServletHolder holderPwd = new ServletHolder("default", new DefaultServlet());
        holderPwd.setInitParameter("resourceBase", "./src/main/resources/static");
        holderPwd.setInitParameter("dirAllowed","false");
        holderPwd.setInitParameter("pathInfoOnly","true");
        handler.addServlet(holderPwd, "/static/*");
    }
}