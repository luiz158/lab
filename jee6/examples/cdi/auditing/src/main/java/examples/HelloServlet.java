package examples;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/")
public class HelloServlet extends HttpServlet {

	@Inject
	private HelloBean helloBean;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		helloBean.say(request.getParameter("s"));
		response.getWriter().write("O resultado est‡ no console do servidor.");
	}

}
