
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class is responsible for reading the content of 
 * a given file as a collection of strings. It will also 
 * compare if they are equal or not.
 * 
 * @param fileA
 * @param fileB
 * @throws IOexceptoin
 *
 */

public class FileComperator {

    private File fileA;
    private File fileB;
    private BufferedReader readerA;
    private BufferedReader readerB;
    private String bufferMessageA = null;
    private String bufferMessageB = null;

    public FileComperator( File fileA, File fileB ) {
        this.fileA = fileA;
        this.fileB = fileB;
    }

    public boolean compare() throws IOException{

        readerA = new BufferedReader( new FileReader( fileA ) );
        readerB = new BufferedReader( new FileReader( fileB ) );
        
        while ( true ) {
            if ( ( bufferMessageA = readerA.readLine() ) == null || ( bufferMessageB = readerB.readLine() ) == null
            || bufferMessageA.compareTo( bufferMessageB ) != 0 ) {
                break;
            }
                
        }            
        return false;
    }
    


}

