/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package affichage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Maxen
 */
public class MaFenetre extends JFrame implements MouseListener, FocusListener,ChangeListener{
    public static Dimension tailleFenetre;
    private Recherche recheche;
    private Playliste playliste;
    private YoutubeDownloadLink YTLink ;
   
    JTabbedPane multiZone = new JTabbedPane();
    
    
    Mixage mixage;
    public ArrayList<DownLoadMusique> listeMusique = new ArrayList<>();
    public ArrayList<File> listeFile  = new ArrayList<>();
    
    public ArrayList<LecteurMp3> mix= new ArrayList<>();
    
    
    
    public MaFenetre(){
        this.setTitle("MixYoutube");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tailleFenetre=getPreferredSize();
        tailleFenetre.height=tailleFenetre.height-25;
        
        mixage = new Mixage();
        mix=mixage.mix;
        
        
        
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (device.isFullScreenSupported()) {
//            device.setFullScreenWindow(this);
        } else {
            System.err.println("Le mode plein ecran n'est pas disponible");
        }
        
        initialisation();
        this.pack();
        multiZone.addChangeListener(this);
    }
    
    private void initialisation(){
        
        multiZone.removeAll();
        recheche= new Recherche();
        playliste = new Playliste(this);
        YTLink = new YoutubeDownloadLink();
        
        listeMusique = playliste.listeMusique;
        listeFile = playliste.listeFile;
        
        multiZone.add("Playlist",playliste);
        
        multiZone.add("Recherche Youtube",recheche);
        
        multiZone.add("Dowload lien Youtube", YTLink);
        
        multiZone.setPreferredSize(new Dimension(tailleFenetre.width/2-10, tailleFenetre.height-20));
        
        
        
        JPanel pano=new JPanel();
        
        
        //ajout du gestionnaire de placement de pano
        
        pano.setLayout(new GridBagLayout());
        
        GridBagConstraints cont = new GridBagConstraints();
        
        //placment des composants
        
        cont.gridx=0;
        cont.gridy=0;
        pano.add(mixage, cont);
        
        cont.gridx=1;
        cont.gridy=0;
        pano.add(multiZone, cont);
        
        // placement du pano dans JFrame
        
        this.setContentPane(pano);
        button();
        
    }
    
    public void button(){
        for (int i = 0; i < listeMusique.size(); i++) {
            listeMusique.get(i).addMouseListener(this);
        }
    }

    

    @Override
    public void focusGained(FocusEvent e) {
        ;
    }

    @Override
    public void focusLost(FocusEvent e) {
        
    }
    
    @Override
    public Dimension getPreferredSize() {
        Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        tailleEcran = new Dimension(tailleEcran.width, tailleEcran.height-30);
        return tailleEcran;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource()==multiZone) {
            YTLink.Jtexte.setText("mettre le lien");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        for (int i = 0; i < listeMusique.size(); i++) {
            if (e.getSource()==listeMusique.get(i)) {
                JDialogChoixMix tmp = new JDialogChoixMix(this,mix);
                tmp.setLocation(new Point(e.getXOnScreen(), e.getYOnScreen()));
                int j = tmp.showDialog();
                mix.get(j).mettreMusique(listeFile.get(i));
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
        for (int i = 0; i < listeMusique.size(); i++) {
            if (e.getSource()==listeMusique.get(i)) {
                listeMusique.get(i).setBackground(Color.BLUE);
                listeMusique.get(i).jTitre.setForeground(Color.WHITE);
                listeMusique.get(i).bpm.setForeground(Color.WHITE);
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        for (int i = 0; i < listeMusique.size(); i++) {
            if (e.getSource()==listeMusique.get(i)) {
                listeMusique.get(i).setBackground(Color.WHITE);
                listeMusique.get(i).jTitre.setForeground(Color.BLACK);
                listeMusique.get(i).bpm.setForeground(Color.BLACK);
            }
        }
    }
}
