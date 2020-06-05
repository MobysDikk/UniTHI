import java.awt.Graphics;
import java.util.List;

class Bezier {
	// TODO: Definieren Sie benötigte Attribute hier.

	private List<Point> pList;
	private double linienTeil;
	private double linienTeil2;
	private Point[][] castArray;
	private Point[] drawArray;
	private Point first;
	private Point last;

	/**
	 * Berechnet Beziér-Kurven. Der Grad der Beziér-Kurve ist über die Zahl der
	 * Kontrollpunkte festgelegt.
	 * 
	 * @param points Kontrollpunkte.
	 * @param h      Schrittweite beim Zeichnen der Beziér-Kurve
	 */
	Bezier(List<Point> points, double h) {
		// TODO: Hier Ihr Code...
		this.pList = points;
		this.linienTeil = h;
		this.linienTeil2 =h;
		this.first = points.get(0);
		this.last = points.get(points.size() - 1);

	}

	/**
	 * Berechne ein Punkt-Objekt, das die zweidimensionale Koordinate der
	 * Bézier-Kurve für einen gegebenen Parameterwert errechnet.
	 * 
	 * @param t Kurvenparameter
	 * @return Koordinate der Bézier-Kurve
	 */
	Point casteljau(double t) {
		// erzeugung eines 2DArrays in der Quardatischen gr��e der Punktmenge in pList.
		int pMenge = pList.size();
		castArray = new Point[pMenge][pMenge];
		// Punkte in die erste Zeile der des Arrays �bertragen
		for (int i = 0; i < pMenge; i++) {
			castArray[0][i] = pList.get(i);
		}
		// In dieser Schleife werden der Reihe nach zwei punkte Zusammen gerechnet und
		// 11111
		// in der Reiher darunter gespeichert (dreieck). 01111
		// Dieser Vorgang wird solange wiederholt bis nur noch ein Punkt �brig bleibt.
		// 00111
		// (1-t) wird mit dem x und y Punkt vom ersrten Punkt mutlipliziert. 00011
		// t wird mit dem zweiten Punkt multipliziert =>
		// (1-t)P1+tP2======================> 00001
		for (int k = 1; k < pMenge; k++) {
			for (int i = k; i < pMenge; i++) {
				Point pNeu = new Point((double) 1 - t, castArray[k - 1][i - 1], (double) t, castArray[k - 1][i]);
				castArray[k][i] = pNeu;
			}
		}
		return castArray[pMenge - 1][pMenge - 1];
	}

	/**
	 * Zeichne eine Bezier-Kurve mit Stütz- und Kontrollpunkten aus points.
	 * 
	 * @param graphics Grafikobjekt
	 */
	void render(Graphics graphics) {
		

		first = casteljau(0);
		last = casteljau(1);
		drawArray = new Point [(int)(1/linienTeil + 0.5)+1];// Bsp: 0,1 => 0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 
		drawArray[0]=first;
		drawArray[drawArray.length -1]= last;
		for( int i = 1; i<drawArray.length-1;i++) {
			drawArray[i]= casteljau(linienTeil);
			linienTeil+=linienTeil2;
		}
		for(int i =1;i<drawArray.length;i++) {
			graphics.drawLine((int) (drawArray[i-1].x +0.5),(int)(drawArray[i-1].y +0.5),(int)(drawArray[i].x +0.5),(int)(drawArray[i].y +0.5));
		}
		
		
	}
}
