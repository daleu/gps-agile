# language: ca

#noinspection SpellCheckingInspection
Característica: Afegir una linia de venda per nom

  Rerefons:
    Donat que hi ha una venda iniciada
    I existeix el producte "Baldufa" amb codi de barres "123456789" i preu per unitat 3.50

  Escenari: Afegir una linia de venda nova per nom
    Quan introdueixo al tpv el producte per nom "Baldufa"
    Aleshores la venda te una linia de venda
