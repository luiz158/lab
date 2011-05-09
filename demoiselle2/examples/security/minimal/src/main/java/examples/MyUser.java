package examples;

import br.gov.frameworkdemoiselle.security.User;

public class MyUser implements User {

	private static final long serialVersionUID = 1L;

	private final String username;

	public MyUser(String username) {
		this.username = username;
	}

	@Override
	public String getId() {
		return username;
	}

	@Override
	public Object getAttribute(Object key) {
		return null;
	}

	@Override
	public void setAttribute(Object key, Object value) {
	}
}
