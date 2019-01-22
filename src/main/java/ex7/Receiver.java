package ex7;

public class Receiver {

  //  == fields ==
  private int num;
  private String name;

  // == getters and setters ==
  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Receiver {" + "num=" + num + ", name='" + name + '\'' + '}';
  }
}
