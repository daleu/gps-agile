# language: ca

  #noinspection SpellCheckingInspection
Característica: Retornar un producte mentres es fa una venda
  Rerefons:
    Donat existeix el producte "Plastilina de 20 colors" amb codi de barres "444" i preu per unitat 25
    I existeix el producte "Action Man" amb codi de barres "777" i preu per unitat 15
    I existeix el producte "Baldufa" amb codi de barres "333" i preu per unitat 3,25
    I existeix el producte "Cotxe teledirigit" amb codi de barres "222" i preu per unitat 25
    I el TPV esta a la botiga "Girona"
    I el "Johnny Depp" ha iniciat un torn
    I que hi ha al tpv 300,00 d'efectiu inicial
    I es va fer una venda amb id 111 dels següens productes i seguents unitats
      |777|2|
      |222|1|
    I es finalitza la venda

  Escenari: Retorno un producte en una venda amb una linea de venda i el resultat de la venda es positiu
    Quan inicio una nova venda
    I s'afegeix a la linia de venda 3 unitats del producte amb codi de barres "333"
    I s'afegeix a la linia de venda 1 unitats del producte amb codi de barres "222"
    I es vol indicar una devolucio de 1 unitats del producte "777" de la venda 111 sense motiu
    I indico que el client vol pagar amb tarjeta
    I indico que el client paga 19,75 euros amb tarjeta
    I es finalitza la venda
    Aleshores la linia 5 del tiquet sera " | Devolucio(ns): | "
    I la linia 6 del tiquet sera " | 1 | Action Man | P.u 15 | P.l 15 | "
    I la linia 8 del tiquet sera " | Total a pagar: 34,75 | "
    I la linia 9 del tiquet sera " | Total en retorn: -15 | "
    I la linia 10 del tiquet sera " | Total: 19,75 | Canvi: 0 | Pagat amb tarjeta | "
    I existeix una devolucio del producte "777" de la venda 111

  Escenari: Retorno un producte en una venda amb una lina de venda i el resultat de la venda es negatiu
    # S'han de poder retornar diners a la targeta
    Quan inicio una nova venda
    I s'afegeix a la linia de venda 3 unitats del producte amb codi de barres "333"
    I es vol indicar una devolucio de 1 unitats del producte "777" de la venda 111 sense motiu
    I es vol indicar una devolucio de 2 unitats del producte "222" de la venda 111 sense motiu
    I indico que el client vol pagar amb tarjeta
    I indico que el client paga 0 euros amb tarjeta
    I es finalitza la venda
    Aleshores obtinc un missatge que diu "Error: El resultat de la venda es negatiu, i hauria de ser positiu"