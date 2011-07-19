package examples;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;

public class MyExtension implements Extension {

	public <T> void processAnnotatedType(@Observes final ProcessAnnotatedType<T> event, final BeanManager beanManager) {
		System.out.println("processing annoted type " + event.getAnnotatedType());
	}
}
