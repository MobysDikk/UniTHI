
public class IncDecThreads  {
    private static int toggle = 0;
    
    public static void main (String[] args) {
    
    
    
    Inc inc = new Inc();
    Dec dec = new Dec();
  
    inc.start();
    dec.start();
    
   

}

    public static int getToggle() {
        return toggle;
    }

    public static void setToggle(int toggle) {
        IncDecThreads.toggle = toggle;
    }
}
