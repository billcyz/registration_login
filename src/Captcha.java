
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;

public class Captcha extends HttpServlet {
	
	CaptchaService cap = new CaptchaService();
	
	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("image/png");
		
//		File image = new File("saved_0.png");
//		BufferedImage bi = ImageIO.read(image);
		
		String a = cap.genCaptcha(4);
		BufferedImage bi = cap.genCaptchaImg(a);
		
		OutputStream out = response.getOutputStream();
		ImageIO.write(bi, "png", out);
		out.close();
	}
}
