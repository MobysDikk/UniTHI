package ddd.visualize;
import javax.swing.JFrame;

/**
 * Bei Frames wird immer das gleich benötigt, hier zusammengefasst.
 */
@SuppressWarnings("serial")
public class SceneRenderFrame extends JFrame {
	/** Konstruktor des Frames */
	public SceneRenderFrame(SceneRenderPanel sceneRenderPanel) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(sceneRenderPanel);
		this.pack();
		this.setVisible(true);
		while (true) {
			// Neuzeichnen anstoßen
			sceneRenderPanel.repaint();
			sceneRenderPanel.update();
			try {
				Thread.sleep(10L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
