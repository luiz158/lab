package examples;

import javax.inject.Inject;

public class SpeakerBean {
	
	@Inject
	private Logger logger;
	
	@Audit
	public void say(String greeting) {
		logger.info("saying... " + greeting);
	}
	
	@Audit
	public void sayHello() {
		say("hello");
	}
}
