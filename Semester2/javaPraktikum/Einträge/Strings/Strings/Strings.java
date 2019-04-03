public class Strings{
  public static void main(String[] args)
  {
    String s = "Hallo duda ";
    System.out.println(s);
    
    // charAt();
    System.out.println("charAt();");
    
    char Nr5=s.charAt(4);
    System.out.println(Nr5);
    
    System.out.println(""); // Würdest du eine Zahl eingeben die höher wäre als der String => fehlermeldung
    
    // Substring
    System.out.println("Substring");

    String sub = s.substring(6);
    System.out.println(sub);

    String sub3 = s.substring(0,0);
    System.out.println(sub3);

    String sub2 = s.substring(0,5);
    System.out.println(sub2);
    System.out.println("");

    // length();

    System.out.println("length();");

    int a = s.length(); // Funktion hat keien Parameter

    System.out.println(a);
    System.out.println("");
    

    // indexOF()
     System.out.println("indexOf");
     int a = s.indexOf(!"H");
     System.out.println(a);


    //compare()
    //Vergeleicht zwei Strings mit eineander und gibt einen Bollean wert zurück.


    //compareTo()
    //Vergleicht den Lexkinaschinen inhalt von Zwei strings miteinander und gibt an, wekcher
    //im alphabet als erstes ankommt
    //


    //indexOf()
     System.out.println("indexOf()");
     int po = s.indexOf(" ");    // => Bestimmt das erste Leerzeichen
     int pos = s.indexOf(" ",4);  // => Bestimmung des ersten Leerzeichens nach der 4 Positoin)
     int posi = s.indexOf("duda"); // => Bestimmung des Buchstaben/Wortes 
     
     System.out.println(po);
     System.out.println(pos);
     System.out.println(posi);
     System.out.println("");
    
    //lastIndexOf()
     System.out.println("lasIndexOf()");
     int back = s.lastIndexOf(" ");//wenn es nicht vorkokmmt ist das Resultat => -1
     System.out.println(back);
     System.out.println("");


  }
}

