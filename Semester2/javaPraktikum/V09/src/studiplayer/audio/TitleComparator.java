package studiplayer.audio;

import java.util.Comparator;

public class TitleComparator implements Comparator<AudioFile> {

    public int compare(AudioFile af1, AudioFile af2) {
    if(af1.getTitle()== null) {
        throw new NullPointerException ("AudioFile Title-af1 is null");    
    }
    if(af2.getTitle()== null) {
        throw new NullPointerException ("AudioFile Title-af2 is null");
    }
    if(af2.getTitle()== null && af1.getTitle()== null) {
        throw new NullPointerException ("AudioFile Title-af1 and Title-af2 are null");
    }
    // af1 kommt vor af2 in der Liste vor
    if(af1.getTitle().compareTo(af2.getTitle()) < 0 || (af1.getTitle() == null)) {
        return -1;
    }
    // af2 kommt nach af2 in der Liste vor
    if(af1.getTitle().compareTo(af2.getTitle()) > 0 || (af2.getTitle() == null)) {
        return 1;
    }
    // af1 und af2 sind gleich
    if(af1.getTitle().compareTo(af2.getTitle()) == 0) {
        return 0;
    }
    else { return 2;}
    }
}
