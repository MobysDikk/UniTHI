package ddd;

import java.awt.Color;

public class Line implements Primitive {
	/** "Ecken" -- jede Linie hat welche */
	public Vector3[] vertices = new Vector3[2];
	/** Farbe -- ist optional */
	public Color color;
	
	public Line(Vector3 v0, Vector3 v1) {
		// Deep Copy für bessere Datenlokalität
		vertices[0] = new Vector3(v0);
		vertices[1] = new Vector3(v1);
	}
	
	public Line(Vector3 v0, Vector3 v1, Color color) {
		this(v0, v1);
		this.color = color;
	}
	
	/**
	 * Copy constructor
	 * 
	 * @param line Linie, von dem wir Werte per Deep-Copy kopieren.
	 */
	public Line(Line line) {
		this(line.vertices[0], line.vertices[1]);
		color = line.color;
	}

}
