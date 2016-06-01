#language: ca

#noinspection SpellCheckingInspection
Característica: Utilitzar Ofertes de NxM

  Rerefons:
    Donat el TPV esta a la botiga "Girona"
    I existeix el producte "Pilota" amb codi de barres "111" i preu per unitat 5
    I existeix el producte "Pala" amb codi de barres "222" i preu per unitat 2
    I existeix el producte "Raqueta" amb codi de barres "333" i preu per unitat 10
    I existeixen al sistema les ofertes NxM:
      | 123 | 2 | 1 | 01/06/2016 | 01/07/2016 | 111,222,333|
    I inicio una nova venda
    I l'empleat que ha iniciat la venda es diu "Joan"

  Escenari: S'utilitza l'oferta automaticament si estem dins del periode
    Donat que estem a dia i hora "03/06/2016 20:24:10"
    I s'afegeix a la linia de venda 3 unitats del producte amb codi de barres "333"
    Quan indico que el client paga 20 euros en efectiu
    I es finalitza la venda
    Aleshores la linia 1 del tiquet sera " | Nom botiga: Girona | "
    I la linia 2 del tiquet sera " | Num. Venda: 1 | Codi Tiquet: C1"
    I la linia 3 del tiquet sera " | 3 | Raqueta | P.u. 10 | P.l. 20 | "
    I la linia 4 del tiquet sera " | 21.0% | P.B: 16,53 | P.T: 20 | "
    I la linia 5 del tiquet sera " | Total: 20 | Canvi: 0 | Pagat en efectiu | "
    I la linia 6 del tiquet sera " | 03/06/2016 20:24:10 | "
    I la linia 7 del tiquet sera " | Atès per: Joan | "

  Escenari: L'oferta no s'utilitza si no ens trobem en el periode d'aquesta
    Donat que estem a dia i hora "03/09/2016 20:24:10"
    I s'afegeix a la linia de venda 3 unitats del producte amb codi de barres "333"
    Quan indico que el client paga 30 euros en efectiu
    I es finalitza la venda
    Aleshores la linia 1 del tiquet sera " | Nom botiga: Girona | "
    I la linia 2 del tiquet sera " | Num. Venda: 1 | Codi Tiquet: C1"
    I la linia 3 del tiquet sera " | 3 | Raqueta | P.u. 10 | P.l. 30 | "
    I la linia 4 del tiquet sera " | 21.0% | P.B: 24,79 | P.T: 30 | "
    I la linia 5 del tiquet sera " | Total: 30 | Canvi: 0 | Pagat en efectiu | "
    I la linia 6 del tiquet sera " | 03/09/2016 20:24:10 | "
    I la linia 7 del tiquet sera " | Atès per: Joan | "

  Escenari: Si hem falta un producte per aplicar l'oferta, opto per aplicar el producte
    Donat que estem a dia i hora "03/06/2016 20:24:10"
    I s'afegeix a la linia de venda 1 unitats del producte amb codi de barres "333"
    I es finalitza la venda
    I obtinc 1 missatge
    I missatge 1 es "Es pot aplicar una oferta 2x1 pel producte Raqueta. Vols aplicar-la?"
    Quan indico que vull aplicar l'oferta pel producte "Raqueta"
    Aleshores indico que el client paga 20 euros en efectiu
    I es finalitza la venda
    I la linia 1 del tiquet sera " | Nom botiga: Girona | "
    I la linia 2 del tiquet sera " | Num. Venda: 1 | Codi Tiquet: C1"
    I la linia 3 del tiquet sera " | 2 | Raqueta | P.u. 10 | P.l. 10 | "
    I la linia 4 del tiquet sera " | 21.0% | P.B: 8,26 | P.T: 10 | "
    I la linia 5 del tiquet sera " | Total: 10 | Canvi: 10 | Pagat en efectiu | "
    I la linia 6 del tiquet sera " | 03/06/2016 20:24:10 | "
    I la linia 7 del tiquet sera " | Atès per: Joan | "

  Escenari: Si hem falta un producte per aplicar l'oferta, opto per no aplicar el producte
    Donat que estem a dia i hora "03/06/2016 20:24:10"
    I s'afegeix a la linia de venda 1 unitats del producte amb codi de barres "333"
    I es finalitza la venda
    I obtinc 1 missatge
    I missatge 1 es "Es pot aplicar una oferta 2x1 pel producte Raqueta. Vols aplicar-la?"
    Quan indico que vull no aplicar l'oferta pel producte "Raqueta"
    Aleshores indico que el client paga 20 euros en efectiu
    I es finalitza la venda
    I la linia 1 del tiquet sera " | Nom botiga: Girona | "
    I la linia 2 del tiquet sera " | Num. Venda: 1 | Codi Tiquet: C1"
    I la linia 3 del tiquet sera " | 1 | Raqueta | P.u. 10 | P.l. 10 | "
    I la linia 4 del tiquet sera " | 21.0% | P.B: 8,26 | P.T: 10 | "
    I la linia 5 del tiquet sera " | Total: 10 | Canvi: 10 | Pagat en efectiu | "
    I la linia 6 del tiquet sera " | 03/06/2016 20:24:10 | "
    I la linia 7 del tiquet sera " | Atès per: Joan | "