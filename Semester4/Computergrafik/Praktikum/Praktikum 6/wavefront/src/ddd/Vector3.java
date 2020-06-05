package ddd;

/**
 * @formatter:off
 * Implementiert einen dreidimensionalen Vektor (x, y, z). Dieser sollte außer
 * in dokumentierten Ausnahmefällen eine abkürzende Schreibweise für 
 * 
 * (x, y, z, 1) 
 * 
 * in homogenen Koordinaten darstellen, also einen Punkt.
 * @formatter:on
 */
public class Vector3 {
	public double x, y, z;

	public Vector3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3() {
	}

	/**
	 * Copy-Konstruktor
	 */
	public Vector3(Vector3 orig) {
		this(orig.x, orig.y, orig.z);
	}

	/**
	 * Euklidische Norm des Vektors.
	 * 
	 * @return norm Norm
	 */
	public double norm() {
		return Math.sqrt(x * x + y * y + z * z);
	}

	/**
	 * Skaliert einen Vektor.
	 * 
	 * @param factor Skalierungsfaktor.
	 */
	public void scale(double factor) {
		x *= factor;
		y *= factor;
		z *= factor;
	}

	/**
	 * Normalisiert den Vektor.
	 */
	public void normalize() {
		double factor = this.norm();
		this.scale(1. / factor);
	}

	/**
	 * Führt eine Vektorsubtraktion durch und speichert das Ergebnis in neuem
	 * Vektor.
	 * 
	 * @param minuend    Vektor
	 * @param subtrahend Vektor
	 * @return minuend - subtrahend
	 */
	public static Vector3 sub(Vector3 minuend, Vector3 subtrahend) {
		Vector3 result = new Vector3();
		result.x = minuend.x - subtrahend.x;
		result.y = minuend.y - subtrahend.y;
		result.z = minuend.z - subtrahend.z;
		return result;
	}

	/**
	 * Berechnet das Vektorprodukt.
	 * 
	 * @param vec1 Erster Vektor
	 * @param vec2 Zweiter Vektor
	 * @return vec1 x vec2
	 */
	public static Vector3 outerProduct(Vector3 vec1, Vector3 vec2) {
		Vector3 result = new Vector3();
		result.x = vec1.y * vec2.z - vec1.z * vec2.y;
		result.y = vec1.z * vec2.x - vec1.x * vec2.z;
		result.z = vec1.x * vec2.y - vec1.y * vec2.x;
		return result;
	}

	@Override
	public String toString() {
		return "[" + x + ", " + y + ", " + z + "]";
	}

	/**
	 * Berechnet das Skalarprodukt.
	 * 
	 * @param u Erster Vektor
	 * @param v Zweiter Vektor
	 * @return vec1 o vec2
	 */
	public static double innerProduct(Vector3 u, Vector3 v) {
		return u.x * v.x + u.y * v.y + u.z * v.z;
	}
}
