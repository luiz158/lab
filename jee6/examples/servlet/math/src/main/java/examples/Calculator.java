package examples;

public class Calculator {
	
	public static Double calculate(Integer n1, Integer n2, Operation op) {
		Double result;
		
		switch (op) {
		case ADD:
			result = Double.valueOf(n1 + n2);
			break;
		
		case SUB:
			result = Double.valueOf(n1 - n2);
			break;
		
		case MUL:
			result = Double.valueOf(n1 * n2);
			break;
		
		case DIV:
			result = Double.valueOf(n1) / Double.valueOf(n2);
			break;
		
		default:
			result = null;
		}
		
		return result;
	}
}
