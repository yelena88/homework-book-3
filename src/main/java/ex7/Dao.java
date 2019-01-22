package ex7;

import javax.sql.DataSource;
import java.util.List;

public interface Dao {
  public void setDataSource(DataSource ds);

  //  == create a record in the receiver table ==
  public int createReceiver(Receiver receiver);

  //  == create a record in the expense table  ==
  public int createExpense(Expense expense);

  Receiver getReceiver(int num);

  List<Receiver> getReceivers();

  Expense getExpense(int num);

  List<Expense> getExpenses();

  public int deleteReceiver(int num);

  public int deleteExpense(int num);

  public boolean updateReceiver(Receiver receiver);

  public boolean updateExpense(Expense expense);

}
