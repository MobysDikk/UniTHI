package studiplayer.audio;

import java.util.Comparator;

public class AuthorComparator implements Comparator<AudioFile>  {

    
    public int compare(AudioFile af1, AudioFile af2) {
        
        if(af1.getAuthor()== null) {
            throw new NullPointerException ("AudioFile Author-af1 is null");    
        }
        if(af2.getAuthor()== null) {
            throw new NullPointerException ("AudioFile Author-af2 is null");
        }
        if(af2.getAuthor()== null && af1.getAuthor()== null) {
            throw new NullPointerException ("AudioFiles Author-af1 and Author-af2 are null");
        }
        // af1 kommt vor af2 in der Liste vor
        if(af1.getAuthor().compareTo(af2.getAuthor()) < 0 || (af1.getAuthor() == null)) {
            return -1;
        }
        // af2 kommt nach af2 in der Liste vor
        if(af1.getAuthor().compareTo(af2.getAuthor()) > 0 || (af2.getAuthor() == null)) {
            return 1;
        }
        // af1 und af2 sind gleich
        if(af1.getAuthor().compareTo(af2.getAuthor()) == 0) {
            return 0;
        }
        else { return 2;}
        }
    }


