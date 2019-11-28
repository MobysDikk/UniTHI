
public class PositionHandle extends Figur {

    PositionHandle(int x, int y) {
        super(x, y);
    }

    public synchronized void setPosition(int randome) {

        x = randome;
        MachMAl.eineLängereZeitNichts();
        y = randome;

    }

    public synchronized void getPosition() {

        System.out.printf("Koordinaten x= %d  y = %d \n\n", x, y);
    }

}
