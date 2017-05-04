/*
 * GameEngine.h
 *
 *  Created on: 23.04.2017
 *      Author: sebastian
 */

#ifndef GAMEENGINE_H_
#define GAMEENGINE_H_
#include "DungeonMap.h"

class GameEngine
{
public:
    GameEngine(const unsigned int height, const unsigned int width,
               const vector<string>& data);
    void run();
    void turn();
    bool finished();
    ~GameEngine();
private:
    int round;
    DungeonMap m_map;
    vector<Character *> characters;
};

#endif /* GAMEENGINE_H_ */