/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MixeYoutube;

import affichage.DownLoadMusique;
import affichage.Playliste;
import static affichage.Playliste.path;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Maxen
 */
public class RechercheFolder extends JPanel {
    MultiMap<Integer, File> multimap = new MultiMap();   
    
    
    
    
    public MultiMap<Integer, File> rechercher(String s,File dep){
        
//        System.out.println(dep);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dep.toString()))) {
            for (Path file: stream) {
                File chemain=new File(file.toString());
                if (chemain.isDirectory()) {
                        rechercher(s,chemain);
                    }
                else{
                    if (file.getFileName().toString().endsWith(".mp3")) {
                    multimap.put(coefRecheche(chemain.getName(), s),chemain);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Playliste.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return multimap;
    }
    
    private int coefRecheche(String verifier, String chercher){
        int max=0;
        int tmp=0;
        verifier.replace("_", "");
        verifier.replace("-", "");
        verifier.replace(":", "");
        verifier.replace(" ", "");
        
        verifier.replace("0", "");
        verifier.replace("1", "");
        verifier.replace("2", "");
        verifier.replace("3", "");
        verifier.replace("4", "");
        verifier.replace("5", "");
        verifier.replace("6", "");
        verifier.replace("7", "");
        verifier.replace("8", "");
        verifier.replace("9", "");
        verifier = verifier.toLowerCase();
        chercher.replace("_", "");
        chercher.replace(" ", "");
        chercher.replace("-", "");
        chercher.replace(":", "");
        
        chercher.replace("0", "");
        chercher.replace("1", "");
        chercher.replace("2", "");
        chercher.replace("3", "");
        chercher.replace("4", "");
        chercher.replace("5", "");
        chercher.replace("6", "");
        chercher.replace("7", "");
        chercher.replace("8", "");
        chercher.replace("9", "");
        chercher = chercher.toLowerCase();
            for (int j = 0; j < verifier.length(); j++) {
                for (int i = 0; i < chercher.length(); i++) {
                    if (j+i<verifier.length()) {
                        if (chercher.charAt(i)==verifier.charAt(j+i)) {
                        tmp++;
                        }
                    }
                    
                }
                if (tmp>max) {
                    max=tmp;
                }
                tmp=0;
            }
//        }
        return max;
    }
    
    public static void main(String[] args) {
        RechercheFolder r = new RechercheFolder();
        MultiMap<Integer, File> multimap = r.rechercher("acdc warmachine",new File(".\\Playlist"));
        for (Integer lastName: multimap.keySet())   
        {  
        //printing key and values  
        System.out.println(lastName + ": " + multimap.get(lastName));  
        }  
    }
}
