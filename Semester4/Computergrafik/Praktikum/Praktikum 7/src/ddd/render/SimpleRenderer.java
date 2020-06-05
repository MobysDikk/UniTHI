package ddd.render;

import ddd.Line;
import ddd.Primitive;
import ddd.Triangle;
import ddd.Vector3;
import ddd.scenegraph.Node;
import ddd.transform.Transform;

/**
 * Primitiver Wireframe-Renderer mit einfacher paralleler Orthogonalprojektion.
 */
public class SimpleRenderer extends Renderer {

	private Transform simpleOrthographicTransform;

	/**
	 * Ctor -- an dieser Stelle wird das Aspect Ratio nicht berücksichtigt!
	 * 
	 * @param width  Breite Ausgabefenster
	 * @param height Höhe Ausgabefenster
	 * @param xMin   minimale logische x-Koordinate
	 * @param yMin   minimale logische y-Koordinate
	 * @param xMax   maximale logische x-Koordinate
	 * @param yMax   maximale logische y-Koordinate
	 */
	public SimpleRenderer(int width, int height, double xMin, double yMin, double xMax, double yMax) {
		double xscale = width / (xMax - xMin);
		double yscale = height / (yMin - yMax);
		//@formatter:off
		simpleOrthographicTransform = new Transform(new double[][] {
			{xscale, 0.0, 0.0, -xscale * xMin},
			{0.0, yscale, 0.0, -yscale * yMax},
			{0.0, 0.0, 0.0, 0.0},
			{0.0, 0.0, 0.0, 1.0},
		});
		//@formatter:on
	}

	@Override
	public void render(Node scene) {
		for (Primitive primitive : scene.getPrimitives()) {
			if (primitive instanceof Triangle) {
				Triangle triangle = (Triangle) primitive;

				// Transformiere Eckpunkte
				Vector3 v0t = simpleOrthographicTransform.multiply(triangle.vertices[0]);
				Vector3 v1t = simpleOrthographicTransform.multiply(triangle.vertices[1]);
				Vector3 v2t = simpleOrthographicTransform.multiply(triangle.vertices[2]);

				// Keine Appearance gesetzt => Wireframe-Zeichnung
				graphics.drawLine((int) (v0t.x + 0.5), (int) (v0t.y + 0.5), (int) (v1t.x + 0.5), (int) (v1t.y + 0.5));
				graphics.drawLine((int) (v1t.x + 0.5), (int) (v1t.y + 0.5), (int) (v2t.x + 0.5), (int) (v2t.y + 0.5));
				graphics.drawLine((int) (v2t.x + 0.5), (int) (v2t.y + 0.5), (int) (v0t.x + 0.5), (int) (v0t.y + 0.5));
			}
			else if (primitive instanceof Line) {
				Line line = (Line) primitive;
				// Transformiere Start- und Endpunkt
				Vector3 v0t = simpleOrthographicTransform.multiply(line.vertices[0]);
				Vector3 v1t = simpleOrthographicTransform.multiply(line.vertices[1]);
				graphics.drawLine((int) (v0t.x + 0.5), (int) (v0t.y + 0.5), (int) (v1t.x + 0.5), (int) (v1t.y + 0.5));
			}
		}
	}
}
