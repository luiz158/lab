package examples;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterDeploymentValidation;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;

public class MyExtension implements Extension {

	public void starting(@Observes final BeforeBeanDiscovery event) {
		System.out.println("starting");
	}

	public <T> void processing(@Observes final ProcessAnnotatedType<T> event) {
		System.out.println("processing annoted type "
				+ event.getAnnotatedType());
	}

	public void started(@Observes final AfterDeploymentValidation event) {
		System.out.println("started");
	}
}
