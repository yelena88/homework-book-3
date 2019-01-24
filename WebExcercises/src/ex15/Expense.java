package ex15;

public class Expense {
  // == fields ==
  private int num;
  private String paydate;
  private String receiver;
  private double value;
  private int receiverInt;

  // == getters and setters ==
  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public String getPaydate() {
    return paydate;
  }

  public void setPaydate(String paydate) {
    this.paydate = paydate;
  }

  public String getReceiver() {
    return receiver;
  }

  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "Expense { num=" + num + ", paydate='" + paydate + '\'' + ", receiver=" + receiver +
            ", value=" + value + '}';
  }

  public void setReceiver(int receiver) {
    this.receiverInt = receiver;
  }

  public int getReceiverInt() {
    return receiverInt;
  }
}
