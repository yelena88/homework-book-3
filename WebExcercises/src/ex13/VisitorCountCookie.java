package ex13;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/visitorCountCookie")
public class VisitorCountCookie extends HttpServlet {
  private int visitorCount = 1;
  private static String cookieValue = null;

  @Override
  public void init() throws ServletException {
    super.init();

  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
          throws IOException, ServletException {
    // Set the response message's MIME type
    response.setContentType("text/html;charset=UTF-8");

    // Setup our cookie
    cookieValue = getCookieValue(request);

    checkCookie(cookieValue, response);

    // Write the response message, in an HTML page
    try (PrintWriter out = response.getWriter()) {
      out.println("<!DOCTYPE html>");
      out.println("<html><head>");
      out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
      out.println("<title>Visitor count#" + visitorCount + "</title></head>");
      out.println("<body>");
      out.println("<h1>Visitor count to this site #" + visitorCount + "</h1>");
      out.println("</body>");
      out.println("</html>");
    }
  }

  private String getCookieValue(HttpServletRequest request) {
    Cookie[] myCookies = request.getCookies();
    String cookieValue = null;
    String cookieName = "counter";

    if (myCookies != null) {
      for (Cookie cookie : myCookies) {
        if (cookieName.equals(cookie.getName())) {
          cookieValue = cookie.getValue();
          break;
        }
      }
    }

    return cookieValue;
  }

  private void checkCookie(String strValue, HttpServletResponse response) {
    if (cookieValue == null) {
      Cookie myCookie = new Cookie("counter", String.valueOf(cookieValue));
      myCookie.setMaxAge(24*60*60);
      response.addCookie(myCookie);
      visitorCount++;
    }
  }

  @Override
  public void destroy() {
    super.destroy();
    // Write our counter to the file upon closing our servlet

  }
}
