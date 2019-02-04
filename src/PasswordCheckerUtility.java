public class PasswordCheckerUtility {
	
	/**
	 * Check a given password to determine validity
	 * @param password the String to be examined
	 * @return True if valid, False if invalid
	 */
	public boolean isValidPassword(String password) {
		if (password.length() > 6) {
			if (containsUpperCaseLetter(password)) {
				if (containsLowerCaseLetter(password)) {
					if (containsNumber(password)) {
						if (!(hasDoubles(password))) {
							return true;
						}
						else throw new InvalidSequenceException();
					}
					else throw new NoDigitException();
				}
				else throw new NoLowerAlphaException();
			}
			else throw new NoUpperAlphaException();
		}
		else throw new LengthException();
	}
	
	/**
	 * Examine a given password to see if it contains any digits
	 * @param password the String to be examined
	 * @return True if there is at least one digit present, False
	 * if there are no digits
	 */
	public boolean containsNumber(String password) {
		char[] passwordChars = password.toCharArray();
		
		for (char character : passwordChars) {
			if (Character.isDigit(character)) {
				 return true; //returns true on the first found digit
			}
		}
		
		return false; //returns false if no digit is found
	}
	
	/**
	 * Examine a given password to see if it contains any upper case letters
	 * @param password the String to be examined
	 * @return True if there is at least one upper case letter present, False
	 * if there are none found
	 */
	public boolean containsUpperCaseLetter(String password) {
		char[] passwordChars = password.toCharArray();
		
		for(char character : passwordChars) {
			if (Character.isUpperCase(character)) {
				return true; //returns true on the first found upper case letter
			}
		}
		
		return false; //returns false if no upper case letter is found
	}
	
	/**
	 * Examine a given password to see if it contains any lower case letters
	 * @param password the String to be examined
	 * @return True if there is at least one lower case letter present, False
	 * if there are none found
	 */
	public boolean containsLowerCaseLetter(String password) {
		char[] passwordChars = password.toCharArray();
		
		for(char character : passwordChars) {
			if (Character.isLowerCase(character)) {
				return true; //returns true on the first found lower case letter
			}
		}
		
		return false; //returns false if no lower case letter is found
	}
	
	/**
	 * Examine a given password to see if there are any identical
	 * characeters in sequence
	 * @param password The String to be examined
	 * @return True if a matched pair is found, False if none found
	 */
	public boolean hasDoubles(String password) {
		char[] passwordChars = password.toCharArray();
		
		//Return true if any character is identical to the following character
		for(int i = 0; i < passwordChars.length - 1; i++) {
			if (passwordChars[i] == passwordChars[i+1]) {
				return true;
			}
		}
		
		return false; //returns false if no doubles are found
	}
	
	@SuppressWarnings("serial")
	public class LengthException extends RuntimeException {
		public LengthException() {
			super("The password must be at least 6 characters long");
		}
		
		public LengthException(String message) {
			super(message);
		}
		
	}
	
	@SuppressWarnings("serial")
	public class NoUpperAlphaException extends RuntimeException {
		public NoUpperAlphaException() {
			super("The password must contain at least one uppercase " +
					"alphabetic character");
		}
		
		public NoUpperAlphaException(String message) {
			super(message);
		}
	}
	
	@SuppressWarnings("serial")
	public class NoLowerAlphaException extends RuntimeException {
		public NoLowerAlphaException() {
			super("The password must contain at least one lowercase " +
					"alphabetic character");
		}
		
		public NoLowerAlphaException(String message) {
			super(message);
		}
	}
	
	@SuppressWarnings("serial")
	public class NoDigitException extends RuntimeException {
		public NoDigitException() {
			super("The password must contain at least one digit");
		}
		
		public NoDigitException(String message) {
			super(message);
		}
	}
	
	@SuppressWarnings("serial")
	public class InvalidSequenceException extends RuntimeException {
		public InvalidSequenceException() {
			super("The password cannot contain more " +
					"than two of the same character in " +
					"sequence");
		}
		
		public InvalidSequenceException(String message) {
			super(message);
		}
	}
	
}
