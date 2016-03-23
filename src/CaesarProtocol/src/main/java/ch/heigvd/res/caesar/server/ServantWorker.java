/**
 * @file ServantWorker.java
 * @author Patrick Champion, Damien Rochat
 */

package ch.heigvd.res.caesar.server;

import ch.heigvd.res.caesar.protocol.*;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServantWorker implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServantWorker.class.getName());

    // The client socket
    private Socket client;

    /**
     * Constructor
     * @param client - the client socket
     */
    public ServantWorker(Socket client) {
        this.client = client;
    }

    /**
     * Conversation with the client
     */
    @Override
    public void run() {
        try {
            // print client info
            LOG.info("Client info: " + client);

            // get streams from/to client
            CaesarFilterInputStream fromClient = new CaesarFilterInputStream(client.getInputStream());
            CaesarFilterOutputStream toClient = new CaesarFilterOutputStream(client.getOutputStream());

            while(true) {
                // wait for a message
                String message = fromClient.readCipher();

                // quit on empty message
                if(message.isEmpty())
                    break;

                LOG.info("Message from " + client + ": " + message);

                // echo the message
                toClient.write(message);
            }

            // end, close socket
            LOG.info("Client quit: " + client);
            client.close();

        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
