import studiplayer.basic.WavParamReader;

public class WavFile extends SampledFile {
    
    //Atribute
   private long allFrames;
   private float framesPerSecond;
   
    //Konstruktor
    public WavFile() {
        super();
    }
    
    public WavFile(String s) {
        super(s);
        readAndSetDurationFromFile(pathName);
        
    }
    
    public void readAndSetDurationFromFile(String pathname) {
         WavParamReader.readParams(pathname);
         allFrames = WavParamReader.getNumberOfFrames();
         framesPerSecond = WavParamReader.getFrameRate();
         duration = computeDuration(allFrames, framesPerSecond);
    }
    
    public static long computeDuration(long numberOfFrames, float frameRate) {
        
            // frames pro sekunde
       long wavDuration= (long) ((numberOfFrames/frameRate)*1000000);
       return wavDuration;
    }
    public String toString() {
        return author + " - " + title + " - " + time_duration;
    }

   
    public String[] fields() {
        
        return null;
    }
    

}
