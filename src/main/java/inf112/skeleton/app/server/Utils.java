package inf112.skeleton.app.server;

import com.dosse.upnp.UPnP;

public class Utils {

    public static void openPort(int udpPort, int tcpPort) {
        try {
            if (UPnP.isUPnPAvailable()) {
                if (UPnP.isMappedTCP(tcpPort)) {
                    System.out.println("[Port Utils] Cant map port " + tcpPort + ": Already mapped");
                } else if (UPnP.openPortTCP(tcpPort)) {
                    if (UPnP.openPortUDP(udpPort)) {
                        System.out.println("[Port Utils] Successfully mapped port: " + tcpPort);
                    }
                }
            }
        } catch (Throwable t) {
            System.err.println("[Port Utils] Network error: " + t);
        }
    }

    public static void closePorts(int udpPort, int tcpPort) {
        try {
            UPnP.closePortTCP(tcpPort);
            UPnP.closePortUDP(udpPort);
        } catch (Throwable t) {
            System.err.println("[Port Utils] Network error: " + t);
        }
    }
}
