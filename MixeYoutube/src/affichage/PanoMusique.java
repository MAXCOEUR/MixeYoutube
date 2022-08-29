/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package affichage;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.util.ArrayList;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Maxen
 */
public class PanoMusique extends JApplet{
    
    private ArrayList<DownLoadMusique> listeMusique = new ArrayList<>();
    private JScrollPane scroll;
    private JPanel panoMusique= new JPanel();
    
    
    public PanoMusique(ArrayList<DownLoadMusique> listeMusique) throws HeadlessException {
        scroll = new JScrollPane(panoMusique);
        this.listeMusique=listeMusique;
        
        
        panoMusique.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        cont.gridx=0;
        cont.gridy=0;
        panoMusique.add(new JLabel("Musique : "),cont);
        
        for (int i = 0; i < listeMusique.size(); i++) {
            cont.gridx=0;
            cont.gridy=i+1;
            panoMusique.add(listeMusique.get(i), cont);
        }
//        panoMusique.setPreferredSize(new Dimension(getPreferredSize().width, MaFenetre.tailleFenetre.height));
        this.add(scroll);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Playliste.taillePlayliste.width-20, Playliste.taillePlayliste.height/3*2-20);
    }
}
