public class AudioFile
{
 



  public static void main (String[] args) // parsePathname
  {
    String pathName ="////////d:\\\\\\\\file.mp3 ";

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
    System.out.println("");

    getFilename(sWin, sLi);


  }

  // getFilename /////////////////////////////////////////////////////////////////////////////////////////////////
  public static void getFilename(String sWin, String sLi)
  {
    if(sWin.indexOf("\\\\") >=0)
    {
      String winFile = sWin.substring(sWin.lastIndexOf("\\\\"));
      System.out.println(winFile);
      System.out.println(sWin.lastIndexOf("\\\\"));
    }
    if(sLi.indexOf("/") >=0)
    {
      String liFile = sLi.substring(sLi.lastIndexOf("/"));
      System.out.println(liFile);
      System.out.println(sLi.lastIndexOf("/"));
    }
    else
    { System.out.println(sWin);
      System.out.println(sLi);
    }
  }


}
