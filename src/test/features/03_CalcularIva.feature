# language: ca

#noinspection SpellCheckingInspection
Característica: Calcular IVA

  Escenari: Incloure IVA del 21% a producte
    Donat que en "Joan" ha iniciat el torn al "tv1" de "Girona"
    I que hi ha una venda iniciada
    I afegeixo una linia de venda amb nom de producte "Baldufa guai", amb preu 5, amb quantitat 3 i IVA 21
    Quan demano el preu total amb iva de la linia de venda
    Aleshores la linia de venda te preu total 18,15

  Escenari: Incloure IVA del 4% a producte
    Donat que en "Joan" ha iniciat el torn al "tv1" de "Girona"
    I que hi ha una venda iniciada
    I afegeixo una linia de venda amb nom de producte "Llaminadura", amb preu 1, amb quantitat 2 i IVA 4
    Quan demano el preu total amb iva de la linia de venda
    Aleshores la linia de venda te preu total 2,08

  Escenari: Incloure IVA de diferents productes amb IVA diferents
    Donat que en "Joan" ha iniciat el torn al "tv1" de "Girona"
    I que hi ha una venda iniciada
    I afegeixo una linia de venda amb nom de producte "Llaminadura", amb preu 1, amb quantitat 2 i IVA 4
    I afegeixo una linia de venda amb nom de producte "Baldufa guai", amb preu 5, amb quantitat 3 i IVA 21
    Quan demano el preu total amb iva de la linia de venda
    Aleshores la linia de venda te preu total 20,23