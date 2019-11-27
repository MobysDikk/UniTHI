import java.io.IOException;

public class DataInputStream {
    
    public static void writeInt(UniqueID id) {
        try {
            id.init(10000);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
