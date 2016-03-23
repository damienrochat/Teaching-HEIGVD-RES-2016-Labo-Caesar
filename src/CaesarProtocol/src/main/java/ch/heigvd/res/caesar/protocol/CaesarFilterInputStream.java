package ch.heigvd.res.caesar.protocol;

import ch.heigvd.res.caesar.server.CaesarServer;
import sun.misc.IOUtils;

import java.io.DataInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.logging.Logger;

/**
 * Created by chlablak on 23.03.2016.
 */
public class CaesarFilterInputStream extends DataInputStream {

    private static final Logger LOG = Logger.getLogger(CaesarServer.class.getName());

    /**
     * Creates a <code>FilterInputStream</code>
     * by assigning the  argument <code>in</code>
     * to the field <code>this.in</code> so as
     * to remember it for later use.
     *
     * @param in the underlying input stream, or <code>null</code> if
     *           this instance is to be created without an underlying stream.
     */
    public CaesarFilterInputStream(InputStream in) {
        super(in);
    }

    /**
     *
     * @return
     * @throws IOException
     */
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
