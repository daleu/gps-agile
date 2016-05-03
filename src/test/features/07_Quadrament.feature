#language: ca

#noinspection SpellCheckingInspection
Característica: Quadrament al finalitzar un torn

  Rerefons:
    Donat existeix el producte "Baldufa" amb codi de barres "1" i preu per unitat 3.50
    Donat existeix el producte "Cotxe" amb codi de barres "2" i preu per unitat 22.50
    Donat existeix el producte "Pilota" amb codi de barres "3" i preu per unitat 1.50

  Escenari: Finalitzar un quadrament correctament
    Quan introdueixo al tpv 0.00 inicials
    I inicio una venda
    I s'afegeix a la linia de venda 2 unitats del producte amb codi de barres "1"
    I s'afegeix a la linia de venda 1 unitats del producte amb codi de barres "2"
    I s'afegeix a la linia de venda 4 unitats del producte amb codi de barres "3"
    I es finalitza la venda
    I inicio una venda
    I s'afegeix a la linia de venda 1 unitats del producte amb codi de barres "1"
    I s'afegeix a la linia de venda 0 unitats del producte amb codi de barres "2"
    I s'afegeix a la linia de venda 3 unitats del producte amb codi de barres "3"
    I es finalitza la venda
    I introdueixo al tpv 43.50 finals
    Aleshores obtinc un missatge que diu "Quadrament correcte"

  Escenari: Finalitzar un quadrament incorrectament
    Quan introdueixo al tpv 50.00 inicials
    I inicio una venda
    I s'afegeix a la linia de venda 2 unitats del producte amb codi de barres "1"
    I s'afegeix a la linia de venda 1 unitats del producte amb codi de barres "2"
    I s'afegeix a la linia de venda 4 unitats del producte amb codi de barres "3"
    I es finalitza la venda
    I inicio una venda
    I s'afegeix a la linia de venda 1 unitats del producte amb codi de barres "1"
    I s'afegeix a la linia de venda 0 unitats del producte amb codi de barres "2"
    I s'afegeix a la linia de venda 3 unitats del producte amb codi de barres "3"
    I es finalitza la venda
    I introdueixo al tpv 100.50 finals
    Aleshores obtinc un missatge que diu "Quadrament incorrecte"

