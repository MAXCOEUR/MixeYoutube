/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MixeYoutube;

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
    
    private JProgressBar songProgressBar;
    private long ls;
    private Chronometre time;

    public DurationBar(JProgressBar songProgressBar, long ls,Chronometre time) {
        this.songProgressBar = songProgressBar;
        this.ls=ls;
        this.time=time;
        
    }
    
    
    
   public void run(){
       songProgressBar.setMinimum(0);
       songProgressBar.setMaximum((int) ls);
       Date tt= new Date();
       
       
       while (time.getTime()<=ls) {
           try {
               long date = tt.getTime();
               
               songProgressBar.setValue((int) time.getTime());
               
               long datet2=tt.getTime();
               Thread.sleep(200-(datet2-date));
               
           } catch (InterruptedException ex) {
               Logger.getLogger(DurationBar.class.getName()).log(Level.SEVERE, null, ex);
           }
            
       }
        
       
   }
}
