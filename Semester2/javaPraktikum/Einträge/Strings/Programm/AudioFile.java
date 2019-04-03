public class AudioFile
{
  public static void main (String[] args) // parsePathname
  {
    String pathName ="file.mp3 ";

    // Slashes sortieren und Laufwerk/////////////////////////////////////////////////////////////////////////////

    // Windows
     String sWin = pathName.replaceAll("/","\\\\");
     if(pathName.indexOf(":")>=0)
         {sWin = sWin.substring(sWin.indexOf(":")-1);}

    System.out.println(sWin);

    // Linux 
     String sLi = pathName.replaceAll("\\\\","/");

     if(pathName.indexOf(":")>=0)
         {sLi = "/" + sLi.replaceAll(":","/");}
     System.out.println(sLi);
     System.out.println("");
  
     getPathname(sWin, sLi);

  }
   // getPathname ///////////////////////////////////////////////////////////////////////////////////////////////
  public static void  getPathname(String sWin, String  sLi)
  {
    
    //doppelte Slasches entfernen
    // Windows
    while(sWin.indexOf("\\\\\\\\")>0)
    {
      sWin = sWin.replaceAll("\\\\\\\\","\\\\");
    }
    System.out.println(sWin);

    // Linux
    while(sLi.indexOf("//")>=0)
    {
      sLi = sLi.replaceAll("//","/");
    }
    System.out.println(sLi);



  }

}
