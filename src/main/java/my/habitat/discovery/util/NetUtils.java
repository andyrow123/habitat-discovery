package my.habitat.discovery.util;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class NetUtils {
    public static InetAddress getIp() {

        InetAddress ipAddr = null;

        try {
            ipAddr = InetAddress.getLocalHost();
            System.out.println(ipAddr.getHostAddress());
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }

        return ipAddr;
    }

    public static void scanSubnetRange(String subnet) throws IOException {

        int timeout=1000;
        for (int i=1;i<255;i++){
            String host = subnet + "." + i;
            if (isReachable(host, 80, timeout)){
                System.out.println(host + " is reachable");
            }
        }
    }

    private static boolean isReachable(String addr, int openPort, int timeOutMillis) {
        try {
            try (Socket soc = new Socket()) {
                soc.connect(new InetSocketAddress(addr, openPort), timeOutMillis);
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    private static List<InetAddress> getAllINet4Addresses(Enumeration<NetworkInterface> networkInterfaces) {
        List<InetAddress> inetAddresses = new ArrayList<>();

        for (NetworkInterface networkInterface : Collections.list(networkInterfaces)) {
            Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
            for (InetAddress address : Collections.list(addresses)) {
                if (address instanceof Inet4Address && !isLocalIp(address)) {
                    System.out.println(address.getHostAddress());
                    inetAddresses.add(address);
                }
            }
        }
        return inetAddresses;
    }

    private static boolean isLocalIp(InetAddress address) {
        return address.getHostAddress().equals("127.0.0.1");
    }
}
