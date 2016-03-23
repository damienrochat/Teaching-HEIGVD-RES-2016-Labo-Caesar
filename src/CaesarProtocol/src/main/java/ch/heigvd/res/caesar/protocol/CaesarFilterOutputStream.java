package ch.heigvd.res.caesar.protocol;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CaesarFilterOutputStream extends FilterOutputStream {

  private static final Logger LOG = Logger.getLogger(CaesarFilterOutputStream.class.getName());

  public CaesarFilterOutputStream(OutputStream out) {
    super(out);
  }

  public void write(String str) throws IOException {

    LOG.log(Level.INFO, "Output string : " + str);

    // generate and write the random delta, between 1 and 25
    int delta = 1 + (int)(Math.random() * ((25 - 1) + 1));
    super.write(ByteBuffer.allocate(4).putInt(delta).array()); // int to byte[]

    // write string length
    super.write(ByteBuffer.allocate(4).putInt(str.length()).array()); // int to byte[]

    // transform and write each characters
    str = Caesar.cipher(str, delta);
    super.write(str.getBytes());

    LOG.log(Level.INFO, "Output crypted string : " + str);
  }
}
