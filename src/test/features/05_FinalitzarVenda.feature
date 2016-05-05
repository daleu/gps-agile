# language: ca

#noinspection SpellCheckingInspection
Característica: Finalitzar venda


  Escenari: Finalitzar o anul.lar una venda buida
    Donat que hi ha una venda iniciada
    I la venda no te linies de venda
    I s'anula la venda
    Aleshores no hi ha cap venda iniciada

  Escenari: Anular una venda amb linies de venda
    Donat que hi ha una venda iniciada
    I existeix el producte "Baldufa" amb codi de barres "123456789" i preu per unitat 3,50
    I introdueixo al tpv el producte per nom "Baldufa"
    I introdueixo al tpv el producte per nom "Baldufa"
    I introdueixo al tpv el producte per nom "Baldufa"
    Quan s'anula la venda
    Aleshores no hi ha cap venda iniciada
    
  Escenari: Finalitzar una venda amb linies de venda
    Donat que hi ha una venda iniciada
    Donat existeix el producte "Action Man", amb codi de barres "777", preu Unitat 15 i iva 0,21
  #12,396 Preu Base.
    I existeix el producte "Barbie", amb codi de barres "111", preu Unitat 14 i iva 0,21
  #11,57 Preu Base.
    I existeix el producte "Lot Guminoles", amb codi de barres "333", preu Unitat 2 i iva 0,1
  #1,818 Preu Base.
    I existeix el producte "Llaminadura Xaxi", amb codi de barres "444", preu Unitat 1 i iva 0,1
  #,909 Preu Base.
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "777"
  #12,396*2=24,792
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "111"
  #11,57*2=23,14
    I que hi ha una linia de venda amb 1 unitats del producte amb codi de barres "333"
  #1,818
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "444"
  #0,909*2=1,818
  #Total: 51,568 Canvi: 8,432
    Quan indico que el client paga 65,0 euros en efectiu
    I es finalitza la venda
    Aleshores el valor a retornar al client és de 3
    I la linia 1 del tiquet sera " | Num. Venda: 1 | "
    I la linia 2 del tiquet sera " | 2 | Action Man | P.u. 15 | P.l. 30 | "
    I la linia 3 del tiquet sera " | 2 | Barbie | P.u. 14 | P.l. 28 | "
    I la linia 4 del tiquet sera " | 1 | Lot Guminoles | P.u. 2 | P.l. 2 | "
    I la linia 5 del tiquet sera " | 2 | Llaminadura Xaxi | P.u. 1 | P.l. 2 | "
    I la linia 6 del tiquet sera " | 21.0% | P.B: 47,93 | P.T: 58 | "
    I la linia 7 del tiquet sera " | 10.0% | P.B: 3,64 | P.T: 4 | "
    I la linia 8 del tiquet sera " | Total: 62 | Canvi: 3 | "