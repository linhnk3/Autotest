package generator;

import java.util.Random;

public class FunctionRandom {
    public static String generateCharacter(int lengh) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String s = "";
        Random random = new Random();
        for (int i = 0; i < lengh; i++) {
            char c = alphabet.charAt(random.nextInt(26));
            s += c;
        }

        return s;

    }

    public static String generateIdNumber(int lengh) {
        String alphabet = "0123456789";
        String s = "";
        Random random = new Random();
        for (int i = 0; i < lengh; i++) {
            char c = alphabet.charAt(random.nextInt(9));
            s += c;
        }
        return s;

    }
}
