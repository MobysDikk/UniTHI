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
     * @param x0
     *            x-Koordinate Startpunkt
     * @param y0
     *            y-Koordinate Startpunkt
     * @param x1
     *            x-Koordinate Endpunkt
     * @param y1
     *            y-Koordinate Endpunkt
     */
    void drawLineEquation(int x0, int y0, int x1, int y1) {
        // TODO Hier Code einfuegen ...

        int y = 0;
        double m = 0;
        double b = 0;
        m = ((double)(y1 - y0)) / ((double)(x1 - x0));
        b = y0 - (m * x0);
        for (int i = x0; i <= x1; i++) {
            y = (int) (((m * i) + b )+ 0.5);
            setPixel(i, y);

        }
    }

    // Shift geeignet bis Fensterhöhe 8192
    private final static int SHIFT = 18;
    private final static int GAMMA = (2 << (SHIFT - 1));

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
        
        int b = 2<<SHIFT;
        int deltY= y1 - y0;
        int deltX= x1 - x0;
        int m = (deltY*b)/deltX;
        int y = y0*b+GAMMA;
        for(int i = x0; i<= x1; i++) {
            setPixel(i,y/b);
            y+=m;
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
        
        int dx= x1-x0;
        int dy= y1-y0;
        int d= 2*dy-dx;
        int delE= 2*dy;
        int delNE = 2*(dy-dx);
        int x = x0;
        int y = y0;
        
        setPixel(x0,y0);
        while(x<x1) {
            if(d<0) {
                d=d+delE;
                x++;
            }else {
                d=d+delNE;
                x++;
                y++;
            }
            setPixel(x,y);
        }
        
        
    }
}