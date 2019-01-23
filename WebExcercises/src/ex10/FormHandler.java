package ex10;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/formHandler")
public class FormHandler extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    performAction(request, response);
  }

  private void performAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
    request.setCharacterEncoding("utf-8");
    String userName = request.getParameter("username");
    String phoneNumber = request.getParameter("phoneN");
    String email = request.getParameter("e-mail");
    response.setCharacterEncoding("utf-8");
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html><html><head lang=\"ru\"><meta charset=\"UTF-8\">");
    if (userName.isEmpty() || (phoneNumber.isEmpty() && email.isEmpty())) {
      printErrorMessageWithHRef(out, request);
    } else {
      printValidDataAnswer(out, userName, phoneNumber, email);
    }
    out.println("</body></html>");
    out.close();
  }

  private void printErrorMessageWithHRef(PrintWriter out, HttpServletRequest request) {
    out.println("<script type=\"text/javascript\">alert(\"Кроме поля \'Имя\' заполните еще хотя бы одно!\");</script>");
    out.println("<title>Недостаточно данных</title></head>");
    out.print("<body><h1>Введенных данных недостаточно!</h1><h2><a href=\"");
    out.print(request.getHeader("referer"));
    out.print("\">Перейдите сюда и заполните обязательные поля формы</a></h2>");
    out.print("<p>Либо вернитесь браузером на предыдущую страницу (в этом случае в полях будут ранее введенные данные)</p>");
  }

  private void printValidDataAnswer(PrintWriter out, String userName, String phoneNumber, String email) {
    out.println("<title>Заполнение формы успешно завершено</title></head>");
    out.print("<body><h1>Введенные данные успешно приняты:</h1>");
    out.print("<p>Имя пользователя: " + userName + "</p>");
    out.print("<p>Номер телефона: " + (phoneNumber.isEmpty() ? "не указан" : phoneNumber) + "</p>");
    out.print("<p>Адрес электронной почты: " + (email.isEmpty() ? "не указан" : email) + "</p>");
  }
}
