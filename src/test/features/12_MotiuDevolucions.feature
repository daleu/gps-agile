# language: ca

  #noinspection SpellCheckingInspection
Característica: Retornar un producte mentres es fa una venda
  Rerefons:
    Donat existeix el producte "Plastilina de 20 colors" amb codi de barres "444" i preu per unitat 25
    I existeix el producte "Action Man" amb codi de barres "777" i preu per unitat 15
    I existeix el producte "Baldufa" amb codi de barres "333" i preu per unitat 3,25
    I existeix el producte "Cotxe teledirigit" amb codi de barres "222" i preu per unitat 25
    I el TPV esta a la botiga "Girona"
    I el "Manolet" ha iniciat un torn
    I que hi ha al tpv 300,00 d'efectiu inicial
    I es va fer una venda amb id 111 dels següens productes i seguents unitats
    |777|2|
    |222|1|
    I es finalitza la venda

  Escenari: Saber motiu ultima devolucio
    Quan inicio una nova venda
    I es vol indicar una devolucio de 1 unitats del producte "222" de la venda 111 pel motiu "Defectuos"
    I es finalitza la venda
    Aleshores "Defectuos" es el motiu de l'ultima devolucio

  Escenari: El client no vol donar motiu de la devolucio
    Quan inicio una nova venda
    I es vol indicar una devolucio de 2 unitats del producte "777" de la venda 111 sense motiu
    I es finalitza la venda
    Aleshores "No existeix motiu" es el motiu de l'ultima devolucio
