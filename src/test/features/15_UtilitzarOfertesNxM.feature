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
    I que estem a dia i hora "03/06/2016 20:24:10"
    I l'empleat que ha iniciat la venda es diu "Joan"

  Escenari: Utilitzar oferta MxN sobre una venda
    Donat s'afegeix a la linia de venda 2 unitats del producte amb codi de barres "333"
    Quan indico que el client paga 20 euros en efectiu
    I es finalitza la venda
    Aleshores la linia 1 del tiquet sera " | Nom botiga: Girona | "
    I la linia 2 del tiquet sera " | Num. Venda: 1 | Codi Tiquet: C1"
    I la linia 3 del tiquet sera " | 2 | Raqueta | P.u. 10 | P.l. 10 | "
    I la linia 4 del tiquet sera " | 21.0% | P.B: 8,26 | P.T: 10 | "
    I la linia 5 del tiquet sera " | Total: 10 | Canvi: 10 | Pagat en efectiu | "
    I la linia 6 del tiquet sera " | 03/06/2016 20:24:10 | "
    I la linia 7 del tiquet sera " | Atès per: Joan | "