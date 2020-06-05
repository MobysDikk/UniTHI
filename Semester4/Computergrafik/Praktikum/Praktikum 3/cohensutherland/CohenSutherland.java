import java.awt.Graphics;
import java.awt.Rectangle;

public class CohenSutherland {
	/** Zum Zeichnen */
	private Graphics graphics;

	/** Dimension des Clipping-Rechtecks */
	private int xmin;
	private int xmax;
	private int ymin;
	private int ymax;

	/**
	 * Der Konstruktor ist mehr dem Testged√∂ns geschuldet als ein Highlight des
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
	 * Berechne den Cohen-Sutherland-Outputcode f√ºr einen Punkt.
	 * 
	 * @formatter:off viertletztes Bit = 1 <=> y > ymax drittletztes Bit = 1 <=> y <
	 *                ymin vorletztes Bit = 1 <=> x > xmax letztes Bit = 1 <=> x <
	 *                xmin
	 * @formatter:on
	 * 
	 *               Die 4 Bits werden sehr verschwenderisch in einem int
	 *               untergebracht.
	 * 
	 *               Warum kein byte? Die bitweisen Operationen sind f√ºr Datentyp
	 *               byte nicht definiert! Genauer werden z.B. die Bytes bei byte1 |
	 *               byte2 zu ints gecastet und das Ergebnis ist ein int. Mehr
	 *               Details: <a href=
	 *               "https://stackoverflow.com/questions/27582233/why-byte-and-short-values-are-promoted-to-int-when-an-expression-is-evaluated">Stack
	 *               Overflow</a>
	 * 
	 * @param x x-Koordinate Punkt
	 * @param y y-Koordinate Punkt
	 * @return Outputcode
	 */
	int outputCode(int x, int y) {

		int xNew = 0;
		int yNew = 0;

		if (x < xmin) {
			xNew = Area.LTXMIN;
		}
		if (x > xmax) {
			xNew = Area.GTXMAX;
		}
		if (y < ymin) {
			yNew = Area.LTYMIN;
		}
		if (y > ymax) {
			yNew = Area.GTYMAX;
		}
		
		return (xNew|yNew);

	}

	// draw Method
	public void zeichne(int xA, int yA, int xE, int yE) {
		graphics.drawLine(xA, yA, xE, yE);
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
		// Du bekokmmst die OutputCodes ( Beispiel: Startpunkt 0001 und Endpunkt 1000)
		int startPoint = outputCode(xA, yA);
		int endPoint = outputCode(xE, yE);
		boolean sGe;

		// Hier ¸berpf¸st du welche outputcode grˆﬂer ist und setzt dementsprechend
		// einen boolean
		if (startPoint > endPoint) {
			sGe = true;
		} else {
			sGe = false;
		}

		// ‹berpr¸fen ob Linie ¸berhaupt sichtbar ist
		if ((startPoint & endPoint) == 0) {

			// veroderung ergibt 0. Beides innerhalb und kann gezeichnet werden
			if ((startPoint | endPoint) == 0) {
				zeichne(xA, yA, xE, yE);
			}

			// Veroderung erigt das Bit f¸r 0001 = Schnittkante mit xMin. neuer Schnittpunkt
			// berechnen!
			if ((startPoint | endPoint) == 1) {

				int yMinNew = (((yE - yA) / (xE - xA)) * (xmin - xE)) + yE;

				if (sGe == true) {
					// Wennd der Startpunkt den grˆﬂeren Outpudcode hat! Rekursion
					clipLine(xmin, yMinNew, xE, yE);
				} else {
					// Wennd der Endpunkt den grˆﬂeren Outpudcode hat! Rekursion
					clipLine(xA, yA, xmin, yMinNew);
				}

			}

			// Bit f¸r 0010 - 0011. Schnittkante mit xMax
			if ((startPoint | endPoint) > 1 && (startPoint | endPoint) <= 3) {

				int yMaxNew = (((yE - yA) / (xE - xA)) * (xmax - xE)) + yE;

				if (sGe == true) {
					clipLine(xmax, yMaxNew, xE, yE);
				} else {
					clipLine(xA, yA, xmax, yMaxNew);
				}

			}

			// Bit f¸r 0100-0111. Schnittkante mit yMin
			if ((startPoint | endPoint) > 3 && (startPoint | endPoint) <= 7) {

				int xMinNew = (((xE - xA) / (yE - yA)) * (ymin - yE)) + xE;

				if (sGe == true) {
					clipLine(xMinNew, ymin, xE, yE);
				} else {
					clipLine(xA, yA, xMinNew, ymin);
				}

			}

			// Bit f¸r alles ¸ber 1000. Schnittkante mit yMax
			if ((startPoint | endPoint) >= 8) {

				int xMaxNew = (((xE - xA) / (yE - yA)) * (ymax - yE)) + xE;

				if (sGe == true) {
					clipLine(xMaxNew, ymax, xE, yE);
				} else {
					clipLine(xA, yA, xMaxNew, ymax);
				}

			}

		}

	}

	/**
	 * Unspannende Hilfsroutine zum Clippen mehrerer Linien.
	 * 
	 * Linie i wird √ºber Anfangspunkt A = (xA, yA) und Endpunkt E = (xE, yE)
	 * definiert. Dabei ist
	 * 
	 * @formatter:off points[i][0] = xA points[i][1] = yA points[i][2] = xE
	 *                points[i][3] = yE
	 * @formatter:on
	 * 
	 * @param points Array von zu zeichnenden Linien, definiert √ºber Koordinaten
	 *               von Anfangs- und Endpunkt
	 */
	public void clipLines(int[][] points) {
		for (int i = 0; i < points.length; i++) {
			clipLine(points[i][0], points[i][1], points[i][2], points[i][3]);
		}
	}

}
