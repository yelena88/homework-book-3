package ex16_17;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static ex14.DBUtils.getDBConnection;


public class DeleteExpenses extends HttpServlet {
  private static String dbUrl;
  private static String jdbcDriver;
  private static String dbUsername;
  private static String dbPassword;
  private static String sqlDeleteQuery;

  @Override
  public void init() throws ServletException {
    ServletContext servletContext = getServletContext();

    dbUrl = servletContext.getInitParameter("dbUrl");
    jdbcDriver = servletContext.getInitParameter("jdbcDriver");
    dbUsername = servletContext.getInitParameter("dbUsername");
    dbPassword = servletContext.getInitParameter("dbPassword");
    sqlDeleteQuery = servletContext.getInitParameter("sqlDeleteQuery");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try (Connection connection = getDBConnection(jdbcDriver, dbUrl, dbUsername, dbPassword);
         Statement statement = connection.createStatement()) {

      int result = statement.executeUpdate(sqlDeleteQuery + request.getParameter("num") + ";");
      String message = "";

      try {
        if (result > 0) {
          message = "Expense has been deleted!";
        } else {
          message = "Expense was not deleted!";
        }
      } catch (Exception e) {
        e.printStackTrace();
      }

      request.setAttribute("message", message);
      request.getRequestDispatcher("result.jsp").forward(request, response);

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("deleteExpenses.jsp").forward(request, response);
  }
}
