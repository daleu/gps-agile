package edu.upc.essi.gps.ecommerce.domain.descomptes;

import edu.upc.essi.gps.ecommerce.domain.Venda;
import edu.upc.essi.gps.ecommerce.repositoris.DescomptesServei;

import java.util.ArrayList;
import java.util.List;

public class CtrlCalculDescomptes {

    private DescomptesServei descomptesServei = new DescomptesServei();


    public CtrlCalculDescomptes (){

    }

    public void calcularPreuDescomptes(List<DescompteImport> descomptes, Venda vendaActual) {

        if  (vendaActual.getNumDescomptes() == 0) {
            DescompteImport dEscollit = descomptes.get(0);
            double impMinim = 0;
            for (DescompteImport dAux : descomptes) {


                double impMinimAux = dAux.getImportMinim();
                if (vendaActual.getPreuTotal() - impMinimAux >= 0 && impMinimAux > impMinim) {
                    impMinim = impMinimAux;
                    dEscollit = dAux;
                }

            }
            vendaActual.afegirDescompte(dEscollit);
        }

    }


/*
        private List<Descompte> escollirMillorCombinacio(List<Descompte> listDescomp, double total) {

            List<List<Descompte>> combinacions = backtracking(listDescomp);
            double maxim = 0.0;
            List<Descompte> combEscollida = new ArrayList<>();
            for(List<Descompte> comb: combinacions) {
                double valor = calcularPreuDescomptes(comb,total);
                if (valor > maxim) {
                    maxim = valor;
                    combEscollida = comb;
                }
            }
            return combEscollida;
        }
*/

        /*double preuDescomptat = total;

        for(Descompte desc: combinacio){

            if (possibleAplicar(desc,preuDescomptat)) {
                preuDescomptat -= desc.calcularPreu(preuDescomptat);
            }
            else {
                return -1;
            }

        }


    }

    private boolean possibleAplicar(Descompte desc, double preuDescomptat) {

        double minim =

    }


    private static Double computeBestDiscountCombination(List<Discount> discounts, List<Product> products) {
        //TENIM: discounts(List<Discount>), products(List<Product>)
        //PRE: No hi ha elements repetits a "discounts"

        //CAL: generar totes les permutacions en l'ordre d'aplicació dels descomptes i substracció de productes utilitzats.



        Double max = 0d;
        Double val;

        System.out.println("Permutations");
        for (List<Discount> list : permutations) {
            System.out.println(list);
            val = computeBestDiscountRecursive(list, products, 0, 0d);
            System.out.println(val);
            if (val > max) max = val;
        }

        return max;
    }



    public static List<List<Descompte>> backtracking(List<Descompte> descomptes){
        if(descomptes.size() == 1) {
            List<Descompte> listDesc = new ArrayList<>();
            List<List<Descompte>> mapDesc = new ArrayList<>();

            listDesc.add(descomptes.get(0));
            mapDesc.add(listDesc);
            return mapDesc;
        }

        List<List<Descompte>> mapDesc = new ArrayList<>();

        for(Descompte d: descomptes){

            List<Descompte> listAux = descomptes;
            listAux.remove(d);

            List<List<Descompte>> novaCombinacio = backtracking(new ArrayList<>(listAux));
            for (List<Descompte> dd : novaCombinacio) {
                novaCombinacio.add(dd);
                mapDesc.add(dd);
            }
        }

        return mapDesc;
    }
    */
}
