package edu.upc.essi.gps.ecommerce.domain.descomptes;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by edu on 19/05/16.
 */
public class FactoriaDescomptes {
    private static HashMap<Double, DescomptePercentatge> descomptesPercentatge;
    private static HashMap<Double, DescompteImport> descomptesImport;
    private static int id = 0;

    private FactoriaDescomptes() {
        id = 0;
    }

    public static void startFactory() {
        if (descomptesPercentatge == null) descomptesPercentatge = new HashMap<>();
        if (descomptesImport == null) descomptesPercentatge = new HashMap<>();
    }

    public static void nouDescomptePerPercentatge(double descompte, Calendar dataCaducitat) {
        descomptesPercentatge.put(descompte, new DescomptePercentatge(++id, descompte, dataCaducitat));
    }

    public static void nouDescomptePerImport(double descompte, Calendar dataCaducitat) {
        descomptesImport.put(descompte, new DescompteImport(++id, descompte, dataCaducitat));
    }

    public static Descompte getDescompteByPercentatge(double valor) {
        return descomptesPercentatge.get(valor);
    }

    public static List<String> getLlistaDescomptes() {
        List<String> llistaDescomptes = new ArrayList<>();
        Set<Double> keys = descomptesPercentatge.keySet();
        SimpleDateFormat dF = new SimpleDateFormat("dd/MM/yyyy");
        llistaDescomptes.add("Llista de Descomptes");
        for (Double key : keys) {
            llistaDescomptes.add(new String("Descompte de " + key
                    + "% | Caduca el " + dF.format(descomptesPercentatge.get(key).getDataCaducitat().getTime())));
        }
        return llistaDescomptes;
    }
}
