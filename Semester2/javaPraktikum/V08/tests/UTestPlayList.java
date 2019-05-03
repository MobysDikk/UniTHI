import static org.junit.Assert.*;

import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;

public class UTestPlayList {

    @Test
    public void test_getCurrnetAudioFile_01() throws Exception {
        PlayList pl = new PlayList();
        assertEquals("Wrong currnet AudioFile", null, pl.getCurrentAudioFile());
    }
    
    @Test
    public void test_getCurrnetAudioFile_02() throws Exception {
        PlayList pl = new PlayList();
        TaggedFile tf0 = new TaggedFile("audiofiles/Eisbach Deep Snow.ogg");
        pl.add(tf0);
        pl.setCurrent(10);
        
        
        assertEquals("Wrong currnet AudioFile", null, pl.getCurrentAudioFile());
    }
    
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
    
    /*@Ignore@Test
    public void test_changeCurrent_01() throws Exception {
        PlayList pl = new PlayList();
        TaggedFile tf0 = new TaggedFile("audiofiles/Eisbach Deep Snow.ogg");
        TaggedFile tf1 = new TaggedFile("audiofiles/Rock 812.mp3");
        TaggedFile tf2 = new TaggedFile("audiofiles/tanom p2 journey.mp3");
        pl.add(tf0);
        pl.add(tf1);
        pl.add(tf2);
        pl.setCurrent(0);
        assertEquals("Wrong current Index",0, pl.getCurrent());
        pl.changeCurrent();
        assertEquals("Wrong chnage in current index", 1, pl.getCurrent());
        pl.changeCurrent();
        assertEquals("Wrong chnage in current index", 2, pl.getCurrent());
        pl.changeCurrent();
        assertEquals("Wrong chnage in current index", 0, pl.getCurrent()); 
        
       
    }*/
    
    

}
