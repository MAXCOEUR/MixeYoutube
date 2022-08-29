/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MixeYoutube;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.json.JSONObject;

/**
 *
 * @author Maxen
 */
public class ApiRecherche {
    private static ArrayList<Musique> Liste = new ArrayList<>();

    public ApiRecherche() {
    }
    public static ArrayList<Musique> Recherche(){
        Liste.clear();
        for (int i = 0; i < 5; i++) {
            Liste.add(new Musique("", "", "", ""));
            
        }
        
        return Liste;
    }
    public static ArrayList<Musique> Recherche(String recherche){
        Liste.clear();
        recherche=recherche.replace(' ', '+');
        try {
            URL u = new URL("https://youtube.googleapis.com/youtube/v3/search?part=snippet&maxResults=5&q='"+recherche+"'&type=video&key=AIzaSyDQBIjI0kNzFnNqujvruMhZfPyhwxS1SG0");
            URLConnection conn = u.openConnection();
            conn.connect();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine())!=null) {
                sb.append(line+"\n");
            }
            br.close();
            JSONObject jsonObj = new JSONObject(sb.toString());
            for (int i = 0; i < 5; i++) {
                Liste.add(new Musique(jsonObj.getJSONArray("items").getJSONObject(i).getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("medium").get("url").toString(), jsonObj.getJSONArray("items").getJSONObject(i).getJSONObject("snippet").get("title").toString(), jsonObj.getJSONArray("items").getJSONObject(i).getJSONObject("snippet").get("channelTitle").toString(), jsonObj.getJSONArray("items").getJSONObject(i).getJSONObject("id").get("videoId").toString()));
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
                
        
        return Liste;
    }
}
