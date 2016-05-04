# language: ca

#noinspection SpellCheckingInspection
Característica: Desglossament del IVA

  Rerefons:
    Donat existeix el producte "Action Man", amb codi de barres "777", preu Base 15 i iva 0,21
    I existeix el producte "Barbie", amb codi de barres "111", preu Base 14 i iva 0,21
    I existeix el producte "Vaixell Lego", amb codi de barres "222", preu Base 30 i iva 0,21
    I existeix el producte "Lot Guminoles", amb codi de barres "333", preu Base 2 i iva 0,1
    I existeix el producte "Llaminadura Xaxi", amb codi de barres "444", preu Base 1 i iva 0,1

  Escenari: Agafar preu Base del producte
    Aleshores el preu Base del producte "Action Man" serà 15

  Escenari: Agafar preu Unitat del producte
    Aleshores el preu Unitat del producte "Action Man" serà 18,15

  Escenari:
    Donat que hi ha una venda iniciada
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "777"
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "111"
    I que hi ha una linia de venda amb 1 unitats del producte amb codi de barres "222"
    I que hi ha una linia de venda amb 1 unitats del producte amb codi de barres "333"
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "444"
    Aleshores els preusBase dels productes amb iva 0,21 es 88
    I els preusBase dels productes amb iva 0,1 es 4
    I el preuTotal dels productes amb iva 0,21 es 106,48
    I el preuTotal dels productes amb iva 0,1 es 4,4


