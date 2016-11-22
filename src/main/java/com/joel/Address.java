package com.joel;

/**
 * Created by Tyrion on 7/30/2016.
 */
public class Address {
    private String ip;
    private String hostname;

    public Address(String ip,String hostname) {
        this.ip = ip;
        this.hostname = hostname;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @Override
    public String toString() {
        return "Address{" +
                "ip='" + ip + '\'' +
                ", hostname='" + hostname + '\'' +
                '}';
    }
}
