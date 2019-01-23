package ex7;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/*
Задание 7
Создайте объект DAO на основе интерфейса приведенного выше
Также необходимо создать классы Receiver и Expense, свойства которых соответствуют
полям таблиц базы данных расходов. Поле дата в классах можно хранить в виде строки.
*/
public class Main {
  public static void main(String[] args) {

    //== Create app context ==
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-cp.xml");

    //== Load the application bean
    MyDao dao = (MyDao) applicationContext.getBean("myDao");

    System.out.println("-------------Find all receivers---------------------");
    List<Receiver> receivers= dao.getReceivers();
    for (Receiver receiver : receivers) {
      System.out.println(receiver);
    }

    System.out.println("--------------Create a new receiver ------------");
    Receiver receiver = new Receiver();
    receiver.setName("Гипермаркет Алми");
    receiver.setNum(5);
    dao.createReceiver(receiver);

    receivers = dao.getReceivers();
    for (Receiver receiver1 : receivers){
      System.out.println(receiver1);
    }

    System.out.println();

    System.out.println("-----Delete a receiver----");
    dao.deleteReceiver(4);

    receivers = dao.getReceivers();
    for (Receiver receiver2 : receivers){
      System.out.println(receiver2);
    }

    System.out.println();

    System.out.println("-----Update a receiver----");
    receiver = dao.getReceiver(5);
    receiver.setName("Гипермаркет Евроопт");
    dao.updateReceiver(receiver);

    receivers = dao.getReceivers();
    for (Receiver receiver1 : receivers){
      System.out.println(receiver1);
    }

    System.out.println();

    System.out.println("-------------Find all expenses---------------------");
    List<Expense> expenses = dao.getExpenses();
    for (Expense expense : expenses) {
      System.out.println(expense);
    }

    System.out.println();

    System.out.println("--------------Create a new expense ------------");
    Expense expense = new Expense();
    expense.setNum(9);
    expense.setPaydate("2011-05-14");
    expense.setReceiver(5);
    expense.setValue(73450);
    dao.createExpense(expense);

    expenses = dao.getExpenses();
    for(Expense expense1 : expenses){
      System.out.println(expense1);
    }

    System.out.println();

    System.out.println("-----Delete an expense----");
    dao.deleteExpense(8);

    expenses = dao.getExpenses();
    for (Expense expense2 : expenses){
      System.out.println(expense2);
    }

    System.out.println();

    System.out.println("-----Update an expense----");
    expense = dao.getExpense(4);
    expense.setPaydate("2011-05-12");
    expense.setValue(24100);
    dao.updateExpense(expense);

    expenses = dao.getExpenses();
    for (Expense expense1 : expenses){
      System.out.println(expense1);
    }

    System.out.println();

    //== Close context ==
    ((ClassPathXmlApplicationContext) applicationContext).close();
  }
}
