/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package affichage;

import MixeYoutube.Musique;
import MixeYoutube.Musique;
import MixeYoutube.ThreadDownload;
import MixeYoutube.ThreadDownload;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author Maxen
 */
public class ZonneRecherche extends JPanel implements ActionListener{
    private JLabel jMiniature;
    private JLabel jTitre;
    private JLabel jAuteur;
    private JButton jButtonDl= new JButton("Download");
    
    Musique musique;

    public ZonneRecherche(Musique m) {
        musique=m;
        if (musique.getjMiniature()==""&&musique.getjTitre()==""&&musique.getjAuteur()=="") {
            jMiniature = new JLabel("");
            jTitre= new JLabel("");
            jAuteur = new JLabel("");
        }
        else{
            try {
                ImageIcon i = new ImageIcon(new URL(musique.getjMiniature()));
                Image zoom = scaleImage(i.getImage(), getPreferredSize().height-15);//taille en pixels
                Icon iconScaled = new ImageIcon(zoom);
                
                jMiniature = new JLabel(iconScaled);
                } catch (MalformedURLException ex) {
            }

            jTitre= new JLabel("Titre : "+musique.getjTitre());
            jAuteur = new JLabel("Auteur : "+musique.getjAuteur());
            
            
        }
        

        jTitre.setPreferredSize(new Dimension(getPreferredSize().width/2, 50));
        jAuteur.setPreferredSize(new Dimension(getPreferredSize().width/2, 50));
        this.setLayout(new GridBagLayout());

        GridBagConstraints cont = new GridBagConstraints();

        
        cont.gridx=0;
        cont.gridy=0;
        cont.gridheight=4;
        try {
            this.add(jMiniature, cont);
        } catch (Exception e) {
        }
        

        JLabel jtemp= new JLabel("");
        cont.gridx=1;
        cont.gridy=0;
        cont.gridheight=1;
        this.add(jtemp, cont);

        cont.gridx=1;
        cont.gridy=1;
        jTitre.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.add(jTitre, cont);


        cont.gridx=1;
        cont.gridy=2;
        jAuteur.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.add(jAuteur, cont);
        
        if (musique.getjMiniature()==""&&musique.getjTitre()==""&&musique.getjAuteur()=="") {
            
        }
        else{

            cont.gridx=2;
            cont.gridy=1;
            cont.gridheight=3;
            this.add(jButtonDl, cont);
        }
        

        //this.setBorder(new LineBorder(Color.BLACK));
        Border insideBorder = new LineBorder(Color.BLACK);
        Border outsideBorder = BorderFactory.createEmptyBorder(5, 2, 5, 2);

        this.setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));

        jButtonDl.addActionListener(this);
        
    }
    public static Image scaleImage(Image source, int width, int height) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) img.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(source, 0, 0, width, height, null);
        g.dispose();
        return img;
    }
    
    public static Image scaleImage(Image source, int size) {
        int width = source.getWidth(null);
        int height = source.getHeight(null);
        double f = 0;
        f = (double) height / (double) width;
        width = (int) (size / f);
        height = size;
        
        return scaleImage(source, width, height);
    }
    
    @Override
    public Dimension getPreferredSize() {
        Dimension tailleEcran = new Dimension(Recherche.tailleRecheche.width-5, (Recherche.tailleRecheche.height-20)/5);
        return tailleEcran;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==jButtonDl) {
            ThreadDownload t = new ThreadDownload(musique);
            t.start();
        }
    }

    
    
}
