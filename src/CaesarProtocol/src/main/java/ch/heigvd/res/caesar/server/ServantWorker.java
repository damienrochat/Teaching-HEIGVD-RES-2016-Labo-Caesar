/**
 * @file ServantWorker.java
 * @brief Manage the conversation with a single client
 * @author Patrick Champion, Damien Rochat
 */

package ch.heigvd.res.caesar.server;

import ch.heigvd.res.caesar.protocol.*;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
            OutputStream toClient = client.getOutputStream();

            while(true) {
                // wait for a message
                String message = new String();
                fromClient.read(message);

                // quit on empty message
                if(message.isEmpty())
                    break;

                // echo the message
                //toClient.write(message);
            }

            // end, close socket
            LOG.info("Client quit: " + client);
            client.close();

        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
