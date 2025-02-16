package network.tcp.v1;

import static util.MyLogger.log;

import java.net.InetAddress;
import java.net.UnknownHostException;

// IP 통신
// TCP를 연결해주는 Socket객체는 내부에 IP통신을 아래와 같이 실행한다.
public class InetAddressMain {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress localhost = InetAddress.getByName("localhost");
        log(localhost);

        InetAddress google = InetAddress.getByName("google.com");
        log(google);
    }
}
