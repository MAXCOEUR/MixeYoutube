/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package affichage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.util.ArrayList;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

/**
 *
 * @author Maxen
 */
public class PanoDir extends JApplet{
    public ArrayList<JButton> listeDirectoryButton = new ArrayList<>();
    private JScrollPane scroll;
    private JPanel panoDir= new JPanel();
    public JButton retour = new JButton("retour");
    public JButton actualiser = new JButton("actualiser");
    public JButton ouvrir = new JButton("Ouvrir");
    
    private Dimension tailleFolder = new Dimension(150, 30);

    public PanoDir(ArrayList<JButton> listeDirectoryButton) throws HeadlessException {
        scroll = new JScrollPane(panoDir);
        this.listeDirectoryButton=listeDirectoryButton;
        
        retour.setPreferredSize(tailleFolder);
        actualiser.setPreferredSize(tailleFolder);
        ouvrir.setPreferredSize(tailleFolder);
        for (JButton b : listeDirectoryButton) {
            b.setPreferredSize(tailleFolder);
            b.setBackground(Color.cyan);
        }
        
        panoDir.setBorder(new LineBorder(Color.BLACK));
        
        panoDir.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        cont.gridx=0;
        cont.gridy=0;
        cont.gridwidth=3;
        panoDir.add(new JLabel("Dossier : "+Playliste.path),cont);
        cont.gridx=0;
        cont.gridy=1;
        cont.gridwidth=1;
        retour.setBackground(Color.white);
        retour.setForeground(Color.black);
        panoDir.add(retour,cont);
        cont.gridx=1;
        actualiser.setBackground(Color.white);
        actualiser.setForeground(Color.black);
        panoDir.add(actualiser,cont);
        cont.gridx=2;
        ouvrir.setBackground(Color.white);
        ouvrir.setForeground(Color.black);
        panoDir.add(ouvrir,cont);
        
        JPanel tmp = new JPanel();
        tmp.setLayout(new GridBagLayout());
//        System.out.println((getPreferredSize().width-10)/tailleFolder.width);
        for (int i = 0; i < listeDirectoryButton.size(); i++) {
            
            cont.gridx=i%((getPreferredSize().width-10)/tailleFolder.width);
            cont.gridy=i/((getPreferredSize().width-10)/tailleFolder.width);
            tmp.add(listeDirectoryButton.get(i), cont);
        }
        
        cont.gridx=0;
        cont.gridy=2;
        cont.gridwidth=3;
        panoDir.add(tmp,cont);
        this.add(scroll);
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Playliste.taillePlayliste.width-20,  Playliste.taillePlayliste.height/3-20);
    }
}
