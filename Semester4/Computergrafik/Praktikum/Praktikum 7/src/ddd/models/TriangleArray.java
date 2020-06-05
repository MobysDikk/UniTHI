package ddd.models;

import java.util.ArrayList;
import java.util.LinkedList;

import ddd.Primitive;
import ddd.Triangle;
import ddd.Vector3;
import ddd.transform.Transform;

/**
 * Diese Klasse ist primär ein Wrapper für eine ArrayList von Triangles
 */
public class TriangleArray extends Shape {

	protected ArrayList<Triangle> triangles;

	public TriangleArray() {
	}

	public TriangleArray(ArrayList<Triangle> triangles) {
		this.triangles = triangles;
	}

	@Override
	public void addPrimitives(Transform transform, LinkedList<Primitive> ret) {
		for (Triangle tri : this.triangles) {
			// Transformiere Eckpunkte
			Vector3 v0t = transform.multiply(tri.vertices[0]);
			Vector3 v1t = transform.multiply(tri.vertices[1]);
			Vector3 v2t = transform.multiply(tri.vertices[2]);
			ret.add(new Triangle(v0t, v1t, v2t
					));
		}
	}
}
