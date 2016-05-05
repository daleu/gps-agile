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
    Donat existeix el producte "Action Man", amb codi de barres "777", preu Base 15 i iva 0,21
    #18,15 Preu Unitat.
    I existeix el producte "Barbie", amb codi de barres "111", preu Base 14 i iva 0,21
    #16,94 Preu Unitat.
    I existeix el producte "Lot Guminoles", amb codi de barres "333", preu Base 2 i iva 0,1
    #2,2 Preu Unitat.
    I existeix el producte "Llaminadura Xaxi", amb codi de barres "444", preu Base 1 i iva 0,1
    #1,1 Preu Unitat.
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "777"
    #18,15*2=36,3
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "111"
    #16,94*2=33,88
    I que hi ha una linia de venda amb 1 unitats del producte amb codi de barres "333"
    #2,2
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "444"
    #2,2
    #Total: 74,58
    Quan indico que el client paga 100,0 euros en efectiu
    I es finalitza la venda
    Aleshores el valor a retornar al client és de 25,42
    I la linia 1 del tiquet sera " | Num. Venda: 1 | "
    I la linia 2 del tiquet sera " | 2 | Action Man | P.u. 18,15 | P.l. 36,3 | "
    I la linia 3 del tiquet sera " | 2 | Barbie | P.u. 16,94 | P.l. 33,88 | "
    I la linia 4 del tiquet sera " | 1 | Lot Guminoles | P.u. 2,2 | P.l. 2,2 | "
    I la linia 5 del tiquet sera " | 2 | Llaminadura Xaxi | P.u. 1,1 | P.l. 2,2 | "
    I la linia 6 del tiquet sera " | 21.0% | P.B: 58 | P.T: 70,18 | "
    I la linia 7 del tiquet sera " | 10.0% | P.B: 4 | P.T: 4,4 | "
    I la linia 8 del tiquet sera " | Total: 74,58 | Canvi: 25,42 | "