# language: ca

#noinspection SpellCheckingInspection
Característica: Afegir una linia de venda per nom i quantitat

  Rerefons:
    Donat existeix el producte "Baldufa" amb codi de barres "123456789" i preu per unitat 3,50
    Donat existeix el producte "Action Man" amb codi de barres "123456788" i preu per unitat 5
    I que hi ha una venda iniciada

  Escenari: Afegir una linia de venda nova per nom
    Quan introdueixo al tpv 1 producte amb nom "Baldufa"
    Aleshores la venda te 1 linia de venda
    I la linia de venda 1 te per producte "Baldufa"
    I la linia de venda 1 te per preu unitat 3,50
    I la linia de venda 1 te per quantitat 1
    I el preu total de la venda es 3,50

  Escenari: Fusionar linies de venda per codi de barres
    Donat que hi ha una linia de venda amb 1 unitats del producte amb codi de barres "123456789"
    Quan introdueixo al tpv 1 producte amb nom "Baldufa"
    Aleshores la venda te 1 linia de venda
    I la linia de venda 1 te per quantitat 2
    I la linia de venda 1 te per preu unitat 3,50
    I el preu total de la venda es 7,00

  Escenari: Afegir mes d'una linia de venda nova per nom
    Quan introdueixo al tpv 1 producte amb nom "Baldufa"
    Quan introdueixo al tpv 1 producte amb nom "Action Man"
    Quan introdueixo al tpv 1 producte amb nom "Action Man"
    Aleshores la venda te 2 linia de venda
    I la linia de venda 1 te per producte "Baldufa"
    I la linia de venda 1 te per preu unitat 3,50
    I la linia de venda 1 te per quantitat 1
    I la linia de venda 2 te per producte "Action Man"
    I la linia de venda 2 te per preu unitat 5
    I la linia de venda 2 te per quantitat 2
    I el preu total de la venda es 13,5