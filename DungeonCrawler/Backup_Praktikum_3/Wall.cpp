/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   Wall.cpp
 * Author: sebastian
 * 
 * Created on 4. Mai 2017, 16:35
 */

#include "Wall.h"

Wall::Wall() : Tile(nullptr) {
}

void Wall::onLeave(Tile* toTile) {
    //Can't leave wall in first place
}

void Wall::onEnter(Character* c, Tile* fromTile) {
    //
}

char Wall::print() {
    return '#';
}