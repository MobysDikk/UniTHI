package Aufgabe5;
import java.util.concurrent.Semaphore;

public class DBReader extends Thread{
	static int count = 0;
	static int reader = 0;
	int myNumber;
	Semaphore mutex, writer;
	
	public DBReader(Semaphore mutex, Semaphore writer){
		myNumber = count++;
		this.mutex = mutex;
		this.writer = writer;
	}
	
	public void run(){
		try {
			mutex.acquire();
			if(reader == 0){
				writer.acquire();
			}
			reader++;
			mutex.release();
			
			System.out.println(myNumber + ": Starte jetzt lesen");
			try{
				sleep(100);
			}catch(Exception e1){
				throw(e1);
			}
			System.out.println(myNumber + ": Höre jetzt auf zu lesen");
			
			mutex.acquire();
			if(reader == 1){
				writer.release();
			}
			reader--;
			mutex.release();
			
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}
		
	}
}
