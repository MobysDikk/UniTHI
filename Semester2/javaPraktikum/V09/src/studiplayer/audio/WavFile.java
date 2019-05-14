package studiplayer.audio;
import studiplayer.basic.WavParamReader;

public class WavFile extends SampledFile {

    // Atribute

    // Konstruktor
    public WavFile() {
        super();
    }

    public WavFile(String s) throws NotPlayableException  {
        super(s);
        readAndSetDurationFromFile(pathName);

    }

    public void readAndSetDurationFromFile(String pathname) throws NotPlayableException  {
        try {
        WavParamReader.readParams(pathname);
        }catch(RuntimeException e) {
            throw new NotPlayableException(pathName, e );
        }
        long allFrames = WavParamReader.getNumberOfFrames();
        float framesPerSecond = WavParamReader.getFrameRate();
        duration = computeDuration(allFrames, framesPerSecond);
    }

    public static long computeDuration(long numberOfFrames, float frameRate) {

        // frames pro sekunde
        long wavDuration = (long) ((numberOfFrames / frameRate) * 1000000);
        return wavDuration;
    }

    public String toString() {
        return (getAuthor() + " - " + getTitle() + " - " + timeFormatter(duration)).trim();
    }

    public String[] fields() {
        String[] graphic_surface = new String[4];
        graphic_surface[0] = getAuthor();
        graphic_surface[1] = getTitle();
        graphic_surface[2] = "";
        graphic_surface[3] = timeFormatter(duration);
        return graphic_surface;
    }

}
