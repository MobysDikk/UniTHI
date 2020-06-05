import java.awt.Graphics;
import java.util.List;

abstract class BSpline {

	/** Ordnung der Spline-Kurve (= Grad + 1) */
	protected int k;

	/** Knotenvektor */

	protected double[] knotVector;

	/** Auflösung der Kurve, Schrittweite, Zeichengenauigkeit */
	private double h;

	/**
	 * Eine Liste von Point-Objekten Dies sind die Stütz- und Kontrollpunkte
	 */
	protected List<Point> points;

	/**
	 * B-Spline-Kurve
	 * 
	 * @param points Kontrollpunkte
	 * @param k      Grad der B-Spline + 1
	 * @param h      Schrittweite beim Zeichnen der B-Spline
	 */
	BSpline(List<Point> points, int k, double h) {
		// TODO: Hier Ihr Code...
		this.k = k;
		this.points = points;
		this.h = h;

	}

	/**
	 * Zeichne eine B-Spline mit Stütz- und Kontrollpunkten aus points.
	 * 
	 * @param graphics Grafikobjekt
	 */
	void render(Graphics graphics) {

		double tStart = knotVector[k - 1];
		double tEnde = knotVector[knotVector.length - k];////???????
		double arraySize = (tEnde - tStart) / h;

		Point[] pointAr = new Point[(int) (arraySize + 0.5)];
		for (int i = 0; i < pointAr.length; i++) {
			pointAr[i] = bSpline(tStart);
			tStart += h;
		}
		for (int i = 1; i < pointAr.length; i++) {
			graphics.drawLine((int) (pointAr[i - 1].x + 0.5), (int) (pointAr[i - 1].y + 0.5),
					(int) (pointAr[i].x + 0.5), (int) (pointAr[i].y + 0.5));
		}
		graphics.drawLine((int) (pointAr[pointAr.length - 1].x + 0.5), (int) (pointAr[pointAr.length - 1].y + 0.5),
				(int) (bSpline(tEnde).x + 0.5), (int) (bSpline(tEnde).y + 0.5));

	}

	/**
	 * Berechne ein Punkt-Objekt, das die zweidimensionale Koordinate der
	 * BSpline-Kurve für einen gegebenen Parameterwert errechnet.
	 * 
	 * @param t Kurvenparameter
	 * @return 2D-Koordinate der B-Spline-Kurve für Parameter t
	 */
	Point bSpline(double t) {

		Point result = new Point(0,0);
		for (int i = 0; i < knotVector.length - k; i++) {

			if (t < knotVector[i] || t > knotVector[i + k]) {
				result = new Point(1, result, 0, points.get(i));
			} else {
				result = new Point(1, result, nik(i, k, t), points.get(i));
			}
		}
		return result;
	}

	double factor1(int i, int k, double t) {
		double z = (double) (t - knotVector[i]);
		double n = (double) (knotVector[i + k - 1] - knotVector[i]);
		if (n == 0) {
			return 0;
		} else {
			return z / n;
		}
	}

	double factor2(int i, int k, double t) {
		double z = (double) (knotVector[i + k] - t);
		double n = (double) (knotVector[i + k] - knotVector[i + 1]);
		if (n == 0) {
			return 0;
		} else {
			return z / n;
		}
	}

	/**
	 * Berechne den Wert der B-Spline-Basisfunktion N_{i,k}(t) analog zu Casteljau.
	 * 
	 * @param i Index der B-Spline-Basisfunktion N_{i,k}
	 * @param k Grad der B-Spline-Basisfunktion N_{i,k}
	 * @param t Parameter, an dem N_{i,k} ausgewertet wird
	 * @return N_{i,k}(t)
	 */
	double nik(int i, int k, double t) {
		// TODO: Ihr Code hier
		double[][] nikArray = new double[k][k];
		int ersatz = i;
		for (int z = 0; z < k; z++) {
			if (t < knotVector[ersatz + 1] && t >= knotVector[ersatz]) {
				nikArray[0][z] = (double) 1.0;
				ersatz++;
			} else {
				nikArray[0][z] = (double) 0.0;
				ersatz++;
			}
		}
		if (k == 2 && t == 1.2) {
			System.out.println("hi");
		}
		for (int p = 1; p < k; p++) {
			int richtigesI = 0;
			for (int pi = p; pi < k; pi++) {
				nikArray[p][pi] = (factor1((i + richtigesI), p + 1, t) * nikArray[p - 1][pi - 1])
						+ (factor2((i + richtigesI), p + 1, t) * nikArray[p - 1][pi]);
				richtigesI++;

			}
		}
		return nikArray[k - 1][k - 1];

//		if(k==1) { // Gleiche berechnung mir rekursion!!
//	 if(t<knotVector[i+1] && t>=knotVector[i]) {
//		 return (double) 1.0;
//	 } else {
//		 return (double)0.0;}
//	 } else {
//		 return (double)(factor1*nik(i,k-1, t)) + (double)(factor2*nik(i+1,k-1, t));
//	 }
	}
}
