package studiplayer.audio;

import java.util.Comparator;

public class AlbumComparator implements Comparator<AudioFile> {

    public int compare(AudioFile af1, AudioFile af2) {

        // bouth objects are TaggedFile
        if (af1 instanceof TaggedFile == true && af2 instanceof TaggedFile == true) {

            TaggedFile tf1 = (TaggedFile) af1;
            TaggedFile tf2 = (TaggedFile) af2;

            if (tf1 == null & tf2 == null) {
                return 0;
            } else if (tf1 != null & tf2 == null) {
                return -1;
            } else if (tf1 == null & tf2 != null) {
                return 1;
            } else {
                
//               //Debugg
//                String stf1 = tf1.getAlbum();
//                String stf2 = tf2.getAlbum();
//                System.out.println(tf2);
                

                if (tf1.getAlbum() == null && tf2.getAlbum() == null) {
                    return 0;
                } else if (tf2.getAlbum() != null && tf1.getAlbum() == null) {
                    return -1;
                } else if (tf2.getAlbum() == null && tf1.getAlbum() != null) {
                    return 1;
                } else {
                    // af1 kommt vor af2 in der Liste vor
                    if (af1.getAuthor().compareTo(af2.getAuthor()) < 0) {
                        return -1;
                    }
                    // af2 kommt nach af2 in der Liste vor
                    else if (af1.getAuthor().compareTo(af2.getAuthor()) > 0) {
                        return 1;

                    } else {
                        return 0;
                    }
                }
            }

            // af1 is no Tagged File
        } else if (af1 instanceof TaggedFile == false && af2 instanceof TaggedFile == true) {
            return -1;

            // af2 is no TaggedFile
        } else if (af1 instanceof TaggedFile == true && af2 instanceof TaggedFile == false) {
            return 1;

            // bouth no TaggedFile
        } else {
            return 0;
        }
    }

}
