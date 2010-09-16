package app.poll;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.hsqldb.Server;

@WebListener
public class DatabaseListener implements ServletContextListener {

	private final Server server;

	public DatabaseListener() {
		server = new Server();
		server.setDatabaseName(0, "polldb");
		server.setDatabasePath(0, "db/polldb");
		server.setPort(9001);
		server.setSilent(false);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		server.stop();
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		server.start();
	}

}
