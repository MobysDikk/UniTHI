package studiplayer.audio;
import java.io.File;

public abstract class AudioFile {

    // Atribute
    protected String pathName;
    protected String fileName;
    protected String author; // durch protected können nun Erb-Klassen diese Atribute benutzen
    protected String title;
    
    private String leer = "";
    private String sonderfall = "-";
    private String author_and_title;
    
    
    // For unit testing
    public AudioFile() {

    }

    // Normal constructor
    public AudioFile(String path_and_fileName) throws NotPlayableException {
        parsePathname(path_and_fileName);
        parseFilename(fileName);
        // getFilename();

        File cr = new File(pathName);
        if (cr.canRead() == false) {
            throw new NotPlayableException (pathName,"Der Pfad " + pathName + " ist nicht lesbar");
        }
    }

    // abstarct methoden für späteren verlauf

    public abstract void play() throws NotPlayableException;

    public abstract void togglePause();

    public abstract void stop();

    public abstract String getFormattedDuration();

    public abstract String getFormattedPosition();

    // Setters or alike
    public void parsePathname(String chaosPathName) {

        // pathName //
        String sLi = chaosPathName;

        // Einheitliche Slashes
        while (sLi.indexOf("\\") >= 0) {
            String sLi1 = sLi.substring(0, sLi.indexOf("\\"));
            String sLi2 = java.io.File.separatorChar + sLi.substring(sLi.indexOf("\\") + 1);
            sLi = sLi1 + sLi2;
        }
        // Laufwerksbuchstaben anpassen
        while (sLi.indexOf(":") >= 0) {
            sLi = java.io.File.separatorChar + sLi.replaceAll(":", java.io.File.separator);
        }
        // doppelte Slasches entfernen
        while (sLi.indexOf("//") >= 0) {
            sLi = sLi.replaceAll("//", java.io.File.separator);
        }

        pathName = sLi;

        // fileName //
        if (pathName.indexOf(".") < 0) {
            fileName = leer;
        }
        if (pathName.lastIndexOf(java.io.File.separator) > pathName.lastIndexOf(".")) {
            fileName = leer;
        }
        fileName = pathName.substring(pathName.lastIndexOf(java.io.File.separator) + 1);

    }

    public void parseFilename(String file_Name) {

        String fn = file_Name;

        // wenn im FileName ein " - " und "." vorkommt
        if (fn.indexOf(" - ") >= 0 && fn.indexOf(".") >= 0) {
            fn = fn.trim();

            author = fn.substring(0, fn.indexOf(" - "));

            title = fn.substring(fn.lastIndexOf(" - ") + 2, fn.lastIndexOf("."));

            // wenn nur "." vorkommt
        } else if (fn.indexOf(".") >= 0) {
            author = leer;
            title = fn.substring(0, fn.lastIndexOf("."));

            // wen nur ein " - " vorkommt
        } else if (fn.indexOf(" - ") >= 0) {

            author = leer;
            title = leer;

            // wenn kein " - " oder "." vorkommt
        } else {
            author = leer;
            title = fn;

        }

        // SOnderfall nur "-"
        if (fn == sonderfall) {
            author = leer;
            title = sonderfall;
        }

        if (author == leer) {
            author_and_title = title;

        } else {
            author_and_title = author + " - " + title;

        }
    }

    // Getters
    public String getPathname() {
        pathName = pathName.trim();
        return pathName;
    }

    public String getFilename() {
        fileName = fileName.trim();
        return fileName;
    }

    public String getAuthor() {
        author = author.trim();
        return author;
    }

    public String getTitle() {
        title = title.trim();
        return title;

    }
    public String toString() {
        author_and_title = author_and_title.trim();
        return author_and_title;
    }
    
    

    public abstract String[] fields();
}
