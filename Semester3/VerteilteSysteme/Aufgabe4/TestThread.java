package Aufgabe4;
import java.io.IOException;

public class TestThread extends Thread {
  String threadname = "";
  UniqueID id;
  public TestThread (UniqueID id, String threadname) {
    this.id = id;
    this.threadname = threadname;
  }
  public void run () {
		  try {
			for(int i = 0; i < 10; i++)
				System.out.println(threadname+ ": " + id.getNext());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }
}
