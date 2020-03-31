import java.awt.Graphics;

import colorscheme.ColorScheme;

public class Mandelbrot {
	/** Graphics-Objekt zum Zeichnen */
	private Graphics graphics;
	/** Fensterdimensionen */
	private int width;
	private int height;
	/** Minimal- und Maximalkoordinaten des logischen Koordinatensystems */
	private double xMin;
	private double yMin;
	private double xMax;
	private double yMax;
	/**
	 * Maximalzahl der Iterationen pro Bildpunkt, falls erreicht, handelt es sich um
	 * einen Punkt der Mandelbrotmenge
	 */
	private int maxiter;
	/** Farbschema */
	private ColorScheme colorScheme;

	/**
	 * Initialisiere den Mandelbrot-Renderer.
	 * 
	 * @param graphics    Graphics-Objekt zum Zeichnen
	 * @param width       Breite Fenster
	 * @param height      Höhe Fenster
	 * @param xMin        minimale logische x-Koordinate
	 * @param yMin        minimale logische y-Koordinate
	 * @param xMax        maximale logische x-Koordinate
	 * @param yMax        maximale logische y-Koordinate
	 * @param maxiter     maximale Zahl der Iterationen
	 * @param colorScheme ein Farbschema aus dem Paket colorscheme
	 */
	public Mandelbrot(Graphics graphics, int width, int height, double xMin, double yMin, double xMax, double yMax,
			int maxiter, ColorScheme colorScheme) {
		this.graphics = graphics;
		this.width = width;
		this.height = height;
		this.xMin = xMin;
		this.yMin = yMin;
		this.xMax = xMax;
		this.yMax = yMax;
		this.maxiter = maxiter;
		this.colorScheme = colorScheme;
	}

	/**
	 * Methode zum Zeichnen eines Punktes. Die (0,0)-Koordinate wird nach links
	 * unten gelegt.
	 * 
	 * HACK: Zeichne Punkt als Linie der Länge 0. Es gibt in Java keine Methode zum
	 * Zeichne eines Einzelpunkts!
	 * 
	 * @param g Grafik-Kontext
	 * @param x x-Koordinate
	 * @param y y-Koordinate
	 */
	private void setPixel(Graphics g, int x, int y) {
		if (x >= 0 && x < width && y >= 0 && y < height)
			g.drawLine(x, y, x, y);
	}

	/**
	 * Wandle GKOS-Koordinate in LKOS-Koordinate um.
	 * 
	 * @param px GKOS-Koordinate
	 * @return LKOS-Koordinate
	 */
	double transformPx(int px) {
		return ((px * (xMax - xMin)) / (width)) + xMin;
	}

	/**
	 * Wandle GKOS-Koordinate in LKOS-Koordinate um.
	 * 
	 * @param py GKOS-Koordinate
	 * @return LKOS-Koordinate
	 */
	double transformPy(int py) {
		return ((py * (yMin - yMax)) / (height)) + yMax;
	}

	/**
	 * Multiplizieren von zwei komplexen Zahlen
	 */
	public double komplexMultiplier(double r, double i) {
		return (r * r) + (i * i);
	}

	/**
	 * Zeichnen der Mandelbrotmenge
	 */
	public void render() {
		// Iterieren über komplexe Zahlenebene
		for (int px = 0; px < width; px++) {
			for (int py = 0; py < height; py++) {
				// Zähler für die Anzahl der Iterationen
				int iter = 0;
				// TODO 1: Hier aus px und py Real- und Imaginärteil einer komplexen Zahl c
				// ausrechnen.
				// Real- und Imaginärteil von z initialisieren (s. Angabe).

				double realC = transformPx(px);
				double imaC = transformPy(py);

				double realZ = 0;
				double imaZ = 0;
				double zMalZ = komplexMultiplier(realZ, imaZ);

				// TODO 2: Hier Schleife einfügen, die solange ausgeführt wird, wie |z|*|z| <
				// 4 und
				// Maximalzahl Iterationen noch nicht überschritten. Im Schleifenrumpf soll die
				// komplexe Zahl z nach der Formel z = z*z + c aktualisiert werden.

				while (iter < maxiter && zMalZ < 4) {
					
					// es gilt f(z) = z^2 +c
					// z0 ist daher = c
					// n�chste Generation w�re daher c^2+c
					// daraus folgt: (realZ+imaZ)*(realZ+imaZ)...
					// siehe zur Erinnerung https://www.youtube.com/watch?v=6z7GQewK-Ks
					
					double temp = realZ * realZ - imaZ * imaZ + realC;
					imaZ = 2 * realZ * imaZ + imaC;
					realZ = temp;
					
					iter++;
					zMalZ = komplexMultiplier(realZ, imaZ);

				}

				graphics.setColor(colorScheme.colorForNumIterations(iter));
				setPixel(graphics, px, py);
			}
		}
	}
}
