package studiplayer.audio;

public class AudioFileFactory {
    
    public static AudioFile getInstance(String pathname) throws NotPlayableException  {
         
        int point = pathname.lastIndexOf(".");
        String suffix = pathname.substring(point);
        String end = suffix.toLowerCase();
        
        if(end.equals(".mp3") || end.equals(".ogg")) {
            return new TaggedFile(pathname); 
           
        }else if (end.equals(".wav")) {
           return new WavFile(pathname); 
        }else {
            throw new NotPlayableException (pathname,"Unkonwn suffix for Audiofile: \""+ pathname + "\"");
        }
        
        
       
    }

}
