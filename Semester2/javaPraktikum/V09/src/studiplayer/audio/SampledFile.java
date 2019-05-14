package studiplayer.audio;
import java.io.IOException;

import studiplayer.basic.BasicPlayer;

public abstract class SampledFile extends AudioFile {
    //Atribute
    protected long duration;
    protected String time_duration;
    private String time_position;
   
    //Konstruktoren
    public SampledFile() {
        super();
    }
    
    public SampledFile(String s) throws NotPlayableException  {
        super(s);
    }
    
    
    
    //Setters/////////////////////////////////////////////////////////////
    public void play() throws NotPlayableException{ 
        try {
        BasicPlayer.play(pathName);
       }catch (RuntimeException e) {
           throw new NotPlayableException(pathName, e );
       }
   
    }

    public void togglePause() { // toggle = pause
        
        BasicPlayer.togglePause();
       
       
    }

    public void stop() { // stop
        
        BasicPlayer.stop();
    }
    
    
    //Getter//////////////////////////////////////////////////////////////
    public String getFormattedDuration() { 
        time_duration = timeFormatter(duration);
        return time_duration; 
    }
    public String getFormattedPosition() { 
        time_position = timeFormatter(BasicPlayer.getPosition());
         return time_position; 
     }
    public static String timeFormatter(long microtime) {
        
        long overFlowGrenze = 5999999999L;
        
        if(microtime<0) {
            throw new RuntimeException("Negativ time value provided");
        }
        if(microtime>overFlowGrenze) {
            throw new RuntimeException("Time value exceeds allowed format");
        }
            String ausgabe;
            long min_and_sec = microtime / 1000000;       
            long min = min_and_sec / 60;
            long sec = min_and_sec % 60; 
            if (min < 10 && sec < 10) {
                ausgabe = "0"+min+":"+"0"+sec;
            }
            else if (min < 10 && sec > 10) {
                ausgabe = "0"+min+":"+sec;
            }
            else if (min > 10 && sec < 10) {
                ausgabe = min+":"+"0"+sec;
            }
            else {
                ausgabe = min+":"+sec;
            }
             return ausgabe; 
    }
    
}
