package pao;

import pao.View.Controller;

/**
 * Created by rares on 31-Mar-17.
 */

/*
Sa se implementeze o aplicatie de tipul CRM (Customer relationship management).
Aplicatia trebuie sa permita managementul clientilor unei companii, emiterea de facturi,
editare de produse (ale companiei), stocuri, preturi etc. (Produsele pot fi bunuri sau servicii).

2p:
Adaugare/editare clienti (persoana fizica/persoana juridica, formulare diferite)
Management produse (adaugare, editare, stoc, pret etc)
Atentie la istoric! Modificarea unui produs nu trebuie sa afecteze facturile precedente!
Generare facturi (formular de adaugare factura pentru clientul X si produsele A,B,C,D)
Rapoarte de vanzari/activitate pe produs si pe client
Cautare dupa diversi parametri (criterii complexe de cautare)
 */


//http://stackoverflow.com/questions/12462964/mvc-do-i-need-to-use-controller-in-the-view


    //DUPA CE IMPORT LISTA DE CLIENTI, DACA ADAUG UNUL NOU, IA DE LA CAPAT SERIALIZAREA

public class Main {


    public static void main (String[] args) {
        Controller control = new Controller();
    }
}
