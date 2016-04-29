# language: ca

#noinspection SpellCheckingInspection
Característica: Afegir una linia de venda per codi de barres
  Rerefons:
    Donat que hi ha una venda iniciada
    I existeix el producte "Baldufa" amb codi de barres "123456789" i preu per unitat 3.50

  Escenari: Afegir una línia de venda nova per codi de barres
    Quan passo pel tpv el codi de barres "123456789"
    Aleshores la venda te una linia de venda
    I la linia de venda 1 te per producte "Baldufa"
    I la linia de venda 1 te per preu unitat 3.50
    I la linia de venda 1 te per quantitat 1
    I el preu total de la venda es 3.50

