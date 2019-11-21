package Aufgabe4;
import java.io.IOException;

public class Test {
	public static void main(String[] args) {
		UniqueID id = new UniqueID("id.dat");
		TestThread t1 = new TestThread(id, "t1");
		TestThread t2 = new TestThread(id, "t2");
		TestThread t3 = new TestThread(id, "t3");
		TestThread t4 = new TestThread(id, "t4");
		TestThread t5 = new TestThread(id, "t5");
		try {
			id.init(10000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t1.start();
		t2.start();
		t3.start();
	}
}
