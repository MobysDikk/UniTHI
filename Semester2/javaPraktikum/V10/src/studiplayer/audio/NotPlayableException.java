package studiplayer.audio;

public class NotPlayableException extends Exception {  //http://www.inf.fu-berlin.de/lehre/SS12/ALP2/slides/V20_ALP2_Exceptions.pdf
    //Atribute
    private String pathname = "";
    
    
    //Konstruktoren
    public NotPlayableException(String pathname, String msg) {
        super(msg);
        this.pathname = pathname;
    }
    
    public NotPlayableException(String pathname, Throwable t) {
        super(t);
        this.pathname = pathname;
    }
    
    public NotPlayableException(String pathname, String msg, Throwable t) {
        super(msg, t);
        this.pathname = pathname;
    }
    
    public String toString() {
        return pathname +": " +super.toString();
    }
    

}
