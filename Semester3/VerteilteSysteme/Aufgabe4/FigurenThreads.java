package Aufgabe4;

public class FigurenThreads {
	public static void main (String[] args) {
		Figur figur = new FigurMov('A', 1);
		Leser leser = new Leser(figur);
		Schreiber schreiber = new Schreiber(figur);
		schreiber.setDaemon(true);
		leser.start();
		schreiber.start();
	}
}
