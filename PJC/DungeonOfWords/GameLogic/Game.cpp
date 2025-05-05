#include "SFML/Graphics.hpp"
#include <iostream>
#include <fstream>
#include "Game.h"

Game::Game() : window(sf::VideoMode(1280, 768), "Dungeon Of Words",
                      sf::Style::Default, sf::ContextSettings(0, 0, 8)) {
    window.setFramerateLimit(60);


    mainFont.loadFromFile("/Users/wojteksujczynski/PJCPROJECT/Fonts/upheavtt.ttf");


    wallTexture.loadFromFile("/Users/wojteksujczynski/PJCPROJECT/texture/wallTexture.png");

    wall.setTexture(wallTexture);

    fontTitle.loadFromFile("/Users/wojteksujczynski/PJCPROJECT/Fonts/alagard.ttf");
    fontButtons.loadFromFile("/Users/wojteksujczynski/PJCPROJECT/Fonts/upheavtt.ttf");

    wallTexture.loadFromFile("/Users/wojteksujczynski/PJCPROJECT/texture/wallTexture.png");
    wall.setTexture(wallTexture);

    sliderDifficultyTexture.loadFromFile("/Users/wojteksujczynski/PJCPROJECT/texture/slider8.png");
    sliderDifficulty.setTexture(sliderDifficultyTexture);

    cursorSizeTexture.loadFromFile("/Users/wojteksujczynski/PJCPROJECT/texture/cursor3.png");
    cursorSize.setTexture(cursorSizeTexture);

    cursorSize.setPosition(455,55);

    sliderFontTexture.loadFromFile("/Users/wojteksujczynski/PJCPROJECT/texture/slider8.png");
    sliderFont.setTexture(sliderDifficultyTexture);

    cursorFontTexture.loadFromFile("/Users/wojteksujczynski/PJCPROJECT/texture/cursor3.png");
    cursorFont.setTexture(cursorSizeTexture);

    cursorFont.setPosition(455,257);

    fontPick1.loadFromFile("/Users/wojteksujczynski/PJCPROJECT/Fonts/pick1.ttf");
    fontPick2.loadFromFile("/Users/wojteksujczynski/PJCPROJECT/Fonts/pick2.ttf");
    fontPick3.loadFromFile("/Users/wojteksujczynski/PJCPROJECT/Fonts/pick3.ttf");

    fullHeart.loadFromFile("/Users/wojteksujczynski/PJCPROJECT/texture/heartFull.png");
    empthyHeart.loadFromFile("/Users/wojteksujczynski/PJCPROJECT/texture/heartEmpty.png");

    heart1.setTexture(fullHeart);
    heart2.setTexture(fullHeart);
    heart3.setTexture(fullHeart);

    heart1.setPosition(sf::Vector2f(1080,10));
    heart2.setPosition(sf::Vector2f(1140,10));
    heart3.setPosition(sf::Vector2f(1200,10));

    //words: txt --> vector
    std::string line;
    std::ifstream file;


    file.open("words.txt"); //Set path from words are taken
    if (file.is_open()) {
        while (std::getline(file, line)) {
            words.push_back(line);
            counter++;
        }
    }


}







