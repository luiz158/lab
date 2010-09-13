package examples;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = "/math")
public class ValidatorFilter implements Filter {

	private static final String PARAM_N1 = "n1";

	private static final String PARAM_N2 = "n2";

	private static final String PARAM_OP = "op";

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {

		validateNotNull(request);
		validateType(request);

		chain.doFilter(request, response);
	}

	private void validateNotNull(ServletRequest request) throws IOException {
		String value;

		value = request.getParameter(PARAM_N1);
		if (value == null || value.trim().equals("")) {
			throw new IOException("O parâmetro \"" + PARAM_N1 + "\" não foi encontrado ou está nulo");
		}

		value = request.getParameter(PARAM_N2);
		if (value == null || value.trim().equals("")) {
			throw new IOException("O parâmetro \"" + PARAM_N2 + "\" não foi encontrado ou está nulo");
		}

		value = request.getParameter(PARAM_OP);
		if (value == null || value.trim().equals("")) {
			throw new IOException("O parâmetro \"" + PARAM_OP + "\" não foi encontrado ou está nulo");
		}
	}

	private void validateType(ServletRequest request) throws IOException {
		String value;

		value = request.getParameter(PARAM_N1);
		try {
			Integer.parseInt(value);
		} catch (NumberFormatException cause) {
			throw new IOException("O parâmetro \"" + PARAM_N1 + "\" deve ser um número inteiro", cause);
		}

		value = request.getParameter(PARAM_N2);
		try {
			Integer.parseInt(value);
		} catch (NumberFormatException cause) {
			throw new IOException("O parâmetro \"" + PARAM_N2 + "\" deve ser um número inteiro", cause);
		}

		value = request.getParameter(PARAM_OP);
		try {
			Operation.valueOf(value.toUpperCase());
		} catch (IllegalArgumentException cause) {

			String validValues = "";
			for (Operation op : Operation.values()) {
				validValues += op.toString() + "; ";
			}

			throw new IOException("O parâmetro \"" + PARAM_OP + "\" não possui um valor válido: " + validValues, cause);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

}
