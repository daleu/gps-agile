# language: ca

  #noinspection SpellCheckingInspection
Característica: Retornar un producte mentres es fa una venda
  Rerefons:
    Donat existeix el producte "Plastilina de 20 colors" amb codi de barres "444" i preu per unitat 25
    I existeix el producte "Action Man" amb codi de barres "777" i preu per unitat 15
    I existeix el producte "Baldufa" amb codi de barres "333" i preu per unitat 3,25
    I existeix el producte "Cotxe teledirigit" amb codi de barres "222" i preu per unitat 25
    I es va fer una venda amb id 111 dels següens productes i seguents unitats
    |777|2|
    |222|1|

  Escenari: Retorno un producte en una venda amb una lina de venda i el resultat de la venda es positiu
  Quan inicio una nova venda
    I s'afegeix a la linia de venda 3 unitats del producte amb codi de barres "333"
    I s'afegeix a la linia de venda 1 unitats del producte amb codi de barres "222"
    I es vol indicar una devolucio de 1 unitats del producte "777" de la venda 111 sense motiu
    I indico que el client paga 20 euros en efectiu
    I es finalitza la venda
    Aleshores la linia 5 del tiquet sera " | Devolucio(ns): | "
    I la linia 6 del tiquet sera " | 1 | Action Man | P.u 15 | P.l 15 | "
    I la linia 8 del tiquet sera " | Total a pagar: 34,75 | "
    I la linia 9 del tiquet sera " | Total en retorn: -15 | "
    I la linia 10 del tiquet sera " | Total: 19,75 | Canvi: 0,25 | Pagat en efectiu | "
    I existeix una devolucio del producte "777" de la venda 111


  Escenari: Retorno un producte en una venda amb una lina de venda i el resultat de la venda es negatiu
    Quan inicio una nova venda
    I s'afegeix a la linia de venda 3 unitats del producte amb codi de barres "333"
    I es vol indicar una devolucio de 1 unitats del producte "777" de la venda 111 sense motiu
    I es vol indicar una devolucio de 2 unitats del producte "222" de la venda 111 sense motiu
    I indico que el client paga 0 euros en efectiu
    I es finalitza la venda
    Aleshores la linia 4 del tiquet sera " | Devolucio(ns): | "
    I la linia 5 del tiquet sera " | 1 | Action Man | P.u 15 | P.l 15 | "
    I la linia 6 del tiquet sera " | 2 | Cotxe teledirigit | P.u 25 | P.l 50 | "
    I la linia 8 del tiquet sera " | Total a pagar: 9,75 | "
    I la linia 9 del tiquet sera " | Total en retorn: -65 | "
    I la linia 10 del tiquet sera " | Total: -55,25 | Canvi: 55,25 | Pagat en efectiu | "
    I existeix una devolucio del producte "777" de la venda 111
    I existeix una devolucio del producte "222" de la venda 111

  Escenari: Retornar un producte que no he venut
    Quan  inicio una nova venda
    I s'afegeix a la linia de venda 1 unitats del producte amb codi de barres "444"
    I es vol indicar una devolucio de 1 unitats del producte "333" de la venda 111 sense motiu
    I indico que el client paga 25 euros en efectiu
    I es finalitza la venda
    Aleshores el tpv m'indica "El producte introduit no es pot retornar"
    I la linia 8 del tiquet sera " | Total: 25 | Canvi: 0 | Pagat en efectiu | "

  Escenari: Controlar no fer la devolucio d'un producte que ya s'ha retornat
    Quan inicio una nova venda amb id 1010
    I s'afegeix a la linia de venda 1 unitats del producte amb codi de barres "444"
    I es vol indicar una devolucio de 1 unitats del producte "222" de la venda 111 sense motiu
    I indico que el client paga 20 euros en efectiu
    I es finalitza la venda
    I inicio una nova venda amb id 2020
    I s'afegeix a la linia de venda 1 unitats del producte amb codi de barres "333"
    I es vol indicar una devolucio de 1 unitats del producte "222" de la venda 1111 sense motiu
    I indico que el client paga 5 euros en efectiu
    I es finalitza la venda
    Aleshores la venda 1010 conte una devolucio del producte "222" de la venda 111
    I el tpv m'indica "El producte introduit no es pot retornar"
    I la venda 2020 no te assignada cap devolucio
