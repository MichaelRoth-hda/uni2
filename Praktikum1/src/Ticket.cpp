/*
 * Ticket.cpp
 *
 *  Created on: 04.04.2017
 *      Author: sebastian
 */

#include "Ticket.h"

Ticket::Ticket() {
	m_text = "NULL";
	m_owner = "NONE";
	m_status = invalid;
	m_id = -1;
	m_prio = -1;
	id_counter++;
}

Ticket::Ticket(string ownerName, string description, int prio) {
	if (prio < 0 || ownerName == "" || description == "")
		throw "Ungültige Parameter in Konstruktor von Ticket";
	m_text = description;
	m_owner = ownerName;
	m_status = open;
	m_id = id_counter;
	m_prio = prio;

	id_counter++;
}

string Ticket::getText() const {
	return m_text;
}

string Ticket::getOwner() const {
	return m_owner;
}

int Ticket::getId() const {
	return m_id;
}

Status Ticket::getStatus() const {
	return m_status;
}

string Ticket::getShort() const {
	return m_text.substr(0, m_text.find('.'));
}

string Ticket::getStatusAsString() const /*throw(string)*/{
	switch (m_status) {
	case 0:
		return "open";
	case 1:
		return "closed";
	case 2:
		return "duplicate";
	default:
		//throw "Invalid type";
		return "NONE";
	}
}

void Ticket::print() const {
	if (m_id != -1)
		cout << "ID: " << m_id << "\nStatus: " << getStatusAsString()
				<< "\nErsteller: " << m_owner << "\nPriorität: " << m_prio
				<< "\n" << m_text << endl;
}

int Ticket::getPrio() const {
	return m_prio;
}
