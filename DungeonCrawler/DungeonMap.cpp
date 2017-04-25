/*
 * DungeonMap.cpp
 *
 *  Created on: 23.04.2017
 *      Author: sebastian
 */

#include "DungeonMap.h"

DungeonMap::DungeonMap(const unsigned int height,const unsigned int width)
{
    m_maxHeight = height;
    m_maxWidth = width;
//    m_map = Tile[height](Tile::Floor,nullptr);
//    for(unsigned int i = 0; i < height; i++)
//        m_map[i] = new Tile[width];
//
//    for (unsigned int i = 0; i < height; i++)
//        for (unsigned int j = 0; j < width; j++)
//            m_map[i][j] = nullptr;
}

DungeonMap::DungeonMap(int height, int width, const vector<string>& data)
{
    m_maxHeight = height;
    m_maxWidth = width;
    m_map = new Tile[height][width];

    unsigned int count = 0;
    for (unsigned int i = 0; i < height; i++)
        for (unsigned int j = 0; j < width; j++)
        {
            if (data[count] == ".")
                ; //m_map[i][j] = new Tile(Tile::Typ Floor, nullptr);
            else
                ;
        }
}
