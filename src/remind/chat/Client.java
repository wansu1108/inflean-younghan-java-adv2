package remind.chat;

import static remind.common.MyLogger.log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import network.tcp.SocketCloseUtil;

public class Client {
    // 클라이언트를 객체로 만든다.
    // 객체로 만드는 이유는? 메인에서 실행하는 경우는 없으니깐,,, 객체로 만들어서 실행시키는게 일반적
    /**
     * isClose를 하는 이유는?
     * -> 동시성 이슈가 있는경우
     * -> 동시성 ReadHandler, WriteHandler에서 닫을 수 있으니깐?
     * -> close는 Client에서 만드는게 맞지? oo 자원정리는 자원을 선언한곳에서 하는게 맞지
     * 
     * 아까 나의 문제점은?
     * try_resource를 사용해서, 멀티스레드로 read,write를 했는데, 멀티스레드를 실행하면 코드는 내려가니깐
     * 내려가는 시점에 try문을 벗어나고, 자원을 반환하겠네 => 소켓종료
     * 자원반환은, wirte,read그리고 client객체를 실행한 시점에 해주면 되겠다.. 예를들면 사용자가 닫기를 누른다던지??
     */
    private final Socket socket; // client객체는 소켓객체가 없으면 성립이 안됨
    private final DataInputStream input;
    private final DataOutputStream output;
    private boolean isClose = false;

    public Client(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
    }

    public void start() {
        log("채팅프로그램을 시작합니다.");
        Scanner scanner = new Scanner(System.in);
        join(scanner);

        Thread writeThread = new Thread(new ClientWritHandler(output, scanner, this));
        Thread readThread = new Thread(new ClientReadHandler(input, this));
        writeThread.start();
        readThread.start();
    }

    private void join(Scanner scanner) {
        try {
            System.out.print("닉네임을 입력해주세요. : ");
            String name = scanner.next();
            output.writeUTF("/join|" + name);
        } catch (Exception e) {
            log(e);
            close();
        }
    }

    public synchronized void close() {
        if (isClose) {
            return;
        }
        
        SocketCloseUtil.closeAll(socket, input, output);
        isClose = true;
        log("채팅 프로그램이 종료되었습니다.");
    }
}
