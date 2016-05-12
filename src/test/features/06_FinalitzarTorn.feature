#language: ca

#noinspection SpellCheckingInspection
Característica: Finalitzar un torn

  Rerefons:
    Donat existeix el producte "Baldufa" amb codi de barres "1" i preu per unitat 3.50
    I existeix el producte "Cotxe" amb codi de barres "2" i preu per unitat 22.50
    I existeix el producte "Pilota" amb codi de barres "3" i preu per unitat 1.50
    I que no hi ha cap torn iniciat

  Escenari: Finalitzar un torn per error
    Donat el TPV esta a la botiga "Carroça"
    I que no hi ha cap venda iniciada
    I el "Joan" ha iniciat un torn
    I que hi ha al tpv 0,00 d'efectiu inicial
    Quan finalitzo el torn
    I cancel·lo la finalitzacio del torn
    Aleshores obtinc un missatge que diu "Cancel·lacio acceptada"

  Escenari: Finalitzar un torn
    Donat el TPV esta a la botiga "Carroça"
    I el "Joan" ha iniciat un torn
    I que hi ha al tpv 0,00 d'efectiu inicial
    I que hi ha una venda iniciada
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "1"
    I que hi ha una linia de venda amb 1 unitats del producte amb codi de barres "2"
    I que hi ha una linia de venda amb 4 unitats del producte amb codi de barres "3"
    I es finalitza la venda
    I que hi ha una venda iniciada
    I que hi ha una linia de venda amb 1 unitats del producte amb codi de barres "1"
    I que hi ha una linia de venda amb 3 unitats del producte amb codi de barres "3"
    I es finalitza la venda
    Quan introdueixo al tpv 44,00 d'efectiu final per fer el quadrament
    I finalitzo el torn
    Aleshores el tpv té 44,00 d'efectiu final


  Escenari: Introduir capital final més d'un cop fins quadrar i finalitzar el torn
    Donat el TPV esta a la botiga "Carroça"
    I el "Joan" ha iniciat un torn
    I que hi ha al tpv 0,00 d'efectiu inicial
    I que hi ha una venda iniciada
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "1"
    I que hi ha una linia de venda amb 1 unitats del producte amb codi de barres "2"
    I que hi ha una linia de venda amb 4 unitats del producte amb codi de barres "3"
    I es finalitza la venda
    I que hi ha una venda iniciada
    I que hi ha una linia de venda amb 1 unitats del producte amb codi de barres "1"
    I que hi ha una linia de venda amb 3 unitats del producte amb codi de barres "3"
    I es finalitza la venda
    Quan introdueixo al tpv 55,20 d'efectiu final per fer el quadrament
    I introdueixo al tpv 50,70 d'efectiu final per fer el quadrament
    I introdueixo al tpv 44,50 d'efectiu final per fer el quadrament
    I finalitzo el torn
    Aleshores el tpv té 44,50 d'efectiu final

  Escenari: Acceptem el desquadrament quan la diferència és > 5€ i finalitzem el torn
    Donat el TPV esta a la botiga "Carroça"
    I el "Joan" ha iniciat un torn
    I que hi ha al tpv 0,00 d'efectiu inicial
    I que hi ha una venda iniciada
    I que hi ha una linia de venda amb 2 unitats del producte amb codi de barres "1"
    I que hi ha una linia de venda amb 1 unitats del producte amb codi de barres "2"
    I que hi ha una linia de venda amb 4 unitats del producte amb codi de barres "3"
    I es finalitza la venda
    I que hi ha una venda iniciada
    I que hi ha una linia de venda amb 1 unitats del producte amb codi de barres "1"
    I que hi ha una linia de venda amb 3 unitats del producte amb codi de barres "3"
    I es finalitza la venda
    Quan introdueixo al tpv 55,20 d'efectiu final per fer el quadrament
    I finalitzo el torn amb 55,20 d'efectiu final acceptant el desquadrament
    Aleshores el tpv té 55,20 d'efectiu final





