#language: ca

#noinspection SpellCheckingInspection
Característica: Crear vals de descompte per percentatge

  Rerefons:
    Donat s'introdueix al sistema els vals de descompte per percentatge:
      | 25 | 07/10/2020 |
      | 50 | 23/04/2020 |
      | 33 | 10/05/2020 |
    I s'introdueix al sistema el val per import 10€, data de caducitat "07/10/2020" i import minim 50€
    I s'introdueix al sistema el val per import 20€, data de caducitat "23/04/2020" i import minim 100€
    I s'introdueix al sistema el val per import 30€, data de caducitat "10/05/2020" i import minim 150€


  Escenari: LListar vals de descompte
    Quan vull veure els vals de descompte existents
    Aleshores la linia 1 de la llista de Descomptes sera "Descompte de 33.0% | Caduca el 10/05/2020"
    I la linia 2 de la llista de Descomptes sera "Descompte de 25.0% | Caduca el 07/10/2020"
    I la linia 3 de la llista de Descomptes sera "Descompte de 50.0% | Caduca el 23/04/2020"
    I la linia 4 de la llista de Descomptes sera "Descompte de 10.0€ | Import minim de 50.0€ | Caduca el 07/10/2020"
    I la linia 5 de la llista de Descomptes sera "Descompte de 20.0€ | Import minim de 100.0€ | Caduca el 23/04/2020"
    I la linia 6 de la llista de Descomptes sera "Descompte de 30.0€ | Import minim de 150.0€ | Caduca el 10/05/2020"