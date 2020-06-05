package ddd.render;

import java.awt.Graphics;

import ddd.scenegraph.Node;

/**
 * Ein Renderer rendert (sic) Grafik über ein Graphics-Objekt.
 */
public abstract class Renderer {
	protected Graphics graphics;

	public Renderer() {
	}

	/**
	 * Setze das zu verwendende Graphics-Objekt
	 * @param graphics das Graphics-Objekt
	 */
	public void setGraphics(Graphics graphics) {
		this.graphics = graphics;
	}

	/**
	 * Rendere eine Szene
	 * @param scene Wurzelknoten der zu rendernden Szene.
	 */
	public abstract void render(Node scene);
}
