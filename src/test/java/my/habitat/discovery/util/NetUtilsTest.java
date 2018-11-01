package my.habitat.discovery.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest( InetAddress.class )
public class NetUtilsTest {

    public InetAddress mockInetAddress;
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
    public void getIp_returnsAValidHostIp() throws UnknownHostException {

        String addr = "100.0.0.3";
        InetAddress inetAddress = PowerMockito.mock(InetAddress.class);
        PowerMockito.when(inetAddress.getHostAddress()).thenReturn(addr);

        PowerMockito.mockStatic(InetAddress.class);
        PowerMockito.when(InetAddress.getLocalHost()).thenReturn(inetAddress);
//        when(mockInetAddress.getHostAddress())
//                .thenReturn("100.0.0.3");

        String hostIp = NetUtils.getIp();

        assertEquals("100.0.0.3", hostIp);

    }


}

class InetAddressWrap {
    public final static InetAddress getLocalHost() throws UnknownHostException {
        return InetAddress.getLocalHost();
    }

}
