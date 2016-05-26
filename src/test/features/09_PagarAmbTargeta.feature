# language: ca

  #noinspection SpellCheckingInspection
#TODO: Comprovar que les tarjetes son valids
#http://www.fivecentnickel.com/2010/03/01/how-do-you-know-if-a-credit-card-number-is-valid/
Característica: Escollir pagar amb targeta una venda.
  Rerefons:
    Donat el TPV esta a la botiga "May The Force Be With You"
    I existeix el producte "Rasca i pica", amb codi de barres "222", preu Unitat 15,2 i iva 0,21
    I existeix el producte "Patins en linia", amb codi de barres "333", preu Unitat 30,5 i iva 0,21
    I inicio una nova venda
    I s'afegeix a la linia de venda 2 unitats del producte amb codi de barres "222"
    I s'afegeix a la linia de venda 1 unitats del producte amb codi de barres "333"
    I l'empleat que ha iniciat la venda es diu "Johnny Depp"

  Escenari: El client paga la venda amb tarjeta i s'imprimeix el tiquet
    Quan indico que el client vol pagar amb tarjeta
    I indico que el client paga 60,9 euros amb tarjeta
    I que estem a dia i hora "21/02/2016 20:24:10"
    I es finalitza la venda
    Aleshores el valor a retornar al client és de 0
    I la linia 1 del tiquet sera " | Nom botiga: May The Force Be With You | "
    I la linia 2 del tiquet sera " | Num. Venda: 1 | Codi Tiquet: C1"
    I la linia 3 del tiquet sera " | 2 | Rasca i pica | P.u. 15,2 | P.l. 30,4 | "
    I la linia 4 del tiquet sera " | 1 | Patins en linia | P.u. 30,5 | P.l. 30,5 | "
    I la linia 5 del tiquet sera " | 21.0% | P.B: 50,33 | P.T: 60,9 | "
    I la linia 6 del tiquet sera " | Total: 60,9 | Canvi: 0 | Pagat amb tarjeta | "
    I la linia 7 del tiquet sera " | 21/02/2016 20:24:10 | "
    I la linia 8 del tiquet sera " | Atès per: Johnny Depp | "

  Escenari: El client demana pagar amb tarjeta inicialment però després vol pagar amb efecteu
    Quan indico que el client vol pagar amb tarjeta
    I indico que el client vol pagar en efectiu
    I indico que el client vol pagar amb tarjeta
    I indico que el client paga 60,9 euros amb tarjeta
    I que estem a dia i hora "21/02/2016 20:24:10"
    I es finalitza la venda
    Aleshores el valor a retornar al client és de 0
    I la linia 1 del tiquet sera " | Nom botiga: May The Force Be With You | "
    I la linia 2 del tiquet sera " | Num. Venda: 1 | Codi Tiquet: C1"
    I la linia 3 del tiquet sera " | 2 | Rasca i pica | P.u. 15,2 | P.l. 30,4 | "
    I la linia 4 del tiquet sera " | 1 | Patins en linia | P.u. 30,5 | P.l. 30,5 | "
    I la linia 5 del tiquet sera " | 21.0% | P.B: 50,33 | P.T: 60,9 | "
    I la linia 6 del tiquet sera " | Total: 60,9 | Canvi: 0 | Pagat amb tarjeta | "
    I la linia 7 del tiquet sera " | 21/02/2016 20:24:10 | "
    I la linia 8 del tiquet sera " | Atès per: Johnny Depp | "
