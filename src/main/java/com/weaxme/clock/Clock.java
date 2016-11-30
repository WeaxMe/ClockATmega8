package com.weaxme.clock;

import java.io.Serializable;

/**
 * @author Vitaliy Gonchar
 */
public class Clock implements Serializable {
    private String name;
    private String ip;
    private int port;

    private ClockTime time;

    public Clock(String name, String ip, int port, ClockTime time) {
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != Clock.class) return false;
        Clock clock = (Clock) o;
        if (!clock.getName().equals(name)) return false;
        if (!clock.getIp().equals(ip)) return false;
        if (clock.getPort() != port) return false;
        if (!clock.getTime().equals(getTime())) return false;


        return true;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getHours() {
        return time.getHours();
    }

    public int getMinutes() {
        return time.getMinutes();
    }

    public int getSeconds() {
        return time.getSeconds();
    }

    public void setHours(int hours) {
        time.setHours(hours);
    }

    public void setMinutes(int minutes) {
        time.setMinutes(minutes);
    }

    public void setSeconds(int seconds) {
        time.setSeconds(seconds);
    }

    public String getTime() {
        return time.toString();
    }

    @Override
    public String toString() {
        return "Clock{" +
                "name='" + name + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }
}
