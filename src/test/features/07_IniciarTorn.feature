#language: ca

#noinspection SpellCheckingInspection
Característica: Iniciar torn

  Rerefons:
    Donat el TPV esta a la botiga "Carroça"

  Escenari: Iniciar un torn
    Quan inicio un torn amb nom empleat "Joan"
    Aleshores el TPV està sent usat pel "Joan"
    I obtinc un missatge que diu "Bon dia, l'atén en Joan"

  Escenari: Entrar l'efectiu inicial a l'iniciar el torn
    Quan inicio un torn amb nom empleat "Joan"
    I introdueixo al tpv 200,43  d'efectiu inicial
    Aleshores el tpv té 200,43  d'efectiu inicial

  Escenari: Iniciar i finalitzar un torn sense introduir l'efectiu inicial
    Quan inicio un torn amb nom empleat "Joan"
    I cancel·lo el torn sense introduir efectiu inicial
    Aleshores obtinc un missatge que diu "Cancel·lacio acceptada. No hi ha cap torn iniciat"

  Escenari: Iniciar un torn quan ja n'hi ha un d'iniciat
    I el "Joan" ha iniciat un torn
    Quan inicio un torn amb nom empleat "Marc"
    Aleshores obtinc un missatge que diu "Ja hi ha un torn iniciat"

