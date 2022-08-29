/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MixeYoutube;

/**
 *
 * @author Maxen
 */
public class Heure {
    public int heure;
    public int minute;
    public int seconde;

    public Heure(int heure, int minute, int seconde) {
        this.heure = heure;
        this.minute = minute;
        this.seconde = seconde;
    }
    
    public int DiffHeur(Heure h1){
        if (heure==23&&h1.heure==0) {
            
        }
        else if (heure==0&&h1.heure==23) {
            
        }
        else return Math.abs(ToSeconde()-h1.ToSeconde());
        return 0;
    }
    
    public int ToSeconde(){
        return heure*3600+minute*60+seconde;
    }
    
}
