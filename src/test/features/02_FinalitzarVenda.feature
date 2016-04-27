# language: ca

#noinspection SpellCheckingInspection
Característica: Finalitzar venda

  Rerefons:
    Donat que hi ha una venda iniciada

  Escenari: Finalitzar una venda buida
    Quan anul·lo una venda
    Aleshores obtinc un missatge que diu "Venda anul·lada"

  Escenari: Finalitzar una venda amb una linia de venda
    Donat que hi ha una linia de venda amb nom de producte "Baldufa", amb preu 5 i amb quantitat 2
    Quan finalitzo una venda
    I el client paga 15
    Aleshores obtinc un missatge que diu "Venda finalitzada"
    I preu total 10
    I canvi 5

  Escenari: Finalitzar una venda amb més d'una linia de venda
    Donat que hi ha una linia de venda amb nom de producte "Baldufa", amb preu 5 i amb quantitat 2
    I que hi ha una linia de venda amb nom de producte "Cotxe", amb preu 3,5 i amb quantitat 3
    I que hi ha una linia de venda amb nom de producte "Pilota", amb preu 7,25 i amb quantitat 1
    I que hi ha una linia de venda amb nom de producte "Peluix", amb preu 2,75 i amb quantitat 4
    Quan finalitzo una venda
    I el client paga 50
    Aleshores obtinc un missatge que diu "Venda finalitzada"
    I preu total 38,75
    I canvi 11,25

  Escenari: Anul·lar una venda en curs
    Donat que hi ha una linia de venda amb nom de producte "Baldufa", amb preu 5 i amb quantitat 2
    I que hi ha una linia de venda amb nom de producte "Cotxe", amb preu 3,5 i amb quantitat 3
    I que hi ha una linia de venda amb nom de producte "Pilota", amb preu 7,25 i amb quantitat 1
    I que hi ha una linia de venda amb nom de producte "Peluix", amb preu 2,75 i amb quantitat 4
    Quan anul·lo una venda
    Aleshores obtinc un missatge que diu "Venda anul·lada"

  Escenari: Impressió de ticket
    Donat que hi ha una linia de venda amb nom de producte "Baldufa", amb preu 5 i amb quantitat 2
    I que hi ha una linia de venda amb nom de producte "Cotxe", amb preu 3,5 i amb quantitat 3
    I que hi ha una linia de venda amb nom de producte "Pilota", amb preu 7,25 i amb quantitat 1
    I que hi ha una linia de venda amb nom de producte "Peluix", amb preu 2,75 i amb quantitat 4
    Quan finalitzo una venda
    I el client paga 50
    Aleshores obtinc un missatge que diu "Venda finalitzada"
    I obtinc un ticket que diu "2|Baldufa|5,00|10,00 - 3|Cotxe|3,50|10,50 - 1|Pilota|7,25|7,25 - 4|Peluix|2,75|11,00 - Preu total: 38,75 - Pagament: 50,00 - Canvi: 11,25"

