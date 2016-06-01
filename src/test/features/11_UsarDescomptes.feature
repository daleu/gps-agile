#language: ca

#noinspection SpellCheckingInspection
Característica: Usar els vals de descomptes en les vendes

  Rerefons:
    Donat existeixen els vals de descompte per percentatge amb id:
    |111 | 25 | 07/10/2020 |
    |222 | 50 | 23/04/2020 |
    |333 | 33 | 10/05/2020 |

    I existeixen els vals de descompte per import amb id:
      | 444 | 10 | 50 | 06/10/2020 |
      | 555 | 20 | 80 | 25/05/2020 |
      | 666 | 30 | 100 | 24/07/2020 |
      | 777 | 5.30 | 20 | 25/06/2000 |
    I existeix el producte "Pin i Pon" amb codi de barres "5353" i preu per unitat 5 i iva 0,21
    I que estem a dia i hora "25/05/2016 16:04:50"
    # Ultim val caducat

  Escenari: No podem usar un val en una venda buida
    Donat que hi ha una venda iniciada
    Quan uso el val de descompte 111
    Aleshores obtinc un missatge que diu "El val no es pot usar en una venda buida"

  Escenari: Usar un val de descompte per percentatge en una venda
    Donat que hi ha una venda iniciada
    I s'afegeix a la linia de venda 5 unitats del producte amb codi de barres "5353"
    Quan uso el val de descompte 111
    Aleshores el preu total de la venda es 18,75

  Escenari: Usar un val de descompte per import en una venda
    Donat que hi ha una venda iniciada
    I s'afegeix a la linia de venda 10 unitats del producte amb codi de barres "5353"
    Quan uso el val de descompte 444
    Aleshores el preu total de la venda es 40

  Escenari: Usar un val de descompte i que aquest estigui caducat
    Donat que hi ha una venda iniciada
    I s'afegeix a la linia de venda 5 unitats del producte amb codi de barres "5353"
    Quan uso el val de descompte 777
    Aleshores obtinc un missatge que diu "Descompte caducat"
    I el preu total de la venda es 25

    Escenari: Usar un val de descompte i que no l'accepti
      Donat que hi ha una venda iniciada
      I s'afegeix a la linia de venda 1 unitats del producte amb codi de barres "5353"
      Quan uso el val de descompte 555
      Aleshores obtinc un missatge que diu "Import mínim no vàlid"
    
  Escenari: Usar més d'un val de descompte (combinar percentatges i imports)
    Donat que hi ha una venda iniciada
    I s'afegeix a la linia de venda 50 unitats del producte amb codi de barres "5353"
    Quan uso el val de descompte 111
    I uso el val de descompte 444
    Aleshores el preu total de la venda es 177,5

  Escenari: Anular un descompte ja introduït
    Donat que hi ha una venda iniciada
    I s'afegeix a la linia de venda 2 unitats del producte amb codi de barres "5353"
    I uso el val de descompte 111
    I uso el val de descompte 222
    Quan anulo l'us del val de descompte 111
    Aleshores el preu total de la venda es 5

  Escenari: Anular un descompte no introduït
    Donat que hi ha una venda iniciada
    I s'afegeix a la linia de venda 2 unitats del producte amb codi de barres "5353"
    I uso el val de descompte 111
    I uso el val de descompte 222
    Quan anulo l'us del val de descompte 333
    Aleshores el preu total de la venda es 3,75

  Escenari: Usar més d'un val de descompte (combinar percentatges)
    Donat que hi ha una venda iniciada
    I s'afegeix a la linia de venda 50 unitats del producte amb codi de barres "5353"
    Quan uso el val de descompte 111
    I uso el val de descompte 222
    Aleshores el preu total de la venda es 93,75

  Escenari: Usar més d'un val de descompte (combinar imports i es pot)
    Donat que hi ha una venda iniciada
    I s'afegeix a la linia de venda 50 unitats del producte amb codi de barres "5353"
    Quan uso el val de descompte 555
    I uso el val de descompte 444
    Aleshores el preu total de la venda es 220

  Escenari: Usar més d'un val de descompte (combinar imports i no es pot)
    #el descompte 555 no es pot aplicar
    Donat que hi ha una venda iniciada
    I s'afegeix a la linia de venda 12 unitats del producte amb codi de barres "5353"
    Quan uso el val de descompte 444
    I uso el val de descompte 555
    Aleshores el preu total de la venda es 50

  Escenari: Usar més d'un val de descompte, anular l'ordre i tornar-los a introduir
    Donat que hi ha una venda iniciada
    I s'afegeix a la linia de venda 20 unitats del producte amb codi de barres "5353"
    I uso el val de descompte 444
    I uso el val de descompte 555
    I anulo l'us del val de descompte 444
    Quan uso el val de descompte 555
    Aleshores el preu total de la venda es 80

  Escenari: Venda completa amb descomptes i devolucions
    Donat el TPV esta a la botiga "Carroça"
    I es va fer una venda amb id 111 dels següens productes i seguents unitats
      |5353|20|
    I es vol indicar una devolucio de 5 unitats del producte "5353" de la venda 111 sense motiu
    I uso el val de descompte 444
    I l'empleat que ha iniciat la venda es diu "Joan"
    I que estem a dia i hora "21/02/2016 20:24:10"
    Quan indico que el client paga 100 euros en efectiu
    I es finalitza la venda
    Aleshores els diners que es retornen per la devolucio son -25
    I el preu total de la venda es 65
    I el valor a retornar al client és de 35