package pti.sb_squash_mvc.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class PasswordGeneratorService {

	private final String symbols;
	private final String capLetters;
	private final String smallLetters;
	private final String numbers;
	
	
	public PasswordGeneratorService() {
		
		symbols = "-/.^&*_!@%=+>)";
		capLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		smallLetters = "abcdefghijklmnopqrstuvwxyz";
		numbers = "0123456789";
	}
	

	
	public String generatePassword() {
		
		Random random = new Random();
		
		int passwordLength = 8;
		String allCharacters = symbols + capLetters + smallLetters + numbers;
	
		String generatedPassword = ""; 
		
		for(int i = 0; i < passwordLength; i++) {
			
			generatedPassword += allCharacters.charAt(random.nextInt( allCharacters.length() ));
		}

		
		return generatedPassword;
	}

}
