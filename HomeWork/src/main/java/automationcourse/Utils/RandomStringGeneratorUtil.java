package automationcourse.Utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class RandomStringGeneratorUtil {

    public static String randomChars(int amountOfChars) {
        return RandomStringUtils.randomAlphabetic(amountOfChars);
    }

    public static String generateRandom9DigitNumberStr() {
        int min = 100000000;
        int max = 1000000000;
        return String.valueOf(new Random().nextInt((max - min)) + min);
    }
}
