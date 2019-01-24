package ex16_17;

import ex15.Expense;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static ex14.DBUtils.getDBConnection;

public class AddExpenses extends HttpServlet {
  private static String dbUrl;
  private static String jdbcDriver;
  private static String dbUsername;
  private static String dbPassword;
  private static String sqlAddQuery;

  @Override
  public void init() throws ServletException {
    ServletContext servletContext = getServletContext();

    dbUrl = servletContext.getInitParameter("dbUrl");
    jdbcDriver = servletContext.getInitParameter("jdbcDriver");
    dbUsername = servletContext.getInitParameter("dbUsername");
    dbPassword = servletContext.getInitParameter("dbPassword");
    sqlAddQuery = servletContext.getInitParameter("sqlAddQuery");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String message = "";
    Expense expense = new Expense();

    expense.setPaydate(request.getParameter("paydate"));
    expense.setReceiver(Integer.valueOf(request.getParameter("receiver")));
    expense.setValue(Double.valueOf(request.getParameter("value")));

    try (
            Connection connection = getDBConnection(jdbcDriver, dbUrl, dbUsername, dbPassword);
            PreparedStatement pStatement = createPrepStatement(connection, expense)) {

      int result = pStatement.executeUpdate();

      if (result > 0) {
        message = "New expense added!";
      } else {
        message = "There was an issue adding the new expense";
      }

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      message += e.toString();
    }

    request.setAttribute("message", message);
    request.getRequestDispatcher("result.jsp").forward(request, response);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("addExpenses.jsp").forward(request, response);
  }

  private PreparedStatement createPrepStatement(Connection con, Expense expense) throws SQLException {
    PreparedStatement pStatement = con.prepareStatement(sqlAddQuery);
    pStatement.setInt(1, 0);
    pStatement.setDate(2, Date.valueOf(expense.getPaydate()));
    pStatement.setInt(3, expense.getReceiverInt());
    pStatement.setDouble(4, expense.getValue());

    return pStatement;
  }
}
