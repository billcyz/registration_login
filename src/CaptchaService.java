
// web link: http://blog.csdn.net/clementad/article/details/48788361

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

public class CaptchaService {

	static char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H','I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	public static String genCaptcha(int count) {
		StringBuilder captcha = new StringBuilder();
		
		for(int i = 0; i < count; i++) {
			// select one random character or number
			char c = chars[ThreadLocalRandom.current().nextInt(chars.length)];
			
//			char c = chars[new Random().nextInt(chars.length)];
			
			captcha.append(c);
		}
		
		return captcha.toString();
	}
	
	public static BufferedImage genCaptchaImg(String captcha) {
		ThreadLocalRandom r = ThreadLocalRandom.current();
		
		int count = captcha.length();
		int fontSize = 65; // font size
		int fontMargin = fontSize / 4; // space between each character
		int width = (fontSize + fontMargin) * count + fontMargin; // figure width
		int height = (int) (fontSize * 1.2); // figure height
		
		int avgWidth = width / count; // average width for each character
		int maxDegree = 26; // max rotate degree
		
		System.out.println("image width is: " + width);
		System.out.println("image height is: " + height);
		System.out.println("image avgWidth is: " + avgWidth);
		
		
		Color bkColor = Color.WHITE; // background color
		Color[] captchaColor = {Color.MAGENTA, Color.BLACK, Color.BLUE, 
				Color.GREEN, Color.ORANGE, Color.PINK, Color.GRAY}; // color for captcha
		
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
	    Graphics2D g = image.createGraphics();
	    
	    // captcha background
	    g.setColor(bkColor);
	    g.fillRect(0, 0, width, height);
	    
	    // captcha outline
//	    g.setColor(Color.BLACK);
//	    g.drawRect(0, 0, width - 1, height - 1);
	    
	    // Draw captcha
	    Font font = new Font(Font.MONOSPACED, Font.ITALIC|Font.BOLD, fontSize);
	    g.setFont(font);
	    
	    for(int i = 0; i < count; i++) {
	    	char c = captcha.charAt(i);
	    	g.setColor(captchaColor[r.nextInt(captchaColor.length)]); // randomly select a color
	    	
	    	int degree = r.nextInt(-maxDegree, maxDegree + 1); // randomly select rotate degree
	    	double offsetFactor = 1 - (Math.abs(degree) / (maxDegree + 1.0));
	    	
	    	g.rotate(degree * Math.PI / 180);
	    	int x = (int) (fontMargin + r.nextInt(avgWidth - fontSize) * offsetFactor);
	    	int y = (int) (fontSize + r.nextInt(height - fontSize) * offsetFactor);
	    	
	    	g.drawString(String.valueOf(c), x, y); // (x, y) is the location of down-left corner of String
	    	
	    	g.rotate(-degree * Math.PI / 180);
	    	g.translate(avgWidth, 0);
	    	
	    }
	    
	    
	    // Draw lines
	    for(int i = 0; i < 30; i++) {
	    	drowLine(r, g, width, height);
	    }
	    
//	    for(int i = 0; i < 155; i++) {
//	    	g.setColor(Color.BLACK);
//	    	int x = r.nextInt(width - 1);
//	    	int y = r.nextInt(height - 1);
//	    	int x1 = r.nextInt(6) + 1;
//	    	int y1 = r.nextInt(12) + 1;
//	    	g.drawLine(x, y, x + x1, y + y1);
//	    	setPixel(g, x, y, Color.BLACK);
//	    }
//	    
//	    for(int i = 0; i < 70; i++) {
//	    	int x = r.nextInt(width - 1);
//	    	int y = r.nextInt(height - 1);
//	    	int x1 = r.nextInt(12) + 1;
//	    	int y1 = r.nextInt(6) + 1;
//	    	g.drawLine(x, y, x - x1, y - y1);
//	    	setPixel(g, x, y, Color.BLACK);
//	    }
	    
	    // Create noise
	    
	    
	    g.dispose();
	    
	    return image;
	}
	
	
	private static void setPixel(Graphics2D g, int x, int y, Color color) {
		g.setColor(color);
		g.fillRect(x, y, 1, 1);
	}
	
	private static void drowLine(ThreadLocalRandom random, Graphics2D g, int width, int height) {
		
		System.out.println("Draw Line");
		
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		int xl = random.nextInt(width);
		int yl = random.nextInt(height);
		g.drawLine(x, y, x + xl, y + yl);
		setPixel(g, x, y, Color.BLACK);
	}
	
	
	public static void main(String[] args) {
		
		for(int i = 0; i < 20; i++) {
			String a = genCaptcha(4);
			System.out.println(genCaptchaImg(a));
			
			try {
				BufferedImage bi = genCaptchaImg(a);
				File outputFile = new File("saved_" + i + ".jpeg");
				ImageIO.write(bi, "jpeg", outputFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
