package ddd.visualize;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

import ddd.render.Renderer;
import ddd.scenegraph.Node;

/**
 * Diese Klasse implementiert eine Art Protokoll für unsere 3D-Darstellungen.
 * 
 * setupRenderer -> createScene -> render
 */
@SuppressWarnings("serial")
public abstract class SceneRenderPanel extends JPanel {

	/** Dimension des Zeichen-Panels */
	protected int width;
	protected int height;

	// Fullscreen nutzen?
	private boolean fullscreen = true;

	/** Zu rendernde Szene */
	protected Node scene;

	/** Der verwendete Renderer */
	protected Renderer renderer;

	/** Konstruktor des Panels */
	public SceneRenderPanel() {
		if (fullscreen) {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			setPreferredSize(screenSize);
			width = screenSize.width;
			height = screenSize.height;
		} else {
			width = 500;
			height = 500;
			setPreferredSize(new Dimension(width, height));
		}

		setupRenderer();
		createScene();
	}

	/**
	 * Inhalt des Zeichen-Panels.
	 */
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		this.setBackground(Color.BLACK);
		graphics.setColor(Color.RED);
		this.renderer.setGraphics(graphics);

		// Die Szene zeichnen
		renderer.render(scene);
	}

	/**
	 * Generiere den Renderer. Dafür muss das Attribut renderer auf eine gültige
	 * Instanz gesetzt werden.
	 */
	protected abstract void setupRenderer();

	/**
	 * Erzeuge die Szene. Der root-Note muss das Attribut scene sein.
	 */
	protected abstract void createScene();

	/**
	 * Animationsfunktion. Bei Überschreiben sollte in jedem Fall super.update()
	 * aufgerufen werden, sonst wird der Szenengraph nicht aktualisiert.
	 */
	protected void update() {
		scene.update();
	}
}
