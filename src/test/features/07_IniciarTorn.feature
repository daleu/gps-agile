#language: ca

#noinspection SpellCheckingInspection
Característica: Iniciar torn

  Escenari: Iniciar un torn
    Donat el TPV esta a la botiga "Carroça"
    Quan inicio un torn amb nom empleat "Joan"
    Aleshores el TPV està sent usat pel "Joan"
    I obtinc un missatge que diu "Bon dia, l'atent en Joan"

  Escenari: Iniciar un torn quan ja n'hi ha un d'iniciat
    Donat el TPV esta a la botiga "Carroça"
    I el "Joan" ha iniciat un torn
    Quan inicio un torn amb nom empleat "Marc"
    Aleshores obtinc un missatge que diu "Ja hi ha un torn iniciat"

