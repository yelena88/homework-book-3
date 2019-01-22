package ex8;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FirstServlet extends HttpServlet {
  public static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>FirstServlet</title></head>");
    out.println("<body><h1>This is First Servlet</h1>");
    out.println("</body></html>");
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
  doGet(request, response);
  }


}
