import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class HuepferFrame extends JPanel {

	/** initiale Dimension des Zeichen-Panels (GKOS) */
	private int width = 1200;
	private int height = 1000;

	/** Konstruktor für das Zeichen-Panel */
	public HuepferFrame() {
		this.setPreferredSize(new Dimension(width, height));
	}

	/**
	 * Inhalt des Zeichen-Panels.
	 * 
	 * @param graphics Zum Zeichnen verwendetes Graphics-Objekt.
	 */
	public void paintComponent(Graphics graphics) {
		this.setBackground(Color.BLACK);
		super.paintComponent(graphics);
		graphics.setColor(Color.WHITE);
		Huepfer h = new Huepfer(graphics, //
				width, //
				height, //
				-50.0, // xMin
                50.0, // xMax
                -50.0, // yMin
                50.0, // yMax
                3.14, // a
                0.3, // b
                0.3, // c
                100_000_0 // num
		);
		h.render();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Huepfer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new HuepferFrame());
		frame.pack();
		frame.setVisible(true);
	}
}
