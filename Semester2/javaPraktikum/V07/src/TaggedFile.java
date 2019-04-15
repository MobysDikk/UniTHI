import java.util.Map;

import studiplayer.basic.TagReader;

public class TaggedFile extends SampledFile {

    // Atribute
    
    
    private String album;
    private String toString;
    
    //konstruktor für Unitests
    public TaggedFile () {
        super();          // ruft den KOnstrukter der SUperklasse auf
                          // wird normlaserweise von alleine erzeugt
        
    }
    
    // konstruktor
    public TaggedFile(String s) {
        super(s);
        readAndStoreTags(fileName);
    }
    
    // Seters ////////////////////////////////////////////////////////
    
   
    
    public void readAndStoreTags(String pathname) { //HashMaps https://www.geeksforgeeks.org/hashmap-get-method-in-java/
        
        Map<String, Object> tag_map = TagReader.readTags(pathname);
        String t  = (String) tag_map.get("title");
        String au = (String) tag_map.get("author");
        duration  = (long) tag_map.get("duration");
        String al = (String) tag_map.get("album");
        author = au.trim();
        title = t.trim();
        album = al.trim();
    }
    
    
    // Getters /////////////////////////////////////////////////////////
    
    
  
    
    
    public String getAlbum() {
        return album;
    }
    
    public String toString() {
        if(album == "") { 
            toString = author+ " - " +title + " - " + time_duration;
        }
        else {
            toString = author+ " - " +title + " - "+ "album"+ " - " + time_duration;
        }
        return toString;
    }

    
    public String[] fields() { // abstarct in oberclasse
       
        return null;
    }

       
}
    
    


