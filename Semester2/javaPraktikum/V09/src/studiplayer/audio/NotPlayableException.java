package studiplayer.audio;

public class NotPlayableException extends Exception {  //http://www.inf.fu-berlin.de/lehre/SS12/ALP2/slides/V20_ALP2_Exceptions.pdf
    //Atribute
    private String pathname = "";
    
    
    //Konstruktoren
    public NotPlayableException(String pathname, String msg) {
        super();
        this.pathname = pathname + ", " + msg;
    }
    
    public NotPlayableException(String pathname, Throwable t) {
        super();
        this.pathname = pathname + ", " + t;
    }
    
    public NotPlayableException(String pathname, String msg, Throwable t) {
        super();
        this.pathname = pathname + ", " + msg + ", " + t;
    }
    
    public String toString() {
        return pathname +": " +super.toString();
    }
    

}
