package ch.heigvd.res.caesar.protocol;

/**
 * Created by chlablak on 23.03.2016.
 */
public class Caesar {
    public static String cipher(String message, int delta) {
        String result = new String();
        for(int i = 0; i < message.length(); ++i) {
            int c = message.charAt(i);
            if ('a' <= c && c <= 'z')
                c = ((c - 'a' + delta) % 26) + 'a';
            else if ('A' <= c && c <= 'Z')
                c = ((c - 'A' + delta) % 26) + 'A';
            result = result + c;
        }
        return result;
    }
}
