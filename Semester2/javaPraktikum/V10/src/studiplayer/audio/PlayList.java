package studiplayer.audio;

import java.util.LinkedList;

import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

public class PlayList extends LinkedList<AudioFile> { // Listen befehle:
                                                      // https://www.tutorialspoint.com/java/util/java_util_linkedlist.htm

    // Atributes
    private int curPos;
    private boolean playMod;

    // Konstruktors
    public PlayList() {

    }

    public PlayList(String a) {
        setCurrent(curPos);
        setRandomOrder(playMod = false);

        try {
            loadFromM3U(a);
        } catch (NotPlayableException e) {

        }

    }

    // Setters
    public void setCurrent(int current) {
        curPos = current;
    }

    public void setRandomOrder(boolean randomeOrder) {
        playMod = randomeOrder;
        if (playMod = true) {
            Collections.shuffle(this);
        }
    }

    public void changeCurrent() {
        if (playMod = false) {
            if (this.size() - 1 == curPos) {
                curPos = 0;
            } else {
                curPos++;
            }
        }

        if (playMod = true) {
            if (this.size() - 1 == curPos) {
                Collections.shuffle(this);
                curPos = 0;
            } else {
                curPos++;
            }
        }
    }

    // Getters
    public int getCurrent() {
        return curPos;
    }

    public AudioFile getCurrentAudioFile() {

        if (this.isEmpty()) {
            return null;
        } else if (this.size() <= curPos) { // !!!!!!!!!!!!!!!!!!!!
            return null;
        } else {
            return this.get(curPos);
        }

    }

    // M3U
    public void saveAsM3U(String pathname) {
        FileWriter writer = null;

        String fname = pathname;
        // String linesep = System.getProperty("line.seperator");
        String linesep = "\n";
        try {

            writer = new FileWriter(fname);

            writer.write("# MorisB Liste" + linesep);
            for (int i = 0; i <= this.size() - 1; i++) {
                writer.write(this.get(i).getPathname() + linesep);

            }

        } catch (IOException e) {
            throw new RuntimeException("Unable to write to file " + fname + ":" + e.getMessage());
        } finally {
            try {
                writer.close();
            } catch (Exception e) {

            }

        }
    }

    public void loadFromM3U(String pathname) throws NotPlayableException {

        this.clear();

        String fname = pathname;
        Scanner scanner = null;
        String line;

        try {
            // Create a Scanner
            scanner = new Scanner(new File(fname));
            
             
            // read line by line
            // int i =1;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                line = line.trim();

                if (!line.isEmpty()) {
                    if (line.charAt(0) != '#') {
                        try {
                            this.add(AudioFileFactory.getInstance(line));
                        } catch (NotPlayableException e) {
                            e.printStackTrace();
                        }}}}

           
        } catch (IOException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);

        } finally {
            try {
                scanner.close();
            } catch (Exception e) {
            }
        }

    }

    public void sort(SortCriterion order) { // ENUM https://www.youtube.com/watch?v=NIUGbgLU5Uk

        switch (order) {
        case AUTHOR:
            Collections.sort(this, new AuthorComparator());
            break;
        case TITLE:
            Collections.sort(this, new TitleComparator());
            break;
        case ALBUM:
            Collections.sort(this, new AlbumComparator());
            break;
        case DURATION:
            Collections.sort(this, new DurationComparator());
            break;
        }
    }

}
