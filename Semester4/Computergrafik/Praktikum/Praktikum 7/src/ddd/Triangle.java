package ddd;


public class Triangle implements Primitive {
	/** Ecken -- jedes Dreieck hat welche */
	public Vector3[] vertices = new Vector3[3];

	
	public Triangle(Vector3 v0, Vector3 v1, Vector3 v2) {
		// Deep Copy für bessere Datenlokalität
		vertices[0] = new Vector3(v0);
		vertices[1] = new Vector3(v1);
		vertices[2] = new Vector3(v2);
	}

	
	/**
	 * Copy constructor
	 * 
	 * @param triangle Dreieck, von dem wir Ecken per Deep-Copy kopieren.
	 */
	public Triangle(Triangle triangle) {
		this(triangle.vertices[0], triangle.vertices[1], triangle.vertices[2]);
	}

	/**
	 * Berechnet die äußere Flächennormale.
	 * 
	 * @return Äußere Flächennormale
	 */
	public Vector4 getOuterNormal() {
		Vector3 n = Vector3.outerProduct(Vector3.sub(vertices[1], vertices[0]), Vector3.sub(vertices[2], vertices[0]));
		n.normalize();
		return new Vector4(n.x, n.y, n.z, 0.0);
	}

	/**
	 * Berechne Mittelpunkt des Dreiecks.
	 * 
	 * @return Mittelpunkt
	 */
	public Vector3 getMidPoint() {
		return new Vector3((vertices[0].x + vertices[1].x + vertices[2].x) / 3.0,
				(vertices[0].y + vertices[1].y + vertices[2].y) / 3.0,
				(vertices[0].z + vertices[1].z + vertices[2].z) / 3.0);
	}
	
	public double area() {
		Vector3 n = Vector3.outerProduct(Vector3.sub(vertices[1], vertices[0]), Vector3.sub(vertices[2], vertices[0]));
		return 0.5 * n.norm();
	}
}
