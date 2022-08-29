/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MixeYoutube;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maxen
 */
public class Chronometre extends Thread{
    double time=0;
    boolean isPlay=false;

    public Chronometre() {
    }
    
   public void run(){
       Date tt= new Date();
       
       while (true) {
           try {
               long date = tt.getTime();
               if (isPlay) {
                   time+=0.2;
               }
               long datet2=tt.getTime();
               Thread.sleep(200-(datet2-date));
               
           } catch (InterruptedException ex) {
               Logger.getLogger(Chronometre.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
        
       
   }
   public void play(){
       isPlay=true;
   }
   public void pause(){
       isPlay=false;
   }
   public void reset(){
       isPlay=false;
       time=0;
   }
   public double getTime(){
       return time;
   }

    public void setTime(double time) {
        this.time = time;
    }
   
   
}
