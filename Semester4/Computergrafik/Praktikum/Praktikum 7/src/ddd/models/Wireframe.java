package ddd.models;

import java.util.LinkedList;

import ddd.Line;
import ddd.Primitive;
import ddd.Vector3;
import ddd.transform.Transform;

/**
 * Definiert ein 3D-Modell in der ev-Relation.
 */
public class Wireframe extends Shape {
	/** Array von Knotenkoordinaten */
	protected double[][] vertices;
	/** Paare von verbundenen Knoten */
	protected int[][] edges;

	@Override
	public void addPrimitives(Transform transform, LinkedList<Primitive> primitives) {
		for (int[] edge : edges) {
			// Transformation von Start- und Endpunkt einer jeden Kante und Zeichnen der
			// Verbindungslinie.
			double[] start = vertices[edge[0]];
			Vector3 from = transform.multiply(new Vector3(start[0], start[1], start[2]));
			double[] end = vertices[edge[1]];
			Vector3 to = transform.multiply(new Vector3(end[0], end[1], end[2]));
			primitives.add(new Line(from, to));
		}		
	}
}
