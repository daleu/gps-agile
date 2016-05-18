#language: ca

#noinspection SpellCheckingInspection
Característica: Visualitzar quadraments

  Rerefons:
    Donat existeix el producte "Baldufa" amb codi de barres "1" i preu per unitat 3,50
    I existeix el producte "Cotxe" amb codi de barres "2" i preu per unitat 22,50
    I existeix el producte "Pilota" amb codi de barres "3" i preu per unitat 1,50
    I el TPV esta a la botiga "Carroça"
    I que no hi ha cap torn iniciat

  Escenari: Visualitzar quadraments
    Donat el "Joan" ha iniciat un torn
    I que hi ha al tpv 0,00  d'efectiu inicial
    I que hi ha una venda iniciada
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "1"
    I que hi ha una linia de venda amb 1 unitats del producte amb codi de barres "2"
    I que hi ha una linia de venda amb 4 unitats del producte amb codi de barres "3"
    I es finalitza la venda
    I que hi ha una venda iniciada
    I que hi ha una linia de venda amb 1 unitats del producte amb codi de barres "1"
    I que hi ha una linia de venda amb 3 unitats del producte amb codi de barres "3"
    I es finalitza la venda
    I finalitzo el torn
    #total venut al torn 43,50€
    I introdueixo al tpv 50,00 d'efectiu final per fer el quadrament
    I accepto el desquadrament
    I el "Ernest" ha iniciat un torn
    I que hi ha al tpv 43,50  d'efectiu inicial
    I que hi ha una venda iniciada
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "1"
    I que hi ha una linia de venda amb 1 unitats del producte amb codi de barres "2"
    I que hi ha una linia de venda amb 4 unitats del producte amb codi de barres "3"
    I es finalitza la venda
    I que hi ha una venda iniciada
    I que hi ha una linia de venda amb 1 unitats del producte amb codi de barres "1"
    I que hi ha una linia de venda amb 3 unitats del producte amb codi de barres "3"
    I es finalitza la venda
    I finalitzo el torn
    #total venut al torn 43,50€
    I introdueixo al tpv 80,00 d'efectiu final per fer el quadrament
    I accepto el desquadrament
    Quan indico que vull visualitzar els desquadraments
    Aleshores obtinc 2 linies
    I la linia 1 sera "TORN 1: NomEmpleat: Joan | Botiga: Carroça | EfectiuInicial: 0.0 | EfectiuFinal: 50.0 | Diferencia: 6.5"
    I la linia 2 sera "TORN 2: NomEmpleat: Ernest | Botiga: Carroça | EfectiuInicial: 43.5 | EfectiuFinal: 80.0 | Diferencia: -7.0"