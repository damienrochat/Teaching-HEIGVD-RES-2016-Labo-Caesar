/**
 * @file CaesarFilterOutputStream.java
 * @author Patrick Champion, Damien Rochat
 */

package ch.heigvd.res.caesar.protocol;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CaesarFilterOutputStream extends DataOutputStream {

  private static final Logger LOG = Logger.getLogger(CaesarFilterOutputStream.class.getName());

  public CaesarFilterOutputStream(OutputStream out) {
    super(out);
  }

  public void write(String str) throws IOException {

    LOG.log(Level.INFO, "Output string : " + str);

    // generate and write the random delta, between 1 and 25
    int delta = 1 + (int)(Math.random() * ((25 - 1) + 1));
    super.writeInt(delta);

    // write string length
    super.writeInt(str.length());

    // transform and write each characters
    str = Caesar.cipher(str, delta);
    super.writeBytes(str);

    LOG.log(Level.INFO, "Output crypted string, delta=" + delta + ": " + str);
  }
}
