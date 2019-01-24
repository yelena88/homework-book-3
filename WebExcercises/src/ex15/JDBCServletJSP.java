package ex15;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static ex14.DBUtils.getDBConnection;

/**
 * Ex. 15
 * Добавить к созданному в предыдущем задании сервлету JSP и заменить сервлет таким образом,
 * чтобы работа с базой выполнялась сервлетом, а отображение результанов на странице - JSP.
 */

public class JDBCServletJSP extends HttpServlet {
  private static String dbUrl;
  private static String jdbcDriver;
  private static String dbUsername;
  private static String dbPassword;
  private static String sqlShowQuery;

  @Override
  public void init() throws ServletException {
    ServletContext servletContext = getServletContext();

    dbUrl = servletContext.getInitParameter("dbUrl");
    jdbcDriver = servletContext.getInitParameter("jdbcDriver");
    dbUsername = servletContext.getInitParameter("dbUsername");
    dbPassword = servletContext.getInitParameter("dbPassword");
    sqlShowQuery = servletContext.getInitParameter("sqlShowQuery");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Get our result using our query to get all the data for our JSP page
    try (Connection connection = getDBConnection(jdbcDriver, dbUrl, dbUsername, dbPassword);
         Statement statement = connection.createStatement();
         ResultSet result = statement.executeQuery(sqlShowQuery);) {

      List<Expense> resList = new ArrayList<>();
      Expense expense = null;

      while (result.next()) {
        expense = new Expense();
        expense.setNum(result.getInt(1));
        expense.setPaydate(result.getString(2));
        expense.setValue(result.getInt(3));
        expense.setReceiver(result.getString(4));
        resList.add(expense);
      }

      // Store our query results in the request
      request.setAttribute("result", resList);

      // Send to our JSP page
      request.getRequestDispatcher("queryresult.jsp").forward(request, response);

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doPost(req, resp);
    doGet(req, resp);
  }
}
