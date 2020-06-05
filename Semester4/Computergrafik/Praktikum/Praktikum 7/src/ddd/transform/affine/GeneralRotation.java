package ddd.transform.affine;

import ddd.Vector3;
import ddd.transform.Transform;

/**
 * Allgemeine Rotation
 */
public class GeneralRotation extends Transform {
	private double h;
//	private double deltax;
//	private double deltay;
//	private double deltaz;
//	private Vector3 vec = new Vector3(deltax, deltay, deltaz);
	
	private double transrx;
	private double transry;
	private double transrz;

	/**
	 * 
	 * Allgemeine Rotationsmatrix mit Fusspunkt, Achse und Winkel. Es wird nicht
	 * davon ausgegangen, dass die Achse normiert ist.
	 * 
	 * @param px  x-Koordinate Fusspunkt der Rotationsachse
	 * @param py  y-Koordinate Fusspunkt der Rotationsachse
	 * @param pz  z-Koordinate Fusspunkt der Rotationsachse
	 * @param rx  x-Koordinate Rotationsachse
	 * @param ry  y-Koordinate Rotationsachse
	 * @param rz  z-Koordinate Rotationsachse
	 * @param phi Rotationswinkel im BogenmaÃŸ
	 */
	public GeneralRotation(double px, double py, double pz, double rx, double ry, double rz, double phi) {
		super();

//		this.deltax = rx - px;
//		this.deltay = ry - py;
//		this.deltaz = rz - pz;
//		this.h = Math.sqrt((deltax * deltax) + (deltaz * deltaz));
		
		this.transrx = rx - px;
		this.transry = ry - py;
		this.transrz = rz - pz;
		this.h = Math.sqrt((transrx * transrx) + (transrz * transrz));
		rightMult(new Translation(-px, -py, -pz));// Rücktranslation
		if (h != 0) {
			rightMult(new RotationY(transrx/h, -transrz / h));
			rightMult(new RotationX(-transry, -h));
			
			rightMult(new RotationZ(phi));
			
			rightMult(new RotationX(transry, h));
			rightMult(new RotationY(-transrx/h, transrz / h));
		} else {
			rightMult(new RotationY(phi));
		}

		
rightMult(new Translation(px, py, pz));// Translation zum Ursprung
	}
}
