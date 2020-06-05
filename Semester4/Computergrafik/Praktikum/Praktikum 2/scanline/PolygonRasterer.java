import java.awt.Graphics;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Rastert Polygone mit Scanline-Verfahren.
 * 
 * Zur Vereinfachung nehmen wir an, dass sich Polygone immer komplett im
 * Viewport befinden, den Rand also nicht schneiden.
 */
public class PolygonRasterer {
	/** Zum Zeichnen */
	private Graphics graphics;
	/** HÃ¶he des Zeichenfensters */
	private int height;
	/** Die Edge Table */
	private LinkedList<Edge> edgeTable = new LinkedList<>();
	/** Die Active Edge Table */
	private LinkedList<Edge> activeEdgeTable = new LinkedList<>();
	private LinkedList<Edge> activeEdgeTable2 = new LinkedList<>();
	public boolean firstTime = false;

	public PolygonRasterer(int height) {
		this.height = height;
	}

	// Sortiert die active edge Table vom geringsten X wert zum höchsten
	public void sortAET() {
		if (activeEdgeTable.size() > 1) {

			double min = 20000000;
			int pos = 0;

			while (activeEdgeTable.size() > 0) {
				for (int i = 0; i < activeEdgeTable.size(); i++) {
					if (min > activeEdgeTable.get(i).getxIntersect()) {
						min = activeEdgeTable.get(i).getxIntersect();
						pos = i;
					}
				}
				activeEdgeTable2.add(activeEdgeTable.get(pos));
				activeEdgeTable.remove(pos);

				min = 20000000;
			}

			for (int i = 0; i < activeEdgeTable2.size(); i++) {
				activeEdgeTable.add(activeEdgeTable2.get(i));
			}

			activeEdgeTable2.clear();

		}
	}

	public void draw(int y) {

		if (activeEdgeTable.size() % 2 == 0 && activeEdgeTable.size() > 0) {

			// even and odd Arrays
			double[] even = new double[activeEdgeTable.size() / 2];
			int evenIter = 0;
			double[] odd = new double[activeEdgeTable.size() / 2];
			int oddIter = 0;
			for (int i = 0; i < activeEdgeTable.size(); i++) {
				if (i % 2 == 0) {
					even[evenIter] = activeEdgeTable.get(i).getxIntersect();
					evenIter++;
				} else {
					odd[oddIter] = activeEdgeTable.get(i).getxIntersect();
					oddIter++;
				}
			}

			for (int i = 0; i < even.length; i++) {
				int x1 = (int) (even[i] + 0.5);
				int x2 = (int) (odd[i] + 0.5);
				graphics.drawLine(x1, y, x2, y);
			}

		}
	}

	/**
	 * Umsetzung des Scan-Line-Verfahrens
	 */
	public void rasterize() {

		if (firstTime==true) {
			System.out.println(edgeTable);

			for (int y = 0; y < height; y++) {

				// Überprüfung ob Ymin == y ist und activeEdgeTable hinzufügen

				for (int i = 0; i < edgeTable.size(); i++) {
					if (y == edgeTable.get(i).getYMin()) {
						activeEdgeTable.add(edgeTable.get(i));
					}
				}
				if (y == 108) {
					System.out.println("");
				}

				// sortieren und zeichene der Pixel
				sortAET();
				draw(y);

				// überprüfen ob Ymax == y ist und activeEdgeTable entfernen
				for (int i = 0; i < edgeTable.size(); i++) {
					if (y == edgeTable.get(i).getyMax()) {
						activeEdgeTable.remove(edgeTable.get(i));
					}
				}

				// aktualisierung der xKoordinaten in der activeEdgeTable
				for (int i = 0; i < activeEdgeTable.size(); i++) {
					double xSchnitt = (activeEdgeTable.get(i).getxIntersect());
					double durchM = activeEdgeTable.get(i).getmReci();
					activeEdgeTable.get(i).setxIntersect(xSchnitt + (durchM));
				}

			}
			
		}
	}

	public void setGraphics(Graphics graphics) {
		this.graphics = graphics;
	}

	public void addEdges(LinkedList<Edge> edges) {
		edgeTable.addAll(edges);
	}
}
