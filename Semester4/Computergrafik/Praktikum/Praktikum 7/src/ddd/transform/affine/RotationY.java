package ddd.transform.affine;

import ddd.transform.Transform;

/**
 * Winkel-phi-Rotation um y-Achse
 */
public class RotationY extends Transform {
	public RotationY(double phi) {
		this(Math.sin(phi), Math.cos(phi));
	}
	
	/**
	 * Rotation um y-Achse
	 * @param sinphi Sinus des Rotationswinkels
	 * @param cosphi Kosinus des Rotationswinkels
	 */
	public RotationY(double sinphi, double cosphi) {
		super();
		// TODO: Ihr Code hier...
		matrix[0][0]=cosphi;
		matrix[0][2]=sinphi;
		matrix[2][0]=(sinphi)*(-1);
		matrix[2][2]=cosphi;
	}
}
