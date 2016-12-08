package com.weaxme.driver;

import org.omg.CORBA.DATA_CONVERSION;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author Vitaliy Gonchar
 */
public class ClientESP8266 {

    private static final String IP = "192.168.4.1";
    private static final int PORT = 8888;
    private InetSocketAddress address;

    private static final Logger LOG = LoggerFactory.getLogger(ClientESP8266.class);

    private int counter = 0;

    private String data;

    private void execute() {
        init();
        new Thread(new Connection()).run();
    }

    private void init() {
        address = new InetSocketAddress(IP, PORT);
    }

    public String getData() {
        execute();
        return data;
    }

    private class Connection implements Runnable {

        private BufferedReader reader;

        public void run() {
            try {
                LOG.debug("Start configuration connection");
                Socket socket = new Socket(address.getAddress(), address.getPort());
                LOG.info("Start connection.");
                LOG.debug("socket: " + socket);
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
                int buffer = 128;
                byte [] buff = new byte[128];
                in.read(buff, 0, buffer);
                StringBuilder builder = new StringBuilder();
                synchronized (this) {
                    int i = 0;
                    while (buff[i] != '\r' && i < 128) {
                        builder.append(buff[i]);
                        i++;
                    }
                    counter++;
                    data = new String(buff);
                }
                LOG.info(counter + " - input data - " + data);

            } catch (IOException e) {
                data = "No connection";
                LOG.error("Cannot start server socket");
                if (LOG.isDebugEnabled()) e.printStackTrace();
            }
        }
    }
}
