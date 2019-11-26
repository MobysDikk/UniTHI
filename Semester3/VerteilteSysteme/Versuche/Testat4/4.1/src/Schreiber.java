
public class Schreiber extends Thread {

    private Figur f;
    private int randome =0;
    public Schreiber(Figur figure) {
       this.f = figure;
    }
    
    public void run () {
        while(true) {
            
            randome = (int) (Math.random() * 8);
            f.setPosition(randome);
        }
        
      }

     
    

}
