package ddd.models.wavefront;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.List;
import java.io.BufferedReader;
import java.io.File;

import ddd.Triangle;
import ddd.Vector3;
import ddd.models.Shape;
import ddd.models.TriangleArray;

/**
 * Lade eine Wavefront-Date in ein Shape-Objekt, genauer ein TriangleArray.
 */
public class WavefrontLoader {

	private static Scanner reader;

	/**
	 * Eigentliche Laderoutine
	 * 
	 * @param fileName Name der *.obj-Datei
	 * @return TriangleArray mit Dreiecks-Facetten
	 * @throws FileNotFoundException falls Datei nicht gefunden
	 */
	public static Shape loadFromFile(String fileName) throws FileNotFoundException {
		// Ihr Code hier...

		ArrayList<Vector3> vList = new ArrayList<Vector3>();
		ArrayList<Vector3> vnList = new ArrayList<Vector3>();
		ArrayList<Vector3> vtList = new ArrayList<Vector3>();
		ArrayList<Triangle> triangles = new ArrayList<>();

		reader = new Scanner(new File(fileName));
		while (reader.hasNextLine()) {
			String line = reader.nextLine();
			if (line.length() > 0) {
				if (line.charAt(0) == 'v' && line.charAt(1) == ' ') {
					vList.add(saveV(line));
				}
				if (line.charAt(0) == 'v' && line.charAt(1) == 'n') {
					vnList.add(saveVn(line));
				}
				if (line.charAt(0) == 'v' && line.charAt(1) == 't') {
					vtList.add(saveVt(line));
				}
				if (line.charAt(0) == 'f') {

					if (vList.size() > 0 && vnList.size() == 0 && vtList.size() == 0) { // nur v

						String s = line.substring(2);
						s.trim();
						String[] values = s.split(" ");

						Triangle tri = new Triangle(
								vList.get(Integer.parseInt(values[0]) - 1),
								vList.get(Integer.parseInt(values[1]) - 1), 
								vList.get(Integer.parseInt(values[2]) - 1));
						triangles.add(tri);
					}
					if (vList.size() > 0 && vnList.size() > 0 && vtList.size() == 0) { // nur v und vn

						triangles.add(verticeParser(line, vList));

					}
					if (vList.size() > 0 && vnList.size() == 0 && vtList.size() > 0) { // nur v und vt

						triangles.add(verticeParser(line, vList));

					}
					if (vList.size() > 0 && vnList.size() > 0 && vtList.size() > 0) { // v, vt und vn

						triangles.add(verticeParser(line, vList));
					}
				}
			}
		}

		TriangleArray tArray = new TriangleArray(triangles);

		return tArray; // TriangleArray
	}

	private static Triangle verticeParser(String line, ArrayList<Vector3> vList) {

		String s = line.substring(2);
		s.trim();
		String[] values = s.split(" ");

		Triangle tri = new Triangle(
				vList.get(Integer.parseInt(values[0].substring(0, values[0].indexOf("/"))) - 1),
				vList.get(Integer.parseInt(values[1].substring(0, values[1].indexOf("/"))) - 1),
				vList.get(Integer.parseInt(values[2].substring(0, values[2].indexOf("/"))) - 1));
		return tri;

	}

	private static Vector3 saveVt(String line) {
		String s = line.substring(2);
		s = s.trim();
		String[] values = s.split(" ");
		Vector3 v = new Vector3(Double.parseDouble(values[0]), Double.parseDouble(values[1]), Double.parseDouble("1"));
		return v;
	}

	private static Vector3 saveVn(String line) {
		String s = line.substring(2);
		s = s.trim();
		String[] values = s.split(" ");
		Vector3 v = new Vector3(Double.parseDouble(values[0]), Double.parseDouble(values[1]),
				Double.parseDouble(values[2]));
		return v;
	}

	private static Vector3 saveV(String line) {
		String s = line.substring(1);
		s = s.trim();
		String[] values = s.split(" ");
		Vector3 v = new Vector3(Double.parseDouble(values[0]), Double.parseDouble(values[1]),
				Double.parseDouble(values[2]));
		return v;

	}
}
