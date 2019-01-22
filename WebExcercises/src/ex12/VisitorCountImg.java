package ex12;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class VisitorCountImg extends HttpServlet {
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
    performAction(request, response);
  }

  private void performAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
    visitorCount++;
    response.setContentType("image/jpeg");
    BufferedImage image = new BufferedImage(250, 45, BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics = image.createGraphics();
    graphics.setFont(new Font("Verdana", Font.BOLD, 24));
    graphics.setColor(Color.ORANGE);
    graphics.drawString("Hello Visitor #" + visitorCount, 15, 30);
    ServletOutputStream out = response.getOutputStream();
    ImageIO.write(image, "jpeg", out);
    out.close();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    performAction(req, resp);
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
