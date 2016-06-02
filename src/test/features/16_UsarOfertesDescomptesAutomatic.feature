#language: ca

#noinspection SpellCheckingInspection
Característica: Usar vals descompte

  Rerefons:
    Donat existeix el producte "Pilota" amb codi de barres "4212" i preu per unitat 3,5
    I existeix el producte "Pala" amb codi de barres "4213" i preu per unitat 30
    I existeix el producte "Raqueta" amb codi de barres "4214" i preu per unitat 25
    I  s'introdueix al sistema els vals de descompte per import:
      | 10 | 07/10/2020 | 50  |
      | 20 | 23/04/2006 | 100 |
      | 30 | 10/05/2020 | 150 |
    I que estem a dia i hora "08/06/2016 15:00:00"

  Escenari: S'introdueix el descompte automaticament de 50
    Quan que estem a dia i hora "08/06/2016 15:00:00"
    I inicio una nova venda
    I s'afegeix a la linia de venda 3 unitats del producte amb codi de barres "4214"
    I indico que el client paga 70 euros en efectiu
    I es finalitza la venda
    Aleshores la linia 5 del tiquet sera " | Total a pagar: 75 | "
    I la linia 6 del tiquet sera " | Total en retorn: 10 | "
    I la linia 7 del tiquet sera " | Total: 65 | Canvi: 5 | Pagat en efectiu | "

  Escenari: S'introdueix el descompte automaticament de 150
    I inicio una nova venda
    I s'afegeix a la linia de venda 4 unitats del producte amb codi de barres "4214"
    I s'afegeix a la linia de venda 2 unitats del producte amb codi de barres "4213"
    I indico que el client paga 140 euros en efectiu
    I es finalitza la venda
    Aleshores la linia 6 del tiquet sera " | Total a pagar: 160 | "
    I la linia 7 del tiquet sera " | Total en retorn: 30 | "
    I la linia 8 del tiquet sera " | Total: 130 | Canvi: 10 | Pagat en efectiu | "


    Escenari: Les ofertes caducades no s'apliquen automaticament
      I inicio una nova venda
      I s'afegeix a la linia de venda 4 unitats del producte amb codi de barres "4214"
      I indico que el client paga 100 euros en efectiu
      I es finalitza la venda
      Aleshores la linia 5 del tiquet sera " | Total a pagar: 100 | "
      I la linia 6 del tiquet sera " | Total en retorn: 10 | "
      I la linia 7 del tiquet sera " | Total: 90 | Canvi: 10 | Pagat en efectiu | "