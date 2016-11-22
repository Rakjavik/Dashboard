package com.joel;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tyrion on 7/29/2016.
 */
public class NetworkCtrl {

    private final String IPSTART = "10.0.0.";
    private String myIP;
    private List<ClientInfo> clientInfos;

    public NetworkCtrl() {
        myIP = getMyAddress();
        clientInfos = new LinkedList<>();
        List<Address> liveHosts = pingNetwork();
        for(Address address : liveHosts) {
            ClientInfo clientInfo = new ClientInfo();
            clientInfo.setAddress(address);
            this.clientInfos.add(clientInfo);
        }
    }

    public void scan (){
        for(ClientInfo clientInfo : clientInfos) {
            clientInfo.scanForOpenPorts(20,500);
        }
    }

    private List<Address> pingNetwork() {
        List<HostScanThread> hostScanThreads = new LinkedList<>();
        for(int count = 1; count < 255; count++) {
            HostScanThread hostScanThread = new HostScanThread(IPSTART+count);
            hostScanThread.start();
            hostScanThreads.add(hostScanThread);
        }
        List<Address> liveAddresses = new LinkedList<>();
        for(HostScanThread hostScanThread : hostScanThreads) {
            try {
                hostScanThread.join();
                if(hostScanThread.isReachable()) {
                    Address address = new Address(hostScanThread.getAddressToScan(), hostScanThread.getHostname());
                    liveAddresses.add(address);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return liveAddresses;
    }

    private String getMyAddress() {
        String myIP = null;

        Enumeration<NetworkInterface> networkInterfaceEnumeration = null;
        try {
            networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
            return null;
        }
        while(networkInterfaceEnumeration.hasMoreElements()) {
            Enumeration<InetAddress> inetEnumeration = networkInterfaceEnumeration.nextElement().getInetAddresses();
            while(inetEnumeration.hasMoreElements()) {
                String address = inetEnumeration.nextElement().getHostAddress();
                if(address.contains(IPSTART)) {
                    myIP = address;
                }
            }
        }
        System.out.println(myIP);
        return myIP;
    }

    public List<ClientInfo> getClientInfos() {
        return clientInfos;
    }
}
