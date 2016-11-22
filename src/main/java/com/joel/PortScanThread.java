package com.joel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Tyrion on 7/30/2016.
 */
public class PortScanThread extends Thread {
    private String address;
    private int port;
    private boolean open;

    public PortScanThread(String address,int port) {
        this.address = address;
        this.port = port;
    }

    @Override
    public void run() {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(address,port),200);
            socket.close();
            open = true;
            System.out.println("Port open " + port);
        } catch (IOException e) {
            open = false;
        }
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public boolean isOpen() {
        return open;
    }
}
