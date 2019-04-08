import static org.junit.Assert.*;

import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;

public class UTestAudioFile {

    @Ignore@Test
    public void test() {
        fail("Not yet implemented");
    }



@Test
public void test_parsePathname_00() throws Exception {
    AudioFile af = new AudioFile();

    af.parsePathname("/mein/pfad/lied1.mp3");

    assertEquals("Pathname stored incorrectly",
            "/mein/pfad/lied1.mp3", af.getPathname());
}
}
