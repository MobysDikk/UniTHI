import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class UniqueID {
    
    private File file;
    private DataOutputStream output;
    private DataInputStream input;
  
    
    public UniqueID(String filename) {
        
        this.file = new File(filename);
    }
    
    public synchronized void init(int value) throws IOException{
        //synchronized(this){
            OutputStream outStream = new FileOutputStream(file);
            output = new DataOutputStream(outStream);
            output.writeInt(value);
            outStream.close();
            output.close();
        //}
    }
    
    public synchronized int getNext() throws IOException{
        //synchronized(this){
            InputStream inStream = new FileInputStream(file);
            input = new DataInputStream(inStream);
            int value = input.readInt() + 1;
            inStream.close();
            input.close();
            init(value);
            return value;
        //}
    }
}
