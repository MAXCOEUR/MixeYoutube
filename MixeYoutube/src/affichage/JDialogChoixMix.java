/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package affichage;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author Maxen
 */
public class JDialogChoixMix extends JDialog implements ActionListener{
    private JComboBox<String> Mix = new JComboBox();
    JLabel Choix = new JLabel("Choix de la piste : ");
    JButton valider = new JButton("Valider");
    public ArrayList<LecteurMp3> Piste;
    int i;

    public JDialogChoixMix(MaFenetre fen,ArrayList<LecteurMp3> m) {
        super(fen,true);
        this.Piste=m;
        for (int i = 1; i <= Piste.size(); i++) {
            Mix.addItem(Integer.toString(i));
        }
        
        int u;
        for (u = 0; u < Piste.size(); u++) {
            if (!Piste.get(u).isIsPlay()) {
                Mix.setSelectedIndex(u);
                break;
            }
        }
        if (u==Piste.size()) {
            Mix.setSelectedIndex(0);
        }
        
        
        this.setTitle("Choix Piste");
        
        this.setLayout(new GridBagLayout());

        GridBagConstraints gc=new GridBagConstraints();
        gc.gridx=0;
        gc.gridy=0;
        this.add(Choix ,gc);
        gc.gridx=1;
        gc.gridy=0;
        this.add(Mix ,gc);
        gc.gridx=0;
        gc.gridy=1;
        valider.setBackground(Color.GREEN);
        this.add(valider ,gc);
        
        
        this.pack();
        
        valider.addActionListener(this);
    }
    
    public int showDialog(){ 
        this.setVisible(true); //on ouvre la fenêtre
        return i;
    } 

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==valider) {
            i = Mix.getSelectedIndex();
            this.setVisible(false);
        }
    }
    
    
}
