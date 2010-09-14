package examples;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class Util {
	
	private static Validator validator;
	
	public static Validator getValidator() {
		if (validator == null) {
			ValidatorFactory dfv = Validation.buildDefaultValidatorFactory();
			validator = dfv.getValidator();
		}
		
		return validator;
	}
}
