package ex14;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import static ex14.DBUtils.*;

/**
 * Задание 14.
 * Создать сервлет, который будет отображать на странице содержимое базы платежей, из модуля 2.
 * Информацию о URL базы, имени пользователя и пароле хранить в начальных данных сервлета.
 */

public class JDBCServlet extends HttpServlet {
  private static String dbUrl;
  private static String jdbcDriver;
  private static String dbUsername;
  private static String dbPassword;
  private static String sqlShowQuery;

  @Override
  public void init() throws ServletException {
    ServletConfig servletConfig = getServletConfig();
    dbUrl = servletConfig.getInitParameter("dbUrl");
    jdbcDriver = servletConfig.getInitParameter("jdbcDriver");
    dbUsername = servletConfig.getInitParameter("dbUsername");
    dbPassword = servletConfig.getInitParameter("dbPassword");
    sqlShowQuery = servletConfig.getInitParameter("sqlShowQuery");
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Connection connection = null;
    Statement statement = null;
    ResultSet result = null;

    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>Cookies Demo</title></head>");
    out.println("<body>");
    out.println("<table align=center border=2>");
    out.println("<caption>Expenses table</caption>");

    try {
      connection = getDBConnection(jdbcDriver, dbUrl, dbUsername, dbPassword);
      statement = connection.createStatement();

      result = statement.executeQuery(sqlShowQuery);

      out.println("<th>");
      out.println("<td>Pay Date</td>");
      out.println("<td>Sum</td>");
      out.println("<td>Receiver</td>");
      out.println("</th>");

      while (result.next()) {
        out.println("<tr>");
        out.println("<td>" + result.getString(1) + "</td>");
        out.println("<td>" + result.getString(2) + "</td>");
        out.println("<td>" + result.getString(3) + "</td>");
        out.println("<td>" + result.getString(4) + "</td>");
        out.println("</tr>");
      }
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    } finally {
      closeDBConnection(connection, statement, result);
    }
    out.println("</table>");
    out.println("</body></html>");
    out.close();
  }
}
