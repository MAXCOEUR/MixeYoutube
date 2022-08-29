/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package affichage;

import affichage.Recherche;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Maxen
 */
public class DownLoadMusique extends JPanel {
    public JLabel jTitre;
    public JLabel bpm;
    

    public DownLoadMusique(String T,Float bpm) {
        this.jTitre = new JLabel(T);
        if (bpm==null) {
            this.bpm = new JLabel("");
        }
        else{
            this.bpm = new JLabel(bpm+" BPM");
        }
        
        
        jTitre= new JLabel(T);
        
        
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);
        this.jTitre.setForeground(Color.BLACK);
        this.bpm.setForeground(Color.BLACK);
        this.bpm.setBorder(new LineBorder(Color.BLACK));
        GridBagConstraints cont = new GridBagConstraints();
        
        cont.gridx=0;
        cont.gridy=0;
        jTitre.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        this.add(jTitre, cont);
        cont.gridx=1;
        cont.gridy=0;
//        this.bpm.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        this.add(this.bpm, cont);
        
        
        this.setBorder(new LineBorder(Color.BLACK));
        
        
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Recherche.tailleRecheche.width-20, 30);
    }

    
    
    
}
