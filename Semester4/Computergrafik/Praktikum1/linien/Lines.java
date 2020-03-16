import java.awt.Graphics;

public class Lines {
	private Graphics graphics;

	/**
	 * Verschiedene Methoden zum Zeichnen von Linien
	 * 
	 * @param graphics Grafik-Kontext, in den gezeichnet wird
	 */
	public Lines(Graphics graphics) {
		this.graphics = graphics;
	}

	/**
	 * Methode zum Zeichnen eines Punktes.
	 * 
	 * HACK: Zeichne Punkt als Linie der Länge 0. Es gibt in Java2D keine Methode
	 * zum Zeichnen eines Einzelpunkts!
	 * 
	 * @param x x-Koordinate
	 * @param y y-Koordinate
	 */
	void setPixel(int x, int y) {
		graphics.drawLine(x, y, x, y);
	}

	/**
	 * Konventionelle Linien-Berechnung über y = m*x + b
	 * 
	 * @param x0 x-Koordinate Startpunkt
	 * @param y0 y-Koordinate Startpunkt
	 * @param x1 x-Koordinate Endpunkt
	 * @param y1 y-Koordinate Endpunkt
	 */
	void drawLineEquation(int x0, int y0, int x1, int y1) {
		// TODO Hier Code einfuegen ...
	}

	// Shift geeignet bis Fensterhöhe 8192
	private final static int SHIFT = 18;
	private final static int GAMMA = (1 << (SHIFT - 1));

	/**
	 * Linien-Berechnung über Digital Differential Analyzer (DDA)
	 * 
	 * @param x0 x-Koordinate Startpunkt
	 * @param y0 y-Koordinate Startpunkt
	 * @param x1 x-Koordinate Endpunkt
	 * @param y1 y-Koordinate Endpunkt
	 */
	void drawDda(int x0, int y0, int x1, int y1) {
		// TODO Hier Code einfuegen ...
	}

	/**
	 * Linien-Berechnung über Bresenham
	 * 
	 * @param x0 x-Koordinate Startpunkt
	 * @param y0 y-Koordinate Startpunkt
	 * @param x1 x-Koordinate Endpunkt
	 * @param y1 y-Koordinate Endpunkt
	 */
	void drawBresenham(int x0, int y0, int x1, int y1) {
		// TODO Hier Code einfuegen ...
	}
}
