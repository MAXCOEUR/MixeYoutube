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
    int i;

    public JDialogChoixMix(MaFenetre fen,int j) {
        super(fen,true);
        for (int i = 1; i <= j; i++) {
            Mix.addItem(Integer.toString(i));
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
