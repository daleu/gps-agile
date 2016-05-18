#language: ca

#noinspection SpellCheckingInspection
Característica: Finalitzar un torn

  Rerefons:
    Donat existeix el producte "Baldufa" amb codi de barres "1" i preu per unitat 3,50
    I existeix el producte "Cotxe" amb codi de barres "2" i preu per unitat 22,50
    I existeix el producte "Pilota" amb codi de barres "3" i preu per unitat 1,50
    I que no hi ha cap torn iniciat
    I el TPV esta a la botiga "Carroça"
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

    #total venut al torn 43,50€

  Escenari: Finalitzar un torn per error
    Quan finalitzo el torn
    I cancel·lo la finalitzacio del torn
    Aleshores obtinc un missatge que diu "Cancel·lacio acceptada. En Joan continua amb el seu torn"
    I el torn no es finalitza

  Escenari: Finalitzar un torn amb quadrament
    Quan finalitzo el torn
    I introdueixo al tpv 44,00 d'efectiu final per fer el quadrament
    Aleshores el tpv té 44,00 d'efectiu final
    I el torn està finalitzat
    I obtinc un missatge que diu "Torn finalitzat amb quadrament de caixa"

  Escenari: Introducció erronia de l'efectiu final per >+5€
    Quan finalitzo el torn 
    I introdueixo al tpv 55,20 d'efectiu final per fer el quadrament
    Aleshores el torn no es finalitza
    I obtinc un missatge que diu "Torn no finalitzat. L'efectiu en caixa introduit és superior al suposat per més de 5 euros"

  Escenari: Introducció repetida erronia de l'efectiu final per >+5€ i >-5€
    Donat finalitzo el torn
    I introdueixo al tpv 55,20 d'efectiu final per fer el quadrament
    I el torn no es finalitza
    I obtinc un missatge que diu "Torn no finalitzat. L'efectiu en caixa introduit és superior al suposat per més de 5 euros"
    Quan finalitzo el torn
    I introdueixo al tpv 35,20 d'efectiu final per fer el quadrament
    Aleshores el torn no es finalitza
    I obtinc un missatge que diu "Torn no finalitzat. L'efectiu en caixa introduit és inferior al suposat per més de 5 euros"

  Escenari: Introducció repetida correcta de l'efectiu final
    Donat finalitzo el torn
    I introdueixo al tpv 55,20 d'efectiu final per fer el quadrament
    I el torn no es finalitza
    I obtinc un missatge que diu "Torn no finalitzat. L'efectiu en caixa introduit és superior al suposat per més de 5 euros"
    Quan finalitzo el torn
    I introdueixo al tpv 44,00 d'efectiu final per fer el quadrament
    Aleshores el tpv té 44,00 d'efectiu final
    I el torn està finalitzat
    I obtinc un missatge que diu "Torn finalitzat amb quadrament de caixa"

  Escenari: Finalitzar un torn acceptant el desquadrament
    Donat finalitzo el torn
    I introdueixo al tpv 55,20 d'efectiu final per fer el quadrament
    I el torn no es finalitza
    I obtinc un missatge que diu "Torn no finalitzat. L'efectiu en caixa introduit és superior al suposat per més de 5 euros"
    Quan accepto el desquadrament
    Aleshores el tpv té 55,20 d'efectiu final
    I el torn està finalitzat
    I obtinc un missatge que diu "Torn finalitzat amb desquadrament de caixa"