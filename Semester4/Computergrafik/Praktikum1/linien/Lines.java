import java.awt.Graphics;

public class Lines {
    private Graphics graphics;

    /**
     * Verschiedene Methoden zum Zeichnen von Linien
     * 
     * @param graphics
     *            Grafik-Kontext, in den gezeichnet wird
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
     * @param x
     *            x-Koordinate
     * @param y
     *            y-Koordinate
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
	    
	    int y=0;
	    int m=0;
	    int b=0;
	    m=(y1-y0)/(x1-x0);
	    b= y0-(m*x0);
	    for(int i =x0; i<x1;i++) {
	        y=(m*i)+b;
	        setPixel(i,y);
	        
	    }
	}

    // Shift geeignet bis Fensterhöhe 8192
    private final static int SHIFT = 18;
    private final static int GAMMA = (1 << (SHIFT - 1));

    /**
     * Linien-Berechnung über Digital Differential Analyzer (DDA)
     * 
     * @param x0
     *            x-Koordinate Startpunkt
     * @param y0
     *            y-Koordinate Startpunkt
     * @param x1
     *            x-Koordinate Endpunkt
     * @param y1
     *            y-Koordinate Endpunkt
     */
    void drawDda(int x0, int y0, int x1, int y1) {
        // TODO Hier Code einfuegen ...
        
        int b = 100;
        int m =0;
        int y =0;
        m=((y1-y0)*(2<<b))/(x1-x0);
        y=y0*(2<<b)+(2<<(b-1));
        for(int i = x0; i<x1;i++) {
            setPixel(i,y>>b);
            y= y+m;
        }
    }

    /**
     * Linien-Berechnung über Bresenham
     * 
     * @param x0
     *            x-Koordinate Startpunkt
     * @param y0
     *            y-Koordinate Startpunkt
     * @param x1
     *            x-Koordinate Endpunkt
     * @param y1
     *            y-Koordinate Endpunkt
     */
    void drawBresenham(int x0, int y0, int x1, int y1) {
        // TODO Hier Code einfuegen ...
    }
}
