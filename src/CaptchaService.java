
// web link: http://blog.csdn.net/clementad/article/details/48788361

import java.util.Random;

public class CaptchaService {

	static char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H','I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	public static String genCaptcha(int count) {
		StringBuilder captcha = new StringBuilder();
		
		for(int i = 0; i < count; i++) {
			// select one random character or number
//			char c = chars[ThreadLocalRandom.current().nextInt(chars.length)];
			
			char c = chars[new Random().nextInt(chars.length)];
			
			captcha.append(c);
		}
		
		return captcha.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(genCaptcha(3));
	}
}
