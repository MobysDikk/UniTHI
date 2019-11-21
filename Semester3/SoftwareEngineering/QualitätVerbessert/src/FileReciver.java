
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * This class is the entry point in the compare application.
 * it will check if there are exactly two arguments given. 
 * If not there will be a log entry in scanlog.txt. 
 * A filelog.txt appears if the Arguments are no Files.
 * There will be also a log entry called threadlog.txt 
 * if the Thread gets interrupted 
 *
 *
 */

public class FileReciver{
     
    private static final Logger log = Logger.getLogger( FileReciver.class.getName() );

    public static void main( String [] args ) throws IOException {
        
        if( args.length != 2 ) {
            FileHandler handler = new FileHandler( "scanlog.txt" );
            handler.setLevel( Level.ALL );
            log.addHandler( handler );
            log.info("More or less than 2 arguments entered");
            return;        
        }
        
        File sourceFile = new File( args[ 0 ] );
        File targetFile = new File( args[ 1 ] );
        
        if( sourceFile.canRead()== false || targetFile.canRead() == false) {
            FileHandler handler = new FileHandler( "filelog.txt" );
            handler.setLevel( Level.ALL );
            log.addHandler( handler );
            log.info("Wrong Input Type. Enter Files");
            return;  
        }

        FileComperator prototyp = new FileComperator( new File( args[ 0 ] ),  new File( args[ 1 ] ) );

        while( true ) {
            if( ! prototyp.compare() ) {
                SourceFileCopy.copy( new File( args[ 0 ] ),  new File( args[ 1 ] ) );
            }
            try {
                Thread.sleep( 10000 );
            } catch (InterruptedException ex) {
                FileHandler handler = new FileHandler( "threadlog.txt" );
                handler.setLevel( Level.ALL );
                log.addHandler( handler );
                log.info("Thread sleep didnt work");
            }
            
        }
    }
}

