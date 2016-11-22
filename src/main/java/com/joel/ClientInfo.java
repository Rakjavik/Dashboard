package com.joel;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tyrion on 7/30/2016.
 */
public class ClientInfo {
    private Address address;
    private List<Port> ports;

    public ClientInfo() {
        ports = new LinkedList<>();
    }

    public void scanForOpenPorts(int start, int end) {
        List<PortScanThread> portScanThreadList = new LinkedList<>();
        for(int count = start; count < end; count++) {
            PortScanThread portScanThread = new PortScanThread(address.getIp(),count);
            portScanThread.start();
            portScanThreadList.add(portScanThread);
        }
        for(PortScanThread portScanThread : portScanThreadList) {
            Port port = new Port();
            port.setPort(portScanThread.getPort());
            try {
                portScanThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
                port.setOpen(false);
                ports.add(port);
                continue;
            }

            port.setOpen(portScanThread.isOpen());
            port.setPort(portScanThread.getPort());
            ports.add(port);
        }
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Port> getPorts() {
        return ports;
    }

    public void setPorts(List<Port> ports) {
        this.ports = ports;
    }
}
