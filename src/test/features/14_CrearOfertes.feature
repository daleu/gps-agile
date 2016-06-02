#language: ca

#noinspection SpellCheckingInspection
Característica: Crear vals de descompte

  Rerefons:
    Donat existeix el producte "Pilota" amb codi de barres "111" i preu per unitat 1,5
    I existeix el producte "Pala" amb codi de barres "222" i preu per unitat 2
    I existeix el producte "Raqueta" amb codi de barres "333" i preu per unitat 65

  Escenari: Crear oferta NxM a productes
    Quan s'introdueix al sistema les ofertes NxM:
      | 123 | 2 | 1 | 01/06/2016 | 01/07/2016 | 111,222,333|
    Aleshores existeix la oferta amb id 123 en el producte "111"
    I existeix la oferta amb id 123 en el producte "222"
    I existeix la oferta amb id 123 en el producte "333"

  Escenari: Crear ofertes NxM a productes
    Quan s'introdueix al sistema les ofertes NxM:
      | 123 | 2 | 1 | 01/06/2016 | 01/07/2016 | 111,222,333|
      | 456 | 5 | 3 | 01/06/2016 | 01/07/2016 | 111,222|
    Aleshores existeix la oferta amb id 123 en el producte "111"
    I existeix la oferta amb id 123 en el producte "222"
    I existeix la oferta amb id 123 en el producte "333"
    I existeix la oferta amb id 456 en el producte "111"
    I existeix la oferta amb id 456 en el producte "222"

  Escenari: Crear oferta per percentatge a productes
    Quan s'introdueix al sistema les ofertes per percentatge:
      | 123 | 20 | 01/06/2016 | 01/07/2016 | 111,222,333|
    Aleshores existeix la oferta amb id 123 en el producte "111"
    I existeix la oferta amb id 123 en el producte "222"
    I existeix la oferta amb id 123 en el producte "333"

  Escenari: Crear oferta de regal a productes
    Quan s'introdueix al sistema les ofertes de regal:
      | 123 | 111 | 5 | 01/06/2016 | 01/07/2016 | 333|
    Aleshores existeix la oferta amb id 123 en el producte "333"

  Escenari: Crear oferta de regals a productes
    Quan s'introdueix al sistema les ofertes de regal:
      | 123 | 111,222 | 5,1 | 01/06/2016 | 01/07/2016 | 333|
    Aleshores existeix la oferta amb id 123 en el producte "333"

  Escenari: Crear ofertes a productes
    Quan s'introdueix al sistema les ofertes de regal:
      | 123 | 111 | 5 | 01/06/2016 | 01/07/2016 | 333|
    I s'introdueix al sistema les ofertes per percentatge:
      | 234 | 20 | 01/06/2016 | 01/07/2016 | 111,222,333|
    I s'introdueix al sistema les ofertes NxM:
      | 345 | 2 | 1 | 01/06/2016 | 01/07/2016 | 111,222,333|
    Aleshores existeix la oferta amb id 123 en el producte "333"
    I existeix la oferta amb id 234 en el producte "111"
    I existeix la oferta amb id 234 en el producte "222"
    I existeix la oferta amb id 234 en el producte "333"
    I existeix la oferta amb id 345 en el producte "111"
    I existeix la oferta amb id 345 en el producte "222"
    I existeix la oferta amb id 345 en el producte "333"

  Escenari: Llistar ofertes per producte
    Donat s'introdueix al sistema les ofertes de regal:
      | 123 | 111,222 | 5,3 | 01/06/2016 | 01/07/2016 | 333|
    I s'introdueix al sistema les ofertes per percentatge:
      | 234 | 20 | 01/06/2016 | 01/07/2016 | 111|
    I s'introdueix al sistema les ofertes NxM:
      | 345 | 2 | 1 | 01/06/2016 | 01/07/2016 | 111,222|
    Quan demano la llista d'ofertes per producte
    Aleshores obtinc un missatge que diu "Pala | 2x1 | 01/06/2016 | 01/07/2016 || Pilota | 20% | 01/06/2016 | 01/07/2016 | - 2x1 | 01/06/2016 | 01/07/2016 || Raqueta | Regalem: Pilota,Pala | Quantitat: 5,3 | 01/06/2016 | 01/07/2016 |"
