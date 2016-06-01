#language: ca

#noinspection SpellCheckingInspection
Característica: Crear vals de descompte

  Rerefons:
    Donat existeix el producte "Pilota" amb codi de barres "111" i preu per unitat 5
    Donat existeix el producte "Pala" amb codi de barres "222" i preu per unitat 2
    Donat existeix el producte "Raqueta" amb codi de barres "333" i preu per unitat 10

  Escenari: Crear ofertes NxM a productes
    Quan s'introdueix al sistema les ofertes NxM:
      | 123 | 2 | 1 | 01/06/2016 | 01/07/2016 | 111,222,333|
    Aleshores existeix la oferta NxM amb id 123 en el producte "111"
    I existeix la oferta NxM amb id 123 en el producte "222"
    I existeix la oferta NxM amb id 123 en el producte "333"