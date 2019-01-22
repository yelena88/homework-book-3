package ex7;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpenseRowMapper implements RowMapper<Expense> {

  @Override
  public Expense mapRow(ResultSet resultSet, int i) throws SQLException {
    Expense expense = new Expense();
    expense.setNum(resultSet.getInt("num"));
    expense.setPaydate(resultSet.getString("paydate"));
    expense.setReceiver(resultSet.getInt("receiver"));
    expense.setValue(resultSet.getDouble("value"));

    return expense;
  }
}
