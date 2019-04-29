
import static org.junit.Assert.*;
import studiplayer.basic.TagReader;
import org.junit.Test;
import java.util.Map;
import jdk.nashorn.internal.ir.annotations.Ignore;

public class UTestTaggedFile {

    @Ignore@Test
    public void test_play_01() throws Exception {
        TaggedFile tf = new TaggedFile("audiofiles/Rock 812.mp3");
        tf.play();
    }

    @Ignore@Test
    public void test_play_02() throws Exception {
        TaggedFile tf = new TaggedFile("audiofiles/beethoven-ohne-album.mp3");
        tf.play();
    }

    @Ignore@Test
    public void test_play_03() throws Exception {
        TaggedFile tf = new TaggedFile("audiofiles/kein.wave.sondern.ogg");
        tf.play();
    }


    @Ignore@Test
    public void test_play_04() throws Exception {
        TaggedFile tf = new TaggedFile("audiofiles/Motiv 5. Symphonie von Beethoven.ogg");
        tf.play();
    }

    @Ignore@Test
    public void test_timeFormatter_01() {
        assertEquals("Wrong time format" , "05:05",
              TaggedFile.timeFormatter(305862000L));  
    }
    @Ignore@Test
    public void test_timeFormatter_02() throws Exception {
        try {
            // call method with time value that underflows out format
            TaggedFile.timeFormatter(-1L);
            // We should never get here
            fail("Time value underflows format; expecting expextion");
            }
        catch (RuntimeException e) {
            //Expected
        }
    }
    
    //Read all tags from a TaffedfILE
    //This test demonstrates the functionality of TagReadeer.readTags()
    @Test
    public void test_readTaggs_01() throws Exception {
        TaggedFile tf = new TaggedFile("audiofiles/kein.wav.sondern.ogg");
        Map<String, Object> tag_map = TagReader.readTags(tf.getPathname());
        for (String key : tag_map.keySet()) {
            System.out.printf("\nKey: %s\n", key);
            System.out.printf("Type of valu: %s\n", tag_map.get(key).getClass().toString());
            System.out.printf("Value: " + tag_map.get(key));
        }
    }
    
    @Ignore@Test
    public void test_readAndStore_01() throws Exception {
        TaggedFile tf = new TaggedFile();
        tf.readAndStoreTags("audiofiles/Rock 812.mp3");
        assertEquals("Wrong author", "Eisbach", tf.getAuthor());
        assertEquals("Wrong title", "Rock 812", tf.getTitle());
        assertEquals("Wrong album", "The Sea, the Sky", tf.getAlbum());
        assertEquals("Wrong time fomrat", "05:31", tf.getFormattedDuration());
    }   
}
