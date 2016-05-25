#language: ca

#noinspection SpellCheckingInspection
Característica: Crear vals de descompte per percentatge

  Escenari: Crear vals de descompte
    Quan s'introdueix al sistema els vals de descompte per "percentatge":
      | 25 | 07/10/2020 |
      | 50 | 23/04/2020 |
      | 33 | 10/05/2020 |
    Aleshores existeix el descompte del 25%
    I existeix el descompte del 50%
    I existeix el descompte del 33%