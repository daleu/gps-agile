# language: ca

  #noinspection SpellCheckingInspection
Característica: Retornar un producte
  Rerefons:
    Donat existeix el producte "Plastilina de 20 colors" amb codi de barres "444" i preu per unitat 25
    I existeix el producte "Action Man" amb codi de barres "777" i preu per unitat 15
    I existeix el producte "Baldufa" amb codi de barres "333" i preu per unitat 3.25
    I existeix el producte "Cotxe telefirigit" amb codi de barres "222" i preu per unitat 25
    I es va fer una venda amb el codi 111 amb 2 productes amb codi "777" i 1 producte amb codi "333"
    I inicio una nova venda
    I s'afegeix a la linia de venda 3 unitats del producte amb codi de barres "333"
    I s'afegeix a la linia de venda 1 unitats del producte amb codi de barres "222"

    #TODO en aquest escenari potser no se li hauria de dir finalització de la venda sino de la devolucio
    #crea confusió més endavant
Escenari: Retornar un sol producte amb saldo positiu
   Quan vull fer una devolucio
   I es vol retornar 1 unitat del producte amb codi "777" de la venda 111 pel motiu "Defectuos"
   I es finalitza la venda
   Aleshores existeix una devolucio del producte "777" de la venda 111 pel motiu "Defectuos"
