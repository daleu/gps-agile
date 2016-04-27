# language: ca

#noinspection SpellCheckingInspection
Característica: Iniciar venda

  Escenari: Crear venda
    Quan inicio una venda nova
    Aleshores no hi ha linies de venda

  Escenari: No es pot iniciar una venta si ja hi ha una venta iniciada
    Donat que hi ha una venda iniciada
    Quan inicio una venda nova
    Aleshores obtinc un error que diu: "Ja hi ha una venda iniciada"

  Escenari: Afegir una linia de venda
     Donat que hi ha una venda iniciada
     Quan afegeixo una linia de venda amb nom de producte "Baldufa", amb preu 5 i amb quantitat 1
     Aleshores la ultima linia de venda te nom de producte "Baldufa", preu 5 i quantitat 1

  Escenari: Afegir una linia de venda 2
    Donat que hi ha una venda iniciada
    Quan afegeixo una linia de venda amb nom de producte "Cotxe", amb preu 3,50 i amb quantitat 1
    Aleshores la ultima linia de venda te nom de producte "Cotxe", preu 3,50 i quantitat 1
  
  Escenari: Demanar el total d'una linia
    Donat que hi ha una venda iniciada
    I afegeixo una linia de venda amb nom de producte "Baldufa", amb preu 5 i amb quantitat 3
    Quan demano el preu total de la ultima linia de venda
    Aleshores la ultima linia de venda te preu total 15

  Escenari: Demanar el total d'una linia 2
    Donat que hi ha una venda iniciada
    I afegeixo una linia de venda amb nom de producte "Cotxe", amb preu 3,50 i amb quantitat 3
    Quan demano el preu total de la ultima linia de venda
    Aleshores la ultima linia de venda te preu total 10,5