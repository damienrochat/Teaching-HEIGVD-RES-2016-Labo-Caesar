/**
 * @file CaesarFilterInputStream.java
 * @author Patrick Champion, Damien Rochat
 */

package ch.heigvd.res.caesar.protocol;

import ch.heigvd.res.caesar.server.CaesarServer;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

public class CaesarFilterInputStream extends DataInputStream {

    private static final Logger LOG = Logger.getLogger(CaesarServer.class.getName());

    public CaesarFilterInputStream(InputStream in) {
        super(in);
    }

    public String readCipher() throws IOException {

        // read delta and length
        int delta = readInt();
        int length = readInt();
        LOG.info("New message, delta=" + delta + ", length=" + length);

        // read the message
        byte[] b = new byte[length];
        super.readFully(b);
        return Caesar.cipher(new String(b), -delta);
    }
}
