import static org.junit.Assert.*;

import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;

public class UTestAudioFile {

    @Ignore
    @Test
    public void test() {
        fail("Not yet implemented");
    }

    @Ignore
    @Test
    public void test_parsePathname_00() throws Exception {
        AudioFile af = new AudioFile();
        // Testfall1 OK
        af.parsePathname("");
        assertEquals("Pathname stored incorrectly", "", af.getPathname());
        /*
         * / Testfall2 Tabs NichtOK af.parsePathname("      ");
         * assertEquals("Pathname stored incorrectly", "       ", af.getPathname());
         */
        // Testfall3 OK
        af.parsePathname("file.mp3");
        assertEquals("Pathname stored incorrectly", "file.mp3", af.getPathname());
        // Testfall4 OK
        af.parsePathname("/my/file.mp3");
        assertEquals("Pathname stored incorrectly", "/my/file.mp3", af.getPathname());
        // Testfall5 OK
        af.parsePathname("///my/////file.mp3/");
        assertEquals("Pathname stored incorrectly", "/my/file.mp3/", af.getPathname());
        // Testfall6 OK
        af.parsePathname("c:\\\\/////my\\\\///file.mp3");
        assertEquals("Pathname stored incorrectly", "/c/my/file.mp3", af.getPathname());

        ////////////////////////////////////////////////////////////

        // Testfall1 OK
        af.parsePathname("");
        assertEquals("Pathname stored incorrectly", "", af.getFilename());
        // Testfall2 Tabs NichtOK
        af.parsePathname("      ");
        assertEquals("Pathname stored incorrectly", "       ", af.getFilename());
        // Testfall3 OK
        af.parsePathname("file.mp3");
        assertEquals("Pathname stored incorrectly", "file.mp3", af.getFilename());
        // Testfall4 OK
        af.parsePathname("/my/file.mp3");
        assertEquals("Pathname stored incorrectly", "file.mp3", af.getFilename());
        // Testfall5 OK
        af.parsePathname("///my/////file.mp3/");
        assertEquals("Pathname stored incorrectly", "", af.getFilename());
        // Testfall6 OK
        af.parsePathname("c:\\\\/////my\\\\///file.mp3");
        assertEquals("Pathname stored incorrectly", "file.mp3", af.getFilename());

    }

    @Test
    public void test_parseFilename_00() throws Exception {
        AudioFile af = new AudioFile();

        // Testfall1 OK
        af.parseFilename(" Falco - Rock me Amadeus .mp3");
        assertEquals("Pathname stored incorrectly", "Falco", af.getAuthor());
        // Testfall2 OK
        af.parseFilename("Frankie Goes To Hollywood - The Power Of Love.ogg");
        assertEquals("Pathname stored incorrectly", "Frankie Goes To Hollywood", af.getAuthor());
        // Testfall3 OK
        af.parseFilename("  A.U.T.O.R  - T.I.T.E.L  .EXXTENSION");
        assertEquals("Pathname stored incorrectly", "A.U.T.O.R", af.getAuthor());
        // Testfall4 OK
        af.parseFilename("Hans-Georg Sonstwas - Blue-eyed boy-friend.mp3");
        assertEquals("Pathname stored incorrectly", "Hans-Georg Sonstwas", af.getAuthor());
        // Testfall5 OK
        af.parseFilename("audiofile.aux");
        assertEquals("Pathname stored incorrectly", "", af.getAuthor());
        // Testfall6 OK
        af.parseFilename(".mp3");
        assertEquals("Pathname stored incorrectly", "", af.getAuthor());
        // Testfall7 OK
        af.parseFilename("Falco - Rock me Amadeus.");
        assertEquals("Pathname stored incorrectly", "Falco", af.getAuthor());
        // Testfall8 OK
        af.parseFilename("-");
        assertEquals("Pathname stored incorrectly", "", af.getAuthor());
        // Testfall9 OK
        af.parseFilename(" - ");
        assertEquals("Pathname stored incorrectly", "", af.getAuthor());

        //////////////////////////////////////////////////////////////////////////////////////////////////////////// 


        // Testfall1 OK
        af.parseFilename(" Falco - Rock me Amadeus .mp3");
        assertEquals("Pathname stored incorrectly", "Rock me Amadeus", af.getTitle());
        // Testfall2 OK
        af.parseFilename("Frankie Goes To Hollywood - The Power Of Love.ogg");
        assertEquals("Pathname stored incorrectly", "The Power of Love", af.getTitle());
        // Testfall3 OK
        af.parseFilename("  A.U.T.O.T  - T.I.T.E.L  .EXXTENSION");
        assertEquals("Pathname stored incorrectly", "T.I.T.E.L", af.getTitle());
        // Testfall4 OK
        af.parseFilename("Hans-Georg Sonstwas - Blue-eyed boy-friend.mp3");
        assertEquals("Pathname stored incorrectly", "Blue-eyed boy-friend", af.getTitle());
        // Testfall5 OK
        af.parseFilename("audiofile.aux");
        assertEquals("Pathname stored incorrectly", "audiofile", af.getTitle());
        // Testfall6 OK
        af.parseFilename(".mp3");
        assertEquals("Pathname stored incorrectly", "", af.getTitle());
        // Testfall7 OK
        af.parseFilename("Falco - Rock me Amadeus.");
        assertEquals("Pathname stored incorrectly", "Rock me Amadeus", af.getTitle());
        // Testfall8 OK
        af.parseFilename("-");
        assertEquals("Pathname stored incorrectly", "-", af.getTitle());
        // Testfall9 OK
        af.parseFilename(" - ");
        assertEquals("Pathname stored incorrectly", "", af.getTitle());

    
    }

}
