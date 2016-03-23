package ch.heigvd.res.caesar.protocol;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class CaesarFilterOutputStream extends FilterOutputStream {

  public CaesarFilterOutputStream(OutputStream out) {
    super(out);
  }

  public void write(String str) throws IOException {

    // generate and write the random delta, between 1 and 25
    int delta = 1 + (int)(Math.random() * ((25 - 1) + 1));
    super.write(ByteBuffer.allocate(4).putInt(delta).array()); // int to byte[]

    // write string length
    super.write(ByteBuffer.allocate(4).putInt(str.length()).array()); // int to byte[]

    // transform and write each characters
    super.write(Caesar.cipher(str, delta).getBytes());
  }
}
