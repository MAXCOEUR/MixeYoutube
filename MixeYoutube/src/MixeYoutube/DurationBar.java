/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MixeYoutube;

import affichage.LecteurMp3;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;

/**
 *
 * @author Maxen
 */
public class DurationBar extends Thread{
    
    private LecteurMp3 lecteurMp3;

    public DurationBar(LecteurMp3 lecteurMp3) {
        this.lecteurMp3=lecteurMp3;
        
    }
    
    
    public void reset(LecteurMp3 lecteurMp3){
        this.lecteurMp3=lecteurMp3;
        lecteurMp3.songProgressBar.setMinimum(0);
        lecteurMp3.songProgressBar.setMaximum((int) ( lecteurMp3.ls-1));
        Date tt= new Date();
    }
   
   public void run(){
       lecteurMp3.songProgressBar.setMinimum(0);
       lecteurMp3.songProgressBar.setMaximum((int) ( lecteurMp3.ls-1));
       Date tt= new Date();
       
       
       while (true) {
           try {
               long date = tt.getTime();
               
               lecteurMp3.songProgressBar.setValue((int) lecteurMp3.time.getTime());
               
               if ( lecteurMp3.time.getTime()>lecteurMp3.ls-15) {
                   lecteurMp3.passMusique();
               }
              if ( lecteurMp3.time.getTime()>lecteurMp3.ls-1) {
                   lecteurMp3.pauseMusique();
               }
               
               long datet2=tt.getTime();
               Thread.sleep(200-(datet2-date));
               
           } catch (InterruptedException ex) {
               Logger.getLogger(DurationBar.class.getName()).log(Level.SEVERE, null, ex);
           }
            
       }
        
       
   }
}
