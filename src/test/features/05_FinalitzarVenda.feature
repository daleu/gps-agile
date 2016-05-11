# language: ca

#noinspection SpellCheckingInspection
Característica: Finalitzar venda


  Escenari: Anul.lar una venda buida
    Donat que hi ha una venda iniciada
    Quan s'anula la venda
    Aleshores no hi ha cap venda iniciada

  Escenari: Anular una venda amb linies de venda
    Donat existeix el producte "Baldufa" amb codi de barres "123456789" i preu per unitat 3,50
    I que hi ha una venda iniciada
    I introdueixo al tpv 3 producte amb nom "Baldufa"
    Quan s'anula la venda
    Aleshores no hi ha cap venda iniciada

    #L'ESTI FENT: CÈLIA
  Escenari: Finalitzar una venda amb linies de venda i s'imprimeix el tiquet
    Donat el TPV esta a la botiga "Carroça"
    I existeix el producte "Action Man", amb codi de barres "777", preu Unitat 15 i iva 0,21
  #12,396 Preu Base.
    I existeix el producte "Barbie", amb codi de barres "111", preu Unitat 14 i iva 0,21
  #11,57 Preu Base.
    I existeix el producte "Lot Guminoles", amb codi de barres "333", preu Unitat 2 i iva 0,1
  #1,818 Preu Base.
    I existeix el producte "Llaminadura Xaxi", amb codi de barres "444", preu Unitat 1 i iva 0,1
  #0,909 Preu Base.
    I que hi ha una venda iniciada
    I es marca el nom de la botiga -ja definit al TPV- a la venda
    I l'empleat que ha iniciat la venda es diu "Joan"
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "777"
  #12,396*2=24,792
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "111"
  #11,57*2=23,14
    I que hi ha una linia de venda amb 1 unitats del producte amb codi de barres "333"
  #1,818
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "444"
    I que estem a dia i hora "12/04/2016 12:34:20"
  #0,909*2=1,818
  #Total: 51,568 Canvi: 8,432
    Quan indico que el client paga 65,0 euros en efectiu
    I es finalitza la venda
    Aleshores el valor a retornar al client és de 3
    #"NomPilaEmpleat, NomBotiga,"
    #"NumVenda, DataIHora, CodiTiquet
    #(Per cada linia) "Quantitat, NomProducte, PreuUnitat, PreuLinia"
    #(Per cada grup d'IVA): "IVA aplicat PreuSumaTotalsBase PreuSumaTotals"
    #"mètodePagament TotalAPagar Pagat Canvi"
    I la linia 1 del tiquet sera " | Nom botiga: Carroça | "
    I la linia 2 del tiquet sera " | Num. Venda: 1 | Codi Tiquet: C1"
    I la linia 3 del tiquet sera " | 2 | Action Man | P.u. 15 | P.l. 30 | "
    I la linia 4 del tiquet sera " | 2 | Barbie | P.u. 14 | P.l. 28 | "
    I la linia 5 del tiquet sera " | 1 | Lot Guminoles | P.u. 2 | P.l. 2 | "
    I la linia 6 del tiquet sera " | 2 | Llaminadura Xaxi | P.u. 1 | P.l. 2 | "
    I la linia 7 del tiquet sera " | 21.0% | P.B: 47,93 | P.T: 58 | "
    I la linia 8 del tiquet sera " | 10.0% | P.B: 3,64 | P.T: 4 | "
    I la linia 9 del tiquet sera " | Total: 62 | Canvi: 3 | "
    I la linia 10 del tiquet sera " | 12/04/2016 12:34:20 | "
    I la linia 11 del tiquet sera " | Atès per: Joan | "
    #Faltarà fer els descomptes quan en tinguem

      #L'ESTI FENT: CÈLIA
  Escenari: Finalitzar una venda amb linies de venda
    Donat el TPV esta a la botiga "Carroça"
    I existeix el producte "Action Man", amb codi de barres "777", preu Unitat 15 i iva 0,21
  #12,396 Preu Base.
    I existeix el producte "Barbie", amb codi de barres "111", preu Unitat 14 i iva 0,21
  #11,57 Preu Base.
    I existeix el producte "Lot Guminoles", amb codi de barres "333", preu Unitat 2 i iva 0,1
  #1,818 Preu Base.
    I existeix el producte "Llaminadura Xaxi", amb codi de barres "444", preu Unitat 1 i iva 0,1
  #0,909 Preu Base.
    I que hi ha una venda iniciada
    I es marca el nom de la botiga -ja definit al TPV- a la venda
    I l'empleat que ha iniciat la venda es diu "Joan"
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "777"
  #12,396*2=24,792
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "111"
  #11,57*2=23,14
    I que hi ha una linia de venda amb 1 unitats del producte amb codi de barres "333"
  #1,818
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "444"
    I que estem a dia i hora "12/04/2016 12:34:20"
  #0,909*2=1,818
  #Total: 51,568 Canvi: 8,432
    Quan indico que el client paga 65,0 euros en efectiu
    I s'anula la venda
    Aleshores no hi ha cap venda iniciada
