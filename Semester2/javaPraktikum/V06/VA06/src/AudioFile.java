public class AudioFile {

    // Atribute
    private String pathName;
    private String fileName;
    private String author;
    private String title;

    // For unit testing
    public AudioFile() {

    }

    // Normal constructor
    public AudioFile(String path_with_fname) {
    }

    // Setters or alike
    public void parsePathname(String chaosPathName) {

        // pathName
        String sLi = chaosPathName;
        // Einheitliche Slashes
        while (chaosPathName.indexOf("\\\\") >= 0) {
            sLi = chaosPathName.replaceAll("\\\\", "/");
        }
        // Laufwerksbuchstaben anpassen
        while (chaosPathName.indexOf(":") >= 0) {
            sLi = java.io.File.separatorChar + sLi.replaceAll(":", "/");
        }
        // doppelte Slasches entfernen
        while (sLi.indexOf("//") >= 0) {
            sLi = sLi.replaceAll("//", "/");
        }
        pathName = sLi;

        ////////////////////////////////////////////////////////////
        // fileName
        // falls an string leer und an der letzten positions ein '/' ist
        if (pathName.indexOf("/") >= 0) {

            int length = sLi.length() - 1;
            if (sLi.charAt(length) == '/') {
                sLi = sLi.substring(0, length);
            }
            fileName = sLi.substring(sLi.lastIndexOf("/"));
        }

        else {
            fileName = pathName;
        }

    }

    public void parseFilename(String filename) {

        String fn = filename;
        if (fn == "-") {
            title = "-";
        } else if (fn == " - ") {
            fn = "";
        } else {
            author = fn.substring(0, fn.indexOf(" - "));
            // Leerzeichen hinten und vorne entfernen
            while(author.charAt(0)==' ') {
                author.substring(1);
            }
            while(author.charAt(author.length()-1)==' ') {
                author.substring(0, author.length());
            }
            title = fn.substring(fn.indexOf(" - "), fn.lastIndexOf("."));
           // Leerzeichen hinten und vorne entfernen
            while(title.charAt(0)==' ') {
                title.substring(1);
            }
            while(title.charAt(title.length()-1)==' ') {
                title.substring(0, title.length());
            }
            
            
        }
       
        

    }

    // Getters
    public String getPathname() {
        return pathName;
    }

    public String getFilename() {
        return fileName;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;

    }
}
