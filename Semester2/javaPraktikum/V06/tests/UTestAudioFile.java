import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;

class UTestAudioFile {

   @Ignore @Test // wen man den test nicht mehr will Ignore davor
    void test() {
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
