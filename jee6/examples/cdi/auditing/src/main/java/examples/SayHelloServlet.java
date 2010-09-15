package examples;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/sayHello")
public class SayHelloServlet extends HttpServlet {
	
	@Inject
	private SpeakerBean bean;
	
	private void draw(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().write("<html>");
		response.getWriter().write("	<body>");
		
		response.getWriter().write("		<p>OSB: O resultado está no console do servidor.</p>");
		response.getWriter().write("		<p><a href=\"say\">SayServlet</a></p>");
		
		response.getWriter().write("	</body>");
		response.getWriter().write("</html>");
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		bean.sayHello();
		draw(request, response);
	}
	
}
