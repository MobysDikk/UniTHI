public class Interpret 
{
  public static void main (String[] args)
  { 
    String s ="     .mp3";
    String cb =" Frankie Goes To Hollywood - The Power Of Love.ogg"; 

    System.out.println(s);

    // Leerzeichen vorne weg
     while(s.charAt(0) == ' ')
     { s = s.substring(1);}

     //System.out.println(s);

   // Datein endung entfernen
      
    int letzterPunkt=  s.lastIndexOf(".");
        s= s.substring(0,letzterPunkt);
  
     

   // Trennung Autro und Songname
    if(s.indexOf(" - ") > 0)
    {
    int trennung = s.indexOf(" - ");
     
    String autor = s.substring(0,trennung);
    String songName = s.substring(trennung+3);

    System.out.println(autor);
    System.out.println(songName);
    }

    else { System.out.println(s);}
  }
}
