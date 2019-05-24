package studiplayer.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import studiplayer.audio.AudioFile;
import studiplayer.audio.NotPlayableException;
import studiplayer.audio.PlayList;

@SuppressWarnings("serial")

public class Player extends JFrame implements ActionListener {

    // Atribute

    private String empty = "empty play list";
    private String noSong = "no current song";
    private String currentSong = "Current song: ";
    private String noTime = "--:--";
    private String startTime = "00:00";
    private static PlayList playList;
    public static String DEFAULT_PLAYLIST = "playlists/DefaultPlayList.m3u";
    private JLabel songDescription;
    private JLabel playTime;
    private JButton play;
    private JButton pause;
    private JButton stop;
    private volatile boolean stopped = true;

    public Player(PlayList pl) {

        playList = pl;

        // Initialize the main frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create GUI components
        // Title
        if (playList.isEmpty()) {
            setTitle("Studiplayer: " + empty);
        } else {
            setTitle(currentSong + playList.get(playList.getCurrent()).toString());
        }

        // JLabel currentSong
        if (playList.isEmpty()) {
            songDescription = new JLabel("<html>" + noSong + "</html> ");
        } else {
            songDescription = new JLabel("<html>" + playList.get(playList.getCurrent()).toString() + "</html> ");
        }
        add(songDescription, BorderLayout.NORTH);

        // JLabel PlayTime
        if (playList.isEmpty()) {
            playTime = new JLabel("<html>" + noTime + "</html> ");
        } else {
            playTime = new JLabel("<html>" + startTime + "</html> ");
        }
        add(playTime, BorderLayout.WEST);

        // Buttons

        JPanel buttons = new JPanel();

        play = new JButton(new ImageIcon("icons/play.png"));
        play.setActionCommand("AC_PLAY");
        play.addActionListener(this);
        buttons.add(play);
        pause = new JButton(new ImageIcon("icons/pause.png"));
        pause.setActionCommand("AC_PAUSE");
        pause.addActionListener(this);
        buttons.add(pause);
        stop = new JButton(new ImageIcon("icons/stop.png"));
        stop.setActionCommand("AC_STOP");
        stop.addActionListener(this);
        buttons.add(stop);
        JButton next = new JButton(new ImageIcon("icons/next.png"));
        next.setActionCommand("AC_NEXT");
        next.addActionListener(this);
        buttons.add(next);
        JButton pl_editor = new JButton(new ImageIcon("icons/pl_editor.png"));
        pl_editor.setActionCommand("AC_PL_EDITOR");
        pl_editor.addActionListener(this);
        buttons.add(pl_editor);

        // Button placement
        add(buttons, BorderLayout.CENTER);

        // Activate GUI
        this.pack();
        this.setVisible(true);

        if (stopped == true) {
            pause.setEnabled(false);
            stop.setEnabled(false);
            play.setEnabled(true);
        }
    }

    private void updateSongInfo(AudioFile af) {

        if (af != null) {
            setTitle(currentSong + playList.get(playList.getCurrent()).toString());
            songDescription.setText("<html>" + playList.get(playList.getCurrent()).toString() + "</html> ");
            playTime.setText("<html>" + startTime + "</html> ");
        } else {
            setTitle("Studiplayer: " + empty);
            songDescription.setText("<html>" + noSong + "</html> ");
            playTime.setText("<html>" + noTime + "</html> ");
        }
    }

    private void playCurrentSong() {
        AudioFile af;
        af = playList.getCurrentAudioFile();
        if(af!=null) {
        updateSongInfo(af);
        stopped = false;
        //Threads
        (new TimerThread()).start();
        (new PlayerThread()).start();
        
        }else { playList.changeCurrent();
        }

        System.out.println("Playing " + af.toString());
        System.out.println("Filename is " + af.getFilename());
        System.out.println("Current index is " + playList.getCurrent());

    }

    private void stopCurrentSong() {
       stopped = true;
        playList.getCurrentAudioFile().stop();
        
        updateSongInfo( playList.getCurrentAudioFile());
       // stopped = true;

        System.out.println("Playing " +  playList.getCurrentAudioFile().toString());
        System.out.println("Filename is " +  playList.getCurrentAudioFile().getFilename());
        System.out.println("Current index is " + playList.getCurrent());
     
    }
    
    private void playStopNext() {
        if (stopped == true) {
            pause.setEnabled(false);
            stop.setEnabled(false);
            play.setEnabled(true);
        } else if (stopped == false) {
            play.setEnabled(false);
            pause.setEnabled(true);
            stop.setEnabled(true);
        }
    }

    public void actionPerformed(ActionEvent e) {

        AudioFile af;
        String cmd = e.getActionCommand();
        
        /////////////////////////////////////////////////////////////////////////////////////////
        if (cmd.equals("AC_PLAY")) {
            System.out.println("pressing Play");

            playCurrentSong();
            playStopNext();
            System.out.println("");
        /////////////////////////////////////////////////////////////////////////////////////////
        } else if (cmd.equals("AC_PAUSE")) {
            
            System.out.println("pressing Pause");
            
            af = playList.getCurrentAudioFile();
            playList.getCurrentAudioFile().togglePause();
            
            if (stopped == true) {
                pause.setEnabled(true);
                stop.setEnabled(true);
                play.setEnabled(false);
            } else if (stopped == false) {
                play.setEnabled(false);
                pause.setEnabled(true);
                stop.setEnabled(true);
            }
            System.out.println("");
        ////////////////////////////////////////////////////////////////////////////////////////////
        } else if (cmd.equals("AC_STOP")) {
            System.out.println("pressing Stop");
            stopCurrentSong();
            playStopNext();
            System.out.println("");
        ////////////////////////////////////////////////////////////////////////////////////////////
        } else if (cmd.equals("AC_NEXT")) {
            System.out.println("pressing Next");

            if (stopped == false) {
                stopCurrentSong();
            }
            playList.changeCurrent();
            playCurrentSong();
            af = playList.getCurrentAudioFile();
            updateSongInfo(af);
            playStopNext();

            System.out.println("");
        /////////////////////////////////////////////////////////////////////////////////////////////
        } else if (cmd.equals("AC_PL_EDITOR")) {

        }}

        private class TimerThread extends Thread {

            public void run() {                  
                    
                    
                while (!stopped && !playList.isEmpty()) {
                    playTime.setText("<html>" + playList.getCurrentAudioFile().getFormattedPosition()+"</html> ");
                    try {
                        sleep(100);

                    } catch (InterruptedException e) {

                    }
                
                
                }
                } }

        private class PlayerThread extends Thread {

            public void run() {

                while (!stopped && !playList.isEmpty()) {
                    try {
                        playList.getCurrentAudioFile().play();
                    } catch (NotPlayableException e) {

                        e.printStackTrace();
                    }
                    
                    if(stopped==false) {
                        playList.changeCurrent();
                        updateSongInfo(playList.getCurrentAudioFile());
                    }

                }

            }
        }
    

    public static void main(String[] args) {

        PlayList pl;

        if (args.length > 0) {
            pl = new PlayList(args[0]);

        } else {
            pl = new PlayList(DEFAULT_PLAYLIST);
        }
        new Player(pl);
    }

}
