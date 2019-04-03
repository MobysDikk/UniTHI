public class SlashWeg
{
  public static void main(String[] args)
  {

    String s = "//////Hallo//wie///gehts euch so//?////";

    
    // vordere Backslashes löschen
      int pos =0;

      while(s.charAt(pos) == '/')
      {
      if(s.charAt(pos) == '/')
      {
        pos++;
      }
      }
   
      // hintere Backslashes
      int pos2 = s. length();
      System.out.println(pos2);
    
      while(s.charAt(pos2-1) == '/')
      {
      if(s.charAt(pos2-1) == '/')
      {
        pos2--;
      }
      }
      s = s.substring(pos,pos2);
   
      // mittlere doppel Backslasches zu einem machen

      while(s.indexOf("//") > 0)
      {
      int IndexNr = s.indexOf("//");
      String Teil1 = s.substring(0,IndexNr);
      String Teil2 = s.substring(IndexNr+1);
      s = Teil1 + Teil2;
      }
      
      
      
      System.out.println(s);
   }
}
