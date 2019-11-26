
public abstract class Figur {
    
    public String name = "Königin";
    public int x;
    public int y;
    
    Figur(int x, int y){
        this.x=x;
        this.y=y;
    }
    
    abstract public void setPosition(int randome);
    abstract public void getPosition();

}
