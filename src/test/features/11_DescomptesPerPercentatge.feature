#language: ca

#noinspection SpellCheckingInspection
Característica: Crear vals de descompte per percentatge

  Escenari: Crear vals de descompte
    Quan creo els vals de descompte:
    |25|07/10/2020|
    |50|23/04/2020|
    |33|10/05/2020|
    Aleshores existeix el descompte del 25%
    I existeix el descompte del 50%
    I existeix el descompte del 33%

  Escenari: LListar vals de descompte
    Donat creo els vals de descompte:
      |25|07/10/2020|
      |50|23/04/2020|
      |33|10/05/2020|
    Quan vull veure els vals de descompte existents
    Aleshores la linia 1 de la llista de Descomptes sera "Descompte de 33.0% | Caduca el 10/05/2020"
    I la linia 2 de la llista de Descomptes sera "Descompte de 25.0% | Caduca el 07/10/2020"
    I la linia 3 de la llista de Descomptes sera "Descompte de 50.0% | Caduca el 23/04/2020"