package examples;

import javax.ejb.Stateless;

@Stateless
public class ConcatBean {
	
	public String concat(String... values) {
		StringBuffer result = new StringBuffer();
		
		for (String value : values) {
			result.append(value);
			result.append(" ------ ");
		}
		
		return result.toString();
	}
}
