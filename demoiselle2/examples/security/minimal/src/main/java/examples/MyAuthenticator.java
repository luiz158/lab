package examples;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.security.Authenticator;
import br.gov.frameworkdemoiselle.security.User;

@Alternative
public class MyAuthenticator implements Authenticator {

	private static final long serialVersionUID = 1L;

	@Inject
	private MyCredentials credentials;

	@Override
	public boolean authenticate() {
		String username = credentials.getUsername();
		String password = credentials.getPassword();

		boolean authenticated = false;

		if (username.equals("admin") && password.equals("secret")) {
			authenticated = true;
		} else if (username.equals("zyc") && password.equals("tcharam")) {
			authenticated = true;
		}

		return authenticated;
	}

	@Override
	public void unAuthenticate() {
		credentials.clear();
	}

	@Override
	public User getUser() {
		return new User() {

			private static final long serialVersionUID = 1L;

			@Override
			public String getId() {
				return credentials.getUsername();
			}

			@Override
			public void setAttribute(Object key, Object value) {
			}

			@Override
			public Object getAttribute(Object key) {
				return null;
			}
		};
	}
}
