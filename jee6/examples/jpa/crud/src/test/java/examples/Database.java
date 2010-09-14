package examples;

import org.hsqldb.Server;

public class Database {
	
	private final HsqldbServer server;
	
	public Database(String name, int port) {
		this.server = new HsqldbServer(name, port);
	}
	
	public void start() {
		this.server.start();
	}
	
	public void stop() {
		this.server.shutdown();
	}
}

class HsqldbServer extends Thread {
	
	private final Server server;
	
	private final String name;
	
	private final int port;
	
	HsqldbServer(String name, int port) {
		this.name = name;
		this.port = port;
		
		this.server = create();
		setDaemon(true);
	}
	
	private Server create() {
		Server server = new Server();
		server.setDatabaseName(0, name);
		server.setDatabasePath(0, "db/" + name);
		server.setPort(port);
		server.setSilent(false);
		
		return server;
	}
	
	@Override
	public void run() {
		this.server.start();
	}
	
	public void shutdown() {
		this.server.stop();
	}
}
