/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package affichage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Maxen
 */
public class Mixage extends JPanel{
    public ArrayList<LecteurMp3> mix= new ArrayList<>();
    static public Dimension tailleMixage;
    
    
    public Mixage() {
//        this.setBackground(new Color(2, 2, 22));
        this.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        tailleMixage=getPreferredSize();
        
        for (int i = 0; i < (getPreferredSize().height-100)/100; i++) {
            mix.add(new LecteurMp3(i+1));
            cont.gridx=0;
            cont.gridy=i*2+1;
            JLabel tmp = new JLabel(" ");
            tmp.setBackground(new Color(2, 2, 22));
            tmp.setPreferredSize(new Dimension(getPreferredSize().width, 100/(((getPreferredSize().height-100)/100)+1)));
            this.add(tmp,cont);
            cont.gridx=0;
            cont.gridy=i*2;
//            mix.get(i).setBorder(BorderFactory.createEmptyBorder(10, 2,10, 2));
            this.add(mix.get(i), cont);
        }
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension((int) (MaFenetre.tailleFenetre.width/2*0.99), (int) (MaFenetre.tailleFenetre.height*0.98));
    }
    
    
}
