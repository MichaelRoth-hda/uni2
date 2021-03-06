/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   CUI.h
 * Author: sebastian
 *
 * Created on 7. Juni 2017, 23:45
 */

#ifndef CUI_H
#define CUI_H

#include "Flugplan.h"
#include "Kunde.h"
#include "Ticket.h"
#include <map>
#include <iostream>

class CUI {
public:
    CUI();
    CUI(const CUI& orig);
    virtual ~CUI();
    bool login(string username, string pwd);
    void erstelleBuchung(int flugnummer, int nTickets);
    bool zeigeMenue();
    void gefundeneFluegeAnzeigen();
    
private:
    Flugplan m_flugplan;
    map<string, Kunde> m_kunden;
    map<int, Ticket> m_tickets;
    map<int, Buchung> m_buchungen;
    const string MENU = "Menu:\n1.login";
};

#endif /* CUI_H */

