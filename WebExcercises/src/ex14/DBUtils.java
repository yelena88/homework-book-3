package ex14;

import java.sql.*;

public class DBUtils {
  static Connection getDBConnection(String driver, String url, String user, String password) throws ClassNotFoundException, SQLException {
    Class.forName(driver);
    return DriverManager.getConnection(url, user, password);
  }

  public static void closeDBConnection(Connection connection, Statement statement) {
    try {
      if (connection != null) {
        connection.close();
      }
      if (statement != null) {
        statement.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  static void closeDBConnection(Connection connection, Statement statement, ResultSet resultSet) {
    try {
      if (connection != null) {
        connection.close();
      }
      if (statement != null) {
        statement.close();
      }
      if (resultSet != null) {
        resultSet.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}