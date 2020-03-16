import java.awt.Color;
import java.awt.Graphics;

class Huepfer {

	/** Zum Zeichnen in Panel */
	Graphics graphics;

	/** Breite und Höhe Zeichen-Panel */
	int width, height;

	/** Minimal-/Maximalkoordinaten des logischen Koordinatensystems (LKOS) */
	double xMin, xMax, yMin, yMax;

	/** Hüpfer-Parameter */
	double a, b, c;

	/** Anzahl Punkte */
	int num;

	public Huepfer(Graphics graphics,
			int width, int height,
			double xMin, double xMax, double yMin, double yMax,
			double a, double b, double c, 
			int num) {
		super();
		this.graphics = graphics;
		this.width = width;
		this.height = height;
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
		this.a = a;
		this.b = b;
		this.c = c;
		this.num = num;
	}

	/**
	 * Methode zum Zeichnen eines Punktes. Die (0,0)-Koordinate wird nach links
	 * unten gelegt.
	 * 
	 * HACK: Zeichne Punkt als Linie der Länge 0. Es gibt in Java keine Methode zum
	 * Zeichne eines Einzelpunkts!
	 * 
	 * @param graphics Grafik-Kontext
	 * @param x x-Koordinate
	 * @param y y-Koordinate
	 */
	void setPixel(int x, int y) {
		graphics.drawLine(x, y, x, y);
	}

	/**
	 * Wandle LKOS-Koordinate in GKOS-Koordinate um.
	 * 
	 * @param x LKOS-Koordinate
	 * @return GKOS-Koordinate
	 */
	int transformX(double x) {
		// TODO Hier Code einfuegen ...
	}

	/**
	 * Wandle LKOS-Koordinate in GKOS-Koordinate um.
	 * 
	 * @param y LKOS-Koordinate
	 * @return GKOS-Koordinate
	 */
	int transformY(double y) {
		// TODO Hier Code einfuegen ...
	}

	public void render() {
		// TODO Ihre Implementierung des Hüpfer-Algorithmus ...
	}
}
