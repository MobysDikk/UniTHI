import static org.junit.Assert.*;

import org.junit.Test;

public class UTestWavFile {

    @Test
    public void test_computeDuration_01() {
        assertEquals("Wrong duration", 2000000L ,
                WavFile.computeDuration(88200L,44100.0f));
    }
    @Test
    public void test_readAndSetDurationFromFile_01() throws Exception{
        WavFile wf = new WavFile();
        wf.parsePathname("audiofiles/wellenmeister - tranquility.wav");
        wf.readAndSetDurationFromFile(wf.getPathname());
        assertEquals("Wrong time format", "02:21", wf.getFormattedDuration());
}
    
}