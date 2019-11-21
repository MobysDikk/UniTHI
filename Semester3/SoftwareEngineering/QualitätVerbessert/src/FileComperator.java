
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
    private String bufferMessageA = null;
    private String bufferMessageB = null;

    public FileComperator( File fileA, File fileB ) {
        this.fileA = fileA;
        this.fileB = fileB;
    }

    public boolean compare() throws IOException{

        BufferedReader readerA = null;
        BufferedReader readerB = null;

        try {

            readerA = new BufferedReader( new FileReader( fileA ) );
            readerB = new BufferedReader( new FileReader( fileB ) );
            bufferMessageA = readerA.readLine();
            bufferMessageB = readerB.readLine();
            
            while ( true ) {
                
                if ( bufferMessageA == null || bufferMessageB == null || bufferMessageA.compareTo( bufferMessageB ) != 0 ) {
                    break;
                }
    
                bufferMessageA = readerA.readLine();
                bufferMessageB = readerB.readLine();
            } 
        
        } catch (IOException e) {
            e.fillInStackTrace();
        }
        finally {
            readerA.close();
            readerB.close();
        }
        
        return false;
    }
    


}

