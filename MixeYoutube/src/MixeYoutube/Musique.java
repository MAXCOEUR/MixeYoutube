/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MixeYoutube;



/**
 *
 * @author Maxen
 */
public class Musique {
    private String jMiniature;
    private String jTitre;
    private String jAuteur;
    private String videoId;

    public Musique(String jMiniature, String jTitre, String jAuteur, String videoId) {
        this.jMiniature = jMiniature;
        this.jTitre = jTitre;
        this.jAuteur = jAuteur;
        this.videoId = videoId;
    }

    public Musique() {
    }

    public String getjMiniature() {
        return jMiniature;
    }

    public String getjTitre() {
        return jTitre;
    }

    public String getjAuteur() {
        return jAuteur;
    }

    public String getVideoId() {
        return videoId;
    }

    @Override
    public String toString() {
        return "Musique{" + "jMiniature=" + jMiniature + ", jTitre=" + jTitre + ", jAuteur=" + jAuteur + ", videoId=" + videoId + '}';
    }
    
    
}
