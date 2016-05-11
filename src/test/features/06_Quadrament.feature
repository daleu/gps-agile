#language: ca

#noinspection SpellCheckingInspection
Característica: Quadrament al finalitzar un torn

  Rerefons:
    Donat existeix el producte "Baldufa" amb codi de barres "1" i preu per unitat 3.50
    I existeix el producte "Cotxe" amb codi de barres "2" i preu per unitat 22.50
    I existeix el producte "Pilota" amb codi de barres "3" i preu per unitat 1.50

  Escenari: Afegir el capital final al demanar el quadrament
    Donat que no hi ha cap venda iniciada
    Quan introdueixo al tpv 524.00 finals
    Aleshores el tpv té 524.00 finals

  Escenari: Acceptem quadrament si la diferència és <= 5€
    Donat que hi ha al tpv 0.00 inicials
    I que hi ha una venda iniciada
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "1"
    I que hi ha una linia de venda amb 1 unitats del producte amb codi de barres "2"
    I que hi ha una linia de venda amb 4 unitats del producte amb codi de barres "3"
    I es finalitza la venda
    I que hi ha una venda iniciada
    I que hi ha una linia de venda amb 1 unitats del producte amb codi de barres "1"
    I que hi ha una linia de venda amb 3 unitats del producte amb codi de barres "3"
    I es finalitza la venda
    Quan introdueixo al tpv 43.50 finals
    I demano el quadrament
    Aleshores obtinc un missatge que diu "Quadrament correcte"

  Escenari: Indiquem quadrament incorrecte si la diferència és > 5€
    Donat que hi ha al tpv 0.00 inicials
    I que hi ha una venda iniciada
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "1"
    I que hi ha una linia de venda amb 1 unitats del producte amb codi de barres "2"
    I que hi ha una linia de venda amb 4 unitats del producte amb codi de barres "3"
    I indico que el client paga 50 euros en efectiu
    I es finalitza la venda
    I que hi ha una venda iniciada
    I que hi ha una linia de venda amb 1 unitats del producte amb codi de barres "1"
    I que hi ha una linia de venda amb 3 unitats del producte amb codi de barres "3"
    I es finalitza la venda
    Quan introdueixo al tpv 100.00 finals
    I demano el quadrament
    Aleshores obtinc un missatge que diu "Quadrament incorrecte"

  Escenari: Introduir capital final més d'un cop
    Quan introdueixo al tpv 1055.20 finals
    I introdueixo al tpv 1000.70 finals
    I introdueixo al tpv 990.50 finals
    Aleshores el tpv té 990.50 finals




