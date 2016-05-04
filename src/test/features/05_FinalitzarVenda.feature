# language: ca

#noinspection SpellCheckingInspection
Característica: Finalitzar venda


  Escenari: Finalitzar o anul.lar una venda buida
    Donat que hi ha una venda iniciada
    I la venda no te linies de venda
    I s'anula la venda
    Aleshores no hi ha cap venda iniciada

  Escenari: Anular una venda amb linies de venda
    Donat que hi ha una venda iniciada
    I existeix el producte "Baldufa" amb codi de barres "123456789" i preu per unitat 3,50
    I introdueixo al tpv el producte per nom "Baldufa"
    I introdueixo al tpv el producte per nom "Baldufa"
    I introdueixo al tpv el producte per nom "Baldufa"
    Quan s'anula la venda
    Aleshores no hi ha cap venda iniciada

  Escenari: Finalitzar una venda amb linies de venda
    Donat que hi ha una venda iniciada
    I existeix el producte "Baldufa" amb codi de barres "123456789" i preu per unitat 5,0
    I existeix el producte "GirafaRock" amb codi de barres "122156659" i preu per unitat 4,50
    I introdueixo al tpv el producte per nom "Baldufa"
    I introdueixo al tpv el producte per nom "GirafaRock"
    Quan es finalitza la venda
    I el client paga 10 euros en efectiu
    Aleshores el valor a retornar al client és de 0,50
