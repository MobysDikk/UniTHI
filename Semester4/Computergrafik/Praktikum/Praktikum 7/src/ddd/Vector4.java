package ddd;

/**
 * Vektor für homogene Koordinaten.
 */
public class Vector4 {
	public double x, y, z, w;

	public Vector4() {
	}

	public Vector4(double x, double y, double z, double w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public Vector4(double x, double y, double z) {
		this(x, y, z, 1.);
	}

	/**
	 * Copy-constructor
	 * 
	 * @param other Zu kopierendes Vec4D
	 */
	public Vector4(Vector4 other) {
		this(other.x, other.y, other.z, other.w);
	}

	/**
	 * Wir clampen den normalisierten Vektor auf [-1, 1].
	 */
	public void clampNormalized() {
		x /= w;
		y /= w;
		z /= w;

		x = (x < -1.0) ? -1.0 : x;
		y = (y < -1.0) ? -1.0 : y;
		z = (z < -1.0) ? -1.0 : z;

		x = (x > 1.0) ? 1.0 : x;
		y = (y > 1.0) ? 1.0 : y;
		z = (z > 1.0) ? 1.0 : z;

		w = 1.0;
	}

	@Override
	public String toString() {
		return "[" + x + ", " + y + ", " + z + ", " + w + "]";
	}

	public static double innerProduct(Vector4 u, Vector4 v) {
		return u.x * v.x + u.y * v.y + u.z * v.z + u.w * v.w;
	}

	public void homNormalize() {
		x /= w;
		y /= w;
		z /= w;
		w = 1.0;
	}

	/**
	 * Euklidische Norm des Vektors (ohne homogene Koordinate).
	 * 
	 * @return norm Norm
	 */
	public double norm() {
		return Math.sqrt(x * x + y * y + z * z);
	}

	/**
	 * Skaliert den Vektor (ohne homogene Koordinate, weil dies die Skalierung
	 * egalisieren würde).
	 * 
	 * @param factor Skalierungsfaktor.
	 */
	public void scale(double factor) {
		x *= factor;
		y *= factor;
		z *= factor;
	}

	/**
	 * Normalisiert den Vektor. Dabei wird die homogene Koordinate weder
	 * berücksichtigt noch modifiziert.
	 */
	public void normalize() {
		double factor = this.norm();
		this.scale(1. / factor);
	}
}
