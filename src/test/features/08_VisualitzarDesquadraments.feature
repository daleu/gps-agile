#language: ca

#noinspection SpellCheckingInspection
Característica: Visualitzar quadraments

  Rerefons:
    Donat existeix el producte "Baldufa" amb codi de barres "1" i preu per unitat 3,50
    I existeix el producte "Cotxe" amb codi de barres "2" i preu per unitat 22,50
    I existeix el producte "Pilota" amb codi de barres "3" i preu per unitat 1,50
    I el TPV esta a la botiga "Carroça"
    I que no hi ha cap torn iniciat
    I el "Joan" ha iniciat un torn

  Escenari: Visualitzar quadraments
    I que hi ha al tpv 0,00  d'efectiu inicial
    I es va fer una venda amb id 111 dels següens productes i seguents unitats
      |1|2|
      |2|1|
      |3|4|
    I es finalitza la venda
    I es va fer una venda amb id 112 dels següens productes i seguents unitats
      |1|1|
      |3|3|
    I es finalitza la venda
    I que estem a dia i hora "12/04/2016 10:31:47"
    I finalitzo el torn
    #total venut al torn 43,50€
    I introdueixo al tpv 43,50 d'efectiu final per fer el quadrament
    I el "Ernest" ha iniciat un torn
    I que hi ha al tpv 43,50  d'efectiu inicial
    I es va fer una venda amb id 113 dels següens productes i seguents unitats
      |1|2|
      |2|1|
      |3|4|
    I es finalitza la venda
    I es va fer una venda amb id 114 dels següens productes i seguents unitats
      |1|1|
      |3|3|
    I es finalitza la venda
    I que estem a dia i hora "12/04/2016 12:32:20"
    I finalitzo el torn
    #total venut al torn 43,50€
    #total a la caixa 87,00€
    I introdueixo al tpv 86,50 d'efectiu final per fer el quadrament
    I accepto el desquadrament
    I el "Pau" ha iniciat un torn
    I que hi ha al tpv 86,50  d'efectiu inicial
    I es va fer una venda amb id 115 dels següens productes i seguents unitats
      |1|2|
      |2|1|
      |3|4|
    I es finalitza la venda
    I es va fer una venda amb id 116 dels següens productes i seguents unitats
      |1|1|
      |3|3|
    I indico que el client vol pagar amb tarjeta
    I es finalitza la venda
    I que estem a dia i hora "12/04/2016 14:34:50"
    I finalitzo el torn
    #total venut al torn 43,50€
    #total a la caixa 122,00€
    I introdueixo al tpv 123,00 d'efectiu final per fer el quadrament
    I accepto el desquadrament
    Quan indico que vull visualitzar els desquadraments
    Aleshores obtinc 2 linies
    I la linia 1 sera "TORN 2: NomEmpleat: Ernest | Botiga: Carroça | Data i Hora: 12/04/2016 12:32:20 | EfectiuInicial: 43.5 | EfectiuFinal: 86.5 | Diferencia: -0.5"
    I la linia 2 sera "TORN 3: NomEmpleat: Pau | Botiga: Carroça | Data i Hora: 12/04/2016 14:34:50 | EfectiuInicial: 86.5 | EfectiuFinal: 123.0 | Diferencia: 1.0"