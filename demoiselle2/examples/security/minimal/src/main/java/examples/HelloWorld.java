package examples;

import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.security.RequiredPermission;
import br.gov.frameworkdemoiselle.security.RequiredRole;

public class HelloWorld {

	@Inject
	private Logger logger;

	@RequiredPermission(resource = "hello", operation = "say")
	public void say() {
		logger.info("Saying hello on console");
	}

	@RequiredRole("administrators")
	public void swear() {
		logger.info("I swear...");
	}
}
