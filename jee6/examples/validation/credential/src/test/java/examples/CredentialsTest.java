package examples;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CredentialsTest {
	
	private Validator validator;
	
	@Before
	public void setUp() throws Exception {
		validator = Util.getValidator();
	}
	
	@Test
	public void testPassword() {
		Credentials credentials;
		Set<ConstraintViolation<Credentials>> violations;
		
		credentials = new Credentials(null, null);
		violations = validator.validateProperty(credentials, "password");
		Assert.assertEquals(1, violations.size());
		
		credentials = new Credentials(null, "abc");
		violations = validator.validateProperty(credentials, "password");
		Assert.assertEquals(1, violations.size());
		
		credentials = new Credentials(null, "123456");
		violations = validator.validateProperty(credentials, "password");
		Assert.assertEquals(1, violations.size());
		
		credentials = new Credentials(null, "1");
		violations = validator.validateProperty(credentials, "password");
		Assert.assertEquals(2, violations.size());
		
		credentials = new Credentials(null, "secretpwd");
		violations = validator.validateProperty(credentials, "password");
		Assert.assertEquals(0, violations.size());
	}
	
	@Test
	public void testUsername() {
		Credentials credentials;
		Set<ConstraintViolation<Credentials>> violations;
		
		credentials = new Credentials(null, null);
		violations = validator.validateProperty(credentials, "username");
		Assert.assertEquals(1, violations.size());
		
		credentials = new Credentials("user1", null);
		violations = validator.validateProperty(credentials, "username");
		Assert.assertEquals(0, violations.size());
		
		credentials = new Credentials("abcdefghijk", null);
		violations = validator.validateProperty(credentials, "username");
		Assert.assertEquals(1, violations.size());
	}
}
