#language: ca

#noinspection SpellCheckingInspection
Característica: Iniciar venda

  Escenari: Iniciar una venda
    Quan inicio una venda
    Aleshores la venda te per identificador 1
    I la venda no te linies de venda
    I el preu total de la venda es 0.00

  Escenari: Iniciar una venda quan ja n'hi ha una iniciada
    Donat que hi ha una venda iniciada
    Quan inicio una venda
    Aleshores obtinc un error que diu "Error: Ja hi ha una venda iniciada"

