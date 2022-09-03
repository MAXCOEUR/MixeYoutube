/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MixeYoutube;

/**
 *
 * @author Maxen
 */
public class Chronometre {
    private long tempsDepart=0;
    private long tempPauseDep=0;
    private long duree=0;
    private boolean isPlay=false;

    public Chronometre() {
    }
   public void play(){
       tempsDepart= System.currentTimeMillis();
       isPlay=true;
   }
   public void pause(){
       tempPauseDep=System.currentTimeMillis();
       duree =duree+(tempPauseDep-tempsDepart);
       tempsDepart=tempPauseDep;
       isPlay=false;
   }
   public void reset(){
       duree=0;
   }
   public double getTime(){
       if (isPlay) {
            long tempMtn=System.currentTimeMillis();
            duree =duree+(tempMtn-tempsDepart);
            tempsDepart=tempMtn;
       }
       else{
           duree =duree+(tempPauseDep-tempsDepart);
       }
       return duree/1000.0;
       
   }

    public void setTime(double time) {
        this.duree = (long) (time*1000);
    }
   
   
}
