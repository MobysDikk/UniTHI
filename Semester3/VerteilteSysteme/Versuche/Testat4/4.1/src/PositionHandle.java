
public class PositionHandle extends Figur {
    
    
    
    PositionHandle(int x, int y) {
        super(x, y);
    }
    
     public void setPosition(int randome) {
         synchronized(this) {
    
             x = randome;
             MachMAl.eineZeitLangGarnichts();
             y = randome; 
         }
        
    }
    
    public void getPosition() {
        
        synchronized(this) {
        
        System.out.printf("Koordinaten x= %d  y = %d \n\n", x, y);
        }
    }
    

}
