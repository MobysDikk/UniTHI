import static org.junit.Assert.*;

import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;

public class UTestAudioFileFactory {

    @Ignore@Test
    public void test_getInstance_o1() throws Exception{
        try {
            AudioFileFactory.getInstance("unknown.xxx");
            fail("Unknow suffix; expecting exception");
        }catch (RuntimeException e) {
            
        }
        }
    @Ignore@Test
    public void test_getInstance_o2() throws Exception{
        try {
            AudioFileFactory.getInstance("unknown.mp3");
            fail("Unknow suffix; expecting exception");
        }catch (RuntimeException e) {
            
        }
        }
    @Test
    public void test_getInstance_03() throws Exception {
        AudioFile af1 = AudioFileFactory.getInstance("audiofiles/Eisbach Deep Snow.ogg");
        assertTrue("Ecpecting object of type TaggedFile", (af1 instanceof TaggedFile));
        
        AudioFile af2 = AudioFileFactory.getInstance("audiofiles/wellenmeister - tranquility.wav");
        assertTrue("Ecpecting object of type WavFile", (af2 instanceof WavFile));
        
        AudioFile af3 = AudioFileFactory.getInstance("audiofiles/special.oGg");
        assertTrue("Ecpecting object of type TaggedFile", (af3 instanceof TaggedFile));
    }
    

}
