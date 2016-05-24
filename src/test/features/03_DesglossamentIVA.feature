# language: ca

#noinspection SpellCheckingInspection
Característica: Desglossament del IVA

  Rerefons:
    Donat existeix el producte "Action Man", amb codi de barres "777", preu Unitat 15 i iva 0,21
    I existeix el producte "Barbie", amb codi de barres "111", preu Unitat 14 i iva 0,21
    I existeix el producte "Vaixell Lego", amb codi de barres "222", preu Unitat 30 i iva 0,21
    I existeix el producte "Lot Guminoles", amb codi de barres "333", preu Unitat 2 i iva 0,1
    I existeix el producte "Llaminadura Xaxi", amb codi de barres "444", preu Unitat 1 i iva 0,1
    I es va fer una venda amb id 111 dels següens productes i seguents unitats
    |777|2|
    |111|2|
    |222|1|
    |333|1|
    |444|2|


  Escenari: Obtenir preusBase d'una venda segons el tipus d'IVA
    Quan es finalitza la venda
    Aleshores els preusBase dels productes amb iva 0,21 es 72,727
    I els preusBase dels productes amb iva 0,1 es 3,636


  Escenari: Obtenir preuTotal d'una venda segons el tipus d'IVA
    Quan es finalitza la venda
    Aleshores el preuTotal dels productes amb iva 0,21 es 88,0
    I el preuTotal dels productes amb iva 0,1 es 4,0


