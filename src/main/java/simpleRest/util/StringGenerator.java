package simpleRest.util;

public class StringGenerator {

    private final static char[] characters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
        'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C',
        'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
        'V', 'W', 'X', 'Y', 'Z'};

    public static String getRandomString() {

        char[] charsForResult = new char[15];
        for (int i = 0; i < charsForResult.length; i++) {
            int ch = (int) (Math.random() * 52);
            charsForResult[i] = characters[ch];
        }
        String result = new String(charsForResult);
        return result;
    }
}
