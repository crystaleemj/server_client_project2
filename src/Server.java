import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {

        ServerSocket serv = new ServerSocket(Integer.parseInt(args[0]));
        // serv.bind(Inet4Address.getByName("103.252.203.124"), Integer.parseInt(args[0]));
        System.out.println("Listening on port: " + args[0]);
        Socket socket = serv.accept();
        System.out.println("Connection Established");

        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);

        String fname = dis.readUTF();
        long fsize = dis.readLong();

        FileOutputStream fos = new FileOutputStream("rcd" + File.separator + fname);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        byte [] buffer = new byte [4096];
        int bytes = 0;

        while ((bytes = dis.read(buffer)) != -1){
            bos.write(buffer, 0, bytes);
        } 
        bos.flush();
        bos.close();

        System.out.println("file " + fname + " successfully received!");


    }
}
