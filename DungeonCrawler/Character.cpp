/*
 * Character.cpp
 *
 *  Created on: 23.04.2017
 *      Author: sebastian
 */




#include "Character.h"
#include <iostream>
using namespace std;

Character::Character(char symbol)
{
    m_symbol = symbol;
};

char Character::getSymbol()
{
    return m_symbol;
}

int Character::move()
{
    int returnvalue;
    cout << "\n Wohin möchten sie laufen?\n";
    cin>>returnvalue;
    if(isdigit(returnvalue)){
        return returnvalue;
    }else{
        cout<<"\nFehler bei der Eingabe!"<<endl;
        return -1;
    }
    return -1;
}
