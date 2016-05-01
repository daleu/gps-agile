# language: ca

  #noinspection SpellCheckingInspection
Característica: Retornar un producte
  Rerefons:
    Donat existeix el producte "Plastilina de 20 colors" amb codi de barres "444" i preu per unitat 25
    I existeix el producte "Action Man" amb codi de barres "777" i preu per unitat 15
    I existeix el producte "Baldufa" amb codi de barres "333" i preu per unitat 3,25
    I existeix el producte "Cotxe telefirigit" amb codi de barres "222" i preu per unitat 25
    I es va fer una venda amb el codi "111" amb 2 productes amb codi "777" i 1 producte amb codi "333"
    I que hi ha una venda començada
    I s'afegeix a la linia de venda 3 unitats del producte amb codi de barres "333"
    I s'afegeix a la linia de venda 1 unitats del producte amb codi de barres "222"

  Escenari: Retornar un sol producte amb saldo positiu
    Quan vull retornar 1 unitat del producte amb codi "777" de la venda "GA22"
    Aleshores comprobo que la venda existeix
    I el producte es va vendre en aquella venda
    I indico la devolucio del producte "777" pel motiu "Producte defectuós"
    I la linia venda inserta el producte en devolució en negatiu
    I El preu final de la venda ha de ser la suma dels productes meny la suma de les devolucions.