import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.junit.Test;



import jdk.nashorn.internal.ir.annotations.Ignore;

public class UTestPlayList {

    @Ignore
    @Test
    public void test_getCurrnetAudioFile_01() throws Exception {
        PlayList pl = new PlayList();
        assertEquals("Wrong currnet AudioFile", null, pl.getCurrentAudioFile());
    }

    @Ignore
    @Test
    public void test_getCurrnetAudioFile_02() throws Exception {
        PlayList pl = new PlayList();
        TaggedFile tf0 = new TaggedFile("audiofiles/Eisbach Deep Snow.ogg");
        pl.add(tf0);
        pl.setCurrent(10);

        assertEquals("Wrong currnet AudioFile", null, pl.getCurrentAudioFile());
    }

    @Ignore
    @Test
    public void test_getCurrnetAudioFile_03() throws Exception {
        PlayList pl = new PlayList();
        TaggedFile tf0 = new TaggedFile("audiofiles/Eisbach Deep Snow.ogg");
        TaggedFile tf1 = new TaggedFile("audiofiles/Rock 812.mp3");
        pl.add(tf0);
        pl.add(tf1);
        pl.setCurrent(1);
        assertEquals("Wrong current AudioFile", tf1, pl.getCurrentAudioFile());

        pl.remove(0);

        assertEquals("Wrong currnet AudioFile", null, pl.getCurrentAudioFile());
    }

    @Ignore
    @Test
    public void test_changeCurrent_01() throws Exception {
        PlayList pl = new PlayList();
        TaggedFile tf0 = new TaggedFile("audiofiles/Eisbach Deep Snow.ogg");
        TaggedFile tf1 = new TaggedFile("audiofiles/Rock 812.mp3");
        TaggedFile tf2 = new TaggedFile("audiofiles/tanom p2 journey.mp3");
        pl.add(tf0);
        pl.add(tf1);
        pl.add(tf2);
        pl.setCurrent(0);
        assertEquals("Wrong current Index", 0, pl.getCurrent());
        pl.changeCurrent();
        assertEquals("Wrong chnage in current index", 1, pl.getCurrent());
        pl.changeCurrent();
        assertEquals("Wrong chnage in current index", 2, pl.getCurrent());
        pl.changeCurrent();
        assertEquals("Wrong chnage in current index", 0, pl.getCurrent());

    }

    @Ignore
    @Test
    public void test_changeCurrent_02() throws Exception {
        PlayList pl = new PlayList();
        TaggedFile f0 = new TaggedFile("audiofiles/Eisbach Deep Snow.ogg");
        TaggedFile f1 = new TaggedFile("audiofiles/tanom p2 journey.mp3");
        TaggedFile f2 = new TaggedFile("audiofiles/Rock 812.mp3");
        WavFile f4 = new WavFile("audiofiles/wellenmeister - tranquility.wav");
        pl.add(f0);
        pl.add(f1);
        pl.add(f2);
        pl.add(f4);
        pl.setRandomOrder(true);
        // note: only the content of the list is shuffled

        for (int i = 0; i < 5 * pl.size(); i++) {
            System.out.printf("Pos=%d Filename=%s\n", pl.getCurrent(), pl.getCurrentAudioFile().getFilename());
            assertEquals("Wrong current index", i % pl.size(), pl.getCurrent());
            pl.changeCurrent();
            if (pl.getCurrent() == 0)
                System.out.println("");

        }
    }

    /* @Test
    public void test_ReadLinesFromFile_o1() throws Exception {
        FileWriter writer = null;

        String fname = "file.txt";
        //String linesep = System.getProperty("line.seperator");
         String linesep ="\n";
        try {
            // Create a file writer
            writer = new FileWriter(fname);
            //Write some strings to the file
            writer.write("# Moris Breiteborns erste Liste" + linesep);
            writer.write("Lin2" + linesep);
            
        } catch (IOException e) {
            throw new RuntimeException("Unable to write to file " + fname + ":" + e.getMessage());
        } finally {
            try {
                writer.close();
            } catch (Exception e) {

            }

        }
    }
    
   /* @Test
    public void test_ReadLinesFromFile_01() throws Exception {
        String fname = "file.txt";
        Scanner scanner = null;
        String line;
        try {
            //Create a Scanner
            scanner = new Scanner(new File(fname));
            
            //read line by line
            int i =1;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                System.out.println("Got line " + i + "|" +line +"|");
                i++;
            }
        }catch (IOException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
            scanner.close();
            }catch (Exception e) {
            
        }
        
    }

}*/
    
    
    
}
