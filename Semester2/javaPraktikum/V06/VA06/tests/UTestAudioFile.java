import static org.junit.Assert.*;

import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;

public class UTestAudioFile {

    @Ignore
    @Test
    public void test() {
        fail("Not yet implemented");
    }
   @Test
    public void test_parsePathAndFilename_10() throws Exception {
        AudioFile af = new AudioFile();
        
        af.parsePathname("");              // ganzer pfadname mit file
        assertEquals("", af.getPathname());// was als pfad raus kommen soll
        assertEquals("", af.getFilename());// wie der file aussehen soll
        af.parseFilename(af.getFilename());
        assertEquals("", af.getAuthor());  // nur author
        assertEquals("", af.getTitle());   // nur titel
        assertEquals("", af.toString());   // autor - titel

        af.parsePathname("/tmp/test/   A.U.T.O.R  -  T.I.T.E.L   .Extensions");
        assertEquals("Pathname stored incorrectly", "/tmp/test/   A.U.T.O.R  -  T.I.T.E.L   .Extensions", af.getPathname());
        assertEquals("Filename stored incorrectly", "   A.U.T.O.R  -  T.I.T.E.L   .Extensions", af.getFilename());
        af.parseFilename(af.getFilename());
        assertEquals("Author stored incorrectly", "A.U.T.O.R", af.getAuthor());
        assertEquals("Title  stored incorrectly", "T.I.T.E.L", af.getTitle());
        assertEquals("Title  stored incorrectly", "A.U.T.O.R - T.I.T.E.L", af.toString());

        af.parsePathname("/tmp/test/   A.U.T.O.R  -  T.I.T.E.L   .Extensions");
        assertEquals("Pathname stored incorrectly", "/tmp/test/   A.U.T.O.R  -  T.I.T.E.L   .Extensions", af.getPathname());
        assertEquals("Filename stored incorrectly", "   A.U.T.O.R  -  T.I.T.E.L   .Extensions", af.getFilename());
        af.parseFilename(af.getFilename());
        assertEquals("Author stored incorrectly", "A.U.T.O.R", af.getAuthor());
        assertEquals("Title  stored incorrectly", "T.I.T.E.L", af.getTitle());
        assertEquals("Title  stored incorrectly", "A.U.T.O.R - T.I.T.E.L", af.toString());

        af.parsePathname("/tmp/test/   A.U.T.O.R  -  T.I.T.E.L   .Extensions");
        assertEquals("Pathname stored incorrectly", "/tmp/test/   A.U.T.O.R  -  T.I.T.E.L   .Extensions", af.getPathname());
        assertEquals("Filename stored incorrectly", "   A.U.T.O.R  -  T.I.T.E.L   .Extensions", af.getFilename());
        af.parseFilename(af.getFilename());
        assertEquals("Author stored incorrectly", "A.U.T.O.R", af.getAuthor());
        assertEquals("Title  stored incorrectly", "T.I.T.E.L", af.getTitle());
        assertEquals("Title  stored incorrectly", "A.U.T.O.R - T.I.T.E.L", af.toString());

        af.parsePathname("/tmp/test/   A.U.T.O.R  -  T.I.T.E.L   .Extensions");
        assertEquals("Pathname stored incorrectly", "/tmp/test/   A.U.T.O.R  -  T.I.T.E.L   .Extensions", af.getPathname());
        assertEquals("Filename stored incorrectly", "   A.U.T.O.R  -  T.I.T.E.L   .Extensions", af.getFilename());
        af.parseFilename(af.getFilename());
        assertEquals("Author stored incorrectly", "A.U.T.O.R", af.getAuthor());
        assertEquals("Title  stored incorrectly", "T.I.T.E.L", af.getTitle());
        assertEquals("Title  stored incorrectly", "A.U.T.O.R - T.I.T.E.L", af.toString());

        af.parsePathname("/tmp/test/   A.U.T.O.R  -  T.I.T.E.L   .Extensions");
        assertEquals("Pathname stored incorrectly", "/tmp/test/   A.U.T.O.R  -  T.I.T.E.L   .Extensions", af.getPathname());
        assertEquals("Filename stored incorrectly", "   A.U.T.O.R  -  T.I.T.E.L   .Extensions", af.getFilename());
        af.parseFilename(af.getFilename());
        assertEquals("Author stored incorrectly", "A.U.T.O.R", af.getAuthor());
        assertEquals("Title  stored incorrectly", "T.I.T.E.L", af.getTitle());
        assertEquals("Title  stored incorrectly", "A.U.T.O.R - T.I.T.E.L", af.toString());

        af.parsePathname("/tmp/test/   A.U.T.O.R  -  T.I.T.E.L   .Extensions");
        assertEquals("Pathname stored incorrectly", "/tmp/test/   A.U.T.O.R  -  T.I.T.E.L   .Extensions", af.getPathname());
        assertEquals("Filename stored incorrectly", "   A.U.T.O.R  -  T.I.T.E.L   .Extensions", af.getFilename());
        af.parseFilename(af.getFilename());
        assertEquals("Author stored incorrectly", "A.U.T.O.R", af.getAuthor());
        assertEquals("Title  stored incorrectly", "T.I.T.E.L", af.getTitle());
        assertEquals("Title  stored incorrectly", "A.U.T.O.R - T.I.T.E.L", af.toString());

        af.parsePathname("/tmp/test/   A.U.T.O.R  -  T.I.T.E.L   .Extensions");
        assertEquals("Pathname stored incorrectly", "/tmp/test/   A.U.T.O.R  -  T.I.T.E.L   .Extensions", af.getPathname());
        assertEquals("Filename stored incorrectly", "   A.U.T.O.R  -  T.I.T.E.L   .Extensions", af.getFilename());
        af.parseFilename(af.getFilename());
        assertEquals("Author stored incorrectly", "A.U.T.O.R", af.getAuthor());
        assertEquals("Title  stored incorrectly", "T.I.T.E.L", af.getTitle());
        assertEquals("Title  stored incorrectly", "A.U.T.O.R - T.I.T.E.L", af.toString());

        af.parsePathname("/tmp/test/   A.U.T.O.R  -  T.I.T.E.L   .Extensions");
        assertEquals("Pathname stored incorrectly", "/tmp/test/   A.U.T.O.R  -  T.I.T.E.L   .Extensions", af.getPathname());
        assertEquals("Filename stored incorrectly", "   A.U.T.O.R  -  T.I.T.E.L   .Extensions", af.getFilename());
        af.parseFilename(af.getFilename());
        assertEquals("Author stored incorrectly", "A.U.T.O.R", af.getAuthor());
        assertEquals("Title  stored incorrectly", "T.I.T.E.L", af.getTitle());
        assertEquals("Title  stored incorrectly", "A.U.T.O.R - T.I.T.E.L", af.toString());
        
         
    }
   
   
   
}

       