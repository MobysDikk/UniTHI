public class AudioFile {

    // Atribute
    private String pathName;
    private String fileName;
    private String author;
    private String title =;
    private String leer;

    // For unit testing
    public AudioFile() {

    }

    // Normal constructor
    public AudioFile(String path_with_fname) {
    }

    // Setters or alike
    public void parsePathname(String chaosPathName) {

        // pathName //
        String sLi = chaosPathName;

        // Einheitliche Slashes
        while (sLi.indexOf("\\") >= 0) {
            String sLi1 = sLi.substring(0, sLi.indexOf("\\"));
            String sLi2 = "/" + sLi.substring(sLi.indexOf("\\") + 1);
            sLi = sLi1 + sLi2;
        }
        // Laufwerksbuchstaben anpassen
        while (sLi.indexOf(":") >= 0) {
            sLi = java.io.File.separatorChar + sLi.replaceAll(":", "/");
        }
        // doppelte Slasches entfernen
        while (sLi.indexOf("//") >= 0) {
            sLi = sLi.replaceAll("//", "/");
        }
        pathName = sLi;

        // fileName //
        if (pathName.length() > 0 && pathName.indexOf("/") >= 0) {
            if (pathName.charAt(pathName.length() - 1) == '/') {
                fileName = leer;
            } else {
                fileName = pathName.substring(pathName.lastIndexOf("/") + 1);
            }
            // wenn string leer oder keine "/" enthalten
        } else {
            fileName = pathName;

        }

    }

    public void parseFilename(String fileName) {

        String fn = fileName;

        // wenn der FileName ein " - " vorkommt
        if (fn.indexOf(" - ") >= 0) {

            // Leerzeichen vorne entfernen
            while (fn.charAt(0) == ' ') {
                fn = fn.substring(1);
            }
            author = fn.substring(0, fn.indexOf(" - "));
            // Anhängende Leerzeichen entfernen
            while (author.charAt(author.length() - 1) == ' ') {
                author = author.substring(0, author.length() - 1);
            }

            // Falls ein Punkt enthalten ist
            if (fn.indexOf(".") >= 0) {
                title = fn.substring(fn.lastIndexOf(" - ") + 2, fn.lastIndexOf("."));
                // vorlaufende Leerzeichen entfernen
                while (title.charAt(0) == ' ') {
                    title = title.substring(1);
                }
                // nachlaufende Leerzeichen entferenen
                while (title.charAt(title.length() - 1) == ' ') {
                    title = title.substring(0, title.length());
                }

            } else {
                title = fn.substring(fn.lastIndexOf(" - ") + 2);
                // vorlaufende Leerzeichen entfernen
                while (title.charAt(0) == ' ') {
                    title = title.substring(1);
                }
                // nachlaufende Leerzeichen entferenen
                while (title.charAt(title.length() - 1) == ' ') {
                    title = title.substring(0, title.length());
                }
            }

            // wenn kein " - " vorkommt
        } else {
            author = leer;

            if (fn.indexOf(".") >= 0) {
                title = fn.substring(0, fn.lastIndexOf("."));
            } else {
                title = fn;
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
