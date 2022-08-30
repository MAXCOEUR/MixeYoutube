/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MixeYoutube;

import affichage.ZonneRecherche;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Maxen
 */
public class ThreadDownloadLink extends Thread{
    private String musique;
    private JLabel tel;

    public ThreadDownloadLink(String musique,JLabel t) {
        this.musique = musique;
        tel = t;
    }
    
    public void run(){
        try {
//                System.out.println("https://www.youtube.com/watch?v="+musique.getVideoId());
                String command = "./lib/youtube-dl.exe -x --audio-format mp3 "+musique;
                Runtime r = Runtime.getRuntime();
                Process p = r.exec(command);
                int returnCode = p.waitFor();
                
                
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("./"))) {
		    for (Path file: stream) {
                        String s = file.getFileName().toString();
                        if (s.endsWith(".mp3")) {
                            File fichier = new File("./"+s+"");
                            File rep = new File("./PlayList/Download/");
                            File Playlist = new File("./PlayList/");
                            if (!Playlist.isDirectory()) {
                                Playlist.mkdir();
                            }
                            if (!rep.isDirectory()) {
                                rep.mkdir();
                            }
                            boolean resultat = fichier.renameTo(new File(rep, fichier.getName()));
                            tel.setText("Le telechargement est fini");
                            if (resultat)
                                {
                                System.out.println("Le fichier a été déplacé vers==> "+rep);
                            }else
                                System.out.println ("Impossible de déplacer ce fichier");
                        }
                    }
		    
		} catch (IOException | DirectoryIteratorException ex) {
		    System.err.println(ex);
		}
                
            } catch (InterruptedException ex) {
                Logger.getLogger(ZonneRecherche.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ZonneRecherche.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
