import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

class Huepfer {
    
    /** Color*/
    int c1;
    int c2;
    int c3;
    int cCounter=0;
    

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
		
		if(cCounter%100==0) {
		    Random rand = new Random();
		    c1 = rand.nextInt(255);
		    c2 = rand.nextInt(255);
		    c3 = rand.nextInt(255);
		    graphics.setColor(new Color(c1+1,c2+1,c3+1));
		    
		    
		}
		
		    
		}
		    

	/**
	 * Wandle LKOS-Koordinate in GKOS-Koordinate um.
	 * 
	 * @param x LKOS-Koordinate
	 * @return GKOS-Koordinate
	 */
	int transformX(double x) {
		
	    return (int) ((width/(xMax-xMin))*(x-xMin)+0.5);
	}

	/**
	 * Wandle LKOS-Koordinate in GKOS-Koordinate um.
	 * 
	 * @param y LKOS-Koordinate
	 * @return GKOS-Koordinate
	 */
	int transformY(double y) {
		
	    return (int) ((height/(yMin-yMax))*(y-yMax)+0.5);
	}

	public void render() {
		
	    double x = 0;
	    double y = 0;
	    for (int i = 0; i <num ; i++) {
	        setPixel(transformX(x),transformY(y));
	        double xx = y- Math.signum(x)*(Math.sqrt(Math.abs((this.b*x)-this.c))); 
	        double yy = a - x;
	        x = xx;
	        y = yy;
	        cCounter++;
	        
	    }
	    
	    
	}
}