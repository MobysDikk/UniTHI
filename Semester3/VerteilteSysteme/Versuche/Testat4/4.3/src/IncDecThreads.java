
public class IncDecThreads  {
    
    
    public static void main (String[] args) {
    Int zahl = new Int(0);
    Inc inc = new Inc(zahl);
    Dec dec = new Dec(zahl);
  
    inc.start();
    dec.start();
    
   

}

}
