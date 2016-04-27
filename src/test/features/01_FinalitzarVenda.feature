# language: ca

#noinspection SpellCheckingInspection
Característica: Finalitzar venda

  Rerefons:
    Donat que hi ha una venda iniciada

  Escenari: Finalitzar una venda buida
    Quan finalitzo una venda
    Aleshores obtinc un missatge que diu "Venda anul·lada"

  Escenari: Finalitzar una venda amb una linia de venda
    Donat que hi ha una linia de venda amb nom de producte "Baldufa", amb preu 5 i amb quantitat 2
    Quan finalitzo una venda
    Aleshores obtinc un missatge que diu "Venda finalitzada"
    I preu total 10
