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
    public JButton retour = new JButton("Retour");
    public JButton actualiser = new JButton("Actualiser");
    public JButton ExplorateurFichier = new JButton("Fichier");
    public JButton ouvrir = new JButton("Ouvrir");
    public JButton download = new JButton("Download");
    
    private Dimension tailleFolder = new Dimension(150, 30);
    private Dimension tailleBout =new Dimension(100, 30);

    public PanoDir(ArrayList<JButton> listeDirectoryButton) throws HeadlessException {
        scroll = new JScrollPane(panoDir);
        this.listeDirectoryButton=listeDirectoryButton;
        
        retour.setPreferredSize(tailleBout);
        actualiser.setPreferredSize(tailleBout);
        ExplorateurFichier.setPreferredSize(tailleBout);
        ouvrir.setPreferredSize(tailleBout);
        download.setPreferredSize(tailleBout);
        for (JButton b : listeDirectoryButton) {
            b.setPreferredSize(tailleFolder);
            b.setBackground(Color.cyan);
        }
        
        
        panoDir.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        
        JPanel tmp2 = new JPanel();
        tmp2.setLayout(new GridBagLayout());
        
        cont.gridx=0;
        cont.gridy=0;
        cont.gridwidth=5;
        tmp2.add(new JLabel("Dossier : "+Playliste.path),cont);
        cont.gridx=0;
        cont.gridy=1;
        cont.gridwidth=1;
        retour.setBackground(Color.white);
        retour.setForeground(Color.black);
        tmp2.add(retour,cont);
        cont.gridx=1;
        actualiser.setBackground(Color.white);
        actualiser.setForeground(Color.black);
        tmp2.add(actualiser,cont);
        cont.gridx=2;
        ouvrir.setBackground(Color.white);
        ouvrir.setForeground(Color.black);
        tmp2.add(ouvrir,cont);
        cont.gridx=3;
        ExplorateurFichier.setBackground(Color.white);
        ExplorateurFichier.setForeground(Color.black);
        tmp2.add(ExplorateurFichier,cont);
        cont.gridx=4;
        download.setBackground(Color.white);
        download.setForeground(Color.black);
        tmp2.add(download,cont);
        
        
        
        
        
        JPanel tmp = new JPanel();
        tmp.setLayout(new GridBagLayout());
        for (int i = 0; i < listeDirectoryButton.size(); i++) {
            
            cont.gridx=(int) (i%((getPreferredSize().width*0.95)/tailleFolder.width));
            cont.gridy=(int) (i/((getPreferredSize().width*0.95)/tailleFolder.width));
            tmp.add(listeDirectoryButton.get(i), cont);
        }
        cont.gridx=0;
        cont.gridy=0;
        cont.gridwidth=1;
        panoDir.add(tmp2,cont);
        cont.gridx=0;
        cont.gridy=1;
        cont.gridwidth=1;
        panoDir.add(tmp,cont);
        this.add(scroll);
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Playliste.taillePlayliste.width-20,  Playliste.taillePlayliste.height/3-20);
    }
}
