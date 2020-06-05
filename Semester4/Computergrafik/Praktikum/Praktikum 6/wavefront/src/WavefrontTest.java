import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.util.LinkedList;

import org.junit.Test;

import ddd.Primitive;
import ddd.Triangle;
import ddd.models.Shape;
import ddd.models.wavefront.WavefrontLoader;
import ddd.transform.Transform;

public class WavefrontTest {
	/**
	 * Teste das Format
	 * f v1 v2 v3
	 */
	@Test
	public void testVertex() {
		try {
			Shape shape = WavefrontLoader.loadFromFile("icosahedron.obj");
			LinkedList<Primitive> triangles = new LinkedList<>();
			shape.addPrimitives(new Transform(), triangles);
			assertEquals("Falsche Zahl von Flächen", 20, triangles.size());
			for (Primitive t : triangles) {
				double area = ((Triangle) t).area();
				assertEquals("Falsche Seitenflaeche", 0.5984088544298709, area, 1e-6); 
			}
		} catch (FileNotFoundException e) {
			fail(e.toString());
		}

	}
	
	/**
	 * Teste das Format
	 * f v1//vn1 v2//vn2 v3//vn3
	 */
	@Test
	public void testVertexNormals() {
		try {
			Shape shape = WavefrontLoader.loadFromFile("cube.obj");
			LinkedList<Primitive> triangles = new LinkedList<>();
			shape.addPrimitives(new Transform(), triangles);
			assertEquals("Falsche Zahl von Flächen", 12, triangles.size());
			for (Primitive t : triangles) {
				double area = ((Triangle) t).area();
				assertEquals("Falsche Seitenflaeche", 2.0, area, 1e-10); 
			}
		} catch (FileNotFoundException e) {
			fail(e.toString());
		}
	}
	
	/**
	 * Teste das Format
	 * f v1/vt1 v2/vt2 v3/vt3
	 */
	@Test
	public void testVertexTexture() {
		try {
			Shape shape = WavefrontLoader.loadFromFile("octahedron.obj");
			LinkedList<Primitive> triangles = new LinkedList<>();
			shape.addPrimitives(new Transform(), triangles);
			assertEquals("Falsche Zahl von Flächen", 8, triangles.size());
			for (Primitive t : triangles) {
				double area = ((Triangle) t).area();
				assertEquals("Falsche Seitenflaeche", 0.8660254037844386, area, 1e-10); 
			}
		} catch (FileNotFoundException e) {
			fail(e.toString());
		}	
	}
	
	/**
	 * Teste das Format
	 * f v1/vt1/vn1 v2/vt2/vn2 v3/vt3/vn3
	 */	
	@Test
	public void testVertexTextureNormals() {
		try {
			Shape shape = WavefrontLoader.loadFromFile("tetrahedron.obj");
			LinkedList<Primitive> triangles = new LinkedList<>();
			shape.addPrimitives(new Transform(), triangles);
			assertEquals("Falsche Zahl von Flächen", 4, triangles.size());
			for (Primitive t : triangles) {
				double area = ((Triangle) t).area();
				assertEquals("Falsche Seitenflaeche", 3.4641016151377544, area, 1e-10); 
			}
		} catch (FileNotFoundException e) {
			fail(e.toString());
		}	
	}
	
	/**
	 * Teste real-world-Beispiel.
	 */
	@Test
	public void testTeaPot() {
		try {
			Shape shape = WavefrontLoader.loadFromFile("teapot.obj");
			LinkedList<Primitive> triangles = new LinkedList<>();
			shape.addPrimitives(new Transform(), triangles);
			assertEquals("Falsche Zahl von Flächen", 2464, triangles.size());
			double totalArea = 0.0;
			for (Primitive t : triangles) {
				totalArea += ((Triangle) t).area();
			}
			assertEquals("Falsche Gesamtoberflaeche", 5.351942960876774, totalArea, 1e-5); 
		} catch (FileNotFoundException e) {
			fail(e.toString());
		}
	}
	
	/**
	 * Falls Datei nicht gefunden wird, wird Exception erwartet.
	 */
	@Test
	public void testException() {
		try {
			@SuppressWarnings("unused")
			Shape shape = WavefrontLoader.loadFromFile("doesnotexist.obj");
			fail("No exception thrown");
		}
		catch (FileNotFoundException e) {
			// Success
		}
	}	
}
