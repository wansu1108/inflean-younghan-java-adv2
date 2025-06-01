package remind.network2.v6;

import static remind.common.MyLogger.log;

import java.io.IOException;
import java.net.ServerSocket;

// Shutdown Hook 발생시, 전체 세션객체와 서버소켓의 자원정리
// 자바 프로세스가 종료되면, 셧다운훅이 실행된다.
public class ShutdownHook implements Runnable {
    private final ServerSocket serverSocket;
    private final SessionManagerV6 sessionManager;

    public ShutdownHook(ServerSocket serverSocket, SessionManagerV6 sessionManager) {
        this.serverSocket = serverSocket;
        this.sessionManager = sessionManager;
    }

    @Override
    public void run() {
        try {
            sessionManager.closeAll();
            serverSocket.close();

            Thread.sleep(1000); // 자원 정리 대기
        } catch (IOException e) {
            log(e);
        } catch (InterruptedException e) {
            log(e);
        }
    }
}
