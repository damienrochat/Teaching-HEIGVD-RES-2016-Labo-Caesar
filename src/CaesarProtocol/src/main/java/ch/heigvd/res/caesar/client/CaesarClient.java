/**
 * @file CaesarClient.java
 * @author Patrick Champion, Damien Rochat
 */

package ch.heigvd.res.caesar.client;

import ch.heigvd.res.caesar.protocol.*;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CaesarClient {

  private static final Logger LOG = Logger.getLogger(CaesarClient.class.getName());

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tH:%1$tM:%1$tS::%1$tL] Client > %5$s%n");
    LOG.info("Caesar client starting...");
    LOG.info("Protocol port: " + Protocol.PORT);

    // for reading from the console
    Scanner cin = new Scanner(System.in);

    try {
      // connect to the server
      LOG.info("Attempt connexion...");
      Socket server = new Socket("localhost", Protocol.PORT);

      // get streams from/to server
      CaesarFilterInputStream fromServer = new CaesarFilterInputStream(server.getInputStream());
      CaesarFilterOutputStream toServer = new CaesarFilterOutputStream(server.getOutputStream());

      while(true) {
        // wait a message and send it
        System.out.print("Message (keep empty to quit): ");
        String message = cin.nextLine();
        toServer.write(message);

        // quit with empty message
        if(message.isEmpty())
          break;

        // wait for the response
        String response = fromServer.readCipher();
        LOG.info("Server response: " + response);
      }

      server.close();
      LOG.info("Connection closed");

    } catch (IOException ex) {
      LOG.log(Level.SEVERE, ex.getMessage(), ex);
      return;
    }
  }
  
}
