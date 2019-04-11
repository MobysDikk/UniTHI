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
        
        BasicPlayer();
    }

    public void stop() { // stop
        
        BasicPlayer();
    }

    public String getFormatterDuration() { 
        return null; 
    }
    
    public String getFormatterPosition() { 
        return null; 
    }

    }
    
    

}
