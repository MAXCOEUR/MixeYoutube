/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MixeYoutube;

import affichage.ZonneRecherche;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.jaudiotagger.Test;

/**
 *
 * @author Maxen
 */
public class ThreadBPM extends Thread{
    File link;
    Boolean isExiste=false;
    JLabel bpmAffichage;

    public ThreadBPM(File link,JLabel jj) {
        this.link = link;
        bpmAffichage=jj;
    }
    
    public void run(){
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(link.getParentFile().toString()))) {
		    for (Path file: stream) {
                        String s = file.getFileName().toString();
//                        System.out.println(s+" "+link.getName()+".bpm");
                        if (s.equals(link.getName()+".bpm")) {
                            isExiste=true;
//                            System.out.println(s);
                        }
                    }
        } catch (IOException ex) {
            Logger.getLogger(ThreadBPM.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!isExiste) {
            try {
                
                String command = "java -jar .\\lib\\TrackAnalyzer.jar \""+link.toString()+"\" -w -o \""+link.toString()+"\".bpm";
                Runtime r = Runtime.getRuntime();
                Process p = r.exec(command);
                int returnCode = p.waitFor();
//                System.out.println("aled");
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadBPM.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ThreadBPM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        try {
            File fichier = new File(link.toString()+".bpm");
            Scanner sc = new Scanner(fichier);
            sc.useDelimiter(";");
            sc.next();
            sc.next();
            bpmAffichage.setText(Float.valueOf(sc.next())+" BPM");
                
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ThreadBPM.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
}
