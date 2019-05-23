import static org.junit.Assert.*;

import org.junit.Test;

import studiplayer.audio.PlayList;
import studiplayer.audio.TaggedFile;
import studiplayer.audio.WavFile;

public class UTestM3U {

    @Test
    public void test_M3U_02() throws Exception {
        PlayList pl = new PlayList();
        TaggedFile f0 = new TaggedFile("audiofiles/Eisbach Deep Snow.ogg");
        TaggedFile f1 = new TaggedFile("audiofiles/tanom p2 journey.mp3");
        TaggedFile f2 = new TaggedFile("audiofiles/Rock 812.mp3");
        WavFile f4 = new WavFile("audiofiles/wellenmeister - tranquility.wav");
        pl.add(f0);
        pl.add(f1);
        pl.add(f2);
        pl.add(f4);

}}
