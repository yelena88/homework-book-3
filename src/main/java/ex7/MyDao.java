package ex7;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class MyDao implements Dao {
  //== fields ==
  private JdbcTemplate jdbcTemplate;

  @Override
  public void setDataSource(DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate(dataSource);
  }

  @Override
  public int createReceiver(Receiver receiver) {
    String sqlQuery = "INSERT INTO receivers (num, name) " + "VALUES(?, ?)";
    Object[] args = new Object[]{receiver.getNum(), receiver.getName()};
    return jdbcTemplate.update(sqlQuery, args);
  }

  @Override
  public int createExpense(Expense expense) {
    String sqlQuery = "INSERT INTO expenses (num, paydate, receiver, value) " + "VALUES(?, ?, ?, ?)";
    Object[] args = new Object[]{expense.getNum(), expense.getPaydate(), expense.getReceiver(), expense.getValue()};
    return jdbcTemplate.update(sqlQuery, args);
  }

  @Override
  public Receiver getReceiver(int num) {
    String sqlQuery = "SELECT * FROM receivers WHERE num = ?";
    List<Receiver> receiverList = jdbcTemplate.query(sqlQuery, new ReceiverRowMapper(), num);
    return receiverList.get(0);
  }

  @Override
  public List<Receiver> getReceivers() {
    String sqlQuery = "SELECT * FROM receivers";
    List<Receiver> receiverList = jdbcTemplate.query(sqlQuery, new ReceiverRowMapper());
    return receiverList;
  }

  @Override
  public Expense getExpense(int num) {
    String sqlQuery = "SELECT * FROM expenses WHERE num = ?";
    List<Expense> expenseList = jdbcTemplate.query(sqlQuery, new ExpenseRowMapper(), num);
    return expenseList.get(0);
  }

  @Override
  public List<Expense> getExpenses() {
    String sqlQuery = "SELECT * FROM expenses";
    List<Expense> expenseList = jdbcTemplate.query(sqlQuery, new ExpenseRowMapper());
    return expenseList;
  }

  @Override
  public int deleteReceiver(int num) {
    String sqlQuery = "DELETE FROM receivers WHERE num = ?";
    return jdbcTemplate.update(sqlQuery, num);
  }

  @Override
  public int deleteExpense(int num) {
    String sqlQuery = "DELETE FROM expenses WHERE num = ?";
    return jdbcTemplate.update(sqlQuery, num);

  }

  @Override
  public boolean updateReceiver(Receiver receiver) {
    String sqlQuery = "UPDATE receivers SET name = ? WHERE num = ?";
    Object[] args = new Object[] {receiver.getName(), receiver.getNum()};
    return jdbcTemplate.update(sqlQuery, args) == 1;
  }

  @Override
  public boolean updateExpense(Expense expense) {
    String sqlQuery = "UPDATE expenses SET paydate = ?, receiver = ?, value = ?  WHERE num = ?";
    Object[] args = new Object[] {expense.getPaydate(), expense.getReceiver(), expense.getValue(), expense.getNum()};
    return jdbcTemplate.update(sqlQuery, args) == 1;
  }
}
