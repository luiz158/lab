package app.poll;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.hsqldb.Server;

@WebListener
public class DatabaseListener implements ServletContextListener {

	private Server server;

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		server.stop();
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		server = new Server();

		server.setDatabaseName(0, "polldb");
		server.setDatabasePath(0, "db/polldb");
		server.setPort(9001);
		server.setSilent(false);

		server.start();
	}

}
