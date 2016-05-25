package edu.upc.essi.gps.ecommerce.repositoris;


import edu.upc.essi.gps.ecommerce.domain.descomptes.Descompte;
import edu.upc.essi.gps.ecommerce.domain.descomptes.DescompteImport;
import edu.upc.essi.gps.ecommerce.domain.descomptes.DescomptePercentatge;

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

}
