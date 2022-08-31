/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package affichage;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
/**
 *
 * @author Maxen
 */
public class Playliste extends JPanel implements ActionListener{
    
    static public Dimension taillePlayliste;
    
    public ArrayList<DownLoadMusique> listeMusique = new ArrayList<>();
    public ArrayList<File> listeFile  = new ArrayList<>();
    
    private JButton retour;
    private JButton actualiser;
    private JButton ExplorateurFichier;
    private JButton ouvrir;
    private JButton download;
    private ArrayList<JButton> listeDirectoryButton  = new ArrayList<>();
    
    static public String path=".\\PlayList";
    static public String pathOriginal=".\\PlayList";
    
    private MaFenetre fen;

    public Playliste(MaFenetre fen) {
        this.fen=fen;
        Actualiser(path);
    }
    public Playliste(MaFenetre fen,String file) {
        this.fen=fen;
        Actualiser(file);
    }
    public void Actualiser(String path){
        listeMusique.clear();
        listeFile.clear();
        listeDirectoryButton.clear();
        this.removeAll();
        
        taillePlayliste=new Dimension(MaFenetre.tailleFenetre.width/2-10, MaFenetre.tailleFenetre.height-30);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(path))) {
            for (Path file: stream) {
                File dossier=new File(file.toString());
                if (file.getFileName().toString().endsWith(".mp3")) {
                    
                    listeFile.add(new File(path+'\\'+file.getFileName().toString()));
                    File chemain = new File(file.toString()+".bpm");
                    if (chemain.exists()) {
                        Scanner sc = new Scanner(chemain);
                        sc.useDelimiter(";");
                        try {
                            sc.next();
                            sc.next();
                            listeMusique.add(new DownLoadMusique(file.getFileName().toString(),Float.valueOf(sc.next())));
                        } catch (Exception e) {
                        }
                        
                    }
                    else{
                        listeMusique.add(new DownLoadMusique(file.getFileName().toString(),null));
                    }
                }
                else if (dossier.isDirectory()) {
                        listeDirectoryButton.add(new JButton(file.getFileName().toString()));
                    }
{
                    
                }
                
            }
        } catch (IOException ex) {
            Logger.getLogger(Playliste.class.getName()).log(Level.SEVERE, null, ex);
        }
        GridBagConstraints cont = new GridBagConstraints();
        PanoMusique panoMusique = new PanoMusique(listeMusique);
        
        PanoDir panoDir = new PanoDir(listeDirectoryButton);
        retour=panoDir.retour;
        actualiser=panoDir.actualiser;
        ExplorateurFichier=panoDir.ExplorateurFichier;
        ouvrir=panoDir.ouvrir;
        download=panoDir.download;
        retour.addActionListener(this);
        actualiser.addActionListener(this);
        ExplorateurFichier.addActionListener(this);
        ouvrir.addActionListener(this);
        download.addActionListener(this);
//        JPanel panoMusique= new JPanel();
//        panoMusique.setBorder(new LineBorder(Color.BLACK));
//        panoMusique.setLayout(new GridBagLayout());
        
//        cont.gridx=0;
//        cont.gridy=0;
//        panoMusique.add(new JLabel("Musique : "),cont);
//        
//        for (int i = 0; i < listeMusique.size(); i++) {
//            cont.gridx=0;
//            cont.gridy=i+1;
//            panoMusique.add(listeMusique.get(i), cont);
//        }
//        panoMusique.setPreferredSize(new Dimension(getPreferredSize().width-10, 20+(listeMusique.size()+1)*20));
        
//        JPanel panoDir= new JPanel();
//        panoDir.setBorder(new LineBorder(Color.BLACK));
//        
//        panoDir.setLayout(new GridBagLayout());
//        cont.gridx=0;
//        cont.gridy=0;
//        panoDir.add(new JLabel("Dossier : "),cont);
//        cont.gridx=1;
//        cont.gridy=0;
//        panoDir.add(new JLabel(path+" "),cont);
//        cont.gridx=2;
//        panoDir.add(retour,cont);
//        retour.addActionListener(this);
//        
//        
//        for (int i = 0; i <= listeDirectoryButton.size()/10; i++) {
//            for (int j = 0; j < listeDirectoryButton.size()%10; j++) {
//                cont.gridx=j;
//                cont.gridy=i+1;
//                panoDir.add(listeDirectoryButton.get(i), cont);
//            }
//        }
//        panoDir.setPreferredSize(new Dimension(getPreferredSize().width-10, 30+(listeDirectoryButton.size()/10+1)*30));
        
        this.setLayout(new GridBagLayout());
        cont.gridx=0;
        cont.gridy=0;
        this.add(panoDir,cont);
        
        cont.gridx=0;
        cont.gridy=1; 
        this.add(panoMusique,cont);
        
        
        for (int i = 0; i < listeDirectoryButton.size(); i++) {
            listeDirectoryButton.get(i).addActionListener(this);
        }
        
        this.updateUI();
        fen.button();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        if (e.getSource()==actualiser) {
            Actualiser(path);
        }
        if (e.getSource()==ExplorateurFichier) {
            File fils =new File(path);
                try {
                    Desktop.getDesktop().open(new File(fils.getAbsolutePath()));
                } catch (IOException ex) {
                    Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        if (e.getSource()==download) {
            path =".\\Playlist\\Download";
            Actualiser(path);
        }
        if (e.getSource()==ouvrir) {
            JFileChooser jfc = new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int ret = jfc.showOpenDialog(null); // ne te rend la main que si tu ferme
            if(ret == JFileChooser.APPROVE_OPTION) { // validation
                path = jfc.getSelectedFile().toString();
                pathOriginal =jfc.getSelectedFile().toString();
                Actualiser(path);
            }
        }
        if (e.getSource()==retour) {
            File tmp = new File(path);
            
            if (tmp.getParent()!=null&&tmp.getParent().startsWith(pathOriginal)) {
                path = tmp.getParent();
                Actualiser(path);
                
            }
            else{
                path=pathOriginal;
                Actualiser(pathOriginal);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                ;
            }
        }
        for (int i = 0; i < listeDirectoryButton.size(); i++) {
            if (e.getSource()==listeDirectoryButton.get(i)) {
                String more = listeDirectoryButton.get(i).getText();
                path = path+'\\'+more;
                Actualiser(path);
            }
        }
        
    }
    @Override
    public Dimension getPreferredSize() {
        
        return taillePlayliste;
    }
    
    
}
