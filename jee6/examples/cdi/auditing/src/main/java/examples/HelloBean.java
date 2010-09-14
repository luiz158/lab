package examples;

import javax.inject.Inject;

public class HelloBean {
	
	@Inject
	private Logger logger;
	
	public void say(String greeting) {
		logger.info("saying... " + greeting);
	}
}
