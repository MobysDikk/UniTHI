package Aufgabe4;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class UniqueID {
	private String filename = "";
	private File file;
	
	public UniqueID(String filename) {
		this.filename = filename;
		this.file = new File(filename);
	}
	
	public synchronized void init(int value) throws IOException{
		//synchronized(this){
			OutputStream outStream = new FileOutputStream(file);
			DataOutputStream output = new DataOutputStream(outStream);
			output.writeInt(value);
		//}
	}
	
	public synchronized int getNext() throws IOException{
		//synchronized(this){
			InputStream inStream = new FileInputStream(file);
			DataInputStream input = new DataInputStream(inStream);
			int value = input.readInt() + 1;
			OutputStream outStream = new FileOutputStream(file);
			DataOutputStream output = new DataOutputStream(outStream);
			output.writeInt(value);
			return value;
		//}
	}
}
