package ddd.transform;

import ddd.Vector3;
import ddd.Vector4;

/**
 * Kapselt Transformationsmatrizen, die mit homogenen Koordinaten arbeiten.
 *
 */
public class Transform {
	/** Trasformationsmatrix */
	protected double[][] matrix = new double[4][4];
	
	/**
	 * Initialisierung mit der Einheitsmatrix
	 */
	public Transform() {
		for (int i=0; i<4; i++) {
			matrix[i][i] = 1.0;
		}
	}
	
	/**
	 * Setze Einträge der Matrix explizit.
	 * @param matrix Transformationsmatrix
	 */
	public Transform(double matrix[][]) {
		this.matrix = matrix;
	}
	
	/**
	 * Copy-Konstruktor
	 * Kopiert die Werte einer gegebenen Transformation.
	 * @param other Transformation, deren Werte übernommen werden sollen.
	 */
	public Transform(Transform other) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				matrix[i][j] = other.matrix[i][j]; 
			}
		}
	}
	
	/**
	 * Multipliziere Transformationsmatrix von rechts an diese Matrix.
	 * 
	 * this = this * other
	 * 
	 * @param other
	 *            Die zu multiplizierende Matrix.
	 */
	public void rightMult(Transform other) {
		double[][] mult1 = matrix;
		double[][] mult2 = other.matrix;
		double[][] result = new double[4][4];
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				double e = 0.0;
				for (int k=0; k<4; k++) {
					e +=  mult1[i][k] * mult2[k][j];
				}
				result[i][j] = e;
			}
		}
		matrix = result;
	}
	
	/**
	 * Wende die Transformation auf einen Vektor an (Matrix-Vektorprodukt).
	 * Dabei wird angenommen, dass die nicht vorhandene homogene Koordinate den Wert 1 hat.
	 * 
	 * @param p 3D-Vektor
	 * @return Normalisiertes this * p
	 */
	public Vector3 multiply(Vector3 p) {
		double retx = matrix[0][0] * p.x + matrix[0][1] * p.y + matrix[0][2] * p.z + matrix[0][3];
		double rety = matrix[1][0] * p.x + matrix[1][1] * p.y + matrix[1][2] * p.z + matrix[1][3];
		double retz = matrix[2][0] * p.x + matrix[2][1] * p.y + matrix[2][2] * p.z + matrix[2][3];
		double retw = matrix[3][0] * p.x + matrix[3][1] * p.y + matrix[3][2] * p.z + matrix[3][3];
		return new Vector3(retx / retw, rety / retw, retz / retw);
	}
	
	/**
	 * Wende die Transformation auf einen Vektor an (Matrix-Vektorprodukt).
	 * 
	 * @param p Vektor in homogenen Koordinaten
	 * @return this * p
	 */
	public Vector4 multiply(Vector4 p) {
		double retx = matrix[0][0] * p.x + matrix[0][1] * p.y + matrix[0][2] * p.z + matrix[0][3] * p.w;
		double rety = matrix[1][0] * p.x + matrix[1][1] * p.y + matrix[1][2] * p.z + matrix[1][3] * p.w;
		double retz = matrix[2][0] * p.x + matrix[2][1] * p.y + matrix[2][2] * p.z + matrix[2][3] * p.w;
		double retw = matrix[3][0] * p.x + matrix[3][1] * p.y + matrix[3][2] * p.z + matrix[3][3] * p.w;
		return new Vector4(retx, rety, retz, retw);
	}
	
	/**
	 * Für mehr Spaß beim Debuggen  :)
	 * @return Transformationsmatrix als String
	 */
	@Override
	public String toString() {
		StringBuffer sbuf = new StringBuffer(512);
		for (int i=0; i<4; i++) {
			sbuf.append('[');
			for (int j=0; j<4; j++) {
				sbuf.append(String.format("%10.6f", matrix[i][j]));
				sbuf.append(" ");
			}
			sbuf.append("]\n");
		}
		return sbuf.toString();
	}
	
	/**
	 * Berechnet die Maximumsnorm für die Differenz der den Transformationen zugrundeliegenden Matrizen.
	 * 
	 * @param a Minuend transform/matrix
	 * @param b Subtrahend transform/Matrix
	 * @return Maximale Distanz zwischen zwei Matrixeinträgen
	 */
	public static double maxNorm(Transform a, Transform b) {
		double max = 0.0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				double diff = Math.abs(a.matrix[i][j] - b.matrix[i][j]);
				if (diff > max) {
					max = diff;
				}
			}
		}
		return max;
	}
}
