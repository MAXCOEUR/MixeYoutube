/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package affichage;

import MixeYoutube.ApiRecherche;
import MixeYoutube.Musique;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author Maxen
 */
public class Recherche extends JPanel implements ActionListener, FocusListener, KeyListener{
    private JTextField jTexteFieldRecherche= new JTextField("Titre a rechercher");
    private JButton jButtonRecherche= new JButton("Rechercher");
    
    private ArrayList<ZonneRecherche> resulat = new ArrayList<>();
    private ArrayList<Musique> Liste = new ArrayList<>();
    
    static public Dimension tailleRecheche;

    public Recherche() {
        tailleRecheche=new Dimension(MaFenetre.tailleFenetre.width/2-20, MaFenetre.tailleFenetre.height-70);
        
        resulat.clear();
        Liste = ApiRecherche.Recherche();
        
         for (Musique m : Liste) {
            resulat.add(new ZonneRecherche(m));
        }
        
        afficheResultat();
        this.updateUI();
        
        
        jTexteFieldRecherche.addFocusListener(this);
        jButtonRecherche.addActionListener(this);
        jTexteFieldRecherche.addKeyListener(this);
        
    }
    
    private void afficheResultat(){
        this.removeAll();
        
        jTexteFieldRecherche.setPreferredSize(new Dimension(getPreferredSize().width/8*7-10, 20));
        jButtonRecherche.setPreferredSize(new Dimension(getPreferredSize().width/8-10, 20));
        
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        
        cont.gridx=0;
        cont.gridy=0;
        //cont.fill=GridBagConstraints.BOTH;
        
        this.add(jTexteFieldRecherche, cont);
        
        
        cont.gridx=1;
        cont.gridy=0;
        this.add(jButtonRecherche, cont);
 
        
//        cont.gridwidth=2;
//        for (int i = 0; i < 10; i++) {
//            
//            cont.gridx=0;
//            cont.gridy=i+1;
//            
//            this.add(new JLabel("re bonjour"), cont);
//        }
        
        
        cont.gridwidth=2;
        for (int i = 0; i < resulat.size(); i++) {
            
            cont.gridx=0;
            cont.gridy=i+1;
            
            this.add(resulat.get(i), cont);
        }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==jButtonRecherche) {
            
            resulat.clear();
            Liste = ApiRecherche.Recherche(jTexteFieldRecherche.getText());
            for (Musique m : Liste) {
                resulat.add(new ZonneRecherche(m));
            }
            afficheResultat();
            this.updateUI();
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource()==jTexteFieldRecherche) {
            jTexteFieldRecherche.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        ;
    }
    
    
    @Override
    public Dimension getPreferredSize() {
        return tailleRecheche;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        ;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource()==jTexteFieldRecherche) {
            if (e.getKeyCode()==10) {
                jButtonRecherche.doClick();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        ;
    }
    
}
