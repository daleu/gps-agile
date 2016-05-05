#language: ca

#noinspection SpellCheckingInspection
Característica: Quadrament al finalitzar un torn

  Rerefons:
    Donat existeix el producte "Cotxe" amb codi de barres "111" i preu per unitat 25
    I que hi ha una venda iniciada
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "111"

  Escenari: Introduir diners en caixa 2 cops
    Quan indico que el client paga 50 euros en efectiu
    I indico que el client paga 56 euros en efectiu
    I indico que el client paga 55 euros en efectiu
    Aleshores el valor a retornar al client és de 5