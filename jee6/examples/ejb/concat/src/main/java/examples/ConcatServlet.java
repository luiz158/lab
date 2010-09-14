package examples;

import java.io.IOException;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/")
public class ConcatServlet extends HttpServlet {
	
	@EJB
	private ConcatBean concatBean;
	
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		String result = concatBean.concat(request.getProtocol(), request.getRemoteHost(), new Date().toString());
		response.getWriter().write("Resultado: " + result.toString());
		
		super.service(request, response);
	}
	
}
