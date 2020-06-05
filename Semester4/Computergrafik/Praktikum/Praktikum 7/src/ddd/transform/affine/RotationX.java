package ddd.transform.affine;

import ddd.transform.Transform;

/**
 * Winkel-phi-Rotation um x-Achse
 */
public class RotationX extends Transform {
	/**
	 * Rotation um x-Achse
	 * @param phi Rotationswinkel im Bogenmaß
	 */
	public RotationX(double phi) {
		this(Math.sin(phi), Math.cos(phi));
	}
	
	/**
	 * Rotation um x-Achse
	 * @param sinphi Sinus des Rotationswinkels
	 * @param cosphi Kosinus des Rotationswinkels
	 */
	public RotationX(double sinphi, double cosphi) {
		super();
		matrix[1][1]=cosphi;
		matrix[1][2]=(sinphi)*(-1);
		matrix[2][1]=sinphi;
		matrix[2][2]=cosphi;
	}
}
