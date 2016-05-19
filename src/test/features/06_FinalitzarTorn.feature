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
    I es va fer una venda amb id 111 dels següens productes i seguents unitats
      |1|2|
      |2|1|
      |3|4|
    I es finalitza la venda
    I es va fer una venda amb id 112 dels següens productes i seguents unitats
      |1|1|
      |3|3|
    I es finalitza la venda

    #total venut al torn 43,50€

  Escenari: Finalitzar un torn per error
    Quan finalitzo el torn
    I cancel·lo la finalitzacio del torn
    Aleshores el torn segueix actiu
    I obtinc un missatge que diu "Cancel·lacio acceptada. En Joan continua amb el seu torn"

  Escenari: Finalitzar un torn amb quadrament
    Quan finalitzo el torn
    I introdueixo al tpv 43,50 d'efectiu final per fer el quadrament
    Aleshores el tpv té 43,50 d'efectiu final
    I el torn està finalitzat
    I obtinc un missatge que diu "Torn finalitzat amb quadrament de caixa"

  Escenari: Introducció erronia de l'efectiu final per >+5€
    Quan finalitzo el torn 
    I introdueixo al tpv 55,20 d'efectiu final per fer el quadrament
    Aleshores el torn segueix actiu
    I obtinc un missatge que diu "Torn no finalitzat. La diferència és major o igual a 5 euros entre l'efectiu en caixa introduit i el suposat"

  Escenari: Introducció erronia de l'efectiu final per <+5€
    Quan finalitzo el torn
    I introdueixo al tpv 44,00 d'efectiu final per fer el quadrament
    Aleshores el torn segueix actiu
    I obtinc un missatge que diu "Torn no finalitzat. La diferència és menor a 5 euros entre l'efectiu en caixa introduit i el suposat"

  Escenari: Introducció repetida erronia de l'efectiu final per >+5€ i >-5€
    Donat finalitzo el torn
    I introdueixo al tpv 55,20 d'efectiu final per fer el quadrament
    I el torn segueix actiu
    I obtinc un missatge que diu "Torn no finalitzat. La diferència és major o igual a 5 euros entre l'efectiu en caixa introduit i el suposat"
    Quan finalitzo el torn
    I introdueixo al tpv 35,20 d'efectiu final per fer el quadrament
    Aleshores el torn segueix actiu
    I obtinc un missatge que diu "Torn no finalitzat. La diferència és major o igual a 5 euros entre l'efectiu en caixa introduit i el suposat"

  Escenari: Introducció repetida correcta de l'efectiu final
    Donat finalitzo el torn
    I introdueixo al tpv 55,20 d'efectiu final per fer el quadrament
    I el torn segueix actiu
    I obtinc un missatge que diu "Torn no finalitzat. La diferència és major o igual a 5 euros entre l'efectiu en caixa introduit i el suposat"
    Quan finalitzo el torn
    I introdueixo al tpv 43,50 d'efectiu final per fer el quadrament
    Aleshores el tpv té 43,50 d'efectiu final
    I el torn està finalitzat
    I obtinc un missatge que diu "Torn finalitzat amb quadrament de caixa"

  Escenari: Finalitzar un torn acceptant el desquadrament
    Donat finalitzo el torn
    I introdueixo al tpv 55,20 d'efectiu final per fer el quadrament
    I el torn segueix actiu
    I obtinc un missatge que diu "Torn no finalitzat. La diferència és major o igual a 5 euros entre l'efectiu en caixa introduit i el suposat"
    Quan accepto el desquadrament
    Aleshores el tpv té 55,20 d'efectiu final
    I el torn està finalitzat
    I obtinc un missatge que diu "Torn finalitzat amb desquadrament de caixa"

  Escenari: Finalitzar un torn quadrant amb pagaments amb targeta
    Donat es va fer una venda amb id 113 dels següens productes i seguents unitats
      |1|2|
      |3|5|
    I indico que el client vol pagar amb tarjeta
    I es finalitza la venda
    Quan finalitzo el torn
    I introdueixo al tpv 43,50 d'efectiu final per fer el quadrament
    Aleshores el tpv té 43,50 d'efectiu final
    I el torn està finalitzat
    I obtinc un missatge que diu "Torn finalitzat amb quadrament de caixa"