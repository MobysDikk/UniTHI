import static org.junit.Assert.assertEquals;

import java.awt.Graphics;

import org.junit.Test;

public class HuepferTest {
	
	String expected = 
			"....................\n" + 
			"....................\n" + 
			"....................\n" + 
			"....................\n" + 
			"....................\n" + 
			"....................\n" + 
			"..........XXX.......\n" + 
			".........XX.X.......\n" + 
			"........XX..X.......\n" + 
			".......XXXXXXX......\n" + 
			"......XXXXXXXX......\n" + 
			".....XXXXXX.X.......\n" + 
			".....X..XX..X.......\n" + 
			".....XXXXXXXX.......\n" + 
			".......XXX..........\n" + 
			"....................\n" + 
			"....................\n" + 
			"....................\n" + 
			"....................\n" + 
			"....................\n";
	
	
	@Test
	public void testHuepfer() {
		int width = 20;
		int height = 20;
		Graphics g = new BufferedGraphics(width, height);
		
		Huepfer h = new Huepfer(g, //
				width, //
				height, //
				-4.0, // xMin
				5.0, // xMax
				-4.0, // yMin
				5.0, // yMax
				0.4, // a
				1.0, // b
				0.0, // c
				100_000 // num
		);
		h.render();
		
		assertEquals("", expected, g.toString());
	}

}
