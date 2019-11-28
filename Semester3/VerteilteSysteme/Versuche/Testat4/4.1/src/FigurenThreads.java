
public class FigurenThreads {

    public static void main(String[] args) {

        Figur figure = new PositionHandle(0, 0);
        Leser reader = new Leser(figure);
        Schreiber writer = new Schreiber(figure);
        writer.setDaemon(true);
        reader.start();
        writer.start();

    }
}
