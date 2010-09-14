package examples;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {
	
	@Override
	public void initialize(final Password annotation) {
	}
	
	@Override
	public boolean isValid(final String password, final ConstraintValidatorContext context) {
		boolean result = true;
		
		if (password != null) {
			try {
				Double.parseDouble(password);
				result = false;
				
			} catch (NumberFormatException e) {
				result = true;
			}
		}
		
		return result;
	}
	
}