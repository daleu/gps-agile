# language: ca

#noinspection SpellCheckingInspection
Característica: Iniciar venda

  Escenari: Crear venda
    Quan inicio una venda nova
    Aleshores no hi ha linies de venda
    I preu total 0
    I no hi ha linies de venda

  Escenari: No es pot iniciar una venta si ja hi ha una venta iniciada
    Donat que hi ha una venda iniciada
    Quan inicio una venda nova
    Aleshores obtinc un error que diu: "Ja hi ha una venda iniciada"