package edu.upc.essi.gps.ecommerce;

import cucumber.api.PendingException;
import cucumber.api.java.ca.Aleshores;
import cucumber.api.java.ca.Donat;
import cucumber.api.java.ca.I;
import cucumber.api.java.ca.Quan;
import edu.upc.essi.gps.ecommerce.domain.*;
import edu.upc.essi.gps.ecommerce.exceptions.*;
import edu.upc.essi.gps.ecommerce.repositoris.DevolucionsServei;
import edu.upc.essi.gps.ecommerce.repositoris.VendesRepositori;
import edu.upc.essi.gps.ecommerce.repositoris.VendesServei;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StepDefinitions {
    /*
     Enter (\\d+)
     Double (.+)
     String \"([^\"]*)\"
      */

    private TPV tpv = new TPV();
    private Exception exception;

    @Quan("^inicio una venda$")
    public void iniciarVenda() {
        try {
            tpv.iniciarVenda();
        } catch (VendaJaIniciadaException e) {
            this.exception = e;
        }
    }

    @Aleshores("^la venda no te linies de venda$")
    public void la_venda_no_te_linies_de_venda() throws Throwable {
        assertEquals(true, tpv.VendaActualisEmpty());
    }

    @Aleshores("^el preu total de la venda es (.+)$")
    public void el_preu_total_de_la_venda_es_(double totalExpected) throws Throwable {
        assertEquals(totalExpected, tpv.getTotalVenda(), 0.001);
    }

    @Aleshores("^la venda te per identificador (\\d+)$")
    public void la_venda_te_per_identificador(int expectedId) throws Throwable {
        assertNotNull(tpv.getVendaActual());
        assertEquals(expectedId, tpv.getIdVenda());
    }

    @Donat("^que hi ha una venda iniciada$")
    public void que_hi_ha_una_venda_iniciada() throws Throwable {
        try {
            tpv.iniciarVenda();
        } catch (VendaJaIniciadaException e) {
            this.exception = e;
        }
    }

    @Aleshores("^obtinc un error que diu \"([^\"]*)\"$")
    public void obtinc_un_error_que_diu(String expectedMessage) throws Throwable {
        assertNotNull(this.exception);
        assertEquals(expectedMessage, this.exception.getMessage());
    }

    @Quan("^tanco una venda sense tenir una venda iniciada$")
    public void tanco_una_venda_sense_tenir_una_venda_iniciada() throws Throwable {
        try {
            tpv.tancarVendaActual();
        } catch (VendaNoIniciadaException e) {
            this.exception = e;
        }
    }

    //TODO Decidir si fa falta aquesta funcio, ja que si la venda no es crea no cal ficarla a null
    @Donat("^que no hi ha cap venda iniciada$")
    public void que_no_hi_ha_cap_venda_iniciada() throws Throwable {
        tpv.setVendaActual(null);
    }

    @I("^existeix el producte \"([^\"]*)\" amb codi de barres \"([^\"]*)\" i preu per unitat (.+)$")
    public void existeix_el_producte_amb_codi_de_barres_i_preu_per_unitat_(String nomProducte, String codiBarres, double preuUnitat) throws Throwable {
        tpv.afegeixProducteACataleg(nomProducte,codiBarres,preuUnitat);
    }

    @Quan("^passo pel tpv el codi de barres \"([^\"]*)\"$")
    public void passo_pel_tpv_el_codi_de_barres(String codiBarres) throws Throwable {
        tpv.passarCodi(codiBarres);
    }

    @Quan("^introdueixo al tpv el producte per nom \"([^\"]*)\"$")
    public void introdueixoAlTpvElProductePerNom(String nomProducte) throws Throwable {
        tpv.introduirNomProducte(nomProducte);
    }

    @Aleshores("^la venda te una linia de venda$")
    public void la_venda_te_una_linia_de_venda() throws Throwable {
        assertEquals(1, tpv.getVendaActual().getNombreLiniesVenda());
    }

    @I("^la linia de venda (\\d+) te per producte \"([^\"]*)\"$")
    public void la_linia_de_venda_te_per_producte(int i, String expectedNom) throws Throwable {
        assertEquals(expectedNom, tpv.getVendaActual().getLiniaVenda(i).getNomProducte());
    }

    @I("^la linia de venda (\\d+) te per preu unitat (.+)$")
    public void la_linia_de_venda_te_per_preu_unitat_(int i, double expectedPreu) throws Throwable {
        assertEquals(expectedPreu, tpv.getVendaActual().getLiniaVenda(i).getTotal(), 0.001);
    }

    @I("^la linia de venda (\\d+) te per quantitat (\\d+)$")
    public void la_linia_de_venda_te_per_quantitat(int i, int expectedQuantitat) throws Throwable {
        assertEquals(expectedQuantitat, tpv.getVendaActual().getLiniaVenda(i).getQuantitat());
    }

    @I("^es finalitza la venda$")
    public void esFinalitzaLaVenda() throws Throwable {
        tpv.tancarVendaActual();
    }

    //TODO s'ha de mirar si es pot fer així
    @I("^es va fer una venda amb el codi (\\d+) amb (\\d+) productes amb codi \"([^\"]*)\" i (\\d+) producte amb codi \"([^\"]*)\"$")
    public void esVaFerUnaVendaAmbElCodiAmbProductesAmbCodiIProducteAmbCodi(int codiVenda, int unitatsProd1, String codiProd1, int unitatsProd2, String codiProd2) throws Throwable {
        tpv.iniciarVendaAmbID(codiVenda);
        tpv.afegirProducteLiniaVenda(codiProd1,unitatsProd1);
        tpv.afegirProducteLiniaVenda(codiProd1,unitatsProd1);
        tpv.tancarVendaActual();
    }


    @I("^s'afegeix a la linia de venda (\\d+) unitats del producte amb codi de barres \"([^\"]*)\"$")
    public void sAfegeixALaLiniaDeVendaUnitatsDelProducteAmbCodiDeBarres(int unitatsProd, String codiBarres) throws Throwable {
        tpv.afegirProducteLiniaVenda(codiBarres,unitatsProd);
    }

    @Quan("^introdueixo al tpv (.+) inicials$")
    public void introduirEfectiuInicial(double efectiu) throws Throwable {
        tpv.setEfectiuInicial(efectiu);
    }

    @Aleshores("^el tpv té (.+) inicials$")
    public void elTpvTeInicials(double inicial) {
        assertEquals(inicial,tpv.getEfectiuInicial(),0.0);
    }

    @Quan("^introdueixo al tpv (.+) finals$")
    public void introduirEfectiuFinal(double efectiu) throws Throwable {
        tpv.setEfectiuFinal(efectiu);
    }

    @Aleshores("^el tpv té (.+) finals$")
    public void elTpvTeFinals(double fin) {
        assertEquals(fin,tpv.getEfectiuFinal(),0.0);
    }

    @Aleshores("^obtinc un missatge que diu \"([^\"]*)\"$")
    public void obtenirMissatge(String msg) {
        assertEquals(msg,tpv.obtenirMissatge());
    }


    //TODO Falta implementar
    @I("^El preu final de la venda ha de ser la suma dels productes meny la suma de les devolucions$")
    public void elPreuFinalDeLaVendaHaDeSerLaSumaDelsProductesMenyLaSumaDeLesDevolucions() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Quan("^vull fer una devolucio$")
    public void vullFerUnaDevolucio(){
        tpv.iniciarDevolucio();
    }

    //TODO: Diferenciar casos
    @I("^es vol retornar (\\d+) unitat del producte amb codi \"([^\"]*)\" de la venda (\\d+) pel motiu \"([^\"]*)\"$")
    public void esVolRetornarUnitatDelProducteAmbCodiDeLaVendaPelMotiu(int unitatsProd, String codiBarres, int idVenda,String motiu) throws Throwable {
        if(tpv.possibilitatDeRetorn(idVenda,codiBarres,unitatsProd)) {
            tpv.afegirDevolucioLiniaVenda(idVenda,codiBarres,unitatsProd,motiu);
        }
        else {
            throw new Exception("No es possible crear aquesta devolucio");
        }
    }

    @Aleshores("^existeix una devolucio del producte \"([^\"]*)\" de la venda (\\d+) pel motiu \"([^\"]*)\"$")
    public void existeixUnaDevolucioDelProducteDeLaVendaPelMotiu(String expectedCodiBarres, int expectedIdVenda, String expectedMotiu) throws Throwable {
        assertEquals(expectedCodiBarres,tpv.getCodiBarresDevolucio(expectedIdVenda,expectedCodiBarres,1));
        assertEquals(expectedIdVenda,tpv.getIdVendaDevolucio(expectedIdVenda,expectedCodiBarres,1));
        assertEquals(expectedMotiu,tpv.getMotiuDevolucio(expectedIdVenda,expectedCodiBarres,1));
    }

    @Quan("^inicio una nova venda$")
    public void inicio_una_nova_venda() throws Throwable {
        try {
            tpv.iniciarVenda();
        } catch (VendaJaIniciadaException e) {
            this.exception = e;
        }
    }
}