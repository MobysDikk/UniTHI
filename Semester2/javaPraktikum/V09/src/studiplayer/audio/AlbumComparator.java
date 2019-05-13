package studiplayer.audio;

import java.util.Comparator;

public class AlbumComparator implements Comparator<AudioFile> {

    public int compare(AudioFile af1, AudioFile af2) {

        if (AudioFileFactory.getInstance(af1.getPathname()) instanceof TaggedFile == true
                && AudioFileFactory.getInstance(af2.getPathname()) instanceof TaggedFile == true) {
            TaggedFile tf1 = (TaggedFile) af1;
            TaggedFile tf2 = (TaggedFile) af2;

            if (tf1.getAlbum() == null) {
                throw new NullPointerException("AudioFile Album-af1 is null");
            }
            if (tf2.getAlbum() == null) {
                throw new NullPointerException("AudioFile Album-af2 is null");
            }
            if (tf1.getAlbum() == null && tf2.getAlbum() == null) {
                throw new NullPointerException("AudioFile Album-af1 and Album-af2 are null");
            }
            // af1 kommt vor af2 in der Liste vor
            if (tf1.getAlbum().compareTo(tf2.getAlbum()) < 0 || (tf1.getAlbum() == null)) {
                return -1;
            }
            // af2 kommt nach af2 in der Liste vor
            if (tf1.getAlbum().compareTo(tf2.getAlbum()) > 0 || (tf2.getAlbum() == null)) {
                return 1;
            }
            // af1 und af2 sind gleich
            if (tf1.getAlbum().compareTo(tf2.getAlbum()) == 0) {
                return 0;
            } else {
                return 2;
            }

        } else if (AudioFileFactory.getInstance(af1.getPathname()) instanceof TaggedFile == true
                && AudioFileFactory.getInstance(af2.getPathname()) instanceof TaggedFile == false) {
            return 1;
        } else if (AudioFileFactory.getInstance(af1.getPathname()) instanceof TaggedFile == false
                && AudioFileFactory.getInstance(af2.getPathname()) instanceof TaggedFile == true) {
            return -1;
        } else {
            return 2;
        }
    }

}
