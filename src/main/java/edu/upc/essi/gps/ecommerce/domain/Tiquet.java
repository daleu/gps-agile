package edu.upc.essi.gps.ecommerce.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Nailek on 05/05/2016.
 */
public class Tiquet {
    private List<String> liniesTiquet;
    private int numTiquet;
    private Calendar calendari;
    public Tiquet(int num) {
        liniesTiquet = new ArrayList<>();
        numTiquet = num;
        calendari = Calendar.getInstance();
    }

    public List<String> getTiquet() {
        return liniesTiquet;
    }

    public int getNum() {
        return numTiquet;
    }

    public String getLinia(int num) {
        return liniesTiquet.get(num);
    }
    public void addLinia(String linia){
        liniesTiquet.add(linia);
    }

    public String getData() {
        return ""+calendari.get(Calendar.DATE) + "/" + calendari.get(Calendar.DAY_OF_MONTH) + "/" + calendari.get(Calendar.YEAR);
    }
    public String getHora() {
        return "" + calendari.get(Calendar.HOUR) + ":" + calendari.get(Calendar.MINUTE);
    }

    public String getDataIHora() {
        return getData() + " " + getHora();
    }

    public Boolean existsLinies(List<String> s) {
        int i = 0;
        for(String lin: liniesTiquet) {
            if(lin == s.get(0)){
                if(s.size() == (i +1)) return true;
                else ++i;
            }
        }
        return false;
    }
}
