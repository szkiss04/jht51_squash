package pti.sb_squash_mvc.util;

import java.util.Random;

public class PasswordGenerator {

	public static String generate() {
		
		Random random = new Random();
		
		char[] generatedPassword = new char[8];
				
		for(int i = 0; i < generatedPassword.length; i++) {
			
			generatedPassword[i] = (char)(random.nextInt(93) + 33); // here it is safe to cast, we're gonna defe
		}

		return String.valueOf(generatedPassword);
	}

}
