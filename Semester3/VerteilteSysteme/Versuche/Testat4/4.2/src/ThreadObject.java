import java.io.IOException;

public class ThreadObject extends Thread{

    String threadname = "";
    UniqueID id;
    public ThreadObject (UniqueID id, String threadname) {
      this.id = id;
      this.threadname = threadname;
    }
    public void run () {
           
              for(int i = 0; i < 10; i++)
                try {
                    System.out.println(threadname + "=" + id.getNext());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
          
    }
}
