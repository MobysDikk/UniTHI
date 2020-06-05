package ddd.transform.affine;

import ddd.transform.Transform;

/**
 * Skalierung in Achsrichtungen
 */
public class Scale extends Transform {
	/**
	 * Skalierung
	 * @param sx x-Richtung
	 * @param sy y-Richtung
	 * @param sz z-Richtung
	 */
	public Scale(double sx, double sy, double sz) {
		super();
		matrix[0][0]=sx;
		matrix[1][1]=sy;
		matrix[2][2]=sz;
	}

	/**
	 * Zentrische Streckung
	 * @param scale Skalierungsfaktor
	 */
	public Scale(double scale) {
		this(scale, scale, scale);
	}
}
