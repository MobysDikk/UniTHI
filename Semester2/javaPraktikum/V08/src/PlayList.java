import java.util.LinkedList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

public class PlayList extends LinkedList<AudioFile> { // Listen befehle:
                                                      // https://www.tutorialspoint.com/java/util/java_util_linkedlist.htm

    // Atributes
    private int curPos;
    private boolean playMod;
    
    // Konstruktors
    public PlayList() {
        setCurrent(curPos);
        setRandomOrder(playMod = false);
    }

    public void setCurrent(int current) {
        curPos = current;
    }

    public int getCurrent() {
        return curPos;
    }

    public AudioFile getCurrentAudioFile() {

        if (this.isEmpty()) {
            return null;
        } else if (this.size() <= curPos) { // !!!!!!!!!!!!!!!!!!!!
            return null;    
       } else {
            return this.get(curPos);
        }

    }

    public void changeCurrent() {
        if(playMod = false) {
        if(this.size() -1 == curPos) {
            curPos = 0;
        }else {curPos++;}
         }
        
        if(playMod = true) {
            if(this.size() -1 == curPos) {
                Collections.shuffle(this);
                curPos = 0;
            }else {curPos++;}    
        }
    }
    

    public void setRandomOrder(boolean randomeOrder) {
           playMod = randomeOrder;
        if (playMod=true) {
            Collections.shuffle(this);
        }
        }
    
    
    public void saveAsM3U(String pathname) {
        FileWriter writer = null;

        String fname = "Playlist.txt.";
        //String linesep = System.getProperty("line.seperator");
         String linesep ="\n";
        try {

            writer = new FileWriter(fname);

            writer.write("# MorisB Liste" + linesep);
            writer.write(pathname + linesep);
            
        } catch (IOException e) {
            throw new RuntimeException("Unable to write to file " + fname + ":" + e.getMessage());
        } finally {
            try {
                writer.close();
            } catch (Exception e) {

            }

        }
    }
}
