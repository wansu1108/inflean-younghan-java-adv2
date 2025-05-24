package remind.network1;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressMain {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress localhost = InetAddress.getByName("localhost");
        System.out.println(localhost); // localhost/127.0.0.1
        InetAddress google = InetAddress.getByName("google.com");
        System.out.println(google); // google.com/142.250.76.142
    }
}
