/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package affichage;

import MixeYoutube.Chronometre;
import MixeYoutube.DurationBar;
import MixeYoutube.ThreadAnalyze;
import MixeYoutube.ThreadBPM;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

/**
 *
 * @author Maxen
 */
public class LecteurMp3 extends JPanel implements ActionListener, ChangeListener,MouseListener{
    private JLabel titre,piste,bpmLabel;
    private JButton playButton, resetButton, liste;
    public JProgressBar songProgressBar;
    private JSlider volumeSlider; 
    private boolean isPlay = false;
    private double volumeMaster=1.0;
    private double volumeChange=1.0;
    
    public ArrayList<File> listeMusique = new ArrayList<>();
    
    public Chronometre time = new Chronometre();
    public long ls;
    DurationBar tpmp;
    private long taille;
    
//    SamplingGraph samplingGraph;
    
//    private MP3Player mp3Player;
    private BasicPlayer mp3Player = new BasicPlayer();

    public LecteurMp3(int i) {
        
        titre= new JLabel("");
        piste= new JLabel("Piste "+i);
        piste.setPreferredSize(new Dimension(70, 40));
        bpmLabel = new JLabel(" BPM");
        bpmLabel.setPreferredSize(new Dimension(70, 40));
        
        playButton = new JButton("Play");
        playButton.setBackground(Color.GREEN);
        playButton.setPreferredSize(new Dimension(70, 40));
        resetButton = new JButton("Reset");
        resetButton.setBackground(Color.ORANGE);
        resetButton.setPreferredSize(new Dimension(70, 40));
        liste = new JButton("Liste");
        liste.setBackground(Color.PINK);
        liste.setPreferredSize(new Dimension(70, 40));
        songProgressBar = new JProgressBar();
        songProgressBar.setPreferredSize(new Dimension((int) (getPreferredSize().width*0.99), 20));
        songProgressBar.setStringPainted(true);
        songProgressBar.setForeground(Color.RED);
        
        volumeSlider = new JSlider(0, 10000, 10000);
        volumeSlider.setPreferredSize(new Dimension((int) ((getPreferredSize().width-70*6)*0.99), 40));
        
        tpmp = new DurationBar(this);
        tpmp.start();
        
//        titre.setBackground(ColorListe.Black);
//        titre.setForeground(Color.white);
//        piste.setBackground(ColorListe.Black);
//        piste.setForeground(Color.white);
//        songProgressBar.setBackground(ColorListe.GrisF);
//        
//        songProgressBar.setBorder(new LineBorder(Color.WHITE,3,true));
//        volumeSlider.setBackground(ColorListe.GrisF);
//        volumeSlider.setForeground(Color.red);
        
//        this.setBackground(ColorListe.GrisF);
        this.setBorder(new LineBorder(Color.BLACK,3,true));
        
        this.setLayout(new GridBagLayout());

        GridBagConstraints cont = new GridBagConstraints();
        
        
        cont.gridx=0;
        cont.gridy=0;
        cont.gridwidth=6;
        this.add(titre, cont);
        cont.gridx=0;
        cont.gridy=1;
        cont.gridwidth=6;
        this.add(songProgressBar, cont);
        
        cont.gridx=0;
        cont.gridy=2;
        cont.gridwidth=1;
        this.add(playButton, cont);
        cont.gridx=1;
        cont.gridy=2;
        cont.gridwidth=1;
        this.add(resetButton, cont);
        cont.gridx=2;
        this.add(bpmLabel, cont);
        cont.gridx=3;
        this.add(volumeSlider, cont);
        cont.gridx=4;
        this.add(liste, cont);
        cont.gridx=5;
        this.add(piste, cont);
        
        playButton.addActionListener(this);
        resetButton.addActionListener(this);
        liste.addActionListener(this);
        volumeSlider.addChangeListener(this);
        
        songProgressBar.addMouseListener(this);
    }
    
    public void ChangeMaster(double max){
        volumeMaster=max;
        stateChanged(new ChangeEvent(volumeSlider));
    }
    
    public void ChangeChange(double vol){
        volumeChange=vol;
        stateChanged(new ChangeEvent(volumeSlider));
    }
    
    public void mettreMusique(File link){
        if (!isPlay) {
            try {
                ThreadBPM bpm = new ThreadBPM(link,bpmLabel);
                bpm.start();
                
                ThreadAnalyze anal = new ThreadAnalyze(link);
                anal.start();
                
                time.reset();
//                System.out.println(link.toString());
                mp3Player.open(link);
                mp3Player.play();
                mp3Player.pause();
                titre.setText(link.getName());
                mp3Player.setGain(volumeSlider.getValue()/10000.0*volumeMaster*volumeChange);
                isPlay=false;
                
                taille = link.length();
                
                Encoder encoder = new Encoder();
                try {
                    MultimediaInfo mi = encoder.getInfo(link);
                    ls = mi.getDuration();
                    ls=ls/1000;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                tpmp.reset(this);
                
//                tpmp.start();
                
            } catch (BasicPlayerException ex) {
                Logger.getLogger(LecteurMp3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            listeMusique.add(link);
//            System.out.println(listeMusique);
        }
    }
    
    public void passMusique(){
//        System.out.println(listeMusique.isEmpty());
        if (listeMusique.isEmpty()) {
//            pauseMusique();
        }
        else{
            try {
                BasicPlayer tmp = new BasicPlayer();
                ThreadBPM bpm = new ThreadBPM(listeMusique.get(0),bpmLabel);
                bpm.start();
                
                ThreadAnalyze anal = new ThreadAnalyze(listeMusique.get(0));
                anal.start();
                
                time.reset();
                tmp.open(listeMusique.get(0));


                tmp.play();
                titre.setText(listeMusique.get(0).getName());
                tmp.setGain(volumeSlider.getValue()/10000.0*volumeMaster*volumeChange);
                isPlay=true;
                
                taille = listeMusique.get(0).length();
                
                Encoder encoder = new Encoder();
                try {
                    MultimediaInfo mi = encoder.getInfo(listeMusique.get(0));
                    ls = mi.getDuration();
                    ls=ls/1000;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                mp3Player=tmp;
                tpmp.reset(this);
                time.play();

                listeMusique.remove(0);
            } catch (BasicPlayerException ex) {
                Logger.getLogger(LecteurMp3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void pauseMusique(){
        try {
            mp3Player.pause();
            playButton.setBackground(Color.GREEN);
            resetButton.setEnabled(true);
            playButton.setText("Play");
            isPlay=false;
            time.pause();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(LecteurMp3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void playMusique(){
        try { 
            mp3Player.resume();
            playButton.setBackground(Color.red);
            resetButton.setEnabled(false);
            playButton.setText("Pause");
            isPlay=true;
            time.play();
        } catch (BasicPlayerException ex) {
            System.out.println(ex);
        }
    }

    public boolean isIsPlay() {
        return isPlay;
    }
    
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension((int) (Mixage.tailleMixage.width*0.99), 100);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==liste) {
            JDialogListe tmp = new JDialogListe(listeMusique);
            tmp.showDialog();
        }
        if (e.getSource()==playButton) {
            if (!isPlay) {
                playMusique();
            }
            else{
                pauseMusique();
            }
            
        }
        if (e.getSource()==resetButton){
            if (!isPlay) {
                try {
                    time.reset();
                    mp3Player.seek(0);
                } catch (BasicPlayerException ex) {
                    Logger.getLogger(LecteurMp3.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
    }
    
    public static void main(String[] args) {
        JFrame t = new JFrame();
        t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LecteurMp3 tmp = new LecteurMp3(1);
        tmp.mettreMusique(new File(".\\PlayList\\Download\\Stromae - Alors On Danse (Official Music Video) [VHoT4N43jK8].mp3"));
        t.add(tmp);
        t.pack();
        t.setVisible(true);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource()==volumeSlider) {
            try {
//                System.out.println("volMaster : "+volumeMaster+" volChange : "+volumeChange);
                mp3Player.setGain(volumeSlider.getValue()/10000.0*volumeMaster*volumeChange);
            } catch (BasicPlayerException ex) {
                ;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource()==songProgressBar) {
            try {
                double coef = (double)taille/(double)songProgressBar.getPreferredSize().width;
//                System.out.println(e.getX()*coef);
                mp3Player.seek((long) (e.getX()*coef));
                
                coef = (double)ls/(double)songProgressBar.getPreferredSize().width;
                time.setTime((int) (e.getX()*coef));
                
//                System.out.println(e.getX());
            } catch (BasicPlayerException ex) {
                Logger.getLogger(LecteurMp3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        ;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        ;
    }

    
}
