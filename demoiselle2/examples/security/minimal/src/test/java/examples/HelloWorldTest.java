package examples;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.fail;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.exception.SecurityException;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.util.DemoiselleRunner;

@RunWith(DemoiselleRunner.class)
public class HelloWorldTest {

	@Inject
	private SecurityContext context;

	@Inject
	private MyCredentials credentials;

	@Inject
	private HelloWorld helloWorld;

	@Test
	public void loginSuccessful() {
		// Acessando com meu usuário
		credentials.setUsername("zyc");
		credentials.setPassword("tcharam");
		context.login();
		assertEquals("zyc", context.getUser().getId());
		context.logout();

		// Acessando como admin
		credentials.setUsername("admin");
		credentials.setPassword("secret");
		context.login();
		assertEquals("admin", context.getUser().getId());
		context.logout();
	}

	@Test
	public void loginFailed() {
		// Tentando acessar com usuário inexistente
		credentials.setUsername("fake");
		credentials.setPassword("idontknow");
		context.login();
		assertNull(context.getUser());
	}

	@Test
	public void authorizedAccess() {
		// Acessando o método "say" com meu usuário
		credentials.setUsername("zyc");
		credentials.setPassword("tcharam");
		context.login();
		helloWorld.say();
		context.logout();

		// Acessando o método "say" e "swear" como admin
		credentials.setUsername("admin");
		credentials.setPassword("secret");
		context.login();
		helloWorld.say();
		helloWorld.swear();
		context.logout();
	}

	@Test
	public void unauthorizedAccess() {
		// Tentando acessar o método "swear"
		credentials.setUsername("zyc");
		credentials.setPassword("tcharam");
		context.login();

		try {
			helloWorld.swear();
			fail();
		} catch (SecurityException e) {
			// Se chegar aqui, o teste deve passar
		}

		context.logout();
	}
}
