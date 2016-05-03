#language: ca

#noinspection SpellCheckingInspection
Característica: Quadrament al finalitzar un torn

  Escenari: Finalitzar un quadrament correctament
    Quan inicio una venda
    Aleshores la venda te per identificador 1
    I la venda no te linies de venda
    I el preu total de la venda es 0.00

  Escenari: Finalitzar un quadrament incorrectament
    Quan introdueixo al tpv 
    Aleshores obtinc un error que diu "Error: Ja hi ha una venda iniciada"

  Escenari: Tancar una venda sense tenir una venda iniciada
    Donat que no hi ha cap venda iniciada
    Quan tanco una venda sense tenir una venda iniciada
    Aleshores obtinc un error que diu "Error: No hi ha cap venda iniciada"
