#language: ca

#noinspection SpellCheckingInspection
Característica: Usar els vals de descomptes en les vendes

  Rerefons:
    Donat existeixen els vals de descompte per percentatge:
      | 111 | 25 | 07/10/2020 |
      | 222 | 50 | 23/04/2020 |
      | 333 | 33 | 10/05/2020 |
    I existeixen els vals de descompte per import:
      | 444 | 10 | 50 | 06/10/2020 |
      | 555 | 20 | 80 | 25/05/2020 |
      | 666 | 30 | 100 | 24/07/2020 |
      | 777 | 5.30 | 20 | 25/06/2000 |
    # Ultim val caducat

  Escenari: No podem usar un val en una venda buida
    Donat que hi ha una venda iniciada
    Quan uso el val de descompte 1
    Aleshores obtinc un error que diu "El val no es pot usar en una venda buida"

  Escenari: Usar un val de descompte per percentatge en una venda
    Donat que hi ha una venda iniciada
    I que el total de la venda es 25
    Quan paso el descompte 111 per tpv
    Aleshores el preu total de la venda es 18.75

  Escenari: Usar un val de descompte per import en una venda
    Donat que hi ha una venda iniciada
    I que el total de la venda es 60
    Quan paso el descompte 444 pel tpv
    Aleshores el preu total de la venda es 50

  Escenari: Usar un val de descompte i que aquest estigui caducat
    Donat que hi ha una venda iniciada
    I que el total de la venda 25
    Quan passo el descompte 777
    Aleshores obtinc un error que diu "El val de descompte està caducat"
    I el preu total de la venda es 25

    Escenari: Usar un val de descompte i que no l'accepti
      Donat que hi ha una venda iniciada
      I que el total de la venda és 20
      Quan passo el descompte 555
      Aleshores obtinc un error que diu "El val de descompte no es pot usar"
    
  Escenari: Usar més d'un val de descompte (combina percentatges i imports)
    Donat que hi ha una venda iniciada
    I que el total de la venda es 100
    Quan passo el descompte 111
    I passo el descompte 444
    Aleshores el preu total de la venda es 65
