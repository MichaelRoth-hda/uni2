/*
 * GameEngine.cpp
 *
 *  Created on: 23.04.2017
 *      Author: sebastian
 */

#include "GameEngine.h"

GameEngine::GameEngine(const unsigned int height, const unsigned int width,
        const vector<string>& data) :
m_map(height, width, data) {

    cout << "Wie viele Runden?" << endl;
    cin >> m_limit;
    //	m_map = DungeonMap(height, width, data);
    characters.push_back(new Character('o')); //Wegen pointer
    Position pos;
    pos.height = 5;
    pos.width = 5;
    m_map.place(pos, characters.at(0));
    m_round = 0;

}

void GameEngine::run() {
    while (!finished())
        turn();
}

void GameEngine::turn() {

    for (unsigned int i = 0; i < characters.size(); i++) {
        Position pos;
        try {
            pos = m_map.findCharacter(characters.at(i));
        } catch (const invalid_argument& ie) {
            cerr << "Error in turn: " << ie.what() << '\n';
        }
        Tile* oldTile = m_map.findTile(pos);
        Position newPos = pos;
        Tile* newTile;
        int eingabe = characters.at(i)->move();
        switch (eingabe) {
            case 1:
                newPos.height++;
                newPos.width--;
                break;
            case 2:
                newPos.height++;
                break;
            case 3:
                newPos.height++;
                newPos.width++;
                break;
            case 4:
                newPos.width--;
                break;
            case 5:

                break;
            case 6:
                newPos.width++;
                break;
            case 7:
                newPos.height--;
                newPos.width--;
                break;
            case 8:
                newPos.height--;
                break;
            case 9:
                newPos.height--;
                newPos.width++;

                break;
            case 0:
                m_round = m_limit;
                break;
            default:
                cout << "Fehler in switchcase in turn()";
                break;
        }

        newTile = m_map.findTile(newPos);
        oldTile->onLeave(newTile);
    }

}

bool GameEngine::finished() {
    m_round++;
    cout << m_round << endl;
    m_map.print();
    if (m_round <= m_limit)
        return false;
    else
        return true;
}

GameEngine::~GameEngine() {
    for (int i = 0; i < characters.size(); i++)
        delete characters.at(i);
    characters.erase(characters.begin(), characters.end());
    m_map.~DungeonMap();
}
