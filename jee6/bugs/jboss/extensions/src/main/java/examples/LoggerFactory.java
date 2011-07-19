package examples;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class LoggerFactory {
	
	@Produces
	public Logger build(InjectionPoint point) {
		Class<?> clazz = point.getMember().getDeclaringClass();
		return new Logger(clazz);
	}
}
