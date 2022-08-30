/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package affichage;

import MixeYoutube.ThreadDownloadLink;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Maxen
 */
public class YoutubeDownloadLink extends JPanel implements ActionListener,FocusListener{
    
    static public Dimension tailleYoutubeDownloadLink;
    public JTextField Jtexte = new JTextField();
    private JButton jBout = new JButton("Download");
    private JLabel tel = new JLabel("");

    public YoutubeDownloadLink() {
        
        tailleYoutubeDownloadLink=new Dimension(MaFenetre.tailleFenetre.width/2-10, MaFenetre.tailleFenetre.height-30);
        
        Jtexte.setPreferredSize(new Dimension(getPreferredSize().width-jBout.getPreferredSize().width-100, jBout.getPreferredSize().height));
        this.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        cont.gridx=0;
        cont.gridy=0;
        this.add(Jtexte,cont);
        
        cont.gridx=1;
        cont.gridy=0;
        this.add(jBout,cont);
        
        cont.gridx=0;
        cont.gridy=1;
        cont.gridwidth=2;
        this.add(tel,cont);
        jBout.addActionListener(this);
        Jtexte.addFocusListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==jBout) {
            tel.setText("telechargement en cours");
            ThreadDownloadLink tmp = new ThreadDownloadLink(Jtexte.getText(),tel);
            tmp.start();
            Jtexte.setText("");
        }
    }
    
    @Override
    public Dimension getPreferredSize() {
        
        return tailleYoutubeDownloadLink;
    }

    @Override
    public void focusGained(FocusEvent e) {
        Jtexte.setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {
        ;
    }
}
