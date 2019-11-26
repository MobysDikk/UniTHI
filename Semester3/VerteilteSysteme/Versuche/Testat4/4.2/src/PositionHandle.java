
public class PositionHandle extends Figur {
    
    
    PositionHandle(int x, int y) {
        super(x, y);
        // TODO Auto-generated constructor stub
    }
    
    public void setPosition(int randome) {
        x = randome;
        MachMAl.eineZeitLangGarnichts();
        y = randome;
    }
    
    public void getPosition() {
        System.out.printf("Koordinaten x= %d  y = %d \n\n", x, y);
        MachMAl.eineLängereZeitNichts();
        
    }

}
