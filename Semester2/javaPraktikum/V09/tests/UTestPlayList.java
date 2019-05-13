import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.junit.Test;



import jdk.nashorn.internal.ir.annotations.Ignore;
import studiplayer.audio.AudioFile;
import studiplayer.audio.PlayList;
import studiplayer.audio.SortCriterion;
import studiplayer.audio.TaggedFile;
import studiplayer.audio.WavFile;

public class UTestPlayList {

    //@Ignore
    @Test
    public void test_getCurrnetAudioFile_01() throws Exception {
        PlayList pl = new PlayList();
        assertEquals("Wrong currnet AudioFile", null, pl.getCurrentAudioFile());
    }

    //@Ignore
    @Test
    public void test_getCurrnetAudioFile_02() throws Exception {
        PlayList pl = new PlayList();
        TaggedFile tf0 = new TaggedFile("audiofiles/Eisbach Deep Snow.ogg");
        pl.add(tf0);
        pl.setCurrent(10);

        assertEquals("Wrong currnet AudioFile", null, pl.getCurrentAudioFile());
    }

    //@Ignore
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

    //@Ignore
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

    //@Ignore
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

    @Ignore@Test
    public void test_sort_byTitle_01() throws Exception {
        PlayList pl1 = new PlayList();
        //Populate the playlist
        pl1.add(new TaggedFile("audiofiles/Eisbach Deep Snow.ogg"));
        pl1.add(new WavFile("audiofiles/wellenmeister - tranquility.wav"));
        pl1.add(new TaggedFile("audiofiles/wellenmeister_awakening.ogg"));
        pl1.add(new TaggedFile("audiofiles/tanom p2 journey.mp3"));
        pl1.add(new TaggedFile("audiofiles/Rock 812.mp3"));
        // Sort the playlist by title
        pl1.sort(SortCriterion.TITLE);
        // Store the toString() strings of the sorted play list inot an array
        // and compare the arrays
        // Note: "T*" < "t*"
        String exp[] = new String[] {
                "Eisbach - Deep Snow - The Sea, the Sky - 03:18",
                "Eisbach - Rock 812 - The Sea, the Sky - 05:31",
                "Wellenmeister - TANOM Part I: Awakening - TheAbsoluteNecessityOfMeaning - 05:55",
                "Wellenmeister - TANOM Part II: Journey - TheAbsoluteNecessityOfMeaning - 02:52",
                "wellenmeister - tranquility - 02:21",};
        String sorted [] = new String[5];
        int i =0;
        for (AudioFile af : pl1) {
            sorted[i] = af.toString();
            i++;
        }
        assertArrayEquals("Wrong sorting by title", exp, sorted);
    }
    
    @Test
    public void test_sort_byDuration_01() throws Exception {
        PlayList pl1 = new PlayList();
        //Populate the playlist
        pl1.add(new TaggedFile("audiofiles/Eisbach Deep Snow.ogg"));
        pl1.add(new WavFile("audiofiles/wellenmeister - tranquility.wav"));
        pl1.add(new TaggedFile("audiofiles/wellenmeister_awakening.ogg"));
        pl1.add(new TaggedFile("audiofiles/tanom p2 journey.mp3"));
        pl1.add(new TaggedFile("audiofiles/Rock 812.mp3"));
        // Sort the playlist by title
        pl1.sort(SortCriterion.DURATION);
        // Store the toString() strings of the sorted play list inot an array
        // and compare the arrays
        // Note: "T*" < "t*"
        String exp[] = new String[] {
                "wellenmeister - tranquility - 02:21",
                "Wellenmeister - TANOM Part II: Journey - TheAbsoluteNecessityOfMeaning - 02:52",
                "Eisbach - Deep Snow - The Sea, the Sky - 03:18",
                "Eisbach - Rock 812 - The Sea, the Sky - 05:31",
                "Wellenmeister - TANOM Part I: Awakening - TheAbsoluteNecessityOfMeaning - 05:55",};
        String sorted [] = new String[5];
        int i =0;
        for (AudioFile af : pl1) {
            sorted[i] = af.toString();
            i++;
        }
        assertArrayEquals("Wrong sorting by duration", exp, sorted);
    }
    
}
