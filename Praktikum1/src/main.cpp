//============================================================================
// Name        : Praktikum1.cpp
// Author      : Sebastian Schuch
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include "PrioQueue.h"
#include "Ticket.h"
using namespace std;

bool menue(PrioQueue<Ticket>& queue);

int main() {

	PrioQueue<Ticket> queue;
	queue.push(Ticket("Runtime Error", "Siehe Unterlagen", 3));
	queue.push(Ticket("Logic Error", "afsdfs", 2));
	queue.push(Ticket("lol", "Siehe Unterlagen", 5));
	while (menue(queue))
		;
	return 0;
}

bool menue(PrioQueue<Ticket>& queue) {
	cout << "1. new ticket \n2. clear \n3. next \n4. EXIT \n" << endl;
	int input;
	Ticket t;
	cin >> input;
	string tmpname, tmptext;
	int prio;
	switch (input) {
	case 1:

		cin >> tmpname;
		cin.ignore(255, '\n');
		getline(cin, tmptext);
		cin >> prio;
		queue.push(Ticket(tmpname, tmptext, prio));
		return 1;
	case 2:
		queue = PrioQueue<Ticket>();
		return 1;
	case 3:
		try {
			t = queue.pop();
		} catch (const string& e) {
			cerr << e << endl;
		}

		t.print();
		return 1;
	case 4:
		return 0;
	default:
		cout << "Invalid input. Exiting";
		return 0;
	}
	return 0;
}
