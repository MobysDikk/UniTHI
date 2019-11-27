
public class Test {
    public static void main(String[] args) {
        UniqueID id = new UniqueID("id.dat");
        DataInputStream.writeInt(id);
        
        ThreadObject t1 = new ThreadObject(id, "t1");
        ThreadObject t2 = new ThreadObject(id, "t2");
        ThreadObject t3 = new ThreadObject(id, "t3");
        ThreadObject t4 = new ThreadObject(id, "t4");
        ThreadObject t5 = new ThreadObject(id, "t5");
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
