package ddd.scenegraph;

/**
 * Activities können zu Nodes mit addActivity hinzugefügt werden. Sie bekommen
 * eine Rückwärtsreferenz zum Node und können dessen Eigenschaften zur Laufzeit
 * ändern.
 */
public abstract class Activity {
	protected Node node;

	/**
	 * Erzeuge eine Activity.
	 * 
	 * @param node Rückwärtsreferenz zu dem Node, zu dem die Activity gehört.
	 */
	public Activity(Node node) {
		this.node = node;
	}

	/**
	 * Aktualisierungsroutine. Wird bei jedem Zeichenvorgang aufgerufen.
	 */
	public abstract void update();
}
