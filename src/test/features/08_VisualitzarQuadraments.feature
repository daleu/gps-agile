#language: ca

#noinspection SpellCheckingInspection
Característica: Visualitzar quadraments

  Rerefons:
    Donat existeix el producte "Baldufa" amb codi de barres "1" i preu per unitat 3.50
    I existeix el producte "Cotxe" amb codi de barres "2" i preu per unitat 22.50
    I existeix el producte "Pilota" amb codi de barres "3" i preu per unitat 1.50

  Escenari: Visualitzar quadraments
    Donat el TPV esta a la botiga "Carroça"
    I que no hi ha cap torn iniciat
    I el "Joan" ha iniciat un torn
    I que hi ha al tpv 222,30 inicials
    I que hi ha una venda iniciada
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "1"
    I que hi ha una linia de venda amb 1 unitats del producte amb codi de barres "2"
    I que hi ha una linia de venda amb 4 unitats del producte amb codi de barres "3"
    I es finalitza la venda
    I que hi ha una venda iniciada
    I que hi ha una linia de venda amb 1 unitats del producte amb codi de barres "1"
    I que hi ha una linia de venda amb 3 unitats del producte amb codi de barres "3"
    I es finalitza la venda
    I introdueixo al tpv 266,8 finals
    I finalitzo el torn
    I el "Ernest" ha iniciat un torn
    I que hi ha al tpv 266,8 inicials
    I que hi ha una venda iniciada
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "1"
    I que hi ha una linia de venda amb 1 unitats del producte amb codi de barres "2"
    I que hi ha una linia de venda amb 4 unitats del producte amb codi de barres "3"
    I es finalitza la venda
    I que hi ha una venda iniciada
    I que hi ha una linia de venda amb 1 unitats del producte amb codi de barres "1"
    I que hi ha una linia de venda amb 3 unitats del producte amb codi de barres "3"
    I es finalitza la venda
    I introdueixo al tpv 311,3 finals
    I finalitzo el torn
    Quan indico que vull visualitzar els quadraments
    Aleshores obtinc 2 linies
    I la linia 1 sera "TORN 1:  EfectiuInicial: 222,3 | EfectiuFinal: 266,8 | Numero de Vendes: 2 | Vendes: 1,2"
    I la linia 2 sera "TORN 2:  EfectiuInicial: 266,8 | EfectiuFinal: 311,3 | Numero de Vendes: 2 | Vendes: 3,4"