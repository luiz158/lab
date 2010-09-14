package examples;

import javax.inject.Inject;

public class HelloBean {

	@Inject
	private Logger logger;

	@Audit
	public void say(String greeting) {
		logger.info("saying... " + greeting);
	}
}
