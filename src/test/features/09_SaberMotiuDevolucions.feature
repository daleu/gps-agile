# language: ca

  #noinspection SpellCheckingInspection
Característica: Saber motiu de la devolucio
  Rerefons:
    Donat existeix el producte "Barbie Malibu" amb codi de barres "111" i preu per unitat 25
    I existeix el producte "Rasca i pica" amb codi de barres "222" i preu per unitat 15
    I existeix el producte "Krusty, el pallaso que habla" amb codi de barres "333" i preu per unitat 42,3
    I es va fer una venda amb id 444 dels següens productes i seguents unitats
      |111|2|
      |222|1|
      |333|2|

  Escenari: Saber motiu ultima devolucio
    Quan inicio una nova venda
    I es vol indicar una devolucio de 1 unitats del producte "333" de la venda 444 pel motiu "Defectuos"
    I es finalitza la venda
    Aleshores "Defectuos" es el motiu de l'ultima devolucio

  Escenari: El client no vol donar motiu
    Quan inicio una nova venda
    I es vol indicar una devolucio de 2 unitats del producte "111" de la venda 444 sense motiu
    I es finalitza la venda
    Aleshores "No existeix motiu" es el motiu de l'ultima devolucio

