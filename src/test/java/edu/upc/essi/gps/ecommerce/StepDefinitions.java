package edu.upc.essi.gps.ecommerce;

import cucumber.api.PendingException;
import cucumber.api.java.ca.Aleshores;
import cucumber.api.java.ca.Donat;
import cucumber.api.java.ca.I;
import cucumber.api.java.ca.Quan;
import edu.upc.essi.gps.ecommerce.domain.*;
import edu.upc.essi.gps.ecommerce.exceptions.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StepDefinitions {
    /*
     Enter (\\d+)
     Double (.+)
     String \"([^\"]*)\"
      */

    private TPVController tpvController = new TPVController();
    private Exception exception;

    @Quan("^inicio una venda$")
    public void iniciarVenda() {
        try {
            tpvController.iniciarVenda();
        } catch (VendaJaIniciadaException e) {
            this.exception = e;
        }
    }

    @Aleshores("^la venda no te linies de venda$")
    public void laVendaNoTeLiniesDeVenda() throws Throwable {
        assertEquals(true, tpvController.vendaActualIsEmpty());
    }

    @Aleshores("^el preu total de la venda es (.+)$")
    public void elPreuTotalDeLaVendaEs(double totalExpected) throws Throwable {
        assertEquals(totalExpected, tpvController.getTotalVenda(), 0.001);
    }

    @Aleshores("^la venda te per identificador (\\d+)$")
    public void laVendaTePerIdentificador(int expectedId) throws Throwable {
        assertNotNull(tpvController.getVendaActual());
        assertEquals(expectedId, tpvController.getIdVenda());
    }

    @Donat("^que hi ha una venda iniciada$")
    public void queHiHaUnaVendaIniciada() throws Throwable {
        try {
            tpvController.iniciarVenda();
        } catch (VendaJaIniciadaException e) {
            this.exception = e;
        }
    }

    @Aleshores("^obtinc un error que diu \"([^\"]*)\"$")
    public void obtincUnErrorQueDiu(String expectedMessage) throws Throwable {
        assertNotNull(this.exception);
        assertEquals(expectedMessage, this.exception.getMessage());
    }

    //TODO Decidir si fa falta aquesta funcio, ja que si la venda no es crea no cal ficarla a null
    @Donat("^que no hi ha cap venda iniciada$")
    public void queNoHiHaCapVendaIniciada() throws Throwable {
        tpvController.setVendaActual(null);
    }

    @I("^existeix el producte \"([^\"]*)\" amb codi de barres \"([^\"]*)\" i preu per unitat (.+)$")
    public void existeixElProducteAmbCodiDeBarresIPreuPerUnitat(String nomProducte, String codiBarres, double preuUnitat) throws Throwable {
        tpvController.afegeixProducteACataleg(nomProducte,codiBarres,preuUnitat);
    }

    @Quan("^passo pel tpv el codi de barres \"([^\"]*)\"$")
    public void passoPelTpvElCodiDeBarres(String codiBarres) throws Throwable {
        tpvController.passarCodi(codiBarres);
    }

    @Quan("^introdueixo al tpv el producte per nom \"([^\"]*)\"$")
    public void introdueixoAlTpvElProductePerNom(String nomProducte) throws Throwable {
        tpvController.introduirNomProducte(nomProducte);
    }

    //TODO: Es podria modificar per la venda te (int) linies de venda. FUNCIO A SOTA
    @Aleshores("^la venda te una linia de venda$")
    public void laVendaTeUnaLiniaDeVenda() throws Throwable {
        assertEquals(1, tpvController.getVendaActual().getNombreLiniesVenda());
    }
    @Aleshores("^la venda te (\\d+) linia de venda$")
    public void laVendaTeLiniaDeVenda(int num) throws Throwable {
        assertEquals(num, tpvController.getVendaActual().getNombreLiniesVenda());
    }


    @I("^la linia de venda (\\d+) te per producte \"([^\"]*)\"$")
    public void laLiniaDeVendaTePerProducte(int i, String expectedNom) throws Throwable {
        assertEquals(expectedNom, tpvController.getVendaActual().getLiniaVenda(i).getNomProducte());
    }

    @I("^la linia de venda (\\d+) te per preu unitat (.+)$")
    public void laLiniaDeVendaTePerPreuUnitat(int i, double expectedPreu) throws Throwable {
        assertEquals(expectedPreu, tpvController.getVendaActual().getLiniaVenda(i).getTotal(), 0.001);
    }

    @I("^la linia de venda (\\d+) te per quantitat (\\d+)$")
    public void laLiniaDeVendaTePerQuantitat(int i, int expectedQuantitat) throws Throwable {
        assertEquals(expectedQuantitat, tpvController.getVendaActual().getLiniaVenda(i).getQuantitat());
    }

    @I("^es finalitza la venda$")
    public void esFinalitzaLaVenda() throws Throwable {
        try {
            tpvController.tancamentVenda();
        } catch (VendaNoIniciadaException | VendaJaFinalitzadaException e) {
            this.exception = e;
        }
    }

    @Quan("^s'anula la venda$")
    public void sAnulaLaVenda() throws Throwable {
        tpvController.anularVendaActual();
    }

    @I("^el client paga (.+) euros en efectiu$")
    public void elClientPagaEurosEnEfectiu(double valor) throws Throwable {
        tpvController.setPreuPagament(valor);
    }

    //TODO s'ha de mirar si es pot fer així
    @I("^es va fer una venda amb el codi (\\d+) amb (\\d+) productes amb codi \"([^\"]*)\" i (\\d+) producte amb codi \"([^\"]*)\"$")
    public void esVaFerUnaVendaAmbElCodiAmbProductesAmbCodiIProducteAmbCodi(int codiVenda, int unitatsProd1, String codiProd1, int unitatsProd2, String codiProd2) throws Throwable {
        tpvController.iniciarVendaAmbID(codiVenda);
        tpvController.afegirProducteLiniaVenda(codiProd1,unitatsProd1);
        tpvController.afegirProducteLiniaVenda(codiProd1,unitatsProd1);
        //El tancament de venda s'ha de fer bé //tpvController.tancamentVenda();
    }

    @I("^s'afegeix a la linia de venda (\\d+) unitats del producte amb codi de barres \"([^\"]*)\"$")
    public void sAfegeixALaLiniaDeVendaUnitatsDelProducteAmbCodiDeBarres(int unitatsProd, String codiBarres) throws Throwable {
        tpvController.afegirProducteLiniaVenda(codiBarres,unitatsProd);
    }

    @Quan("^introdueixo al tpv (.+) inicials$")
    public void introduirEfectiuInicial(double efectiu) throws Throwable {
        tpvController.setEfectiuInicial(efectiu);
    }

    @Aleshores("^el tpv té (.+) inicials$")
    public void elTpvTeInicials(double inicial)throws Throwable {
        assertEquals(inicial, tpvController.getEfectiuInicial(),0.0);
    }

    @Quan("^introdueixo al tpv (.+) finals$")
    public void introduirEfectiuFinal(double efectiu) throws Throwable {
        tpvController.setEfectiuFinal(efectiu);
    }

    @Aleshores("^el tpv té (.+) finals$")
    public void elTpvTeFinals(double fin)throws Throwable {
        assertEquals(fin, tpvController.getEfectiuFinal(),0.0);
    }

    @Aleshores("^obtinc un missatge que diu \"([^\"]*)\"$")
    public void obtenirMissatge(String msg) {
        assertEquals(msg, tpvController.getEstatQuadrament());
    }

    @Donat("^que hi ha al tpv (.+) inicials")
    public void elTpvHiHaInicials(double efectiu) throws Throwable { tpvController.setEfectiuInicial(efectiu);}

    @I("^que hi ha una linia de venda amb (\\d+) unitats del producte amb codi de barres \"([^\"]*)\"$")
    public void HiHaUnaLiniaDeVenda(int unitats, String codi) throws Throwable {
        tpvController.afegirProducteLiniaVenda(codi,unitats);
    }

    @I("^demano el quadrament")
    public void demanoQuadrament() { tpvController.quadrament(); }




    //TODO Falta implementar
    @I("^El preu final de la venda ha de ser la suma dels productes menys la suma de les devolucions$")
    public void elPreuFinalDeLaVendaHaDeSerLaSumaDelsProductesMenyLaSumaDeLesDevolucions() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Quan("^vull fer una devolucio$")
    public void vullFerUnaDevolucio(){
        tpvController.iniciarDevolucio();
    }

    //TODO: Diferenciar casos. Possibilitat de retorn es un Donat i AfegirDevolucioLiniaVenda és un Quan
    //No es pot fer un throw aqui, ho ha de fer el TPVController en aquest cas (és lògica)
    @I("^es vol retornar (\\d+) unitat del producte amb codi \"([^\"]*)\" de la venda (\\d+) pel motiu \"([^\"]*)\"$")
    public void esVolRetornarUnitatDelProducteAmbCodiDeLaVendaPelMotiu(int unitatsProd, String codiBarres, int idVenda,String motiu) throws Throwable {
        if(tpvController.possibilitatDeRetorn(idVenda,codiBarres,unitatsProd)) {
            tpvController.afegirDevolucioLiniaVenda(idVenda,codiBarres,unitatsProd,motiu);
        }
        else {
            throw new Exception("No es possible crear aquesta devolucio");
        }
    }

    @Aleshores("^existeix una devolucio del producte \"([^\"]*)\" de la venda (\\d+) pel motiu \"([^\"]*)\"$")
    public void existeixUnaDevolucioDelProducteDeLaVendaPelMotiu(String expectedCodiBarres, int expectedIdVenda, String expectedMotiu) throws Throwable {
        assertEquals(expectedCodiBarres, tpvController.getCodiBarresDevolucio(expectedIdVenda,expectedCodiBarres,1));
        assertEquals(expectedIdVenda, tpvController.getIdVendaDevolucio(expectedIdVenda,expectedCodiBarres,1));
        assertEquals(expectedMotiu, tpvController.getMotiuDevolucio(expectedIdVenda,expectedCodiBarres,1));
    }

    @Quan("^inicio una nova venda$")
    public void inicio_una_nova_venda() throws Throwable {
        try {
            tpvController.iniciarVenda();
        } catch (VendaJaIniciadaException e) {
            this.exception = e;
        }
    }


    @Aleshores("^no hi ha cap venda iniciada$")
    public void noHiHaCapVendaIniciada() throws Throwable {
        tpvController.vendaActualIsNull();
    }


    @Aleshores("^el valor a retornar al client és de (.+)$")
    public void elValorARetornarAlClientÉsDe(double valor) throws Throwable {
        assertEquals(valor, tpvController.getCanviUltimaVenda(),0.001);

    }
}