import java.util.Map;

import studiplayer.basic.TagReader;

public class TaggedFile extends SampledFile {

    // Atribute

    private String album;

    // konstruktor für Unitests
    public TaggedFile() {
        super(); // ruft den KOnstrukter der SUperklasse auf
                 // wird normlaserweise von alleine erzeugt

    }

    // konstruktor
    public TaggedFile(String s) {
        super(s);
        readAndStoreTags(pathName);
    }

    // Seters ////////////////////////////////////////////////////////

    public void readAndStoreTags(String pathname) {

        Map<String, Object> tag_map = TagReader.readTags(pathname);// HashMaps
                                                                   // https://www.geeksforgeeks.org/hashmap-get-method-in-java/

        // überprüfen ob ein key überhaupt vorhanden ist
        // https://www.mkyong.com/java/java-check-if-key-exists-in-hashmap/

        if (tag_map.containsKey("author")) {
            String au = (String) tag_map.get("author");

            if (au == null) {                 // MERKE: bei oder verknüpfungen muss die "null" abfrage immer als letztes stehen
                author = super.author;
            }

            if (au != null) {
                au.trim();
                if (au != "") {
                    author = au;
                }
            }
        }

        if (tag_map.containsKey("title")) {
            String t = (String) tag_map.get("title");

            if (t == null) {
                title = super.title;
            }

            if (t != null) {
                t.trim();
                if (t != "") {
                    title = t;

                }
            }
        }

        if (tag_map.get("album") != null) {
            album = (String) tag_map.get("album");
        } else {
            album = null;
        }

        duration = (long) tag_map.get("duration");

    }

    // Getters /////////////////////////////////////////////////////////

    public String getAlbum() {
        album = album.trim();
        return album;
    }

    public String toString() {
        if (album == null) {
            String all = (getAuthor() + " - " + getTitle() + " - " + timeFormatter(duration)).trim();
            if (all.charAt(0) == '-') {
                all = all.substring(2);
            }
            return all;
        } else {
            String all = (getAuthor() + " - " + getTitle() + " - " + getAlbum() + " - " + timeFormatter(duration))
                    .trim();
            if (all.charAt(0) == '-') {
                all = all.substring(2);
            }
            return all;
        }

    }

    public String[] fields() { // abstarct in oberclasse
        String[] graphic_surface = new String[4];

        graphic_surface[0] = getAuthor();
        graphic_surface[1] = getTitle();
        graphic_surface[2] = getAlbum();
        graphic_surface[3] = timeFormatter(duration);

        return graphic_surface;
    }

}
