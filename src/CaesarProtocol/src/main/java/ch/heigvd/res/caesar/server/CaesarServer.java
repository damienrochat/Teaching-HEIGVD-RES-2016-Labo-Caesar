/**
 * @file CaesarServer.java
 * @author Patrick Champion, Damien Rochat
 */

package ch.heigvd.res.caesar.server;

import ch.heigvd.res.caesar.protocol.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CaesarServer {

  private static final Logger LOG = Logger.getLogger(CaesarServer.class.getName());

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tH:%1$tM:%1$tS::%1$tL] Server > %5$s%n");
    LOG.info("Caesar server starting...");
    LOG.info("Protocol port: " + Protocol.PORT);

    // create the listener
    ServerSocket serverSocket;
    try {
      serverSocket = new ServerSocket(Protocol.PORT);
    } catch(IOException ex) {
      LOG.log(Level.SEVERE, ex.getMessage(), ex);
      return;
    }

    // accept incoming client
    while(true) {
      LOG.info("Waiting for a new client");
      try {
        Socket client = serverSocket.accept();
        LOG.info("A new client has arrived...");
        new Thread(new ServantWorker(client)).start();
      } catch(IOException ex) {
        LOG.log(Level.SEVERE, ex.getMessage(), ex);
      }
    }
  }
  
}
