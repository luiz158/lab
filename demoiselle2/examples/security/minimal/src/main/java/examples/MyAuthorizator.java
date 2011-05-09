package examples;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.security.Authorizator;
import br.gov.frameworkdemoiselle.security.SecurityContext;

@Alternative
public class MyAuthorizator implements Authorizator {

	private static final long serialVersionUID = 1L;

	@Inject
	private SecurityContext context;

	@Override
	public boolean hasRole(String role) {
		String usr = context.getUser().getId();
		boolean authorized = false;

		if (usr.equals("admin") && role.equals("administrators")) {
			authorized = true;
		}

		return authorized;
	}

	@Override
	public boolean hasPermission(Object res, String op) {
		String usr = context.getUser().getId();
		boolean authorized = false;

		if (usr.equals("zyc") && res.equals("hello") && op.equals("say")) {
			authorized = true;
		} else if (context.hasRole("administrators")) {
			authorized = true;
		}

		return authorized;
	}
}
