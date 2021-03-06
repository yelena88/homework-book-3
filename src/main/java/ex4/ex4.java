package ex4;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
   Задание 4
   Напиши программу получающую данные о расходе в качестве исходных параметров main,
   и добавляющую этот расход в базу, после чего выводящую таблицу расходов на экран
*/

public class ex4 {
  // == fields ==
  private static final String dbURL = "jdbc:mysql://localhost:3306/ListExpenses";
  private static final String USERNAME = "dev";
  private static final String PW = "r0man88";

  public static void main(String[] args) {
//    /* Loading class `com.mysql.jdbc.Driver'. This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'.
//     * The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.
//     */
//    try {
//      Class.forName("com.mysql.jdbc.Driver");
//    }catch (ClassNotFoundException cnfe) {
//      System.out.println("Error loading driver: " + cnfe);
//    }

    // шаг 1 & 2 - получить данные о расходе из параметра args main()
    addToExpenses(args);

    // шаг 3 - напечатать выводящую таблицу
    printExpenses();

  }

  private static void addToExpenses(String[] args) {
    String date = null;
    String receiverId = null;
    String value = null;
    try {
      date = args[0];
      receiverId = args[1];
      value = args[2];
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Please execute the program with all argument in the form of: ex4 (date) (receiver) (value)");
    }

    if (date == null || receiverId == null || value == null)
      return;

    String query = "INSERT INTO expenses VALUES (NULL," + date + "," + receiverId + "," + value + ")";

    try (Statement statement = DriverManager.getConnection(dbURL, USERNAME, PW).createStatement()) {
      statement.executeUpdate(query);

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void printExpenses() {
    String query = "SELECT * FROM expenses";

    try (Statement statement = DriverManager.getConnection(dbURL, USERNAME, PW).createStatement()) {
      ResultSet result = statement.executeQuery(query);

      while (result.next()) {
        System.out.println(result.getString(1) + " " +
                result.getString(2) + " " +
                result.getString(3) + " " +
                result.getString(4)
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
