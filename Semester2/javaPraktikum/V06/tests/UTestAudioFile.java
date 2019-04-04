import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class UTestAudioFile {

    @Test
    public void test_parsePathname_03() throws Exception {
        AudioFile af = new AudioFile();
        af.parsePathname("/my-tmp/file.mp3");
        char sepchar = java.io.File.separatorChar;
        
        assertEquals("Pathname stored incorrectly,",
                sepchar + "my-tmp" + sepchar+ "file.mp3",
                af.getPathname());
        
    }

}
