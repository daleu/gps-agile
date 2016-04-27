# language: ca

#noinspection SpellCheckingInspection
Característica: Afegir una línia de venda

  Rerefons:
    Donat que hi ha una venda iniciada

  Escenari: Afegir una linia de venda
  Quan afegeixo una linia de venda amb nom de producte "Baldufa", amb preu 5 i amb quantitat 1
  Aleshores la ultima linia de venda te nom de producte "Baldufa", preu 5 i quantitat 1

  Escenari: Afegir una linia de venda 2
  Quan afegeixo una linia de venda amb nom de producte "Cotxe", amb preu 3,50 i amb quantitat 1
  Aleshores la ultima linia de venda te nom de producte "Cotxe", preu 3,50 i quantitat 1

  Escenari: Afegir una linia de venda 3
  I hi ha una linia de venda amb nom de producte "Cotxe", amb preu 3,50 i amb quantitat 1
  Quan afegeixo una altra linia de venda amb nom de producte "Cotxe", amb preu 3,50 i amb quantitat 1
  Aleshores la ultima linia de venda te nom de producte "Cotxe", preu 7 i quantitat 2

  Escenari: Demanar el total d'una linia
  I afegeixo una linia de venda amb nom de producte "Baldufa", amb preu 5 i amb quantitat 3
  Quan demano el preu total de la ultima linia de venda
  Aleshores la ultima linia de venda te preu total 15

  Escenari: Demanar el total d'una linia 2
  I afegeixo una linia de venda amb nom de producte "Cotxe", amb preu 3,50 i amb quantitat 3
  Quan demano el preu total de la ultima linia de venda
  Aleshores la ultima linia de venda te preu total 10,5