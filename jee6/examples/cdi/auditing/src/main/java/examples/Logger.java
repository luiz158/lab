package examples;

import java.util.Date;

public class Logger {
	
	private final Class<?> clazz;
	
	public Logger(Class<?> clazz) {
		this.clazz = clazz;
	}
	
	public void info(String message) {
		System.out.println(new Date().toString() + " - " + clazz.getCanonicalName() + ": " + message);
	}
}
