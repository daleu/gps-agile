package edu.upc.essi.gps.ecommerce.domain.descomptes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by edu on 19/05/16.
 */
public class FactoriaDescomptes {

   /* private static HashMap<String, DescomptePercentatge> descomptesPercentatge;
    private static HashMap<String, DescompteImport> descomptesImport;
    private static int codiDeBarres = 100;

    public static void startFactory() {
        if (descomptesPercentatge == null) descomptesPercentatge = new HashMap<>();
        if (descomptesImport == null) descomptesImport = new HashMap<>();
    }

    public static void nouDescomptePerPercentatge(double descompte, Calendar dataCaducitat) {
        int aux = ++codiDeBarres;
        descomptesPercentatge.put(aux, new DescomptePercentatge(aux, descompte, dataCaducitat));
    }

    public static void nouDescomptePerImport(double descompte, String dataCaducitat, double importMinim) {
        SimpleDateFormat dF = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = dF.parse(dataCaducitat);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int aux = ++codiDeBarres;
        descomptesImport.put(aux, new DescompteImport(aux, descompte, calendar, importMinim));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<String> getLlistaDescomptes() {
        List<String> llistaDescomptes = new ArrayList<>();
        Set<String> keys = descomptesPercentatge.keySet();
        SimpleDateFormat dF = new SimpleDateFormat("dd/MM/yyyy");
        llistaDescomptes.add("Llista de Descomptes");
        for (String key : keys) {
            llistaDescomptes.add("Descompte de " + descomptesPercentatge.get(key).getDescompte()
                    + "% | Caduca el " + dF.format(descomptesPercentatge.get(key).getDataCaducitat().getTime())
                    + " | Codi de Barres " + key);
        }
        keys = descomptesImport.keySet();
        for (String key : keys) {
            llistaDescomptes.add("Descompte de " + descomptesImport.get(key).getDescompte()
                    + "€ | Import minim de " + descomptesImport.get(key).getImportMinim()
                    + "€ | Caduca el " + dF.format(descomptesImport.get(key).getDataCaducitat().getTime())
                    + " | Codi de Barres " + key);
        }
        return llistaDescomptes;
    }

    public static Descompte getDescompteImportByCodi(String codiDeBarres) {
        return descomptesImport.get(codiDeBarres);
    }

    public static Descompte getDescomptePercentatgeByCodi(String codiDeBarres) {
        return descomptesPercentatge.get(codiDeBarres);
    }
    */
}
