package app.poll;

import org.hsqldb.Server;

public class Database {
	
	public static void main(String[] args) {
		Server server = new Server();
		
		server.setDatabaseName(0, "polldb");
		server.setDatabasePath(0, "db/polldb");
		server.setPort(9001);
		server.setSilent(false);
		
		server.start();
	}
}
