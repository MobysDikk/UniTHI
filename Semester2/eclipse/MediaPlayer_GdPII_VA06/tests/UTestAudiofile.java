import junit.framework.TestCase;

//import org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
public class UTestAudiofile extends TestCase {
	@Test
	public void test_parsePathname_03() throws Exception {
		AudioFile af = new AudioFile();
		af.parsePathname("/my-tmp/file.mp3");
		char sepchar = java.io.File.separatorChar;
		//
		//
		assertEquals("Pathname stored incorrectly", 
				sepchar + "my-tmp" +sepchar + "file.mp3" ,
				af.getPathname());
		assertEquals("Returned filenam is incorrect",
				"file.mp3",
				af.getFilename());
	}

}
