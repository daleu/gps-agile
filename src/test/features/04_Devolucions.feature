# language: ca

  #noinspection SpellCheckingInspection
Característica: Retornar un producte
  Rerefons:
    Donat existeix el producte "Plastilina de 20 colors" amb codi de barres "444" i preu per unitat 25
    I existeix el producte "Action Man" amb codi de barres "777" i preu per unitat 15
    I existeix el producte "Baldufa" amb codi de barres "333" i preu per unitat 3,25
    I existeix el producte "Cotxe telefirigit" amb codi de barres "222" i preu per unitat 25
    I es va fer una venda amb id 111 dels següens productes i seguents unitats
    |777|2|
    |222|1|

  Escenari: Retornar un sol producte amb saldo positu
  Quan inicio una nova venda
  I s'afegeix a la linia de venda 3 unitats del producte amb codi de barres "333"
  I s'afegeix a la linia de venda 1 unitats del producte amb codi de barres "222"
  I es vol indicar una devolucio de 1 unitats del producte "777" de la venda 111 pel motiu "Defectuos"
  Aleshores existeix una devolucio del producte "777" de la venda 111 pel motiu "Defectuos"
  I el preu total es la suma dels productes a vendre menys el de la devolució, es a dir, 19,75

  Escenari: Retornar dos productes amb saldo negatius
    Quan inicio una nova venda
    I s'afegeix a la linia de venda 3 unitats del producte amb codi de barres "333"
    I es vol indicar una devolucio de 1 unitats del producte "777" de la venda 111 pel motiu "Defectuos"
    I es vol indicar una devolucio de 1 unitats del producte "222" de la venda 111 pel motiu "No interesant"
    Aleshores existeix una devolucio del producte "222" de la venda 111 pel motiu "No interesant"
    I el preu total es la suma dels productes a vendre menys el de la devolució, es a dir, -30,25

