package com.joel;

import jcifs.netbios.NbtAddress;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Tyrion on 7/30/2016.
 */
public class HostScanThread extends Thread {
    private String addressToScan;
    private boolean reachable;
    private String hostname;

    public HostScanThread(String addressToScan) {
        this.addressToScan = addressToScan;
    }

    @Override
    public void run() {
        InetAddress address;
        try {
            address = InetAddress.getByName(addressToScan);
            if(address.isReachable(100)) {
                System.out.println(address + " is reachable");
                reachable = true;
                String hostname;
                try {
                    NbtAddress[] nbts = NbtAddress.getAllByAddress(addressToScan);
                    hostname = nbts[0].getHostName();
                } catch(UnknownHostException e) {
                    hostname = "Unknown";
                }
                if(hostname != null && !hostname.isEmpty() && !hostname.equals(addressToScan)) {
                    this.hostname = hostname;
                } else {
                    this.hostname = "Unknown";
                }
            } else {
                System.out.println(address + " is NOT reachable");
                reachable = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            reachable = false;
        }
    }

    public boolean isReachable() {
        return reachable;
    }

    public String getAddressToScan() {
        return addressToScan;
    }

    public String getHostname() {
        return hostname;
    }
}
