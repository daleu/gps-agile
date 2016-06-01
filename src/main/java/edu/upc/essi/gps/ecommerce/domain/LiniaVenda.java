package edu.upc.essi.gps.ecommerce.domain;

import edu.upc.essi.gps.ecommerce.domain.ofertes.Oferta;
import edu.upc.essi.gps.ecommerce.domain.ofertes.OfertaNxM;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by edu on 28/04/16.
 */
public class LiniaVenda {
    private Producte producte;
    private int quantitat;
    private int ofertaM = 0;
    private boolean ofertaAplicada = true;

    public LiniaVenda(Producte p,Integer q){
        this.producte = p;
        quantitat = q;
    }

    public double getPreuTotal(Calendar dataIHora) {
        ArrayList<Oferta> llistaOfertes = producte.getOfertes();
        boolean ofertaNxMUtilitzada = false;
        for(int i = 0; i<llistaOfertes.size(); ++i){
            Calendar dataIni = llistaOfertes.get(i).getDataInici();
            Calendar datafi = llistaOfertes.get(i).getDataFinal();
            int N = 0;
            int M = 0;
            if(llistaOfertes.get(i) instanceof OfertaNxM){
                N = ((OfertaNxM) llistaOfertes.get(i)).getN();
                M = ((OfertaNxM) llistaOfertes.get(i)).getM();
            }
            if(dataIHora.before(datafi) && dataIHora.after(dataIni) && quantitat>=N){
                ofertaNxMUtilitzada = true;
                ofertaM = (quantitat/N)*M+quantitat%N;
            }
        }
        if(ofertaNxMUtilitzada){
            return producte.getPreuUnitat()*ofertaM;
        }
        else{
            return producte.getPreuUnitat()*quantitat;
        }
    }

    public String getNomProducte() {
        return producte.getNom();
    }

    public int getQuantitat() {
        return quantitat;
    }

    public String getCodiProducte() {
        return producte.getCodiBarres();
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

    public void incrementaQuantitat(Integer unitats) {
        this.quantitat += unitats;
    }

    public double getPreuUnitat() {
        return producte.getPreuUnitat();
    }

    public double getIVAProducte() {return producte.getIVA();}


    public double getTotalPreuBase(double iva) {
        if(producte.mateixIva(iva)){
            if(ofertaM!=0){
                return producte.getPreuBase()*ofertaM;
            }
            else {
                return producte.getPreuBase()*quantitat;
            }
        }
        else return 0.0;
    }

    public double getTotalUnitatBase(double iva) {
        if(producte.mateixIva(iva)){
            if(ofertaM!=0){
                return producte.getPreuUnitat()*ofertaM;
            }
            else{
                return producte.getPreuUnitat()*quantitat;
            }
        }
        else return 0.0;
    }

    public boolean comprovarOfertaMxN(Calendar dataIHora) {
        boolean existeix = false;
        if(ofertaAplicada==true) {
            ArrayList<Oferta> llistaOfertes = producte.getOfertes();
            for (int i = 0; i < llistaOfertes.size(); ++i) {
                Calendar dataIni = llistaOfertes.get(i).getDataInici();
                Calendar datafi = llistaOfertes.get(i).getDataFinal();
                int N = 0;
                int M = 0;
                if (llistaOfertes.get(i) instanceof OfertaNxM) {
                    N = ((OfertaNxM) llistaOfertes.get(i)).getN();
                    M = ((OfertaNxM) llistaOfertes.get(i)).getM();
                }
                if (dataIHora.before(datafi) && dataIHora.after(dataIni) && (quantitat == N - 1 || quantitat==M)){
                    existeix = true;
                }
            }
        }
        return existeix;
    }

    public OfertaNxM getOfertaNxM() {
        OfertaNxM aux = null;
        ArrayList<Oferta> llistaOfertes = producte.getOfertes();
        for(int i = 0; i<llistaOfertes.size(); ++i){
            if(llistaOfertes.get(i) instanceof OfertaNxM){
                aux = ((OfertaNxM) llistaOfertes.get(i));
            }
        }
        return aux;
    }

    public void setQuantitatOferta() {
        OfertaNxM aux = null;
        ArrayList<Oferta> llistaOfertes = producte.getOfertes();
        for(int i = 0; i<llistaOfertes.size(); ++i){
            if(llistaOfertes.get(i) instanceof OfertaNxM){
                aux = ((OfertaNxM) llistaOfertes.get(i));
            }
        }
        quantitat = aux.getN();
    }

    public void noAplicarOferta() {
        ofertaAplicada = false;
    }
}
