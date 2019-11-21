package Aufgabe5;

import java.util.concurrent.Semaphore;

public class Philosopher extends Thread{
	static int count = 1;
	int myNumber;
	Semaphore fork1, fork2;
	
	public Philosopher(Semaphore fork1, Semaphore fork2){
		myNumber = count++;
		this.fork1 = fork1;
		this.fork2 = fork2;
	}
	public void run(){
		try {
			fork1.acquire();
			fork2.acquire();
			
			System.out.println("Philosoph " + myNumber + ": Esse jetzt.");
			try{
				sleep(100);
			}catch(Exception e1){
				throw(e1);
			}
			System.out.println("Philosoph " + myNumber + ": Gehe jetzt wieder denken.");
			
			fork2.release();
			fork1.release();
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}
		
	}
}
