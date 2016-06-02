#language: ca

#noinspection SpellCheckingInspection
Característica: Usar vals descompte

  Rerefons:
    Donat existeix el producte "Pilota" amb codi de barres "4212" i preu per unitat 3,5
    I existeix el producte "Pala" amb codi de barres "4213" i preu per unitat 30
    I existeix el producte "Raqueta" amb codi de barres "4214" i preu per unitat 25
    I  s'introdueix al sistema els vals de descompte per import:
      | 10 | 07/10/2020 | 50  |
      | 20 | 23/04/2020 | 100 |
      | 30 | 10/05/2020 | 150 |

  Escenari: S'introdueix el descompte automaticament de 50
    Quan que estem a dia i hora "08/06/2016 15:00"
    I inicio una nova venda
    I s'afegeix a la linia de venda 3 unitats del producte amb codi de barres "4214"
    I es finalitza la venda
    Aleshores el preu total de la venda es 65

  Escenari: S'introdueix el descompte automaticament de 150
    Quan que estem a dia i hora "08/06/2016 15:00"
    I inicio una nova venda
    I s'afegeix a la linia de venda 4 unitats del producte amb codi de barres "4214"
    I s'afegeix a la linia de venda 2 unitats del producte amb codi de barres "4213"
    I es finalitza la venda
    Aleshores el preu total de la venda es 130
