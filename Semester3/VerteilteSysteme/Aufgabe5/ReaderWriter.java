package Aufgabe5;
import java.util.concurrent.Semaphore;

public class ReaderWriter {
	
	public ReaderWriter(){}
	
public static void main(String args[]){
	Semaphore mutex, writer;
	mutex = new Semaphore(1);
	writer = new Semaphore(1);
	DBReader r1, r2, r3;
	DBWriter w1, w2, w3, w4;
	r1 = new DBReader(mutex, writer);
	r2 = new DBReader(mutex, writer);
	r3 = new DBReader(mutex, writer);
	w1 = new DBWriter(writer);
	w2 = new DBWriter(writer);
	w3 = new DBWriter(writer);
	w4 = new DBWriter(writer);
	r1.start();
	w1.start();
	r2.start();
	w2.start();
	r3.start();
	w3.start();
	w4.start();
}
	
	
}
