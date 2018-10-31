package my.habitat.discovery.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InterfaceAddress;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(InetAddress.class)
public class NetUtilsTest {

    private static final byte NET_PREFIX = (byte) 24;
    private static final byte[] IPV4_ADDRESS1  = new byte[]{110, 0, 1, 1};
    private static final byte[] IPV4_ADDRESS2  = new byte[]{120, 0, 0, 1};
    private static final byte[] IPV6_ADDRESS1  = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    private static final byte[] IPV4_LOCALHOST = new byte[]{127, 0, 0, 1};


//    @Test
//    public void getIp_returnsOnlyIpv4Addresses() {
//        NetworkInterface networkInterface = mock(NetworkInterface.class);
//        InterfaceAddress addresses[] = new InterfaceAddress[]{
//                mockInterfaceAddress(IPV4_ADDRESS2),
//                mockInterfaceAddress(IPV4_ADDRESS1),
//                mockInterfaceAddress(IPV6_ADDRESS1),
//        };
//        when(networkInterface.getInterfaceAddresses()).thenReturn(Arrays.asList(addresses));
//
//        List<InetAddress> result = NetUtils.getIp();
//
//        assertEquals(2, result.size());
//    }

    @Test
    public void getHostIp_returnsAValidHostIp() throws IOException {

        mockStatic(InetAddress.class);
        byte[] ipAddr = new byte[]{100, 0, 0, 3};
        InetAddress addr = InetAddress.getByAddress(ipAddr);
        when(InetAddress.getLocalHost()).thenReturn(addr);

        String hostIp = NetUtils.getIp().getHostName();
        String subnet = hostIp.substring(0, hostIp.lastIndexOf("."));
        NetUtils.scanSubnetRange(subnet);

        assertEquals("100.0.0", hostIp);
    }

    private InterfaceAddress mockInterfaceAddress(final byte[] netAddress) {
        final InterfaceAddress iface = mockInterfaceAddress(netAddress, NET_PREFIX);
        if (netAddress.equals(IPV4_LOCALHOST)) {
            InetAddress address =  mock(InetAddress.class);
            when(address.isLoopbackAddress()).thenReturn(true);
            when(iface.getAddress()).thenReturn(address);
        }

        return iface;
    }

    private InterfaceAddress mockInterfaceAddress(final byte[] netAddress, final int netPrefix) {
        InetAddress address = mock(InetAddress.class);
        when(address.getAddress()).thenReturn(netAddress);

        InterfaceAddress adapter = mock(InterfaceAddress.class);
        when(adapter.getAddress()).thenReturn(address);
        when(adapter.getNetworkPrefixLength()).thenReturn((short) netPrefix);
        return adapter;
    }

}