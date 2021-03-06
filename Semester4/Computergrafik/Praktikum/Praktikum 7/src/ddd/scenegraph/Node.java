package ddd.scenegraph;

import java.util.LinkedList;
import java.util.List;

import ddd.Primitive;
import ddd.models.Shape;
import ddd.transform.Transform;

/**
 * Mit Nodes kann ein Szenengraph aufgebaut werden. Jeder Node hat stets eine
 * gültige Transform. Das zugehörige Geometrie-Objekt shape kann null sein. In
 * diesem Fall ist der Node ein Gruppierungsknoten.
 */
public class Node {
	/** Lokale Transformation */
	private Transform transform;
	/** Optionales zugeordnetes Geometrieobjekt */
	private Shape shape;
	/** Liste von Aktivitäten */
	private List<Activity> activities;
	/** Liste von Kindknoten */
	private List<Node> children;

	/**
	 * Erzeuge Node ohne Geometrie (= Gruppierungsknoten) mit der Einheitsmatrix als
	 * Transformation.
	 */
	public Node() {
		this.transform = new Transform();
		activities = new LinkedList<>();
	}

	/**
	 * Erzeuge Node mit Transformation und Geometrie.
	 * 
	 * @param transform Transformation
	 * @param shape     Geometrie
	 */
	public Node(Transform transform, Shape shape) {
		this.transform = transform;
		this.shape = shape;
		activities = new LinkedList<>();
	}

	/**
	 * Erzeuge Node ohne Geometrie (= Gruppierungsknoten) mit gegebener
	 * Transformation.
	 * 
	 * @param transform Transformation
	 */
	public Node(Transform transform) {
		this.transform = transform;
		activities = new LinkedList<>();
	}

	/**
	 * Füge dem Node einen Kind-Node mit gegebener Transformation und Geometrie
	 * hinzu. Convenience-Methode.
	 * 
	 * @param t Transformation des Kinds
	 * @param s Geometrie des Kinds
	 */
	public void addChild(Transform t, Shape s) {
		addChild(new Node(t, s));
	}

	/**
	 * Füge dem Node einen Kind-Node hinzu.
	 * 
	 * @param n Kind-Node
	 */
	public void addChild(Node n) {
		if (children == null) {
			children = new LinkedList<Node>();
		}
		children.add(n);
	}

	/**
	 * Führe alle update-Methoden aller Activities dieses Nodes und aller seiner
	 * Kinder durch.
	 */
	public void update() {
		for (Activity a : activities) {
			a.update();
		}
		if (children != null) {
			for (Node child : children) {
				child.update();
			}
		}
	}

	/**
	 * Füge Activity hinzu.
	 * @param activity Activity
	 */
	public void addActivity(Activity activity) {
		activities.add(activity);
	}

	/**
	 * Hole alle grafischen Primitive eines Szenegraphen. Die Primitive sind im
	 * Objektraum korrekt transformiert. Sollte nur vom root-Node aus aufgerufen
	 * werden.
	 * 
	 * @return Liste der grafischen Primitive
	 */
	public LinkedList<Primitive> getPrimitives() {
		LinkedList<Primitive> primitives = new LinkedList<>();
		addPrimitives(new Transform(), primitives);
		return primitives;
	}

	/**
	 * Füge rekursiv grafische Primitive eines Szenegraphen zu einer Liste aller
	 * Primitive hinzu.
	 * 
	 * @param parentTransform Transformation die vom hierarchisch höher liegenden
	 *                        Knoten geerbt wird.
	 * @param primitives      Inout-Liste aller grafischen Primitive.
	 */
	private void addPrimitives(Transform parentTransform, LinkedList<Primitive> primitives) {
		// Berechne die effektive Transform dieses Nodes
		Transform combinedTransform = new Transform(parentTransform);
		combinedTransform.rightMult(transform);
		if (shape != null) {
			// Primitive dieser Geometrie hinzufügen
			shape.addPrimitives(combinedTransform, primitives);
		}
		if (children != null) {
			// Rekursiv Primitive der Kindknoten hinzufügen
			for (Node n : children) {
				n.addPrimitives(combinedTransform, primitives);
			}
		}
	}

	/**
	 * Getter für die Transform
	 * @return Transform des Nodes
	 */
	public Transform getTransform() {
		return transform;
	}

	/**
	 * Setter für die Transform.
	 * @param transform überschreibt die aktuelle Transform
	 */
	public void setTransform(Transform transform) {
		this.transform = transform;
	}
}