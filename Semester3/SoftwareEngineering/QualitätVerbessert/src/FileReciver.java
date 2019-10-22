

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * This class is the entry point in the compare application.
 * it will check if there are exactly two arguments given. 
 * 
 *
 */

public class FileReciver{
     
    private static final Logger log = Logger.getLogger( FileReciver.class.getName() );

    public static void main( String [] args ) throws IOException, InterruptedException {
        
        if( args.length != 2 ) {
            FileHandler handler = new FileHandler( "log.txt" );
            handler.setLevel( Level.ALL );
            log.addHandler( handler );
            log.info("More or less than 2 arguments entered");
            return;        
        }

        FileComperator prototyp = new FileComperator( new File( args[ 0 ] ),  new File( args[ 1 ] ) );

        while( true ) {
            if( ! prototyp.compare() ) {
                SourceFileCopy.copy( new File( args[ 0 ] ),  new File( args[ 1 ] ) );
            }
            Thread.sleep( 10000 );
        }
    }
}

