package ddd.transform.affine;

import ddd.transform.Transform;

/**
 * Scherungsmatrix
 * 
 * @formatter:off
 * (  1 yx zx 0 )
 * ( xy  1 zy 0 )
 * ( xz yz  1 0 )
 * (  0  0  0 1 )
 * @formatter:on
 */
public class Shear extends Transform {
	public Shear(double yx, double zx, double xy, double zy, double xz, double yz) {
		super();
		matrix[0][1] = yx;
		matrix[0][2] = yz;
		matrix[1][0] = xy;
		matrix[1][2] = zy;
		matrix[2][0] = xz;
		matrix[2][1] = yz;
	}
}
