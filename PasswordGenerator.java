import java.security.SecureRandom;
import java.util.Random;

public class PasswordGenerator {
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String OTHER_CHAR = "!@#$%^&*()_+-=[]{}|;':,.<>?";

    private static final String PASSWORD_CHARS = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;

    private static SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        int length = 12;
        System.out.println("Generated Password: " + generatePassword(length));
    }

    public static String generatePassword(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Password length must be greater than 0.");
        }

        StringBuilder password = new StringBuilder(length);
        Random random = new Random();

        password.append(randomChar(CHAR_LOWER));
        password.append(randomChar(CHAR_UPPER));
        password.append(randomChar(NUMBER));
        password.append(randomChar(OTHER_CHAR));

        for (int i = 4; i < length; i++) {
            password.append(PASSWORD_CHARS.charAt(random.nextInt(PASSWORD_CHARS.length())));
        }

        String shuffledPassword = shufflePassword(password.toString());

        return shuffledPassword;
    }

    private static char randomChar(String charSet) {
        int randomIndex = random.nextInt(charSet.length());
        return charSet.charAt(randomIndex);
    }

    private static String shufflePassword(String password) {
        char[] passwordChars = password.toCharArray();
        for (int i = 0; i < passwordChars.length; i++) {
            int randomIndex = random.nextInt(passwordChars.length);
            char temp = passwordChars[i];
            passwordChars[i] = passwordChars[randomIndex];
            passwordChars[randomIndex] = temp;
        }
        return new String(passwordChars);
    }
}
