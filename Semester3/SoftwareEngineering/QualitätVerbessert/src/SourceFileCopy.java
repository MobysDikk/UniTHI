
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SourceFileCopy {
    /*
     * This class copy the source File content and overwrite the content of the
     * target File
     * 
     * 
     * @param sorceFile
     * 
     * @param targetFile
     * 
     * @throws IOException
     */

    public static void copy(File sourceFile, File targetFile) throws IOException {

        FileInputStream fIn = null;
        FileOutputStream fOut = null;
        byte[] content = null;
        int fileSize = 0;

        try {

            fIn = new FileInputStream(sourceFile);
            fOut = new FileOutputStream(targetFile);
            content = new byte[1024];

            fileSize = fIn.read(content);

            while (fileSize > 0) {
                fOut.write(content, 0, fileSize);
                fileSize = fIn.read(content);
            }
        } finally {

            if (fIn != null) {

                try {
                    fIn.close();
                } catch (IOException e) {
                    // Do somthing for errro handling
                }
                ;
            }

            if (fOut != null) {

                try {
                    fOut.close();
                } catch (IOException e) {
                    // Do somthing for errro handling
                }
                ;
            }
        }

    }
}
