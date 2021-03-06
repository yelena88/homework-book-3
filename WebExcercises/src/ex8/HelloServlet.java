package ex8;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/helloServlet")
public class HelloServlet extends HttpServlet {
  @Override

  public void doGet(HttpServletRequest request, HttpServletResponse response)
          throws IOException, ServletException {
    // Set the response message's MIME type
    response.setContentType("text/html;charset=UTF-8");
    // Allocate a output writer to write the response message into the network socket

    // Write the response message, in an HTML page
    try (PrintWriter out = response.getWriter()) {

      out.println("<!DOCTYPE html>");
      out.println("<html><head>");
      out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
      out.println("<title>Hello, World</title></head>");
      out.println("<body>");
      out.println("<h1>Hello, world!</h1>");

      // Echo client's request information
      out.println("<p>Request URI: " + request.getRequestURI() + "</p>");
      out.println("<p>Protocol: " + request.getProtocol() + "</p>");
      out.println("<p>PathInfo: " + request.getPathInfo() + "</p>");
      out.println("<p>Remote Address: " + request.getRemoteAddr() + "</p>");

      // Generate a random number upon each request
      out.println("<p>A Random Number: <strong>" + Math.random() + "</strong></p>");
      out.println("</body>");
      out.println("</html>");
    }
  }
}