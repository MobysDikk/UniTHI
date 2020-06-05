package ddd.models;

import java.util.LinkedList;

import ddd.Primitive;
import ddd.transform.Transform;

public abstract class Shape {

	/**
	 * Füge transformierte grafische Primitive dieser geometrischen Form an die
	 * Liste aller grafischen Primitive an.
	 * 
	 * @param transform  Wird auf die Primitive dieser Form angewandt.
	 * @param primitives Inout-Liste, der die transformierten Primitive angefügt
	 *                   werden.
	 */
	public abstract void addPrimitives(Transform transform, LinkedList<Primitive> primitives);
}
