
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class SourceFileCopy {
    /*
     * This class copy the source File content and 
     * overwrite the content of the target File
     * 
     * 
     * @param sorceFile
     * @param targetFile
     * @throws IOException
     */
    
    public static void copy( File sourceFile, File targetFile ) throws IOException{

        FileInputStream  fIn  = new FileInputStream( sourceFile );
        FileOutputStream fOut = new FileOutputStream( targetFile );

        byte [] content = new byte[ 4096 ];

        int length = fIn.read( content );
        fOut.write( content, 0, length );

        fIn.close();
        fOut.close();
    }

}
