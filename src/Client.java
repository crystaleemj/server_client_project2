import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

public class Client {
    public static void main (String [] args) throws Exception{

        Socket sock = new Socket(args[0], Integer.parseInt(args[1]));

        System.out.println("Connection Established");

        File f = new File(args[2]);

        String fname = f.getName();
        long fsize = f.length();

        // when reading from a file, we need file object, so we definitely need it
        FileInputStream fis = new FileInputStream(f);

        BufferedOutputStream bos = new BufferedOutputStream(sock.getOutputStream());
        DataOutputStream dos = new DataOutputStream(bos);

        dos.writeUTF(fname);
        dos.writeLong(fsize);

        byte [] buffer = new byte [4096];
        int bytes = 0;

        while ((bytes = fis.read(buffer)) != -1){
            bos.write(buffer, 0, bytes);
        } 
        bos.flush();
        bos.close();
        fis.close();

        System.out.println(fname + " has been sent to server successfully");


        
    }
}
