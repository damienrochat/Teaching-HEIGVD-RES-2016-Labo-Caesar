/**
 * @file Caesar.java
 * @author Patrick Champion, Damien Rochat
 */

package ch.heigvd.res.caesar.protocol;

public class Caesar {
    public static String cipher(String message, int delta) {

        // adapte delta (for crypt/decrypg)
        while(delta < 0)
            delta += 26;
        delta %= 26;

        String result = new String();

        // read ans convert each characters
        for(int i = 0; i < message.length(); ++i) {
            int c = message.charAt(i);
            if ('a' <= c && c <= 'z')
                c = ((c - 'a' + delta) % 26) + 'a';
            else if ('A' <= c && c <= 'Z')
                c = ((c - 'A' + delta) % 26) + 'A';
            result = result + (char) c;
        }
        return result;
    }
}
