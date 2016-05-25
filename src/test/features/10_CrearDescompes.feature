#language: ca

#noinspection SpellCheckingInspection
Característica: Crear vals de descompte

  Escenari: Crear vals de descompte per import
    Quan s'introdueix al sistema els vals de descompte per import:
      | 10 | 07/10/2020 | 50  |
      | 20 | 23/04/2020 | 100 |
      | 30 | 10/05/2020 | 150 |
    Aleshores existeix el descompte per import amb codi de barres "101"
    I existeix el descompte per import amb codi de barres "102"
    I existeix el descompte per import amb codi de barres "103"

  Escenari: Crear vals de descompte per percentatge
    Quan s'introdueix al sistema els vals de descompte per percentatge:
      | 25 | 07/10/2020 |
      | 50 | 23/04/2020 |
      | 33 | 10/05/2020 |
    Aleshores existeix el descompte per percentatge amb codi de barres "104"
    I existeix el descompte per percentatge amb codi de barres "105"
    I existeix el descompte per percentatge amb codi de barres "106"

  Escenari: LListar vals de descompte
    Donat s'introdueix al sistema els vals de descompte per percentatge:
      | 25 | 07/10/2020 |
      | 33 | 10/05/2020 |
      | 50 | 23/04/2020 |
    I s'introdueix al sistema els vals de descompte per import:
      | 10 | 07/10/2020 | 50  |
      | 20 | 23/04/2020 | 100 |
      | 30 | 10/05/2020 | 150 |
    Quan vull veure els vals de descompte existents
    Aleshores la linia 1 de la llista de Descomptes sera "Descompte de 25.0% | Caduca el 07/10/2020 | Codi de Barres 104"
    I la linia 2 de la llista de Descomptes sera "Descompte de 50.0% | Caduca el 23/04/2020 | Codi de Barres 105"
    I la linia 3 de la llista de Descomptes sera "Descompte de 33.0% | Caduca el 10/05/2020 | Codi de Barres 106"
    I la linia 4 de la llista de Descomptes sera "Descompte de 10.0€ | Import minim de 50.0€ | Caduca el 07/10/2020 | Codi de Barres 101"
    I la linia 5 de la llista de Descomptes sera "Descompte de 20.0€ | Import minim de 100.0€ | Caduca el 23/04/2020 | Codi de Barres 102"
    I la linia 6 de la llista de Descomptes sera "Descompte de 30.0€ | Import minim de 150.0€ | Caduca el 10/05/2020 | Codi de Barres 103"