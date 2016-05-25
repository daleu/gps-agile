#language: ca

#noinspection SpellCheckingInspection
Característica: Usar els vals de descomptes en les vendes

  Rerefons:
    Donat existeixen els vals de descompte per percentatge:
    |111 , 25 , 07/10/2020 |
    |222 , 50 , 23/04/2020 |
    |333 , 33 , 10/05/2020 |
    I existeixen els vals de descompte per import:
      | 444 | 10 | 50 | 06/10/2020 |
      | 555 | 20 | 80 | 25/05/2020 |
      | 666 | 30 | 100 | 24/07/2020 |
      | 777 | 5.30 | 20 | 25/06/2000 |
    I existeix el producte "Pin i Pon" amb codi de barres "5353" i preu per unitat 5
    I que estem a dia i hora "25/05/2016 16:04:50"
    # Ultim val caducat

  Escenari: No podem usar un val en una venda buida
    Donat que hi ha una venda iniciada
    Quan uso el val de descompte 111
    Aleshores obtinc un error que diu "El val no es pot usar en una venda buida"

  Escenari: Usar un val de descompte per percentatge en una venda
    Donat que hi ha una venda iniciada
    I s'afegeix a la linia de venda 5 unitats del producte amb codi de barres "5353"
    Quan uso el val de descompte 111
    Aleshores el preu total de la venda es 18.75

  Escenari: Usar un val de descompte per import en una venda
    Donat que hi ha una venda iniciada
    I s'afegeix a la linia de venda 10 unitats del producte amb codi de barres "5353"
    Quan uso el val de descompte 444
    Aleshores el preu total de la venda es 50

  Escenari: Usar un val de descompte i que aquest estigui caducat
    Donat que hi ha una venda iniciada
    I s'afegeix a la linia de venda 5 unitats del producte amb codi de barres "5353"
    Quan uso el val de descompte 777
    Aleshores obtinc un error que diu "Descompte caducat"
    I el preu total de la venda es 25

    Escenari: Usar un val de descompte i que no l'accepti
      Donat que hi ha una venda iniciada
      I s'afegeix a la linia de venda 1 unitats del producte amb codi de barres "5353"
      Quan uso el val de descompte 555
      Aleshores obtinc un error que diu "Import mínim no vàlid"
    
  Escenari: Usar més d'un val de descompte (combina percentatges i imports)
    Donat que hi ha una venda iniciada
    I s'afegeix a la linia de venda 50 unitats del producte amb codi de barres "5353"
    Quan uso el val de descompte 111
    I uso el val de descompte 444
    Aleshores el preu total de la venda es 65
