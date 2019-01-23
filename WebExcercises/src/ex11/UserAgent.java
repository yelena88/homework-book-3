package ex11;

/* Задание  11. Сделать сервлет, который по содержимому User-Agent будет определять
с помощью какого браузера зашел пользователь, и выводить сообщение вида:
Приветствую пользователя Firefox. */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/userAgent")
public class UserAgent extends HttpServlet {
  private static final String[] KNOWN_BROWSERS = {"Chrome", "Firefox", "Opera", "Yandex", "Internet Explorer", "IE", "Safari", "Netscape", "Konqueror", "SeaMonkey", "Minefield", "Maxthon", "K-Meleon", "Iceweasel", "Camino", "Edge"};
  private static final String GREETING_PAGE_PREFIX = "<!DOCTYPE html><html><head lang=\"ru\"><meta charset=\"UTF-8\"><title>Приветствие</title></head><body><h1>Приветствую пользователя ";
  private static final String GREETING_PAGE_SUFFIX = "!</h1></body></html>";

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    performAction(request, response);
  }

  private void performAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String userAgentHeader = request.getHeader("User-Agent");
    response.setCharacterEncoding("utf-8");
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.print(GREETING_PAGE_PREFIX);
    for (String currentBrowser : KNOWN_BROWSERS) {
      if (userAgentHeader.contains(currentBrowser)) {
        out.print(currentBrowser + "/");
      }
    }
    out.print(GREETING_PAGE_SUFFIX);
    out.close();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    performAction(request, response);
  }
}
