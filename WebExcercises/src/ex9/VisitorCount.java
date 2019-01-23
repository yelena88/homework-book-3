package ex9;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/*
Задание 9
Сделайте простейший сервлет, подсчитывающий количество посещений. Т.е. при каждом обращении к сервлету, следует
увеличивать счетчик посещений, и выводить его значение на страницу. Количество следует хранить в файле.
 */
@WebServlet("/visitorCount")
public class VisitorCount extends HttpServlet {
  private static final String VISITOR_COUNT_FILE =
          "C:\\Users\\User\\IdeaProjects\\HomeworkBook3\\WebExcercises\\src\\ex9\\visitor.dat";
  private int visitorCount = 0;

  @Override
  public void init() throws ServletException {
    super.init();
    File counterFile = new File(VISITOR_COUNT_FILE);
    if (!counterFile.exists()) {
      try {
        counterFile.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    try (DataInputStream dataInputStream =
                 new DataInputStream(new BufferedInputStream(new FileInputStream(VISITOR_COUNT_FILE)))) {
      if (counterFile.length() != 0) {
        visitorCount = dataInputStream.readInt();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
          throws IOException, ServletException {
    // Set the response message's MIME type
    response.setContentType("text/html;charset=UTF-8");

    // Increase our visitor count on every visit
    visitorCount++;

    // Write the response message, in an HTML page
    try (PrintWriter out = response.getWriter()) {
      out.println("<!DOCTYPE html>");
      out.println("<html><head>");
      out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
      out.println("<title>Hello Visitor #" + visitorCount + "</title></head>");
      out.println("<body>");
      out.println("<h1>Hello Visitor #" + visitorCount + "</h1>");
      out.println("</body>");
      out.println("</html>");
    }
  }

  @Override
  public void destroy() {
    super.destroy();
    // Write our counter to the file upon closing our servlet
    try (
            DataOutputStream dataOutputStream =
                    new DataOutputStream(new BufferedOutputStream(new FileOutputStream(VISITOR_COUNT_FILE)))
    ) {
      dataOutputStream.writeInt(visitorCount);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
