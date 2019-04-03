public class Filename
{
public static void main(String[] args)
{
  String s = "aöo/ifnöovnaoiün:...n/weff.mp3";

  int lastPoint = s.lastIndexOf(".");
  int lastSlash = s.lastIndexOf("/");

  String DatEnd = s.substring(lastPoint);
  String FileName = s.substring(lastSlash+1,lastPoint);
  s = FileName + DatEnd;

  System.out.println(s);
}
}
