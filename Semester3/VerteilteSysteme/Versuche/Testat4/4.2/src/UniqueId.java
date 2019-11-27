import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UniqueId {
    
    private String dataName = "";
    private FileWriter fw;
    private FileReader fr;
    private BufferedWriter bw;
    
    UniqueId(String dateiName){
      this.dataName = dateiName;  
    }
    
    void init(int wert) {
        try {
            fw = new FileWriter(dataName);
        } catch (IOException e) {
            System.out.println("FileWriter konnte nicht erzeugt werden");
            e.printStackTrace();
        }
        bw = new BufferedWriter(fw);

        try {
            bw.write(wert);
        } catch (IOException e) {
            System.out.println("Datei konnte nicht beschrieben werden");
            e.printStackTrace();
        }
        

        try {
            bw.close();
        } catch (IOException e) {
            System.out.println("Fehler beim close write prozess");
            e.printStackTrace();
        }
    }
    
    void getNext() {
        
        try {
            fr = new FileReader(dataName);
        } catch (FileNotFoundException e) {
            System.out.println("Fehler beim lesend er Datei");
            e.printStackTrace();
        }
        
    }

}
