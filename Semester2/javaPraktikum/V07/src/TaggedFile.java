import studiplayer.basic.BasicPlayer;

public class TaggedFile extends AudioFile {

    
    
    
    //konstruktor für Unitests
    public TaggedFile () {
        super();          // ruft den KOnstrukter der SUperklasse auf
                          // wird normlaserweise von alleine erzeugt
        
    }
    
    // konstruktor
    public TaggedFile(String s) {
        super(s);
        
    }
    
    
    public void play() { // Pfadnamen damit der weis wo das lied ist

        BasicPlayer.play(getPathname());
    }

    public void togglePause() { // toggle = pause
        
        BasicPlayer.togglePause();
    }

    public void stop() { // stop
        
        BasicPlayer.stop();
    }

    public String getFormattedDuration() { 
        return "";
    }
    
    public String getFormattedPosition() { 
        return ""; 
    } 

    public static String timeFormatter(long microtime) {
        if(microtime<0) {
            throw new RuntimeException("Negativ time value provided");
        }
        if(microtime>(Math.pow(2,63)-1)) {
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
    
    


