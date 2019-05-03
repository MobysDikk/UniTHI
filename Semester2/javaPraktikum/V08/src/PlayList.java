import java.util.LinkedList;

public class PlayList extends LinkedList<AudioFile> { // Listen befehle:
                                                      // https://www.journaldev.com/13386/java-linkedlist-linkedlist-java#java-linkedlist-generics
    
    // Atributes
    private int curPos;
    
    // Konstruktors
    public PlayList() {
        setCurrent(curPos);
    }

    public void setCurrent(int current) {

    }

    public int getCurrent() {
        return curPos;
    }

    public AudioFile getCurrentAudioFile() {

        if (pl.isEmpty()) {
            return null;
        } else if (pl.get(curPos) == null) {
            return null;
        } else {
            return pl.get(curPos);
        }

    }
    
    public void changeCurrent() {
       
    }
    
    public void setRandomOrder(boolean randomeOrder) {
        
    }

}
