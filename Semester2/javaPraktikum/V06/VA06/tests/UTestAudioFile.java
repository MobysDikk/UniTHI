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
        af.parseFilename("");
        assertEquals("Pathname stored incorrectly", "", af.getAuthor());
        // Testfall1 OK
        af.parseFilename("");
        assertEquals("Pathname stored incorrectly", "", af.getAuthor());
        // Testfall1 OK
        af.parseFilename("");
        assertEquals("Pathname stored incorrectly", "", af.getAuthor());
        // Testfall1 OK
        af.parseFilename("");
        assertEquals("Pathname stored incorrectly", "", af.getAuthor());
        // Testfall1 OK
        af.parseFilename("");
        assertEquals("Pathname stored incorrectly", "", af.getAuthor());
        // Testfall1 OK
        af.parseFilename("");
        assertEquals("Pathname stored incorrectly", "", af.getAuthor());

        //////////////////////////////////////////////////////////////////////////////////////////////////////////// 

        // Testfall1 OK
        af.parseFilename("");
        assertEquals("Pathname stored incorrectly", "", af.getTitle());
        // Testfall1 OK
        af.parseFilename("");
        assertEquals("Pathname stored incorrectly", "", af.getTitle());
        // Testfall1 OK
        af.parseFilename("");
        assertEquals("Pathname stored incorrectly", "", af.getTitle());
        // Testfall1 OK
        af.parseFilename("");
        assertEquals("Pathname stored incorrectly", "", af.getTitle());
        // Testfall1 OK
        af.parseFilename("");
        assertEquals("Pathname stored incorrectly", "", af.getTitle());
        // Testfall1 OK
        af.parseFilename("");
        assertEquals("Pathname stored incorrectly", "", af.getTitle());

    }

}
