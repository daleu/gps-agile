# language: ca

#noinspection SpellCheckingInspection
Característica: Finalitzar venda

  Rerefons:
    Donat que hi ha una venda iniciada

  Escenari: Crear venda buida
    Quan inicio una venda nova
    Aleshores no hi ha linies de venda

   Escenari: Afegir una linia de venda
     Donat que hi ha una venda
     Quan afegeixo una linia de venda amb nom de producte "Baldufa", amb preu 5 i amb quantitat 1
     Aleshores la ultima linia de venda te nom de producte "Baldufa", preu 5 i quantitat 1
