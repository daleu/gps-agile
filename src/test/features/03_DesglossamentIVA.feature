# language: ca

#noinspection SpellCheckingInspection
Característica: Desglossament del IVA

  Rerefons:
    Donat existeix el producte "Action Man", amb codi de barres "777", preu Unitat 15 i iva 0,21
    I existeix el producte "Barbie", amb codi de barres "111", preu Unitat 14 i iva 0,21
    I existeix el producte "Vaixell Lego", amb codi de barres "222", preu Unitat 30 i iva 0,21
    I existeix el producte "Lot Guminoles", amb codi de barres "333", preu Unitat 2 i iva 0,1
    I existeix el producte "Llaminadura Xaxi", amb codi de barres "444", preu Unitat 1 i iva 0,1

  Escenari: Obtenir preusBase d'una venda segons el tipus d'IVA
    Donat que hi ha una venda iniciada
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "777"
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "111"
    I que hi ha una linia de venda amb 1 unitats del producte amb codi de barres "222"
    I que hi ha una linia de venda amb 1 unitats del producte amb codi de barres "333"
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "444"
    Quan es finalitza la venda
    Aleshores els preusBase dels productes amb iva 0,21 es 72,727
    I els preusBase dels productes amb iva 0,1 es 3,636


  Escenari: Obtenir preuTotal d'una venda segons el tipus d'IVA
    Donat que hi ha una venda iniciada
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "777"
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "111"
    I que hi ha una linia de venda amb 1 unitats del producte amb codi de barres "222"
    I que hi ha una linia de venda amb 1 unitats del producte amb codi de barres "333"
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "444"
    Quan es finalitza la venda
    Aleshores el preuTotal dels productes amb iva 0,21 es 88,0
    I el preuTotal dels productes amb iva 0,1 es 4,0


