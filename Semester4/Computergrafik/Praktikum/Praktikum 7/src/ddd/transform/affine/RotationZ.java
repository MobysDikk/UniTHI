package ddd.transform.affine;

import ddd.transform.Transform;

/**
 * Winkel-phi-Rotation um z-Achse
 */
public class RotationZ extends Transform {
	public RotationZ(double phi) {
		this(Math.sin(phi), Math.cos(phi));
	}
	
	/**
	 * Rotation um z-Achse
	 * @param sinphi Sinus des Rotationswinkels
	 * @param cosphi Kosinus des Rotationswinkels
	 */
	public RotationZ(double sinphi, double cosphi) {
		super();
		// TODO: Ihr Code hier...
		matrix[0][0]=cosphi;
		matrix[0][1]=(sinphi)*(-1);
		matrix[1][0]=sinphi;
		matrix[1][1]=cosphi;
	}
}
