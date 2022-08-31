/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package affichage;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Maxen
 */
public class JDialogListe extends JDialog implements ActionListener{
    private ArrayList<DownLoadMusique> listeMusiqueAffiche = new ArrayList<>();
    private ArrayList<JButton> listeButtonUP = new ArrayList<>();
    private ArrayList<JButton> listeButtonDown = new ArrayList<>();
    private ArrayList<JButton> listeButtonSupp = new ArrayList<>();
    
    private ArrayList<File> liste;
    
    private JScrollPane scroll;
    private JPanel panoMusique= new JPanel();

    public JDialogListe(ArrayList<File> l) {
        scroll = new JScrollPane(panoMusique);
        liste=l;
        actualiser();
        this.setTitle("Liste");
        this.add(scroll);
        this.pack();
        
    }
    
    public void actualiser(){
        panoMusique.removeAll();
        listeButtonUP.clear();
        listeButtonDown.clear();
        listeButtonSupp.clear();
        listeMusiqueAffiche.clear();
        if (!liste.isEmpty()) {
            for (File f:liste) {
                File t = new File(f.toString()+".bpm");
                if (t.exists()) {
                    try {
                        Scanner sc = new Scanner(t);
                        sc.useDelimiter(";");
                        sc.next();
                        sc.next();
                        listeMusiqueAffiche.add(new DownLoadMusique(f.getName(), Float.valueOf(sc.next())));
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(JDialogListe.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    listeMusiqueAffiche.add(new DownLoadMusique(f.getName(), null));
                }
                listeButtonUP.add(new JButton("UP"));
                listeButtonDown.add(new JButton("Down"));
                listeButtonSupp.add(new JButton("Supprimer"));
            }

            GridBagConstraints cont = new GridBagConstraints();
            panoMusique.setLayout(new GridBagLayout());

            for (int i = 0; i < liste.size(); i++) {
                cont.gridx=0;
                cont.gridy=i;
                panoMusique.add(listeMusiqueAffiche.get(i),cont);
                cont.gridx=1;
                cont.gridy=i;
                panoMusique.add(listeButtonUP.get(i),cont);
                cont.gridx=2;
                cont.gridy=i;
                panoMusique.add(listeButtonDown.get(i),cont);
                cont.gridx=3;
                cont.gridy=i;
                panoMusique.add(listeButtonSupp.get(i),cont);

                listeButtonUP.get(i).setPreferredSize(new Dimension(70, 30));
                listeButtonDown.get(i).setPreferredSize(new Dimension(70, 30));
                listeButtonSupp.get(i).setPreferredSize(new Dimension(70, 30));
                listeButtonUP.get(i).addActionListener(this);
                listeButtonDown.get(i).addActionListener(this);
                listeButtonSupp.get(i).addActionListener(this);
            }


            listeButtonDown.get(listeButtonDown.size()-1).setEnabled(false);
            listeButtonUP.get(0).setEnabled(false);
        }
        panoMusique.updateUI();
        
    }
    
    public void showDialog(){ 
        this.setVisible(true); //on ouvre la fenêtre
        return;
    } 

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < listeButtonUP.size(); i++) {
            if (e.getSource() == listeButtonUP.get(i)) {
                File tmp = liste.remove(i);
                liste.add(i-1, tmp);
                actualiser();
            }
            else if (e.getSource() == listeButtonDown.get(i)) {
                File tmp = liste.remove(i);
                liste.add(i+1, tmp);
                actualiser();
            }
            else if (e.getSource()==listeButtonSupp.get(i)) {
                liste.remove(i);
                actualiser();
                
            }
        }
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Playliste.taillePlayliste.width+210, Playliste.taillePlayliste.height/3*2-20);
    }
    
    
}
