package studiplayer.audio;

import java.util.Comparator;

public class DurationComparator implements Comparator<AudioFile> {

    private String time1;
    private String time2;

    public int compare(AudioFile af1, AudioFile af2) {
        // Typ-Cast
        SampledFile t1 = (SampledFile) af1;
        SampledFile t2 = (SampledFile) af2;
        
        if(t1.getFormattedDuration()== null) {
            throw new NullPointerException ("AudioFile Time-af1 is null");    
        }
        if(t2.getFormattedDuration()== null) {
            throw new NullPointerException ("AudioFile Time-af2 is null");
        }
        if(t1.getFormattedDuration()== null && t2.getFormattedDuration()== null) {
            throw new NullPointerException ("AudioFile Time-af1 and Time-af2 are null");
        }
        
        if(t1.getFormattedDuration()!= null && t2.getFormattedDuration()!= null) {
        // get Formatted- String   5:22
        time1 = t1.getFormattedDuration();
        time2 = t2.getFormattedDuration();
        }
        
     // time1 ist länger als time2
        if(time1.compareTo(time2) < 0 || (t2.getFormattedDuration() == null)) {
            return -1;
        }
        // time1 ist kürzer als time2
        if(time1.compareTo(time2) > 0 || (t1.getFormattedDuration() == null)) {
            return 1;
        }
        // af1 und af2 sind gleich
        if(time1.compareTo(time2) == 0) {
            return 0;
        }
        else { return 2;}
        
}

}
