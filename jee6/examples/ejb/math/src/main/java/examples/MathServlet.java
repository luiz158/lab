package examples;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/math")
public class MathServlet implements Servlet {
	
	@EJB
	private CalculatorBean calculator;
	
	@Override
	public void destroy() {
	}
	
	@Override
	public ServletConfig getServletConfig() {
		return null;
	}
	
	@Override
	public String getServletInfo() {
		return null;
	}
	
	@Override
	public void init(ServletConfig arg0) throws ServletException {
	}
	
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		Integer n1 = Integer.parseInt(request.getParameter("n1"));
		Integer n2 = Integer.parseInt(request.getParameter("n2"));
		Operation op = Operation.valueOf(request.getParameter("op").toUpperCase());
		
		Double result = calculator.calculate(n1, n2, op);
		response.getWriter().write("O resultado é " + result.toString());
	}
	
}
