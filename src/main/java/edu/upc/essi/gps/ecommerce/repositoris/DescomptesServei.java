package edu.upc.essi.gps.ecommerce.repositoris;


import com.sun.corba.se.spi.orb.ParserData;
import edu.upc.essi.gps.ecommerce.domain.descomptes.Descompte;
import edu.upc.essi.gps.ecommerce.domain.descomptes.DescompteImport;
import edu.upc.essi.gps.ecommerce.domain.descomptes.DescomptePercentatge;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DescomptesServei {

    private DescomptesRepositori descomptesRepositori;

    public DescomptesServei() {
        this.descomptesRepositori = new DescomptesRepositori();
    }

    public Descompte nouDescompte(double descompte, Calendar dataCaducitat, String tipus,  List<String> args){ //ID auto
        int id = descomptesRepositori.newId();

        return nouDescompte(id,descompte,dataCaducitat,tipus,args);

    }

    public Descompte nouDescompte(int id,double descompte, Calendar dataCaducitat, String tipus,  List<String> args){ //ID manual

        Descompte result = null;

        switch(tipus) {
            case "Import":
                double impMinim = Double.parseDouble(args.get(0));
                result = new DescompteImport(id,descompte,dataCaducitat,impMinim);

            case "Percentatge":
                result = new DescomptePercentatge(id,descompte,dataCaducitat);
        }

        descomptesRepositori.checkInsert(result);
        guardarDescompte(result);
        return result;

    }

    public void guardarDescompte(Descompte des){
        descomptesRepositori.checkInsert(des);
        descomptesRepositori.insert(des);
    }

    public List<Descompte> llistarDescomptes(){
        return descomptesRepositori.list();
    }

    public Descompte trobaPerCodi(int id) {
        return descomptesRepositori.trobarDescompte(id);
    }

    public List<String> imprimirLlistaDescomptes() {

        List<Descompte> listDesc = llistarDescomptes();
        List<String> listaImpr = new ArrayList<>();
        SimpleDateFormat dF = new SimpleDateFormat("dd/MM/yyyy");

        String info = "";

        for(Descompte d: listDesc) {

            if(d instanceof DescompteImport){
                info = impressioDescompteImport(d,dF);
            }
            else if(d instanceof  DescomptePercentatge){
                info = impressioDescomptePercentage(d,dF);
            }

            listaImpr.add(info);
        }

        return listaImpr;
    }

    /*** FUNCION AUX PER A CADA TIPUS DE DESCOMPTES **/

    public String impressioDescompteImport(Descompte d, SimpleDateFormat dF) {

        DescompteImport dAux = (DescompteImport) d;

        String linia;
        linia = "Descompte de "+ dAux.getDescompte();
        linia += "€ | Import minim de " + dAux.getImportMinim();
        linia += "€ | Caduca el " + dF.format(dAux.getDataCaducitat().getTime());
        linia += " | Codi de Barres " + dAux.getId();
        return linia;

    }

    public String impressioDescomptePercentage(Descompte d, SimpleDateFormat dF) {

        DescomptePercentatge dAux = (DescomptePercentatge) d;

        String linia;
        linia = "Descompte de "+ dAux.getDescompte();
        linia += "€ | Caduca el " + dF.format(dAux.getDataCaducitat().getTime());
        linia += " | Codi de Barres " + dAux.getId();
        return linia;

    }
}
