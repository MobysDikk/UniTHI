package Aufgabe5;
import java.util.concurrent.Semaphore;

public class DBWriter extends Thread{
	static int count = 0;
	int myNumber;
	Semaphore writer;
	
	public DBWriter(Semaphore writer){
		myNumber = count++;
		this.writer = writer;
	}
	
	public void run(){
		try {
			writer.acquire();
			
			System.out.println(myNumber + ": Starte jetzt schreiben");
			try{
				sleep(100);
			}catch(Exception e1){
				throw(e1);
			}
			System.out.println(myNumber + ": Höre jetzt auf zu schreiben");
			
			writer.release();
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}
		
	}
}
