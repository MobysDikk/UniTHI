import java.awt.Graphics;

public class CohenSutherland {
	/** Zum Zeichnen */
	private Graphics graphics;

	/** Dimension des Clipping-Rechtecks */
	private int xmin;
	private int xmax;
	private int ymin;
	private int ymax;

	/**
	 * Der Konstruktor ist mehr dem Testgedöns geschuldet als ein Highlight des
	 * Sofware-Engineerings.
	 * 
	 * @param graphics Zum Zeichnen
	 * @param xmin     minimale x-Koordinate
	 * @param ymin     minimale y-Koordinate
	 * @param xmax     maximale x-Koordinate
	 * @param ymax     maximale y-Koordinate
	 */
	public CohenSutherland(Graphics graphics, int xmin, int ymin, int xmax, int ymax) {
		super();
		this.graphics = graphics;
		this.xmin = xmin;
		this.ymin = ymin;
		this.xmax = xmax;
		this.ymax = ymax;
	}

	/**
	 * Berechne den Cohen-Sutherland-Outputcode für einen Punkt.
	 * 
	 * @formatter:off
	 * viertletztes Bit = 1 <=> y > ymax 
	 * drittletztes Bit = 1 <=> y < ymin
	 * vorletztes Bit = 1 <=> x > xmax 
	 * letztes Bit = 1 <=> x < xmin
	 * @formatter:on
	 * 
	 * Die 4 Bits werden sehr verschwenderisch in einem int untergebracht.
	 * 
	 * Warum kein byte? Die bitweisen Operationen sind für Datentyp byte nicht
	 * definiert! Genauer werden z.B. die Bytes bei byte1 | byte2 zu ints gecastet
	 * und das Ergebnis ist ein int.
	 * Mehr Details: <a href="https://stackoverflow.com/questions/27582233/why-byte-and-short-values-are-promoted-to-int-when-an-expression-is-evaluated">Stack Overflow</a>
	 * 
	 * @param x x-Koordinate Punkt
	 * @param y y-Koordinate Punkt
	 * @return Outputcode
	 */
	int outputCode(int x, int y) {

		// TODO: Ihr Code hier ...

	}

	/**
	 * Clipping nach Cohen-Sutherland. Die Linie von (xA,yA) nach (xE,yE) wird an
	 * dem durch die Attribute (xmin,ymin) und (xmax,ymax) definierten Rechteck
	 * geclippt und der sichtbare Teil der Linie gezeichnet.
	 * 
	 * @param xA x-Koordinate Anfangspunkt Linie
	 * @param yA y-Koordinate Anfangspunkt Linie
	 * @param xE x-Koordinate Endpunkt Linie
	 * @param yE y-Koordinate Endpunkt Linie
	 */
	void clipLine(int xA, int yA, int xE, int yE) {

		// TODO: Ihr Code hier ...

	}

	/**
	 * Unspannende Hilfsroutine zum Clippen mehrerer Linien.
	 * 
	 * Linie i wird über Anfangspunkt A = (xA, yA) und Endpunkt E = (xE, yE) definiert. Dabei ist
	 * 
	 * @formatter:off
	 * points[i][0] = xA
	 * points[i][1] = yA
	 * points[i][2] = xE
	 * points[i][3] = yE
	 * @formatter:on
	 * 
	 * @param points Array von zu zeichnenden Linien, definiert über Koordinaten von Anfangs- und Endpunkt
	 */
	public void clipLines(int[][] points) {
		for (int i = 0; i < points.length; i++) {
			clipLine(points[i][0], points[i][1], points[i][2], points[i][3]);
		}
	}

}
