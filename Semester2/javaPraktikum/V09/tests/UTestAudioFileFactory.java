import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;
import studiplayer.audio.AudioFileFactory;
import studiplayer.audio.NotPlayableException;
import studiplayer.audio.PlayList;

public class UTestAudioFileFactory {
    
    
    
    // RuntimeExceptions
   /* @Ignore@Test
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
    @Ignore@Test
    public void test_getInstance_03() throws Exception {
        AudioFile af1 = AudioFileFactory.getInstance("audiofiles/Eisbach Deep Snow.ogg");
        assertTrue("Ecpecting object of type TaggedFile", (af1 instanceof TaggedFile));
        
        AudioFile af2 = AudioFileFactory.getInstance("audiofiles/wellenmeister - tranquility.wav");
        assertTrue("Ecpecting object of type WavFile", (af2 instanceof WavFile));
        
        AudioFile af3 = AudioFileFactory.getInstance("audiofiles/special.oGg");
        assertTrue("Ecpecting object of type TaggedFile", (af3 instanceof TaggedFile));
    }*/
    
    // NotPlayableException 
    @Test
    public void test_getInstance_o4() throws Exception{
        try {
            AudioFileFactory.getInstance("unknown.xxx");
            fail("Unknow suffix; expecting exception");
        }catch (NotPlayableException e) {
            
        }
        }
    @Test
    public void test_getInstance_o5() throws Exception{
        try {
            AudioFileFactory.getInstance("unknown.mp3");
            fail("Unknow suffix; expecting exception");
        }catch (NotPlayableException e) {
            
        }
        }
   
    
    @Test
    public void test_loadFromM3U_02() throws Exception{
        String m3u_pathname = "playlist.m3u";
        String mp3_pathname ="cprrupt.mp3";
        
        // Create teh M3U file with one entry for a non-existetn mp3 file
        FileWriter writer = null;
        try {
            // Create  FileWriter
            writer = new FileWriter(m3u_pathname);
            writer.write(mp3_pathname + System.getProperty("line.seperator"));
            } catch (IOException e) {
                throw new RuntimeException("Unable to store M3U file: " + m3u_pathname, e);
            }finally {
                try {
                    writer.close();
                }catch(IOException e) {
                    //Just swallow
                }
            }
        
        // the playlist for testing is in place
        PlayList pl = new PlayList();
        pl.loadFromM3U(m3u_pathname);
        // cleanup
        new File(m3u_pathname).delete();
    }
    

    
}
