package ddd.transform.affine;

import ddd.Vector3;
import ddd.transform.Transform;

/**
 * Translation um (dx, dy, dz)
 */
public class Translation extends Transform {
	/**
	 * Translation
	 * @param trans Translationsvektor
	 */
	public Translation(Vector3 trans) {
		this(trans.x, trans.y, trans.z);
	}
	
	/**
	 * Translation
	 * @param dx x-Richtung
	 * @param dy y-Richtung
	 * @param dz z-Richtung
	 */
	public Translation(double dx, double dy, double dz) {
		super();
		// TODO: Ihr Code hier ...
		matrix[0][3] = dx;
		matrix[1][3] = dy;
		matrix[2][3] = dz;
	}
}
