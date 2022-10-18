/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package affichage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Maxen
 */
public class Mixage extends JPanel implements ChangeListener , ActionListener{
    public ArrayList<LecteurMp3> mix= new ArrayList<>();
    static public Dimension tailleMixage;
    static public JSlider volumeMaster; 
    public JLabel Master= new JLabel("Master : ");
    public JLabel Switch =new JLabel("Switch : ");
    int nbrPiste;
    
    static public JComboBox<Integer> Choix1= new JComboBox<>();
    static public JComboBox<Integer> Choix2= new JComboBox<>();
    
    static public JSlider volumeChangement; 
    
    
    public Mixage() {
//        this.setBackground(new Color(2, 2, 22));
        this.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        tailleMixage=getPreferredSize();
        nbrPiste=(getPreferredSize().height-140)/100;
        
        volumeMaster = new JSlider(0, 10000, 10000);
        volumeMaster.setPreferredSize(new Dimension((int)((getPreferredSize().width-Master.getPreferredSize().width-Switch.getPreferredSize().width-Choix1.getPreferredSize().width-Choix2.getPreferredSize().width)*0.45), 40));
        volumeMaster.addChangeListener(this);
        
        volumeChangement = new JSlider(0, 20000, 10000);
        volumeChangement.setPreferredSize(new Dimension((int)((getPreferredSize().width-Master.getPreferredSize().width-Switch.getPreferredSize().width-Choix1.getPreferredSize().width-Choix2.getPreferredSize().width)*0.45), 40));
        volumeChangement.addChangeListener(this);
        volumeChangement.setPaintTrack(true); 
        volumeChangement.setPaintTicks(true);
        volumeChangement.setMajorTickSpacing(100000); 
        volumeChangement.setMinorTickSpacing(10000); 
        
        Choix1.addActionListener(this);
        Choix2.addActionListener(this);
        
        cont.gridx=0;
        cont.gridy=0;
        this.add(Master,cont);
        cont.gridx=1;
        cont.gridy=0;
        this.add(volumeMaster,cont);
        
        cont.gridx=2;
        cont.gridy=0;
        this.add(Switch,cont);
        cont.gridx=3;
        cont.gridy=0;
        this.add(Choix1,cont);
        cont.gridx=4;
        cont.gridy=0;
        this.add(volumeChangement,cont);
        cont.gridx=5;
        cont.gridy=0;
        this.add(Choix2,cont);
        
        for (int i = 0; i < nbrPiste; i++) {
            Choix1.addItem(i+1);
            Choix2.addItem(i+1);
            mix.add(new LecteurMp3(i+1));
            cont.gridx=0;
            cont.gridy=i*2+1;
            cont.gridwidth=7;
            this.add(mix.get(i), cont);
        }
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension((int) (MaFenetre.tailleFenetre.width/2*0.99), (int) (MaFenetre.tailleFenetre.height*0.98));
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource()==volumeMaster) {
            for (int i = 0; i < mix.size(); i++) {
                mix.get(i).ChangeMaster(volumeMaster.getValue()/10000.0);
            }
        }
        if (e.getSource()==volumeChangement) {
            for (int i = 0; i < mix.size(); i++) {
                if (i==Choix1.getSelectedIndex()) {
                    if (volumeChangement.getValue()<=10000) {
                        mix.get(i).ChangeChange(1.0);
                    }
                    else{
                        mix.get(i).ChangeChange((Math.abs(volumeChangement.getValue()-20000))/10000.0);
                    }
                    
                }
                else if (i==Choix2.getSelectedIndex()) {
                    if (volumeChangement.getValue()>=10000) {
                        mix.get(i).ChangeChange(1.0);
                    }
                    else{
                        mix.get(i).ChangeChange(volumeChangement.getValue()/10000.0);
                    }
                    
                }
                else {
                    mix.get(i).ChangeChange(1.0);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==Choix1) {
            stateChanged(new ChangeEvent(volumeChangement));
        }
        if (e.getSource()==Choix2) {
            stateChanged(new ChangeEvent(volumeChangement));
        }
    }
    
    
}
