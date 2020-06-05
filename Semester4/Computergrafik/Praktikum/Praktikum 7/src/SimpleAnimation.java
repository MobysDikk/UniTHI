import ddd.models.simple.House;
import ddd.models.simple.Plane;
import ddd.models.simple.Tower;
import ddd.render.SimpleRenderer;
import ddd.scenegraph.Activity;
import ddd.scenegraph.Node;
import ddd.transform.Transform;
import ddd.transform.affine.GeneralRotation;
import ddd.transform.affine.RotationY;
import ddd.transform.affine.Scale;
import ddd.transform.affine.Translation;
import ddd.visualize.SceneRenderFrame;
import ddd.visualize.SceneRenderPanel;

@SuppressWarnings("serial")
public class SimpleAnimation extends SceneRenderPanel {

	/** Konstruktor des Panels */
	public SimpleAnimation() {
		super();
	}

	@Override
	protected void createScene() {		
		// Universum, Szene, root
		scene = new Node();
		// zus. Knoten unterhalb des root-Node einfügen, dessen Transform wir animieren wollen
		Node base = new Node();
		scene.addChild(base);
		
		Node street = new Node();
		Plane plane = new Plane();
		street.addChild(new Scale(10.0, 1.0, 1.0), plane);
		street.addChild(new Scale(1.0, 1.0, 10.0), plane);
		base.addChild(street);
		House house = new House();
		base.addChild(new Translation(3.0, 0.0, 4.0), house);

		// TODO: Weitere Objekte zur Szene hinzufügen...

		
		base.addActivity(new Activity(base) {
			private double angle = 0.0;
			private double a1 = 0.0;
			private double a2 = 0.0;
			private double a3 = 1.0;

			@Override
			public void update() {
				Transform animation = new Scale(0.1);
								
				animation.rightMult(new GeneralRotation(0.0, 0.0, 0.0, a1, a2, a3, angle));
				angle += 0.004;
				a1 = Math.sin(angle);
				a2 = Math.sin(2.0 * angle);
				a3 = Math.cos(angle);
				
				node.setTransform(animation);
			}
		});
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

	public static void main(String[] args) {
		SimpleAnimation colorCubeViewer = new SimpleAnimation();
		@SuppressWarnings("unused")
		SceneRenderFrame sceneRenderFrame = new SceneRenderFrame(colorCubeViewer);
	}
}
