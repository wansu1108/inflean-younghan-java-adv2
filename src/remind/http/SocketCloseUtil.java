package remind.http;

import static remind.common.MyLogger.log;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketCloseUtil {

    public static void closeAll(Socket socket, BufferedReader input, PrintWriter output) {
        close(input);
        close(output);
        close(socket);
    }

    public static void close(BufferedReader input) {
        if(input != null) {
            try {
                input.close();
            } catch (Exception e) {
                log(e);
            }
        }
    }

    public static void close(PrintWriter ouput) {
        if(ouput != null) {
            try {
                ouput.close();
            } catch (Exception e) {
                log(e);
            }
        }
        
    }

    public static void close(Socket socket) {
        if(socket != null) {
            try {
                socket.close();
            } catch (Exception e) {
                log(e);
            }
        }
        
    }
}
