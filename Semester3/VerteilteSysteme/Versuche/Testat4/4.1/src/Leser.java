
public class Leser extends Thread {

    private Figur f;

    public Leser(Figur figure) {
        this.f = figure;
    }

    public void run() {

        while (true) {

            f.getPosition();
            MachMAl.eineLängereZeitNichts();

        }

    }

}
