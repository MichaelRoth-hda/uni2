/*
 * Ticket.h
 *
 *  Created on: 04.04.2017
 *      Author: sebastian
 */

#include <string>
#include <iostream>
using namespace std;

#ifndef TICKET_H_
#define TICKET_H_



enum Status{
	 open,
	 closed,
	 duplicate,
	 invalid
};

struct Ticket{
private:
	//static int id_counter;// = 0;
	string m_text;
	string m_owner;
	Status m_status;
	int m_id;
        int m_prio;
public:
	Ticket();
	Ticket(string ownerName, string description,int prio);
	string getText()  const;
	string getOwner() const;
	int getId() const;
	Status getStatus() const;
	string getShort() const;
	string getStatusAsString() const;
	void print() const;
        int getPrio() const;
};

static int id_counter = 0;
#endif /* TICKET_H_ */