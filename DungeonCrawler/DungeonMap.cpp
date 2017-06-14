/*
 * DungeonMap.cpp
 *
 *  Created on: 23.04.2017
 *      Author: sebastian
 */

//#include <memory>

#include "DungeonMap.h"
#include "Glass.h"
#include <math.h>
#include <memory>
#include <map>
#include <stdint.h>

DungeonMap::DungeonMap(const unsigned int height, const unsigned int width,
        const vector<string>& data) {
    m_maxHeight = height;
    m_maxWidth = width;

    m_map = new Tile**[height];
    for (unsigned int i = 0; i < height; i++) {
        m_map[i] = new Tile*[width];
        for (unsigned int j = 0; j < width; j++) {
            // cout << i << " " << j << endl;
            switch (data.at(i).at(j)) {
                case '.':
                    m_map[i][j] = new Floor(nullptr, nullptr);
                    break;
                case '#':
                    m_map[i][j] = new Wall();
                    break;
                case 'X':
                    m_map[i][j] = new Door();
                    break;
                case '/':
                    m_map[i][j] = new Door();
                    dynamic_cast<Door*> (m_map[i][j])->use();
                    break;
                case 'S':
                    m_map[i][j] = new Switch(nullptr);
                    break;
                case 's':
                    m_map[i][j] = new Switch(nullptr);
                    dynamic_cast<Switch*> (m_map[i][j])->use();
                    break;
                case 'L':
                    m_map[i][j] = new Lever(nullptr);
                    break;
                case 'l':
                    m_map[i][j] = new Lever(nullptr);
                    dynamic_cast<Lever*> (m_map[i][j])->use();
                    break;
                case 't':
                    m_map[i][j] = new Trap(nullptr, -20);
                    break;
                case 'T':
                    m_map[i][j] = new Trap(nullptr, -20);
                    dynamic_cast<Trap*> (m_map[i][j])->setActivated(true);
                    break;
                case ' ':
                    m_map[i][j] = new Glass();
                    break;
                default:
                    throw std::runtime_error("invalid Tile Type requested");
            }
        }
    }
}

DungeonMap::~DungeonMap() {
    for (unsigned int i = 0; i < m_maxHeight; i++) {
        for (unsigned int j = 0; j < m_maxWidth; j++)
            delete m_map[i][j];
        delete[] m_map[i];
    }
    delete[] m_map;

    m_map = nullptr;
    m_maxHeight = 0;
    m_maxWidth = 0;
}

/*
DungeonMap::DungeonMap(DungeonMap& map) {
    m_maxHeight = map.m_maxHeight;
    m_maxWidth = map.m_maxWidth;
    Position pos;
    m_map = new Tile**[m_maxHeight];
    for (unsigned int i = 0; i < m_maxHeight; i++) {
        pos.height = i;
        m_map[i] = new Tile*[m_maxWidth];
        for (unsigned int j = 0; j < m_maxWidth; j++) {
            pos.width = j;
            m_map[i][j] = map.findTile(pos);
        }
    }
}*/

void DungeonMap::place(Position pos, Character* c) {
    if (pos.height > m_maxHeight || pos.width > m_maxWidth)
        throw out_of_range("Zielposition außerhalb des Spielfelds");
    m_map[pos.height][pos.width]->setCharacter(c);
}

Position DungeonMap::findTile(Tile* t) {
    for (unsigned int i = 0; i < m_maxHeight; i++) {
        for (unsigned int j = 0; j < m_maxWidth; j++) {
            if (m_map[i][j] == t) {
                Position p;
                p.height = i;
                p.width = j;
                return p;
            }
        }
    }
    throw out_of_range("Tile außerhalb des Spielfelds");
}

Tile* DungeonMap::findTile(const Position pos) {
    if (pos.height >= m_maxHeight || pos.width >= m_maxWidth)
        throw out_of_range("Gesuchtes Tile außerhalb des Spielfelds");
    return m_map[pos.height][pos.width];
}

Position DungeonMap::findCharacter(Character* c) {
    for (unsigned int i = 0; i < m_maxHeight; i++) {
        for (unsigned int j = 0; j < m_maxWidth; j++) {
            if (m_map[i][j]->getCharacter() == c) {
                Position p;
                p.height = i;
                p.width = j;
                return p;
            }
        }
    }
    throw invalid_argument("Character außerhalb des Spielfelds");
}

void DungeonMap::print(Position center) {

    for (unsigned int i = 0; i < m_maxHeight; i++) {
        for (unsigned int j = 0; j < m_maxWidth; j++) {
            Position aktPos;
            aktPos.height = i;
            aktPos.width = j;
            if (hasLineOfSight(center, aktPos)) {
                cout << m_map[i][j]->print();
            } else {
                cout << "#";
            }
        }
        cout << endl;
    }

}

bool DungeonMap::hasLineOfSight(Position from, Position to) {
    double x = to.width - from.width;
    double y = to.height - from.height;
    double len = sqrt((x * x) + (y * y));

    if (!len) //eigene Tile
        return true;

    double stepx = x / len;
    double stepy = y / len;

    x = from.width;
    y = from.height;
    for (double i = 0; i < len; i += 1) {
        if (m_map[static_cast<int> (y)][static_cast<int> (x)]->isTransparent() == false)
            return false;

        x += stepx;
        y += stepy;
    }
    return true; //hat sicht*/
}

ostream& operator<<(ostream& outputstream, const DungeonMap& map) {

    outputstream << map.m_maxHeight << " " << map.m_maxWidth << "\n";
    for (int i = 0; i < map.m_maxHeight; i++) {
        for (int j = 0; j < map.m_maxWidth; j++) {
            outputstream << map.m_map[i][j]->save();
        }
        outputstream << "\n";
    }
    outputstream << "\n";

    return outputstream;
}

void DungeonMap::saveItems(ostream& outputstream) {
    for (int i = 0; i < m_maxHeight; i++) {
        for (int j = 0; j < m_maxWidth; j++) {
            if (dynamic_cast<Floor*> (m_map[i][j]) != nullptr) {
                if (dynamic_cast<Floor*> (m_map[i][j])->getItem() != nullptr)
                    outputstream << *(dynamic_cast<Floor*> (m_map[i][j])->getItem()) << " " << i << "/" << j << endl;
            }
        }
    }
}

const vector<Position> DungeonMap::getPathTo(const Position from, const Position to) {
    vector<Position> pfad;
    set<Position> Q;
    map<Position, Position> prev;
    map<Position, int> dist;


    Position pos(0, 0);
    for (pos; pos.height < m_maxHeight; pos.height++) {
        for (pos; pos.width < m_maxWidth; pos.width++) {
            if (findTile(pos)->canBeEntered()) {
                Q.insert(pos);
                dist[pos] = numeric_limits<int>::max();
                prev[pos] = Position(-1, -1);
            
            }
        }
        pos.width = 0;
    }
    dist[from] = 0;
    Position u;
    //cout << endl << Q.size() << endl;
    while (Q.size() != 0) {

        //aktuelle Position mit der aktuell gerinstens distanz suchen
        auto itQ = Q.begin();
        u = *itQ;
        while (itQ != Q.end()) {

            if (dist[*itQ] < dist[u])
                u = *itQ;
            itQ++;
        }
        //    if(dist[u] == numeric_limits<int>::max())
        //        return pfad;

        Q.erase(u); // Knoten u aus dem set entfernen
        
        
        //entfernung aller angrenzenden felder zu u updaten
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (findTile(Position(u.height+i, u.width+j))->canBeEntered() == true && dist[u] + 1 < dist[Position(u.height + i, u.width + j)]) {
                    dist[Position(u.height + i, u.width + j)] = dist[u] + 1;
                    prev[Position(u.height + i, u.width + j)] = u;
                }
            }


        }
    }
        pfad.push_back(to);
        Position tmp = to;
        while (tmp != Position(-1, -1)) {
            pfad.push_back(prev[tmp]);
            tmp = prev[tmp];
            if(tmp == prev[tmp])
                return vector<Position>();
        }

    
    
    return pfad;
}
