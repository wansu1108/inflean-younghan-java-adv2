package network.exception.close.normal;

import static util.MyLogger.log;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

// inputStream에 따라 각각 EOF를 전달받는 방식이 다르다.
public class NormalCloseClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 45678);
        log("소캣 연결: " + socket);
        InputStream in = socket.getInputStream();

        readByInputStream(in, socket);
        readByBufferedReader(in, socket);
        readByDataInputStream(in, socket);

        log("연결 종료: " + socket.isClosed());
    }

    private static void readByInputStream(InputStream in, Socket socket) throws IOException {
        int read = in.read();
        log("read : " + read);
        if(read == -1) { // EOF = -1
            in.close();
            socket.close();
        }
    }

    private static void readByBufferedReader(InputStream in, Socket socket) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line = br.readLine();
        log("read : " + line);
        if(line == null) { // EOF = null
            br.close();
            socket.close();
        }
    }

    private static void readByDataInputStream(InputStream in, Socket socket) throws IOException {
        DataInputStream dis = new DataInputStream(in);
        try {
            dis.readUTF();
        } catch (EOFException e) {
            log(e);
        } finally {
            dis.close();
            socket.close();
        }
        
    }
}
