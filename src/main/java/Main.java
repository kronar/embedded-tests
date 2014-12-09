import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 */
public class Main {


    public static void main(String[] args) {


        Server server = new Server(8080);
        Injector injector = Guice.createInjector(new MainModule());
        final ServletContextHandler context = new ServletContextHandler();
        context.addServlet(new ServletHolder(new UnitTestRunner()), "/runUnitTests");
        context.addServlet(new ServletHolder(new IntegrationTestRunner(injector)),"/runIntegrationTests");

        server.setHandler(context);
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
