import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import ddd.models.Shape;
import ddd.models.wavefront.WavefrontLoader;
import ddd.render.SimpleRenderer;
import ddd.scenegraph.Node;
import ddd.transform.Transform;
import ddd.visualize.SceneRenderFrame;
import ddd.visualize.SceneRenderPanel;

@SuppressWarnings("serial")
public class WavefrontViewer extends SceneRenderPanel {
	private static String filePath;

	/**
	 * Konstruktor des Panels
	 * 
	 * @throws FileNotFoundException
	 */
	public WavefrontViewer() throws FileNotFoundException {
		super();
	}

	@Override
	/**
	 * Generiere die Szene.
	 */
	protected void createScene() {
		// Universum, Szene, root
		scene = new Node();
		Shape shape = null;
		try {
			// Laden der Datei
			shape = WavefrontLoader.loadFromFile(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		// Hinzufügen der geladenen Geometrie zur Szene
		scene.addChild(new Transform(), shape);
	}

	@Override
	protected void setupRenderer() {
		// Mit den nächsten drei Werten können Sie den Bildausschnitt modifizieren.

		// Mittelpunkt des Bildausschnitts in logischen Koordinaten.
		double midpointX = 0.0;
		double midpointY = 0.0;

		// Breite des Bildausschnitts in logischen Koordinaten. (Die logische Höhe ist
		// damit über das Sichtverhältnis determiniert.)
		double logicalWidth = 5.0;

		// do not edit below
		// Sichtverhältnis
		double aspectRatio = (double) height / width;
		double logicalHeight = aspectRatio * logicalWidth;
		renderer = new SimpleRenderer(width, height, midpointX - 0.5 * logicalWidth, midpointY - 0.5 * logicalHeight,
				midpointX + 0.5 * logicalWidth, midpointY + 0.5 * logicalHeight);
	}

	public static void main(String[] args) throws IOException {
		if (args.length > 0) {
			// main wurde ein Argument übergeben
			filePath = args[0];
		} else {
			// grafischen Dialog zum Wählen einer OBJ-Datei anwerfen
			JFileChooser fc = new JFileChooser();
			fc.setFileFilter(new FileNameExtensionFilter("Wavefront File", "obj"));
			int returnVal = fc.showOpenDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				// This is where a real application would open the file.
				filePath = file.getCanonicalPath();
			} else {
				System.out.println("Open command cancelled by user.");
				System.exit(1);
			}
		}
		WavefrontViewer wavefrontViewer = new WavefrontViewer();
		@SuppressWarnings("unused")
		SceneRenderFrame sceneRenderFrame = new SceneRenderFrame(wavefrontViewer);
	}
}
